package pt.isel.leic.mpd.v1819.li41d.weatherapi;

import pt.isel.leic.mpd.v1819.li41d.weatherapi.dto.LocationInfo;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.dto.WeatherInfo;

import java.io.IOException;
import java.time.LocalDate;

public interface WeatherWebApi {
    WeatherInfo[] pastWeather(double lat, double log, LocalDate from, LocalDate to) throws IOException;
    LocationInfo[] search(String query) throws IOException;
}
