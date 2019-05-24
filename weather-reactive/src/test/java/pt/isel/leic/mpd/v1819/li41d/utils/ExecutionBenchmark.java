package pt.isel.leic.mpd.v1819.li41d.utils;

public class ExecutionBenchmark {
    public static void measure(Runnable runnable) {
        final long start = System.currentTimeMillis();
        runnable.run();
        final long end = System.currentTimeMillis();

        System.out.println("Operation took " + (double)(end-start)/1000 + "s");

    }


}
