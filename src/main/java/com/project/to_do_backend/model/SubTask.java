package com.project.to_do_backend.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SubTask extends BaseTask {
    @ManyToOne
    @JoinColumn(name = "main_task_id")
    private MainTask mainTask;

    public SubTask(UUID id, String description, Priority priority, Status status, User user, MainTask mainTask) {
        super(id, description, priority, status, user);
        this.mainTask = mainTask;
    }

    public MainTask getMainTask() {
        return mainTask;
    }

    public void setMainTask(MainTask mainTask) {
        this.mainTask = mainTask;
    }

}
