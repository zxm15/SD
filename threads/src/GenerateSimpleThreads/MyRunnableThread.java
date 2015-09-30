package GenerateSimpleThreads;

/**
 * Created by ZXM on 9/30/15.
 */
public class MyRunnableThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello" + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread runner1 = new Thread(new MyRunnableThread());
        runner1.start();
        Thread runner2 = new Thread(new MyRunnableThread());
        runner2.start();

    }
}
