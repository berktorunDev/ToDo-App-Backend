package com.project.to_do_backend.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class UserDTO {
    private UUID id;
    private String username;
    private String email;
    private LocalDateTime creationDate;
    private Set<MainTaskDTO> tasks;
    private Boolean isVerified;

    public UserDTO() {
    }

    public UserDTO(UUID id, String username, String email, LocalDateTime creationDate,
            Set<MainTaskDTO> tasks, Boolean isVerified) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.creationDate = creationDate;
        this.tasks = tasks;
        this.isVerified = isVerified;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Set<MainTaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(Set<MainTaskDTO> tasks) {
        this.tasks = tasks;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

}
