import java.util.ArrayList;
import java.util.Scanner;

import java.io.*;

public class Menu {

    public static String filename = "inventory.txt";

    File file = new File("inventory.txt");

    public static void welcomeMessage() {
        System.out.printf("<=-=-=>TARUC SuperMarket<=-=-=>\n" + "Log in to continue\n");
    }

    public static String[] getCredentials() {
        Scanner scanner = new Scanner(System.in);
        String[] credentials = new String[2];
        System.out.print("Username: ");
        credentials[0] = scanner.nextLine();
        System.out.printf("Password: ");
        credentials[1] = scanner.nextLine();
        return credentials;
    }

    public static Customer checkCredentials(String username, String password) { // validation
        ArrayList<Customer> customers = Customer.getCustomers();
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)
                    && customer.getPassword().equals(password)) {
                return customer;
            }
        }
        return null;
    }

    public static void getCustomerMenu(Customer sc) {
        int choice;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("== Sales Menu ==");
            System.out.println("1: Product List");
            System.out.println("2: Buy Product");
            System.out.println("0: Exit program");
            System.out.print("Enter your choice : ");
            choice = scanner.nextInt();
            System.out.println();
            switch (choice) {
                case 1:

                    printProductsSimple();
                    break;
                case 2:
                    buyProducts(sc);
                    break;
                case 0:
                    System.out.println("Thank you for choosing us! Have a nice day!");
                    break;
                default:
                    System.out.println("Not a valid choice.");
                    break;
            }
        } while (choice != 0);
    }

    public static void getMemberMenu(Member ms) {
        int choice;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("== Sales Menu ==");
            System.out.println("1: Change Password");
            System.out.println("3: Product List");
            System.out.println("0: Exit program");
            System.out.print("Enter your choice : ");
            choice = scanner.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    changeUserPassword(ms);
                    break;
                case 2:
                    printBonusPoints(ms);
                    break;
                case 3:
                    printProductsBonus();
                    break;
                case 4:
                    buyProducts(ms);
                    break;
                case 5:
                    printPrevTransactions(ms);
                    break;
                case 0:
                    System.out.println("Thank you for choosing us! Have a nice day!");
                    break;
                default:
                    System.out.println("Not a valid choice.");
            }
        } while (choice != 0);
    }

    public static void changeUserPassword(Customer sc) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type new password: ");
        String password1 = scanner.nextLine();
        System.out.print("Type it again: ");
        String password2 = scanner.nextLine();
        sc.changePassword(password1, password2);
    }

    public static void printBonusPoints(Member ms) {
        int points = ms.getCard().getPoints();
        int needed = 200 - points;
        System.out.printf("You have %d points ", points);
        System.out.printf("you need %d to get 20%% discount\n", needed);
    }

    public static void printProductsBonus() {
        System.out.println("\t\t\tList of Available Product");
        System.out.printf("Order Number\t Product ID\t Product Name\t\t  Price\t\t\t Quantity\n");
        ArrayList<Product> products = Product.getProducts();
        for (Product product : products) {
            if (product.getQuantity() > 0) {
                System.out.println(product);
            }
        }
        Menu.writeProductFile(products);
    }

    public static void printProductsSimple() {
        System.out.println("\t\t\tList of Available Product");
        System.out.printf("Order Number\t Product ID\t Product Name\t\t  Price\t\t\t Quantity\n");
        ArrayList<Product> products = Product.getProducts();
        for (Product product : products) {
            System.out.println(product);
        }

        Menu.writeProductFile(products);

    }

    public static void writeProductFile(ArrayList<Product> products) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("inventory.txt"));

            for (Product product : products) {
                pw.println(String.format("%3s %15s %5d %7.2f %7.2f ", product.getProductid(), product.getName(),
                        product.getQuantity(), product.getPrice(), product.getProfit()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pw != null)
                pw.close();
        }
    }

    public static void buyProducts(Customer cust) {
        int id;
        int quantity;
        int totalQuantity = 0;
        Product prod;
        ArrayList<int[]> basket = new ArrayList<int[]>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int[] purchase = new int[2];
            System.out.print("Type Order id: ");
            id = scanner.nextInt();
            prod = Product.checkAvailability(id);
            if (id == -1) {
                break;
            }
            if (prod == null) {
                System.out.println("Order with id " + id + " does not exist. Please Try Again.");
            } else if (prod.getQuantity() == 0) {
                System.out.println("Product " + prod.getName() + " is not available");
            } else {
                while (true) {
                    System.out.print("Quantity: ");
                    quantity = scanner.nextInt();
                    if (quantity <= prod.getQuantity()) {
                        purchase[0] = id;
                        purchase[1] = quantity;
                        basket.add(purchase);
                        prod.setQuantity(prod.getQuantity() - quantity);
                        totalQuantity += quantity;
                        break;
                    } else {
                        System.out.printf("Not enough stock.\n");
                        System.out.println("Stock: " + prod.getQuantity() + " pieces.");
                        break;
                    }
                }
            }
        }
        Receipt rec = new Receipt(cust, basket);
        rec.setItems(totalQuantity);
        double amount = 0.0;
        if (cust instanceof Member) {
            Member customer = (Member) cust;
            int ptsWon = customer.getCard().pointsWon(rec.getCost());
            customer.getCard().addPoints(ptsWon);
            int points = customer.getCard().getPoints();
            double totalCost = rec.getCost();
            while (true) {
                System.out.printf("You need to pay = RM%.2f\n", totalCost);
                System.out.print("Please enter your payment amount: RM");
                amount = scanner.nextDouble();
                if (amount >= totalCost) {
                    break;
                } else {
                    System.out.println("Not enough money");
                }
            }
            if (points >= 200) {
                rec.printReceipt();
                System.out.println(
                        "=============================================================================================");
                System.out.println(
                        "Today you earned " + ptsWon + " points and reached" + " 200 points. You get 20% discount.");
                customer.getCard().setPoints(points - 200);
                points = customer.getCard().getPoints();
                totalCost *= 0.8;
                amount = amount - totalCost;
                rec.setCost(totalCost);
                System.out.println("You now have " + points + " points.");
                System.out.printf("Total Cost x Discount        = RM%.2f\n", totalCost);
                System.out.printf("Return Money                 = RM%.2f\n", amount);
                System.out.println("Thank you for purchasing! See you again!");
                System.out.println(
                        "=============================================================================================");
            } else {
                rec.printReceipt();
                amount = amount - totalCost;
                System.out.println(
                        "=============================================================================================");
                System.out.println("Not enough points for a discount yet!");
                System.out.printf("Total Cost                      = RM%.2f\n", totalCost);
                System.out.printf("Return Money                    = RM%.2f\n", amount);
                System.out.println("Thank you for purchasing! See you again!");
                System.out.println(
                        "=============================================================================================");
            }
        } else {
            double totalCost = rec.getCost();
            while (true) {
                System.out.printf("You need to pay = RM%.2f\n", totalCost);
                System.out.print("Please enter your payment amount: RM");
                amount = scanner.nextDouble();
                if (amount >= totalCost) {
                    break;
                } else {
                    System.out.println("Not enough money");
                }
            }
            amount = amount - totalCost;
            rec.printReceipt();
            System.out.println("=======================================================================");
            System.out.printf("Total Cost                      = RM%.2f\n", totalCost);
            System.out.printf("Return Money                    = RM%.2f\n", amount);
            System.out.println("Thank you for purchasing! See you again!");
            System.out.println("=======================================================================");
        }
    }

    public static void printPrevTransactions(Member ms) {
        ArrayList<Receipt> receipts = Receipt.getReceipts();
        for (Receipt receipt : receipts) {
            if (receipt.getBonusCustomer().equals(ms)) {
                System.out.println(receipt);
            }
        }
    }

}