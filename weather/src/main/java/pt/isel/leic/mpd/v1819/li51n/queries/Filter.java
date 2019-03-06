package pt.isel.leic.mpd.v1819.li51n.queries;

public interface Filter<T> {
    boolean test(T t);
}
