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

public class DriverController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    RadioButton hours;
    @FXML
    RadioButton delivery;
    @FXML
    TextArea feedback;
    @FXML
    TextField id;

    public void backToStaffLogin(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(StaffLoginApplication.class.getResource("staff-login-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();;
    }

    public void takeAction(ActionEvent event) throws IOException {
        int userId = Integer.parseInt(id.getText());
        if(hours.isSelected()){
            feedback.setText(MethodsAndStorage.driverStuff(userId, 2));
        }
        if(delivery.isSelected()){
            feedback.setText(MethodsAndStorage.driverStuff(userId, 1));
        }
    }
}
