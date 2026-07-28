package edu.utsa.cs3443.suntivity.controller;

import java.util.List;

import edu.utsa.cs3443.suntivity.model.Account;
import edu.utsa.cs3443.suntivity.model.ChildAccount;
import edu.utsa.cs3443.suntivity.model.ParentAccount;

/**
 * Handles user login validation and navigation to the correct account interface.
 */
public class LoginController {

    private final NavigationController navigationController;
    private final List<Account> accounts;

    /**
     * Creates a LoginController with access to navigation and account data.
     *
     * @param navigationController controls switching between screens
     * @param accounts list of registered accounts
     */
    public LoginController(
            NavigationController navigationController,
            List<Account> accounts) {

        this.navigationController = navigationController;
        this.accounts = accounts;
    }

    /**
     * Validates user credentials and opens the correct dashboard.
     *
     * @param username entered username
     * @param password entered password
     * @return true if login is successful, false otherwise
     */
    public boolean handleLogin(String username, String password) {

        Account account = validateCredentials(username, password);

        if (account instanceof ParentAccount parent) {
            navigationController.showParentDashboard(parent);
            return true;
        }

        if (account instanceof ChildAccount child) {
            navigationController.showChildTasks(child);
            return true;
        }

        return false;
    }

    /**
     * Checks entered credentials against stored accounts.
     *
     * @param username entered username
     * @param password entered password
     * @return matching account or null if credentials are invalid
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
}