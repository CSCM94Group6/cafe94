package com.group6.controller;

import com.group6.cafe94gui.CustomerLoginApplication;
import com.group6.cafe94gui.RestaurantApplication;

import com.group6.cafe94gui.StaffLoginApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RestaurantController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public void switchToStaffLogin(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(StaffLoginApplication.class.getResource("staff-login-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();;
    }

    public void switchToCustomerLogin(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(CustomerLoginApplication.class.getResource("customer-login-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();;
    }
}