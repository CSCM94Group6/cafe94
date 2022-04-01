package com.group6.controller;

import com.group6.cafe94gui.*;
import com.group6.models.MethodsAndStorage;
import com.group6.models.Waiter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class staffLoginController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    TextField id;

    public void backToMain(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(RestaurantApplication.class.getResource("main-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void enterStaff(ActionEvent event) throws IOException {
        int userId = Integer.parseInt(id.getText());
        if (MethodsAndStorage.showStaff().containsKey(userId)){
            if (MethodsAndStorage.showStaff().get(userId).isWaiter()){
                fxmlLoader = new FXMLLoader(WaiterApplication.class.getResource("waiter-view.fxml"));
                Parent root = fxmlLoader.load();
                WaiterController waiterController = fxmlLoader.getController();
                waiterController.setId(userId);
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else if (MethodsAndStorage.showStaff().get(userId).isDriver()){
                fxmlLoader = new FXMLLoader(DriverApplication.class.getResource("driver-view.fxml"));
                Parent root = fxmlLoader.load();
                DriverController driverController = fxmlLoader.getController();
                driverController.setId(userId);
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else if (MethodsAndStorage.showStaff().get(userId).isChef()){
                fxmlLoader = new FXMLLoader(ChefApplication.class.getResource("chef-view.fxml"));
                Parent root = fxmlLoader.load();
                ChefController chefController = fxmlLoader.getController();
                chefController.setId(userId);
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        else if(userId == 1234){
            fxmlLoader = new FXMLLoader(ManagerApplication.class.getResource("manager-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        }

    }


}
