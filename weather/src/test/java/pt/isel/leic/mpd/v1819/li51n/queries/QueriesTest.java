package pt.isel.leic.mpd.v1819.li51n.queries;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class QueriesTest {

    private final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
    private final List<String> strings = Arrays.asList("Sport", "Lisboa", "e", "Benfica");

    @Test
    public void filterTest() {
        final Iterable<Integer> filteredNumbers = Queries.filter(numbers, i -> i % 2 == 0);

        assertEquals(numbers.size()/2, Queries.count(filteredNumbers));
    }

    @Test
    public void mapTest() {
        final Iterable<Integer> mappedStrings = Queries.map(strings, s -> s.length());
        assertEquals(Queries.count(mappedStrings), strings.size());
        assertArrayEquals(new Integer[] {5, 6, 1, 7}, Queries.toArray(mappedStrings, i -> new Integer[i]));
    }


    @Test
    public void mapAndFilterTest() {
        final Iterable<Integer> filteredNumbers = Queries.filter(Queries.map(strings, s -> s.length()), i -> i % 2 == 0);

        assertEquals(1, Queries.count(filteredNumbers));

        // How I would like to use Queries... To achieve that, I have some work to do....
        // Queries.of(strings).map(s -> s.length()).filter(i -> i % 2 == 0);
    }

    @Test
    public void someTest() {
        Queries.first(Queries.filter(Queries.map(strings, s -> s.length()), i -> i % 2 == 0));

    }
}