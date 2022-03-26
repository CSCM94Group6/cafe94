import java.util.ArrayList;

public class Driver extends Staff{
    private boolean isDriver;

    public Driver(String firstName, String lastname, int id){
        super();
        this.isDriver = true;
    }

    @Override
    public boolean isDriver(){
        return isDriver;
    }

    public ArrayList<Order> showApprovedOrders(){
        ArrayList<Order> approvedOrders = new ArrayList<>();
        for (Order o: MethodsAndStorage.showOrders()){
            if (o.isApproved()){
                approvedOrders.add(o);
            }
        }
        return approvedOrders;
    }


}
