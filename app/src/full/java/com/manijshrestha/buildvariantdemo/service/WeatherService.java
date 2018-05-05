package com.manijshrestha.buildvariantdemo.service;

import com.manijshrestha.buildvariantdemo.Constants;
import com.manijshrestha.buildvariantdemo.model.WeatherData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Weather service that uses Retrofit to get the weather data
 *
 */
public class WeatherService {

    private WeatherListener mListener;

    public WeatherService(WeatherListener listener) {
        this.mListener = listener;
    }

    public void getWeather(String cityName) {
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(Constants.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpenWeatherService openWeatherService = adapter.create(OpenWeatherService.class);

        Call<WeatherData> c = openWeatherService.getWeatherData(
                cityName,
                Constants.IMPERIAL_UNITS,
                Constants.API_KEY
        );

        c.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                mListener.onWeatherData(response.body());
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                mListener.onNoWeatherData();
            }
        });
    }
}
