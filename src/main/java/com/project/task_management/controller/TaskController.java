package com.project.task_management.controller;
import com.project.task_management.dto.TaskCreationDto;
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
    public ResponseEntity<Task> createTask(
            @RequestHeader("X-User-Id") UUID userId,
            @RequestBody TaskCreationDto taskDto) {
        try {
            Task createdTask = taskService.createTask(userId, taskDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create task", ex);
        }
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTask(
            @RequestHeader("X-User-Id") UUID userId,
            @PathVariable UUID taskId) {
        Task task = taskService.getTaskById(taskId, userId);
        return ResponseEntity.ok(task);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getUserTasks(
            @RequestHeader("X-User-Id") UUID userId) {
        List<Task> tasks = taskService.getUserTasks(userId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<Task>> getUserTasksByStatus(
            @RequestHeader("X-User-Id") UUID userId,
            @RequestParam Task.TaskStatus status) {
        List<Task> tasks = taskService.getUserTasksByStatus(userId, status);
        return ResponseEntity.ok(tasks);
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(
            @RequestHeader("X-User-Id") UUID userId,
            @PathVariable UUID taskId,
            @RequestBody TaskUpdateDto updateDto) {
        Task updatedTask = taskService.updateTask(taskId, userId, updateDto);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @RequestHeader("X-User-Id") UUID userId,
            @PathVariable UUID taskId) {
        taskService.deleteTask(taskId, userId);
        return ResponseEntity.noContent().build();
    }
}
