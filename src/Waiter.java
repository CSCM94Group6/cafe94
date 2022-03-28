import javafx.fxml.FXML;
import java.util.objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.IOException
import java.util.ArrayList;
/**
 * The waiter class is an extension of the staff class. It adds a few extra functions for the waiter. It has a method
 * that indicates to the application that it is a waiter, and methods to approve bookings and deliveries. two extra
 * methods, one to show bookings, and the other orders are also present but not used. these methods may be
 * deprecated in a future version.
 * @author Eylul Altun
 * @version 1.0
 */
public class Waiter extends Staff{

    private  boolean isWaiter;

    /**
     * @param firstName First name of waiter.
     * @param lastName Last name of waiter.
     * @param id is an automatically generated staff id.
     */
    public Waiter(String firstName, String lastName, int id){
        super();
        super.setFirstName(firstName);
        super.setLastName(lastName);
        this.isWaiter = true;
    }

    /**
     * The <code>isWaiter()</code> indicates to the application user is a waiter.
     */
    @Override
    public boolean isWaiter() {
        return isWaiter;
    }

    public ArrayList<Table> showBookings(){
        return MethodsAndStorage.showBookings();
    }

    public ArrayList<Order> showOrders(){
        return MethodsAndStorage.showOrders();
    }

    /**
     * The <code>approveBooking()</code> method takes no arguments. It changes the attribute
     * "approved" in the booking class from false to true.
     */
    @Override
    public void approveBooking(){
        for(Table t : MethodsAndStorage.showBookings()){
            for (Booking b: t.getBookings().values()){
                b.setApproved(true);
            }
        }
    }

    /**
     * The <code>approveDelivery()</code> method takes no arguments. It changes the attribute
     * "approved" in the order class for deliveries from false to true.
     */
    @Override
    public void approveDelivery(){
        for (Order o: MethodsAndStorage.showOrders()){
            if (o.getType().equalsIgnoreCase("delivery")){
                o.setApproved(true);
            }
        }
    }
}
