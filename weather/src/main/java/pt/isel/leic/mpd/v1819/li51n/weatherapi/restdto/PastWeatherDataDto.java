package pt.isel.leic.mpd.v1819.li51n.weatherapi.restdto;

public class PastWeatherDataDto {
    private final PastWeatherDataWeatherDto[] weather;

    public PastWeatherDataDto(PastWeatherDataWeatherDto[] weather) {
        this.weather = weather;
    }

    public PastWeatherDataWeatherDto[] getWeather() {
        return weather;
    }
}
