package pt.isel.leic.mpd.v1819.li41d.misc.stream;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class Streams {

    private Streams() {    }

    public static <T> Stream<T> collapse(Stream<T> src) {
        return StreamSupport.stream(new CollapseSpliterator(src.spliterator()), false);
    }

    private static class CollapseSpliterator<T> implements Spliterator<T> {
        private Spliterator<T> src;
        T prev = null;

        public CollapseSpliterator(Spliterator<T> src) {
            this.src = src;
        }

        @Override
        public boolean tryAdvance(Consumer<? super T> action) {
            final Object[] curr = {null};
            boolean advanced;
            while ((advanced = src.tryAdvance(
               t -> curr[0] = t
            )) && compareObjects(curr[0], prev));

            if (advanced) {
                prev = (T) curr[0];
                action.accept(prev);
            }

            return advanced;

        }

        private boolean compareObjects(Object o1, Object o2) {
            final boolean equals = Objects.equals(o1, o2);
            return equals;
        }

        @Override
        public Spliterator<T> trySplit() {
            System.out.println("trySplit");
            return null;
        }

        @Override
        public long estimateSize() {
            final long size = src.estimateSize();
            System.out.println("estimateSize: " + size);

            return size;
        }

        @Override
        public int characteristics() {
            final int characteristics = src.characteristics();
            System.out.println("characteristics: " + characteristics);
            return src.characteristics() & ~SIZED;
        }
    }
}
