package hu.zolkasza.hw.model.ui;

import hu.zolkasza.hw.model.AbstractModel;

public class SauceLabUser extends AbstractModel {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
