/**
 * The main is the entry into the application. it has a console interface for staff, and another for the customer.
 * @author Yusuf Dauda.
 * @version 1.0
 */
public class Main {
	public static void main(String[] args) {
		//messages displayed to the console for users.
		final String ACTION = "1 to Book or 2 to Order: ";
		final String ENTER_ID = "Enter your user id: ";
		final String REGISTER = "1 = new user, 2 = registered user, 3 = exit: ";
		final String ERROR = "Invalid input, please read the instructions carefully and try again: ";
		final String STAFF_OR_CUSTOMER = "Choose 1 for Staff, 2 for Customer or 3 to exit: ";
		final String MANAGER_OPTIONS = "Where do we Start?%n" +
				"1. view bookings%n" +
				"2. view orders%n" +
				"3. view reports%n" +
				"4. view staff%n" +
				"5. add staff%n" +
				"6. remove staff%n-> ";

		//options selected by users to be used by the application.
		final int STAFF = 1;
		final int CUSTOMER = 2;
		final int NEW_USER = 1;
		final int REGISTERED_USER = 2;
		final int BOOK = 1;
		final int ORDER = 2;
		final int MANAGER_ID = 1234;
		final int VIEW_BOOKING = 1;
		final int VIEW_ORDER = 2;
		final int VIEW_REPORTS= 3;
		final int VIEW_STAFF = 4;
		final int ADD_STAFF = 5;
		final int REMOVE_STAFF = 6;

		//Customer and Staff id is used to keep track of who is using the application.
		int customerId = 0;
		int staffId = 0;

		//This is the main entry point for all users.
		boolean exit = false;
		do {
			//select if user is a staff or customer.
			int staffCusChoice = UserInteract.enterInteger(STAFF_OR_CUSTOMER);
			while (!UserInteract.optionRange(staffCusChoice, 1, 3)) {
				staffCusChoice = UserInteract.enterInteger(ERROR);
				UserInteract.optionRange(staffCusChoice, 1, 3);
			}

			//main entry point for staff
			if (staffCusChoice == STAFF) {
				boolean exitStaff = false;
				do {
					staffId = UserInteract.enterInteger(ENTER_ID);

					//manager id is 1234 by default this is the manager console entry point
					if (staffId == MANAGER_ID){
						System.out.println("Welcome manager!");
						int managerChoice = UserInteract.enterInteger(MANAGER_OPTIONS);
						while (!UserInteract.optionRange(managerChoice,1,6)) {
							managerChoice = UserInteract.enterInteger(ERROR);
							UserInteract.optionRange(managerChoice,1,6);
						}

						if(managerChoice == VIEW_BOOKING) {
							if(MethodsAndStorage.showBookings().isEmpty()) System.out.println("There are no bookings" +
									"in the database.");
							else for (Table t: MethodsAndStorage.showBookings()) System.out.println(t);
						}
						if(managerChoice == VIEW_ORDER) {
							if(MethodsAndStorage.showOrders().isEmpty()) System.out.println("There are no orders" +
									"in the database");
							else for (Order o: MethodsAndStorage.showOrders()) System.out.println(o);
						}
						if(managerChoice == VIEW_REPORTS)System.out.println("Coming soon!");
						if(managerChoice == VIEW_STAFF) {
							if(MethodsAndStorage.showStaff().isEmpty()) System.out.println("You have no Staff!");
							else for(Staff s: MethodsAndStorage.showStaff().values()) System.out.println(s);
						}
						if(managerChoice == ADD_STAFF)MethodsAndStorage.addStaff();
						if(managerChoice == REMOVE_STAFF){
							int id = UserInteract.enterInteger("please enter staff id: ");
							if(MethodsAndStorage.showStaff().containsKey(id)) MethodsAndStorage.removeStaff(id);
							else System.out.printf("The id %d does not exists in the database.", id);
						}
						exitStaff = true;

					} else {
						//This is the entry point for all other staff(non-manager)
						boolean valid = MethodsAndStorage.showStaff().containsKey(staffId);
						while (!valid) {
							staffId = UserInteract.enterInteger("This user does not exist, " +
									"enter a valid Id or press 0 to register: ");
							valid = MethodsAndStorage.showStaff().containsKey(staffId);
							if (staffId == 0) valid = true;
						}
						if (staffId == 0) {
							exitStaff = true;
							continue;
						}
						//if staff id is valid, execute code below
						if (valid) {
							//if staff is a waiter, execute code below.
							if (MethodsAndStorage.showStaff().get(staffId).isWaiter()){
								boolean exitWaiter = false;
								do{
									MethodsAndStorage.waiterStuff(staffId);
									int yesOrNo = UserInteract.enterInteger("Continue? 1. yes, 2. no: ");
									while(!UserInteract.optionRange(yesOrNo, 1, 2)){
										yesOrNo = UserInteract.enterInteger(ERROR);
										UserInteract.optionRange(yesOrNo,1,2);
									}
									if (yesOrNo == 2) exitWaiter = true;
								} while (!exitWaiter);

							}
							//if staff is a driver, execute code below.
							if (MethodsAndStorage.showStaff().get(staffId).isDriver()){
								boolean exitDriver = false;
								do{
									MethodsAndStorage.driverStuff(staffId);
									int yesOrNo = UserInteract.enterInteger("Continue? 1. yes, 2. no: ");
									while(!UserInteract.optionRange(yesOrNo, 1, 2)){
										yesOrNo = UserInteract.enterInteger(ERROR);
										UserInteract.optionRange(yesOrNo,1,2);
									}
									if (yesOrNo == 2) exitDriver = true;
								}while(!exitDriver);
							}
							//if staff is a chef, execute code below.
							if (MethodsAndStorage.showStaff().get(staffId).isChef()){
								boolean exitChef = false;
								do{
									MethodsAndStorage.chefStuff(staffId);
									int yesOrNo = UserInteract.enterInteger("Continue? 1. yes, 2. no: ");
									while(!UserInteract.optionRange(yesOrNo, 1, 2)){
										yesOrNo = UserInteract.enterInteger(ERROR);
										UserInteract.optionRange(yesOrNo,1,2);
									}
									if (yesOrNo == 2) exitChef = true;
								}while(!exitChef);
							}
						}
					}
				} while (!exitStaff);
			}

			//main entry point for customer
			else if (staffCusChoice == CUSTOMER) {
				boolean exitCustomer = false;
				do {

					int customerOptions = UserInteract.enterInteger(REGISTER);

					while (!UserInteract.optionRange(customerOptions, 1, 3)) {
						customerOptions = UserInteract.enterInteger(ERROR);
						UserInteract.optionRange(customerOptions, 1, 3);
					}

					//if the user is not a registered customer, this section registers the user,
					//and stores in the database.
					if (customerOptions == NEW_USER) {
						MethodsAndStorage.addCustomer();
						//if the user is a registered customer, then proceed with either
						//booking or placing an order
					} else if (customerOptions == REGISTERED_USER) {
						boolean finished = false;
						do {
							//customer id keeps track of who is making the booking or
							//placing the order
							customerId = UserInteract.enterInteger(ENTER_ID);
							boolean valid = MethodsAndStorage.showCustomers().containsKey(customerId);
							while (!valid) {
								customerId = UserInteract.enterInteger("This user does not exist, " +
										"enter a valid Id or press 0 to register: ");
								valid = MethodsAndStorage.showCustomers().containsKey(customerId);
								if (customerId == 0) {
									valid = true;
								}
							}
							if (customerId == 0) {
								finished = true;
								continue;
							}
							//ask the user to either make a booking, or place an order
							int bookOrOrder = UserInteract.enterInteger(ACTION);
							while (!UserInteract.optionRange(bookOrOrder, 1, 2)) {
								bookOrOrder = UserInteract.enterInteger(ERROR);
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
								MethodsAndStorage.placeOrder(customerId);
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
						String name = MethodsAndStorage.showCustomers().get(o.getUserId()).getFirstName();
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

