package pt.isel.leic.mpd.v1819.li41d.vertx;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class VertxHttpServerWithRouter {

    private static final int PORT = 8080;

    public static void main(String[] args) {
        final Vertx vertx = Vertx.vertx();

        HttpServer server = vertx.createHttpServer();


        final Router router = Router.router(vertx);

        ArtistsController artistsController = new ArtistsController();

        router.get("/artists").handler(artistsController::searchArtists);

        server.requestHandler(router);

        server.listen(PORT);

        System.out.println("Server listening on http://localhost:" + PORT);




    }
}
