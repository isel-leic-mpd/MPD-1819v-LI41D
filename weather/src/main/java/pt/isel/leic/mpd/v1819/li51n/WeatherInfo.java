package pt.isel.leic.mpd.v1819.li51n;

import java.time.LocalDate;

public class WeatherInfo {
    final LocalDate date;
    final int tempMaxC;
    final int tempMinC;

    public WeatherInfo(LocalDate date, int tempMaxC, int tempMinC) {
        this.date = date;
        this.tempMaxC = tempMaxC;
        this.tempMinC = tempMinC;
    }

    public static WeatherInfo valueOf(String line) {
        return null;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
            "date=" + date +
            ", tempMaxC=" + tempMaxC + '\'' +
            '}';
    }
}
