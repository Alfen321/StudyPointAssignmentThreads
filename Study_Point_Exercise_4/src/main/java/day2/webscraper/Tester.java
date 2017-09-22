package day2.webscraper;

public class Tester {

    public static void main(String[] args) throws InterruptedException {
        TagCounter tc1 = new TagCounter("http://www.fck.dk");
        TagCounter tc2 = new TagCounter("http://www.google.com");
        TagCounter tc3 = new TagCounter("http://politiken.dk/");

        System.out.println("Avilable Processors: " + Runtime.getRuntime().availableProcessors());

        long start = System.nanoTime();
        tc1.start();
        tc2.start();
        tc3.start();

        tc1.join();
        tc2.join();
        tc3.join();
        long end = System.nanoTime();
        System.out.println("Time Sequential: " + (end - start));

        System.out.println("Title: " + tc1.getTitle());
        System.out.println("Div's: " + tc1.getDivCount());
        System.out.println("Body's: " + tc1.getBodyCount());

        System.out.println("Title: " + tc2.getTitle());
        System.out.println("Div's: " + tc2.getDivCount());
        System.out.println("Body's: " + tc2.getBodyCount());

        System.out.println("Title: " + tc3.getTitle());
        System.out.println("Div's: " + tc3.getDivCount());
        System.out.println("Body's: " + tc3.getBodyCount());
    }
    
    // Threads does it a little bit faster since it runs the count at the same time
}
