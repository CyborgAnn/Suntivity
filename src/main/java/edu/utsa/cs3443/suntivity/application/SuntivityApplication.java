package edu.utsa.cs3443.suntivity.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SuntivityApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                SuntivityApplication.class.getResource(
                        "/edu/utsa/cs3443/suntivity/fxml/start-view.fxml"
                )
        );

        Scene scene = new Scene(loader.load(), 400, 750);

        stage.setScene(scene);
        stage.setTitle("Suntivity");

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}