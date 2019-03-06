package pt.isel.leic.mpd.v1819.li51n.queries;

import pt.isel.leic.mpd.v1819.li51n.utils.HttpRequest;
import pt.isel.leic.mpd.v1819.li51n.weatherapi.WeatherInfo;
import pt.isel.leic.mpd.v1819.li51n.weatherapi.WeatherWebApi;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MiscWeatherInfoQueries {


    private static WeatherWebApi weatherWebApi = new WeatherWebApi(new HttpRequest());

    public static List<WeatherInfo> getWeatherInfoWithMaxTemperaturesAbove(double lat, double log, LocalDate from, LocalDate to, int minTemp) throws IOException {
        return getWeatherInfoFiltered(lat, log, from, to, weatherInfo -> weatherInfo.getTempMaxC() >= minTemp);
    }


    public static List<WeatherInfo> getWeatherInfoWithMaxTemperaturesBetween(double lat, double log, LocalDate from, LocalDate to, int minTemp, int maxTemp) throws IOException {
        return getWeatherInfoFiltered(lat, log, from, to, weatherInfo -> weatherInfo.getTempMaxC() >= minTemp && weatherInfo.getTempMaxC() <= maxTemp);
    }

    public static List<WeatherInfo> getWeatherInfoWithMinTemperaturesAbove(double lat, double log, LocalDate from, LocalDate to, int minTemp) throws IOException {
        return getWeatherInfoFiltered(lat, log, from, to, weatherInfo -> weatherInfo.getTempMinC() >= minTemp);
    }


    public static List<WeatherInfo> getWeatherInfoWithMinTemperaturesBetween(double lat, double log, LocalDate from, LocalDate to, int minTemp, int maxTemp) throws IOException {
        return getWeatherInfoFiltered(lat, log, from, to,
                weatherInfo -> weatherInfo.getTempMinC() >= minTemp && weatherInfo.getTempMinC() <= maxTemp
        );
    }



    public static List<WeatherInfo> getWeatherInfoFiltered(double lat, double log, LocalDate from, LocalDate to, Filter<WeatherInfo> filter) throws IOException {
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
