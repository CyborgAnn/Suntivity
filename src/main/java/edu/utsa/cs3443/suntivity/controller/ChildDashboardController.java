package edu.utsa.cs3443.suntivity.controller;

import edu.utsa.cs3443.suntivity.model.ChildAccount;
import edu.utsa.cs3443.suntivity.model.Task;
import edu.utsa.cs3443.suntivity.model.TaskStatus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.geometry.Pos;

import javafx.stage.Stage;

import java.io.IOException;

import java.time.LocalDate;
import java.time.YearMonth;


/**
 * Controls the child dashboard.
 */
public class ChildDashboardController {


    private ChildAccount child;


    @FXML
    private Label welcomeLabel;


    @FXML
    private Label pointsLabel;


    @FXML
    private Label plantStageLabel;


    @FXML
    private ImageView plantImageView;

    @FXML
    private ImageView boxImageView;

    @FXML
    private ImageView faceImageView;

    @FXML
    private ListView<Task> taskListView;


    @FXML
    private GridPane calendarGrid;


    @FXML
    private Label monthLabel;


    /**
     * Receives logged in child.
     */
    public void setChild(ChildAccount child) {

        this.child = child;

        loadChildInfo();

        loadTasks();

        setupTaskDisplay();

        createCalendar();

    }


    /**
     * Loads child information.
     */
    /**
     * Loads child information.
     */
    private void loadChildInfo() {

        if (child == null) {
            return;
        }


        // Display username
        welcomeLabel.setText(
                "Welcome, "
                        + child.getUserName()
                        + "!"
        );


        // Display points
        pointsLabel.setText(
                "Points: "
                        + child.getPoints()
        );


        // Display plant stage and image
        if (child.getPlant() != null
                && child.getPlant().getStatus() != null) {


            plantStageLabel.setText(
                    "Plant Stage: "
                            + child.getPlant()
                            .getStatus()
                            .getName()
            );


            if (child.getPlant()
                    .getStatus()
                    .getImage() != null) {


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

        } else {

            plantStageLabel.setText(
                    "Plant Stage: Seed"
            );

        }


        // Load equipped face, pot, and color
        displayPlantCustomizations();


        // ===============================
        // DISPLAY NOTIFICATIONS
        // ===============================

        if (!child.getNotifications().isEmpty()) {


            for (String message : child.getNotifications()) {


                Alert alert =
                        new Alert(
                                Alert.AlertType.INFORMATION
                        );


                alert.setTitle(
                        "Suntivity Update"
                );


                alert.setHeaderText(
                        "🌱 Suntivity Update"
                );


                alert.setContentText(
                        message
                );


                alert.showAndWait();

            }


            child.clearNotifications();

        }

    }


    /**
     * Loads only incomplete tasks.
     */
    private void loadTasks() {

        taskListView.getItems().clear();


        if (child != null) {


            for (Task task : child.getTasks()) {


                if (task.getStatus()
                        == TaskStatus.INCOMPLETE) {


                    taskListView.getItems()
                            .add(task);

                }

            }

        }

    }


    /**
     * Formats task display.
     */
    private void setupTaskDisplay() {

        taskListView.setCellFactory(list ->
                new ListCell<Task>() {


                    @Override
                    protected void updateItem(
                            Task task,
                            boolean empty
                    ) {

                        super.updateItem(task, empty);


                        if (empty || task == null) {

                            setText(null);

                        } else {


                            setText(
                                    task.getTaskName()
                                            + "\n"
                                            + task.getDescription()
                                            + "\nDue: "
                                            + task.getDueDate()
                                            + "\nPoints: "
                                            + task.getPoints()
                            );

                        }

                    }

                });

    }


    /**
     * Child completes selected task.
     */
    @FXML
    public void completeTask() {


        Task task =
                taskListView
                        .getSelectionModel()
                        .getSelectedItem();


        if (task == null) {


            Alert alert =
                    new Alert(
                            Alert.AlertType.ERROR
                    );


            alert.setTitle(
                    "No Task Selected"
            );


            alert.setHeaderText(null);


            alert.setContentText(
                    "Please select a task first."
            );


            alert.showAndWait();


            return;

        }


        child.completeTask(task);


        System.out.println(
                "Task Status After Completion: "
                        + task.getStatus()
        );


        // Remove from visible task list
        taskListView.getItems()
                .remove(task);


        showReward(task);


        createCalendar();

    }


    /**
     * Reward popup.
     */
    private void showReward(Task task) {


        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );


        alert.setTitle(
                "Reward Earned!"
        );


        alert.setHeaderText(
                "🌱 Great Job!"
        );


        alert.setContentText(
                "Your task was submitted!\n\n"
                        + "Reward: "
                        + task.getPoints()
                        + " points\n\n"
                        + "Waiting for parent approval."
        );


        alert.showAndWait();

    }


