package pt.isel.leic.mpd.v1819.li41d.misc;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class MethodReferenceTests {
    @Test
    public void shouldCallAnStaticMethodReference() {


        m(() -> Math.random());

        Supplier<Double> sup = Math::random;
        m(sup);
        m(sup);

        System.out.println(m1().get());
    }

    @Test
    public void shouldCallAnInstanceMethodReferenceForASpecificObject() {
        String str = "SLB";

        Supplier<Integer> sup = createString()::length;
        Supplier<Integer> sup1 = str::length;
        m(sup);
        m(sup1);

    }

    @Test
    public void shouldCallAnInstanceMethodReferenceForAnArbitraryObject() {
        BiFunction<String, Integer, String> strIntToStr = (s, i) -> s.substring(i);
        BiFunction<String, Integer, String> strIntToStr1 = String::substring;

        final String ica = strIntToStr.apply("Benfica", 4);
        final String slb = strIntToStr.apply("SLB", 0);

    }

    String createString() {
        return "SLB";
    }

    private <T> void m(Supplier<T> sup) {
        System.out.println(sup.get());
    }

    private Supplier<Double> m1() {
        //return () -> Math.random();
        return Math::random;
    }


    String subStr(String str, int idx) {
        return str.substring(idx);
    }
}
