public class Inventory {
    private String idNumber;
    private String productName;
    private int quantity;
    private double price;
    private double restockPrice;
    
    public Inventory(String idNumber, String productName, int quantity, double price, double restockPrice) {
        this.idNumber = idNumber;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.restockPrice = restockPrice;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRestockPrice() {
        return restockPrice;
    }

    public void setRestockPrice(double restockPrice) {
        this.restockPrice = restockPrice;
    }
    
    @Override
    public String toString() {
        return ""+ idNumber +"  "+ productName +"  "+ quantity +"  "+ price + "  "+restockPrice;
    }
}
