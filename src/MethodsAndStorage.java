import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MethodsAndStorage {
    private static final String YES_NO = "Add another? 1 = yes, 2 = no: ";
    private static final String ERROR = "Invalid input, please read the instructions carefully and try again: ";

    private static HashMap<Integer, Customer> customers = new HashMap<>();

    private static final Table TABLE_OF_TWO = new Table(2,2);
    private static final Table TABLE_OF_FOUR = new Table(4,4);
    private static final Table TABLE_OF_EIGHT = new Table(8,8);
    private static final Table TABLE_OF_TEN = new Table(10,10);
    private static final ArrayList<Table> TABLES = new ArrayList<>(Arrays.asList(TABLE_OF_TWO, TABLE_OF_FOUR, TABLE_OF_EIGHT, TABLE_OF_TEN));

    private static int keyCustomer = 1;
    private static int userId = 0;

    public static void addCustomer(){
        boolean finished = false;
        do {
            System.out.println("lets get you registered");
            customers.put(keyCustomer, UserInteract.addCustomer());
            customers.get(keyCustomer).setId(keyCustomer);
            keyCustomer++;
            int custOption = UserInteract.enterInteger(YES_NO);
            while (!UserInteract.optionRange(custOption,1,2)) {
                custOption = UserInteract.enterInteger(ERROR);
                UserInteract.optionRange(custOption,1,2);
            }
            if (custOption == 2) finished = true;
        } while(!finished);
    }

    public static HashMap<Integer, Customer> showCustomers(){
        return customers;
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
            booking.setCustId(userId);
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

}
