package edu.utsa.cs3443.suntivity.controller;

import edu.utsa.cs3443.suntivity.model.ChildAccount;
import edu.utsa.cs3443.suntivity.model.ParentAccount;
import edu.utsa.cs3443.suntivity.model.Task;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import edu.utsa.cs3443.suntivity.model.TaskStatus;

import java.io.IOException;

import java.time.LocalDate;

public class ParentDashboardController {


    private ParentAccount parent;


    @FXML
    private Label linkingCodeLabel;


    @FXML
    private TextField taskNameField;


    @FXML
    private TextArea taskDescriptionField;


    @FXML
    private DatePicker dueDatePicker;


    @FXML
    private Spinner<Integer> hourSpinner;


    @FXML
    private Spinner<Integer> minuteSpinner;


    @FXML
    private ComboBox<String> amPmChoice;


    @FXML
    private CheckBox repeatCheckBox;


    @FXML
    private VBox repeatOptionsBox;


    @FXML
    private ComboBox<String> repeatFrequencyChoice;


    @FXML
    private DatePicker repeatEndDatePicker;


    @FXML
    private ComboBox<ChildAccount> childSelector;

    @FXML
    private ListView<ChildAccount> childrenListView;

    @FXML
    private ComboBox<Integer> pointsSelector;


    // ================= SETTINGS =================


    @FXML
    private TextField usernameSettingsField;


    @FXML
    private PasswordField passwordSettingsField;


    @FXML
    private ComboBox<String> timeZoneSettingsChoice;

    @FXML
    private Button createChildButton;

    @FXML
    private ListView<Task> pendingTaskListView;

    /**
     * Receives logged in parent account.
     */
    public void setParent(ParentAccount parent) {

        this.parent = parent;


        loadChildren();

        loadLinkingCode();

        setupSpinners();

        setupPoints();

        setupRepeatOptions();

        setupSettings();

        loadSettings();

        loadPendingTasks();
    }


    /**
     * Initializes repeat task settings.
     */
    private void setupRepeatOptions() {

        repeatFrequencyChoice.getItems().clear();


        repeatFrequencyChoice.getItems().addAll(
                "Daily",
                "Weekly",
                "Monthly"
        );


        repeatFrequencyChoice.setValue("Daily");


        repeatOptionsBox.setVisible(true);

        repeatOptionsBox.setManaged(true);

    }


    /**
     * Shows or hides repeat settings.
     */
    @FXML
    public void toggleRepeat() {

        repeatOptionsBox.setVisible(
                repeatCheckBox.isSelected()
        );


        repeatOptionsBox.setManaged(
                repeatCheckBox.isSelected()
        );

    }


    /**
     * Displays parent linking code.
     */
    private void loadLinkingCode() {

        if (parent != null) {

            linkingCodeLabel.setText(
                    "Your Linking Code: "
                            + parent.getLinkingCode()
            );

        }

    }


    /**
     * Loads children connected to parent.
     */
    private void loadChildren() {

        childSelector.getItems().clear();

        childSelector.getItems()
                .addAll(parent.getChildren());


        childrenListView.getItems().clear();

        childrenListView.getItems()
                .addAll(parent.getChildren());

    }


