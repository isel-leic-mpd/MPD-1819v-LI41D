package pt.isel.leic.mpd.v1819.li51n.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HttpRequest extends BaseRequest {
    @Override
    protected InputStream openStream(String url) throws IOException {
        return new URL(url).openStream();
    }
}
