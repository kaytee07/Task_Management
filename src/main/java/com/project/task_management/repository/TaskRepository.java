package com.project.task_management.repository;
import com.project.task_management.model.Task;
import com.project.task_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByCreatedBy(User user);

    List<Task> findByCreatedByAndStatus(User user, Task.TaskStatus status);

    List<Task> findByCreatedByAndTitleContainingIgnoreCase(User user, String title);

    long countByCreatedByAndStatus(User user, Task.TaskStatus status);
}