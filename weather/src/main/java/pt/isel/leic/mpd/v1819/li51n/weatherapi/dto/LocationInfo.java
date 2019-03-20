package pt.isel.leic.mpd.v1819.li51n.weatherapi.dto;

public class LocationInfo {
    private final String name;
    private final String country;
    private final String region;
    private final double latitude;
    private final double longitude;

    public LocationInfo(String name, String country, String region, double latitude, double longitude) {
        this.name = name;
        this.country = country;
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "LocationInfo{" +
            "name='" + name + '\'' +
            ", country='" + country + '\'' +
            ", region='" + region + '\'' +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            '}';
    }

    public static LocationInfo valueOf(String line) {
        String[] data = line.split("\t");
        return new LocationInfo(
                data[0],
                data[1],
                data[2].isEmpty() ? "N/A" : data[2],
                Double.parseDouble(data[3]),
                Double.parseDouble(data[4]));
    }

    public String getName() {
        return name;
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
}
