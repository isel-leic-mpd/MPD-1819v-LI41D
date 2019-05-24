package pt.isel.leic.mpd.v1819.li41d.weatherapi.model;


import pt.isel.leic.mpd.v1819.li41d.weatherapi.WeatherWebApi;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.dto.LocationInfo;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.dto.WeatherInfo;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;


public class WeatherService {

    final WeatherWebApi api;

    public WeatherService(WeatherWebApi api) {
        this.api = api;
    }


    public CompletableFuture<Stream<Location>> search(String query) throws IOException {
        return api.search(query).thenApply(this::locationInfosToLocationStream);

        //return api.search(query).thenApply(locationInfos -> Stream.of(locationInfos).map(this::toLocation));
    }

    private Stream<Location> locationInfosToLocationStream(LocationInfo[] locationInfos) {
        return Stream.of(locationInfos).map(this::toLocation);
    }


    private Location toLocation(LocationInfo l) {
        return new Location(
                l.getCountry(),
                l.getRegion(),
                l.getLatitude(),
                l.getLongitude(),
                getPastWeather(l));
    }


    private CompletableFuture<Stream<WeatherInfo>> getPastWeather(LocationInfo l) {

        try {
            return api.pastWeather(
                    l.getLatitude(),
                    l.getLongitude(),
                    LocalDate.now().minusDays(30),
                    LocalDate.now()).thenApply(Stream::of);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
