package packt.java9.by.example.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadIntermingling {
    static class MyThread implements Runnable {
        private final String name;

        MyThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 1; i < 10; i++) {
                System.out.print(name + " " + i + ", ");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newFixedThreadPool(2);
        Runnable t1 = new MyThread("t1");
        Runnable t2 = new MyThread("t2");
        Future<? extends Object> f1 = es.submit(t1);
        Future<?> f2 = es.submit(t2);
        System.out.print("started ");
        Object o = f1.get();
        System.out.println("object returned "+o);
        f2.get();
        System.out.println();
        f1 = es.submit(t1);
        es.shutdown();
    }
}
