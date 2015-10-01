package MultipleThreadsDoTasksTogether;

import java.util.concurrent.*;

/**
 * Created by ZXM on 10/1/15.
 * Let us mimic a situation, say we have 3 threads,
 * and 15 services.
 * When we start up a system, these 15 services must
 * be prepared ready to ensure the system works.
 * How to simulate these process?
 */
public class TwoThreadsConsumeFixTaskQueue {
    private final int numOfThreads = 3;
    private final int numOfServices = 15;
    //private ExecutorService executors = Executors.newFixedThreadPool(numOfThreads);
    private Thread[] threads = new Thread[numOfThreads];
    private BlockingQueue<Integer> tasks = new ArrayBlockingQueue<>(numOfServices);
    private CountDownLatch latch = new CountDownLatch(numOfServices);

    public TwoThreadsConsumeFixTaskQueue() {
        for (int i = 0; i < numOfThreads; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    consumeTask();

                }
            });
        }
        for (int i = 0; i < numOfServices; i++) {
            tasks.offer(i);
        }
    }

    private void consumeTask() {
    // we do not need synchronized keyword here because
        //1. we are using thread safe data set
        while (! tasks.isEmpty()) {
            try {
                Thread.sleep(1000);
                int task = tasks.poll();
                System.out.println("start the service" + task);
                latch.countDown();
                System.out.println("run up service " + task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getNumOfServices() {
        return numOfServices;
    }

    public int getNumOfThreads() {
        return numOfThreads;
    }

    public Thread[] getThreads() {
        return threads;
    }

    public BlockingQueue<Integer> getTasks() {
        return tasks;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public static void main(String[] args) {
        TwoThreadsConsumeFixTaskQueue solution = new TwoThreadsConsumeFixTaskQueue();
        System.out.println(solution.getTasks().size());
        for (int i = 0; i < solution.getNumOfThreads(); i++) {
            solution.getThreads()[i].start();
        }
        try {
            solution.getLatch().await();
            System.out.println("All services are up, system is running");
            for (int i = 0; i < solution.getNumOfThreads(); i++) {
                solution.getThreads()[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
