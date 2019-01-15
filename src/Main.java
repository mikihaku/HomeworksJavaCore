import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr = new float[size];
    static ExecutorService executor = Executors.newFixedThreadPool(2);

    // Intermediate arrays
    static float[] a1 = new float[h];
    static float[] a2 = new float[h];

    public static void main(String[] args) throws Exception {

        singleThread();
        doubleThread();

    }

    public static void singleThread() {

        Arrays.fill(arr, 1);

        long a = System.currentTimeMillis();

        for(int i = 0; i < arr.length; i++) {

            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

        }

        System.out.println("Один поток (общее время): " + (System.currentTimeMillis() - a));

    }

    public static void doubleThread() throws Exception {

        Arrays.fill(arr, 1);

        long a = System.currentTimeMillis();

        // Array split
        long b = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        System.out.println("Два потока (разделение массива): " + (System.currentTimeMillis() - b));

        // Computations
        // Thread 1
        long d = System.currentTimeMillis();
        Runnable thread1 = () -> {

            for(int i = 0; i < a1.length; i++) {

                a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

            }

            System.out.println("Два потока (первый поток): " + (System.currentTimeMillis() - d));
        };

        // Thread 2
        long e = System.currentTimeMillis();
        Runnable thread2 = () -> {

            for(int i = 0; i < a2.length; i++) {

                a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

            }

            System.out.println("Два потока (первый поток): " + (System.currentTimeMillis() - e));
        };

        executor.execute(thread1);
        executor.execute(thread2);

        awaitTerminationAfterShutdown(executor, a);

    }

    static void awaitTerminationAfterShutdown(ExecutorService threadPool, long a) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            } else {

                // Array combine
                long c = System.currentTimeMillis();
                System.arraycopy(a1, 0, arr, 0, h);
                System.arraycopy(a2, 0, arr, h, h);
                System.out.println("Два потока (объединение массива): " + (System.currentTimeMillis() - c));

                System.out.println("Два потока (общее время): " + (System.currentTimeMillis() - a));

            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}


