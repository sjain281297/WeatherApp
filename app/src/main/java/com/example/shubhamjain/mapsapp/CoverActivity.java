package com.example.shubhamjain.mapsapp;

import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.example.shubhamjain.mapsapp.HourlyForecast.HourlyFragment;
import com.example.shubhamjain.mapsapp.Weather10DayForecast.Weather10DayFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

public class CoverActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,com.google.android.gms.location.LocationListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    GoogleApiClient mApiClient;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        if(mApiClient==null){
            mApiClient=new GoogleApiClient.Builder(CoverActivity.this).addConnectionCallbacks(CoverActivity.this)
                    .addOnConnectionFailedListener(CoverActivity.this)
                    .addApi(LocationServices.API).build();


        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLocationUpdate();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cover, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new MainFragment();
                case 1:
                    return new HourlyFragment();
                case 2:
                    return new MapFragment();
                case 3:
                    return new Weather10DayFragment();
            }

            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Now";
                case 1:
                    return "Hourly";
                case 2:
                    return "Maps";
                case 3:
                    return "Daily";
            }

            return null;
        }
    }

    private void startLocationUpdate(){
        final LocationRequest lr=LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).setInterval(5000).setFastestInterval(1000);

        LocationSettingsRequest.Builder builder=new LocationSettingsRequest.Builder().addLocationRequest(lr);
        LocationServices.SettingsApi.checkLocationSettings(mApiClient,builder.build()).setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                int statusCode=locationSettingsResult.getStatus().getStatusCode();
                if(statusCode== LocationSettingsStatusCodes.SUCCESS){
                    checkPermission();
                    LocationServices.FusedLocationApi.requestLocationUpdates(mApiClient,lr,CoverActivity.this);

                }else if(statusCode==LocationSettingsStatusCodes.RESOLUTION_REQUIRED){
                    try {
                        locationSettingsResult.getStatus()
                                .startResolutionForResult(CoverActivity.this,1);
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

        Toast.makeText(CoverActivity.this,"null",Toast.LENGTH_LONG);

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
        Toast.makeText(CoverActivity.this,la +" "+lo,Toast.LENGTH_LONG).show();
        ep.commit();



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
