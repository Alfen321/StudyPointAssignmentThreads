package examPrep_ProducerConsumer;

import static java.lang.Thread.sleep;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Tester {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> s1 = new ArrayBlockingQueue(100);
        ArrayBlockingQueue<Integer> s2 = new ArrayBlockingQueue(20);

        for (int i = 0; i < 100; i++) {
            s1.put((Integer) (int) (Math.random() * 20));
        }
        
        ExecutorService es = Executors.newCachedThreadPool();
        //Create and start four producers (P1-P4 in the exercise-figure)
        System.out.println("init prducers");
        es.execute(new FibProducer(s2, s1));
        es.execute(new FibProducer(s2, s1));
        es.execute(new FibProducer(s2, s1));
        es.execute(new FibProducer(s2, s1));
        
        //Create and start single consumer (C1 in the exercise-figure)
        FibConsumer consumer = new FibConsumer(s2);
        System.out.println("init consumer");
        es.execute(consumer);

        es.shutdown();
        es.awaitTermination(10, TimeUnit.SECONDS);
    }
}
