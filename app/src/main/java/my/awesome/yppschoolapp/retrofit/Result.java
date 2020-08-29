package my.awesome.yppschoolapp.retrofit;

public class Result {

    private String id,name,dob,gender,email,lastname,presentadd,permanentadd,class_name,section,aadhar,session,fathersname,mothersname,phone;

    public Result(String id, String name, String dob, String gender, String email, String lastname, String presentadd, String permanentadd, String class_name, String section, String aadhar, String session, String fathersname, String mothersname, String phone) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.lastname = lastname;
        this.presentadd = presentadd;
        this.permanentadd = permanentadd;
        this.class_name = class_name;
        this.section = section;
        this.aadhar = aadhar;
        this.session = session;
        this.fathersname = fathersname;
        this.mothersname = mothersname;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPresentadd() {
        return presentadd;
    }

    public void setPresentadd(String presentadd) {
        this.presentadd = presentadd;
    }

    public String getPermanentadd() {
        return permanentadd;
    }

    public void setPermanentadd(String permanentadd) {
        this.permanentadd = permanentadd;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getFathersname() {
        return fathersname;
    }

    public void setFathersname(String fathersname) {
        this.fathersname = fathersname;
    }

    public String getMothersname() {
        return mothersname;
    }

    public void setMothersname(String mothersname) {
        this.mothersname = mothersname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
