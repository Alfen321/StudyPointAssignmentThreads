package day1.exercise1;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class exercise1 {

    volatile static boolean stop = false;

    public static void main(String[] args) {
        exercise1();
    }

    public static void exercise1() {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i <= 1e9; i++) {
                System.out.println("T1: " + i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i <= 5; i++) {
                System.out.println("T2: " + i);
                try {
                    sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(exercise1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        Thread t3 = new Thread(() -> {
            int i = 10;
            while (!stop) {
                System.out.println("T3: " + i);
                i++;
                try {
                    sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(exercise1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        //t1.start();

        t2.start();

        t3.start();

        try {
            sleep(10000);
        } catch (Exception e) {
        }
        stop = true;

        //Questions
        //a     not in this case since none of the threads are using shared variables
        //b     The solve to the problem you are hinting we are using a volitile boolean, so that the thread reads from main memory
        //c     This way we arent using a unsafe method (t3.stop()) but instead checking a variable if it should do another pass of the loop
    }

}
