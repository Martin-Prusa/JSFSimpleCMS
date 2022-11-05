package cz.martin.models;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    private String id;
    private String username;
    private String passwordHash;
    private ArrayList<UserRole> roles = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt(8));
        this.id = UUID.randomUUID().toString();
        this.roles.add(UserRole.Reader);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public ArrayList<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<UserRole> roles) {
        this.roles = roles;
    }
}
