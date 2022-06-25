DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id)
VALUES  ('2022-06-20 13:00' , 'Admin launch', 510, 100001 ),
        ('2022-06-20 19:00', 'Admin dinner', 1500, 100001 ),
        ('2022-06-20 13:00', 'User launch', 500, 100000 ),
        ('2022-06-20 20:00', 'User dinner', 1500, 100000 ),
        ('2022-06-21 14:00', 'User launch', 600, 100000 ),
        ('2022-06-21 20:00', 'User dinner', 1500, 100000 );