package pt.isel.leic.mpd.v1819.li41d.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

abstract public class BaseRequest implements Request {
    @Override
    final public Stream<String> getLines(String url) throws IOException {
        List<String> lines = new ArrayList<>();

        try(InputStream is = openStream(url)) {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        return lines.stream();
    }

    protected abstract InputStream openStream(String url) throws IOException;
}
