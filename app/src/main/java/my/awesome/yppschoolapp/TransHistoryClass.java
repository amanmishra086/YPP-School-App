package my.awesome.yppschoolapp;

public class TransHistoryClass {

    String receipt_no,receipt_date,total_amount,receipt_status,payment_mode,stu_name,stu_class,stu_session;

    public TransHistoryClass() {
    }

    public TransHistoryClass(String receipt_no, String receipt_date, String total_amount, String receipt_status, String payment_mode, String stu_name, String stu_class, String stu_session) {
        this.receipt_no = receipt_no;
        this.receipt_date = receipt_date;
        this.total_amount = total_amount;
        this.receipt_status = receipt_status;
        this.payment_mode = payment_mode;
        this.stu_name = stu_name;
        this.stu_class = stu_class;
        this.stu_session = stu_session;
    }

    public String getReceipt_no() {
        return receipt_no;
    }

    public void setReceipt_no(String receipt_no) {
        this.receipt_no = receipt_no;
    }

    public String getReceipt_date() {
        return receipt_date;
    }

    public void setReceipt_date(String receipt_date) {
        this.receipt_date = receipt_date;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getReceipt_status() {
        return receipt_status;
    }

    public void setReceipt_status(String receipt_status) {
        this.receipt_status = receipt_status;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_class() {
        return stu_class;
    }

    public void setStu_class(String stu_class) {
        this.stu_class = stu_class;
    }

    public String getStu_session() {
        return stu_session;
    }

    public void setStu_session(String stu_session) {
        this.stu_session = stu_session;
    }
}