    @FXML
    public void openShop(ActionEvent event) throws IOException {


        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/edu/utsa/cs3443/suntivity/fxml/shop-view.fxml"
                        )
                );


        Parent root =
                loader.load();


        ShopController controller =
                loader.getController();


        controller.setChild(child);


        Stage stage =
                (Stage)
                        ((Node) event.getSource())
                                .getScene()
                                .getWindow();


        stage.setScene(
                new Scene(
                        root,
                        390,
                        844
                )
        );


        stage.show();

    }


    @FXML
    public void openCalendar() {

        System.out.println(
                "Opening Calendar"
        );

    }


    @FXML
    public void openSettings() {

        System.out.println(
                "Opening Settings"
        );

    }


    /**
     * Creates monthly calendar.
     * Sunday is first column.
     */
    private void createCalendar() {


        calendarGrid.getChildren()
                .clear();


        String[] days = {

                "SUN",
                "MON",
                "TUE",
                "WED",
                "THU",
                "FRI",
                "SAT"

        };


        for (int i = 0; i < 7; i++) {


            Label header =
                    new Label(
                            days[i]
                    );


            header.setStyle(
                    "-fx-font-weight:bold;"
            );


            calendarGrid.add(
                    header,
                    i,
                    0
            );

        }


        YearMonth month =
                YearMonth.now();


        monthLabel.setText(
                month.getMonth()
                        + " "
                        + month.getYear()
        );


        int column =
                month.atDay(1)
                        .getDayOfWeek()
                        .getValue()
                        % 7;


        int row = 1;


        for (int day = 1;
             day <= month.lengthOfMonth();
             day++) {


            LocalDate date =
                    month.atDay(day);


            VBox dayBox =
                    new VBox();


            dayBox.setAlignment(
                    Pos.CENTER
            );


            dayBox.setPrefSize(
                    40,
                    40
            );


            dayBox.setStyle(
                    "-fx-background-color:white;"
                            +
                            "-fx-border-color:#035e1b;"
            );


            Label dayNumber =
                    new Label(
                            String.valueOf(day)
                    );


            dayNumber.setTextFill(
                    Color.BLACK
            );


            dayBox.getChildren()
                    .add(dayNumber);


            if (hasTaskOnDate(date)) {


                Circle dot =
                        new Circle(4);


                dot.setFill(
                        Color.RED
                );


                dayBox.getChildren()
                        .add(dot);

            }


            calendarGrid.add(
                    dayBox,
                    column,
                    row
            );


            column++;


            if (column == 7) {

                column = 0;

                row++;

            }


        }


    }


    /**
     * Checks if task exists on date.
     */
    private boolean hasTaskOnDate(LocalDate date) {


        if (child == null) {

            return false;

        }


        for (Task task : child.getTasks()) {


            if (task.getDueDate() != null
                    &&
                    task.getDueDate()
                            .equals(date)
                    &&
                    task.getStatus()
                            == TaskStatus.INCOMPLETE) {


                return true;

            }

        }


        return false;

    }


    /**
     * Logout.
     */
    @FXML
    public void logout(ActionEvent event)
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
                (Stage)
                        ((Node) event.getSource())
                                .getScene()
                                .getWindow();


        stage.setScene(
                new Scene(
                        root,
                        390,
                        844
                )
        );


        stage.show();

    }

    private void displayPlantCustomizations() {

        if (child == null
                || child.getPlant() == null) {

            return;

        }


        // ===============================
        // PLANT IMAGE
        // ===============================

        if (child.getPlant().getStatus() != null
                && child.getPlant()
                .getStatus()
                .getImage() != null) {


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


        // ===============================
        // FACE
        // ===============================

        if (child.getPlant().getFace() != null) {


            faceImageView.setImage(
                    new Image(
                            child.getPlant()
                                    .getFace()
                                    .getImage()
                                    .toURI()
                                    .toString()
                    )
            );


            faceImageView.setTranslateY(-25);


        } else {

            faceImageView.setImage(null);

        }


        // ===============================
        // PLANT BOX / POT
        // ===============================

        if (child.getPlant().getBox() != null) {


            boxImageView.setImage(
                    new Image(
                            child.getPlant()
                                    .getBox()
                                    .getImage()
                                    .toURI()
                                    .toString()
                    )
            );


            boxImageView.setTranslateY(60);


        } else {

            boxImageView.setImage(null);

        }

    }
}