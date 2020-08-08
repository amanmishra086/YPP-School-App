package my.awesome.yppschoolapp;

public class classtask_class {
    String date,subject,task_desc,teacher;

    public classtask_class() {
    }

    public classtask_class(String date, String subject, String task_desc, String teacher) {
        this.date = date;
        this.subject = subject;
        this.task_desc = task_desc;
        this.teacher = teacher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTask_desc() {
        return task_desc;
    }

    public void setTask_desc(String task_desc) {
        this.task_desc = task_desc;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
