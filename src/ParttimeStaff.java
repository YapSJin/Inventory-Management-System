import java.util.Scanner;

public class ParttimeStaff extends Staff {
	private int workHours;

	public ParttimeStaff(int staffCode, String name, String ICNum, String phone, String email, String password, int workHours) {
		super(staffCode, name, ICNum, phone, email, password);
		this.workHours = workHours;
	}

	public int getWorkHours() {
		return workHours;
	}

	public void setWorkHours(int workHours) {
		this.workHours = workHours;
	}

	public double getSalary() {
		return workHours * 100;
	}

	public void viewSalary() {
		System.out.printf("\n%-15s: RM%8.2f\n", "Base Salary", 100.0);
		System.out.printf("%-15s: %6d Hrs\n", "Work Hours", getWorkHours());
		System.out.printf("%-15s: RM%8.2f\n", "Total Salary", getSalary());
	}

	public void updateDetails(Scanner scanner) {
		boolean invalid = true;

		do {
			System.out.println(this.toString());

			System.out.print("\nEnter field to update, done to confirm: ");
			String field = scanner.nextLine();

			switch (field) {
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
				case "work hours":
					int workHours = getValidInt(scanner, "Enter new work hours: ");
					setWorkHours(workHours);
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

	public static int getValidInt(Scanner scanner, String prompt) {
		int value = -1;
		boolean valid = false;

		do {
			System.out.print(prompt);
			String input = scanner.nextLine();

			if (input.isEmpty()) {
				System.out.println("Input cannot be empty, please try again.");
			} else {
				try {
					value = Integer.parseInt(input);
					valid = true;
				} catch (NumberFormatException e) {
					System.out.println("Invalid number, please try again.");
				}
			}
		} while (!valid);

		return value;
	}

	public String toString(boolean format) {
		if (format) {
			return super.toString(true) + String.format(" %-12d %.2f\n", this.workHours, this.getSalary());
		} else {
			return super.toString(false) + String.format("|%d", this.workHours);
		}
	}

	public String toString() {
		return super.toString() + String.format("Work Hours: %d\n", getWorkHours());
	}
}
