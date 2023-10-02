package com.project.to_do_backend.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.project.to_do_backend.model.PriorityType;
import com.project.to_do_backend.model.StatusType;

public class MainTaskDTO extends BaseTaskDTO {

    private Set<SubTaskDTO> subTasks;

    public MainTaskDTO() {
    }

    public MainTaskDTO(UUID id, String description, PriorityType priority, StatusType status,
            LocalDateTime creationDate, UUID userId) {
        super(id, description, priority, status, creationDate, userId);
    }

    public MainTaskDTO(UUID id, String description, PriorityType priority, StatusType status,
            LocalDateTime creationDate, UUID userId, Set<SubTaskDTO> subTasks) {
        super(id, description, priority, status, creationDate, userId);
        this.subTasks = subTasks;
    }

    public Set<SubTaskDTO> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(Set<SubTaskDTO> subTasks) {
        this.subTasks = subTasks;
    }

}
