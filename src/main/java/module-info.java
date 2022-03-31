module com.group6.cafe94gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.group6.cafe94gui to javafx.fxml;
    exports com.group6.cafe94gui;
    exports com.group6.controller;
    opens com.group6.controller to javafx.fxml;
}