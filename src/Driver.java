/**
 * the driver class extends the staff class(superclass). it extends all the functions of the staff class,
 * and has only one method that indicates to the entire application that it is a driver.
 * @author pj
 * @version 1.0
 */
public class Driver extends Staff{
    private boolean isDriver;

    /**
     * @param firstName First name of driver.
     * @param lastname Last name of driver.
     * @param id is an automatically generated staff id.
     */
    public Driver(String firstName, String lastname, int id){
        super();
        super.setFirstName(firstName);
        super.setLastName(lastname);
        this.isDriver = true;
    }

    /**
     * the <code>isDriver()</code> indicates to the application that object is a driver.
     */
    @Override
    public boolean isDriver(){
        return isDriver;
    }

}
