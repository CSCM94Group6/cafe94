import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
/**
 * The main method brings all the classes together. Data is persisted here, and all 
 * class interactions take place here.
 */
public class Main {
	public static void main(String[] args) {
		//Constants to be used repeatedly throughout the program
		//(NB: should be final) hi
		String action = "1 to Book or 2 to Order";
		String enterId = "Enter your user id: ";
		String register = "1 = new user, 2 = registered user, 3 = exit: ";
		String error = "Invalid input, please read the instructions carefully and try again: ";
		String staffOrCustomer = "Choose 1 for Staff, 2 for Customer or 3 to exit: ";

		final int STAFF = 1;
		final int CUSTOMER = 2;
		final int NEW_USER = 1;
		final int REGISTERED_USER = 2;
		final int BOOK = 1;
		final int ORDER = 2;

		//the keys(customer and booking) are used to keep
		//track of all customers and bookings in the database.
		int userId = 0;

		boolean exit = false;
		do {
			int staffCusChoice = UserInteract.enterInteger(staffOrCustomer);
			while (!UserInteract.optionRange(staffCusChoice, 1, 3)) {
				staffCusChoice = UserInteract.enterInteger(error);
				UserInteract.optionRange(staffCusChoice, 1, 3);
			}

			if (staffCusChoice == STAFF) {
				boolean exitStaff = false;
				do {

				} while (!exitStaff);
			}
			//The main body of the program starts here. All other
			//sub-functionalities are contained here

			else if (staffCusChoice == CUSTOMER) {
				boolean exitCustomer = false;
				do {

					int customerOptions = UserInteract.enterInteger(register);

					while (!UserInteract.optionRange(customerOptions, 1, 3)) {
						customerOptions = UserInteract.enterInteger(error);
						UserInteract.optionRange(customerOptions, 1, 3);
					}

					//if the user is not a registered customer, this section registers the user,
					//and stores in the database. (NB all magic numbers will be changed to
					//final variables)
					if (customerOptions == NEW_USER) {
						MethodsAndStorage.addCustomer();
						//if the user is a registered customer, then proceed with either
						//booking or placing an order
					} else if (customerOptions == REGISTERED_USER) {
						boolean finished = false;
						do {
							//user id keeps track of who is making the booking or
							//placing the order
							userId = UserInteract.enterInteger(enterId);
							boolean valid = MethodsAndStorage.showCustomers().containsKey(userId);
							while (!valid) {
								userId = UserInteract.enterInteger("This user does not exist, " +
										"enter a valid Id or press 0 to register: ");
								if (userId == 0) {
									valid = true;
								}
							}
							if (userId == 0) {
								finished = true;
								continue;
							}
							//ask the user to either make a booking, or place an order
							int bookOrOrder = UserInteract.enterInteger(action);
							while (!UserInteract.optionRange(bookOrOrder, 1, 2)) {
								bookOrOrder = UserInteract.enterInteger(error);
								UserInteract.optionRange(bookOrOrder, 1, 2);
							}
							//if the user chooses to make a booking, we execute the block of
							//code below
							if (bookOrOrder == BOOK) {
								MethodsAndStorage.makeBooking();
								finished = true;
							}

							//if the user wants to place an order, the following block of code
							//is executed.
							else if (bookOrOrder == ORDER) {
								MethodsAndStorage.placeOrder(userId);
								finished = true;
							}
						} while (!finished);
					} else exitCustomer = true;

					//display all customers in the database
					for (Customer c : MethodsAndStorage.showCustomers().values()) {
						System.out.println(c);
					}

					//display all orders in the database
					for (Order o : MethodsAndStorage.showOrders()) {
						String name = MethodsAndStorage.showCustomers().get(o.getCustId()).getFirstName();
						System.out.format("Customer name: %S%n", name);
						System.out.print(o);
					}

					//display all bookings in the database
					for (Table t : MethodsAndStorage.showBookings()) {
						System.out.print(t);
					}
				} while (!exitCustomer);
			} else exit = true;

		} while (!exit);
	}
}

