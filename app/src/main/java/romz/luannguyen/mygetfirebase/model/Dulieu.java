package romz.luannguyen.mygetfirebase.model;

/**
 * Created by Admin on 10/27/2016.
 */
public class Dulieu {
    private String content;
    private String title;
    private String url;

    public Dulieu(String content, String title, String url) {
        this.content = content;
        this.title = title;
        this.url = url;
    }

    public Dulieu() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
