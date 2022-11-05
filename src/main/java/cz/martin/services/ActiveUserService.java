package cz.martin.services;

import cz.martin.models.User;
import cz.martin.qualifiers.Normal;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.Optional;

@SessionScoped
@Normal
public class ActiveUserService implements Serializable {
    @Inject
    @Normal
    private UsersService usersService;

    private User activeUser;

    public boolean login(String username, String password) {
        Optional<User> u = usersService.getUserByCredentials(username, password);
        if(u.isEmpty()) return false;
        this.activeUser = u.get();
        return true;
    }

    public boolean register(String username, String password) {
        Optional<User> u = usersService.addNewUser(new User(username, password));
        if(u.isEmpty()) return false;
        this.activeUser = u.get();
        return true;
    }

    public void logout() {
        this.activeUser = null;
    }

    public User getActiveUser() {
        return activeUser;
    }
}
