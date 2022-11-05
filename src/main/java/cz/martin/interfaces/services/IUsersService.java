package cz.martin.interfaces.services;

import cz.martin.models.User;

import java.util.List;
import java.util.Optional;

public interface IUsersService {
    Optional<User> addNewUser(User newUser);
    void saveChanges();
    Optional<User> getUserByCredentials(String username, String password);
    Optional<User> getUserById(String id);
    List<User> getUsers();
}
