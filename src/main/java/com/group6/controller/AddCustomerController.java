package com.group6.controller;

import com.group6.models.MethodsAndStorage;
import com.group6.cafe94gui.CustomerLoginApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddCustomerController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    TextField firstName;
    @FXML
    TextField lastName;
    @FXML
    Label feedback;


    public void backToCustomerLogin(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(CustomerLoginApplication.class.getResource("customer-login-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();;
    }

    public void addCustomer(ActionEvent event) throws IOException {
        String fName = firstName.getText();
        String lName = lastName.getText();

        int id = MethodsAndStorage.addCustomer(fName, lName);

        feedback.setText("Successfully added, your ID is: " + id);
    }
}
