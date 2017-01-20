package com.example.shubhamjain.mapsapp;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shubhamjain.mapsapp.Weather3DayForecast.WeatherApiService;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    TextView temp;
    ImageView weathImg;
    TextView weathText;
    TextView humid;
    TextView DewPoint;
    TextView visibiity;
    TextView precip;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.activity_main, container, false);
        temp=(TextView) v.findViewById(R.id.temprature);
        weathImg=(ImageView) v.findViewById(R.id.weather);
        weathText=(TextView)v.findViewById(R.id.weatherText);
        humid=(TextView)v.findViewById(R.id.humidity);
        precip=(TextView)v.findViewById(R.id.precip);
        visibiity=(TextView)v.findViewById(R.id.visiblity);
        DewPoint=(TextView)v.findViewById(R.id.dewPoint);
        WeatherApiService apiService=new Retrofit.Builder().baseUrl("http://api.wunderground.com")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).build().create(WeatherApiService.class);
        SharedPreferences sp=getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        String la=sp.getString("latitude",null);
        String lo=sp.getString("longitude",null);
        String path=la+","+lo+".json";
        Call<CurrentResponse> call=apiService.getCurrentWeather(path);
        final ProgressDialog mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        call.enqueue(new Callback<CurrentResponse>() {
            @Override
            public void onResponse(Call<CurrentResponse> call, Response<CurrentResponse> response) {
                CurrentResponse.Condition condition=response.body().getCurrent_observation();
                if(condition==null){
                    return;
                }
                if(mProgressDialog.isShowing()){
                    mProgressDialog.dismiss();
                }
                double temperature=condition.getTemp_c();
                Picasso.with(getActivity()).load(condition.getIcon_url()).into(weathImg);
                weathText.setText(condition.getWeather());
                temp.setText(Html.fromHtml(temperature+"<small><small><small><sup>o</sup></small></small></small>"+"C"));
                humid.setText(condition.getRelative_humidity());
                precip.setText(condition.getPrecip_today_metric());
                visibiity.setText(condition.getVisibility_mi());
                DewPoint.setText(condition.getDewpoint_c().toString());
            }

            @Override
            public void onFailure(Call<CurrentResponse> call, Throwable t) {

            }
        });



        return v;
    }

}
