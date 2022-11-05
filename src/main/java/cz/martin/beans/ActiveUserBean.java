package cz.martin.beans;

import cz.martin.interfaces.services.IActiveUserService;
import cz.martin.models.User;
import cz.martin.qualifiers.Normal;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("user")
@SessionScoped
public class ActiveUserBean implements Serializable {
    @Inject
    @Normal
    private IActiveUserService activeUserService;

    public void logout() {
        this.activeUserService.logout();
    }

    public boolean isLoggedIn() {
        return this.activeUserService.isLoggedIn();
    }

    public User getUser() {
        return activeUserService.getActiveUser();
    }
}
