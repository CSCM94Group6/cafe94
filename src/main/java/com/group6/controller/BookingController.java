package com.group6.controller;

import com.group6.cafe94gui.BookOrOrderApplication;
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

public class BookingController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private int uId;

    @FXML
    TextField date;
    @FXML
    TextField time;
    @FXML
    TextField guests;
    @FXML
    Label feedback;

    public void setUId(int customer){
        uId = customer;
    }

    public void backToBookOrOrder(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(BookOrOrderApplication.class.getResource("book-or-order-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();;
    }

    public void bookTable(ActionEvent event) throws IOException {
        String getDate = date.getText();
        String getTime = time.getText();
        int numOfGuests = Integer.parseInt(guests.getText());

        String message = MethodsAndStorage.makeBooking(getDate, getTime, numOfGuests, uId);

        feedback.setText(message);
    }
}
