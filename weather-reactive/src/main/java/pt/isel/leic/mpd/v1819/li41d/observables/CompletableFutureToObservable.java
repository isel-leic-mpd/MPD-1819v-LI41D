package pt.isel.leic.mpd.v1819.li41d.observables;

import io.reactivex.Observable;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureToObservable {
    public static <T>Observable<T> fromCompletableFuture(CompletableFuture<T> cf) {
        return Observable.create(subscriber -> cf.thenAccept(value -> { subscriber.onNext(value); subscriber.onComplete(); })
                .exceptionally(ex -> { subscriber.onError(ex); return null;} ));
    }

    public static <T>Observable<T> fromCompletableFutureWhichShouldNotBeUsed(CompletableFuture<T> cf) {
        return Observable.fromFuture(cf);
    }

}
