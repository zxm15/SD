package ProducerAndConsumerPattern;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by ZXM on 10/2/15.
 */
public class Processor implements Runnable {
    private int id;
    private boolean isProducer;
    private Random rand;
    private ProducerAndConsumerWithConcurrentClasses solution;
    public Processor(int id, boolean isProducer) {
        this.id = id;
        this.isProducer = isProducer;
        rand = new Random();
        solution = new ProducerAndConsumerWithConcurrentClasses();
    }
    public void produce() {
        while (solution.getLatch().getCount() > 0) {
            // while true will not be interrupted by latch await()
            int task = rand.nextInt(100);
            try {
                solution.addTask(task);
                System.out.println("Producer " + id + " adds task " + task + " into the queue");
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void consume() {
        //long count = ProducerAndConsumerWithConcurrentClasses.countDown();
        while (solution.getLatch().getCount() > 0) {
            try {
                Thread.sleep(100);
                //System.out.println("Count is " + solution.getLatch().getCount());
                int task = solution.removeTask();
                System.out.println("Consumer " + id + " finish task " + task);

                //System.out.println("Count is " + ProducerAndConsumerWithConcurrentClasses.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void run() {
        if (isProducer) produce();
        else consume();
    }
}
