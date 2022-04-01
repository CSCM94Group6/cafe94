package com.group6.controller;

import com.group6.cafe94gui.StaffLoginApplication;
import com.group6.models.MethodsAndStorage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ChefController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private int uId;

    @FXML
    RadioButton hours;
    @FXML
    RadioButton orders;
    @FXML
    RadioButton ready;
    @FXML
    TextField id;
    @FXML
    Label hoursLeft;
    @FXML
    Label feedback;
    @FXML
    TextArea viewOrder;
    @FXML
    TextArea markOrder;
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

    public void performAction(ActionEvent event) throws IOException {
        if(hours.isSelected()){
            hoursLeft.setText(MethodsAndStorage.chefStuff(uId, 2,""));
        }
        if(orders.isSelected()){
            viewOrder.setText(MethodsAndStorage.chefStuff(uId, 1,""));
        }
        if(ready.isSelected()){
            markOrder.setText(MethodsAndStorage.chefStuff(uId, 3,""));
        }
    }

    public void markReady(ActionEvent event) throws IOException{
        feedback.setText(MethodsAndStorage.chefStuff(uId, 4, selection.getText()));
    }


}
