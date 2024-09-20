import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {
    private static String filename = "../inventory.txt";
    private Scanner input = new Scanner(System.in); // For user input

    public Inventory() {
    }

    // Function to add a product
    public void addProduct(String prodName, int quantity, double price) {
        Product prod = new Product(prodName, quantity, price);
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(prod.toString() + "\n"); // Ensure a newline for each product
            System.out.println("----------------------------------");
            System.out.println("The product added successfully!");
            System.out.println("----------------------------------");
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    // Function to restock a product
    public void restockProduct(int prodID) {
        List<String> al = new ArrayList<>(); // Temporary list to store inventory data
        boolean productFound = false;
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((line = br.readLine()) != null) {
                String[] productData = line.split("\t");
                int currentProdID = Integer.parseInt(productData[0].trim()); // Assume the prodID is stored in the first field

                if (currentProdID == prodID) {
                    int currentQuantity = Integer.parseInt(productData[2].trim());

                    // Ask user for quantity to restock
                    System.out.print("Enter the quantity to restock: ");
                    int restockQuantity = input.nextInt();
                    input.nextLine(); // Consume newline

                    // Update quantity
                    int newQuantity = currentQuantity + restockQuantity;
                    productData[2] = String.valueOf(newQuantity); // Update quantity

                    // Rebuild line with updated quantity
                    String updatedLine = String.join("\t", productData);
                    al.add(updatedLine);
                    productFound = true;

                    System.out.println("Product restocked! New Quantity: " + newQuantity);
                } else {
                    al.add(line); // Add unchanged lines
                }
            }

            if (!productFound) {
                System.out.println("Product with ID " + prodID + " not found!");
            }

        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }

        // Rewrite the file with updated product info
        try (FileWriter fw = new FileWriter(filename)) {
            for (String item : al) {
                fw.write(item + "\n");
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    // Function to delete a product
    public void deleteProduct(int prodID) {
        List<String> al = new ArrayList<>();
        boolean productFound = false;
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((line = br.readLine()) != null) {
                String[] productData = line.split("\t");
                int currentProdID = Integer.parseInt(productData[0].trim());

                if (currentProdID == prodID) {
                    productFound = true; // Skip the line (delete the product)
                    System.out.println("Product with ID " + prodID + " deleted.");
                } else {
                    al.add(line); // Add other lines to the list
                }
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }

        // If product is found, rewrite the file without the deleted product
        if (productFound) {
            try (FileWriter fw = new FileWriter(filename)) {
                for (String item : al) {
                    fw.write(item + "\n");
                }
            } catch (IOException e) {
                System.err.println("IOException: " + e.getMessage());
            }
        } else {
            System.out.println("Product with ID " + prodID + " not found.");
        }
    }

    // Function to edit a product
    public void editProduct(int prodID) {
        List<String> al = new ArrayList<>();
        boolean productFound = false;
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((line = br.readLine()) != null) {
                String[] productData = line.split("\t");
                int currentProdID = Integer.parseInt(productData[0].trim());

                if (currentProdID == prodID) {
                    // Display options to the user for editing
                    System.out.print("Enter the field to edit (name/quantity/price): ");
                    String fieldToEdit = input.nextLine();

                    switch (fieldToEdit.toLowerCase()) {
                        case "name":
                            System.out.print("Enter the new name: ");
                            productData[1] = input.nextLine();
                            break;
                        case "quantity":
                            System.out.print("Enter the new quantity: ");
                            productData[2] = input.nextLine();
                            break;
                        case "price":
                            System.out.print("Enter the new price: ");
                            productData[3] = input.nextLine();
                            break;
                        default:
                            System.out.println("Invalid field.");
                            return;
                    }

                    // Rebuild the product line with the updated info
                    String updatedLine = String.join("\t", productData);
                    al.add(updatedLine);
                    productFound = true;

                    System.out.println("Product updated successfully.");
                } else {
                    al.add(line); // Add unchanged lines
                }
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }

        // Rewrite the file with updated product info
        if (productFound) {
            try (FileWriter fw = new FileWriter(filename)) {
                for (String item : al) {
                    fw.write(item + "\n");
                }
            } catch (IOException e) {
                System.err.println("IOException: " + e.getMessage());
            }
        } else {
            System.out.println("Product with ID " + prodID + " not found.");
        }
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
}
