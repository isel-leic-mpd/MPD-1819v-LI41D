package pt.isel.leic.mpd.v1819.li41d.flow.spreadsheet;

import pt.isel.leic.mpd.v1819.li41d.flow.Publisher;
import pt.isel.leic.mpd.v1819.li41d.flow.Subscriber;

import java.util.ArrayList;
import java.util.Collection;

public class SimpleCell implements Publisher<Integer>, Subscriber<Integer> {
    private int value = 0;
    private String name;

    Collection<Subscriber<Integer>> subscribers = new ArrayList<>();

    public SimpleCell(String name) {
        this.name = name;
    }

    @Override
    public void subscribe(Subscriber<Integer> subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void onNext(Integer nextValue) {
        this.value = nextValue;
        System.out.println(this.name + ":" + this.value);
        notifyAllSubscribers();

    }


    public int getValue() {
        return value;
    }

    private void notifyAllSubscribers() {
        subscribers.forEach(s -> s.onNext(this.value));
    }
}
