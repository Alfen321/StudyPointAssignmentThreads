package examPrep_ProducerConsumer;

import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;

public class FibProducer implements Runnable {

    private static volatile boolean stop = false;

    private ArrayBlockingQueue<Integer> numbersProduced;
    private ArrayBlockingQueue<Integer> numbersRequested;

    public FibProducer(ArrayBlockingQueue<Integer> numbersProduced, ArrayBlockingQueue<Integer> numbersRequested) {
        this.numbersProduced = numbersProduced;
        this.numbersRequested = numbersRequested;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                numbersProduced.put(fib(numbersRequested.remove()));
            } catch (InterruptedException | NoSuchElementException ex) {
                System.out.println(this.toString() + " done!");
                return;
            }
        }
    }

    private int fib(int n) {
        if ((n == 0) || (n == 1)) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

}
