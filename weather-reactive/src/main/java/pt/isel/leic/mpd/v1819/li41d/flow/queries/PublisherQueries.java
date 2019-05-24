package pt.isel.leic.mpd.v1819.li41d.flow.queries;

import pt.isel.leic.mpd.v1819.li41d.flow.Publisher;
import pt.isel.leic.mpd.v1819.li41d.flow.Subscriber;

import java.util.function.Function;

public class PublisherQueries<T> implements Publisher<T> {
    public static <T> PublisherQueries of(T[] values) {
        return null;
    }

    @Override
    public void subscribe(Subscriber<T> subscriber) {
        return;
    }


    public <R> PublisherQueries<R> map(Function<T, R> mapper) {
        return null;
    }
}
