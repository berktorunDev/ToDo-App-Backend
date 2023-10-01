package com.project.to_do_backend.dto;

import java.util.UUID;

import com.project.to_do_backend.model.PriorityType;

public class PriorityDTO {
    private UUID id;
    private PriorityType priorityType;

    public PriorityDTO() {
    }

    public PriorityDTO(UUID id, PriorityType priorityType) {
        this.id = id;
        this.priorityType = priorityType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PriorityType getPriorityType() {
        return priorityType;
    }

    public void setPriorityType(PriorityType priorityType) {
        this.priorityType = priorityType;
    }

}
