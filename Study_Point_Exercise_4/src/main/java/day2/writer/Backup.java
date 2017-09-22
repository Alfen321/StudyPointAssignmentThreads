package day2.writer;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;

public class Backup extends Thread {

    private volatile List<String> lines = new ArrayList();
    private volatile boolean stop = false;
    @Override
    public void run() {
        while (!stop) {
            String userDir = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
            try {
                System.out.println("Saving");
                System.out.println(userDir);
                FileWriter writer = new FileWriter(userDir + "/backup.txt", false);
                PrintWriter out = new PrintWriter(writer);
                out.println(lines.toString());
                out.close();
                //writer.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    Thread.sleep(15000);
                } catch (Exception e) {
                }
            }
        }
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
}
