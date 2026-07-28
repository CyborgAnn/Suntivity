package edu.utsa.cs3443.suntivity.controller;

import edu.utsa.cs3443.suntivity.model.Account;
import edu.utsa.cs3443.suntivity.model.ChildAccount;
import edu.utsa.cs3443.suntivity.model.ParentAccount;

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
    public void showLogin() {
        // TODO: Load login.fxml
    }

    /**
     * Displays the sign-up screen.
     */
    public void showSignUp() {
        // TODO: Load signup.fxml
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