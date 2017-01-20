package com.example.shubhamjain.mapsapp.Weather3DayForecast;

import java.util.ArrayList;

/**
 * Created by SHUBHAM JAIN on 29-08-2016.
 */
public class WeatherListResponse {
    Forecast forecast;

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    public static class Forecast{
        SimpleForcast simpleforecast;

        public SimpleForcast getSimpleforecast() {
            return simpleforecast;
        }

        public void setSimpleforecast(SimpleForcast simpleforecast) {
            this.simpleforecast = simpleforecast;
        }

        public static class SimpleForcast{
            ArrayList<ForecastDay> forecastday;

            public ArrayList<ForecastDay> getForecastday() {
                return forecastday;
            }

            public void setForecastday(ArrayList<ForecastDay> forecastday) {
                this.forecastday = forecastday;
            }

            public static class ForecastDay{
                String conditions;
                String icon_url;
                Date date;
                High high;
                Low low;

                public Date getDate() {
                    return date;
                }

                public void setDate(Date date) {
                    this.date = date;
                }

                public High getHigh() {
                    return high;
                }

                public void setHigh(High high) {
                    this.high = high;
                }

                public Low getLow() {
                    return low;
                }

                public void setLow(Low low) {
                    this.low = low;
                }

                public static class High{
                    String celsius;

                    public String getCelsius() {
                        return celsius;
                    }

                    public void setCelsius(String celsius) {
                        this.celsius = celsius;
                    }
                }

                public static class Low{
                    String celsius;

                    public String getCelsius() {
                        return celsius;
                    }

                    public void setCelsius(String celsius) {
                        this.celsius = celsius;
                    }
                }


                public static class Date{
                    String weekday;


                    public String getWeekday() {
                        return weekday;
                    }

                    public void setWeekday(String weekday) {
                        this.weekday = weekday;
                    }
                }

                public String getConditions() {
                    return conditions;
                }

                public void setConditions(String conditions) {
                    this.conditions = conditions;
                }

                public String getIcon_url() {
                    return icon_url;
                }

                public void setIcon_url(String icon_url) {
                    this.icon_url = icon_url;
                }

                public ForecastDay(String conditions, String icon_url) {

                    this.conditions = conditions;
                    this.icon_url = icon_url;
                }
            }
        }
    }


}
