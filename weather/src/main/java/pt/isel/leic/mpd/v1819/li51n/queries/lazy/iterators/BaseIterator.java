package pt.isel.leic.mpd.v1819.li51n.queries.lazy.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

public abstract class BaseIterator<T> implements Iterator<T> {

    private Optional<T> current = Optional.empty();


    @Override
    public final boolean hasNext() {
        if (current.isPresent()) {
            return true;
        }

        current = tryAdvance();
        return current.isPresent();
    }

    protected abstract Optional<T> tryAdvance();


    @Override
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        T ret = current.get();
        current = Optional.empty();
        return ret;
    }
}
