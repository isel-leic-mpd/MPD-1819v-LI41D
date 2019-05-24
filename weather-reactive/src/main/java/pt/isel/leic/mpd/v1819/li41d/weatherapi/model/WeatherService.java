package pt.isel.leic.mpd.v1819.li41d.weatherapi.model;


import pt.isel.leic.mpd.v1819.li41d.weatherapi.WeatherWebApi;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.dto.LocationInfo;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.dto.WeatherInfo;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Stream;


public class WeatherService {

    final WeatherWebApi api;

    public WeatherService(WeatherWebApi api) {
        this.api = api;
    }


    public Stream<Location> search(String query) throws IOException {
        return Stream.of(api.search(query)).map(li -> toLocation(li));
    }

    private Location toLocation(LocationInfo l) {
        return new Location(
                l.getCountry(),
                l.getRegion(),
                l.getLatitude(),
                l.getLongitude(),
                getPastWeatherFor(l));
    }

    private Stream<WeatherInfo> getPastWeatherFor(LocationInfo l) {

//        final Stream<WeatherInfo> weatherInfoStream = Stream.generate(() ->
//                getPastWeather(l)).limit(1).flatMap(Function.identity());

        return Stream.of(l).flatMap(this::getPastWeatherFor);

    }

    private Stream<WeatherInfo> getPastWeather(LocationInfo l) {
        try {
            return Stream.of(api.pastWeather(
                    l.getLatitude(),
                    l.getLongitude(),
                    LocalDate.now().minusDays(30),
                    LocalDate.now()));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
