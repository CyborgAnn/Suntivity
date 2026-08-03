package edu.utsa.cs3443.suntivity.controller;


import edu.utsa.cs3443.suntivity.application.SuntivityApplication;
import edu.utsa.cs3443.suntivity.model.ChildAccount;
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
 * Handles creating child accounts from the Parent Dashboard.
 */
public class CreateChildController {


    @FXML
    private TextField usernameField;


    @FXML
    private PasswordField passwordField;


    @FXML
    private PasswordField confirmPasswordField;


    @FXML
    private ChoiceBox<String> timeZoneChoice;



    private ParentAccount parent;

    private Model model;



    public CreateChildController(){

        model = SuntivityApplication.getModel();

    }




    /**
     * Receives the logged in parent.
     */
    public void setParent(ParentAccount parent){

        this.parent = parent;

    }





    @FXML
    public void initialize(){


        timeZoneChoice.getItems().addAll(
                "EST",
                "CST",
                "MST",
                "PST"
        );


        timeZoneChoice.setValue("CST");

    }







    /**
     * Creates child account under parent.
     */
    @FXML
    public void createChildAccount(){


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




        if(parent == null){


            showError(
                    "No parent account found."
            );

            return;

        }





        ChildAccount child =
                new ChildAccount(
                        username,
                        password,
                        getTimeZone()
                );



        // Link child to parent
        parent.addChild(child);
        child.saveData();
        parent.saveData();



        // Add child to application model
        model.addChild(child);





        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );


        alert.setTitle("Child Account Created");

        alert.setHeaderText(null);

        alert.setContentText(
                "Child account has been created successfully."
        );


        alert.showAndWait();





        goBackToDashboard();

    }







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








    private void goBackToDashboard(){


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








    @FXML
    public void handleBack(ActionEvent event)
            throws IOException{


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
                (Stage)((Node)event.getSource())
                        .getScene()
                        .getWindow();



        stage.setScene(
                new Scene(root,390,844)
        );


        stage.show();

    }






    private void showError(String message){


        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );


        alert.setTitle("Create Child Account Error");

        alert.setHeaderText(null);

        alert.setContentText(message);


        alert.showAndWait();

    }


}