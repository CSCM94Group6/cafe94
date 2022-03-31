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

    public void backToStaffLogin(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(StaffLoginApplication.class.getResource("staff-login-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();;
    }

    public void performAction(ActionEvent event) throws IOException {
        int userId = Integer.parseInt(id.getText());
        if(hours.isSelected()){
            hoursLeft.setText(MethodsAndStorage.chefStuff(userId, 2,""));
        }
        if(orders.isSelected()){
            viewOrder.setText(MethodsAndStorage.chefStuff(userId, 1,""));
        }
        if(ready.isSelected()){
            markOrder.setText(MethodsAndStorage.chefStuff(userId, 3,""));
        }
    }

    public void markReady(ActionEvent event) throws IOException{
        int userId = Integer.parseInt(id.getText());
        feedback.setText(MethodsAndStorage.chefStuff(userId, 4, selection.getText()));
    }


}
