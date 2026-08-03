package edu.utsa.cs3443.suntivity.controller;

import edu.utsa.cs3443.suntivity.application.SuntivityApplication;
import edu.utsa.cs3443.suntivity.model.Account;
import edu.utsa.cs3443.suntivity.model.ChildAccount;
import edu.utsa.cs3443.suntivity.model.Model;
import edu.utsa.cs3443.suntivity.model.ParentAccount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import java.io.IOException;


/**
 * Handles user login and directs users to the correct interface.
 */
public class LoginController {


    @FXML
    private TextField usernameField;


    @FXML
    private PasswordField passwordField;


    private NavigationController navigationController;

    private Model model;



    /**
     * Required constructor for FXMLLoader.
     */
    public LoginController() {

        navigationController = new NavigationController();

        model = SuntivityApplication.getModel();

    }



    /**
     * Handles login button click.
     */
    @FXML
    public void handleLogin() {


        String username =
                usernameField.getText();


        String password =
                passwordField.getText();



        // Empty fields check
        if (username.isEmpty() || password.isEmpty()) {

            showError(
                    "Please enter both username and password."
            );

            return;

        }



        Account account =
                validateCredentials(
                        username,
                        password
                );



        // Account not found
        if (account == null) {

            showError(
                    "Incorrect username or password."
            );

            return;

        }



        System.out.println(
                "Login successful."
        );



        if(account instanceof ParentAccount){


            try {

                openParentDashboard(
                        (ParentAccount) account
                );


            } catch (IOException e) {

                e.printStackTrace();

            }


        }
        else if(account instanceof ChildAccount){


            openChildDashboard(
                    (ChildAccount) account
            );


        }

    }





    /**
     * Opens Parent Dashboard after successful login.
     */
    private void openParentDashboard(ParentAccount parent)
            throws IOException {



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
                (Stage) usernameField.getScene()
                        .getWindow();



        stage.setScene(
                new Scene(root,390,844)
        );



        stage.show();



        System.out.println(
                "Parent Dashboard opened."
        );

    }





    /**
     * Opens Child Dashboard after successful login.
     */
    private void openChildDashboard(ChildAccount child) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/edu/utsa/cs3443/suntivity/fxml/child-dashboard-view.fxml"
                            )
                    );

            Parent root = loader.load();

            ChildDashboardController controller =
                    loader.getController();

            controller.setChild(child);

            Stage stage =
                    (Stage) usernameField
                            .getScene()
                            .getWindow();

            stage.setScene(
                    new Scene(root, 390, 844)
            );

            stage.show();

            System.out.println("Child Dashboard opened.");

        }
        catch (IOException e) {

            e.printStackTrace();

        }

    }





    /**
     * Displays error popup messages.
     */
    private void showError(String message){


        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );


        alert.setTitle(
                "Login Error"
        );


        alert.setHeaderText(null);


        alert.setContentText(
                message
        );


        alert.showAndWait();

    }





    /**
     * Checks username and password against stored accounts.
     *
     * @param username entered username
     * @param password entered password
     * @return matching account or null
     */
    private Account validateCredentials(
            String username,
            String password) {


        return model.validateLogin(
                username,
                password
        );

    }





    /**
     * Returns to start screen.
     */
    @FXML
    public void goBack(ActionEvent event)
            throws IOException {


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





    public NavigationController getNavigationController() {

        return navigationController;

    }




    public void setNavigationController(
            NavigationController navigationController) {

        this.navigationController =
                navigationController;

    }





    public Model getModel() {

        return model;

    }





    public void setModel(Model model) {

        this.model = model;

    }

}