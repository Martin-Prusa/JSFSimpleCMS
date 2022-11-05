package cz.martin.services;

import cz.martin.interfaces.services.IActiveUserService;
import cz.martin.interfaces.services.IUsersService;
import cz.martin.models.User;
import cz.martin.qualifiers.Normal;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.Optional;

@SessionScoped
@Normal
public class ActiveUserService implements Serializable, IActiveUserService {
    @Inject
    @Normal
    private IUsersService usersService;

    private User activeUser;

    @Override
    public boolean login(String username, String password) {
        Optional<User> u = usersService.getUserByCredentials(username, password);
        if(u.isEmpty()) return false;
        this.activeUser = u.get();
        return true;
    }

    @Override
    public boolean register(String username, String password) {
        Optional<User> u = usersService.addNewUser(new User(username, password));
        if(u.isEmpty()) return false;
        this.activeUser = u.get();
        return true;
    }

    @Override
    public void logout() {
        this.activeUser = null;
    }

    @Override
    public boolean isLoggedIn() {
        return this.activeUser != null;
    }

    @Override
    public User getActiveUser() {
        return activeUser;
    }
}
