package pt.isel.leic.mpd.v1819.li41d.weatherapi;

import pt.isel.leic.mpd.v1819.li41d.queries.eager.EagerQueries;
import pt.isel.leic.mpd.v1819.li41d.utils.Request;

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

        final Iterable<String> lines = skipComments(request.getLines(url));
        List<WeatherInfo> weatherInfos = new ArrayList<>();

        int i = 0;
        for (String line : lines) {
            if(i++%2 == 1)
                weatherInfos.add(WeatherInfo.valueOf(line)); // Read daily information
        }
        return weatherInfos;
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

        final String url = getSearchUrl(query);
        final Iterable<String> lines = skipComments(request.getLines(url));
        List<LocationInfo> locations = new ArrayList<>();

        for (String line : lines) {
            locations.add(LocationInfo.valueOf(line));
        }
        return locations;
    }

    private Iterable<String> skipComments(Iterable<String> lines) {
        return EagerQueries.of(lines).filter(line -> !line.startsWith("#"));
    }
}
