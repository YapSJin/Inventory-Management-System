/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
import java.util.ArrayList;
import java.util.Date;

public class Receipt {

    private static int counter = 0;
    private static ArrayList<Receipt> receipts = new ArrayList<Receipt>();
    private int id;
    private Member customer;
    private Date date;
    private double cost;
    private ArrayList<int[]> basket = new ArrayList<int[]>();
    private int items;

    public Receipt(Customer cust, ArrayList<int[]> bskt) {
        receipts.add(this);
        counter++;
        setId(counter);
        setDate(new Date());
        setBonusCustomer(cust);
        setBasket(bskt);
        calculateCost();

    }

    public void setBonusCustomer(Customer cust) {
        if (cust instanceof Member) {
            customer = (Member) cust;
        } else {
            customer = null;
        }
    }

    public Member getBonusCustomer() {
        return customer;
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
        ArrayList<Product> products = Product.getProducts();
        double price;
        double cost = 0;
        int quantity;
        for (int[] purchase : basket) {
            for (Product product : products) {
                if (product.getId() == purchase[0]) {
                    price = product.getPrice();
                    quantity = purchase[1];
                    cost += price * quantity;
                    break;
                }
            }
        }
        setCost(cost);
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
            String name = Product.getNameById(purchase[0]);
            String price = Double.toString(Product.getPriceById(purchase[0]));
            String quantity = Integer.toString(purchase[1]);
            String cost = Double.toString(Double.parseDouble(price) * purchase[1]);
            System.out.printf("%s RM%s x %s\n", name, price, quantity);
            System.out.printf("                                = RM%s \n", cost);
        }
    }

    @Override
    public String toString() {
        return String.format("Date of issue: %s, Total Cost: RM %f, " + "Bought items: %d", getDate(), getCost(), getItems());
    }

}