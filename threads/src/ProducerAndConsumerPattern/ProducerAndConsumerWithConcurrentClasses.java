package ProducerAndConsumerPattern;

import java.util.concurrent.*;

/**
 * Created by ZXM on 10/2/15.
 * We need one shared task queue, it need to be thread-safe. ArrayBlockingQueue
 * some producer thread, some consumer thread
 * we could use ExecutorService to generate producers and consumers,
 *
 */
public class ProducerAndConsumerWithConcurrentClasses {
    private final int taskQueueCapacity = 20;
    private final int numOfProducer = 6;
    private final int numOfConsumer = 6;
    private static BlockingQueue<Integer> taskQueue;
    private ExecutorService producers;
    private ExecutorService consumers;
    private static CountDownLatch latch = new CountDownLatch(100);


    public ProducerAndConsumerWithConcurrentClasses() {
        taskQueue = new ArrayBlockingQueue<>(taskQueueCapacity);
        producers = Executors.newFixedThreadPool(numOfProducer);
        consumers = Executors.newFixedThreadPool(numOfConsumer);
    }

    public void start() throws InterruptedException {

        for (int i = 0; i < numOfProducer; i++) {
            producers.submit(new Processor(i, true));
        }
        for (int i = 0; i < numOfConsumer; i++) {
            consumers.submit(new Processor(i, false));
        }
        latch.await();
        System.out.println("All the tasks have been finished");
        producers.shutdown();
        consumers.shutdown();
        System.out.println("Threads shut down");
        producers.awaitTermination(10, TimeUnit.SECONDS);
        consumers.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Threads terminated");
    }

    public void addTask(int task) throws InterruptedException {
        //latch.countDown();
        taskQueue.put(task);
    }

    public int removeTask() throws InterruptedException {
        latch.countDown();
        int task = taskQueue.take();
        return task;
    }

    public static CountDownLatch getLatch() {
        return latch;
    }

    public int getTaskQueueCapacity() {
        return taskQueueCapacity;
    }

    public int getNumOfProducer() {
        return numOfProducer;
    }

    public int getNumOfConsumer() {
        return numOfConsumer;
    }

    public static BlockingQueue<Integer> getTaskQueue() {
        return taskQueue;
    }

    public ExecutorService getProducers() {
        return producers;
    }

    public ExecutorService getConsumers() {
        return consumers;
    }




}
