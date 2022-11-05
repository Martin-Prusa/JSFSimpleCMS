package cz.martin.beans;

import cz.martin.interfaces.services.IActiveUserService;
import cz.martin.interfaces.services.IUsersService;
import cz.martin.models.User;
import cz.martin.models.UserRole;
import cz.martin.qualifiers.Normal;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("userManagement")
@SessionScoped
public class UserManagementBean implements Serializable {

    @Inject
    @Normal
    private IUsersService usersService;

    @Inject
    @Normal
    private IActiveUserService activeUserService;

    public void saveChanges() throws IOException {
        if(!activeUserService.isLoggedIn() || !activeUserService.getActiveUser().isSuperUser()) return;
        System.out.println("a");
        this.usersService.saveChanges();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

    public UserRole[] roles() {
        return UserRole.values();
    }

    public List<User> getUsers() {
        if(!activeUserService.isLoggedIn() || !activeUserService.getActiveUser().isSuperUser()) return new ArrayList<>();
        return usersService.getUsers();
    }
}
