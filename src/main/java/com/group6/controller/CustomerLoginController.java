package com.group6.controller;

import com.group6.cafe94gui.AddCustomerApplication;
import com.group6.cafe94gui.BookOrOrderApplication;
import com.group6.cafe94gui.RestaurantApplication;
import com.group6.cafe94gui.StaffLoginApplication;
import com.group6.models.MethodsAndStorage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerLoginController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    TextField id;
    @FXML
    Label feedback;

    public void switchToAddCustomer(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(AddCustomerApplication.class.getResource("add-customer-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();;
    }

    public void switchToBookOrOrder(ActionEvent event) throws IOException {
        int userId = Integer.parseInt(id.getText());
        if(MethodsAndStorage.showCustomers().containsKey(userId)){
            fxmlLoader = new FXMLLoader(BookOrOrderApplication.class.getResource("book-or-order-view.fxml"));
            Parent root = fxmlLoader.load();
            bookOrOrderController b = fxmlLoader.getController();
            b.setId(userId);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            feedback.setText("User does not exist, try again.");
        }

    }

    public void backToMain(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(RestaurantApplication.class.getResource("main-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();;
    }
}
