import java.util.Date;

public class dailyReport {

    private String productName;
    private String productBarcode;
    private double productPrice;
    private int productQuantity;
    private int productAmount;
    private final Date dateCreated;

    public dailyReport() {

        this.dateCreated = new Date();
    }

    public String getProductName() {
        return productName;
    }

    public String getProductBarcode() {
        return productBarcode;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public int getProductAmount() {
        productAmount = (int) (productQuantity * productPrice);
        return productAmount;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductBarcode(String productBarcode) {
        this.productBarcode = productBarcode;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    @Override
    public String toString() {
        return "Barcode :" + productBarcode + "| Product Name :" + productName + " | Product Price :" + productPrice
                + " | Qauntity :" + productQuantity
                + " | Total Amount :" + productAmount + " | Date of Report" + dateCreated;
    }

}