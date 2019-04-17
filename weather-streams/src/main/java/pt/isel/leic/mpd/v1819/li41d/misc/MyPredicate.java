package pt.isel.leic.mpd.v1819.li41d.misc;


@FunctionalInterface
public interface MyPredicate<T> {
    boolean test(T t);


    default MyPredicate<T> and(MyPredicate<T> that) {
        return t -> this.test(t) && that.test(t);
    }

    default MyPredicate<T> or(MyPredicate<T> that) {
        return t -> this.test(t) || that.test(t);
    }

    default MyPredicate<T> not() {
        return t -> !this.test(t);
    }
}
