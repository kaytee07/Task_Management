package com.project.task_management.dto;

import com.project.task_management.model.Task;
import com.project.task_management.dto.UserDto;

import java.util.UUID;

public class TaskDto {
    private UUID id;
    private String title;
    private String description;
    private Task.TaskStatus status;
    private UserDto createdBy;

    public TaskDto() {}

    public TaskDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.status = task.getStatus();
        this.createdBy = new UserDto(task.getCreatedBy());
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Task.TaskStatus getStatus() { return status; }
    public void setStatus(Task.TaskStatus status) { this.status = status; }
    public UserDto getCreatedBy() { return createdBy; }
    public void setCreatedBy(UserDto createdBy) { this.createdBy = createdBy; }
}
