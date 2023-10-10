package com.project.to_do_backend.model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.Email;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String username;

    @Email
    private String email;

    private String password;

    private LocalDateTime creationDate;

    private boolean isVerified;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<MainTask> tasks;

    // Create a private and static field for password encoding using
    // BCryptPasswordEncoder.
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Method to encrypt (hash) the user's password.
     * This method replaces the plain text password with its hashed version.
     */
    public void encryptPassword() {
        this.password = passwordEncoder.encode(this.password);
    }

    public User() {
        this.creationDate = LocalDateTime.now();
        this.isVerified = Boolean.FALSE;
    }

    public User(UUID id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Set<MainTask> getTasks() {
        return tasks;
    }

    public void setTasks(Set<MainTask> tasks) {
        this.tasks = tasks;
    }

    public boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

}
