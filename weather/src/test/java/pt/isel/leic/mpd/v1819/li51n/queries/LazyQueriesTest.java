package pt.isel.leic.mpd.v1819.li51n.queries;

import pt.isel.leic.mpd.v1819.li51n.queries.lazy.LazyQueries;

public class LazyQueriesTest extends QueriesTest {

    @Override
    protected <T> Queries<T> createQueries(Iterable<T> src) {
        return LazyQueries.of(src);
    }
}
