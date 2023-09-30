package com.project.to_do_backend.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.project.to_do_backend.model.PriorityType;
import com.project.to_do_backend.model.StatusType;

public class BaseTaskDTO {
    private UUID id;
    private String description;
    private PriorityType priority;
    private StatusType status;
    private LocalDateTime creationDate;
    private UUID userId;

    public BaseTaskDTO(UUID id, String description, PriorityType priority, StatusType status,
            LocalDateTime creationDate, UUID userId) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.creationDate = creationDate;
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PriorityType getPriority() {
        return priority;
    }

    public void setPriority(PriorityType priority) {
        this.priority = priority;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

}
