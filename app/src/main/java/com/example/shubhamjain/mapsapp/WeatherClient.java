package com.example.shubhamjain.mapsapp;

import com.example.shubhamjain.mapsapp.Weather3DayForecast.WeatherApiService;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SHUBHAM JAIN on 29-08-2016.
 */
public class WeatherClient {
    static WeatherApiService service;


    public static WeatherApiService getService() {
        if(service==null){
            Retrofit r=new Retrofit.Builder().baseUrl("http://api.openweathermap.org")
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).build();
            service=r.create(WeatherApiService.class);
        }



        return service;
    }
}
