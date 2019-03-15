package pt.isel.leic.mpd.v1819.li51n.queries.lazy.iterators;

import java.util.Iterator;
import java.util.function.Consumer;

public class LimitIterator<T> extends BaseIterator<T> {
    private final Iterator<T> src;
    private int n;

    public LimitIterator(Iterator<T> src, int n) {
        this.src = src;
        this.n = n;
    }

    @Override
    protected boolean tryAdvance(Consumer<T> consumer) {
        if(n > 0 && src.hasNext()) {
            --n;
            consumer.accept(src.next());
            return true;
        }
        return false;
    }
}
