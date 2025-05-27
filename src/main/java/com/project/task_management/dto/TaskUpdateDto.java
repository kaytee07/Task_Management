package com.project.task_management.dto;

import com.project.task_management.model.Task;

import java.time.Instant;
import java.util.UUID;

public class TaskUpdateDto {
    private String title;
    private String description;
    private Task.TaskStatus status;
    private Instant deadline;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Task.TaskStatus getStatus() {
        return status;
    }


    public Instant getDeadline() {
        return deadline;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Task.TaskStatus status) {
        this.status = status;
    }


    public void setDeadline(Instant deadline) {
        this.deadline = deadline;
    }
}
