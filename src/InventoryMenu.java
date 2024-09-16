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

            switch (choice) {
                case 1:
                    System.out.print("nEnter product name: ");
                    String prodName = scanner.nextLine();

                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();

                    System.out.print("Enter price of product (xx.xx): RM");
                    double price = scanner.nextDouble();

                    inv.addProduct(prodName, quantity, price);
                    break;

                case 2:
                    inv.displayAll();

                    System.out.print("\nEnter Product ID to delete: ");
                    int prodID = scanner.nextInt();

                    inv.deleteProduct(prodID);
                    break;

                case 3:
                    inv.displayAll();

                    System.out.print("\nEnter Product ID to edit: ");
                    prodID = scanner.nextInt();

                    inv.editProduct(prodID);
                    break;

                case 4:
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
        System.out.println("2: Delete Product");
        System.out.println("3: Update Product");
        System.out.println("4: Display All Product");
        System.out.println("0: Back");
        System.out.print("Enter option: ");
    }

}
