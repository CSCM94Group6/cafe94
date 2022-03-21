import java.util.HashMap;
/**
* Table class
* @author meg
*/
public class Table {
    private int seat;
    private int tableID;
    //we need a unique key for each booking. the key is a
    //Concatenation of the date and time. this ensures that
    //tables are not booked twice for the same time period
    private String keyBooking;
    //private ArrayList<Booking> bookings;
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
