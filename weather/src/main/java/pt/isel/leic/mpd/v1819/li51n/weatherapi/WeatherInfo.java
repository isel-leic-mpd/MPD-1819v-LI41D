package pt.isel.leic.mpd.v1819.li51n.weatherapi;

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
        String[] words = line.split(",");
        LocalDate date = LocalDate.parse(words[0]);
        int max = Integer.parseInt(words[1]);
        int min = Integer.parseInt(words[3]);
        return new WeatherInfo(date, max, min);
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

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "date=" + date +
                ", tempMaxC=" + tempMaxC +
                ", tempMinC=" + tempMinC +
                '}';
    }


}
