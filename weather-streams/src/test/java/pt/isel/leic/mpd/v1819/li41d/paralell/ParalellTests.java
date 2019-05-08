package pt.isel.leic.mpd.v1819.li41d.paralell;

import org.javaync.io.AsyncFiles;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pt.isel.leic.mpd.v1819.li41d.utils.ExecutionBenchmark;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ParalellTests {
    final static int NUMBER_OF_PROCESSORS = Runtime.getRuntime().availableProcessors();
    final static int NUMBER_OF_TASKS = NUMBER_OF_PROCESSORS;
    final static Path basePath = Paths.get("/Users/lfalcao/Cloud Drives/OneDrive/OneDrive - Instituto Superior de Engenharia de Lisboa/ISEL/Disciplinas/2018-2019/2018-2019Ver-PI/Repositories/PI-1819v-LI51N/node.js8-the-right-way/databases/data/cache/epub");
    static long numFiles;

    private static final PathMatcher matcher = FileSystems.getDefault()
            .getPathMatcher("glob:*.rdf");
    Stream<Path> files = null;

    @BeforeClass
    public static void beforeClasss() throws Exception {
        numFiles = getFileStream().count();
    }

    @Before
    public void setUp() throws Exception {
        files = getFileStream();

    }



    @Test
    public void shouldCountAllFilesLinesWithinAGivenDirectorySequentially() throws IOException {
        System.out.println(countFilesLinesInternal(files));
    }

    @Test
    public void shouldCountAllFilesLinesWithinAGivenDirectoryWithParallelStream() throws IOException {
        System.out.println(countFilesLinesInternal(files.parallel()));
    }


    @Test
    public void shouldCountAllFilesLinesWithinAGivenDirectoryWithManualThreadCreation() throws IOException {
        System.out.println(new LineCounterExecutor(files, NUMBER_OF_PROCESSORS).countFilesLines());
    }

    @Test
    public void shouldCountAllFilesLinesWithinAGivenDirectoryAWithAnExecutorService() throws ExecutionException, InterruptedException {
        System.out.println(executorServiceCountFileLines());
    }

    @Test
    public void shouldCountAllFilesLinesWithinAGivenDirectoryAAsyncFileRw() throws ExecutionException, InterruptedException {
        //AsyncFiles.readAllBytes().
    }

    private static Stream<Path> getFileStream() throws IOException {
        return Files.walk(basePath, 5).filter(ParalellTests::fileMatched).limit(30000);
    }


    private long executorServiceCountFileLines() throws ExecutionException, InterruptedException {
        long[] totalLines = {0L};
        int numberOfTasks = NUMBER_OF_TASKS*2;

        ExecutionBenchmark.measure(() -> {
            final ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_PROCESSORS);
            long numFilesPerTask = numFiles / numberOfTasks;

            List<Path> filesCache = files.collect(toList());

            final Stream<Future<Long>> futureStream = IntStream.range(0, numberOfTasks).mapToObj(i ->
                    executorService.submit(() -> countFilesLinesInternal(filesCache.stream().skip(i * numFilesPerTask).limit(numFilesPerTask)))
            );

            totalLines[0] = futureStream.mapToLong(longFuture -> {
                try {
                    return longFuture.get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).sum();

        });

        return totalLines[0];

    }


    private long countFilesLinesInternal(Stream<Path> files) {
        long[] totalLines = {0L};
        ExecutionBenchmark.measure(() -> {
            totalLines[0] = files.mapToLong(this::countLines).sum();
        });

        return totalLines[0];
    }

    private long countLines(Path path) {
        try {
            return Files.lines(path).count();
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }


    class LineCounterExecutor {
        private final List<Path> files;
        private final int numTasks;
        private final long numFiles;
        private final long numFilesPerTask;
        private long[] taskResults;



        public LineCounterExecutor(Stream<Path> stream, int numTasks) {
            this.numTasks = numTasks;
            this.files = stream.collect(toList());
            this.numFiles = files.size();
            this.numFilesPerTask = numFiles / numTasks;
            taskResults = new long[numTasks];
        }

        long countFilesLines() {
                ExecutionBenchmark.measure(() -> IntStream
                        .range(0, numTasks)
                        .mapToObj(this::countLines)
                        .forEach(thread -> {
                            try {
                                thread.join();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }));

            return LongStream.of(taskResults).sum();

        }

        private Thread countLines(int i) {
            Thread t = new Thread(() -> {
                taskResults[i] = countFilesLinesInternal(files.stream().skip(i*numFilesPerTask).limit(numFilesPerTask));
            });
            t.start();
            return t;
        }
    }

    // Compares the glob pattern against the file or directory name.
    static boolean fileMatched(Path file) {
        Path name = file.getFileName();
        return name != null && matcher.matches(name);
    }
}
