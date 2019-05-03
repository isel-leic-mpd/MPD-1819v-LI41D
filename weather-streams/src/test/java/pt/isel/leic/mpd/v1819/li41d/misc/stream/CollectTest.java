package pt.isel.leic.mpd.v1819.li41d.misc.stream;

import org.junit.Test;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class CollectTest {

    final List<String> src = Arrays.asList("a", "a", "b", "bb", "c", "c", "a");


    @Test
    public void shouldReduceAStreamToAListUsingCollect() {

        List<String> result = src.stream().parallel().collect(
                this::supplier,
                this::accumulator,
                this::combiner
        );

        System.out.println("result:" + result);
    }

    @Test
    public void shouldReduceAStreamToItsMaximumValue() {

        String[] max= src.stream().parallel().collect(
                () -> new String[] {"\0"},
                (acc, currStr) -> acc[0] = currStr.compareTo(acc[0]) > 0 ? currStr : acc[0],
                (acc1, acc2) -> acc1[0] = acc1[0].compareTo(acc2[0]) > 0 ? acc1[0] : acc2[0]
        );

        assertEquals("c", max[0]);
    }


    @Test
    public void shouldReduceAStreamToAListUsingToListCollectorFromCollectorsClass() {

        List<String> result = src.stream().parallel().collect(Collectors.toList());

        System.out.println("result:" + result);
    }

    @Test
    public void shouldReduceAStreamToAListUsingACustomCollector() {

        List<String> result = src.stream().collect(new MyToListCollector<>());

        System.out.println("result:" + result);
    }



    private List<String> supplier() {
        System.out.println("supplier called");
        return new LinkedList<>();
    }

    private void accumulator(List<String> acc, String currStr) {
        System.out.println(MessageFormat.format("accumulator called with {0} and {1}", acc, currStr));
        acc.add(currStr);

    }

    private int cntCombine = 0;

    private void combiner(List<String> acc1, List<String> acc2) {
        System.out.println(MessageFormat.format("{2} - combiner called with {0} and {1}", acc1, acc2, ++cntCombine));
        acc1.addAll(acc2);

    }
}
