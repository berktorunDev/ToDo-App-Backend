CREATE TABLE IF NOT EXISTS startup_control (
    id serial PRIMARY KEY,
    isInitialized BOOLEAN
);

INSERT INTO startup_control (id, isInitialized) VALUES (1, true);