package ProducerAndConsumerPattern;

import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZXM on 10/2/15.
 */
public class ProducerAndConsumerMain {
    public static void main(String[] args) {
        ProducerAndConsumerWithConcurrentClasses solution = new ProducerAndConsumerWithConcurrentClasses();
        try {
            solution.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The process ended");

    }
}
