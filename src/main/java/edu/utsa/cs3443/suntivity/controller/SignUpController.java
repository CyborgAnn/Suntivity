package edu.utsa.cs3443.suntivity.controller;

import edu.utsa.cs3443.suntivity.application.SuntivityApplication;
import edu.utsa.cs3443.suntivity.model.Model;
import edu.utsa.cs3443.suntivity.model.ParentAccount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Handles creating Parent accounts for Suntivity.
 *
 * Child accounts are created from the Parent Dashboard.
 */
public class SignUpController {


    @FXML
    private TextField usernameField;


    @FXML
    private PasswordField passwordField;


    @FXML
    private PasswordField confirmPasswordField;


    @FXML
    private ChoiceBox<String> timeZoneChoice;



    private Model model;



    /**
     * Creates controller and connects application model.
     */
    public SignUpController() {

        model = SuntivityApplication.getModel();

    }





    /**
     * Initializes signup options.
     */
    @FXML
    public void initialize() {


        timeZoneChoice.getItems().addAll(
                "EST",
                "CST",
                "MST",
                "PST"
        );


        timeZoneChoice.setValue("CST");

    }





    /**
     * Creates parent account.
     */
    @FXML
    public void handleSignUp() {


        String username =
                usernameField.getText();


        String password =
                passwordField.getText();


        String confirm =
                confirmPasswordField.getText();



        if(username.isEmpty()
                || password.isEmpty()
                || confirm.isEmpty()){


            showError(
                    "Please complete all fields."
            );

            return;

        }




        if(!password.equals(confirm)){


            showError(
                    "Passwords do not match."
            );

            return;

        }




        int timezone =
                getTimeZone();





        ParentAccount parent =
                new ParentAccount(
                        username,
                        password,
                        timezone,
                        model
                );



        model.addParent(parent);



        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );


        alert.setTitle("Parent Account Created");

        alert.setHeaderText(null);

        alert.setContentText(
                "Account created successfully!\n\n"
                        + "Your Child Linking Code is:\n"
                        + parent.getLinkingCode()
        );


        alert.showAndWait();




        openParentDashboard(parent);


    }







    /**
     * Converts selected timezone into number.
     */
    private int getTimeZone(){


        switch(timeZoneChoice.getValue()){


            case "EST":
                return -5;


            case "CST":
                return -6;


            case "MST":
                return -7;


            case "PST":
                return -8;


            default:
                return -6;

        }

    }







    /**
     * Opens parent dashboard after signup.
     */
    private void openParentDashboard(ParentAccount parent){


        try{


            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/edu/utsa/cs3443/suntivity/fxml/parent-dashboard-view.fxml"
                            )
                    );



            Parent root =
                    loader.load();




            ParentDashboardController controller =
                    loader.getController();



            controller.setParent(parent);




            Stage stage =
                    (Stage) usernameField
                            .getScene()
                            .getWindow();




            stage.setScene(
                    new Scene(root,390,844)
            );



            stage.show();



        }
        catch(IOException e){

            e.printStackTrace();

        }


    }







    /**
     * Displays error message.
     */
    private void showError(String message){


        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );


        alert.setTitle("Sign Up Error");

        alert.setHeaderText(null);

        alert.setContentText(message);


        alert.showAndWait();

    }







    /**
     * Returns to start screen.
     */
    @FXML
    public void handleBack(ActionEvent event) throws IOException{


        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/edu/utsa/cs3443/suntivity/fxml/start-view.fxml"
                        )
                );



        Parent root =
                loader.load();




        Stage stage =
                (Stage)((Node)event.getSource())
                        .getScene()
                        .getWindow();




        stage.setScene(
                new Scene(root,390,844)
        );



        stage.show();


    }


}