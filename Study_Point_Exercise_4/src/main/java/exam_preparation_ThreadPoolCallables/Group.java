package exam_preparation_ThreadPoolCallables;
public class Group {

    private String url;
    private String author;
    private String Schoolclass;
    private String group;

    public Group(String url, String author, String Schoolclass, String group) {
        this.url = url;
        this.author = author;
        this.Schoolclass = Schoolclass;
        this.group = group;
    }

    public String getURL() {
        return url;
    }

    public void setURL(String URL) {
        this.url = URL;
    }
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSchoolclass() {
        return Schoolclass;
    }

    public void setSchoolclass(String Schoolclass) {
        this.Schoolclass = Schoolclass;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
