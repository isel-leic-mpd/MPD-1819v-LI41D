package pt.isel.leic.mpd.v1819.li41d.vertx;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

public class ArtistsController {
    public void searchArtists(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "text/plain");
        response.setChunked(true);

        response.write("Artist1\n");

        routingContext.vertx().setTimer(5000, tid -> response.write("Artist2\n"));
        routingContext.vertx().setTimer(10000, tid -> response.end("Artist3\n"));

    }
}
