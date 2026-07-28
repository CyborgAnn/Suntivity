package edu.utsa.cs3443.suntivity.model;

/**
 * Represents a user account in Suntivity.
 * ParentAccount and ChildAccount extend this class.
 */
public abstract class Account {

    private String userName;
    private String password;
    private int timeZone;

    /**
     * Creates an account with login information and timezone.
     *
     * @param userName account username
     * @param password account password
     * @param timeZone user's timezone
     */
    public Account(String userName, String password, int timeZone) {
        this.userName = userName;
        this.password = password;
        this.timeZone = timeZone;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setTimeZone(int timeZone) {
        this.timeZone = timeZone;
    }

    public int getTimeZone() {
        return timeZone;
    }
}