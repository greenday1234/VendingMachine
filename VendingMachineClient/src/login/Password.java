package login;

public class Password {

    private String password;

    public Password(String password) {
        this.password = password;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
