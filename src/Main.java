import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final int size = 10000000;
    private static final int h = size / 2;
    private static float[] arr = new float[size];
    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    // Intermediate arrays
    private static float[] a1 = new float[h];
    private static float[] a2 = new float[h];
    private static float[][] tasks;

    public static void main(String[] args) {

        singleThread();
        //doubleThread();
        multiThread(10);

    }

    private static void singleThread() {

        Arrays.fill(arr, 1);

        long a = System.currentTimeMillis();

        for(int i = 0; i < arr.length; i++) {

            arr[i] = (float)(arr[i] * Math.sin(0.2f + (float) i / 5) * Math.cos(0.2f + (float) i / 5) * Math.cos(0.4f + (float) i / 2));

        }

        System.out.println("Один поток (общее время): " + (System.currentTimeMillis() - a));

    }

    private static void doubleThread() {

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

                a1[i] = (float)(a1[i] * Math.sin(0.2f + (float) i / 5) * Math.cos(0.2f + (float) i / 5) * Math.cos(0.4f + (float) i / 2));

            }

            System.out.println("Два потока (первый поток): " + (System.currentTimeMillis() - d));
        };

        // Thread 2
        long e = System.currentTimeMillis();
        Runnable thread2 = () -> {

            for(int i = 0; i < a2.length; i++) {

                a2[i] = (float)(a2[i] * Math.sin(0.2f + (float) i / 5) * Math.cos(0.2f + (float) i / 5) * Math.cos(0.4f + (float) i / 2));

            }

            System.out.println("Два потока (первый поток): " + (System.currentTimeMillis() - e));
        };

        executor.execute(thread1);
        executor.execute(thread2);

        awaitTerminationAfterShutdown(executor, a);

    }

    private static void multiThread(int threadNumber) {

        System.out.println("\n" + threadNumber + "-поточное выполнение\n");

        ExecutorService multiExecutor = Executors.newFixedThreadPool(threadNumber);

        long a = System.currentTimeMillis();

        // Split the task for the separate arrays
        int chunkSize = size / threadNumber;

        try {

            if(size % threadNumber != 0) throw new Exception("Количество потоков должно делить " + size + " без остатка!");
            else {

                long b = System.currentTimeMillis();

                tasks = new float[threadNumber][chunkSize];

                for (int u = 0; u < threadNumber; u++) {

                    System.arraycopy(arr, (u * chunkSize), tasks[u], 0, chunkSize);

                }

                System.out.println("Разделение массива на " + tasks.length + " частей за: " + (System.currentTimeMillis() - b));

                for(int i = 0; i < threadNumber; i++) {

                    multiExecutor.execute(createRunnable(i));

                }

                awaitTerminationAfterShutdownMulti(multiExecutor, a);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    private static Runnable createRunnable(final int i){

        Runnable aRunnable = new Runnable() {

            public void run(){

                long e = System.currentTimeMillis();

                for(int j = 0; j < tasks[i].length; j++) {

                    tasks[i][j] = (float)(tasks[i][j] * Math.sin(0.2f + (float) j / 5) * Math.cos(0.2f + (float) j / 5) * Math.cos(0.4f + (float) j / 2));

                }

                System.out.println("Поток № "+ i + " отработал за: " + (System.currentTimeMillis() - e));

            }
        };

        return aRunnable;

    }

    private static void awaitTerminationAfterShutdown(ExecutorService threadPool, long a) {
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

    private static void awaitTerminationAfterShutdownMulti(ExecutorService threadPool, long a) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            } else {

                // Array combine
                long c = System.currentTimeMillis();

                System.arraycopy(a2, 0, arr, h, h);

                for (int f = 0; f < tasks.length; f++) {

                    System.arraycopy(tasks[f], 0, arr, (f * tasks[f].length), tasks[f].length);

                }

                System.out.println("Обратное объединение массива: " + (System.currentTimeMillis() - c));

                System.out.println(tasks.length + " потоков отработали за (общее время): " + (System.currentTimeMillis() - a));

            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}


