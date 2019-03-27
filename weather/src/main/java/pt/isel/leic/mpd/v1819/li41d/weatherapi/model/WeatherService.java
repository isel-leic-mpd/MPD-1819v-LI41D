package pt.isel.leic.mpd.v1819.li41d.weatherapi.model;

import pt.isel.leic.mpd.v1819.li41d.queries.lazy.LazyQueries;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.WeatherWebApi;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.dto.LocationInfo;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.dto.WeatherInfo;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.LocalDate;


public class WeatherService {

    final WeatherWebApi api;

    public WeatherService(WeatherWebApi api) {
        this.api = api;
    }


    public Iterable<Location> search(String query) throws IOException {
        Iterable<LocationInfo> locals = api.search(query);
        return LazyQueries.of(locals).map(li -> toLocation(li));
    }

    private Location toLocation(LocationInfo l) {
            return new Location(
                    l.getCountry(),
                    l.getRegion(),
                    l.getLatitude(),
                    l.getLongitude(),
                    () -> getPastWeatherFor(l));
    }

    Iterable<WeatherInfo> getPastWeatherFor(LocationInfo l) {
        try {
            return api.pastWeather(
                    l.getLatitude(),
                    l.getLongitude(),
                    LocalDate.now().minusDays(30),
                    LocalDate.now());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
