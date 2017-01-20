package com.example.shubhamjain.mapsapp;

/**
 * Created by SHUBHAM JAIN on 11-09-2016.
 */
public class CurrentResponse {
    Condition current_observation;


    public Condition getCurrent_observation() {
        return current_observation;
    }

    public void setCurrent_observation(Condition current_observation) {
        this.current_observation = current_observation;
    }

    public static class Condition{
        Double temp_c;
        String weather;
        String relative_humidity;
        String icon_url;
        String feelslike_c;
        Double dewpoint_c;
        String precip_today_metric;
        String visibility_mi;

        public String getVisibility_mi() {
            return visibility_mi;
        }

        public void setVisibility_mi(String visibility_mi) {
            this.visibility_mi = visibility_mi;
        }

        public String getPrecip_today_metric() {
            return precip_today_metric;
        }

        public void setPrecip_today_metric(String precip_today_metric) {
            this.precip_today_metric = precip_today_metric;
        }

        public Double getDewpoint_c() {
            return dewpoint_c;
        }

        public void setDewpoint_c(Double dewpoint_c) {
            this.dewpoint_c = dewpoint_c;
        }

        public Double getTemp_c() {
            return temp_c;
        }

        public void setTemp_c(Double temp_c) {
            this.temp_c = temp_c;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getRelative_humidity() {
            return relative_humidity;
        }

        public void setRelative_humidity(String relative_humidity) {
            this.relative_humidity = relative_humidity;
        }

        public String getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(String icon_url) {
            this.icon_url = icon_url;
        }

        public String getFeelslike_c() {
            return feelslike_c;
        }

        public void setFeelslike_c(String feelslike_c) {
            this.feelslike_c = feelslike_c;
        }
    }
}
