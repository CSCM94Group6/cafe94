package com.group6.cafe94gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerLoginApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RestaurantApplication.class.getResource("customer-login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello Customer!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}