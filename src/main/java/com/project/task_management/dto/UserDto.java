package com.project.task_management.dto;

import com.project.task_management.model.User;
import java.util.UUID;

public class UserDto {
    private UUID Id;
    private String username;
    private String firstName;
    private String lastName;

    public UserDto() {}

    public UserDto(User user) {
        this.Id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }
    public UUID getId() { return Id; }
    public void setId(UUID id) { this.Id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}