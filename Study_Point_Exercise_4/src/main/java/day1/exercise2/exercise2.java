package day1.exercise2;

public class exercise2 {
    
    public static void main(String[] args) {
     
        Even even = new Even();
        
        Thread t1 = new Thread(() ->{
            System.out.println(even.next());
        });
        Thread t2 = new Thread(() ->{
            System.out.println(even.next());
        });
        
        t1.start();
        t2.start();
        
    }
    
}

/*
Questions
a       it doesn't always print in the correct order
b       happens 50/50
c       done
d       ?


*/
