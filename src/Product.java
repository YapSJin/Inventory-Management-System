public class Product {
    private int id = 0;
    private String name;
    public int quantity;
    public double retailPrice;
    public double wholesalePrice;

    public Product(int id, String name, int quantity, double retailPrice, double wholesalePrice) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.retailPrice = retailPrice;
        this.wholesalePrice = wholesalePrice;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
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

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(double wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public String toString(boolean format) {
        if (format) {
            return String.format("%-3d %-20s %-10d RM%-8.2f RM%.2f", this.id, this.name, this.quantity, this.retailPrice, this.wholesalePrice);
        } else {
            return String.format("%d|%s|%d|%.2f|%.2f", this.id, this.name, this.quantity, this.retailPrice, this.wholesalePrice);
        }
    }

    public String toString(String action, int quantity, double total) {
        return toString(false) + String.format("|%s|%d|%.2f", action, quantity, total);
    }
}
