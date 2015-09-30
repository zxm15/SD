package GenerateSimpleThreads;

/**
 * Created by ZXM on 9/30/15.
 */
public class GenerateSimpleThreadsMain {

    public static void main(String[] args) {
        Thread runner1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("Hello " + i);
                    try {
                        Thread.sleep(100);
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        Thread runner2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("Hello " + i);
                    try {
                        Thread.sleep(100);
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        runner1.start();
        runner2.start();
    }
}
