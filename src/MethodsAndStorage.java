import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MethodsAndStorage {
    private static final String YES_NO = "Add another? 1 = yes, 2 = no: ";
    private static final String ERROR = "Invalid input, please read the instructions carefully and try again: ";
    private static final String SELECT_ITEMS = "Select your items by selecting the id,"
            + "for multiple items, separate id by commas: ";
    private static final String WAITER_ACTIONS = "Choose an option below:%n" +
            "1. place order%n" +
            "2. view my hours%n" +
            "3. approve bookings%n" +
            "4. approve deliveries%n";

    private static final int placeOrder = 1;
    private static final int viewHours = 2;
    private static final int approveBookings = 3;
    private static final int approveDelivery = 4;

    private static final HashMap<Integer, Customer> CUSTOMERS = new HashMap<>();
    private static final HashMap<Integer, Staff> STAFF = new HashMap<>();
    private static final ArrayList<Order> ORDERS = new ArrayList<Order>();

    private static final Menu ITEM_1 = new Menu("Rice", 5.50, 1);
    private static final Menu ITEM_2 = new Menu("Machboos", 6.50, 2);
    private static final Menu ITEM_3 = new Menu("Suki", 5.75, 3);
    private static final Menu ITEM_4 = new Menu("Steak", 8.50, 4);
    private static final Menu ITEM_5 = new Menu("Saksuka", 5.00, 5);
    private static final ArrayList<Menu> MAIN_MENU = new ArrayList<Menu>(Arrays.asList(
            ITEM_1, ITEM_2, ITEM_3, ITEM_4, ITEM_5));

    private static final Table TABLE_OF_TWO = new Table(2,2);
    private static final Table TABLE_OF_FOUR = new Table(4,4);
    private static final Table TABLE_OF_EIGHT = new Table(8,8);
    private static final Table TABLE_OF_TEN = new Table(10,10);
    private static final ArrayList<Table> TABLES = new ArrayList<>(Arrays.asList(
            TABLE_OF_TWO, TABLE_OF_FOUR, TABLE_OF_EIGHT, TABLE_OF_TEN));

    private static int key = 1;
    private static int customerId = 0;
    private static int staffId = 0;

    public static void addCustomer(){
        boolean finished = false;
        do {
            System.out.println("lets get you registered");
            while(key % 2 != 0) key++;
            CUSTOMERS.put(key, UserInteract.addCustomer());
            CUSTOMERS.get(key).setId(key);
            key++;
            int customerOption = UserInteract.enterInteger(YES_NO);
            while (!UserInteract.optionRange(customerOption,1,2)) {
                customerOption = UserInteract.enterInteger(ERROR);
                UserInteract.optionRange(customerOption,1,2);
            }
            if (customerOption == 2) finished = true;
        } while(!finished);
    }

    public static HashMap<Integer, Customer> showCustomers(){
        return CUSTOMERS;
    }

    public static void makeBooking(){
        boolean finBooking = false;
        do {
            Booking booking = UserInteract.bookAtable();
            String key1 = booking.getDate() + booking.getTime() + "a";
            String key2 = booking.getDate() + booking.getTime() + "b";
            String key3 = booking.getDate() + booking.getTime() + "c";
            String key4 = booking.getDate() + booking.getTime() + "d";
            boolean contains2 = TABLE_OF_TWO.getBookings().containsKey(key1)
                    && TABLE_OF_TWO.getBookings().containsKey(key2)
                    && TABLE_OF_TWO.getBookings().containsKey(key3)
                    && TABLE_OF_TWO.getBookings().containsKey(key4);
            boolean contains4 = TABLE_OF_FOUR.getBookings().containsKey(key1)
                    && TABLE_OF_FOUR.getBookings().containsKey(key2)
                    && TABLE_OF_FOUR.getBookings().containsKey(key3)
                    && TABLE_OF_FOUR.getBookings().containsKey(key4);
            boolean contains8 = TABLE_OF_EIGHT.getBookings().containsKey(key1)
                    && TABLE_OF_EIGHT.getBookings().containsKey(key2);
            boolean contains10 = TABLE_OF_EIGHT.getBookings().containsKey(key1);
            if (booking.getNumOfGuests() < 3){
                if(TABLE_OF_TWO.isAvailable() && !contains2) TABLE_OF_TWO.addBooking(booking);
                else if (TABLE_OF_FOUR.isAvailable() && !contains4) TABLE_OF_FOUR.addBooking(booking);
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
                        contains2 = TABLE_OF_TWO.getBookings().containsKey(key1)
                                && TABLE_OF_TWO.getBookings().containsKey(key2)
                                && TABLE_OF_TWO.getBookings().containsKey(key3)
                                && TABLE_OF_TWO.getBookings().containsKey(key4);
                    }
                    TABLE_OF_TWO.addBooking(booking);
                }
            }
            else if (booking .getNumOfGuests() > 2
                    && booking.getNumOfGuests() < 5) {
                if(TABLE_OF_FOUR.isAvailable() && !contains4) TABLE_OF_FOUR.addBooking(booking);
                else if(TABLE_OF_EIGHT.isAvailable() && !contains8) TABLE_OF_EIGHT.addBooking(booking);
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
                        contains4 = TABLE_OF_FOUR.getBookings().containsKey(key1)
                                && TABLE_OF_FOUR.getBookings().containsKey(key2)
                                && TABLE_OF_FOUR.getBookings().containsKey(key3)
                                && TABLE_OF_FOUR.getBookings().containsKey(key4);
                    }
                    TABLE_OF_FOUR.addBooking(booking);
                }
            }
            else if (booking.getNumOfGuests() > 4
                    && booking.getNumOfGuests() < 9){
                if(TABLE_OF_EIGHT.isAvailable() && !contains8) TABLE_OF_EIGHT.addBooking(booking);
                else if(TABLE_OF_TEN.isAvailable() && !contains10) TABLE_OF_TEN.addBooking(booking);
                else{
                    while (contains8) {
                        System.out.println("Please select another day or time:");
                        int dateI = UserInteract.enterInteger("Date(format 0101 for jan 1): ");
                        int timeI = UserInteract.enterInteger("Time (hours only between 08 an 18, and must be two digits): ");
                        booking.setDate(Integer.toString(dateI));
                        booking.setTime(Integer.toString(timeI));
                        key1 = booking.getDate() + booking.getTime() + "a";
                        key2 = booking.getDate() + booking.getTime() + "b";
                        contains8 = TABLE_OF_EIGHT.getBookings().containsKey(key1)
                                && TABLE_OF_EIGHT.getBookings().containsKey(key2);

                    }
                    TABLE_OF_EIGHT.addBooking(booking);
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
                    contains10 = TABLE_OF_TEN.getBookings().containsKey(key1);
                }
                TABLE_OF_TEN.addBooking(booking);
            } else {
                System.out.format("Sorry, no tables for %d guests, please reduce the number%n.",
                        booking.getNumOfGuests());
                finBooking = true;
                //continue;
            }
            //we add the customer id to the booking here
            booking.setCustId(customerId);
            //option to book more tables as required
            int moreBookings = UserInteract.enterInteger(YES_NO);
            while (!UserInteract.optionRange(moreBookings,1,2)) {
                moreBookings = UserInteract.enterInteger(ERROR);
                UserInteract.optionRange(moreBookings,1,2);
            }
            if (moreBookings == 2) {
                finBooking = true;
                //finished = true;
            }
        }while(!finBooking);
    }

    public static ArrayList<Table> showBookings(){
        return TABLES;
    }

    public static void placeOrder(int userId){
        boolean finOrder;
        do {
            Order order = new Order(userId);
            //Show the user all items on the menu
            int count = 0;
            System.out.println("Our menu Items: ");
            while (count < MAIN_MENU.size()) {
                System.out.println(count);
                System.out.print(MAIN_MENU.get(count));
                count ++;
            }
            //allow the user select multiple items from the menu,
            //add all the items selected to the users order
            String selection = UserInteract.enterString(SELECT_ITEMS);
            String[] items = selection.split(",");
            count = 0;
            while(count < items.length) {
                order.addToOrder(MAIN_MENU.get(Integer.parseInt(items[count])));
                count++;
            }
            //have the user select take-away or delivery
            if (userId % 2 == 0){
                int taOrDel = UserInteract.enterInteger("1 for Takeaway or 2 for delivery?: ");
                while (!UserInteract.optionRange(taOrDel,1,2)) {
                    taOrDel = UserInteract.enterInteger(ERROR);
                    UserInteract.optionRange(taOrDel,1,2);
                }
                if (taOrDel == 1) order.setType("Takeaway");
                else {
                    order.setType("Delivery");
                    order.setApproved(false);
                }
            }
            //add the order to the database
            ORDERS.add(order);
            System.out.println();
            finOrder = true;
            //finished = true;
        } while(!finOrder);
    }

    public static ArrayList<Order> showOrders(){
        return ORDERS;
    }

    public static HashMap<Integer, Staff> showStaff(){
        return STAFF;
    }

    public static void addStaff(){
        boolean finished = false;
        do {
            System.out.println("lets get you registered");
            while(key % 2 != 1) key++;
            STAFF.put(key, UserInteract.addStaff());
            STAFF.get(key).setId(key);
            key++;
            int staffOption = UserInteract.enterInteger(YES_NO);
            while (!UserInteract.optionRange(staffOption,1,2)) {
                staffOption = UserInteract.enterInteger(ERROR);
                UserInteract.optionRange(staffOption,1,2);
            }
            if (staffOption == 2) finished = true;
        } while(!finished);
    }

    public static Staff removeStaff(int StaffId){
        Staff staff = new Staff();
        if (STAFF.containsKey(staffId)) {
            staff = STAFF.remove(staffId);
        }
        return staff;
    }

    public static void waiterStuff(int id){
        Staff waiter = STAFF.get(id);
        System.out.printf("Hello %S, where do we start?%n", waiter.getFirstName());
        int waiterChoice = UserInteract.enterInteger(WAITER_ACTIONS);
        while (!UserInteract.optionRange(waiterChoice, 1, 4)){
            waiterChoice = UserInteract.enterInteger(ERROR);
            UserInteract.optionRange(waiterChoice, 1, 4);
        }
        if (waiterChoice == placeOrder) placeOrder(waiter.getId());
        if (waiterChoice == viewHours) System.out.printf("you have %dhrs remaining.%n%n ", waiter.hoursRemaining());
        if (waiterChoice == approveBookings) {
            showBookings();
            int yesOrN0 = UserInteract.enterInteger("Would you like to approve these bookings:%n" +
                    "Enter 1. yes, or 2.no");
            while(!UserInteract.optionRange(yesOrN0, 1, 2)){
                yesOrN0 = UserInteract.enterInteger(ERROR);
                UserInteract.optionRange(yesOrN0,1,2);
            }
            if(yesOrN0 == 1) waiter.approveBooking();
        }
        if (waiterChoice == approveDelivery){
            for (Order o: ORDERS){
                if (o.getType().equalsIgnoreCase("delivery")){
                    System.out.println(o);
                }
            }
            int yesOrN0 = UserInteract.enterInteger("Would you like to approve these deliveries:%n" +
                    "Enter 1. yes, or 2.no");
            while(!UserInteract.optionRange(yesOrN0, 1, 2)){
                yesOrN0 = UserInteract.enterInteger(ERROR);
                UserInteract.optionRange(yesOrN0,1,2);
            }
            if(yesOrN0 == 1) waiter.approveDelivery();
        }
    }

}
