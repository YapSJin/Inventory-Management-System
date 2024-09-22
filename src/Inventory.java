import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
	private static String filename = "../Inventory.txt";
	private ArrayList<Product> inventory = new ArrayList<>();

	public Inventory() {
		loadFromFile();
	}

	public ArrayList<Product> getInventory() {
		return inventory;
	}

	public void addProduct(Scanner scanner) {
		System.out.print("Enter product name: ");
		String prodName = scanner.nextLine();

		System.out.print("Enter quantity: ");
		int quantity = scanner.nextInt();

		System.out.print("Enter the retail price of product (xx.xx): RM");
		double retailPrice = scanner.nextDouble();

		System.out.print("Enter the wholesale price of product (xx.xx): RM");
		double wholesalePrice = scanner.nextDouble();

		int newID = inventory.isEmpty() ? 1 : inventory.get(inventory.size() - 1).getID() + 1;

		Product prod = new Product(newID, prodName, quantity, retailPrice, wholesalePrice);
		inventory.add(prod);
		saveToFile(prod);

		System.out.println("-----------------------------");
		System.out.println("Product successfully added!");
		System.out.println("-----------------------------");
	}

	public void restockProduct(Scanner scanner, int prodID) {
		Product toRestock = null;
		for (Product product : inventory) {
			if (product.getID() == prodID) {
				toRestock = product;
			}
		}

		if (toRestock != null) {
			System.out.println("Current stock for product ID " + toRestock.getName() + ": " + toRestock.getQuantity());

			System.out.print("Enter amount of product to buy: ");
			int addQuantity = Integer.parseInt(scanner.nextLine());

			double total = toRestock.getWholesalePrice() * addQuantity;
			String choice = "";
			do {
				System.out.printf("\nTotal is %.2f, proceed to payment? (y/n): ", total);
				choice = scanner.next();
				scanner.nextLine();
				switch (choice) {
					case "y":
						System.out.println("Payment successful!");
						int newQuantity = toRestock.getQuantity() + addQuantity;

						toRestock.setQuantity(newQuantity);
						updateFile();

						try (BufferedWriter writer = new BufferedWriter(new FileWriter("../Transaction.txt", true))) {
							writer.write(toRestock.toString("Restock", addQuantity, total));
							writer.newLine();
						} catch (IOException e) {
							System.out.println("An error occurred write writing to Inventory.txt");
							e.printStackTrace();
						}

						System.out.println("-------------------------------");
						System.out.println("Product successfully restocked!");
						System.out.println("-------------------------------");
						break;
					case "n":
						System.out.println("--------------------------");
						System.out.println("Product restock cancelled");
						System.out.println("--------------------------");
						break;
					default:
						System.out.println("Invalid option, please try again.");
						break;
				}
			} while (!choice.equals("y") && !choice.equals("n"));
		} else {
			System.out.println("Product not found");
		}
	}

	public void deleteProduct(Scanner scanner) {
		System.out.print("\nEnter product ID to delete (or 'cancel' to stop): ");
		String input = scanner.nextLine().trim();

		if (!input.equalsIgnoreCase("cancel")) {
			int prodID;
			try {
				prodID = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid product ID or 'cancel'.");
				return;
			}

			Product toRemove = null;
			for (Product product : inventory) {
				if (product.getID() == prodID) {
					toRemove = product;
					break;
				}
			}

			if (toRemove != null) {
				System.out.print("Are you sure you want to delete this product (ID: " + prodID + ")? (Y/N): ");
				char confirm = Character.toLowerCase(scanner.next().charAt(0));
				scanner.nextLine();

				if (confirm == 'y') {
					inventory.remove(toRemove);
					updateFile();

					System.out.println("-----------------------------");
					System.out.println("Product successfully deleted!");
					System.out.println("-----------------------------");
				} else {
					System.out.println("-----------------------------");
					System.out.println("Product deletion cancelled!");
					System.out.println("-----------------------------");
				}
			} else {
				System.out.println("Product with ID " + prodID + " not found.");
			}
		} else {
			System.out.println("-----------------------------");
			System.out.println("Product deletion cancelled!");
			System.out.println("-----------------------------");
		}
	}

	public void editProduct(Scanner scanner) {
		System.out.print("\nEnter product ID to update (0 to cancel): ");
		int id = Integer.parseInt(scanner.nextLine());

		if (id != 0) {
			Product toUpdate = null;
			for (Product product : inventory) {
				if (product.getID() == id) {
					toUpdate = product;
					break;
				}
			}

			if (toUpdate != null) {
				boolean finished = false;
				do {
					System.out.print("\nEnter field to update (name/quantity/retail/wholesale) ('done' to confirm): ");
					String field = scanner.nextLine().toLowerCase();

					switch (field) {
						case "name":
							System.out.print("Enter new name: ");
							String name = scanner.nextLine();
							toUpdate.setName(name);
							break;
						case "quantity":
							System.out.print("Enter new quantity: ");
							int quantity = Integer.parseInt(scanner.nextLine());
							toUpdate.setQuantity(quantity);
							break;
						case "retail":
							System.out.print("Enter new retail price: ");
							double price = Double.parseDouble(scanner.nextLine());
							toUpdate.setRetailPrice(price);
							break;
						case "wholesale":
							System.out.print("Enter new wholesale price: ");
							double wholesalePrice = Double.parseDouble(scanner.nextLine());
							toUpdate.setWholesalePrice(wholesalePrice);
							break;
						case "done":
							finished = true;
							break;
						default:
							System.out.println("Invalid option, please try again.");
					}
				} while (!finished);

				updateFile();

				System.out.println("-----------------------------");
				System.out.println("Product successfully updated!");
				System.out.println("-----------------------------");
			} else {
				System.out.println("Product with ID " + id + " not found.");
			}
		} else {
			System.out.println("-------------------------");
			System.out.println("Product update cancelled!");
			System.out.println("-------------------------");
		}
	}

	public void displayAll() {
		System.out.printf("\n%-3s %-20s %-10s %-10s %s\n", "ID", "ProdName", "Quantity", "Retail", "Wholesale");
		for (Product product : inventory) {
			System.out.println(product.toString(true));
		}
	}

	public Product getProductById(int id) {
		for (Product product : inventory) {
			if (product.getID() == id) {
				return product;
			}
		}
		return null;
	}

	private void loadFromFile() {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] productData = line.split("\\|");

				int id = Integer.parseInt(productData[0]);
				String name = productData[1];
				int quantity = Integer.parseInt(productData[2]);
				double retailPrice = Double.parseDouble(productData[3]);
				double wholesalePrice = Double.parseDouble(productData[4]);

				Product product = new Product(id, name, quantity, retailPrice, wholesalePrice);
				inventory.add(product);
			}
		} catch (IOException e) {
			System.out.println("An error occurred while reading from Inventory.txt");
			e.printStackTrace();
		}
	}

	private void saveToFile(Product product) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
			writer.write(product.toString(false));
			writer.newLine();
		} catch (IOException e) {
			System.out.println("An error occurred write writing to Inventory.txt");
			e.printStackTrace();
		}
	}

	private void updateFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
			for (Product product : inventory) {
				writer.write(product.toString(false));
				writer.newLine();
			}
		} catch (Exception e) {
			System.out.println("An error occurred write writing to Inventory.txt");
			e.printStackTrace();
		}
	}
}
