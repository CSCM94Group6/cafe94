package com.group6.controller;

import com.group6.cafe94gui.AddCustomerApplication;
import com.group6.cafe94gui.BookingApplication;
import com.group6.cafe94gui.CustomerLoginApplication;
import com.group6.cafe94gui.OrderApplication;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class bookOrOrderController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public void switchToBook(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(BookingApplication.class.getResource("book-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();;
    }

    public void switchToOrder(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(OrderApplication.class.getResource("order-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();;
    }

    public void backToCustomerLogin(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(CustomerLoginApplication.class.getResource("customer-login-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();;
    }
}
