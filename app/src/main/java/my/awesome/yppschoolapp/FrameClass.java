package my.awesome.yppschoolapp;

public class FrameClass {

    String fee,fine,status;
    String month;
    public FrameClass() {
    }



    public FrameClass(String month, String fee, String fine, String status) {
        this.month = month;
        this.fee = fee;
        this.fine = fine;
        this.status = status;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
