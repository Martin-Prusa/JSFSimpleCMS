package cz.martin.interfaces.services;

import cz.martin.models.User;

public interface IActiveUserService {
    boolean login(String username, String password);
    boolean register(String username, String password);
    void logout();
    User getActiveUser();

    boolean isLoggedIn();
}
