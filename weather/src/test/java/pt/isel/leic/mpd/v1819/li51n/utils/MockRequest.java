package pt.isel.leic.mpd.v1819.li51n.utils;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MockRequest extends AbstractRequest {
//
//    @Override
//    protected InputStream openStream(String uri) {
//    String[] parts = uri.split("/");
//        String path = parts[parts.length-1]
//                .replace('?', '-')
//                .replace('&', '-')
//                .replace('=', '-')
//                .replace(',', '-')
//                .substring(0,68);
//        try {
//            return ClassLoader.getSystemResource(path).openStream();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MockRequest extends BaseRequest {
    @Override
    protected InputStream openStream(String url) throws IOException {
        String[] parts = url.split("/");
        String path = parts[parts.length-1]
                .replace('?', '-')
                .replace('&', '-')
                .replace('=', '-')
                .replace(',', '-')
                .substring(0,68);

        return ClassLoader.getSystemResource(path).openStream();

    }
}


