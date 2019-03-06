package pt.isel.leic.mpd.v1819.li51n.queries;

import pt.isel.leic.mpd.v1819.li51n.utils.HttpRequest;
import pt.isel.leic.mpd.v1819.li51n.weatherapi.WeatherInfo;
import pt.isel.leic.mpd.v1819.li51n.weatherapi.WeatherWebApi;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Queries {


    private static WeatherWebApi weatherWebApi = new WeatherWebApi(new HttpRequest());


    public static List<WeatherInfo> filter(double lat, double log, LocalDate from, LocalDate to, Filter<WeatherInfo> filter) throws IOException {
        final List<WeatherInfo> weatherInfos = weatherWebApi.pastWeather(lat, log, from, to);

        List<WeatherInfo> filteredWeatherInfo = new ArrayList<>();

        for (WeatherInfo weatherInfo : weatherInfos) {
            if(filter.test(weatherInfo)) {
                filteredWeatherInfo.add(weatherInfo);
            }
        }

        return filteredWeatherInfo;
    }
}
