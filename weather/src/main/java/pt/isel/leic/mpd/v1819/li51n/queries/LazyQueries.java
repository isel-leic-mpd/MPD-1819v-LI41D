package pt.isel.leic.mpd.v1819.li51n.queries;

import java.util.function.Function;
import java.util.function.Predicate;

public class LazyQueries<T> extends BaseQueries<T> {

    public LazyQueries(Iterable<T> src) {
        super(src);
    }

    @Override
    public EagerQueries<T> filter(Predicate<T> filter) {
        return null;
    }

    @Override
    public <R> EagerQueries<R> map(Function<T, R> map) {
        return null;
    }

    public static <T> Queries<T> of(Iterable<T> src) {
        return new LazyQueries<T>(src);
    }
}
