package pt.isel.leic.mpd.v1819.li51n.queries;

import pt.isel.leic.mpd.v1819.li51n.queries.eager.EagerQueries;

public class EagerQueriesTest extends QueriesTest {

    @Override
    protected <T> Queries<T> createQueries(Iterable<T> src) {
        return EagerQueries.of(src);
    }
}
