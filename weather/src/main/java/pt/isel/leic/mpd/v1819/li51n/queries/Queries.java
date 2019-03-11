package pt.isel.leic.mpd.v1819.li51n.queries;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;

public class Queries<T> {

    public static <T> Iterable<T> filter(Iterable<T> src, Predicate<T> filter) {
        List<T> filteredSrc = new ArrayList<>();

        for (T t : src) {
            if(filter.test(t)) {
                filteredSrc.add(t);
            }
        }

        return filteredSrc;
    }


    public static <T> T first(Iterable<T> src) {
        final Iterator<T> iterator = src.iterator();
        if(!iterator.hasNext()) {
            throw new NoSuchElementException();
        }

        return iterator.next(); 
    }




    public static <T, R> Iterable<R> map(Iterable<T> src, Function<T, R> map) {
        List<R> mappedSrc = new ArrayList<>();

        for (T t : src) {
            mappedSrc.add(map.apply(t));
        }

        return mappedSrc;
    }

    public static <T> int count(Iterable<T> src) {

        int i = 0;
        for (T t : src) { ++i; }

        return i;
    }

    public static <T> T[] toArray(Iterable<T> src, IntFunction<T[]> arrayFactory) {
        final T[] destArray = arrayFactory.apply(count(src));
        int i = 0;
        for (T t : src) {
            destArray[i++] = t;
        }

        return destArray;
    }

    public static <T> Queries<T> of(Iterable<T> src) {
        return null;
    }
}
