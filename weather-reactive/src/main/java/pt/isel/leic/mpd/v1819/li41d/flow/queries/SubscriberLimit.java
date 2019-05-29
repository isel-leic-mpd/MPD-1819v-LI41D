package pt.isel.leic.mpd.v1819.li41d.flow.queries;

import java.util.concurrent.Flow;

public class SubscriberLimit<T> implements Flow.Subscriber<T> {
    private final Flow.Subscriber<T> subR;
    private int n;
    private Flow.Subscription subscription;

    public SubscriberLimit(Flow.Subscriber<T> subR, int n) {
        this.subR = subR;
        this.n = n;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
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
        if(--n >= 0) {
            subR.onNext(item);
        }

        subscription.cancel();
    }
}
