package pt.isel.leic.mpd.v1819.li41d.weatherapi.model;


import pt.isel.leic.mpd.v1819.li41d.weatherapi.dto.WeatherInfo;

import java.util.stream.Stream;

public class Location {
    private final String country;
    private final String region;
    private final double latitude;
    private final double longitude;

    private Stream<Weather>  past30daysWeather = null;

    Location(String country, String region, double latitude, double longitude, Stream<WeatherInfo> past30daysWeather) {
        this.country = country;
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
        this.past30daysWeather = past30daysWeather.map(this::toWeather);

    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Stream<Weather> getPast30daysWeather() {
        return past30daysWeather;
    }

    private Weather toWeather(WeatherInfo wi) {
        return null;
    }
}
