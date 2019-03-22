package pt.isel.leic.mpd.v1819.li41d.weatherapi;

import org.junit.Assert;
import org.junit.Test;
import pt.isel.leic.mpd.v1819.li41d.queries.Queries;
import pt.isel.leic.mpd.v1819.li41d.queries.lazy.LazyQueries;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public abstract class WeatherWebApiTest {
    private final WeatherWebApi weatherWebApi;

    public WeatherWebApiTest(WeatherWebApi weatherWebApi) {
        this.weatherWebApi = weatherWebApi;
    }


    @Test
    public void shouldGetLocationsForOportoSearch() throws IOException {
        // Arrange
        final String locationStr = "Oporto";

        // Act
        final Iterable<LocationInfo> locations = weatherWebApi.search(locationStr);

        // Assert
        Assert.assertTrue(locations.iterator().hasNext());
        assertEachLocationIsValid(locations);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForEmptyStringOnSearch() throws IOException {
        // Arrange

        // Act
        final Iterable<LocationInfo> locations = weatherWebApi.search("");

        // Assert
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForNullStringOnSearch() throws IOException {
        // Arrange

        // Act
        final Iterable<LocationInfo> locations = weatherWebApi.search(null);

        // Assert
    }


    @Test
    public void shouldGetPastWeatherForOporto() throws IOException {
        final double lat = 37.017;
        final double log = 7.933;
        final LocalDate from = LocalDate.parse("2019-02-01");
        final LocalDate to = LocalDate.parse("2019-02-25");

        final Queries<WeatherInfo> weatherInfos = LazyQueries.of(weatherWebApi.pastWeather(lat, log, from, to));


        assertEquals(25, weatherInfos.count());


    }

    private void assertIfInRange(double value, float limit) {
        Assert.assertTrue(value <= limit && value >= -limit);
    }

    private void assertNotNullNorEmpty(String str) {
        Assert.assertNotNull(str);
        Assert.assertFalse(str.isEmpty());
    }

    private void assertEachLocationIsValid(Iterable<LocationInfo> locations) {
        for (LocationInfo location : locations) {
            Assert.assertNotNull(location);
            assertNotNullNorEmpty(location.getName());
            assertNotNullNorEmpty(location.getCountry());
            assertNotNullNorEmpty(location.getRegion());
            assertIfInRange(location.getLatitude(), 90F);
            assertIfInRange(location.getLongitude(), 180F);
        }
    }
}
