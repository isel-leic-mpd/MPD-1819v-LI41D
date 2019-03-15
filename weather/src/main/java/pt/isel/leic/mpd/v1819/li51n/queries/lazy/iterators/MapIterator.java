package pt.isel.leic.mpd.v1819.li51n.queries.lazy.iterators;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;

public class MapIterator<T, R> extends BaseIterator<R> {
    private final Function<T, R> mapper;
    private final Iterator<T> src;

    public MapIterator(Iterator<T> src, Function<T, R> mapper) {
        this.src = src;
        this.mapper = mapper;
    }

    @Override
    protected boolean tryAdvance(Consumer<R> consumer) {
        if(!src.hasNext()) {
            return false;
        }
        consumer.accept(mapper.apply(src.next()));
        return true;
    }
}
