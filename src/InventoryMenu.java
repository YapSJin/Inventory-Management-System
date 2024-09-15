
import java.io.*;
import java.util.*;

public class InventoryMenu {

    public static String filename = "inventory.txt";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Scanner insert = new Scanner(System.in);
        File file = new File("inventory.txt");

        int choice = 0;
        do {
            menu();
            choice = input.nextInt();
            ArrayList<String> al = new ArrayList<String>();

            switch (choice) {
                case 1:
                    System.out.print("\nWhat is the product id number (Pxx): ");
                    String idNumber = insert.nextLine();

                    System.out.print("What is the product name : ");
                    String productName = insert.nextLine();

                    System.out.print("What is the quantity of the product : ");
                    int quantity = input.nextInt();

                    System.out.print("How many price of single product (xx.xx): RM");
                    double price = input.nextDouble();
                    
                    System.out.print("How many restock price for single product (xx.xx): RM");
                    double restockPrice = input.nextDouble();

                    Inventory inv = new Inventory(idNumber, productName, quantity, price, restockPrice);

                    try {
                        FileWriter fw = new FileWriter(filename, true);
                        fw.append(inv.toString());
                        fw.append("\n");
                        fw.close();
                        System.out.println("----------------------------------");
                        System.out.println("The product is added succesfully!!");
                        System.out.println("----------------------------------");
                    } catch (IOException e) {
                        System.out.println(e);
                    }

                    break;

                case 2:
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

                    break;

                case 3:
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

                break;

                case 4:
                try {
                    BufferedReader br = new BufferedReader(new FileReader(filename));
                    System.out.print("\nEnter Product id number (Pxx): ");
                    id = insert.nextLine();

                    int count = 0;

                    while ((line = br.readLine()) != null) {
                        if (line.contains(id)) {
                            System.out.println("----------------------------------------");
                            System.out.println("This Is The Record Find Inside The File.\n");
                            System.out.println(line);
                            System.out.println("----------------------------------------");

                            count++;
                        }

                    }
                    if (count == 0) {
                        System.out.println("----------------------------------------");
                        System.out.println("This Is The Record Find Inside The File.");
                        System.out.println("----------------------------------------");
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }

                break;

                case 5:
                     
                    System.out.println("ProdID \tProdName\t Quantity\tPrice");
                    try {

                        BufferedReader br = new BufferedReader(new FileReader("inventory.txt"));

                        Scanner x = new Scanner(br);
                        new FileReader("inventory.txt");

                        while (x.hasNext()) {
                            String a = x.next();
                            String b = x.next();

                            String c = x.next();
                            int s = Integer.valueOf(c);

                            String d = x.next();
                            double z = Double.valueOf(d);
                            
                            String l = x.next();

                         
                            
                            System.out.printf("%s \t%s\t %d\t\t%.2f\n",a,b,s,z);

                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    

                    break;

                case 0:
                    System.out.println("\n---------------");
                    System.out.println("Thank you for using the program. Goodbye!");
                    System.out.println("---------------");

                    break;

                default:
                    System.out.println("\n---------------");
                    System.out.println("Invalid input!!");
                    System.out.println("---------------");

                    break;
            }
        } while (choice != 0);

    }

    public static void menu() {
        System.out.println("Welcome To Our Inventory Management's MENU");
        System.out.println("1: Add Product");
        System.out.println("2: Delete Product");
        System.out.println("3: Update Product");
        System.out.println("4: Search Product");
        System.out.println("5: Display All Product");
        System.out.println("0: Exit program");
        System.out.print("Enter The Number You Want : ");
    }

}
