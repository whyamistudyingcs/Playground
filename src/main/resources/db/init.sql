CREATE TABLE USER (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO USER (username, email) VALUES ('john_doe', 'john@example.com');
INSERT INTO USER (username, email) VALUES ('jane_smith', 'jane@example.com');