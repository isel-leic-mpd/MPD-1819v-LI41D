package pt.isel.leic.mpd.v1819.li41d.flow;

import pt.isel.leic.mpd.v1819.li41d.flow.Subscriber;

public interface Publisher<T> {
    void subscribe(Subscriber<T> subscriber);
}
