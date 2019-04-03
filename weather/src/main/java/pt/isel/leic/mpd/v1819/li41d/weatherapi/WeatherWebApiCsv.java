package pt.isel.leic.mpd.v1819.li41d.weatherapi;

import pt.isel.leic.mpd.v1819.li41d.queries.lazy.LazyQueries;
import pt.isel.leic.mpd.v1819.li41d.utils.Request;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.dto.LocationInfo;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.dto.WeatherInfo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeatherWebApiCsv extends BaseWeatherApi {

    private final Request request;

    public WeatherWebApiCsv(Request request) {
        super("csv", "tab");

        this.request = request;
    }

    /**
     * E.g. http://api.worldweatheronline.com/premium/v1/past-weather.ashx?q=37.017,-7.933&date=2019-01-01&enddate=2019-01-30&tp=24&format=csv&key=04c0dd0f6827411d8ee153351192202
     *
     * @param lat Location latitude
     * @param log Location longitude
     * @param from Beginning date
     * @param to End date
     * @return List of WeatherInfo objects with weather information.
     */
    @Override
    public Iterable<WeatherInfo> pastWeather(double lat, double log, LocalDate from, LocalDate to) throws IOException {
        final String url = getPastWeatherUrl(lat, log, from, to);

        int[] i = {0};
        return LazyQueries
                .of(request.getLines(getPastWeatherUrl(lat, log, from, to)))
                .filter(WeatherWebApiCsv::isNotComment)
                .filter(line -> i[0]++%2 == 1)
                .map(WeatherInfo::valueOf);
    }



    /**
     * e.g. http://api.worldweatheronline.com/premium/v1/search.ashx?query=Oporto&format=tab&key=04c0dd0f6827411d8ee153351192202
     *
     * @param query Name of the city you are looking for.
     * @return List of LocationInfo objects with location information.
     */
    @Override
    public Iterable<LocationInfo> search(String query) throws IOException {
        if(query == null || query.isEmpty()) {
            throw new IllegalArgumentException("query cannot be null or empty");
        }

        return LazyQueries
                .of(request.getLines(getSearchUrl(query)))
                .filter(WeatherWebApiCsv::isNotComment)
                .map(LocationInfo::valueOf);
    }

    private static boolean isNotComment(String line) {
        return !line.startsWith("#");
    }


}
