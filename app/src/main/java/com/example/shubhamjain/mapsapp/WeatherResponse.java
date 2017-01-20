package com.example.shubhamjain.mapsapp;

import java.util.ArrayList;

/**
 * Created by SHUBHAM JAIN on 29-08-2016.
 */
public class WeatherResponse {

    ArrayList<Weather> weather;
    Main main;


    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public static class Main{
        double temp;
        double humidity;

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }

        public Main(double temp, double humidity) {

            this.temp = temp;
            this.humidity = humidity;
        }
    }


    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public WeatherResponse(ArrayList<Weather> weather) {

        this.weather = weather;
    }

    public static class Weather{
        String main;
        String icon;


        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Weather(String main, String icon) {

            this.main = main;
            this.icon = icon;
        }
    }
}
