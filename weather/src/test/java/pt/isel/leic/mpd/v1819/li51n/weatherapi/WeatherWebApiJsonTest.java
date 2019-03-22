package pt.isel.leic.mpd.v1819.li51n.weatherapi;

import pt.isel.leic.mpd.v1819.li51n.utils.HttpRequest;

public class WeatherWebApiJsonTest extends WeatherWebApiTest {

    public WeatherWebApiJsonTest() {
        super(new WeatherWebApiJson(new HttpRequest()));
    }
}
