package packt.java9.by.example.ch03.quick;

import junit.framework.AssertionFailedError;
import org.junit.Before;
import org.junit.Test;
import packt.java9.by.example.ch03.qsort.FJQuickSort;
import packt.java9.by.example.ch03.qsort.NonRecursiveQuickSort;
import packt.java9.by.example.ch03.qsort.Qsort;
import packt.java9.by.example.ch03.support.ArraySwapper;
import packt.java9.by.example.ch03.support.ArrayWrapper;

import java.util.Random;

public class SimpleSpeedTest {
    final static int N = 10_000_000;
    final Double[] testData = new Double[N];

    @Before
    public void createRandomData() {
        Random rnd = new Random();
        for (int i = 0; i < N; i++) {
            testData[i] = rnd.nextDouble();
        }
    }

    private void assertSorted(){
        for (int i = 1; i < N; i++) {
            if( testData[i-1] > testData[i])throw new AssertionFailedError();
        }
    }

    @Test
    public void measureTimeWithRecursiveQsort() {
        long start = System.nanoTime();
        Qsort<Double> qsort = new Qsort<>(Double::compareTo, new ArraySwapper<Double>(testData));
        qsort.qsort(new ArrayWrapper<>(testData), 0, N - 1);
        long runTime = System.nanoTime() - start;
        System.out.println("qs run time " + runTime / 1000000);
        assertSorted();
    }

    @Test
    public void measureTimeWithNonRecursiveQsort() {
        long start = System.nanoTime();
        NonRecursiveQuickSort<Double> qsort = new NonRecursiveQuickSort<>(Double::compareTo, new ArraySwapper<Double>(testData));
        qsort.qsort(new ArrayWrapper<>(testData), 0, N - 1);
        long runTime = System.nanoTime() - start;
        System.out.println("nr run time " + runTime / 1000000);
        assertSorted();
    }

    @Test
    public void measureTimeWithForkJoinQsort() {
        long start = System.nanoTime();
        FJQuickSort<Double> qsort = new FJQuickSort<>(Double::compareTo, new ArraySwapper<Double>(testData));
        qsort.qsort(new ArrayWrapper<>(testData), 0, N - 1);
        long runTime = System.nanoTime() - start;
        System.out.println("fj run time " + runTime / 1000000);
        assertSorted();
    }
}
