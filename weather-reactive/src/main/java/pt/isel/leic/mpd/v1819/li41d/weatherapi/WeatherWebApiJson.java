package pt.isel.leic.mpd.v1819.li41d.weatherapi;

import com.google.gson.Gson;

import pt.isel.leic.mpd.v1819.li41d.utils.HttpRequest;
import pt.isel.leic.mpd.v1819.li41d.utils.Request;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.dto.LocationInfo;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.dto.WeatherInfo;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.jsonDto.PastWeatherDataWeatherDto;
import pt.isel.leic.mpd.v1819.li41d.weatherapi.jsonDto.PastWeatherDto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public LocationInfo[] search(String query) {
        throw new UnsupportedOperationException();
    }

    @Override
    public WeatherInfo[] pastWeather(double lat, double log, LocalDate from, LocalDate to) throws IOException {
        final String url = getPastWeatherUrl(lat, log, from, to);

        String body = req.getLines(url).collect(Collectors.joining("\n"));
        PastWeatherDto dto = gson.fromJson(body, PastWeatherDto.class);
        PastWeatherDataWeatherDto[] weather = dto.getData().getWeather();

        return Stream.of(weather).map(pwdwDto -> toWeatherInfo(pwdwDto)).toArray(size -> new WeatherInfo[size]);

    }

    WeatherInfo toWeatherInfo(PastWeatherDataWeatherDto dto) {
        return new WeatherInfo(LocalDate.parse(dto.getDate()), dto.getMaxtempC(), dto.getMintempC());

    }
}
