package core.basesyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        List<Future<String>> futures = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            Callable<String> callable = new MyThread();
            Future<String> future = executorService.submit(callable);
            futures.add(future);
        }

        try {
            for (Future<String> future : futures) {
                String value = future.get();
                logger.info(value);
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);

        }
        executorService.shutdown();
    }
}
