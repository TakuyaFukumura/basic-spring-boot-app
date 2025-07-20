-- Drop table if exists to ensure clean state
DROP TABLE IF EXISTS message;

-- Create message table
CREATE TABLE message (
    id BIGINT AUTO_INCREMENT,
    text VARCHAR(255),
    PRIMARY KEY (id)
);