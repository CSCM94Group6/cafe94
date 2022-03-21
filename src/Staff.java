public class Staff {
    private String firstName;
    private String lastName;
    private int id;
    private String user_type;

    public Staff(String firstName, String lastName, int id, String user_type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.user_type = user_type;
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

    public void setLastName(String user_type) {
        this.user_type = user_type;
    }

    public String getUserType() {
        return user_type;
    }

    public void setUserType(String lastname) {
        this.lastName = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return String.format("First Name:" + firstName + "Last Name:" + lastName
                + "Staff ID:" + id + "User Type:" + user_type);
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
