-- Create users table with UUID
CREATE TABLE IF NOT EXISTS users (
                                     id CHAR(36) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    username VARCHAR(150) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY (username)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create tasks table with UUID and ENUM status
CREATE TABLE IF NOT EXISTS tasks (
                                     id CHAR(36) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    status ENUM('TODO', 'IN_PROGRESS', 'DONE', 'ARCHIVED') NOT NULL DEFAULT 'TODO',
    created_by CHAR(36) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Optional: Add trigger for UUID generation if not handled by application
DELIMITER //
CREATE TRIGGER before_user_insert
    BEFORE INSERT ON users
    FOR EACH ROW
BEGIN
    IF NEW.id IS NULL THEN
        SET NEW.id = UUID();
END IF;
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER before_task_insert
    BEFORE INSERT ON tasks
    FOR EACH ROW
BEGIN
    IF NEW.id IS NULL THEN
        SET NEW.id = UUID();
END IF;
END//
DELIMITER ;

-- Create indexes for better performance
CREATE INDEX idx_task_status ON tasks(status);
CREATE INDEX idx_task_created_by ON tasks(created_by);