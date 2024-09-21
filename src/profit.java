import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class profit {

    private static double sst = 0.02;
    public static String filename = "inventory.txt";
    public static String filename1 = "Reportsales.txt";
    File file1 = new File("Reportsales.txt");

    File file = new File("inventory.txt");

    public static double getSst() {
        return sst;
    }

    public static void setSst(double sst) {
        profit.sst = sst;
    }

    @SuppressWarnings("resource")
    public static void getProfitMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("== Report Menu ==");
        System.out.println("1: Inventory Profit report");
        System.out.println("2: Salse Profit report");
        System.out.println("3: Exit program");
        System.out.print("Enter your option of report : ");
        int choice = scanner.nextInt();
        System.out.println();

        switch (choice) {
            case 1:
                System.out.println("-------------------------Inventory Profit report-----------------------");
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("ProdID \tProdName\t Quantity\tPrice(RM)\tOriginalPrice");
                try {

                    BufferedReader ir = new BufferedReader(new FileReader("inventory.txt"));

                    Scanner x = new Scanner(ir);
                    double total = 0.0;
                    double total1 = 0.0;
                    new FileReader("inventory.txt");

                    while (x.hasNext()) {
                        String a = x.next();
                        String b = x.next();

                        String c = x.next();
                        int s = Integer.valueOf(c);
                        // double l = Double.valueOf(c);

                        String d = x.next();
                        double z = Double.valueOf(d);
                        total += (s * z);

                        String f = x.next();
                        double g = Double.valueOf(f);
                        total1 += (s * g);

                        System.out.printf("%s \t%s\t %d\t\t%.2f\t\t%.2f\n", a, b, s, z, g);

                    }
                    System.out.println("--------------------------------------------------");
                    System.out.printf("Total (RM)Amount that can be sales is : %.2f\n", total);
                    System.out.println("--------------------------------------------------");
                    System.out.printf("Total (RM)Amount of Restock : %.2f\n", total1);
                    System.out.println("--------------------------------------------------------------");
                    System.out.printf("Total (RM)amount of profit you can earn is : ** %.2f **(not include sst)\n\n",
                            (total - total1));
                } catch (Exception e) {
                    System.out.println(e);
                }

                break;
            case 2:
                System.out.println("--------------------------Report Sales--------------------------");
                System.out.println("----------------------------------------------------------------");
                System.out.println("ProdID \tProdName \tPrice(RM) \t Quantitysell \t OriginalPrice");
                try {

                    BufferedReader sr = new BufferedReader(new FileReader("Reportsales.txt"));

                    Scanner x = new Scanner(sr);
                    new FileReader("Reportsales.txt");
                    double salestotal = 0.0;
                    double profittotal = 0.0;
                    double Sstprice = 0.0;

                    while (x.hasNext()) {
                        String id = x.next();
                        String name = x.next();

                        String d = x.next();
                        double price = Double.valueOf(d);

                        String c = x.next();
                        int quantitysell = Integer.valueOf(c);

                        String e = x.next();
                        double profitprice = Double.valueOf(e);
                        profittotal += (quantitysell * profitprice);
                        salestotal += (quantitysell * price);

                        System.out.printf("%s \t%s\t%.02f\t\t %d\t\t %.02f\n", id, name, price, quantitysell,
                                profitprice);

                    }
                    Sstprice = (salestotal * profit.getSst());
                    System.out.println("--------------------------------------------------");
                    System.out.printf("Total (RM)Amount of sales is : %.2f\n", salestotal);
                    System.out.println("--------------------------------------------------");
                    System.out.printf("Total (RM)Amount of SST is : %.2f\n", Sstprice);
                    System.out.println("--------------------------------------------------");
                    System.out.printf("Total (RM)Amount of Original price is : %.2f\n", profittotal);
                    System.out.println("--------------------------------------------------------");
                    System.out.printf("Total (RM)amount of profit you earn is : *** %.2f *** \n\n",
                            (salestotal - Sstprice - profittotal));
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case 0:
                System.out.println("Bye bye.");
                System.exit(0);
                break;
            default:
                System.out.println("Not a valid choice.");
                break;
        }
    }

}