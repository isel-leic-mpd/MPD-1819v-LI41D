package pt.isel.leic.mpd.v1819.li41d.utils;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static org.asynchttpclient.Dsl.asyncHttpClient;

public class HttpRequest implements Request {

    private AsyncHttpClient asyncHttpClient;


    public HttpRequest() {
        this.asyncHttpClient = asyncHttpClient();
    }

    @Override
    public CompletableFuture<String> getContent(String url) throws IOException {
        return asyncHttpClient.prepareGet(url)
                .execute()
                .toCompletableFuture()
                .thenApply(Response::getResponseBody);
    }
}
