import java.util.Scanner;
import java.util.ArrayList;

public class ReportMenu {

    public static void main(Scanner scanner) {
        Inventory inventory = new Inventory();
        Profit profit = new Profit();
        ArrayList<Product> prodList = inventory.getInventory();

        int menuSelected = 0;
        do {
            System.out.println("\nPlease Select an Option:");
            System.out.println("1. Current Inventory");
            System.out.println("2. Inventory Profit");
            System.out.println("3. Sales Profit");
            System.out.println("0. Back");
            System.out.print("Enter a Number: ");
            menuSelected = scanner.nextInt();

            switch (menuSelected) {
                case 1:
                    System.out.print("\n---------------Current Inventory----------------");
                    inventory.displayAll();
                    break;
                case 2:
                    System.out.println("\n-------------------------Inventory Profit report-----------------------");
                    inventory.displayAll();
                    profit.inventoryProfit(prodList);
                    break;
                case 3:
                    profit.salesProfit();
                    break;
                case 0:
                    System.out.println("Thank you for choosing us! Have a nice day!");
                    break;

                default:
                    System.out.println("Not a valid choice.");
            }
        } while (menuSelected != 0);
    }
}
