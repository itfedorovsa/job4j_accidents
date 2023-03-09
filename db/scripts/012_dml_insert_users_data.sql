INSERT INTO users (username, enabled, password, authority_id)
VALUES ('admin', true, '$2a$10$v9WEnDOezpisdPjv9xZNg.9a.VXOI8EsoE5bDgwp5leVcm2whA6gK',
(SELECT id FROM authorities WHERE authority = 'ROLE_ADMIN'));