package pt.isel.leic.mpd.v1819.li41d.weatherapi.restdto;

public class SearchApiResultDto {
    private final ContainerDto[] country;
    private final ContainerDto[] region;
    private final String latitude;
    private final String longitude;

    public SearchApiResultDto(ContainerDto[] country, ContainerDto[] region, String latitude, String longitude) {
        this.country = country;
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public ContainerDto[] getCountry() {
        return country;
    }

    public ContainerDto[] getRegion() {
        return region;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
