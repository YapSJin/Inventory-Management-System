import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class StaffManagement {
    private ArrayList<Staff> management = new ArrayList<>();
    private String filename = "../Staff.txt";
    private String adminUser = "admin";
    private String adminPass = "admin1234";

    public StaffManagement() {
        loadFromFile();
    }

    public void login(Scanner scanner, String username, String password) {
        if (username.equals(adminUser) && password.equals(adminPass)) {
            menu(scanner);
        } else {
            Staff staff = matchCredentials(username, password);

            if (staff != null) {
                menu(scanner, staff);
            } else {
                System.out.println("\nInvalid username or password\n");
            }
        }
    }

    // Admin Methods
    private void menu(Scanner scanner) {
        int option;
        do {
            System.out.println("\nWelcome Admin, what do you like to do?");
            System.out.println("1. Report Module");
            System.out.println("2. Display All Staff");
            System.out.println("3. Manage Staff");
            System.out.println("0. Logout");
            System.out.println("-------------------------------------------");
            System.out.print("Enter your choice: ");
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    break;
                case 2:
                    displayAll();
                    break;
                case 3:
                    manageStaff(scanner);
                    break;
                case 0:
                    System.out.println("\nLogged out successfully!");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        } while (option != 0);
    }

    private void displayAll() {
        System.out.println("\nFulltime Staff");
        System.out.printf("\n%-3s %-15s %-15s %-15s %-20s %-15s %-8s %-8s %-12s %s\n", "ID", "Name", "IC Number", "Phone Number", "Email", "Password", "Bonus", "Fines", "Base Salary", "Salary");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        for (Staff staff : management) {
            if (staff instanceof FulltimeStaff) {
                FulltimeStaff ftstaff = (FulltimeStaff) staff;
                System.out.printf("%-3d %-15s %-15s %-15s %-20s %-15s %-8.2f %-8.2f %-12.2f %.2f\n", ftstaff.getStaffCode(), ftstaff.getName(), ftstaff.getICNum(), ftstaff.getPhone(), ftstaff.getEmail(), ftstaff.getPassword(), ftstaff.getBonus(), ftstaff.getFine(), ftstaff.getBaseSalary(), ftstaff.getSalary());
            }
        }

        System.out.println("\n\nParttime Staff");
        System.out.printf("\n%-3s %-15s %-15s %-15s %-20s %-15s %-12s %s\n", "ID", "Name", "IC Number", "Phone Number", "Email", "Password", "Work Hours", "Salary");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        for (Staff staff : management) {
            if (staff instanceof ParttimeStaff) {
                ParttimeStaff ptstaff = (ParttimeStaff) staff;
                System.out.printf("%-3d %-15s %-15s %-15s %-20s %-15s %-12d %.2f\n", ptstaff.getStaffCode(), ptstaff.getName(), ptstaff.getICNum(), ptstaff.getPhone(), ptstaff.getEmail(), ptstaff.getPassword(), ptstaff.getWorkHours(), ptstaff.getSalary());
            }
        }
    }

    private void manageStaff(Scanner scanner) {
        int choice;

        do {
            displayAll();

            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Add Staff");
            System.out.println("2. Update Staff");
            System.out.println("3. Delete Staff");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addStaff(scanner);
                    break;
                case 2:
                    updateStaff(scanner);
                    break;
                case 3:
                    deleteStaff(scanner);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        } while (choice != 0);
    }

    private void addStaff(Scanner scanner) {
        int id = management.size() + 1;

        System.out.print("\nEnter staff name: ");
        String name = scanner.nextLine();

        System.out.print("Enter staff IC: ");
        String ICNum = scanner.nextLine();

        System.out.print("Enter staff phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter staff email: ");
        String email = scanner.nextLine();

        System.out.print("Enter staff password: ");
        String password = scanner.nextLine();

        boolean invalid = true;

        do {
            System.out.print("Enter staff position (FULLTIME/PARTTIME): ");
            String position = scanner.nextLine();

            if (position.equalsIgnoreCase("Fulltime")) {
                invalid = false;

                System.out.print("Enter staff salary: ");
                double salary = Double.parseDouble(scanner.nextLine());

                FulltimeStaff staff = new FulltimeStaff(id, name, ICNum, phone, email, password, 0, 0, salary);
                management.add(staff);
                saveToFile(staff);

                System.out.println("-----------------------------");
                System.out.println("Staff successfully added!");
                System.out.println("-----------------------------");

            } else if (position.equalsIgnoreCase("Parttime")) {
                invalid = false;

                System.out.print("Enter staff working hours: ");
                int workHours = Integer.parseInt(scanner.nextLine());

                ParttimeStaff staff = new ParttimeStaff(id, name, ICNum, phone, email, password, workHours);
                management.add(staff);
                saveToFile(staff);

                System.out.println("-----------------------------");
                System.out.println("Staff successfully added!");
                System.out.println("-----------------------------");
            }
        } while (invalid);
    }

    private void updateStaff(Scanner scanner) {
        System.out.print("\nEnter staff ID to update (0 to cancel): ");
        int id = Integer.parseInt(scanner.nextLine());

        Staff toUpdate = null;
        for (Staff staff : management) {
            if (staff.getStaffCode() == id) {
                toUpdate = staff;
                break;
            }
        }

        if (toUpdate != null) {
            if (toUpdate instanceof FulltimeStaff) {
                boolean invalid = true;
                do {
                    FulltimeStaff staff = (FulltimeStaff) toUpdate;

                    System.out.println("\nCurrent details:\n");
                    System.out.println("Name: " + staff.getName());
                    System.out.println("IC Number: " + staff.getICNum());
                    System.out.println("Phone Number: " + staff.getPhone());
                    System.out.println("Email: " + staff.getEmail());
                    System.out.println("Password: " + staff.getPassword());
                    System.out.println("Bonus: " + staff.getBonus());
                    System.out.println("Fines: " + staff.getFine());
                    System.out.println("Base Salary: " + staff.getBaseSalary());

                    System.out.print("\nEnter field to update, done to confirm: ");
                    String field = scanner.nextLine();

                    switch (field.toLowerCase()) {
                        case "name":
                            System.out.print("Enter new name: ");
                            String name = scanner.nextLine();
                            staff.setName(name);
                            break;
                        case "ic number":
                            System.out.print("Enter new IC Number: ");
                            String icNum = scanner.nextLine();
                            staff.setICNum(icNum);
                            break;
                        case "phone number":
                            System.out.print("Enter new phone number: ");
                            String phone = scanner.nextLine();
                            staff.setPhone(phone);
                            break;
                        case "email":
                            System.out.print("Enter new email: ");
                            String email = scanner.nextLine();
                            staff.setEmail(email);
                            break;
                        case "password":
                            System.out.print("Enter new password: ");
                            String password = scanner.nextLine();
                            staff.setPassword(password);
                            break;
                        case "bonus":
                            System.out.print("Enter new bonus amount: ");
                            double bonus = Double.parseDouble(scanner.nextLine());
                            staff.setBonus(bonus);
                            break;
                        case "fines":
                            System.out.print("Enter new fines amount: ");
                            double fines = Double.parseDouble(scanner.nextLine());
                            staff.setFine(fines);
                            break;
                        case "base salary":
                            System.out.print("Enter new base salary: ");
                            double salary = Double.parseDouble(scanner.nextLine());
                            staff.setBaseSalary(salary);
                            break;
                        case "done":
                            invalid = false;
                            break;
                        default:
                            System.out.println("Invalid option, please try again.");
                            break;
                    }
                } while (invalid);
            } else {
                boolean invalid = true;
                do {
                    ParttimeStaff staff = (ParttimeStaff) toUpdate;

                    System.out.println("\nCurrent details:\n");
                    System.out.println("Name: " + staff.getName());
                    System.out.println("IC Number: " + staff.getICNum());
                    System.out.println("Phone Number: " + staff.getPhone());
                    System.out.println("Email: " + staff.getEmail());
                    System.out.println("Password: " + staff.getPassword());
                    System.out.println("Work Hours: " + staff.getWorkHours());

                    System.out.print("\nEnter field to update, done to confirm: ");
                    String field = scanner.nextLine();

                    switch (field.toLowerCase()) {
                        case "name":
                            System.out.print("Enter new name: ");
                            String name = scanner.nextLine();
                            staff.setName(name);
                            break;
                        case "ic number":
                            System.out.print("Enter new IC Number: ");
                            String icNum = scanner.nextLine();
                            staff.setICNum(icNum);
                            break;
                        case "phone number":
                            System.out.print("Enter new phone number: ");
                            String phone = scanner.nextLine();
                            staff.setPhone(phone);
                            break;
                        case "email":
                            System.out.print("Enter new email: ");
                            String email = scanner.nextLine();
                            staff.setEmail(email);
                            break;
                        case "password":
                            System.out.print("Enter new password: ");
                            String password = scanner.nextLine();
                            staff.setPassword(password);
                            break;
                        case "work hours":
                            System.out.print("Enter new work hours: ");
                            int workHours = Integer.parseInt(scanner.nextLine());
                            staff.setWorkHours(workHours);
                            break;
                        case "done":
                            invalid = false;
                            break;
                        default:
                            System.out.println("Invalid option, please try again.");
                            break;
                    }
                } while (invalid);
            }

            updateFile();

            System.out.println("-----------------------------");
            System.out.println("Staff successfully updated!");
            System.out.println("-----------------------------");
        } else {
            System.out.println("Staff with ID " + id + " not found.");
        }
    }

    private void deleteStaff(Scanner scanner) {
        System.out.print("\nEnter staff ID to delete (0 to cancel): ");
        int id = Integer.parseInt(scanner.nextLine());

        Staff toRemove = null;
        for (Staff staff : management) {
            if (staff.getStaffCode() == id) {
                toRemove = staff;
                break;
            }
        }

        if (toRemove != null) {
            management.remove(toRemove);
            updateFile();

            System.out.println("-----------------------------");
            System.out.println("Staff successfully deleted!");
            System.out.println("-----------------------------");
        } else {
            System.out.println("Staff with ID " + id + " not found.");
        }
    }

    // Staff Methods
    private Staff matchCredentials(String username, String password) {
        for (Staff staff : management) {
            if (staff.getEmail().equals(username) && staff.getPassword().equals(password)) {
                return staff;
            }
        }
        return null;
    }

    private void menu(Scanner scanner, Staff staff) {
        int option;
        do {
            System.out.println("\nWelcome " + staff.getName() + ", what do you like to do?");
            System.out.println("1. Inventory Module");
            System.out.println("2. View Salary");
            System.out.println("3. Edit Account");
            System.out.println("0. Logout");
            System.out.println("-------------------------------------------");
            System.out.print("Enter your choice: ");
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    break;
                case 2:
                    viewSalary(staff);
                    break;
                case 3:
                    updateStaff(scanner, staff);
                    break;
                case 0:
                    System.out.println("\nLogged out successfully!");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        } while (option != 0);
    }

    private void viewSalary(Staff staff) {
        if (staff instanceof FulltimeStaff) {
            FulltimeStaff ftStaff = (FulltimeStaff) staff;

            System.out.printf("\n\n%-15s: RM%8.2f\n", "Base Salary", ftStaff.getBaseSalary());
            System.out.printf("%-15s: RM%8.2f\n", "Bonus", ftStaff.getBonus());
            System.out.printf("%-15s: RM%8.2f\n", "Fines", ftStaff.getFine());
            System.out.printf("%-15s: RM%8.2f\n\n", "Total Salary", ftStaff.getSalary());
        } else {
            ParttimeStaff ptStaff = (ParttimeStaff) staff;

            System.out.printf("\n\n%-15s: RM%8.2f\n", "Base Salary", 100.0);
            System.out.printf("%-15s: %6d Hrs\n", "Work Hours", ptStaff.getWorkHours());
            System.out.printf("%-15s: RM%8.2f\n\n", "Total Salary", ptStaff.getSalary());
        }
    }

    private void updateStaff(Scanner scanner, Staff staff) {
        boolean invalid = true;
        do {
            System.out.println("\nCurrent details:\n");
            System.out.println("Name: " + staff.getName());
            System.out.println("IC Number: " + staff.getICNum());
            System.out.println("Phone Number: " + staff.getPhone());
            System.out.println("Email: " + staff.getEmail());
            System.out.println("Password: " + staff.getPassword());

            System.out.print("\nEnter field to update, done to confirm: ");
            String field = scanner.nextLine();

            switch (field.toLowerCase()) {
                case "name":
                    System.out.print("Enter new name: ");
                    String name = scanner.nextLine();
                    staff.setName(name);
                    break;
                case "ic number":
                    System.out.print("Enter new IC Number: ");
                    String icNum = scanner.nextLine();
                    staff.setICNum(icNum);
                    break;
                case "phone number":
                    System.out.print("Enter new phone number: ");
                    String phone = scanner.nextLine();
                    staff.setPhone(phone);
                    break;
                case "email":
                    System.out.print("Enter new email: ");
                    String email = scanner.nextLine();
                    staff.setEmail(email);
                    break;
                case "password":
                    System.out.print("Enter new password: ");
                    String password = scanner.nextLine();
                    staff.setPassword(password);
                    break;
                case "done":
                    invalid = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        } while (invalid);

        updateFile();
        System.out.println("-----------------------------");
        System.out.println("Account successfully updated!");
        System.out.println("-----------------------------");
    }

    // Misc Methods
    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] staffData = line.split("\\|");
                int id = Integer.parseInt(staffData[0]);
                String name = staffData[1];
                String ICNum = staffData[2];
                String phone = staffData[3];
                String email = staffData[4];
                String password = staffData[5];

                if (staffData.length == 9) {
                    double bonus = Double.parseDouble(staffData[6]);
                    double fine = Double.parseDouble(staffData[7]);
                    double salary = Double.parseDouble(staffData[8]);

                    FulltimeStaff staff = new FulltimeStaff(id, name, ICNum, phone, email, password, bonus, fine, salary);
                    management.add(staff);
                } else if (staffData.length == 7) {
                    int workHours = Integer.parseInt(staffData[6]);

                    ParttimeStaff staff = new ParttimeStaff(id, name, ICNum, phone, email, password, workHours);
                    management.add(staff);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from Staff.txt");
            e.printStackTrace();
        }
    }

    private void saveToFile(Staff staff) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(staff.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred write writing to Staff.txt");
            e.printStackTrace();
        }
    }

    private void updateFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Staff staff : management) {
                writer.write(staff.toString());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("An error occurred write writing to Staff.txt");
            e.printStackTrace();
        }
    }
}
