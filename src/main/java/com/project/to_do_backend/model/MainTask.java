package com.project.to_do_backend.model;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class MainTask extends BaseTask {
    @OneToMany(mappedBy = "mainTask", cascade = CascadeType.ALL)
    private Set<SubTask> subTasks;

    public MainTask(UUID id, String description, Priority priority, Status status, User user) {
        super(id, description, priority, status, user);
    }

    public MainTask(UUID id, String description, Priority priority, Status status, Set<SubTask> subTasks, User user) {
        super(id, description, priority, status, user);
        this.subTasks = subTasks;
    }

    public Set<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(Set<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

}
