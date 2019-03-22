package pt.isel.leic.mpd.v1819.li41d.queries.lazy;


import pt.isel.leic.mpd.v1819.li41d.queries.BaseQueries;
import pt.isel.leic.mpd.v1819.li41d.queries.Queries;
import pt.isel.leic.mpd.v1819.li41d.queries.lazy.iterators.FilterIterator;
import pt.isel.leic.mpd.v1819.li41d.queries.lazy.iterators.LimitIterator;
import pt.isel.leic.mpd.v1819.li41d.queries.lazy.iterators.MapIterator;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

public class LazyQueries<T> extends BaseQueries<T> {

    private LazyQueries(Iterable<T> src) {
        super(src);
    }

    @Override
    public Queries<T> filter(Predicate<T> filter) {
        return new LazyQueries<>(new Iterable<T>() {

            @Override
            public Iterator<T> iterator() {
                return new FilterIterator<>(src.iterator(), filter);
            }
        });
    }

    @Override
    public <R> Queries<R> map(Function<T, R> mapper) {
        return new LazyQueries<>(new Iterable<R>() {

            @Override
            public Iterator<R> iterator() {
                return new MapIterator<>(src.iterator(), mapper);
            }
        });
    }

    @Override
    public Queries<T> limit(int n) {
        return new LazyQueries<>(() -> new LimitIterator<>(src.iterator(), n));
    }

    public static <T> Queries<T> of(Iterable<T> src) {
        return new LazyQueries<>(src);
    }
}
