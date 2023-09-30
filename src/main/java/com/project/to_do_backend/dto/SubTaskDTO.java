package com.project.to_do_backend.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.project.to_do_backend.model.PriorityType;
import com.project.to_do_backend.model.StatusType;

public class SubTaskDTO extends BaseTaskDTO {
    private UUID mainTaskId;

    public SubTaskDTO(UUID id, String description, PriorityType priority, StatusType status, LocalDateTime creationDate,
            UUID userId, UUID mainTaskId) {
        super(id, description, priority, status, creationDate, userId);
        this.mainTaskId = mainTaskId;
    }

    public UUID getMainTaskId() {
        return mainTaskId;
    }

    public void setMainTaskId(UUID mainTaskId) {
        this.mainTaskId = mainTaskId;
    }

}
