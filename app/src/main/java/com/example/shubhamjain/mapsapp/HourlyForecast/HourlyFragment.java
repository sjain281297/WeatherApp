package com.example.shubhamjain.mapsapp.HourlyForecast;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shubhamjain.mapsapp.R;
import com.example.shubhamjain.mapsapp.SimpleDividerItemDecoration;
import com.example.shubhamjain.mapsapp.Weather3DayForecast.WeatherApiService;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class HourlyFragment extends Fragment {
    ArrayList<HourlyForecastResponse.HourlyForecast> data;
    RecyclerView hourlyView;
    HourlyAdapter adapter;


    public HourlyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_hourly, container, false);
        hourlyView=(RecyclerView)v.findViewById(R.id.hourlyView);
        data=new ArrayList<>();
        adapter=new HourlyAdapter(data,getActivity());
        hourlyView.setAdapter(adapter);
        WeatherApiService apiService=new Retrofit.Builder().baseUrl("http://api.wunderground.com")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).build().create(WeatherApiService.class);
        SharedPreferences sp=getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        String la=sp.getString("latitude",null);
        String lo=sp.getString("longitude",null);
        String path=la+","+lo+".json";
        Log.i("path",path);
        Call<HourlyForecastResponse> call=apiService.getHourlyForecast(path);
        call.enqueue(new Callback<HourlyForecastResponse>() {
            @Override
            public void onResponse(Call<HourlyForecastResponse> call, Response<HourlyForecastResponse> response) {
                if(response.isSuccessful()){
                    ArrayList<HourlyForecastResponse.HourlyForecast> hourlyForecasts=response.body().getHourly_forecast();

                    if(hourlyForecasts==null){
                        return;
                    }

                    data.addAll(hourlyForecasts);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<HourlyForecastResponse> call, Throwable t) {

            }
        });


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        hourlyView.setLayoutManager(linearLayoutManager);



        return v;
    }

}
