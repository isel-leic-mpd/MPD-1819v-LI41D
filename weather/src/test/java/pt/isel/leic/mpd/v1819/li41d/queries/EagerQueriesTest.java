package pt.isel.leic.mpd.v1819.li41d.queries;

import pt.isel.leic.mpd.v1819.li41d.queries.eager.EagerQueries;

public class EagerQueriesTest extends QueriesTest {

    @Override
    protected <T> Queries<T> createQueries(Iterable<T> src) {
        return EagerQueries.of(src);
    }
}
