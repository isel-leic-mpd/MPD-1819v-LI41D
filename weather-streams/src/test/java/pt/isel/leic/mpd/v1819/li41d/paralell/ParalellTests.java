package pt.isel.leic.mpd.v1819.li41d.paralell;

import org.javaync.io.AsyncFiles;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import pt.isel.leic.mpd.v1819.li41d.utils.ExecutionBenchmark;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongConsumer;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ParalellTests {
    final static int NUMBER_OF_PROCESSORS = Runtime.getRuntime().availableProcessors();
    final static int NUMBER_OF_TASKS = NUMBER_OF_PROCESSORS;
    final static Path basePath = Paths.get("/Users/lfalcao/Cloud Drives/OneDrive/OneDrive - Instituto Superior de Engenharia de Lisboa/ISEL/Disciplinas/2018-2019/2018-2019Ver-PI/Repositories/PI-1819v-LI51N/node.js8-the-right-way/data/cache/epub");
    private static long numFiles;
    private static List<Path> files;

    private static final PathMatcher matcher = FileSystems.getDefault()
            .getPathMatcher("glob:*.rdf");
    Stream<Path> filesStream = null;

    @BeforeClass
    public static void beforeClass() throws Exception {
        files = Files.walk(basePath, 5)
                .filter(ParalellTests::fileMatched)
                .limit(8)
                .collect(toList());
        numFiles = files.size();
    }

    @Before
    public void setUp() throws Exception {
        filesStream = files.stream();
    }


    @Test
    public void shouldCountAllFilesLinesWithinAGivenDirectorySequentially() throws IOException {
        System.out.println(countFilesLinesSequentially(filesStream));
    }

    @Test
    public void shouldCountAllFilesLinesWithinAGivenDirectoryWithParallelStream() throws IOException {
        System.out.println(countFilesLinesSequentially(filesStream.parallel()));
    }


    @Test
    public void shouldCountAllFilesLinesWithinAGivenDirectoryWithManualThreadCreation() throws IOException {
        System.out.println(new LineCounterExecutor(filesStream, NUMBER_OF_PROCESSORS).countFilesLines());
    }

    @Test
    public void shouldCountAllFilesLinesWithinAGivenDirectoryWithAnExecutorService() throws ExecutionException, InterruptedException {
        System.out.println(executorServiceCountFileLines());
    }

    @Test
    public void shouldCountAllFilesLinesWithinAGivenDirectoryAsyncFileRw() throws ExecutionException, InterruptedException {
        System.out.println(asyncFileRwCountFilesLines());

    }

    private long asyncFileRwCountFilesLines() {
        Semaphore semaphore = new Semaphore(0);
        long[] numLines = {0};
        ExecutionBenchmark.measure(() -> {
            countFilesLinesInternalAsync(filesStream, l -> {
                numLines[0] = l;
                semaphore.release();
            });
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return numLines[0];
    }


    private long executorServiceCountFileLines() throws ExecutionException, InterruptedException {
        long[] totalLines = {0L};


        ExecutionBenchmark.measure(() -> {
            int numberOfTasks = NUMBER_OF_TASKS;
            final ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_PROCESSORS);
            long numFilesPerTask = numFiles / NUMBER_OF_TASKS;
            long remainingFiles = numFiles % numberOfTasks;
            
            Stream<Future<Long>> futureStream =
                    IntStream.range(0, numberOfTasks).
                            mapToObj(
                                    i -> executorService.submit(
                                            () -> countFilesLinesSequentially(
                                                    getSubstream(i * numFilesPerTask + (i < remainingFiles ? i : remainingFiles),
                                                            (numFilesPerTask + (long)(i < remainingFiles ? 1 : 0)))))
            );


            totalLines[0] = futureStream.mapToLong(longFuture -> {
                try {
                    final Long numFiles = longFuture.get();
                    return numFiles;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).sum();

        });

        return totalLines[0];

    }

    private Stream<Path> getSubstream(long start, long limit) {
        System.out.println("start: " + start + " - limit : " + limit);

        return files.stream().skip(start)
                .limit(limit);
    }


    private long countFilesLinesSequentially(Stream<Path> files) {
        long[] totalLines = {0L};
        ExecutionBenchmark.measure(() -> {
            totalLines[0] = files.mapToLong(this::countLines).sum();
        });

        return totalLines[0];
    }

    private void countFilesLinesInternalAsync(Stream<Path> files, LongConsumer consumer) {
        final AtomicLong numLines = new AtomicLong(0);
        final AtomicLong countFiles = new AtomicLong(0);
        files.forEach(p -> {
            final long processingFiles = countFiles.incrementAndGet();
            System.out.println("processing " + processingFiles);
            countLinesAsync(p, l -> {
                numLines.addAndGet(l);
                final long toProcess = countFiles.decrementAndGet();
                System.out.println("toProcess " + toProcess);
                if (toProcess == 0) {
                    consumer.accept(numLines.longValue());
                }
            });
        });
    }

    private long countLines(Path path) {
        try {
            return Files.lines(path).count();
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }


    private void countLinesAsync(Path path, LongConsumer consumer) {
        AsyncFiles.lines(path.toString()).subscribe(
                new Subscriber<String>() {
                    private long count = 0;

                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(String s) {
                        ++count;
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {
                        consumer.accept(count);
                    }
                }
        );
    }


    class LineCounterExecutor {
        private final List<Path> files;
        private final int numThreads;
        private final long numFiles;
        private final long numFilesPerTask;
        private long[] taskResults;


        public LineCounterExecutor(Stream<Path> stream, int numThreads) {
            this.files = stream.collect(toList());
            this.numFiles = files.size();
            this.numFilesPerTask = numFiles / numThreads;
            this.numThreads = numThreads + numFiles % numThreads != 0 ? 1 : 0;
            taskResults = new long[numThreads];
        }

        long countFilesLines() {
            ExecutionBenchmark.measure(() -> IntStream
                    .range(0, numThreads)
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
                taskResults[i] = countFilesLinesSequentially(files.stream().skip(i * numFilesPerTask).limit(numFilesPerTask));
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
