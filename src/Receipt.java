import java.util.ArrayList;
import java.util.Date;

public class Receipt {
    private static int counter = 0;
    private static ArrayList<Receipt> receipts = new ArrayList<>();
    private int id;
    private Date date;
    private double cost;
    private ArrayList<int[]> basket = new ArrayList<>();
    private int items;
    private Inventory inventory; // Reference to Inventory

    // Constructor now takes Inventory instance as a parameter
    public Receipt(Inventory inventory, ArrayList<int[]> bskt) {
        this.inventory = inventory; // Assign the passed inventory
        receipts.add(this);
        counter++;
        setId(counter);
        setDate(new Date());
        setBasket(bskt);
        calculateCost();
    }

    public void setId(int iden) {
        id = iden;
    }

    public int getId() {
        return id;
    }

    public void setDate(Date dt) {
        date = dt;
    }

    public Date getDate() {
        return date;
    }

    public void setBasket(ArrayList<int[]> bskt) {
        basket = bskt;
    }

    public ArrayList<int[]> getBasket() {
        return basket;
    }

    public void setItems(int it) {
        items = it;
    }

    public int getItems() {
        return items;
    }

    public void calculateCost() {
        double totalCost = 0;
        int totalItems = 0;

        for (int[] purchase : basket) {
            Product product = inventory.getProductById(purchase[0]);
            if (product != null) {
                double price = product.getPrice();
                int quantity = purchase[1];
                totalCost += price * quantity;
                totalItems += quantity;
            }
        }
        setCost(totalCost);
        setItems(totalItems); // Set the total number of items
    }

    public void setCost(double cst) {
        cost = Math.round(cst * 100.0) / 100.0;
    }

    public double getCost() {
        return cost;
    }

    public static ArrayList<Receipt> getReceipts() {
        return receipts;
    }

    public void printReceipt() {
        System.out.println("=======================================================================");
        System.out.println("                   <=-=-=>Welcome To ToyUMT<=-=-=>");
        System.out.println("=======================================================================");
        System.out.printf("Receipt [%d]\n", getId());
        System.out.println("Date of issue: " + getDate());
        for (int[] purchase : basket) {
            Product product = inventory.getProductById(purchase[0]);
            if (product != null) {
                String name = product.getName();
                String price = String.format("%.2f", product.getPrice());
                String quantity = Integer.toString(purchase[1]);
                String cost = String.format("%.2f", product.getPrice() * purchase[1]);
                System.out.printf("%s RM%s x %s\n", name, price, quantity);
                System.out.printf("                                = RM%s \n", cost);
            }
        }
        System.out.printf("Total cost: RM%.2f\n", getCost());
        System.out.printf("Total items: %d\n", getItems());
    }

    @Override
    public String toString() {
        return String.format("Date of issue: %s, Total Cost: RM %.2f, Bought items: %d", getDate(), getCost(), getItems());
    }
}
