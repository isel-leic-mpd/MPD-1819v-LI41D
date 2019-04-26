package pt.isel.leic.mpd.v1819.li41d.misc.stream;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class StreamsTest {

    @Test
    public void collapse() {
        // Arrange & Act
        final List<String> src = Arrays.asList("a", "a", "b", "bb", "c", "c", "a");
        final Stream<String> collapsed = Streams.collapse(src.stream()).parallel();


        // Assert
        final List<String> expected = Arrays.asList("a", "b", "bb", "c", "a");
        final Object[] actuals = collapsed.toArray();
        Assert.assertArrayEquals(expected.toArray(), actuals);

    }
}