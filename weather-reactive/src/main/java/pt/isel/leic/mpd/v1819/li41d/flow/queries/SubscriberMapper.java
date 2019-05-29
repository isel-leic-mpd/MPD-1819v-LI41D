package pt.isel.leic.mpd.v1819.li41d.flow.queries;

import java.util.concurrent.Flow;
import java.util.function.Function;

public class SubscriberMapper<T, R> implements Flow.Subscriber<T> {
    private final Flow.Subscriber<R> subR;
    private final Function<T,R> mapper;

    public SubscriberMapper(Flow.Subscriber<R> subR, Function<T, R> mapper) {
        this.subR = subR;
        this.mapper = mapper;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subR.onSubscribe(subscription);
    }

    @Override
    public void onError(Throwable throwable) {
        subR.onError(throwable);
    }

    @Override
    public void onComplete() {
        subR.onComplete();
    }

    @Override
    public void onNext(T item) {
        subR.onNext(mapper.apply(item));
    }
}