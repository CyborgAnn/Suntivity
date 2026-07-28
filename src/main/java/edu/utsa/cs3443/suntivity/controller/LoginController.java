package edu.utsa.cs3443.suntivity.controller;

import edu.utsa.cs3443.suntivity.model.Account;
import edu.utsa.cs3443.suntivity.model.ChildAccount;
import edu.utsa.cs3443.suntivity.model.ParentAccount;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles user login and directs users to the correct interface.
 */
public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private NavigationController navigationController;
    private List<Account> accounts;


    /**
     * Required empty constructor for FXMLLoader.
     */
    public LoginController() {
        this.navigationController = new NavigationController();
        this.accounts = new ArrayList<>();
    }


    /**
     * Constructor used when connecting controller with application data.
     *
     * @param navigationController handles screen changes
     * @param accounts list of registered accounts
     */
    public LoginController(NavigationController navigationController,
                           List<Account> accounts) {

        this.navigationController = navigationController;
        this.accounts = accounts;
    }


    /**
     * Handles login button click from login-view.fxml.
     */
    @FXML
    public void handleLogin() {

        String username = usernameField.getText();
        String password = passwordField.getText();

        Account account = validateCredentials(username, password);

        if (account instanceof ParentAccount parent) {

            navigationController.showParentDashboard(parent);

        } else if (account instanceof ChildAccount child) {

            navigationController.showChildTasks(child);

        } else {

            System.out.println("Invalid username or password.");
        }
    }


    /**
     * Checks user credentials against stored accounts.
     *
     * @param username entered username
     * @param password entered password
     * @return matching account or null
     */
    private Account validateCredentials(String username, String password) {

        for (Account account : accounts) {

            if (account.getUserName().equals(username)
                    && account.getPassword().equals(password)) {

                return account;
            }
        }

        return null;
    }


    public List<Account> getAccounts() {
        return accounts;
    }


    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }


    public NavigationController getNavigationController() {
        return navigationController;
    }


    public void setNavigationController(
            NavigationController navigationController) {

        this.navigationController = navigationController;
    }
}