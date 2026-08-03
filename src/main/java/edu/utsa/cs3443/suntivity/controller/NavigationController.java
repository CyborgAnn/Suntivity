package edu.utsa.cs3443.suntivity.controller;

import edu.utsa.cs3443.suntivity.model.Account;
import edu.utsa.cs3443.suntivity.model.ChildAccount;
import edu.utsa.cs3443.suntivity.model.ParentAccount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Centralizes navigation between Suntivity screens.
 *
 * This controller will handle switching between JavaFX views.
 * FXML loading logic will be added once screen layouts are finalized.
 */
public class NavigationController {

    private Account currentAccount;

    /**
     * Returns the currently logged-in account.
     *
     * @return current account
     */
    public Account getCurrentAccount() {
        return currentAccount;
    }

    /**
     * Sets the currently logged-in account.
     *
     * @param currentAccount account currently using the application
     */
    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    /**
     * Displays the start screen.
     */
    public void showStart() {
        // TODO: Load start.fxml
    }

    /**
     * Displays the login screen.
     */
    @FXML
    public void showLogin(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/edu/utsa/cs3443/suntivity/fxml/login-view.fxml"
                )
        );

        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Displays the sign-up screen.
     */
    @FXML
    public void showSignUp(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/edu/utsa/cs3443/suntivity/fxml/signup-view.fxml"
                )
        );

        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        Scene scene = new Scene(root, 390, 844);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Displays the parent dashboard.
     *
     * @param parent logged-in parent account
     */
    public void showParentDashboard(ParentAccount parent) {
        setCurrentAccount(parent);

        // TODO: Load parent dashboard FXML
        // Pass parent account information to ParentDashboardController
    }

    /**
     * Displays the child task screen.
     *
     * @param child logged-in child account
     */
    public void showChildTasks(ChildAccount child) {
        setCurrentAccount(child);

        // TODO: Load child tasks FXML
        // Pass child account information to TaskController
    }

    /**
     * Displays settings.
     *
     * @param account current account
     */
    public void showSettings(Account account) {
        setCurrentAccount(account);

        // TODO: Load settings.fxml
    }

    /**
     * Displays rewards screen.
     *
     * @param child child account viewing rewards
     */
    public void showRewards(ChildAccount child) {
        setCurrentAccount(child);

        // TODO: Load rewards.fxml
    }

    /**
     * Displays account linking screen.
     *
     * @param account account requesting account linking
     */
    public void showLinkAccount(Account account) {
        setCurrentAccount(account);

        // TODO: Load link account FXML
    }
}