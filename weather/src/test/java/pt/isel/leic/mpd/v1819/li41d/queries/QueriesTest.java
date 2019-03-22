package pt.isel.leic.mpd.v1819.li41d.queries;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


abstract public class QueriesTest {

    private final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
    private final List<String> strings = Arrays.asList("Sport", "Lisboa", "e", "Benfica");
    private Queries<Integer> queries2;


    @Test
    public void shouldFilter() {
        final Queries<Integer> filteredNumbers = createQueries(numbers).filter(i -> i % 2 == 0);


        assertEquals(numbers.size()/2, filteredNumbers.count());
    }


    @Test
    public void shouldMap() {
        final Queries<Integer> mappedStrings = createQueries(strings).map(s -> s.length());
        assertEquals(mappedStrings.count(), strings.size());
        assertArrayEquals(new Integer[] {5, 6, 1, 7}, mappedStrings.toArray(i -> new Integer[i]));
    }


    @Test
    public void shouldMapThenFilter() {
        final Queries<Integer> filteredNumbers = createQueries(strings).map(s -> s.length()).filter(i -> i % 2 == 0);

        assertEquals(1, filteredNumbers.count());

        // How I would like to use Queries... To achieve that, I have some work to do....
        // Queries.of(strings).map(s -> s.length()).filter(i -> i % 2 == 0);
    }

    @Test
    public void shouldLimit() {
        final Queries<String> filteredStrings = createQueries(strings).limit(3);

        assertEquals(3, filteredStrings.count());
    }

    @Test
    public void shouldNotReturnThisOnNonTerminalMethods() {
        final Queries<Integer> queries1 = createQueries(numbers);
        final Queries<Integer> queries2 = queries1.filter(i -> i % 2 == 0);

        assertEquals(numbers.size(), queries1.count());
        assertEquals(numbers.size()/2, queries2.count());

    }

    protected abstract  <T> Queries<T> createQueries(Iterable<T> src);
}
