package pt.isel.leic.mpd.v1819.li51n.weatherapi.restdto;

public class PastWeatherDataWeatherHourlyDto {
    private final int tempC;
    private final ContainerDto[] weatherDesc;
    private final double  precipMM;
    private final int FeelsLikeC;

    public PastWeatherDataWeatherHourlyDto(int tempC, ContainerDto[] weatherDesc, double precipMM, int feelsLikeC) {
        this.tempC = tempC;
        this.weatherDesc = weatherDesc;
        this.precipMM = precipMM;
        this.FeelsLikeC = feelsLikeC;
    }

    public int getTempC() {
        return tempC;
    }

    public String getWeatherDesc() {
        return weatherDesc[0].getValue();
    }

    public double getPrecipMM() {
        return precipMM;
    }

    public int getFeelsLikeC() {
        return FeelsLikeC;
    }
}
