package com.example.shubhamjain.mapsapp.Weather3DayForecast;

import com.example.shubhamjain.mapsapp.CurrentResponse;
import com.example.shubhamjain.mapsapp.HourlyForecast.HourlyForecastResponse;
import com.example.shubhamjain.mapsapp.PlannerResponse;
import com.example.shubhamjain.mapsapp.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by SHUBHAM JAIN on 29-08-2016.
 */
public interface WeatherApiService {
    @GET("/data/2.5/weather?q=Delhi,in&appid=81ec10ded9b4592a54a73cdfd10e9907&units=metric")
    Call<WeatherResponse> getWeather();
    @GET("/api/547d6d2406620fd2/forecast/q/{location}")
    Call<WeatherListResponse> getForecast(@Path("location") String location);
    @GET("/api/547d6d2406620fd2/hourly/q/{location}")
    Call<HourlyForecastResponse> getHourlyForecast(@Path("location") String location);
    @GET("/api/547d6d2406620fd2/planner_09020905/q/bangalore.json")
    Call<PlannerResponse> getPlanner();
    @GET("/api/547d6d2406620fd2/forecast10day/q/{location}")
    Call<WeatherListResponse> getForecast10Day(@Path("location") String location);
    @GET("/api/547d6d2406620fd2/conditions/q/{location}")
    Call<CurrentResponse> getCurrentWeather(@Path("location") String location);

}
