package pt.isel.leic.mpd.v1819.li51n.queries;

import pt.isel.leic.mpd.v1819.li51n.utils.HttpRequest;
import pt.isel.leic.mpd.v1819.li51n.weatherapi.WeatherInfo;
import pt.isel.leic.mpd.v1819.li51n.weatherapi.WeatherWebApi;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Queries {



    public static <T> List<T> filter(List<T> src, Filter<T> filter) throws IOException {

        List<T> filteredSrc = new ArrayList<>();

        for (T t : src) {
            if(filter.test(t)) {
                filteredSrc.add(t);
            }
        }

        return filteredSrc;
    }
}
