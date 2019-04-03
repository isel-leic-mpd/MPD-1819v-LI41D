package pt.isel.leic.mpd.v1819.li41d.queries.weather;

import pt.isel.leic.mpd.v1819.li41d.queries.eager.EagerQueries;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.WeatherWebApiCsv;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.dto.WeatherInfo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.function.Predicate;

public class WeatherQueries {


    private static WeatherWebApiCsv weatherWebApi = null;

    public static void setWeatherWebApi(WeatherWebApiCsv wwApi) {
        weatherWebApi = wwApi;
    }


    public static Iterable<WeatherInfo> filter(double lat, double log, LocalDate from, LocalDate to, Predicate<WeatherInfo> filter) throws IOException {
        final Iterable<WeatherInfo> weatherInfos = weatherWebApi.pastWeather(lat, log, from, to);
        return EagerQueries.of(weatherInfos).filter(filter);
    }
}
