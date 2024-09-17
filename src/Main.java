import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StaffManagement staffManagement = new StaffManagement();
        int choice;

        do {
            System.out.print(
                    "                              _\n" +
                            "                     ___----~~~~~~----__\n" +
                            "                _-~~~____-----~~-----____~~~-\n" +
                            "             ..~.-~~--   ___------___   --~~-_~.\n" +
                            "           .'_.~.~~---~~~  __----__  ~~~---~` ~`.`\n" +
                            "         .'.~ .'.~~__---~~~        ~~~---__~`.`. `_`\n" +
                            "        ' .'.' /'/~          ,-._          ~-.'.`. `.~\n" +
                            "      /'/'/'/'/'/          _.-'  '--.         `.`.`. ~.`\n" +
                            "    .~.~.~.~.~.~         .'       _  -` _       `..`. .``.\n" +
                            "   .'.'.'.'.'.'         / .----. `_.'----'        .`.`.`.``\n" +
                            "  | | | | | |           ;/     `                   : .`.`.`.\n" +
                            "_|_|_|_|_|_|_         /_;                           : `.`.`.\n" +
                            "_.-._)` _.-._)` _.-._)` _.-._)` _.-._)` _.-._)` _.-._)` _.-._)`\n" +
                            "----------------------------------------------------------------\n" +
                            "          Welcome to ToyUMT Inventory Management System\n" +
                            "----------------------------------------------------------------\n");
            System.out.println("1: Login");
            System.out.println("0: Exit");
            System.out.println("---------------------");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("\nEmail: ");
                    String username = scanner.nextLine();

                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    staffManagement.login(scanner, username, password);
                    break;
                case 0:
                    System.out.println("Goodbye, have a nice day!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        } while (choice != 0);
        scanner.close();
    }
}
