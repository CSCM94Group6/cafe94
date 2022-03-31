package com.group6.controller;

import com.group6.cafe94gui.AddStaffApplication;
import com.group6.cafe94gui.DeleteStaffApplication;
import com.group6.cafe94gui.StaffLoginApplication;
import com.group6.models.MethodsAndStorage;
import com.group6.models.Order;
import com.group6.models.Staff;
import com.group6.models.Table;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    RadioButton orders;
    @FXML
    RadioButton viewstaff;
    @FXML
    RadioButton addstaff;
    @FXML
    RadioButton deletestaff;
    @FXML
    RadioButton reports;
    @FXML
    RadioButton booking;
    @FXML
    TextArea display;

    public void backToStaffLogin(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(StaffLoginApplication.class.getResource("staff-login-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();;
    }

    public void takeAction(ActionEvent event) throws IOException {
        if(addstaff.isSelected()){
            fxmlLoader = new FXMLLoader(AddStaffApplication.class.getResource("add-staff-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();;
        }
        else if (deletestaff.isSelected()){
            fxmlLoader = new FXMLLoader(DeleteStaffApplication.class.getResource("delete-staff-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();;
        }
        else if (viewstaff.isSelected()){
            StringBuilder staff = new StringBuilder();
            for (Staff s: MethodsAndStorage.showStaff().values()){
                staff.append(s);
            }
            display.setText(String.valueOf(staff));
        }
        else if (orders.isSelected()){
            StringBuilder orders = new StringBuilder();
            for (Order o: MethodsAndStorage.showOrders()){
                orders.append(o);
            }
            display.setText(String.valueOf(orders));
        }
        else if (booking.isSelected()){
            StringBuilder booking = new StringBuilder();
            for (Table t: MethodsAndStorage.showBookings()){
                booking.append(t);
            }
            display.setText(String.valueOf(booking));
        }
        else if (reports.isSelected()){
            display.setText("Coming Soon!");
        }

    }


}
