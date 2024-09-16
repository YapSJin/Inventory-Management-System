public class Product {
    private int id = 0;
    private String name;
    private int quantity;
    private double price;

    public Product(String name, int quantity, double price) {
        id++;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String setName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return String.format("%d\t\t  %s\t\t  %d\t\t  RM%.2f", id, name, quantity, price);
    }
}
