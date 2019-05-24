package pt.isel.leic.mpd.v1819.li41d.flow;

public interface Subscriber<T> {
    void onNext(T nextValue);

}
