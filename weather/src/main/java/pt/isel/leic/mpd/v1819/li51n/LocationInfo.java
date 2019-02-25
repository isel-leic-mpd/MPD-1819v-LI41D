package pt.isel.leic.mpd.v1819.li51n;

public class LocationInfo {
    private final String country;
    private final String region;
    private final double latitude;
    private final double longitude;

    public LocationInfo(String country, String region, double latitude, double longitude) {
        this.country = country;
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "LocationInfo{" +
            "country='" + country + '\'' +
            ", region='" + region + '\'' +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            '}';
    }

    public static LocationInfo valueOf(String line) {
        String[] data = line.split("\t");
        return new LocationInfo(
                data[1],
                data[2],
                Double.parseDouble(data[3]),
                Double.parseDouble(data[4]));
    }
}
