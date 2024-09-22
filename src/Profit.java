import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Profit {
    private static double sst = 0.06;
    private static String filename = "../Transaction.txt";
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Profit() {
        readTransaction();
    }

    public void inventoryProfit(ArrayList<Product> inventory) {
        double retail = 0;
        double wholesale = 0;
        try {
            for (Product product : inventory) {
                retail += product.getQuantity() * product.getRetailPrice();
                wholesale += product.getQuantity() * product.getWholesalePrice();
            }

            System.out.println("------------------------------------");
            System.out.printf("Total sales revenue: RM%.2f\n", retail);
            System.out.println("------------------------------------");
            System.out.printf("Total amount to restock: RM%.2f\n", wholesale);
            System.out.println("------------------------------------");
            System.out.printf("Total profit to be made: RM%.2f\n\n", (retail - wholesale));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void salesProfit() {
        double profit = 0;
        double loss = 0;
        double sstTotal = 0;
        System.out.println("\n--------------------------Sales Profit report--------------------------");

        for (Transaction transaction : transactions) {
            System.out.println(transaction.toString());

            sstTotal += transaction.getTotal() * sst;
            if (transaction.getAction().equals("Restock")) {
                loss += transaction.getTotal();
            } else {
                profit += transaction.getTotal();
            }
        }
        try {
            System.out.println("--------------------------------------------");
            System.out.printf("Total amount of sales: RM%.2f\n", profit);
            System.out.println("--------------------------------------------");
            System.out.printf("Total amount of restocks: RM%.2f\n", loss);
            System.out.println("--------------------------------------------");
            System.out.printf("Total amount of sst incurred: RM%.2f\n", sstTotal);
            System.out.println("--------------------------------------------");
            System.out.printf("Total profit earned: RM%.2f\n\n", (profit - loss - sstTotal));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void readTransaction() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] sales = line.split("\\|");

                int id = Integer.parseInt(sales[0]);
                String name = sales[1];
                int quantity = Integer.parseInt(sales[2]);
                double retailPrice = Double.parseDouble(sales[3]);
                double wholesalePrice = Double.parseDouble(sales[4]);
                String action = sales[5];
                int items = Integer.parseInt(sales[6]);
                double total = Double.parseDouble(sales[7]);

                Transaction transaction = new Transaction(id, name, quantity, retailPrice, wholesalePrice, action, items, total);
                transactions.add(transaction);

            }
        } catch (Exception e) {
            System.err.println("An error occurred while reading Transaction.txt");
        }
    }
}
