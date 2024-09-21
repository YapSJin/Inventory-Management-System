import java.util.Scanner;

public class InventoryMenu {
    public static String filename = "inventory.txt";

    public static void main(String[] args) {
        Inventory inv = new Inventory();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            menu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    inv.addProduct(scanner);
                    break;
                case 2:
                inv.displayAll();
                System.out.print("\nEnter Product ID to Restock: ");
                int prodID = scanner.nextInt();
                scanner.nextLine();
                
                inv.restockProduct(scanner, prodID);
                break;
                

                case 3:
                    inv.displayAll();

                    inv.deleteProduct(scanner);
                    break;

                case 4:
                    inv.displayAll();

                    inv.editProduct(scanner);
                    break;

                case 5:
                    inv.displayAll();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }

    public static void menu() {
        System.out.print("\nInventory Management Module\n");
        System.out.println("1: Add Product");
        System.out.println("2: Restock Product");
        System.out.println("3: Delete Product");
        System.out.println("4: Update Product");
        System.out.println("5: Display All Product");
        System.out.println("0: Back");
        System.out.print("Enter option: ");
    }

}
