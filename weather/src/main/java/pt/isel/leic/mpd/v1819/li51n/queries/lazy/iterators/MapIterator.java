package pt.isel.leic.mpd.v1819.li51n.queries.lazy.iterators;

import java.util.Iterator;
import java.util.function.Function;

public class MapIterator<T, R> implements Iterator<R> {
    private final Iterator<T> src;
    private final Function<T, R> mapper;

    public MapIterator(Iterator<T> src, Function<T, R> mapper) {
        this.src = src;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        return src.hasNext();
    }

    @Override
    public R next() {
        return mapper.apply(src.next());
    }
}
