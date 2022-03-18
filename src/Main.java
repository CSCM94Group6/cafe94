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
		HashMap<Integer, Customer> customers = new HashMap<>();
		
		//Sample menu items for a customer to select from
		Menu item1 = new Menu("Rice", 5.50, 1);
		Menu item2 = new Menu("Machboos", 6.50, 2);
		Menu item3 = new Menu("Suki", 5.75, 3);
		Menu item4 = new Menu("Steak", 8.50, 4);
		Menu item5 = new Menu("Saksuka", 5.00, 5);
		
		//The main menu contains all food items and can be viewed by all customers
		ArrayList<Menu> mainMenu = new ArrayList<Menu>(Arrays.asList(item1, item2, item3, item4, item5));
		
		//Constants to be used repeatedly throughtout the program
		//(NB: should be final)
		String action = "1 to Book or 2 to Order";
		String enterId = "Enter your user id: ";
		String register = "1 = new user, 2 = registered user, 3 = exit: ";
		String error = "Invalid input, please read the instructions carefully and try again: ";
		String yesNo = "Add another? 1 = yes, 2 = no: ";
		String selectItems = "Select your items by selecting the id,"
				+ "for multiple items, separate id by commas: ";
		//the keys(customer and booking) are use to keep
		//track of all customers and bookings in the database.
		int keyCustomer = 1;
		String keyBooking = "";
		int userId = 0;
		boolean exit = false;
		
		//The main body of the program starts here. All other 
		//sub-functionalities are contained here
		do {
			/**
			 * this displays the main menu options and asks for an input. refer to the
			 * enterInteger() method below the main method for a description of
			 * enterInteger().
			 */
			int mainOption = enterInteger(register);

			/**
			 * This while loop ensures the main menu option selection is within the range 1
			 * and 2, corresponding to the options displayed on the console. refer to the
			 * optionRange() method below the main method for a description of
			 * optionRange().
			 */
			while (!optionRange(mainOption,1,3)) {
				mainOption = enterInteger(error);
				optionRange(mainOption,1,3);
			}
			
			//if the user is not a registered customer, this section registers the user, 
			//and stores in the database. (NB all magic numbers will be changed to
			//final variables)
			if (mainOption == 1) {
				boolean finished = false;
				do {
					System.out.println("lets get you registered");
					customers.put(keyCustomer, addCustomer());
					customers.get(keyCustomer).setId(keyCustomer);
					keyCustomer++;
					int custOption = enterInteger(yesNo);
					while (!optionRange(custOption,1,2)) {
						custOption = enterInteger(error);
						optionRange(custOption,1,2);
					}
					if (custOption == 2) finished = true;
				} while(!finished);
				
				//if the user is a registered customer, then proceed with either 
				//booking or placing an order
			} else if (mainOption == 2){
				boolean finished = false;
				do {
					//user id keeps track of who is making the booking or 
					//placing the order 
					userId = enterInteger(enterId);
					boolean valid = customers.containsKey(userId);
					while (!valid) {
						userId = enterInteger("This user does not exist, enter a valid Id or press 0 to register: ");
						if (userId == 0) {
							valid = true;
						}
					}
					if (userId == 0) {
						finished = true;
						continue;
					}
					//ask the user to either make a booking, or place an order
					int bookOrOrder = enterInteger(action);
					while (!optionRange(bookOrOrder,1,2)) {
						bookOrOrder = enterInteger(error);
						optionRange(bookOrOrder,1,2);
					}
					//if the user chooses to make a booking, we execute the block of 
					//code below
					if (bookOrOrder == 1) {
						boolean finBooking = false;
						do {
							Booking booking = bookAtable();
							//we need a unique key for each booking. the key is a
							//Concatenation of the date and time. this ensures that
							//tables are not booked twice for the same time period
							keyBooking = booking.getDate() + booking.getTime();
							//next, we store the booking
							if (bookings.isEmpty()) bookings.put(keyBooking, booking);
							else {
								while (bookings.containsKey(keyBooking)) {
									System.out.println("Please select another day or time:");
									int dateI = enterInteger("Date(format 0101 for jan 1): ");
									int timeI = enterInteger("Time (hours only between 08 an 18, and must be two digits): ");
									booking.setDate(Integer.toString(dateI));
									booking.setTime(Integer.toString(timeI)); 
									keyBooking = booking.getDate() + booking.getTime();
								}
								bookings.put(keyBooking, booking);
							}
							//we add the customer id to the booking here
							bookings.get(keyBooking).setCustId(userId);
							//option to book more tables as required
							int moreBookings = enterInteger(yesNo);
							while (!optionRange(moreBookings,1,2)) {
								moreBookings = enterInteger(error);
								optionRange(moreBookings,1,2);
							}
							if (moreBookings == 2) {
								finBooking = true;
								finished = true;
							}
						}while(!finBooking);
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
							String selection = enterString(selectItems);
							String[] items = selection.split(",");
							count = 0;
							while(count < items.length) {
								order.addToOrder(mainMenu.get(Integer.parseInt(items[count])));
								count++;
							}
							//have the user select take-away or delivery
							int taOrDel = enterInteger("1 for Takeaway or 2 for delivery?: ");
							while (!optionRange(taOrDel,1,2)) {
								taOrDel = enterInteger(error);
								optionRange(taOrDel,1,2);
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
			for (Customer c : customers.values()) {
				System.out.println(c);
			}
			
			//display all orders in the database
			for (Order o: orders) {
				String name = customers.get(o.getCustId()).getFirstName(); 
				System.out.format("Customer name: %S%n", name);
				System.out.print(o);
			}
			
			//display all bookings in the database
			for (Booking b : bookings.values()) {
				String name = customers.get(b.getCustId()).getFirstName();
				System.out.format("Customer Name: %S%n", name);
				System.out.print(b);
			}
		} while (!exit);
	}
	
	/**
	 * The bookAtable() method is used to add bookings to the database
	 * It collects the required data for a booking object
	 */
	static Booking bookAtable() {
		int dateI = enterInteger("Date(format DDMM): ");
		int timeI = enterInteger("Time (hours only between 08 an 18, and must be two digits): ");
		int guests = enterInteger("Number of guests(max 10): ");
		String date = Integer.toString(dateI);
		String time = Integer.toString(timeI);
		return new Booking(guests, date, time);
	}
	
	/**
	 * the enterInteger() method takes a string as argument. It displays this string
	 * to the console as a message and requires the user to enter an integer. the
	 * input is checked to ensure that the correct data type is entered into the
	 * console. A loop will continue to run until the correct input is registered.
	 */
	static int enterInteger(String message) {
		Scanner in = new Scanner(System.in);
		System.out.printf(message);
		while (!in.hasNextInt()) {
			System.out.printf("Please enter a number and ensure " + "it is less than %d: ", Integer.MAX_VALUE);
			in.nextLine();
		}
		int selection = in.nextInt();
		System.out.println();
		return selection;
	}
	
	/**
	 * the enterString() method takes a string as argument. It displays this string
	 * to the console as a message and requires the user to enter a String. the
	 * input is checked to ensure that the correct data type is entered into the
	 * console. A loop will continue to run until the correct input is registered.
	 */
	static String enterString(String message) {
		Scanner in = new Scanner(System.in);
		System.out.printf(message);
		while (!in.hasNext()) {
			System.out.println("Please enter a valid name: ");
			in.nextLine();
		}
		String selection = in.nextLine().toLowerCase().trim();
		//System.out.println();
		return selection;
	}
	
	/**
	 * The optionRange() method returns a boolean. it checks to see if a given
	 * value(reference) is within a set range (start, end).
	 */
	static boolean optionRange(int reference, int start, int end) {
		boolean range = reference >= start && reference <= end;
		return range;
	}
	
	/**
	 * The addCustomer() method takes no argument, it asks the user to
	 * enter the first and last name. it then
	 * uses these inputs to create a Customer object.
	 */
	static Customer addCustomer() {
		String firstName = enterString("First Name: ");
		String lastName = enterString("Last Name: ");
		int id = 0;
		return new Customer(firstName, lastName, id);

	}
}
