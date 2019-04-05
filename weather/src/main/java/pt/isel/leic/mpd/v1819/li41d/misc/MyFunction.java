package pt.isel.leic.mpd.v1819.li41d.misc;


@FunctionalInterface
public interface MyFunction<T, R> {
    R apply(T t);

    default void print(T t) {
        System.out.println("###" + this.apply(t));

    }

    default <U> MyFunction<T, U> andThen(MyFunction<R, U> after) {
        return (t) -> {
            R r = this.apply(t);
            return after.apply(r);
        };
    }


    default <U> MyFunction<U, R> compose(MyFunction<U, T> before) {
        return (u) ->
            this.apply(before.apply(u));

    }
}
