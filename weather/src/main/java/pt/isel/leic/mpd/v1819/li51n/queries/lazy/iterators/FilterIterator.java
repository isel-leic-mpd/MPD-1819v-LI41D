package pt.isel.leic.mpd.v1819.li51n.queries.lazy.iterators;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Predicate;

public class FilterIterator<T> extends BaseIterator<T> {
    private final Iterator<T> src;
    private final Predicate<T> filter;


    public FilterIterator(Iterator<T> src, Predicate<T> filter) {
        this.src = src;
        this.filter = filter;
    }


    @Override
    protected Optional<T> tryAdvance() {
        T current;
        while (src.hasNext()) {
            current = src.next();
            if(filter.test(current)) {
                return Optional.ofNullable(current);
            }
        }
        return Optional.empty();
    }

}
