package edu.utsa.cs3443.suntivity.application;

import edu.utsa.cs3443.suntivity.model.Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SuntivityApplication extends Application {

    private static Model model;


    @Override
    public void start(Stage stage) throws IOException {

        // Create ONE shared model for the entire app
        model = new Model();


        FXMLLoader loader = new FXMLLoader(
                SuntivityApplication.class.getResource(
                        "/edu/utsa/cs3443/suntivity/fxml/start-view.fxml"
                )
        );


        Scene scene = new Scene(loader.load(), 390, 844);


        stage.setScene(scene);
        stage.setTitle("Suntivity");


        stage.show();

    }


    /**
     * Allows controllers to access the same application model.
     */
    public static Model getModel() {

        return model;

    }


    public static void main(String[] args) {

        launch();

    }

}