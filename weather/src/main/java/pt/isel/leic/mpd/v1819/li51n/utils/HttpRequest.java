package pt.isel.leic.mpd.v1819.li51n.utils;

import pt.isel.leic.mpd.v1819.li51n.weatherapi.WeatherInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpRequest implements Request {
    @Override
    public List<String> getLines(String url) throws IOException {
        List<String> lines = new ArrayList<>();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }
}
