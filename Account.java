package suntivity_model;

public abstract class Account {

    private String userName;
    private String password;
    private int timeZone;

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
