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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    TextField items;
    @FXML
    TextField userid;
    @FXML
    Label feedback;
    @FXML
    TextArea menu;
    @FXML
    RadioButton delivery;
    @FXML
    RadioButton takeaway;

    public void backToBookOrOrder(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(BookOrOrderApplication.class.getResource("book-or-order-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();;
    }

    public void makeOrder(ActionEvent event) throws IOException {
        String selection = items.getText();
        int id = Integer.parseInt(userid.getText());
        String type = "";
        boolean approved = false;
        if(delivery.isSelected()){
            type = "delivery";
        }
        else if (takeaway.isSelected()){
            type = "Takeaway";
            approved = true;
        }

        String message = MethodsAndStorage.placeOrder(id, selection, type, approved);

        feedback.setText(message);
    }

    public void showMenu(ActionEvent event) throws IOException {
        menu.setText(MethodsAndStorage.showMenu());
    }
}
