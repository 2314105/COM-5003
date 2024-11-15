-- Create table if it doesn't already exist
CREATE TABLE IF NOT EXISTS USERS (
    id INT AUTO_INCREMENT PRIMARY KEY,  -- or use 'IDENTITY' for H2 depending on your version
    name VARCHAR(255) NOT NULL
    );

-- Clear all rows from the USERS table
TRUNCATE TABLE USERS;

-- Insert sample data
INSERT INTO USERS (name) VALUES ('Marco');
INSERT INTO USERS (name) VALUES ('Lisa');