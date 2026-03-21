CREATE DATABASE IF NOT EXISTS quantity_db;

USE quantity_db;
CREATE TABLE IF NOT EXISTS quantity_measurement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    operation VARCHAR(50),
    value DOUBLE,
    unit VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);