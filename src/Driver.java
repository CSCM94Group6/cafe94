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

}
