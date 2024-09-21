import java.util.Scanner;

public class test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Are You Staff (Y/N): ");
        String ID = scanner.next();

        if ("N".equalsIgnoreCase(ID)) {
            // For staff details
            System.out.print("Enter staff code: ");
            int staffCode = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter your IC Number: ");
            String ICNum = scanner.nextLine();

            System.out.print("Enter your phone number: ");
            String phone = scanner.nextLine();

            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            // Create an instance of StaffDetail
            StaffDetail staff = new StaffDetail(staffCode, name, ICNum, phone, email, password);
            System.out.println("Staff details have been successfully recorded.");
            
            // Use the staff instance to print details
            System.out.println(staff);

        } else {
            System.out.println("Invalid input, please enter again.");
        }

        scanner.close();
    }
}
