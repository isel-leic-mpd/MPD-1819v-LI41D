package pt.isel.leic.mpd.v1819.li41d;

import pt.isel.leic.mpd.v1819.li41d.utils.HttpRequest;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.WeatherWebApi;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.WeatherWebApiCsv;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.dto.WeatherInfo;

import java.io.IOException;
import java.util.stream.Stream;

import static java.time.LocalDate.parse;

public class App {

    public static void main(String[] args) throws IOException {
        WeatherWebApi weatherWebApi = new WeatherWebApiCsv(new HttpRequest());
        /*
         * Get past weather at Lisbon between 2019-02-01 and 2019-02-10
         */
        WeatherInfo[] infos = weatherWebApi
            .pastWeather(38.717,-9.133, parse("2019-01-01"), parse("2019-01-10"));
        for (WeatherInfo info : infos)
            System.out.println(info);
        /*
         * Get locations with name Oporto.
         */
        Stream.of(weatherWebApi.search("Oporto"))
            .forEach(System.out::println);
    }
}
