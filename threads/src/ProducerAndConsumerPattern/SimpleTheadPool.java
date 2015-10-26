package ProducerAndConsumerPattern;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZXM on 10/26/15.
 */

class Task implements Runnable{
    private int id;

    public Task(int id) {
        this.id = id;
    }

    public void processTask() {
        System.out.println("Starting task " + id);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Ending task " + id);

    }
    @Override
    public void run() {
        processTask();
    }
}
public class SimpleTheadPool {


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 15; i++) {
            executorService.submit(new Task(i));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All the tasks are completed");
    }
}