    /**
     * Sets up task time selectors.
     */
    private void setupSpinners() {

        hourSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        1,
                        12,
                        12
                )
        );


        minuteSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        0,
                        59,
                        0,
                        5
                )
        );


        amPmChoice.getItems().clear();


        amPmChoice.getItems().addAll(
                "AM",
                "PM"
        );


        amPmChoice.setValue("AM");

    }


    /**
     * Sets available task point values.
     */
    private void setupPoints() {

        pointsSelector.getItems().clear();


        pointsSelector.getItems().addAll(
                5,
                10,
                20,
                50,
                100
        );

    }


    /**
     * Sets up Settings options.
     */
    private void setupSettings() {

        timeZoneSettingsChoice.getItems().clear();


        timeZoneSettingsChoice.getItems().addAll(
                "EST",
                "CST",
                "MST",
                "PST"
        );

    }


    /**
     * Loads current parent information into settings.
     */
    private void loadSettings() {

        if (parent == null) {
            return;
        }


        usernameSettingsField.setText(
                parent.getUserName()
        );


        passwordSettingsField.clear();


        timeZoneSettingsChoice.setValue(
                convertTimeZone(
                        parent.getTimeZone()
                )
        );

    }


    /**
     * Converts timezone number to text.
     */
    private String convertTimeZone(int timeZone) {

        switch (timeZone) {

            case -5:
                return "EST";

            case -6:
                return "CST";

            case -7:
                return "MST";

            case -8:
                return "PST";

            default:
                return "CST";

        }

    }


    /**
     * Converts timezone choice to number.
     */
    private int getTimeZoneFromChoice(String zone) {

        switch (zone) {

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
     * Saves account settings.
     */
    @FXML
    public void saveSettings() {

        if (parent == null) {
            return;
        }


        if (!usernameSettingsField.getText().isEmpty()) {

            parent.setUserName(
                    usernameSettingsField.getText()
            );

        }


        if (!passwordSettingsField.getText().isEmpty()) {

            parent.setPassword(
                    passwordSettingsField.getText()
            );

        }


        if (timeZoneSettingsChoice.getValue() != null) {

            parent.setTimeZone(
                    getTimeZoneFromChoice(
                            timeZoneSettingsChoice.getValue()
                    )
            );

        }


        System.out.println("Settings saved.");

    }


    /**
     * Logs user out.
     */
    /**
     * Logs out and returns to start screen.
     */
    @FXML
    public void logout(ActionEvent event) throws IOException {


        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/edu/utsa/cs3443/suntivity/fxml/start-view.fxml"
                )
        );


        Parent root = loader.load();


        Stage stage =
                (Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();


        stage.setScene(
                new Scene(root, 390, 844)
        );


        stage.show();


        System.out.println("Logged out.");

    }


    /**
     * Creates a task assigned to child.
     */
    @FXML
    public void createTask() {


        if (parent == null) {

            System.out.println("No parent logged in.");

            return;

        }


        ChildAccount child =
                childSelector.getValue();


        LocalDate date =
                dueDatePicker.getValue();


        if (child == null) {

            System.out.println("Select a child.");

            return;

        }


        if (date == null) {

            System.out.println("Select due date.");

            return;

        }


        Task task =
                parent.createTask(
                        child,
                        taskNameField.getText(),
                        taskDescriptionField.getText(),
                        date,
                        hourSpinner.getValue(),
                        pointsSelector.getValue(),
                        repeatCheckBox.isSelected()
                );


        System.out.println(
                "Task Created: "
                        + taskNameField.getText()
        );


        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Task Created");

        alert.setHeaderText(null);

        alert.setContentText(
                "Task successfully assigned to "
                        + child.getUserName()
        );

        alert.showAndWait();
    }


    @FXML
    public void viewFinishedTasks() {

        System.out.println("Viewing finished tasks.");

    }


    @FXML
    public void viewPendingTasks() {

        System.out.println("Viewing pending tasks.");

    }


    @FXML
    public void manageAccounts() {

        System.out.println("Managing linked accounts.");

    }


    @FXML
    public void openSettings() {

        System.out.println("Opening settings.");

    }

    @FXML
    public void openCreateChildAccount() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/edu/utsa/cs3443/suntivity/fxml/create-child-view.fxml"
                            )
                    );


            Parent root = loader.load();


            CreateChildController controller =
                    loader.getController();


            controller.setParent(parent);


            Stage stage =
                    (Stage) createChildButton
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

    /**
     * Removes selected child account.
     */
    @FXML
    public void removeChild() {

        ChildAccount selectedChild =
                childrenListView.getSelectionModel()
                        .getSelectedItem();


        if (selectedChild == null) {

            Alert alert =
                    new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Remove Child Error");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Please select a child account first."
            );

            alert.showAndWait();

            return;

        }


        parent.removeChild(selectedChild);


        childrenListView.getItems()
                .remove(selectedChild);


        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Child Removed");
        alert.setHeaderText(null);
        alert.setContentText(
                "Child account removed successfully."
        );

        alert.showAndWait();

    }

    private void loadPendingTasks() {

        pendingTaskListView.getItems().clear();


        for (ChildAccount child : parent.getChildren()) {


            for (Task task : child.getTasks()) {


                if (task.getStatus() == TaskStatus.PENDING) {

                    pendingTaskListView.getItems()
                            .add(task);

                }

            }

        }

    }

    @FXML
    public void approveTask() {

        Task task =
                pendingTaskListView
                        .getSelectionModel()
                        .getSelectedItem();


        if (task == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a task first.");
            alert.showAndWait();

            return;
        }


        task.approveTask();


        ChildAccount child =
                task.getChild();


        child.addPoints(
                task.getPoints()
        );


        child.addNotification(
                "🎉 Reward Received!\n\n"
                        + "You earned "
                        + task.getPoints()
                        + " points!"
        );


        loadPendingTasks();


        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Task Approved");
        alert.setContentText(
                "Task approved successfully."
        );

        alert.showAndWait();

    }

    @FXML
    public void denyTask() {

        Task task =
                pendingTaskListView
                        .getSelectionModel()
                        .getSelectedItem();


        if (task == null) {

            Alert alert =
                    new Alert(Alert.AlertType.ERROR);

            alert.setTitle("No Task Selected");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Please select a task first."
            );

            alert.showAndWait();

            return;
        }
        // Change task status
        task.denyTask();


        ChildAccount child =
                task.getChild();


        // Save denial notification for child
        child.addNotification(
                "❌ Task Denied\n\n"
                        + "Your task:\n"
                        + task.getName()
                        + "\n\n"
                        + "was not approved.\n\n"
                        + "No points were earned."
        );


        // Refresh parent approval list
        loadPendingTasks();


        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);


        alert.setTitle("Task Denied");

        alert.setHeaderText(null);

        alert.setContentText(
                "Task denied.\n"
                        + child.getUserName()
                        + " did not receive points."
        );


        alert.showAndWait();

    }
}