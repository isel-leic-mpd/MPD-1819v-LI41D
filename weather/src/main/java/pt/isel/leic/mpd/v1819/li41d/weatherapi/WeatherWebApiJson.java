package pt.isel.leic.mpd.v1819.li41d.weatherapi;

import com.google.gson.Gson;
import pt.isel.leic.mpd.v1819.li41d.queries.lazy.LazyQueries;
import pt.isel.leic.mpd.v1819.li41d.utils.HttpRequest;
import pt.isel.leic.mpd.v1819.li41d.utils.Request;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.restdto.PastWeatherDataWeatherDto;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.restdto.PastWeatherDto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

public class WeatherWebApiJson extends BaseWeatherApi {

    private final Request req;
    private final Gson gson;

    public WeatherWebApiJson() {
        this(new HttpRequest(), new Gson());
    }


    public WeatherWebApiJson(Request req) {
        this(req, new Gson());
    }

    public WeatherWebApiJson(Request req, Gson gson) {
        super("json", "json");
        this.req = req;
        this.gson = gson;
    }

    @Override
    public Iterable<LocationInfo> search(String query) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<WeatherInfo> pastWeather(double lat, double log, LocalDate from, LocalDate to) throws IOException {
        final String url = getPastWeatherUrl(lat, log, from, to);
        Iterable<String> src = req.getLines(url);
        String body = String.join("", src);
        PastWeatherDto dto = gson.fromJson(body, PastWeatherDto.class);
        PastWeatherDataWeatherDto[] weather = dto.getData().getWeather();

        return LazyQueries.of(Arrays.asList(weather)).map(pwdwDto -> toWeatherInfo(pwdwDto));

    }

    WeatherInfo toWeatherInfo(PastWeatherDataWeatherDto dto) {
        return new WeatherInfo(LocalDate.parse(dto.getDate()), dto.getMaxtempC(), dto.getMintempC());

    }
}
