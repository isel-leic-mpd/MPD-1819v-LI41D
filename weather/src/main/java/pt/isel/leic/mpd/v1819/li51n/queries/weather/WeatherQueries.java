package pt.isel.leic.mpd.v1819.li51n.queries.weather;

import pt.isel.leic.mpd.v1819.li51n.queries.Filter;
import pt.isel.leic.mpd.v1819.li51n.queries.Queries;
import pt.isel.leic.mpd.v1819.li51n.utils.HttpRequest;
import pt.isel.leic.mpd.v1819.li51n.weatherapi.WeatherInfo;
import pt.isel.leic.mpd.v1819.li51n.weatherapi.WeatherWebApi;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeatherQueries {


    private static WeatherWebApi weatherWebApi = null;

    public static void setWeatherWebApi(WeatherWebApi wwApi) {
        weatherWebApi = wwApi;
    }


    public static Iterable<WeatherInfo> filter(double lat, double log, LocalDate from, LocalDate to, Filter<WeatherInfo> filter) throws IOException {
        final Iterable<WeatherInfo> weatherInfos = weatherWebApi.pastWeather(lat, log, from, to);
        return Queries.filter(weatherInfos, filter);
    }
}
