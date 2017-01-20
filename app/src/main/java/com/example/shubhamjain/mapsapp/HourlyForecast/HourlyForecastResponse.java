package com.example.shubhamjain.mapsapp.HourlyForecast;

import java.util.ArrayList;

/**
 * Created by SHUBHAM JAIN on 31-08-2016.
 */
public class HourlyForecastResponse {
    ArrayList<HourlyForecast> hourly_forecast;


    public ArrayList<HourlyForecast> getHourly_forecast() {
        return hourly_forecast;
    }

    public void setHourly_forecast(ArrayList<HourlyForecast> hourly_forecast) {
        this.hourly_forecast = hourly_forecast;
    }

    public static class HourlyForecast{
        String condition;
        String icon_url;
        FctTime FCTTIME;
        String humidity;
        Temp temp;


        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public FctTime getFCTTIME() {
            return FCTTIME;
        }

        public void setFCTTIME(FctTime FCTTIME) {
            this.FCTTIME = FCTTIME;
        }

        public Temp getTemp() {
            return temp;
        }

        public void setTemp(Temp temp) {
            this.temp = temp;
        }

        public static class Temp{
          String metric;


            public String getMetric() {
                return metric;
            }

            public void setMetric(String metric) {
                this.metric = metric;
            }
        }

        public static class FctTime{
            String pretty;
            String civil;


            public String getPretty() {
                return pretty;
            }

            public void setPretty(String pretty) {
                this.pretty = pretty;
            }








        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(String icon_url) {
            this.icon_url = icon_url;
        }
    }
}
