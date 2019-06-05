package pt.isel.leic.mpd.v1819.li41d.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ArtistsController {
    private Vertx worker = Vertx.vertx(new VertxOptions().setWorkerPoolSize(1));

    public void searchArtists(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "text/plain");
        response.setChunked(true);

        worker.executeBlocking((f) -> {
            for (int i = 0; i < 128; i++) {
                response.write("Artist1");
                sleep(100);
                response.write("Artist2");
            }

            response.end("Artist3\n");
            f.complete(); },
                ar -> { });



//        worker.setTimer(5000, tid -> response.write("Artist2\n"));
//        worker.setTimer(10000, tid -> response.end("Artist3\n"));

    }

    private void sleep(int tout) {
        try {
            Thread.sleep(tout);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
