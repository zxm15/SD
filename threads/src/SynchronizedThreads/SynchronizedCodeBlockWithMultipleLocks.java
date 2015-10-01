package SynchronizedThreads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zxm on 9/30/15.
 */
public class SynchronizedCodeBlockWithMultipleLocks {
    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();
    private Random rand = new Random();
    private Object lock1 = new Object();
    private Object lock2 = new Object();
//    private synchronized void addNumberToFirstList() {
//        int n = rand.nextInt(100);
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        list1.add(n);
//    }
//
//    private synchronized void addNumberToSecondList() {
//        int n = rand.nextInt(100);
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        list2.add(n);
//    }

    private void addNumberToFirstList() {
        synchronized (lock1) {
            int n = rand.nextInt(100);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(n);
        }

    }

    private void addNumberToSecondList() {
        synchronized (lock2) {
            int n = rand.nextInt(100);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(n);
        }
    }


    public void process() {
        for (int i = 0; i < 100; i++) {
            addNumberToFirstList();
            addNumberToSecondList();
        }
    }

    public static void main(String[] args) {
        SynchronizedCodeBlockWithMultipleLocks solution = new SynchronizedCodeBlockWithMultipleLocks();
        Thread runner1 = new Thread(new Runnable() {
            @Override
            public void run() {
                solution.process();
            }
        });

        Thread runner2 = new Thread(new Runnable() {
            @Override
            public void run() {
                solution.process();
            }
        });
        long start = System.currentTimeMillis();
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
        System.out.println("First list size: " + solution.list1.size() + " Second List size: " + solution.list2.size());

        long end = System.currentTimeMillis();
        System.out.println("Time consumed: " + (end - start));
    }
}
