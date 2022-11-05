package cz.martin.interfaces.repositories;

import cz.martin.models.User;

import java.util.List;

public interface IUsersRepository {
    List<User> load();

    void save(List<User> users);
}
