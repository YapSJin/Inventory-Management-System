import java.util.Scanner;

public abstract class Staff {
    private int staffCode;
    private String name;
    private String ICNum;
    private String phone;
    private String email;
    private String password;

    public Staff(int staffCode, String name, String ICNum, String phone, String email, String password) {
        this.staffCode = staffCode;
        this.name = name;
        this.ICNum = ICNum;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public int getStaffCode() {
        return this.staffCode;
    }

    public void setStaffCode(int staffCode) {
        this.staffCode = staffCode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getICNum() {
        return this.ICNum;
    }

    public void setICNum(String ICNum) {
        this.ICNum = ICNum;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.phone = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getSalary() {
        return 0;
    }

    public abstract void viewSalary();

    public abstract void updateDetails(Scanner scanner);

    public void updateAccountDetails(Scanner scanner) {
        boolean invalid = true;
        do {
            System.out.printf("\nCurrent details:\n\nName: %s\nIC Number: %s\nPhone Number: %s\nEmail: %s\nPassword: %s\n", getName(), getICNum(), getPhone(), getEmail(), getPassword());

            System.out.print("\nEnter field to update, done to confirm: ");
            String field = scanner.nextLine();

            switch (field.toLowerCase()) {
                case "name":
                    String name = getValidString(scanner, "Enter new name: ");
                    setName(name);
                    break;
                case "ic number":
                    String icNum = getValidString(scanner, "icnum", "Enter new IC Number: ");
                    setICNum(icNum);
                    break;
                case "phone number":
                    String phone = getValidString(scanner, "phone", "Enter new phone number: ");
                    setPhone(phone);
                    break;
                case "email":
                    String email = getValidString(scanner, "email", "Enter new email: ");
                    setEmail(email);
                    break;
                case "password":
                    String password = getValidString(scanner, "Enter new password: ");
                    setPassword(password);
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

    public static String getValidString(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty, please try again.");
            }
        } while (input.isEmpty());

        return input;
    }

    public static String getValidString(Scanner scanner, String type, String prompt) {
        String input;
        boolean valid;

        do {
            System.out.print(prompt);
            input = scanner.nextLine();
            valid = true;

            switch (type.toLowerCase()) {
                case "icnum":
                    if (!input.matches("^(([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01]))([0-9]{2})([0-9]{4})$")) {
                        System.out.println("Invalid IC number, please try again.");
                        valid = false;
                    }
                    break;
                case "phone":
                    if (!input.matches("^[0-9]{10,11}$")) {
                        System.out.println("Invalid phone number, please try again.");
                        valid = false;
                    }
                    break;
                case "email":
                    if (!input.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]{2,3}$")) {
                        System.out.println("Invalid email address, please try again.");
                        valid = false;
                    }
                    break;
            }
        } while (!valid);

        return input;
    }

    public String toString(boolean format) {
        if (format) {
            return String.format("%-3d %-20s %-15s %-15s %-20s %-15s", this.staffCode, this.name, this.ICNum, this.phone, this.email, this.password);
        } else {
            return String.format("%d|%s|%s|%s|%s|%s", this.staffCode, this.name, this.ICNum, this.phone, this.email, this.password);
        }
    }

    public String toString() {
        return String.format("\nCurrent details:\n\nName: %s\nIC Number: %s\nPhone Number: %s\nEmail: %s\nPassword: %s\n", getName(), getICNum(), getPhone(), getEmail(), getPassword());
    }
}