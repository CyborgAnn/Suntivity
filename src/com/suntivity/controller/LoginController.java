package com.suntivity.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import suntivity_model.Account;
import suntivity_model.ChildAccount;
import suntivity_model.Model;
import suntivity_model.ParentAccount;

import java.util.Objects;
import java.util.Optional;

/**
 * Controls the Suntivity login screen.
 *
 * <p>The controller validates a submitted username and password against the
 * configured {@link Model}. A successful login is routed to either the
 * parent dashboard or child task screen according to the concrete account type.</p>
 */
public final class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private NavigationController navigation;
    private Model model;

    /**
     * Supplies the services required by the login screen.
     *
     * @param navigation navigation service responsible for changing screens
     * @param model application model containing the registered accounts
     * @throws NullPointerException if either argument is {@code null}
     */
    public void configure(NavigationController navigation, Model model) {
        this.navigation = Objects.requireNonNull(navigation);
        this.model = Objects.requireNonNull(model);
    }

    /**
     * Initializes the FXML controls after they have been injected.
     */
    @FXML
    private void initialize() {
        errorLabel.setText("");
    }

    /**
     * Validates the form and attempts to log in the user.
     *
     * <p>If authentication succeeds, the user is routed according to the account
     * subtype. Otherwise, a validation message is displayed on the current screen.</p>
     *
     * @throws IllegalStateException if
     *         {@link #configure(NavigationController, Model)} has not been called
     */
    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isBlank()) {
            showError("Enter both your username and password.");
            return;
        }

        Optional<Account> match = validateCredentials(username, password);
        if (match.isEmpty()) {
            showError("The username or password is incorrect.");
            passwordField.clear();
            return;
        }

        Account account = match.get();
        errorLabel.setText("");

        if (account instanceof ParentAccount parent) {
            navigation.showParentDashboard(parent);
        } else if (account instanceof ChildAccount child) {
            navigation.showChildTasks(child);
        } else {
            showError("This account has an unsupported account type.");
        }
    }

    /**
     * Searches for an account whose username and password match the submitted values.
     *
     * @param username submitted username
     * @param password submitted password
     * @return the matching account, or an empty {@link Optional} when authentication fails
     * @throws IllegalStateException if
     *         {@link #configure(NavigationController, Model)} has not been called
     */
    public Optional<Account> validateCredentials(String username, String password) {
        requireConfigured();
        Account account = model.getAccountByUsername(username);
        if (account != null && account.getPassword().equals(password)) {
            return Optional.of(account);
        }
        return Optional.empty();
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
     * Displays a validation or authentication error.
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
                    "Call configure(...) before showing the login screen.");
        }
    }
}
