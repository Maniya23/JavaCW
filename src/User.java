public class User {
    private String username;
    private String password;

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
}
