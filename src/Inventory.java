import java.io.*;
import java.util.ArrayList;

public class Inventory {
    private static String filename = "../inventory.txt";
    private ArrayList<String> al = new ArrayList<>();

    public Inventory(String idNumber, String productName, int quantity, double price, double restockPrice) {
        this.prodID = prodID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.restockPrice = restockPrice;
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

    public void restockProduct() {
        
    }

    public void deleteProduct(int prodID) {
        System.out.print("\nEnter Product id number (Pxx): ");
            String id = insert.nextLine();
                String line;
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(filename));
                        while ((line = br.readLine()) != null) {
                            if (line.contains(id)) {
                                System.out.println("----------------------------------------------------------------------------------------");
                                System.out.println("This record has been deleted!!\n");
                                System.out.println(line);
                                continue;
                            } else {
                                al.add(line);
                            }
                        }
                    } catch (Exception e) {
                        
                    }
                    try {
                        FileWriter fw = new FileWriter(filename);
                        for (int i = 0; i < al.size(); i++) {
                            fw.append(al.get(i));
                            fw.append("\n");
                        }

                        fw.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    } finally {
                        System.out.println("----------------------------------------------------------------------------------------");
                    }
    }

    public void editProduct(int prodID) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            System.out.print("\nEnter id number to update product (Pxx): ");
            id = insert.nextLine();

            while ((line = br.readLine()) != null) {
                if (line.contains(id)) {
                    System.out.print("Enter the value that you want to change (Quantity/Price/Product Name) : ");
                    String oldValue = input.next();
                    System.out.print("Enter new value in the class (Quantity/Price/Product Name) : ");
                    String newValue = input.next();

                    al.add(line.replace(oldValue, newValue));
                    System.out.println("---------------------------------------");
                    System.out.println("Your Are Successful Modify The Record!!");
                    System.out.println("---------------------------------------");

                } else {
                    al.add(line);

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            FileWriter fw = new FileWriter(filename);

            for (int i = 0; i < al.size(); i++) {
                fw.append(al.get(i));
                fw.append("\n");
            }

            fw.close();

        } catch (Exception e) {
            System.out.println(e);
        }

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
