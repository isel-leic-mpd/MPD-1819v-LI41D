package pt.isel.leic.mpd.v1819.li51n;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class WeatherWebApiTest {

    private final WeatherWebApi weatherWebApi = new WeatherWebApi();

    @Test
    public void shouldGetLocationsForOportoSearch() {
        // Arrange
        final String locationStr = "Oporto";

        // Act
        final List<LocationInfo> locations = weatherWebApi.search(locationStr);

        // Assert
        Assert.assertTrue(locations.size() > 0);
        assertEachLocationIsValid(locations);
    }



    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForEmptyStringOnSearch() {
        // Arrange

        // Act
        final List<LocationInfo> locations = weatherWebApi.search("");

        // Assert
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForNullStringOnSearch() {
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
