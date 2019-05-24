package pt.isel.leic.mpd.v1819.li41d.misc;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorsTest {
    @Test
    public void shouldSortWithMultipleCrirerias() {
        final List<String> strings = Arrays.asList("Sport", "Lisboa", "e", "Benfica", "o", "Maior");
        Comparator<String> cmp1 = (s1, s2) -> s1.length() - s2.length();

        Comparator<String> cmp2 = (s1, s2) -> s1.compareTo(s2);

        final Comparator<String> cmp3 = cmp1.thenComparing(cmp2);
        final Comparator<String> cmp = cmp3.reversed();

        Collections.sort(strings, cmp);

        System.out.println(strings);


    }
}
