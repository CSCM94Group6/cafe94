import java.util.ArrayList;

public class Waiter extends Staff{

    private  boolean isWaiter;

    public Waiter(String firstname, String lastName, int id){
        super();
        this.isWaiter = true;
    }

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

    @Override
    public void approveBooking(){
        for(Table t : MethodsAndStorage.showBookings()){
            for (Booking b: t.getBookings().values()){
                b.setApproved(true);
            }
        }
    }

    @Override
    public void approveDelivery(){
        for (Order o: MethodsAndStorage.showOrders()){
            if (o.getType().equalsIgnoreCase("delivery")){
                o.setApproved(true);
            }
        }
    }
}
