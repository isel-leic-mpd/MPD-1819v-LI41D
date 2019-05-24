package pt.isel.leic.mpd.v1819.li41d.weatherapi;

import pt.isel.leic.mpd.v1819.li41d.utils.MockRequest;

public class WeatherWebApiCsvTest extends WeatherWebApiTest {

    public WeatherWebApiCsvTest() {
        super(new WeatherWebApiCsv(new MockRequest()));
    }
}
