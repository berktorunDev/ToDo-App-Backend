package com.project.to_do_backend.dto;

import java.util.UUID;

import com.project.to_do_backend.model.StatusType;

public class StatusDTO {
    private UUID id;
    private StatusType statusType;

    public StatusDTO() {
    }

    public StatusDTO(UUID id, StatusType statusType) {
        this.id = id;
        this.statusType = statusType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

}
