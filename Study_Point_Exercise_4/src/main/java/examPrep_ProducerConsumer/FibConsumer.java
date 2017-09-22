package examPrep_ProducerConsumer;

import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;

public class FibConsumer implements Runnable {

    private ArrayBlockingQueue<Integer> numbersProduced;

    public FibConsumer(ArrayBlockingQueue<Integer> numbersProduced) {
        this.numbersProduced = numbersProduced;
    }

    private int i = 0;

    @Override
    public void run() {
        while (true) {
            for (int j = 0; j < 100; j++) {
                try {
                    i++;
                    System.out.println(i + ": " + numbersProduced.take());
                } catch (InterruptedException | NoSuchElementException ex) {
                    return;
                }
            }
            return;
        }

    }

}
