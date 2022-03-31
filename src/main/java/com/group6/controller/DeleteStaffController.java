package com.group6.controller;

import com.group6.cafe94gui.ManagerApplication;
import com.group6.models.MethodsAndStorage;
import com.group6.models.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteStaffController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    TextField id;
    @FXML
    TextArea feedback;

    public void backToManager(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(ManagerApplication.class.getResource("manager-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();;
    }

    public void deleteStaff(ActionEvent event) throws IOException {
        int userId = Integer.parseInt(id.getText());
        if (MethodsAndStorage.showStaff().containsKey(userId)){
            feedback.setText(String.format("you have removed:%n%s  ", MethodsAndStorage.showStaff().get(userId)));
            MethodsAndStorage.showStaff().remove(userId);
        }

    }
}
