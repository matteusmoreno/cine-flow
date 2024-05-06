CREATE TABLE movies (
    id BINARY(16) PRIMARY KEY,
    title VARCHAR(255),
    released VARCHAR(255),
    runtime VARCHAR(20),
    genre VARCHAR(255),
    director VARCHAR(255),
    writer VARCHAR(255),
    actors VARCHAR(255),
    poster VARCHAR(255),
    added_at DATE,
    deleted_at DATE,
    active BOOLEAN
);
