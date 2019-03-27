package pt.isel.leic.mpd.v1819.li41d.weatherapi.jsonDto;

public class PastWeatherDto {
    public PastWeatherDto(PastWeatherDataDto data) {
        this.data = data;
    }

    private final PastWeatherDataDto data;

    public PastWeatherDataDto getData() {
        return data;
    }
}
