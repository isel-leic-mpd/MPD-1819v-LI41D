package pt.isel.leic.mpd.v1819.li41d.flow.queries;

import java.util.concurrent.Flow;
import java.util.function.Function;
import java.util.function.Predicate;

public interface PublisherQueries<T> extends Flow.Publisher<T> {


    static <T> PublisherQueries<T> of(T[] values) {
        return sub -> sub.onSubscribe(new SubscriptionFromArray<>(values, sub));
    }



    default <R> PublisherQueries<R> map(Function<T, R> mapper) {
        return sub -> this.subscribe(new SubscriberMapper(sub, mapper));
    }

    default PublisherQueries<T> filter(Predicate<T> predicate) {
        return sub -> this.subscribe(new SubscriberFilter(sub, predicate));
    }

    default PublisherQueries<T> limit(int n) {
        return sub -> this.subscribe(new SubscriberLimit(sub, n));
    }
}
