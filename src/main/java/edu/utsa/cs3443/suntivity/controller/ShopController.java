package edu.utsa.cs3443.suntivity.controller;

import edu.utsa.cs3443.suntivity.model.ChildAccount;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

import edu.utsa.cs3443.suntivity.model.Face;
import edu.utsa.cs3443.suntivity.model.Box;
import edu.utsa.cs3443.suntivity.model.Item;

import javafx.scene.layout.StackPane;

import java.io.File;

import javafx.geometry.Pos;


public class ShopController {


    private ChildAccount child;


    @FXML
    private Label pointsLabel;


    @FXML
    private ImageView plantImageView;

    @FXML
    private ImageView faceImageView;

    @FXML
    private ImageView boxImageView;


    @FXML
    private Label plantStatusLabel;


    @FXML
    private GridPane facesGrid;


    @FXML
    private GridPane colorsGrid;


    @FXML
    private GridPane boxesGrid;

    @FXML
    private ImageView logoImageView;



    private final String customizationPath =
            "/edu/utsa/cs3443/suntivity/DataFiles/SuntivityDesigns/CharacterCustomization/";


    public void setChild(ChildAccount child){

        this.child = child;

        loadChildInfo();

        displayPlantCustomizations();

        loadFaces();

        loadBoxes();

        loadColors();

    }


    private void loadChildInfo(){

        if(child == null){
            return;
        }


        // Display points
        pointsLabel.setText(
                "Points: " + child.getPoints()
        );

// TEST LOGO PATH
        System.out.println(
                getClass().getResource(
                        "/edu/utsa/cs3443/suntivity/DataFiles/SuntivityDesigns/AppArt/AppName2.png"
                )
        );


        if(logoImageView != null){

            logoImageView.setImage(
                    new Image(
                            getClass()
                                    .getResource(
                                            "/edu/utsa/cs3443/suntivity/DataFiles/SuntivityDesigns/AppArt/AppName2.png"
                                    )
                                    .toExternalForm()
                    )
            );

        }
        // Display plant health stage
        if(child.getPlant() != null
                && child.getPlant().getStatus() != null){


            plantStatusLabel.setText(
                    "Plant: "
                            + child.getPlant()
                            .getStatus()
                            .getName()
            );


            if(child.getPlant()
                    .getStatus()
                    .getImage() != null){


                plantImageView.setImage(
                        new Image(
                                child.getPlant()
                                        .getStatus()
                                        .getImage()
                                        .toURI()
                                        .toString()
                        )
                );

            }

        }
        else{

            plantStatusLabel.setText(
                    "Plant: Low Health"
            );

        }



        // Display equipped face
        if(child.getPlant() != null
                && child.getPlant().getFace() != null){

            System.out.println(
                    "Equipped Face: "
                            + child.getPlant()
                            .getFace()
                            .getName()
            );

        }



        // Display equipped box
        if(child.getPlant() != null
                && child.getPlant().getBox() != null){

            System.out.println(
                    "Equipped Box: "
                            + child.getPlant()
                            .getBox()
                            .getName()
            );

        }
        if(logoImageView != null){

            logoImageView.setImage(
                    new Image(
                            getClass()
                                    .getResource(
                                            "/edu/utsa/cs3443/suntivity/DataFiles/SuntivityDesigns/AppArt/AppName2.png"
                                    )
                                    .toExternalForm()
                    )
            );

        }

    }


    private void loadFaces() {

        facesGrid.getChildren().clear();


        String path =
                customizationPath
                        + "Faces/";


        int column = 0;

        int row = 0;


        for (int i = 1; i <= 5; i++) {


            String imagePath =
                    path
                            + "Face"
                            + i
                            + ".png";


            if (getClass()
                    .getResource(imagePath) != null) {


                VBox item =
                        createShopItem(
                                imagePath,
                                20,
                                new Face(
                                        20,
                                        "Face " + i,
                                        new File(
                                                getClass()
                                                        .getResource(imagePath)
                                                        .getPath()
                                        )
                                )
                        );


                facesGrid.add(
                        item,
                        column,
                        row
                );


                column++;


                if (column == 3) {

                    column = 0;

                    row++;

                }


            } else {


                System.out.println(
                        "Missing face: "
                                + imagePath
                );


            }

        }

    }
    private void displayPlant(){

        if(child == null || child.getPlant() == null){
            return;
        }


        // Health image
        if(child.getPlant().getStatus() != null){

            if(child.getPlant()
                    .getStatus()
                    .getImage() != null){


                plantImageView.setImage(
                        new Image(
                                child.getPlant()
                                        .getStatus()
                                        .getImage()
                                        .toURI()
                                        .toString()
                        )
                );

            }

        }



        // Face image
        if(child.getPlant().getFace() != null){

            faceImageView.setImage(
                    new Image(
                            child.getPlant()
                                    .getFace()
                                    .getImage()
                                    .toURI()
                                    .toString()
                    )
            );

        }



        // Box image
        if(child.getPlant().getBox() != null){

            boxImageView.setImage(
                    new Image(
                            child.getPlant()
                                    .getBox()
                                    .getImage()
                                    .toURI()
                                    .toString()
                    )
            );

        }

    }

