package my.awesome.yppschoolapp;

public class cca_result_class {

    String class_name, cca_name, cca_pos,date;

    public cca_result_class() {
    }

    public cca_result_class(String class_name, String cca_name, String cca_pos, String date) {
        this.class_name = class_name;
        this.cca_name = cca_name;
        this.cca_pos = cca_pos;
        this.date = date;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getCca_name() {
        return cca_name;
    }

    public void setCca_name(String cca_name) {
        this.cca_name = cca_name;
    }

    public String getCca_pos() {
        return cca_pos;
    }

    public void setCca_pos(String cca_pos) {
        this.cca_pos = cca_pos;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
