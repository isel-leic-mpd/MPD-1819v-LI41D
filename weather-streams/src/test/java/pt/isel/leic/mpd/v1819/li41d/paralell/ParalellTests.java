package pt.isel.leic.mpd.v1819.li41d.paralell;

import org.junit.Before;
import org.junit.Test;
import pt.isel.leic.mpd.v1819.li41d.utils.ExecutionBenchmark;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ParalellTests {
    final Path basePath = Paths.get("/Users/lfalcao/Cloud Drives/OneDrive/OneDrive - Instituto Superior de Engenharia de Lisboa/ISEL/Disciplinas/2018-2019/2018-2019Ver-PI/Repositories/PI-1819v-LI51N/node.js8-the-right-way/databases/data/cache/epub");
    private final PathMatcher matcher = FileSystems.getDefault()
            .getPathMatcher("glob:*.rdf");
    Stream<Path> files = null;


    @Before
    public void setUp() throws Exception {
        files = Files.walk(basePath, 5).filter(this::fileMatched).limit(10000);

    }

    @Test
    public void shouldCountAllFilesLinesWithinAGivenDirectorySequentially() throws IOException {
        countFilesLines(files);
    }

    @Test
    public void shouldCountAllFilesLinesWithinAGivenDirectoryWithParallelStream() throws IOException {
        countFilesLines(files.parallel());
    }




    private void countFilesLines(Stream<Path> files) {
        ExecutionBenchmark.measure(() -> {
            long totalLines = files.mapToLong(this::countLines).sum();
            System.out.println(totalLines);
        });
    }

    private long countLines(Path path) {
        try {
            return Files.lines(path).count();
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }


    class TaskExecutor {
        private final List<Path> files;
        private final int numTasks;
        private final long numFiles;
        private final long numFilesPerTask;


        public TaskExecutor(Stream<Path> stream, int numTasks) {
            this.numTasks = numTasks;
            this.files = stream.collect(toList());
            this.numFiles = files.size();
            this.numFilesPerTask = numFiles / numTasks;
        }

        void run() {

            final Stream<Thread> threadStream = IntStream.range(0, numTasks).mapToObj(this::countLines);

        }

        private Thread countLines(int i) {

            return new Thread(() -> {
                countFilesLines(files.stream().skip(i*numFilesPerTask).limit(numFilesPerTask));

            });
        }
    }

    // Compares the glob pattern against
    // the file or directory name.
    boolean fileMatched(Path file) {
        Path name = file.getFileName();
        return name != null && matcher.matches(name);
    }
}
