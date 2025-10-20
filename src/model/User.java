package model;

import java.io.Serializable;

public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String username;
    private String password;
    private Role role;

    public User(int id, String name, String username, String password, Role role) {
        validateInput(name, username, password);
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password; // In a real system, hash the password
        this.role = role;
    }

    private void validateInput(String name, String username, String password) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateInput(name, this.username, this.password);
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        validateInput(this.name, username, this.password);
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        validateInput(this.name, this.username, password);
        this.password = password; // In a real system, hash the password
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        this.role = role;
    }

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    @Override
    public String toString() {
        return "User [ID=" + id + ", Name=" + name + ", Role=" + role + "]";
    }
}