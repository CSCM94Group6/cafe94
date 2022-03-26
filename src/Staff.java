import java.util.ArrayList;

public class Staff {
    private String firstName;
    private String lastName;
    private int id;
    private final int hoursToWork;
    private int totalHoursWorked;

    public Staff(String firstName, String lastName, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.hoursToWork = 40;
        this.totalHoursWorked = 0;
    }

    public Staff(){
        this.hoursToWork = 40;
    }

    public int getTotalHoursWorked() {
        return totalHoursWorked;
    }

    public void setTotalHoursWorked(int totalHoursWorked) {
        this.totalHoursWorked = totalHoursWorked;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int hoursRemaining(){
        return hoursToWork - totalHoursWorked;
    }
    public String toString() {
        return String.format("First Name:" + firstName + "Last Name:" + lastName
                + "Staff ID:" + id);
    }

    public void approveBooking(){}

    public void approveDelivery(){}

    public boolean isWaiter(){
        return false;
    }

    public boolean isDriver(){
        return false;
    }

}

/**
 * Waiter - Select Booking, Orders, delivery
 * Booking - Show pending approvals, approve booking, notify manager
 * Order - view (show) orders, new order
 * New order - enter table, view menu item, select item and send order to chef
 * delivery - show pending approvals, approve delivery, notify manager and
 * delivery driver
 * 
 * Delivery driver - show pending delivery, mark as delivered
 * 
 */
