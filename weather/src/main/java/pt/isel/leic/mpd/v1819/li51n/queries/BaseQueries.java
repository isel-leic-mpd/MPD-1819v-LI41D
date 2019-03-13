package pt.isel.leic.mpd.v1819.li51n.queries;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.IntFunction;

abstract public class BaseQueries<T> implements Queries<T> {
    protected final Iterable<T> src;

    public BaseQueries(Iterable<T> src) {
        this.src = src;
    }

    public T first() {
        final Iterator<T> iterator = src.iterator();
        if(!iterator.hasNext()) {
            throw new NoSuchElementException();
        }

        return iterator.next();
    }

    public int count() {

        int i = 0;
        for (T t : src) { ++i; }

        return i;
    }

    public T[] toArray(IntFunction<T[]> arrayFactory) {
        final T[] destArray = arrayFactory.apply(count());
        int i = 0;
        for (T t : src) {
            destArray[i++] = t;
        }

        return destArray;
    }

    @Override
    public Iterator<T> iterator() {
        return src.iterator();
    }
}
