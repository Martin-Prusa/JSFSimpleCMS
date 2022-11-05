package cz.martin.services;

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
public class UsersService {

    @Inject
    @Normal
    private UsersRepository usersRepository;

    private List<User> users;

    @PostConstruct
    public void init() {
        this.users = this.usersRepository.load();
    }

    public Optional<User> addNewUser(User newUser) {
        Optional<User> u = this.users.stream().filter(i -> i.getUsername().equals(newUser.getUsername())).findAny();
        if(u.isPresent()) return Optional.empty();
        this.users.add(newUser);
        this.usersRepository.save(this.users);
        return Optional.of(newUser);
    }

    public Optional<User> getUserByCredentials(String username, String password) {
        Optional<User> user = this.users.stream().filter(i -> i.getUsername().equals(username)).findAny();
        if(user.isEmpty()) return user;
        if(BCrypt.checkpw(password, user.get().getPasswordHash())) return user;
        return Optional.empty();
    }

    public List<User> getUsers() {
        return users;
    }
}
