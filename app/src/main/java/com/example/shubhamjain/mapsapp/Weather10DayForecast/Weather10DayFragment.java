package com.example.shubhamjain.mapsapp.Weather10DayForecast;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shubhamjain.mapsapp.R;
import com.example.shubhamjain.mapsapp.SimpleDividerItemDecoration;
import com.example.shubhamjain.mapsapp.Weather3DayForecast.WeatherAdapter;
import com.example.shubhamjain.mapsapp.Weather3DayForecast.WeatherApiService;
import com.example.shubhamjain.mapsapp.Weather3DayForecast.WeatherListResponse;
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
public class Weather10DayFragment extends Fragment {
    ArrayList<WeatherListResponse.Forecast.SimpleForcast.ForecastDay> data;
    RecyclerView weatherView;
    WeatherAdapter adapter;


    public Weather10DayFragment() {
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
        SharedPreferences sp=getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        String la=sp.getString("latitude",null);
        String lo=sp.getString("longitude",null);
        String path=la+","+lo+".json";
        WeatherApiService apiService=new Retrofit.Builder().baseUrl("http://api.wunderground.com")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).build().create(WeatherApiService.class);;
        Call<WeatherListResponse> call=apiService.getForecast10Day(path);
        call.enqueue(new Callback<WeatherListResponse>() {
            @Override
            public void onResponse(Call<WeatherListResponse> call, Response<WeatherListResponse> response) {
                if(response.isSuccessful()){
                    ArrayList<WeatherListResponse.Forecast.SimpleForcast.ForecastDay> days=response.body()
                            .getForecast().getSimpleforecast().getForecastday();

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







        return v;
    }

}
