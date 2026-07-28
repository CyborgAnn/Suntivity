package com.suntivity.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import suntivity_model.Account;
import suntivity_model.ChildAccount;
import suntivity_model.Model;
import suntivity_model.ParentAccount;

import java.util.Objects;

/**
 * Controls the Suntivity account-registration screen.
 *
 * <p>The controller validates the registration form, rejects duplicate usernames,
 * creates the selected account subtype, stores it in the {@link Model}, and opens
 * the appropriate authenticated screen.</p>
 */
public final class SignUpController {
    private static final String PARENT = "Parent";
    private static final String CHILD = "Child";
    private static final String ACCOUNT_TYPE_PROMPT = "Account Type";
    private static final String TIME_ZONE_PROMPT = "Time Zone";

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private ChoiceBox<String> accountTypeChoice;
    @FXML private ChoiceBox<String> timeZoneChoice;
    @FXML private Label errorLabel;

    private NavigationController navigation;
    private Model model;

    /**
     * Supplies the services required by the registration screen.
     *
     * @param navigation navigation service responsible for changing screens
     * @param model application model used for duplicate checks and account storage
     * @throws NullPointerException if either argument is {@code null}
     */
    public void configure(NavigationController navigation, Model model) {
        this.navigation = Objects.requireNonNull(navigation);
        this.model = Objects.requireNonNull(model);
    }

    /**
     * Initializes account-type and time-zone choices after FXML injection.
     */
    @FXML
    private void initialize() {
        accountTypeChoice.getItems().setAll(PARENT, CHILD);
        accountTypeChoice.setValue(ACCOUNT_TYPE_PROMPT);
        timeZoneChoice.getItems().setAll(
                "EST", "CST", "MST", "PST", "AKST", "HST", "AST");
        timeZoneChoice.setValue(TIME_ZONE_PROMPT);
        errorLabel.setText("");
    }

    /**
     * Validates the form, creates the account, and opens its initial screen.
     *
     * @throws IllegalStateException if
     *         {@link #configure(NavigationController, Model)} has not been called or
     *         an unsupported account subtype is created
     */
    @FXML
    private void handleSignUp() {
        requireConfigured();

        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirmation = confirmPasswordField.getText();
        String type = accountTypeChoice.getValue();
        String timeZone = timeZoneChoice.getValue();

        String validationError = validateInput(username, password, confirmation, type, timeZone);
        if (validationError != null) {
            showError(validationError);
            return;
        }

        if (model.getAccountByUsername(username) != null) {
            showError("That username is already in use.");
            return;
        }

        int timeZoneOffset = toUtcOffset(timeZone);
        Account created = PARENT.equals(type)
                ? new ParentAccount(username, password, timeZoneOffset, model)
                : new ChildAccount(username, password, timeZoneOffset);

        model.addAccount(created);
        errorLabel.setText("");

        if (created instanceof ParentAccount parent) {
            navigation.showParentDashboard(parent);
        } else if (created instanceof ChildAccount child) {
            navigation.showChildTasks(child);
        } else {
            throw new IllegalStateException("An unsupported account type was created.");
        }
    }

    /**
     * Checks all user-supplied registration values.
     *
     * @param username requested username
     * @param password requested password
     * @param confirmation repeated password
     * @param type selected account type
     * @param timeZone selected time-zone abbreviation
     * @return an error message, or {@code null} when all values are valid
     */
    private String validateInput(
            String username,
            String password,
            String confirmation,
            String type,
            String timeZone) {
        if (username.isEmpty()) return "Enter a username.";
        if (username.length() < 3) return "Username must be at least 3 characters.";
        if (password.length() < 8) return "Password must be at least 8 characters.";
        if (!password.equals(confirmation)) return "The passwords do not match.";
        if (!PARENT.equals(type) && !CHILD.equals(type)) return "Choose Parent or Child.";
        if (timeZone == null || TIME_ZONE_PROMPT.equals(timeZone)) {
            return "Choose a time zone.";
        }
        return null;
    }

    /**
     * Converts a displayed US time-zone abbreviation to its standard UTC offset.
     *
     * @param timeZone selected time-zone abbreviation
     * @return whole-hour UTC offset stored by the account model
     * @throws IllegalArgumentException if the abbreviation is unsupported
     */
    private int toUtcOffset(String timeZone) {
        return switch (timeZone) {
            case "AST" -> -4;
            case "EST" -> -5;
            case "CST" -> -6;
            case "MST" -> -7;
            case "PST" -> -8;
            case "AKST" -> -9;
            case "HST" -> -10;
            default -> throw new IllegalArgumentException(
                    "Unsupported time zone: " + timeZone);
        };
    }

    /**
     * Returns the user to the opening screen.
     *
     * @throws IllegalStateException if
     *         {@link #configure(NavigationController, Model)} has not been called
     */
    @FXML
    private void handleBack() {
        requireConfigured();
        navigation.showStart();
    }

    /**
     * Displays a registration validation error.
     *
     * @param message message to display
     */
    private void showError(String message) {
        errorLabel.setText(message);
    }

    /**
     * Verifies that the controller's required services were supplied.
     *
     * @throws IllegalStateException if
     *         {@link #configure(NavigationController, Model)} has not been called
     */
    private void requireConfigured() {
        if (navigation == null || model == null) {
            throw new IllegalStateException(
                    "Call configure(...) before showing the sign-up screen.");
        }
    }
}
