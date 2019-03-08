package pt.isel.leic.mpd.v1819.li51n.queries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Queries {
    public static <T> Iterable<T> filter(Iterable<T> src, Filter<T> filter) throws IOException {

        List<T> filteredSrc = new ArrayList<>();

        for (T t : src) {
            if(filter.test(t)) {
                filteredSrc.add(t);
            }
        }

        return filteredSrc;
    }
}
