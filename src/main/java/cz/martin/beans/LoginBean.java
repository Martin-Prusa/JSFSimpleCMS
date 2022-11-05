package cz.martin.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("login")
@RequestScoped
public class LoginBean {
    private String username = "";
    private String password = "";

    public void login() {
        System.out.println(username+" "+password);
    }

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
