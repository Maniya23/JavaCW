import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private boolean UserFound=false;

    User(String username, String password){
        this.username=username;
        this.password=password;
    }

    //Getters
    public String getUsername() {
        return username;
    }

    protected String getPassword() {
        return password;
    }

    //Setters

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserFound(boolean userFound) {
        this.UserFound = userFound;
    }

    public boolean isUserFound() {
        return UserFound;
    }
}
