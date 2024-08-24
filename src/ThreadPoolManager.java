import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {

    static ExecutorService service = Executors.newFixedThreadPool(10);
    Path path;
    String keyword;

    public ThreadPoolManager(Path path, String keyword) {
        this.path = path;
        this.keyword = keyword;
    }

    private static void shutDown() {
        service.shutdown();
        try {
            if (!service.awaitTermination(60, TimeUnit.SECONDS)) {
                service.shutdownNow(); // Force shutdown if tasks are still running
                if (!service.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("ExecutorService did not terminate");
                }
            }
        } catch (InterruptedException e) {
            service.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public  void exploreDirectory() {
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (Files.isRegularFile(file) && !file.toString().endsWith(".java")) {
//                        you can uncomment it if you want to see all the files...
//                        System.out.println("Text File: " + file);
                        FileSearchTask fileSearchTask = new FileSearchTask(List.of(file.toString()), keyword);
                        service.submit(fileSearchTask);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    if (exc != null) {
                        exc.printStackTrace();
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        exploreDirectory();
        shutDown();
    }
}
