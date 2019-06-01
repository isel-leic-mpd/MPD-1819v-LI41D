package pt.isel.leic.mpd.v1819.li41d.observables;

import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.*;

public class CompletableFutureToObservableTest {

    @Test
    public void fromCompletableFuture() {
        final CompletableFuture<String> cf = CompletableFuture.supplyAsync(CompletableFutureToObservableTest::supplyValue);

        //final Observable<String> observable = CompletableFutureToObservable.fromCompletableFuture(cf);
        final Observable<String> observable = CompletableFutureToObservable.fromCompletableFuture(cf);

        observable.blockingSubscribe(System.out::println);


        System.out.println("Done");

    }

    private static String supplyValue() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Benfica";
    }
}