public class Transaction extends Product {
    private String action;
    private int items;
    private double total;

    public Transaction(int ID, String name, int quantity, double retailPrice, double wholesalePrice, String action, int items, double total) {
        super(ID, name, quantity, retailPrice, wholesalePrice);
        this.action = action;
        this.items = items;
        this.total = total;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getQuantity() {
        return this.items;
    }

    public void setQuantity(int items) {
        this.items = items;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String toString() {
        if (getAction().equals("Restock")) {
            return String.format("%-20s %-10d RM%-10.2f - RM%.2f", super.getName(), items, super.getWholesalePrice(), total);
        } else {
            return String.format("%-20s %-10d RM%-10.2f + RM%.2f", super.getName(), items, super.getRetailPrice(), total);
        }
    }
}
