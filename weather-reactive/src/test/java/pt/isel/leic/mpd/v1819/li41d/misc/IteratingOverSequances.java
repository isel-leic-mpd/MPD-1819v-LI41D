package pt.isel.leic.mpd.v1819.li41d.misc;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IteratingOverSequances {


    List<String> strings = Arrays.asList("Sport", "Lisboa", "e", "Benfica");

    @Test
    public void shouldIteratorOverASequenceWithIterable() {
        final Iterator<String> iterator = strings.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void shouldIteratorOverASequenceWithForEach() {

//        final Iterator<String> iterator = strings.iterator();
//        while (iterator.hasNext()) {
//            String s = iterator.next();
//            System.out.println(s);
//        }

        for(String s : strings) {
            System.out.println(s);
        }
    }

    @Test
    public void shouldIteratorOverASequenceWithSpliterator() {

        final Spliterator<String> spliterator = strings.spliterator();
        while (spliterator.tryAdvance(System.out::println));
    }


    @Test
    public void cannotIterateMoreThanOnceOverAStream() {

        Stream<String> stream = strings.stream();

        System.out.println(stream.count());



        final Stream<Integer> infiniteStream = Stream.iterate(1, n -> ++n);

        iterateMoreThanOnceOverAStream(infiniteStream);

    }


    private <T> void iterateMoreThanOnceOverAStream(Stream<T> stream) {
        final List<T> list = stream.limit(100).collect(Collectors.toList());

        list.stream().count();

        list.stream().count();

    }
}
