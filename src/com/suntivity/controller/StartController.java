package com.suntivity.controller;

import javafx.fxml.FXML;

import java.util.Objects;

/**
 * Controls the opening screen of the Suntivity application.
 *
 * <p>This controller does not interact with the application's model. It delegates
 * all screen changes to a {@link NavigationController}.</p>
 */
public final class StartController {
    private NavigationController navigation;

    /**
     * Supplies the navigation service used by this controller.
     *
     * @param navigation navigation service responsible for changing screens
     * @throws NullPointerException if {@code navigation} is {@code null}
     */
    public void setNavigationController(NavigationController navigation) {
        this.navigation = Objects.requireNonNull(navigation);
    }

    /**
     * Opens the login screen when the user selects Log In.
     *
     * @throws IllegalStateException if a navigation controller has not been supplied
     */
    @FXML
    private void handleLogin() {
        requireNavigation().showLogin();
    }

    /**
     * Opens the registration screen when the user selects Sign Up.
     *
     * @throws IllegalStateException if a navigation controller has not been supplied
     */
    @FXML
    private void handleSignUp() {
        requireNavigation().showSignUp();
    }

    /**
     * Returns the configured navigation service.
     *
     * @return configured navigation controller
     * @throws IllegalStateException if a navigation controller has not been supplied
     */
    private NavigationController requireNavigation() {
        if (navigation == null) {
            throw new IllegalStateException(
                    "NavigationController must be injected before showing the start screen.");
        }
        return navigation;
    }
}
