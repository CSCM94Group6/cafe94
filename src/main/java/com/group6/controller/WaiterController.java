package com.group6.controller;

import com.group6.cafe94gui.StaffLoginApplication;
import com.group6.models.MethodsAndStorage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class WaiterController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private int uId;

    @FXML
    RadioButton hours;
    @FXML
    RadioButton bookings;
    @FXML
    RadioButton deliveries;
    @FXML
    RadioButton order;
    @FXML
    TextArea viewstuff;
    @FXML
    TextArea approvedStuff;
    @FXML
    TextField id;
    @FXML
    TextField selection;

    public void setId(int staff){
        uId = staff;
        id.setText(Integer.toString(uId));
    }


    public void backToStaffLogin(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(StaffLoginApplication.class.getResource("staff-login-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();;
    }

    public void takeAction(ActionEvent event) throws IOException {
       if(hours.isSelected()){
           viewstuff.setText(MethodsAndStorage.waiterStuff(uId, "", 2));
       }
       else if(bookings.isSelected()){
         viewstuff.setText(MethodsAndStorage.waiterStuff(uId, "", 3));
       }
       else if(order.isSelected()){
           viewstuff.setText(String.format("Select items below separated by commas:%n%s", MethodsAndStorage.showMenu()));
       }
       else if(deliveries.isSelected()){
           viewstuff.setText(MethodsAndStorage.waiterStuff(uId, "",4));
       }

    }

    public void approveAll(ActionEvent event) throws IOException {
       if (bookings.isSelected()){
           approvedStuff.setText(MethodsAndStorage.waiterStuff(uId, "",6));
       }
       else if (deliveries.isSelected()){
           approvedStuff.setText(MethodsAndStorage.waiterStuff(uId, "",5));
       }
    }

    public void placeOrder(ActionEvent event) throws IOException{
        int uId = Integer.parseInt(id.getText());
        String message = MethodsAndStorage.waiterStuff(uId, selection.getText(), 1);
        approvedStuff.setText(message);
    }
}
