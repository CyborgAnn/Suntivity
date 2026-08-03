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



    /**
     * Changes username.
     *
     * @param userName new username
     */
    public void setUserName(String userName) {

        this.userName = userName;

    }



    /**
     * Gets username.
     *
     * @return username
     */
    public String getUserName() {

        return userName;

    }



    /**
     * Changes password.
     *
     * @param password new password
     */
    public void setPassword(String password) {

        this.password = password;

    }



    /**
     * Gets password.
     *
     * @return password
     */
    public String getPassword() {

        return password;

    }



    /**
     * Changes timezone.
     *
     * @param timeZone timezone offset
     */
    public void setTimeZone(int timeZone) {

        this.timeZone = timeZone;

    }



    /**
     * Gets timezone.
     *
     * @return timezone offset
     */
    public int getTimeZone() {

        return timeZone;

    }

}