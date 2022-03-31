package com.group6.controller;

import com.group6.cafe94gui.CustomerLoginApplication;
import com.group6.cafe94gui.ManagerApplication;
import com.group6.models.MethodsAndStorage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddStaffController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    TextField firstName;
    @FXML
    TextField lastName;
    @FXML
    Label feedback;
    @FXML
    RadioButton waiter;
    @FXML
    RadioButton driver;
    @FXML
    RadioButton chef;


    public void backToManager(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(ManagerApplication.class.getResource("manager-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();;
    }

    public void addStaff(ActionEvent event) throws IOException {
        String fName = firstName.getText();
        String lName = lastName.getText();
        String type = "waiter";
        if (chef.isSelected()) type = "chef";
        else if (driver.isSelected()) type = "driver";

        int id = MethodsAndStorage.addStaff(fName, lName, type);

        feedback.setText("Successfully added, your ID is: " + id);
    }
}
