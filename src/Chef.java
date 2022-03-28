import javafx.fxml.FXML;
import java.util.objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.IOException;


/**
 * the chef class extends the staff class(superclass). it extends implements all the functions of the staff class,
 * and has only one method that indicates to the entire application that it is a chef.
 * @author purit jessadakannasoon
 * @version 1.0
 */
public class Chef extends Staff{
    private boolean isChef;
    
    @FXML
    public button HomeButton
    public     

    /**
     * @param firstName First name of chef.
     * @param lastName Last name of chef.
     * @param id is an automatically generated staff id.
     */
    public Chef(String firstName, String lastName, int id){
        super();
        super.setFirstName(firstName);
        super.setLastName(lastName);
        this.isChef = true;
    }

    /**
     * the <code>isChef()</code> method indicates to the application that object is a chef.
     */
    @Override
    public boolean isChef(){
        return isChef;
    }
}
