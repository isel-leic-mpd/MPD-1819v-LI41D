package pt.isel.leic.mpd.v1819.li41d.misc.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class StreamReductions {

    final List<String> src = Arrays.asList("Sport", "Lisboa", "e", "Benfica", "1904");
    private final int NUM_VALUES = 100;
    final List<Integer> srcNumbers = Stream.iterate(1, i -> i + 1).limit(NUM_VALUES).collect(toList());


    @Test
    public void shouldReturnIdentityValueUsingReduce() {


        final String identity = "";
        final String reduced = Stream.<String>empty().reduce(identity, (s1, s2) -> "Should never be returned");

        assertSame(identity, reduced);
    }

    @Test
    public void shouldReturnMaxValueUsingReduce() {
        final String reduced = src.stream().parallel().reduce("", (s1, s2) -> s1.compareTo(s2) >= 0 ? s1 : s2);

        assertEquals("e", reduced);
    }

    @Test
    public void shouldReturnMaxValueUsingReduceTheWrongWay() {
        final List<Integer> max = new ArrayList<>();

        srcNumbers.stream().parallel().reduce((s1, s2) -> {
            //System.out.println("Current threadId:" + Thread.currentThread().getId());
            try {
                Thread.sleep((long) (Math.random()*100));
            } catch (InterruptedException e) {
                // Abafator used with knowledge of the consequences!!!
            }
            max.add(0, s1.compareTo(s2) >= 0 ? s1  : s2);
            return max.get(0);
        });

        assertEquals((Integer) NUM_VALUES, max.get(0));
    }
}
