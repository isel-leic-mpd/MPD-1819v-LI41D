package pt.isel.leic.mpd.v1819.li41d.weatherapi;

import pt.isel.leic.mpd.v1819.li41d.weatherapi.dto.LocationInfo;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.dto.WeatherInfo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

public interface WeatherWebApi {
    CompletableFuture<WeatherInfo[]> pastWeather(double lat, double log, LocalDate from, LocalDate to) throws IOException;
    CompletableFuture<LocationInfo[]> search(String query) throws IOException;
}
