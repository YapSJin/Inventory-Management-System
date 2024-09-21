import java.util.Scanner;

public class testReport1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Create an Inventory instance
        Inventory inventory = new Inventory();
        
        // Assuming ReportSales is a class that you've created to handle sales reports
        ReportSales product = new ReportSales("1", "Milk", 1.99, 180);
        ReportSales product1 = new ReportSales("2", "Najib Super Ring", 1.50, 230);
        ReportSales product2 = new ReportSales("3", "Weilun Small Snack", 5.00, 220);
        ReportSales product3 = new ReportSales("4", "Pet Food", 7.00, 230);
        
        
        int menuSelected = 0;
        do {
            System.out.println("Please Select an Option:");
            System.out.println("1. Sales Report");
            System.out.println("2. Inventory Report");
            System.out.println("3. Profit Report");
            System.out.println("0. Quit");
            System.out.print("Enter a Number: ");
            menuSelected = scanner.nextInt();

            switch (menuSelected) {
                case 1:
                    System.out.println("--------Sales Report--------");
                    System.out.println("----------------------------");
                    double totalSalesAmount = product.getTotalAmount() + product1.getTotalAmount() +
                                              product2.getTotalAmount() + product3.getTotalAmount();
                    System.out.println("Total Sales Amount: RM" + totalSalesAmount);
                    System.out.println("ProdID \tProdName \tPrice(RM) \t Quantity Sold");

                    break;

                case 2:
                    System.out.println("---------------Inventory Report----------------");
                    System.out.println("-----------------------------------------------");
                    System.out.println("ProdID \tProdName\t Quantity\tPrice(RM)");
                    
                    // Display all products from the inventory
                    inventory.displayAll();
                    
                    break;

                case 3:
                    profit.getProfitMenu(); // Assuming `profit` is a valid class/method in your setup
                    break;

                case 0:
                    System.out.println("Thank you for choosing us! Have a nice day!");
                    break;

                default:
                    System.out.println("Not a valid choice.");
            }
        } while (menuSelected != 0);

        scanner.close();
    }
}
