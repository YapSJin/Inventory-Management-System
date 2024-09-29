import java.util.Scanner;

public class FulltimeStaff extends Staff {
	private double bonus;
	private double fine;
	private double baseSalary;

	public FulltimeStaff(int staffCode, String name, String ICNum, String phone, String email, String password, double bonus, double fine, double baseSalary) {
		super(staffCode, name, ICNum, phone, email, password);
		this.bonus = bonus;
		this.fine = fine;
		this.baseSalary = baseSalary;
	}

	public double getBonus() {
		return this.bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public double getFine() {
		return this.fine;
	}

	public void setFine(double fine) {
		this.fine = fine;
	}

	public double getBaseSalary() {
		return this.baseSalary;
	}

	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public double getSalary() {
		return baseSalary + bonus - fine;
	}

	public void viewSalary() {
		System.out.printf("\n%-15s: RM%8.2f\n", "Base Salary", getBaseSalary());
		System.out.printf("%-15s: RM%8.2f\n", "Bonus", getBonus());
		System.out.printf("%-15s: RM%8.2f\n", "Fines", getFine());
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
				case "bonus":
					double bonus = getValidDouble(scanner, "Enter new bonus amount: ");
					setBonus(bonus);
					break;
				case "fines":
					double fines = getValidDouble(scanner, "Enter new fines amount: ");
					setFine(fines);
					break;
				case "base salary":
					double salary = getValidDouble(scanner, "Enter new base salary: ");
					setBaseSalary(salary);
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

	public static double getValidDouble(Scanner scanner, String prompt) {
		double value = -1;
		boolean valid = false;

		do {
			System.out.print(prompt);
			String input = scanner.nextLine();

			if (input.isEmpty()) {
				System.out.println("Input cannot be empty, please try again.");
			} else {
				try {
					value = Double.parseDouble(input);
					valid = true;
				} catch (Exception e) {
					System.out.println("Invalid number, please try again.");
				}
			}
		} while (!valid);

		return value;
	}

	public String toString(boolean format) {
		if (format) {
			return super.toString(true) + String.format(" %-8.2f %-8.2f %-12.2f %.2f\n", this.bonus, this.fine, this.baseSalary, this.getSalary());
		} else {
			return super.toString(false) + String.format("|%.2f|%.2f|%.2f", this.bonus, this.fine, this.baseSalary);
		}
	}

	public String toString() {
		return super.toString() + String.format("Bonus: %.2f\nFines: %.2f\nBase Salary %.2f\n", getBonus(), getFine(), getBaseSalary());
	}
}