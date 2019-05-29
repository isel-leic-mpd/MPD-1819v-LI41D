package pt.isel.leic.mpd.v1819.li41d.flow.queries;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.function.Predicate;

public class SubscriberFilter<T> implements Subscriber<T> {

    private final Subscriber<T> sub;
    private final Predicate<T> p;
    private Subscription sign;

    public SubscriberFilter(Subscriber<T> sub, Predicate<T> p) {
        this.sub = sub;
        this.p = p;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.sign = subscription;
        sub.onSubscribe(subscription);
    }

    @Override
    public void onNext(T item) {
        if(p.test(item))
            sub.onNext(item);
        else
            sign.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        sub.onError(throwable);
    }

    @Override
    public void onComplete() {
        sub.onComplete();
    }
}
