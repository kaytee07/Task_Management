package com.project.task_management.service;
import com.project.task_management.dto.TaskCreationDto;
import com.project.task_management.dto.TaskUpdateDto;
import com.project.task_management.model.Task;
import com.project.task_management.model.User;
import com.project.task_management.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Transactional
    public Task createTask(UUID userId, TaskCreationDto taskDto) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(Task.TaskStatus.IN_PROGRESS);
        task.setCreatedBy(user);

        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(UUID taskId, UUID userId, TaskUpdateDto updateDto) {
        Task task = getTaskIfOwner(taskId, userId);

        if (updateDto.getTitle() != null) {
            task.setTitle(updateDto.getTitle());
        }
        if (updateDto.getDescription() != null) {
            task.setDescription(updateDto.getDescription());
        }
        if (updateDto.getStatus() != null) {
            task.setStatus(updateDto.getStatus());
        }

        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(UUID taskId, UUID userId) {
        Task task = getTaskIfOwner(taskId, userId);
        taskRepository.delete(task);
    }

    public Task getTaskById(UUID taskId, UUID userId) {
        return getTaskIfOwner(taskId, userId);
    }

    public List<Task> getUserTasks(UUID userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return taskRepository.findByCreatedBy(user);
    }

    public List<Task> getUserTasksByStatus(UUID userId, Task.TaskStatus status) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return taskRepository.findByCreatedByAndStatus(user, status);
    }

    private Task getTaskIfOwner(UUID taskId, UUID userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));

        if (!task.getCreatedBy().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not authorized to access this task");
        }

        return task;
    }
}