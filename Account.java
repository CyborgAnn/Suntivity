package suntivity_model;

/**
 * Represents a user account in the Suntivity application.
 *
 * <p>This abstract class stores common account information such as
 * username, password, and time zone. Specific account types should
 * extend this class and add their own functionality.</p>
 */
public abstract class Account {

    /** The username associated with this account. */
    private String userName;

    /** The password associated with this account. */
    private String password;

    /** The user's time zone represented as an integer value. */
    private int timeZone;

    /**
     * Creates a new Account with the specified user information.
     *
     * @param userName the username for the account
     * @param password the password for the account
     * @param timeZone the user's time zone
     */
    public Account(String userName, String password, int timeZone) {
        this.userName = userName;
        this.password = password;
        this.timeZone = timeZone;
    }

    /**
     * Updates the username for this account.
     *
     * @param userName the new username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Retrieves the username associated with this account.
     *
     * @return the account username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Updates the password for this account.
     *
     * @param password the new account password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the password associated with this account.
     *
     * @return the account password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Updates the time zone for this account.
     *
     * @param timeZone the new time zone value
     */
    public void setTimeZone(int timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * Retrieves the account's time zone.
     *
     * @return the account time zone
     */
    public int getTimeZone() {
        return timeZone;
    }
}
