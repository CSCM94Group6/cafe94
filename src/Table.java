import java.util.HashMap;
/**
 * Table class represents the tables available at cafe94 on any given day. the restaurant has a total seating
 * capacity of 110, and this is taken into account when determining restaurant availability. All tables along with
 * their seating capacities are accounted for.
 * @author Meg Symons
 * @version 1.0
 */
public class Table {
    private int seat;
    private int tableID;
    //we need a unique key for each booking. the key is a
    //Concatenation of the date and time. this ensures that
    //tables are not booked twice for the same time period
    private String keyBooking;
    private HashMap<String, Booking> bookings;

    public Table(int tableID, int seat) {
        this.tableID = tableID;
        this.seat = seat;
        this.keyBooking = "";
        this.bookings = new HashMap<>();
    }

    public int getNumberOfSeats() {
        return seat;
    }

    public void setNumberOfSeats(int seat) {
        this.seat = seat;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    /**
     * The method <code>isAvailable()</code> takes no arguments.it returns a boolean indicating if a
     * table is available to be booked.
     * @return false if a table is not available for the number of guests required, true otherwise.
     * */
    public boolean isAvailable(){
        if (seat == 2) return bookings.size() < 40;
        else if (seat == 4) return bookings.size() < 40;
        else if (seat == 8) return bookings.size() < 20;
        else if (seat == 10) return bookings.size() < 10;
        else return false;
    }

    public Booking getBooking(int m) {
        return bookings.get(m);
    }

    public HashMap<String, Booking> getBookings() {
        return bookings;
    }

    /**
     * The method <code>addBooking()</code> will store a booking in the database is the table required is available
     * on the day and time for the booking.
     * @param booking a booking that includes date, time, and number of guests.
     * */
    public void addBooking(Booking booking) {
        if (bookings.isEmpty() || seat == 10) {
            keyBooking = booking.getDate() + booking.getTime() + "a";
            bookings.put(keyBooking, booking);
        } else{
            String test1 = booking.getDate() + booking.getTime() + "b";
            String test2 = booking.getDate() + booking.getTime() + "c";
            String test3 = booking.getDate() + booking.getTime() + "d";
            if (seat == 2 || seat == 4) {
                if (bookings.containsKey(keyBooking) && !bookings.containsKey(test1)) bookings.put(test1, booking);
                else if (bookings.containsKey(test1) && !bookings.containsKey(test2)) bookings.put(test2, booking);
                else if (bookings.containsKey(test2) && !bookings.containsKey(test3)) bookings.put(test3, booking);
            }
            else if (seat == 8){
                if (bookings.containsKey(keyBooking) && !bookings.containsKey(test1)) bookings.put(test1, booking);
            }
        }
    }

    public String toString() {
        StringBuilder printOut = new StringBuilder();
        for(Booking b: bookings.values()){
            printOut.append(String.format("Customer Id: %S%n" +
                    "Date: %s%n" +
                    "Time: %s%n" +
                    "Table Id: %d%n", b.getCustId(),b.getDate(), b.getTime(), tableID));
        }
        return printOut.toString();
    }
}
