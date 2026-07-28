package edu.utsa.cs3443.suntivity.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SuntivityApplication extends Application {

    @Override
    public void start(Stage stage) {

        Label label = new Label("Welcome to Suntivity!");

        Scene scene = new Scene(label, 400, 300);

        stage.setScene(scene);
        stage.setTitle("Suntivity");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}