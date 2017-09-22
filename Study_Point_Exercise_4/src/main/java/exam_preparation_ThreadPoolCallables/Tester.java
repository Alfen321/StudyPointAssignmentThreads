package exam_preparation_ThreadPoolCallables;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Tester {

    static String[] hostList = {"http://165.227.151.92:8080/CA1/", "http://www.alfen.me/CA1"};
    static volatile List<Group> groupsInformation = new ArrayList();
    volatile static boolean runningCache = true;
    static Gson gson = new Gson();

    public static void main(String[] args) throws InterruptedException {

        //A very pretty caching method....
        Thread t1 = new Thread(() -> {
            while (runningCache) {
                try {
                    getInformation();
                    Thread.sleep(3600000);
                } catch (InterruptedException ex) {
                }
            }
        });

        t1.start();
    }

    private static void getInformation() throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);

        List<Callable<String>> tasks = new ArrayList();

        for (String url : hostList) {
            Callable<String> c1 = () -> {
                Group groupInfo;
                Document doc;
                String authorString = "";
                String schoolClassString = "";
                String groupString = "";

                try {

                    doc = Jsoup.connect(url).get();

                    Elements author = doc.select("#authors");
                    authorString = author.text();

                    Elements schoolClass = doc.select("#class");
                    schoolClassString = schoolClass.text();

                    Elements group = doc.select("#group");
                    groupString = group.text();

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                groupInfo = new Group(url, authorString, schoolClassString, groupString);
                groupsInformation.add(groupInfo);
                return gson.toJson(groupInfo, Group.class);
            };
            tasks.add(c1);
        }

        es.invokeAll(tasks).stream()
                .forEach(future -> {
                    try {
                        Group groupInfo2 = gson.fromJson(future.get(), Group.class);
                        System.out.println("");
                        System.out.println("URL: " + groupInfo2.getURL());
                        System.out.println("Author: " + groupInfo2.getAuthor());
                        System.out.println("Group: " + groupInfo2.getGroup());
                        System.out.println("Class: " + groupInfo2.getSchoolclass());
                    } catch (InterruptedException | ExecutionException ex) {

                    }
                });
        es.shutdown();
        es.awaitTermination(10, TimeUnit.SECONDS);
    }
}

//Questions!
//Explain the benefits from using a Thread Pool 
// - You dont have to manage the shutdown and creation of the threadpool yourself. It does it for you
//Explains ways to handle "returned values" from Threads
// - What I did was using the future method that has a get method, when using future it waits on the thread to finish whatever its doing before doing what its supposed to.
//Explain the use of the Callable interface and how to use it.
// - Its a task that returns a value (or a exception) when a task in a thread.
