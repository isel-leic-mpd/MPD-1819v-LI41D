package pt.isel.leic.mpd.v1819.li41d.flow;

public interface Publisher<T> {
    void subscribe(Subscriber<T> subscriber);
}
