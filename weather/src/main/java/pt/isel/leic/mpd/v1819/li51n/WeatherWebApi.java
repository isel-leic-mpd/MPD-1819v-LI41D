package pt.isel.leic.mpd.v1819.li51n;

import com.sun.jndi.toolkit.url.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeatherWebApi {
    final String HOST = "http://api.worldweatheronline.com/premium/v1/";
    final String PATH_PAST_WEATHER = "past-weather.ashx?q=%s,%s&date=%s&enddate=%s&tp=24&format=csv&key=%s";
    final String PATH_SEARCH = "search.ashx?query=%s&format=tab&key=%s";
    final String WEATHER_KEY = "04c0dd0f6827411d8ee153351192202";

    /**
     * E.g. http://api.worldweatheronline.com/premium/v1/past-weather.ashx?q=37.017,-7.933&date=2019-01-01&enddate=2019-01-30&tp=24&format=csv&key=04c0dd0f6827411d8ee153351192202
     *
     * @param lat Location latitude
     * @param log Location longitude
     * @param from Beginning date
     * @param to End date
     * @return List of WeatherInfo objects with weather information.
     */
    public List<WeatherInfo> pastWeather(double lat, double log, LocalDate from, LocalDate to) {
        return null;
    }

    /**
     * e.g. http://api.worldweatheronline.com/premium/v1/search.ashx?query=Oporto&format=tab&key=04c0dd0f6827411d8ee153351192202
     *
     * @param query Name of the city you are looking for.
     * @return List of LocationInfo objects with location information.
     */
    public List<LocationInfo> search(String query) {
        final String url = String.format(HOST + PATH_SEARCH, query, WEATHER_KEY);

        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
            String line;
            while ((line = reader.readLine()) != null) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
