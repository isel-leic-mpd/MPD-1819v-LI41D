package pt.isel.leic.mpd.v1819.li41d.queries.eager;

import pt.isel.leic.mpd.v1819.li41d.queries.BaseQueries;
import pt.isel.leic.mpd.v1819.li41d.queries.Queries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class EagerQueries<T> extends BaseQueries<T> {

    private EagerQueries(Iterable<T> src) {
        super(src);
    }

    public Queries<T> filter(Predicate<T> filter) {
        List<T> filteredSrc = new ArrayList<>();

        for (T t : this) {
            if(filter.test(t)) {
                filteredSrc.add(t);
            }
        }

        return new EagerQueries<>(filteredSrc);
    }


    public <R> Queries<R> map(Function<T, R> map) {
        List<R> mappedSrc = new ArrayList<>();

        for (T t : this.src) {
            mappedSrc.add(map.apply(t));
        }

        return new EagerQueries<>(mappedSrc);
    }

    @Override
    public Queries<T> limit(int n) {
        List<T> filteredSrc = new ArrayList<>();

        for (T t : this) {
            if(--n >= 0) {
                filteredSrc.add(t);
            }
        }

        return new EagerQueries<>(filteredSrc);
    }


    public static <T> Queries<T> of(Iterable<T> src) {
        return new EagerQueries<>(src);
    }

}
