-- insert
INSERT INTO priority (id, priority_type) VALUES (uuid_generate_v4(), 'CRITICAL');
INSERT INTO priority (id, priority_type) VALUES (uuid_generate_v4(), 'HIGH');
INSERT INTO priority (id, priority_type) VALUES (uuid_generate_v4(), 'MEDIUM');
INSERT INTO priority (id, priority_type) VALUES (uuid_generate_v4(), 'LOW');