package com.example.shubhamjain.mapsapp;

import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shubhamjain.mapsapp.Weather3DayForecast.WeatherApiService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,com.google.android.gms.location.LocationListener {
    TextView temp;
    ImageView weathImg;
    TextView weathText;
    GoogleApiClient mApiClient;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temp=(TextView) findViewById(R.id.temprature);
        weathImg=(ImageView) findViewById(R.id.weather);
        weathText=(TextView)findViewById(R.id.weatherText);
        WeatherApiService apiService=new Retrofit.Builder().baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).build().create(WeatherApiService.class);
        Call<WeatherResponse> call=apiService.getWeather();
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                WeatherResponse.Weather weather=response.body().getWeather().get(0);
                double temprature=response.body().getMain().getTemp();
                Picasso.with(MainActivity.this).load("http://openweathermap.org/img/w/"+weather.getIcon()+".png").into(weathImg);
                weathText.setText(weather.getMain());
                temp.setText(Html.fromHtml(temprature+"<small><small><small><sup>o</sup></small></small></small>"+"C"));


            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

            }
        });



        if(mApiClient==null){
            mApiClient=new GoogleApiClient.Builder(this).addConnectionCallbacks(MainActivity.this).addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build();


        }
    }




    private void startLocationUpdate(){
        final LocationRequest lr=LocationRequest.create().setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY).setInterval(5000);

        LocationSettingsRequest.Builder builder=new LocationSettingsRequest.Builder().addLocationRequest(lr);
        LocationServices.SettingsApi.checkLocationSettings(mApiClient,builder.build()).setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                int statusCode=locationSettingsResult.getStatus().getStatusCode();
                if(statusCode== LocationSettingsStatusCodes.SUCCESS){
                    checkPermission();
                    LocationServices.FusedLocationApi.requestLocationUpdates(mApiClient,lr,MainActivity.this);

                }else if(statusCode==LocationSettingsStatusCodes.RESOLUTION_REQUIRED){
                    try {
                        locationSettingsResult.getStatus()
                                .startResolutionForResult(MainActivity.this,1);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                startLocationUpdate();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onStart() {
        mApiClient.connect();
        startLocationUpdate();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        checkPermission();
        Location mLastLocation=LocationServices.FusedLocationApi.getLastLocation(mApiClient);
        Toast.makeText(MainActivity.this,"null",Toast.LENGTH_LONG);

    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this,"Process Interrupted",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this,"Connection Failed",Toast.LENGTH_LONG).show();

    }


    @Override
    public void onLocationChanged(Location location) {
        sp=getSharedPreferences("shared",MODE_PRIVATE);
        SharedPreferences.Editor ep=sp.edit();
        double la=location.getLatitude();
        double lo=location.getLongitude();
        ep.putString("latitude",location.getLatitude()+"");
        ep.putString("longitude",location.getLongitude()+"");
        ep.commit();

        Toast.makeText(MainActivity.this,la +" " +lo,Toast.LENGTH_LONG).show();

    }

    private void checkPermission() {
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        return;
    }
}
