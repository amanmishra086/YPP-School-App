package my.awesome.yppschoolapp;

public class exam_result_Class {

    String term,grade,remarks,class_name,id;
    String session_id,  dob,  parent_name,  promoted_next,  exam_date,  work_education_grade, art_education_grade,  health_education_grade,  discipline_grade,  stu_name;

//    public exam_result_Class(String exam_term, String grade, String teacher_remarks, String class_id, String session_id, String dob, String parent_name, String promoted_next, String exam_date, String work_education_grade, String art_education_grade, String health_education_grade, String discipline_grade, String stu_name) {
//    }


    public exam_result_Class(String term, String grade, String remarks, String class_name, String session_id, String dob, String parent_name, String promoted_next, String exam_date, String work_education_grade, String art_education_grade, String health_education_grade, String discipline_grade, String stu_name,String id) {
        this.id=id;
        this.term = term;
        this.grade = grade;
        this.remarks = remarks;
        this.class_name = class_name;
        this.session_id = session_id;
        this.dob = dob;
        this.parent_name = parent_name;
        this.promoted_next = promoted_next;
        this.exam_date = exam_date;
        this.work_education_grade = work_education_grade;
        this.art_education_grade = art_education_grade;
        this.health_education_grade = health_education_grade;
        this.discipline_grade = discipline_grade;
        this.stu_name = stu_name;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getPromoted_next() {
        return promoted_next;
    }

    public void setPromoted_next(String promoted_next) {
        this.promoted_next = promoted_next;
    }

    public String getExam_date() {
        return exam_date;
    }

    public void setExam_date(String exam_date) {
        this.exam_date = exam_date;
    }

    public String getWork_education_grade() {
        return work_education_grade;
    }

    public void setWork_education_grade(String work_education_grade) {
        this.work_education_grade = work_education_grade;
    }

    public String getArt_education_grade() {
        return art_education_grade;
    }

    public void setArt_education_grade(String art_education_grade) {
        this.art_education_grade = art_education_grade;
    }

    public String getHealth_education_grade() {
        return health_education_grade;
    }

    public void setHealth_education_grade(String health_education_grade) {
        this.health_education_grade = health_education_grade;
    }

    public String getDiscipline_grade() {
        return discipline_grade;
    }

    public void setDiscipline_grade(String discipline_grade) {
        this.discipline_grade = discipline_grade;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }
}
