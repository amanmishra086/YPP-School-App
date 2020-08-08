package my.awesome.yppschoolapp;

public class exam_result_Class {

    String term,grade,remarks,class_name;

    public exam_result_Class() {
    }

    public exam_result_Class(String term, String grade, String remarks, String class_name) {
        this.term = term;
        this.grade = grade;
        this.remarks = remarks;
        this.class_name = class_name;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }
}
