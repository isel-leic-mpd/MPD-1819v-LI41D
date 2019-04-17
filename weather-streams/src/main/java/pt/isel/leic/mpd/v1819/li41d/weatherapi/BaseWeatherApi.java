package pt.isel.leic.mpd.v1819.li41d.weatherapi;

import java.time.LocalDate;

public abstract class BaseWeatherApi implements WeatherWebApi {
    // Weather API documentation: https://www.worldweatheronline.com/developer/api/docs/historical-weather-api.aspx
    private final String HOST = "http://api.worldweatheronline.com/premium/v1/";
    private final String PATH_PAST_WEATHER = "past-weather.ashx?q=%s,%s&date=%s&enddate=%s&tp=24&format=%s&key=%s";
    private final String PATH_SEARCH = "search.ashx?query=%s&format=%s&key=%s";
    private final String WEATHER_KEY = "04c0dd0f6827411d8ee153351192202";
    private final String PAST_WEATHER_FORMAT;
    private final String SEARCH_FORMAT;

   public BaseWeatherApi(String pastWeatherFormat, String searchFormat) {
        PAST_WEATHER_FORMAT = pastWeatherFormat;
        SEARCH_FORMAT = searchFormat;
    }

    protected String getPastWeatherUrl(double lat, double log, LocalDate from, LocalDate to) {
        return String.format(HOST + PATH_PAST_WEATHER, lat, log, from, to, PAST_WEATHER_FORMAT, WEATHER_KEY);
    }

    protected String getSearchUrl(String query) {
        return String.format(HOST + PATH_SEARCH, query, SEARCH_FORMAT, WEATHER_KEY);
    }
}
