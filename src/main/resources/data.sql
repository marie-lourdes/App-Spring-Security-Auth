DROP TABLE IF EXISTS dbuser;

CREATE TABLE dbuser (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  role VARCHAR(250) NOT NULL
);
/*structure et valeur chargé automatiqument dans H2 bdd  par Spring data avec la configuration datasource de application.properties*/ 
INSERT INTO dbuser (username, password, role) VALUES ('dbuser', '$2a$10$qQxjbJtOlIvn0gV/AUIvQ.g8gKtg7mMY1cxx7ChiKLW7rjSHtJ45m', 'USER'),
('dbadmin', '$2a$10$QrYhxG2bksZsWfwJKfSW2eRnvo7ngJXu42kirmYwsu/hVZ8hq79kO', 'ADMIN');