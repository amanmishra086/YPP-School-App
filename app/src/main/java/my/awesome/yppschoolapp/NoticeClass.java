package my.awesome.yppschoolapp;

public class NoticeClass {

    String title,description,no;

    public NoticeClass() {
    }

    public NoticeClass(String title, String description, String no) {
        this.title = title;
        this.description = description;
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String expirydate) {
        this.no = expirydate;
    }
}
