package pt.isel.leic.mpd.v1819.li51n.queries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class EagerQueries<T> extends BaseQueries<T> {

    private EagerQueries(Iterable<T> src) {
        super(src);
    }

    public EagerQueries<T> filter(Predicate<T> filter) {
        List<T> filteredSrc = new ArrayList<>();

        for (T t : this) {
            if(filter.test(t)) {
                filteredSrc.add(t);
            }
        }

        return new EagerQueries<>(filteredSrc);
    }


    public <R> EagerQueries<R> map(Function<T, R> map) {
        List<R> mappedSrc = new ArrayList<>();

        for (T t : this.src) {
            mappedSrc.add(map.apply(t));
        }

        return new EagerQueries<>(mappedSrc);
    }


    public static <T> Queries<T> of(Iterable<T> src) {
        return new EagerQueries<T>(src);
    }

}
