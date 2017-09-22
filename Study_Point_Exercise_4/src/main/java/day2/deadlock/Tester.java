package day2.deadlock;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Tester {

    public static void main(String[] args) {
        ResourceContainer resources = new ResourceContainer();
        ResourceUser1 t1 = new ResourceUser1(resources);
        ResourceUser2 t2 = new ResourceUser2(resources);
        DeadLockDetector dl = new DeadLockDetector();

        try {
            dl.start();
            t1.start();
            t2.start();

            t1.join();
            t2.join();
            
            System.out.println("Done");
            System.out.println("Words produced: " + resources.getResourceWords().size());
            System.out.println("Numbers produced: " + resources.getResourceNumbers().size());
        } catch (InterruptedException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dl.exit();
            // or GHETTO fix 
            //System.exit(0);
        }
    }
}

/*
Questions
a       The program runs forever
c       The user1 locks words and wait for numbers to be unlocked, while user2 locks numbers and wait for words to be unlocked
b       done
c       done
        made the users more functional orientated so it finishes one task before starting another
        can be fixed with join between but then the threads don't run at the same time
 */