    private void loadBoxes() {

        boxesGrid.getChildren().clear();

        String path =
                "/edu/utsa/cs3443/suntivity/DataFiles/SuntivityDesigns/CharacterCustomization/PlantBoxes/";


        int column = 0;
        int row = 0;

        for (int i = 1; i <= 5; i++) {

            String imagePath =
                    path
                            + "PlantPot"
                            + i
                            + ".png";

            if (getClass().getResource(imagePath) != null) {

                Box box =
                        new Box(
                                50,
                                "Plant Pot " + i,
                                new File(
                                        getClass()
                                                .getResource(imagePath)
                                                .getPath()
                                )
                        );

                VBox item =
                        createShopItem(
                                imagePath,
                                50,
                                box
                        );

                boxesGrid.add(
                        item,
                        column,
                        row
                );

                column++;

                if (column == 3) {

                    column = 0;
                    row++;

                }

            }
            else {

                System.out.println(
                        "Missing box: "
                                + imagePath
                );

            }

        }

    }


    private void loadColors() {

        colorsGrid.getChildren().clear();


        Label label =
                new Label(
                        "Colors coming soon!"
                );


        colorsGrid.add(
                label,
                0,
                0
        );

    }


    private VBox createShopItem(
            String imagePath,
            int cost,
            Item item){

        Image image =
                new Image(
                        getClass()
                                .getResource(imagePath)
                                .toExternalForm()
                );


        ImageView imageView =
                new ImageView(image);


        imageView.setFitWidth(70);
        imageView.setFitHeight(70);
        imageView.setPreserveRatio(true);



        Label costLabel =
                new Label(
                        cost + " pts"
                );



        Button buyButton =
                new Button("Buy");


        buyButton.setOnAction(e -> {

            if (child.purchaseItem(item)) {

                child.equipItem(item);

                // Save child customization and points after purchase
                child.saveData();

                // Update plant appearance after purchase
                displayPlantCustomizations();


                pointsLabel.setText(
                        "Points: " + child.getPoints()
                );


                buyButton.setText("Owned");

                buyButton.setDisable(true);


                Alert alert =
                        new Alert(
                                Alert.AlertType.INFORMATION
                        );


                alert.setTitle(
                        "Purchase Successful"
                );


                alert.setHeaderText(null);


                alert.setContentText(
                        item.getName()
                                + " equipped!"
                );


                alert.showAndWait();


                loadChildInfo();

            }
            else {

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR
                        );


                alert.setTitle(
                        "Not Enough Points"
                );


                alert.setHeaderText(null);


                alert.setContentText(
                        "You don't have enough points."
                );


                alert.showAndWait();

            }

        });



        VBox box =
                new VBox(
                        5,
                        imageView,
                        costLabel,
                        buyButton
                );


        box.setAlignment(
                javafx.geometry.Pos.CENTER
        );


        return box;

    }


    @FXML
    public void goBack(ActionEvent event) {

        if(child != null){
            child.saveData();
        }

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/edu/utsa/cs3443/suntivity/fxml/child-dashboard-view.fxml"
                            )
                    );


            Parent root =
                    loader.load();


            ChildDashboardController controller =
                    loader.getController();


            controller.setChild(child);


            Stage stage =
                    (Stage)
                            ((Node) event.getSource())
                                    .getScene()
                                    .getWindow();


            stage.setScene(
                    new Scene(root, 390, 844)
            );


            stage.show();


        } catch (IOException e) {

            e.printStackTrace();

        }

    }
    private void displayPlantCustomizations(){

        if(child == null || child.getPlant() == null){
            return;
        }


        // Plant stage image
        if(child.getPlant().getStatus() != null
                && child.getPlant().getStatus().getImage() != null){

            plantImageView.setImage(
                    new Image(
                            child.getPlant()
                                    .getStatus()
                                    .getImage()
                                    .toURI()
                                    .toString()
                    )
            );
        }



        // Face image
        if(child.getPlant().getFace() != null){

            faceImageView.setImage(
                    new Image(
                            child.getPlant()
                                    .getFace()
                                    .getImage()
                                    .toURI()
                                    .toString()
                    )
            );

        }
        else{

            faceImageView.setImage(null);

        }



        // Box image
        if(child.getPlant().getBox() != null){

            boxImageView.setImage(
                    new Image(
                            child.getPlant()
                                    .getBox()
                                    .getImage()
                                    .toURI()
                                    .toString()
                    )
            );

        }
        else{

            boxImageView.setImage(null);

        }

    }
}