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
		HashMap<String, Booking> bookings = new HashMap<>();
		ArrayList<Order> orders = new ArrayList<Order>();
		//HashMap<Integer, Customer> customers = new HashMap<>();
		Table tableOfTwo = new Table(2,2);
		Table tableOfFour = new Table(4,4);
		Table tableOfEight = new Table(8,8);
		Table tableOfTen = new Table(10,10);

		//Sample menu items for a customer to select from
		Menu item1 = new Menu("Rice", 5.50, 1);
		Menu item2 = new Menu("Machboos", 6.50, 2);
		Menu item3 = new Menu("Suki", 5.75, 3);
		Menu item4 = new Menu("Steak", 8.50, 4);
		Menu item5 = new Menu("Saksuka", 5.00, 5);
		
		//The main menu contains all food items and can be viewed by all customers
		ArrayList<Menu> mainMenu = new ArrayList<Menu>(Arrays.asList(item1, item2, item3, item4, item5));
		ArrayList<Table> tables = new ArrayList<>(Arrays.asList(tableOfTwo, tableOfFour, tableOfEight, tableOfTen));
		
		//Constants to be used repeatedly throughtout the program
		//(NB: should be final) hi
		String action = "1 to Book or 2 to Order";
		String enterId = "Enter your user id: ";
		String register = "1 = new user, 2 = registered user, 3 = exit: ";
		String error = "Invalid input, please read the instructions carefully and try again: ";
		String yesNo = "Add another? 1 = yes, 2 = no: ";
		String selectItems = "Select your items by selecting the id,"
				+ "for multiple items, separate id by commas: ";
		String staffOrCustomer= "Choose 1 for Staff or 2 for Customer";
		//the keys(customer and booking) are use to keep
		//track of all customers and bookings in the database.
		int keyCustomer = 1;
		String keyBooking = "";
		int userId = 0;


		int staffCusChoice = UserInteract.enterInteger(staffOrCustomer);
		while (!UserInteract.optionRange(staffCusChoice,1,3)) {
			staffCusChoice = UserInteract.enterInteger(error);
			UserInteract.optionRange(staffCusChoice,1,3);
		}

		if (staffCusChoice == 1){
			boolean exit = false;
			do{

			} while(!exit);
		}
		//The main body of the program starts here. All other 
		//sub-functionalities are contained here

		if (staffCusChoice == 2){
			boolean exit = false;
			do {
				/**
				 * this displays the main menu options and asks for an input. refer to the
				 * enterInteger() method below the main method for a description of
				 * enterInteger().
				 */
				int mainOption = UserInteract.enterInteger(register);

				/**
				 * This while loop ensures the main menu option selection is within the range 1
				 * and 2, corresponding to the options displayed on the console. refer to the
				 * optionRange() method below the main method for a description of
				 * optionRange().
				 */
				while (!UserInteract.optionRange(mainOption,1,3)) {
					mainOption = UserInteract.enterInteger(error);
					UserInteract.optionRange(mainOption,1,3);
				}

				//if the user is not a registered customer, this section registers the user,
				//and stores in the database. (NB all magic numbers will be changed to
				//final variables)
				if (mainOption == 1) {
					/*boolean finished = false;
					do {
						System.out.println("lets get you registered");
						customers.put(keyCustomer, UserInteract.addCustomer());
						customers.get(keyCustomer).setId(keyCustomer);
						keyCustomer++;
						int custOption = UserInteract.enterInteger(yesNo);
						while (!UserInteract.optionRange(custOption,1,2)) {
							custOption = UserInteract.enterInteger(error);
							UserInteract.optionRange(custOption,1,2);
						}
						if (custOption == 2) finished = true;
					} while(!finished);*/
					MethodsAndStorage.addCustomer();
					//if the user is a registered customer, then proceed with either
					//booking or placing an order
				} else if (mainOption == 2){
					boolean finished = false;
					do {
						//user id keeps track of who is making the booking or
						//placing the order
						userId = UserInteract.enterInteger(enterId);
						boolean valid = MethodsAndStorage.showCustomers().containsKey(userId);
						while (!valid) {
							userId = UserInteract.enterInteger("This user does not exist, enter a valid Id or press 0 to register: ");
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
						while (!UserInteract.optionRange(bookOrOrder,1,2)) {
							bookOrOrder = UserInteract.enterInteger(error);
							UserInteract.optionRange(bookOrOrder,1,2);
						}
						//if the user chooses to make a booking, we execute the block of
						//code below
						if (bookOrOrder == 1) {
							/*boolean finBooking = false;
							do {
								Booking booking = UserInteract.bookAtable();
								String key1 = booking.getDate() + booking.getTime() + "a";
								String key2 = booking.getDate() + booking.getTime() + "b";
								String key3 = booking.getDate() + booking.getTime() + "c";
								String key4 = booking.getDate() + booking.getTime() + "d";
								boolean contains2 = tableOfTwo.getBookings().containsKey(key1)
										&& tableOfTwo.getBookings().containsKey(key2)
										&& tableOfTwo.getBookings().containsKey(key3)
										&& tableOfTwo.getBookings().containsKey(key4);
								boolean contains4 = tableOfFour.getBookings().containsKey(key1)
										&& tableOfFour.getBookings().containsKey(key2)
										&& tableOfFour.getBookings().containsKey(key3)
										&& tableOfFour.getBookings().containsKey(key4);
								boolean contains8 = tableOfEight.getBookings().containsKey(key1)
										&& tableOfEight.getBookings().containsKey(key2);
								boolean contains10 = tableOfEight.getBookings().containsKey(key1);
								if (booking.getNumOfGuests() < 3){
									if(tableOfTwo.isAvailable() && !contains2) tableOfTwo.addBooking(booking);
									else if (tableOfFour.isAvailable() && !contains4) tableOfFour.addBooking(booking);
									else{
										while (contains2) {
											System.out.println("Please select another day or time:");
											int dateI = UserInteract.enterInteger("Date(format 0101 for jan 1): ");
											int timeI = UserInteract.enterInteger("Time (hours only between 08 an 18, and must be two digits): ");
											booking.setDate(Integer.toString(dateI));
											booking.setTime(Integer.toString(timeI));
											key1 = booking.getDate() + booking.getTime() + "a";
											key2 = booking.getDate() + booking.getTime() + "b";
											key3 = booking.getDate() + booking.getTime() + "c";
											key4 = booking.getDate() + booking.getTime() + "d";
											contains2 = tableOfTwo.getBookings().containsKey(key1)
													&& tableOfTwo.getBookings().containsKey(key2)
													&& tableOfTwo.getBookings().containsKey(key3)
													&& tableOfTwo.getBookings().containsKey(key4);
										}
										tableOfTwo.addBooking(booking);
									}
								}
								else if (booking .getNumOfGuests() > 2
										&& booking.getNumOfGuests() < 5) {
									if(tableOfFour.isAvailable() && !contains4) tableOfFour.addBooking(booking);
									else if(tableOfEight.isAvailable() && !contains8) tableOfEight.addBooking(booking);
									else{
										while (contains4) {
											System.out.println("Please select another day or time:");
											int dateI = UserInteract.enterInteger("Date(format 0101 for jan 1): ");
											int timeI = UserInteract.enterInteger("Time (hours only between 08 an 18, and must be two digits): ");
											booking.setDate(Integer.toString(dateI));
											booking.setTime(Integer.toString(timeI));
											key1 = booking.getDate() + booking.getTime() + "a";
											key2 = booking.getDate() + booking.getTime() + "b";
											key3 = booking.getDate() + booking.getTime() + "c";
											key4 = booking.getDate() + booking.getTime() + "d";
											contains4 = tableOfFour.getBookings().containsKey(key1)
													&& tableOfFour.getBookings().containsKey(key2)
													&& tableOfFour.getBookings().containsKey(key3)
													&& tableOfFour.getBookings().containsKey(key4);
										}
										tableOfFour.addBooking(booking);
									}
								}
								else if (booking.getNumOfGuests() > 4
										&& booking.getNumOfGuests() < 9){
									if(tableOfEight.isAvailable() && !contains8) tableOfEight.addBooking(booking);
									else if(tableOfTen.isAvailable() && !contains10) tableOfTen.addBooking(booking);
									else{
										while (contains8) {
											System.out.println("Please select another day or time:");
											int dateI = UserInteract.enterInteger("Date(format 0101 for jan 1): ");
											int timeI = UserInteract.enterInteger("Time (hours only between 08 an 18, and must be two digits): ");
											booking.setDate(Integer.toString(dateI));
											booking.setTime(Integer.toString(timeI));
											key1 = booking.getDate() + booking.getTime() + "a";
											key2 = booking.getDate() + booking.getTime() + "b";
											contains8 = tableOfEight.getBookings().containsKey(key1)
													&& tableOfEight.getBookings().containsKey(key2);

										}
										tableOfEight.addBooking(booking);
									}
								}
								else if (booking.getNumOfGuests() == 9
										|| booking.getNumOfGuests() == 10){
									while (contains10) {
										System.out.println("Please select another day or time:");
										int dateI = UserInteract.enterInteger("Date(format 0101 for jan 1): ");
										int timeI = UserInteract.enterInteger("Time (hours only between 08 an 18, and must be two digits): ");
										booking.setDate(Integer.toString(dateI));
										booking.setTime(Integer.toString(timeI));
										key1 = booking.getDate() + booking.getTime() + "a";
										contains10 = tableOfTen.getBookings().containsKey(key1);
									}
									tableOfTen.addBooking(booking);
								} else {
									System.out.format("Sorry, no tables for %d guests, please reduce the number.",
											booking.getNumOfGuests());
									finBooking = true;
									continue;
								}
								//we add the customer id to the booking here
								booking.setCustId(userId);
								//option to book more tables as required
								int moreBookings = UserInteract.enterInteger(yesNo);
								while (!UserInteract.optionRange(moreBookings,1,2)) {
									moreBookings = UserInteract.enterInteger(error);
									UserInteract.optionRange(moreBookings,1,2);
								}
								if (moreBookings == 2) {
									finBooking = true;
									finished = true;
								}
							}while(!finBooking);*/
							MethodsAndStorage.makeBooking();
							finished = true;
						}

						//if the user wants to place an order, the following block of code
						//is executed.
						else if (bookOrOrder == 2) {
							boolean finOrder = false;
							do {
								Order order = new Order(userId);
								//Show the user all items on the menu
								int count = 0;
								System.out.println("Our menu Items: ");
								while (count < mainMenu.size()) {
									System.out.println(count);
									System.out.print(mainMenu.get(count));
									count ++;
								}
								//allow the user select multiple items from the menu,
								//add all the items selected to the users order
								String selection = UserInteract.enterString(selectItems);
								String[] items = selection.split(",");
								count = 0;
								while(count < items.length) {
									order.addToOrder(mainMenu.get(Integer.parseInt(items[count])));
									count++;
								}
								//have the user select take-away or delivery
								int taOrDel = UserInteract.enterInteger("1 for Takeaway or 2 for delivery?: ");
								while (!UserInteract.optionRange(taOrDel,1,2)) {
									taOrDel = UserInteract.enterInteger(error);
									UserInteract.optionRange(taOrDel,1,2);
								}
								if (taOrDel == 1) order.setType("Takeaway");
								else order.setType("Delivery");
								//add the order to the database
								orders.add(order);
								System.out.println();
								finOrder = true;
								finished = true;
							}while(!finOrder);
						}
					}while(!finished);
				} else exit = true;

				//display all customers in the database
				for (Customer c : MethodsAndStorage.showCustomers().values()) {
					System.out.println(c);
				}

				//display all orders in the database
				for (Order o: orders) {
					String name = MethodsAndStorage.showCustomers().get(o.getCustId()).getFirstName();
					System.out.format("Customer name: %S%n", name);
					System.out.print(o);
				}

				//display all bookings in the database
				for (Table t : MethodsAndStorage.showBookings()) {
					System.out.print(t);
				}
			} while (!exit);
		}

	}

}
