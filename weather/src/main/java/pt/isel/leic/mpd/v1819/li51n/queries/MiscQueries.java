package pt.isel.leic.mpd.v1819.li51n.queries;

import pt.isel.leic.mpd.v1819.li51n.utils.HttpRequest;
import pt.isel.leic.mpd.v1819.li51n.weatherapi.WeatherInfo;
import pt.isel.leic.mpd.v1819.li51n.weatherapi.WeatherWebApi;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MiscQueries {


    private static WeatherWebApi weatherWebApi = new WeatherWebApi(new HttpRequest());

    public static List<WeatherInfo> getWeatherInfoWithMaxTemperaturesAbove(double lat, double log, LocalDate from, LocalDate to, int minTemp) throws IOException {
        final List<WeatherInfo> weatherInfos = weatherWebApi.pastWeather(lat, log, from, to);

        List<WeatherInfo> filteredWeatherInfo = new ArrayList<>();

        for (WeatherInfo weatherInfo : weatherInfos) {
            if(weatherInfo.getTempMaxC() >= minTemp) {
                filteredWeatherInfo.add(weatherInfo);
            }
        }

        return filteredWeatherInfo;
    }


    public static List<WeatherInfo> getWeatherInfoWithMaxTemperaturesBetween(double lat, double log, LocalDate from, LocalDate to, int minTemp, int maxTemp) throws IOException {
        final List<WeatherInfo> weatherInfos = weatherWebApi.pastWeather(lat, log, from, to);

        List<WeatherInfo> filteredWeatherInfo = new ArrayList<>();

        for (WeatherInfo weatherInfo : weatherInfos) {
            if(weatherInfo.getTempMaxC() >= minTemp && weatherInfo.getTempMaxC() <= maxTemp) {
                filteredWeatherInfo.add(weatherInfo);
            }
        }

        return filteredWeatherInfo;
    }
}
