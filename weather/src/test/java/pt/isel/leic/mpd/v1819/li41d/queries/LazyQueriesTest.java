package pt.isel.leic.mpd.v1819.li41d.queries;

import pt.isel.leic.mpd.v1819.li41d.queries.lazy.LazyQueries;

public class LazyQueriesTest extends QueriesTest {

    @Override
    protected <T> Queries<T> createQueries(Iterable<T> src) {
        return LazyQueries.of(src);
    }
}
