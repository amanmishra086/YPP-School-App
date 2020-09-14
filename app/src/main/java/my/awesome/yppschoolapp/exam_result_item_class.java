package my.awesome.yppschoolapp;

public class exam_result_item_class {

    String subject,perodic_text,notebook,subject_enrichment,annual_examination,marks_obtained,grade;

    public exam_result_item_class(String subject, String perodic_text, String notebook, String subject_enrichment, String annual_examination, String marks_obtained, String grade) {
        this.subject = subject;
        this.perodic_text = perodic_text;
        this.notebook = notebook;
        this.subject_enrichment = subject_enrichment;
        this.annual_examination = annual_examination;
        this.marks_obtained = marks_obtained;
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPerodic_text() {
        return perodic_text;
    }

    public void setPerodic_text(String perodic_text) {
        this.perodic_text = perodic_text;
    }

    public String getNotebook() {
        return notebook;
    }

    public void setNotebook(String notebook) {
        this.notebook = notebook;
    }

    public String getSubject_enrichment() {
        return subject_enrichment;
    }

    public void setSubject_enrichment(String subject_enrichment) {
        this.subject_enrichment = subject_enrichment;
    }

    public String getAnnual_examination() {
        return annual_examination;
    }

    public void setAnnual_examination(String annual_examination) {
        this.annual_examination = annual_examination;
    }

    public String getMarks_obtained() {
        return marks_obtained;
    }

    public void setMarks_obtained(String marks_obtained) {
        this.marks_obtained = marks_obtained;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
