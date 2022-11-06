package cz.martin.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.UUID;

public class User {
    private String id;
    private String username;
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User(String username, String password) {
        this.username = username;
        this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt(8));
        this.id = UUID.randomUUID().toString();
        this.role = UserRole.Reader;
    }

    public boolean isEditor() {
        return this.role == UserRole.Editor || this.role == UserRole.SuperUser;
    }

    public boolean isSuperUser() {
        return this.role == UserRole.SuperUser;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
