import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class testReport1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReportSales product = new ReportSales("1", "Milk", 1.99, 180);
        ReportSales product1 = new ReportSales("2", "Najib Super Ring", 1.50, 230);
        ReportSales product2 = new ReportSales("3", "Weilun Small Snack", 5.00, 220);
        ReportSales product3 = new ReportSales("4", "Pet Food", 7.00, 230);
        dailyReport daily = new dailyReport();
        Product.getProducts();
        File file = new File("inventory.txt");
        File file1 = new File("Reportsales.txt");
        int menuSelected = 0;
        do {
            System.out.println(
                    "Please Select a Option:");
            System.out.println(
                    "1.Sales Report");
            System.out.println(
                    "2.inventory Report");
            System.out.println(
                    "3.Profit Report");
            System.out.println(
                    "0.Quit");
            System.out.print(
                    "Enter a Number :" + " ");

            menuSelected = scanner.nextInt();

            switch (menuSelected) {
                case 1:
                    System.out.println("--------Report Sales--------");
                    System.out.println("----------------------------");
                    System.out.println("Total Sales Amount :" + (product.getTotalAmount() + product1.getTotalAmount()
                            + product2.getTotalAmount() + product3.getTotalAmount()));
                    System.out.println("ProdID \tProdName \tPrice(RM) \t Quantitysell");
                    try {

                        BufferedReader sr = new BufferedReader(new FileReader("Reportsales.txt"));

                        Scanner x = new Scanner(sr);
                        new FileReader("Reportsales.txt");

                        while (x.hasNext()) {
                            String id = x.next();
                            String name = x.next();

                            String d = x.next();
                            double price = Double.valueOf(d);

                            String c = x.next();
                            int quantitysell = Integer.valueOf(c);

                            String e = x.next();
                            double profitsell = Double.valueOf(e);

                            System.out.printf("%s \t%s\t%.02f\t\t %d\n", id, name, price, quantitysell);
                            System.out.println("Total amount :" + (quantitysell * price));
                            System.out.println("-------------------------------------------------");

                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

                case 2:
                    System.out.println("---------------Inventory Report----------------");
                    System.out.println("-----------------------------------------------");
                    System.out.println("ProdID \tProdName\t Quantity\tPrice(RM)");
                    try {

                        BufferedReader ir = new BufferedReader(new FileReader("inventory.txt"));

                        Scanner x = new Scanner(ir);
                        int num = 0;
                        double total = 0.0;
                        new FileReader("inventory.txt");

                        while (x.hasNext()) {
                            String a = x.next();
                            num++;
                            String b = x.next();

                            String c = x.next();
                            int s = Integer.valueOf(c);
                            // double l = Double.valueOf(c);

                            String d = x.next();
                            double z = Double.valueOf(d);
                            total += (s * z);

                            String e = x.next();
                            double profitinventorysell = Double.valueOf(e);

                            System.out.printf("%s \t%s\t %d\t\t%.2f\n", a, b, s, z);

                        }
                        System.out.println("--------------------------------------------");
                        System.out.printf("Total Amount of Restock : %d\n", num);
                        System.out.printf("Total Amount of Output : %.2f\n\n", total);
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    break;
                case 3:
                    profit.getProfitMenu();
                    break;
                case 0:
                    System.out.println("Thank you for choosing us! Have a nice day!.");
                    break;
                default:
                    System.out.println("Not a valid choice.");

            }
        } while (menuSelected != 0);

    }
}