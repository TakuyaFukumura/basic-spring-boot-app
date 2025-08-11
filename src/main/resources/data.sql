DELETE FROM message;
INSERT INTO message (text) VALUES ('Hello World!');

DELETE FROM users;
-- user/password (BCrypt hash of "password")
INSERT INTO users (username, password, roles, enabled) VALUES ('user', '$2a$10$XY0d9WX4.TfZPJnYVN7/6OK5m8q0T8qZ8EfE6DZk6Fq1BT5v0vQ8G', 'USER', true);
-- admin/admin (BCrypt hash of "admin") 
INSERT INTO users (username, password, roles, enabled) VALUES ('admin', '$2a$10$X/k1xhGK2sO5Qs7Eo3QbFu2oI5d2Bl7zG4iC5mA8fM3kN6pD9oK8Q', 'ADMIN,USER', true);
