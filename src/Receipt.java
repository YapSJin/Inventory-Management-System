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

    public Receipt(ArrayList<int[]> bskt) {
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

    public int getItems() {
        return items;
    }

    public void calculateCost() {
        ArrayList<Product> products = Product.getProducts();
        double totalCost = 0;
        items = 0;

        for (int[] purchase : basket) {
            for (Product product : products) {
                if (product.getId().equals(String.valueOf(purchase[0]))) {
                    double price = product.getPrice();
                    int quantity = purchase[1];
                    totalCost += price * quantity;
                    items += quantity; // Count total items
                    break;
                }
            }
        }
        setCost(totalCost);
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
        System.out.println("                   <=-=-=>TARUC SuperMarket<=-=-=>");
        System.out.println("=======================================================================");
        System.out.printf("Receipt [%d]\n", getId());
        System.out.println("Date of issue: " + getDate());
        for (int[] purchase : basket) {
            String name = Product.getNameById(purchase[0]);
            double price = Product.getPriceById(purchase[0]);
            int quantity = purchase[1];
            double totalCost = price * quantity;
            System.out.printf("%s RM%.2f x %d\n", name, price, quantity);
            System.out.printf("                                = RM%.2f \n", totalCost);
        }
    }

    @Override
    public String toString() {
        return String.format("Date of issue: %s, Total Cost: RM %.2f, Items Bought: %d", 
            getDate(), getCost(), getItems());
    }
}
