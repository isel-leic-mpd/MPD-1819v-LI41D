package pt.isel.leic.mpd.v1819.li51n.queries;

public class LazyQueriesTest extends QueriesTest {

    @Override
    protected <T> Queries<T> createQueries(Iterable<T> src) {
        return LazyQueries.of(src);
    }
}
