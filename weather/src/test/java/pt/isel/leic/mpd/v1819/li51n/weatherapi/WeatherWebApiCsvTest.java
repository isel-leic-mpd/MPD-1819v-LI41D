package pt.isel.leic.mpd.v1819.li51n.weatherapi;

import pt.isel.leic.mpd.v1819.li51n.utils.MockRequest;

public class WeatherWebApiCsvTest extends WeatherWebApiTest {

    public WeatherWebApiCsvTest() {
        super(new WeatherWebApiCsv(new MockRequest()));
    }
}
