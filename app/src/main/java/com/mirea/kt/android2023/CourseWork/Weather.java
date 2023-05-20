package com.mirea.kt.android2023.CourseWork;

import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Weather {

    private JSONObject weather;
    private String weatherIcon;

    public Weather(double latitude, double longitude){
        weather = requestWeather(latitude, longitude);
        weatherIcon = setWeatherIcon();
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public JSONObject getWeatherResponse() {
        return weather;
    }

    private String setWeatherIcon(){
        int weatherCode;
        try {
            weatherCode = weather.getJSONObject("current_weather")
                    .getInt("weathercode");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        switch (weatherCode){
            case (0):
                return "ic_weather_cloud";
            default:
                return "ic_weather_sun";
        }
    }
    private JSONObject requestWeather( double latitude, double longitude) {
        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("latitude", Double.toString(latitude));
        requestBody.put("longitude", Double.toString(longitude));
        HTTPGETRunnable httpgetRunnable = new HTTPGETRunnable
                ("https://api.open-meteo.com/v1/forecast?hourly=temperature_2m&daily=weathercode&current_weather=true&forecast_days=1&timezone=Europe%2FMoscow", requestBody);
        Thread th = new Thread(httpgetRunnable);
        th.start();
        JSONObject response;
        try {
            th.join();
            response = new JSONObject(httpgetRunnable.getResponseBody());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

}
