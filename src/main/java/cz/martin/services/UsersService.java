package cz.martin.services;

import cz.martin.interfaces.repositories.IUsersRepository;
import cz.martin.interfaces.services.IUsersService;
import cz.martin.models.User;
import cz.martin.qualifiers.Normal;
import cz.martin.repositories.UsersRepository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Normal
public class UsersService implements IUsersService {

    @Inject
    @Normal
    private IUsersRepository usersRepository;

    private List<User> users;

    @PostConstruct
    public void init() {
        this.users = this.usersRepository.load();
    }

    @Override
    public Optional<User> addNewUser(User newUser) {
        Optional<User> u = this.users.stream().filter(i -> i.getUsername().equals(newUser.getUsername())).findAny();
        if(u.isPresent()) return Optional.empty();
        this.users.add(newUser);
        this.usersRepository.save(this.users);
        return Optional.of(newUser);
    }

    @Override
    public void saveChanges() {
        this.usersRepository.save(this.users);
    }

    @Override
    public Optional<User> getUserByCredentials(String username, String password) {
        Optional<User> user = this.users.stream().filter(i -> i.getUsername().equals(username)).findAny();
        if(user.isEmpty()) return user;
        if(BCrypt.checkpw(password, user.get().getPasswordHash())) return user;
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserById(String id) {
        return this.users.stream().filter(i -> i.getId().equals(id)).findAny();
    }

    @Override
    public List<User> getUsers() {
        return users;
    }
}
