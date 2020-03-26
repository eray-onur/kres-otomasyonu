package meltem.models;

public class User {
    private int userId;
    private String userName;
    private String password;
    private short userAuth;

    public User(int userId, String userName, String password, short userType) {

    }
    public User(int userId, String userName, String password) {
        this.setUserId(userId);
        this.setUserName(userName);
        this.setPassword(password);
    }
    public User(){}

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getUserAuth() {
        return this.userAuth;
    }

    public void setUserAuth(short userAuth) {
        this.userAuth = userAuth;
    }
}
