package pt.isel.leic.mpd.v1819.li51n.weatherapi;

import org.junit.Assert;
import org.junit.Test;
import pt.isel.leic.mpd.v1819.li51n.utils.MockRequest;


import java.io.IOException;
import java.util.List;

public class WeatherWebApiTest {

    private final WeatherWebApi weatherWebApi = new WeatherWebApi(new MockRequest());

    @Test
    public void shouldGetLocationsForOportoSearch() throws IOException {
        // Arrange
        final String locationStr = "Oporto";

        // Act
        final List<LocationInfo> locations = weatherWebApi.search(locationStr);

        // Assert
        Assert.assertTrue(locations.size() > 0);
        assertEachLocationIsValid(locations);
    }



    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForEmptyStringOnSearch() throws IOException {
        // Arrange

        // Act
        final List<LocationInfo> locations = weatherWebApi.search("");

        // Assert
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForNullStringOnSearch() throws IOException {
        // Arrange

        // Act
        final List<LocationInfo> locations = weatherWebApi.search(null);

        // Assert
    }

    private void assertIfInRange(double value, float limit) {
        Assert.assertTrue(value <= limit && value >= -limit);
    }

    private void assertNotNullNorEmpty(String str) {
        Assert.assertNotNull(str);
        Assert.assertFalse(str.isEmpty());
    }

    private void assertEachLocationIsValid(List<LocationInfo> locations) {
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
