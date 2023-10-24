package com.hbrs.performancecockpit;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/cockpit.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        primaryStage.setTitle("Performance Cockpit");
        primaryStage.setScene(new Scene(root, 650, 450));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}