package pt.isel.leic.mpd.v1819.li51n.queries;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;

public interface Queries<T> extends Iterable<T> {
    EagerQueries<T> filter(Predicate<T> filter);

    <R> EagerQueries<R> map(Function<T, R> map);

    abstract T first();

    abstract int count();

    abstract T[] toArray(IntFunction<T[]> arrayFactory);

    @Override
    Iterator<T> iterator();
}
