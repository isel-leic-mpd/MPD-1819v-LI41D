package pt.isel.leic.mpd.v1819.li51n.queries.lazy.iterators;

import java.util.Iterator;
import java.util.Optional;

public class LimitIterator<T> extends BaseIterator<T> {
    private final Iterator<T> src;
    private int n;

    public LimitIterator(Iterator<T> src, int n) {
        this.src = src;
        this.n = n;
    }

    @Override
    public Optional<T> tryAdvance() {
        if(n > 0 && src.hasNext()) {
            --n;
            return Optional.ofNullable(src.next());
        }

        return Optional.empty();
    }
}
