package cz.martin.beans;

import cz.martin.interfaces.services.IActiveUserService;
import cz.martin.qualifiers.Normal;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

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

    public void register() {

    }
}
