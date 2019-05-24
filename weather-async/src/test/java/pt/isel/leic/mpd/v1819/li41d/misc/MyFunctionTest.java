package pt.isel.leic.mpd.v1819.li41d.misc;

import org.junit.Test;

public class MyFunctionTest {

    @Test
    public void useDefaultMethod() {
        MyFunction<String, Integer> mf = s ->  s.length();

        final String str = "Benfica";
        mf.print(str);
        System.out.println(mf.apply(str));

        MyFunction<String, Integer> mf1 = new MyFunction<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }

            @Override
            public void print(String s) {
                System.out.println(this.apply(s) + " - O Maior!!!");
            }
        };



        mf1.print(str);
        System.out.println(mf1.apply(str));
    }
}