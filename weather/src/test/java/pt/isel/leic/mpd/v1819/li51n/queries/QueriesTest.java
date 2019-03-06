package pt.isel.leic.mpd.v1819.li51n.queries;

import org.junit.Test;
import pt.isel.leic.mpd.v1819.li51n.weatherapi.WeatherInfo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class QueriesTest {


    private double lat = 37.017;
    private double log = 7.933;
    private LocalDate from = LocalDate.parse("2019-02-01");
    private LocalDate to = LocalDate.parse("2019-02-28");


    @Test
    public void getWeatherInfoWithMaxTemperaturesAbove() throws IOException {
        // Arrange
        int minTemp = 15;

        // Act
        // Assert
        testFilteredWeatherInfo(weatherInfo -> weatherInfo.getTempMaxC() >= minTemp);
    }


    @Test
    public void getWeatherInfoWithMaxTemperaturesBetween() throws IOException {
        int minTemp = 5;
        int maxTemp = 15;

        testFilteredWeatherInfo(weatherInfo -> weatherInfo.getTempMaxC() >= minTemp && weatherInfo.getTempMaxC() <= maxTemp);


    }

    @Test
    public void getWeatherInfoWithMinTemperaturesAbove() throws IOException {

        int minTemp = 6;

        testFilteredWeatherInfo(weatherInfo -> weatherInfo.getTempMinC() >= minTemp);
    }


    @Test
    public void getWeatherInfoWithMinTemperaturesBetween() throws IOException {
        int minTemp = 5;
        int maxTemp = 15;

        testFilteredWeatherInfo(weatherInfo -> weatherInfo.getTempMinC() >= minTemp && weatherInfo.getTempMinC() <= maxTemp);
    }

    private void testFilteredWeatherInfo(Filter<WeatherInfo> filter) throws IOException {
        final List<WeatherInfo> weatherInfos = Queries.filter(lat, log, from, to, filter);
        assertNotNull(weatherInfos);
        for (WeatherInfo weatherInfo : weatherInfos) {
            assertTrue(filter.test(weatherInfo));
        }
    }

}