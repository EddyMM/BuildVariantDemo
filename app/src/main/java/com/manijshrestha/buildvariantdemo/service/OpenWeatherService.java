package com.manijshrestha.buildvariantdemo.service;

import com.manijshrestha.buildvariantdemo.model.WeatherData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by manijshrestha on 3/21/15.
 */
public interface OpenWeatherService {

    @GET("/data/2.5/weather")
    Call<WeatherData> getWeatherData(
            @Query("q")String cityName,
            @Query("units") String unit,
            @Query("APPID") String ApiKey
    );
}
