import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {
    private static String filename = "../inventory.txt";
    private ArrayList<Product> inventory = new ArrayList<>();

    public Inventory() {
        loadFromFile();
    }

    // Function to add a product
    public void addProduct(String prodName, int quantity, double price) {
        int id = inventory.size()+1;
        Product prod = new Product(id, prodName, quantity, price);
        inventory.add(prod);
        saveToFile(prod);
        System.out.println("Product Added Successfully");
    }

    // Function to restock a product
    public void restockProduct() {
        
    }
    

    // Function to delete a product
    public void deleteProduct() {
        
    }
    

    // Function to edit a product
    public void editProduct() {
        
    }
    

    // Function to display all products
    public void displayAll() {
        System.out.println("\nProdID\tProdName\tQuantity\tPrice");
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] staffData = line.split("\\|");
                int id = Integer.parseInt(staffData[0]);
                String name = staffData[1];
                String ICNum = staffData[2];
                String phone = staffData[3];
                String email = staffData[4];
                String password = staffData[5];

                if (staffData.length == 9) {
                    double bonus = Double.parseDouble(staffData[6]);
                    double fine = Double.parseDouble(staffData[7]);
                    double salary = Double.parseDouble(staffData[8]);

                    Product prod = new Product(id, prodName, quantity, price);
                    Inventory.add(inventory);
                } else if (staffData.length == 7) {
                    int workHours = Integer.parseInt(staffData[6]);

                    ParttimeStaff staff = new ParttimeStaff(id, name, ICNum, phone, email, password, workHours);
                    management.add(staff);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from Inventory.txt");
            e.printStackTrace();
        }
    }

    private void saveToFile(Product product) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(product.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred write writing to Inventory.txt");
            e.printStackTrace();
        }
    }

    private void updateFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for Product product : inventory) {
                writer.write(inventory.toString());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("An error occurred write writing to Inventory.txt");
            e.printStackTrace();
        }
    }
}




