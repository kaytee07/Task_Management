package com.project.task_management.controller;
import com.project.task_management.dto.TaskCreationDto;
import com.project.task_management.dto.TaskDto;
import com.project.task_management.dto.TaskUpdateDto;
import com.project.task_management.model.Task;
import com.project.task_management.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(
            @RequestHeader("createdById") UUID userId,
            @RequestBody TaskCreationDto taskDto) {
        try {
            TaskDto createdTask = taskService.createTask(userId, taskDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create task", ex);
        }
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTask(
            @RequestHeader("createdById") UUID userId,
            @PathVariable UUID taskId) {
        TaskDto task = taskService.getTaskById(taskId, userId);
        return ResponseEntity.ok(task);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getUserTasks(
            @RequestHeader("createdById") UUID userId) {
        List<TaskDto> tasks = taskService.getUserTasks(userId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<TaskDto>> getUserTasksByStatus(
            @RequestHeader("createdById") UUID userId,
            @RequestParam Task.TaskStatus status) {
        List<TaskDto> tasks = taskService.getUserTasksByStatus(userId, status);
        return ResponseEntity.ok(tasks);
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask(
            @RequestHeader("createdById") UUID userId,
            @PathVariable UUID taskId,
            @RequestBody TaskUpdateDto updateDto) {
        TaskDto updatedTask = taskService.updateTask(taskId, userId, updateDto);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @RequestHeader("createdById") UUID userId,
            @PathVariable UUID taskId) {
        taskService.deleteTask(taskId, userId);
        return ResponseEntity.noContent().build();
    }
}
