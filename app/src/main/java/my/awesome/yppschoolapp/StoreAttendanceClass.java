package my.awesome.yppschoolapp;

public class StoreAttendanceClass {

    String date,status,remarks;

    public StoreAttendanceClass() {
    }

    public StoreAttendanceClass(String date, String status, String remarks) {
        this.date = date;
        this.status = status;
        this.remarks = remarks;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
