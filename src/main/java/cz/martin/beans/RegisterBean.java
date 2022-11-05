package cz.martin.beans;

import cz.martin.interfaces.services.IActiveUserService;
import cz.martin.qualifiers.Normal;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;

@Named("register")
@SessionScoped
public class RegisterBean implements Serializable {

    @Inject
    @Normal
    private IActiveUserService activeUserService;

    private String username;
    private String password;
    private String passwordCheck;
    private String error;

    public void register() throws IOException {
        error = "";
        if (password.equals(passwordCheck) && activeUserService.register(username, password))
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        else error = "Passwords do not match or username is used";
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public String getError() {
        return error;
    }
}
