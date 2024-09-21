import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
    private static String filename = "../inventory.txt";
    private ArrayList<Product> inventory = new ArrayList<>();

    public Inventory() {
        loadFromFile();
    }

    public void addProduct(Scanner scanner) {
      System.out.print("Enter product name: ");
      String prodName = scanner.nextLine();
    
      System.out.print("Enter quantity: ");
      int quantity = scanner.nextInt();
    
      System.out.print("Enter price of product (xx.xx): RM");
      double price = scanner.nextDouble();
    
      int newID = inventory.isEmpty() ? 1 : inventory.get(inventory.size() - 1).getID() + 1;
    
      Product prod = new Product(newID, prodName, quantity, price);
      inventory.add(prod);
      saveToFile(prod);
    
      System.out.println("-----------------------------");
      System.out.println("Product successfully added!");
      System.out.println("-----------------------------");
    }

    public void restockProduct(Scanner scanner, int prodID) {
      try {
          boolean productFound = false;
          int newQuantity = 0;
  
          BufferedReader reader = new BufferedReader(new FileReader(filename));
          String line;
          while ((line = reader.readLine()) != null) {
              String[] productData = line.split("\\|");
  
              if (productData.length == 4 && Integer.parseInt(productData[0]) == prodID) {
                  productFound = true;
                  System.out.println("Current stock for product ID " + prodID + ": " + productData[2]);
  
                  System.out.print("Enter the quantity to add: ");
                  int addQuantity = Integer.parseInt(scanner.nextLine());
  
                  newQuantity = Integer.parseInt(productData[2]) + addQuantity;
                  break;
              }
          }
          reader.close();
  
          if (productFound) {
              for (Product product : inventory) {
                  if (product.getID() == prodID) {
                      product.setQuantity(newQuantity);
                      break;
                  }
              }
              updateFile();
              System.out.println("-----------------------------");
              System.out.println("Product successfully restocked!");
              System.out.println("-----------------------------");
          } else { 
          System.out.println("Product not found");
          }
      } catch (IOException e) {
          System.out.println("An error occurred while reading/writing to Inventory.txt");
          e.printStackTrace();
      }
  }
      public void deleteProduct(Scanner scanner) {
        System.out.print("\nEnter product ID to delete (or 'cancel' to stop): ");
        String input = scanner.nextLine().trim();
      
        if (!input.equalsIgnoreCase("cancel")) {
          int prodID;
          try {
            prodID = Integer.parseInt(input);
          } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid product ID or 'cancel'.");
            return;
          }
      
          Product toRemove = null;
          for (Product product : inventory) {
            if (product.getID() == prodID) {
              toRemove = product;
              break;
            }
          }
      
          if (toRemove != null) {
            System.out.print("Are you sure you want to delete this product (ID: " + prodID + ")? (Y/N): ");
            char confirm = Character.toLowerCase(scanner.next().charAt(0));
            scanner.nextLine();
      
            if (confirm == 'y') {
              inventory.remove(toRemove);
              updateFile();
      
              System.out.println("-----------------------------");
              System.out.println("Product successfully deleted!");
              System.out.println("-----------------------------");
            } else {
              System.out.println("-----------------------------");
              System.out.println("Product deletion cancelled!");
              System.out.println("-----------------------------");
            }
          } else {
            System.out.println("Product with ID " + prodID + " not found.");
          }
        } else {
          System.out.println("-----------------------------");
          System.out.println("Product deletion cancelled!");
          System.out.println("-----------------------------");
        }
      }

    public void editProduct(Scanner scanner) {
        System.out.print("\nEnter product ID to update (0 to cancel): ");
        int id = Integer.parseInt(scanner.nextLine());
      
        if (id != 0) {
          Product toUpdate = null;
          for (Product product : inventory) {
            if (product.getID() == id) {
              toUpdate = product;
              break;
            }
          }
      
          if (toUpdate != null) {
            boolean finished = false;
            do {
              System.out.print("\nEnter field to update (name/quantity/price)('done' to confirm): ");
              String field = scanner.nextLine().toLowerCase();
      
              switch (field) {
                case "name":
                  System.out.print("Enter new name: ");
                  String name = scanner.nextLine();
                  toUpdate.setName(name);
                  break;
                case "quantity":
                  System.out.print("Enter new quantity: ");
                  int quantity = Integer.parseInt(scanner.nextLine());
                  toUpdate.setQuantity(quantity);
                  break;
                case "price":
                  System.out.print("Enter new price: ");
                  double price = Double.parseDouble(scanner.nextLine());
                  toUpdate.setPrice(price);
                  break;
                case "done":
                  finished = true;
                  break;
                default:
                  System.out.println("Invalid option, please try again.");
              }
            } while (!finished);
      
            updateFile();
      
            System.out.println("-----------------------------");
            System.out.println("Product successfully updated!");
            System.out.println("-----------------------------");
          } else {
            System.out.println("Product with ID " + id + " not found.");
          }
        } else {
          System.out.println("-------------------------");
          System.out.println("Product update cancelled!");
          System.out.println("-------------------------");
        }
      }
    
    public void displayAll() {
        System.out.println("\nProdID|ProdName|Quantity|Price");
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
    public Product getProductById(int id) {
      for (Product product : inventory) {
          if (product.getID() == id) {
              return product;
          }
      }
      return null;
  }
    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] productData = line.split("\\|");
    
                if (productData.length == 4) {
                    int id = Integer.parseInt(productData[0]);
                    String name = productData[1];
                    int quantity = Integer.parseInt(productData[2]);
                    double price = Double.parseDouble(productData[3].substring(2));
    
                    Product product = new Product(id, name, quantity, price);
                    inventory.add(product);
                } else {
                    System.out.println("Invalid product data format: " + line);
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
            for (Product product : inventory) {
                writer.write(product.toString());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("An error occurred write writing to Inventory.txt");
            e.printStackTrace();
        }
    }
}




