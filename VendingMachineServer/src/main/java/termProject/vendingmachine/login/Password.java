package termProject.vendingmachine.login;

import lombok.Getter;

@Getter
public class Password {

    private String password;

    public Password(String password) {
        this.password = password;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
