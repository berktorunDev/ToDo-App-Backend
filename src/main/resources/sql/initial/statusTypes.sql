-- insert
INSERT INTO status (id, status_type) VALUES (uuid_generate_v4(), 'NEW');
INSERT INTO status (id, status_type) VALUES (uuid_generate_v4(), 'IN_PROGRESS');
INSERT INTO status (id, status_type) VALUES (uuid_generate_v4(), 'COMPLETED');
INSERT INTO status (id, status_type) VALUES (uuid_generate_v4(), 'ON_HOLD');
INSERT INTO status (id, status_type) VALUES (uuid_generate_v4(), 'CANCELLED');
INSERT INTO status (id, status_type) VALUES (uuid_generate_v4(), 'POSTPONED');