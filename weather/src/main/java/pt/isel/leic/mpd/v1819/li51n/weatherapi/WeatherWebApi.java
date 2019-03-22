package pt.isel.leic.mpd.v1819.li51n.weatherapi;

import java.io.IOException;
import java.time.LocalDate;

public interface WeatherWebApi {
    Iterable<WeatherInfo> pastWeather(double lat, double log, LocalDate from, LocalDate to) throws IOException;
    Iterable<LocationInfo> search(String query) throws IOException;
}
