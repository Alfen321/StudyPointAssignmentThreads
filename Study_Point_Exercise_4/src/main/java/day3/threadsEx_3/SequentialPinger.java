package day3.threadsEx_3;

/*
 * Code taken from 
 * http://crunchify.com/how-to-get-ping-status-of-any-http-end-point-in-java/
 */
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SequentialPinger {

    public static void main(String args[]) throws InterruptedException {

        String[] hostList = {"http://crunchify.com", "http://yahoo.com",
            "http://www.ebay.com", "http://google.com",
            "http://www.example.co", "https://paypal.com",
            "http://bing.com/", "http://techcrunch.com/",
            "http://mashable.com/", "http://thenextweb.com/",
            "http://wordpress.com/", "http://cphbusiness.dk/",
            "http://example.com/", "http://sjsu.edu/",
            "http://ebay.co.uk/", "http://google.co.uk/",
            "http://www.wikipedia.org/",
            "http://dr.dk", "http://pol.dk", "https://www.google.dk",
            "http://phoronix.com", "http://www.webupd8.org/",
            "https://studypoint-plaul.rhcloud.com/", "http://stackoverflow.com",
            "http://docs.oracle.com", "https://fronter.com",
            "http://imgur.com/", "http://www.imagemagick.org"
        };

        ExecutorService es = Executors.newFixedThreadPool(10);

        List<Callable<String>> tasks = new ArrayList();

        for (String url : hostList) {
            Callable<String> c1 = () -> {
                String result = "Error";
                try {
                    URL siteURL = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) siteURL
                            .openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    int code = connection.getResponseCode();
                    if (code == 200) {
                        result = "Green";
                    }
                    if (code == 301) {
                        result = "Redirect";
                    }
                } catch (Exception e) {
                    result = "->Red<-";
                }
                return String.format("%1$-40" + "s", url) + result;
            };
            tasks.add(c1);
        }

        es.invokeAll(tasks).stream()
                .forEach(future -> {
                    try {
                        System.out.println(future.get());
                    } catch (InterruptedException | ExecutionException ex) {
                        Logger.getLogger(SequentialPinger.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
        es.shutdown();
        es.awaitTermination(10, TimeUnit.SECONDS);

    }
}
