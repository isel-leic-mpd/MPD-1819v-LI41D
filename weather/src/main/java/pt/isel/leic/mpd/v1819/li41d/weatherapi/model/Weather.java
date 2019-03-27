package pt.isel.leic.mpd.v1819.li41d.weatherapi.model;

import java.time.LocalDate;

public class Weather {
    final LocalDate date;
    final int tempMaxC;
    final int tempMinC;

    public Weather(LocalDate date, int tempMaxC, int tempMinC, double precipMM, String desc) {
        this.date = date;
        this.tempMaxC = tempMaxC;
        this.tempMinC = tempMinC;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getTempMaxC() {
        return tempMaxC;
    }

    public int getTempMinC() {
        return tempMinC;
    }
}
