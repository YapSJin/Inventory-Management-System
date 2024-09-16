import java.io.*;

public class Inventory {
    private static String filename = "../inventory.txt";

    public Inventory() {

    }

    public void addProduct(String prodName, int quantity, double price) {
        Product prod = new Product(prodName, quantity, price);
        try {
            FileWriter fw = new FileWriter(filename, true);
            fw.write(prod.toString());
            fw.close();
            System.out.println("----------------------------------");
            System.out.println("The product added successfully!");
            System.out.println("----------------------------------");
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    public void deleteProduct(int prodID) {

    }

    public void editProduct(int prodID) {

    }

    public void displayAll() {
        System.out.println("\nProdID \tProdName\t Quantity\tPrice");
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            fr.close();
        } catch (Exception e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
}
