package core.basesyntax;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.Callable;

public class MyThread implements Callable<String> {
    private final Random random = new Random();

    @Override
    public String call() throws InterruptedException {
        int duration = random.nextInt(1000);
        Thread.sleep(duration);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSS");
        return "Task duration was "
                + duration + " ms, execution finished at "
                + LocalTime.now().format(formatter);
    }
}
