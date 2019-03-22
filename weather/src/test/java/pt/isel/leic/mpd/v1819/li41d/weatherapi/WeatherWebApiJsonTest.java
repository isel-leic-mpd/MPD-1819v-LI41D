package pt.isel.leic.mpd.v1819.li41d.weatherapi;

import pt.isel.leic.mpd.v1819.li41d.utils.HttpRequest;

public class WeatherWebApiJsonTest extends WeatherWebApiTest {

    public WeatherWebApiJsonTest() {
        super(new WeatherWebApiJson(new HttpRequest()));
    }
}
