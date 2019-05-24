package pt.isel.leic.mpd.v1819.li41d.utils;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * Interface that allows its users to obtain a list of strings with each line
 * of its text content.
 */
@FunctionalInterface
public interface Request {
    /**
     * Gets the content of the given {@code url} response
     * @param url the url to get its content lines
     * @return the content of the given {@code url} response
     */
    CompletableFuture<String> getContent(String url) throws IOException;

}
