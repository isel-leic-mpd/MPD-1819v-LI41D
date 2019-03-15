package pt.isel.leic.mpd.v1819.li51n.queries.lazy.iterators;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;

public class MapIterator<T, R> extends BaseIterator<R> {
    private final Function<T, R> mapper;
    private final Iterator<T> src;

    public MapIterator(Iterator<T> src, Function<T, R> mapper) {
        this.src = src;
        this.mapper = mapper;
    }

    @Override
    protected Optional<R> tryAdvance() {
        if(!src.hasNext()) {
            return Optional.empty();
        }
        return Optional.ofNullable(mapper.apply(src.next()));
    }
}
