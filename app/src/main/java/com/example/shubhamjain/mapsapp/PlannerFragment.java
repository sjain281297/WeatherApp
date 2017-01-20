package com.example.shubhamjain.mapsapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shubhamjain.mapsapp.Weather3DayForecast.WeatherApiService;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlannerFragment extends Fragment {
    TextView maxTemp;
    TextView minTemp;
    TextView chanceOfHumidDay;
    TextView chanceOfSultryDay;
    TextView chanceOfRainyDay;
    TextView chanceOfThunderDay;


    public PlannerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_planner, container, false);
        maxTemp=(TextView) v.findViewById(R.id.maxTemp);
        minTemp=(TextView) v.findViewById(R.id.minTemp);
        chanceOfHumidDay=(TextView) v.findViewById(R.id.chanceOfHumid);
        chanceOfSultryDay=(TextView) v.findViewById(R.id.chanceOfSultry);
        chanceOfRainyDay=(TextView) v.findViewById(R.id.chanceOfRain);
        chanceOfThunderDay=(TextView) v.findViewById(R.id.chanceOfThunder);


        WeatherApiService apiService=new Retrofit.Builder().baseUrl("http://api.wunderground.com")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).build().create(WeatherApiService.class);;
        Call<PlannerResponse>call=apiService.getPlanner();

        call.enqueue(new Callback<PlannerResponse>() {
            @Override
            public void onResponse(Call<PlannerResponse> call, Response<PlannerResponse> response) {
                String tempHigh=response.body().getTrip().getTemp_high().getAvg().getC();
                String tempLow=response.body().getTrip().getTemp_low().getAvg().getC();
                String chanceOfRain=response.body().getTrip().getChance_of().getChanceofprecip().getPercentage();
                String chanceOfHumid=response.body().getTrip().getChance_of().getChanceofhumidday().getDescription();
                String chanceOfSultry=response.body().getTrip().getChance_of().getChanceofsultryday().getDescription();
                chanceOfSultry=chanceOfSultry.replace("&deg;","<small><small><sup>o</sup></small></small>");
                String chanceOfThunder=response.body().getTrip().getChance_of().getChanceofthunderday().getPercentage();
                chanceOfHumid=chanceOfHumid.replace("&deg;","<small><small><sup>o</sup></small></small>");
                maxTemp.setText(tempHigh);
                minTemp.setText(tempLow);
                chanceOfHumidDay.append(Html.fromHtml(chanceOfHumid)
                );
                chanceOfSultryDay.setText(Html.fromHtml(chanceOfSultry));
                chanceOfRainyDay.setText(chanceOfRain+"%");
                chanceOfThunderDay.setText(chanceOfThunder+"%");
            }

            @Override
            public void onFailure(Call<PlannerResponse> call, Throwable t) {

            }
        });


        return v;
    }

}
