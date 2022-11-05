package cz.martin.interfaces.services;

import cz.martin.models.User;

import java.util.List;
import java.util.Optional;

public interface IUsersService {
    Optional<User> addNewUser(User newUser);
    Optional<User> getUserByCredentials(String username, String password);
    List<User> getUsers();
}