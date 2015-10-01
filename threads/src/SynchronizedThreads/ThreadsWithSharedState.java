package SynchronizedThreads;

/**
 * Created by zxm on 9/30/15.
 */
public class ThreadsWithSharedState {
    private int count = 0;

    private synchronized void incrementCount() {
        count++;
    }
    public void incrementCountByTwoThreads() {
        Thread runner1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    //count++;
                    incrementCount();
                }
            }
        });

        Thread runner2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    //count++;
                    incrementCount();
                }
            }
        });

        runner1.start();
        runner2.start();
        try {
            runner1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            runner2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        ThreadsWithSharedState threads = new ThreadsWithSharedState();
        threads.incrementCountByTwoThreads();
        System.out.println(threads.getCount());
    }
}
