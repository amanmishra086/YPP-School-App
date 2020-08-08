package my.awesome.yppschoolapp;

public class PaymentReceipt_class {

    String product,quantity,price;

    public PaymentReceipt_class() {
    }

    public PaymentReceipt_class(String product, String quantity, String price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
