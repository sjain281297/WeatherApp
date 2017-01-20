package com.example.shubhamjain.mapsapp.Weather3DayForecast;


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
import com.example.shubhamjain.mapsapp.WeatherResponse;
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
public class WeatherFragment extends Fragment {

    ArrayList<WeatherListResponse.Forecast.SimpleForcast.ForecastDay> data;
    RecyclerView weatherView;
    WeatherAdapter adapter;

    public static WeatherFragment newInstance() {
        WeatherFragment fragment = new WeatherFragment();
        return fragment;
    }


    public WeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_weather, container, false);

        weatherView=(RecyclerView)v.findViewById(R.id.weatherView);
        data=new ArrayList<>();
        adapter=new WeatherAdapter(data,getActivity());
        weatherView.setAdapter(adapter);
        WeatherApiService apiService=new Retrofit.Builder().baseUrl("http://api.wunderground.com")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).build().create(WeatherApiService.class);
        SharedPreferences sp=getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        String la=sp.getString("latitude",null);
        String lo=sp.getString("longitude",null);
        String path=la+","+lo+".json";
        Log.i("path",path);
        Call<WeatherListResponse> call=apiService.getForecast(path);
        call.enqueue(new Callback<WeatherListResponse>() {
            @Override
            public void onResponse(Call<WeatherListResponse> call, Response<WeatherListResponse> response) {
                if(response.isSuccessful()){
                    ArrayList<WeatherListResponse.Forecast.SimpleForcast.ForecastDay> days=response.body()
                            .getForecast().getSimpleforecast().getForecastday();

                    if(days==null){
                        return;
                    }

                    data.addAll(days);

                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<WeatherListResponse> call, Throwable t) {

            }
        });

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        weatherView.setLayoutManager(linearLayoutManager);
        weatherView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));






        return v;
    }




}
