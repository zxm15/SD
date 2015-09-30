package GenerateSimpleThreads;

/**
 * Created by ZXM on 9/30/15.
 */
public class MyThread extends Thread{
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
        MyThread runner1 = new MyThread();
        runner1.start();
        MyThread runner2 = new MyThread();
        runner2.start();

    }

}
