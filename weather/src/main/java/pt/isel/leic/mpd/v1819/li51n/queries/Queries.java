package pt.isel.leic.mpd.v1819.li51n.queries;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;

public interface Queries<T> extends Iterable<T> {
    Queries<T> filter(Predicate<T> filter);

    <R> Queries<R> map(Function<T, R> map);


    Queries<T> limit(int n);


    T first();

    int count();

    T[] toArray(IntFunction<T[]> arrayFactory);

    @Override
    Iterator<T> iterator();
}
