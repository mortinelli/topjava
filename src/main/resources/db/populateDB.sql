DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals(datetime, description, calories, user_id) VALUES
  ('2015-05-30 10:00', 'Breackfast',1000,100000),
  ('2015-05-30 13:00', 'Diner',500,100000),
  ('2015-05-30 20:00', 'Evning',200,100000),
('2015-05-31 10:00', 'Breackfast',1000,100000),
('2015-05-31 13:00', 'Diner',600,100000),
('2015-05-31 20:00', 'Evning',100,100000),
('2015-05-30 10:00', 'Breackfast',1000,100001),
('2015-05-30 13:00', 'Diner',500,100001),
('2015-05-30 20:00', 'Evning',200,100001),
('2015-05-31 10:00', 'Breackfast',1000,100001),
('2015-05-31 13:00', 'Diner',600,100001),
('2015-05-31 20:00', 'Evning',100,100001);
