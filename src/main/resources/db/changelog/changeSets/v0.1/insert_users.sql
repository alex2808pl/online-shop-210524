-- liquibase formatted sql

-- changeset yulia:insert_users
insert into users (name, email, phoneNumber, passwordHash, role)
values
    ('Bill Smith','billsmith@example.com', '+4915245783',  '$2a$10$78nKEFFKJOyH9Rhw2xsBKeTU9cqq7/uwVsBx1MZ9WZVc7LBFA0Fgu', 'CLIENT'),
    ('Jane Miller','janemiller@example.com', '+4915736548',  '$2a$10$78nKEFFKJOyH9Rhw2xsBKeTU9cqq7/uwVsBx1MZ9WZVc7LBFA0Fgu', 'CLIENT'),
    ('Big Boss','bigboss@example.com', '+491586666',  '$2a$10$78nKEFFKJOyH9Rhw2xsBKeTU9cqq7/uwVsBx1MZ9WZVc7LBFA0Fgu', 'ADMIN');