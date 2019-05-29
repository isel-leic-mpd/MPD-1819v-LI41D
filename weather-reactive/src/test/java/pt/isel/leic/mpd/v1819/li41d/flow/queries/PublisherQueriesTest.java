package pt.isel.leic.mpd.v1819.li41d.flow.queries;

import org.junit.Test;

import java.text.MessageFormat;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Flow;

import static org.junit.Assert.assertEquals;


public class PublisherQueriesTest {
    private final String[] src = {"Sport", "Lisboa", "e", "Benfica"};

    @Test
    public void shouldCreateAPublisherFromAnArray() {
        final PublisherQueries<String> publisher = PublisherQueries.of(src);

        assertPublisherIndividually(src, publisher);

    }

    @Test
    public void shouldFilter() {
        final PublisherQueries<String> publisher = PublisherQueries.of(src).filter(s -> s.length() > 2);

        assertPublisherIndividually(new String[] {"Sport", "Lisboa", "Benfica"}, publisher);

    }


    @Test
    public void shouldNotRaiseStackOverflow() {
        CompletableFuture cf = new CompletableFuture();
        final PublisherQueries<String> publisher = PublisherQueries.of(src);

        final Flow.Subscriber<String> subscriber = new Flow.Subscriber<>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                System.out.println(item + " ");
                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable);
            }

            @Override
            public void onComplete() {
                cf.complete(null);
            }
        };
        publisher.subscribe(subscriber);


        cf.join();

    }

    static <T> void assertPublisherIndividually(T[] expected, Flow.Publisher<T> actual) {
        CompletableFuture<Void> cf  = new CompletableFuture<>();
        int [] count = {0};
        actual
                .subscribe(new Flow.Subscriber<T>() {
                    Flow.Subscription sign;
                    int i = 0;
                    public void onSubscribe(Flow.Subscription subscription) {
                        subscription.request(1);
                        this.sign = subscription;
                    }
                    public void onNext(T item) {
                        System.out.println(MessageFormat.format("Item[{0}]={1}", i, item));
                        assertEquals(expected[i++], item);
                        sign.request(1);
                    }
                    public void onError(Throwable err) {
                        cf.completeExceptionally(err);
                    }
                    public void onComplete() {
                        cf.complete(null);
                        count[0] = i;
                    }
                });
        cf.join();
        assertEquals(expected.length, count[0]);
    }
}