package cz.martin.beans;

import cz.martin.interfaces.services.IActiveUserService;
import cz.martin.qualifiers.Normal;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;

@Named("login")
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
    @Normal
    private IActiveUserService activeUserService;

    private String username = "";
    private String password = "";

    private String error = "";

    public void login() throws IOException {
        error = "";
        if(this.activeUserService.login(this.username, this.password)) FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        else error = "Invalid username or password";
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

    public String getError() {
        return error;
    }
}
