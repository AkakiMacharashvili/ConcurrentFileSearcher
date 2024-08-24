import java.nio.file.*;
import java.util.Scanner;
import java.util.concurrent.*;

public class FileSearcher {
    static ExecutorService service = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter path of root directory...");
        String rootDir = scanner.nextLine();
        System.out.println("Enter keyword...");
        String keyword = scanner.nextLine();

        ThreadPoolManager threadPoolManager = new ThreadPoolManager(Paths.get(rootDir), keyword);
        threadPoolManager.start();

        ResultAggregator resultAggregator = ResultAggregator.getInstance();
        for (var v : resultAggregator.getStorage()) {
            System.out.println(v.toString());
        }
    }


}
