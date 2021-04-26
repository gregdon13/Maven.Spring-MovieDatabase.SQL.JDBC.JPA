DROP TABLE IF EXISTS PERSON;

CREATE TABLE PERSON (
  ID INT NOT NULL AUTO_INCREMENT,
  FIRST_NAME VARCHAR(255) NOT NULL DEFAULT '',
  LAST_NAME VARCHAR(255) NOT NULL DEFAULT '',
  MOBILE VARCHAR(20) NOT NULL DEFAULT '',
  BIRTHDAY DATE DEFAULT NULL,
  HOME_ID SMALLINT DEFAULT NULL,
  PRIMARY KEY (ID));



INSERT INTO PERSON (FIRST_NAME, LAST_NAME, MOBILE, BIRTHDAY, HOME_ID) VALUES
('Greg', 'Donnelly', '528-9535', '1995-04-27', 1),
('Abe', 'Figueroa', '589-9514', '1989-05-19', 2),
('Katie', 'Sharpe', '543-3543', '1995-07-15', 1),
('George', 'Tapia', '456-9853', '1993-09-28', 3);

INSERT INTO PERSON ( LAST_NAME, FIRST_NAME, MOBILE, BIRTHDAY, HOME_ID ) VALUES ('Carbral', 'Sheeri', '230-4233', '1970-02-23', 2);
INSERT INTO PERSON ( LAST_NAME, FIRST_NAME, MOBILE, BIRTHDAY, HOME_ID) VALUES ( 'Sharam', 'Raj', '186-5223', '1980-08-31', 3);
INSERT INTO PERSON ( LAST_NAME, FIRST_NAME, MOBILE, BIRTHDAY, HOME_ID)VALUES ('Durand', 'Noelle', '395-6161', '1960-07-06', 1);
INSERT INTO PERSON ( LAST_NAME, FIRST_NAME, MOBILE, BIRTHDAY, HOME_ID)VALUES ('Smith', 'Thomas', '395-6181', '1987-07-06', 1);
INSERT INTO PERSON ( LAST_NAME, FIRST_NAME, MOBILE, BIRTHDAY, HOME_ID)VALUES ('Smith', 'Jane', '393-6181', '1987-12-06', 3);
INSERT INTO PERSON ( LAST_NAME, FIRST_NAME, MOBILE, BIRTHDAY, HOME_ID)VALUES ('Brown', 'Doug', '466-6241', '1954-12-07', 3);

DROP TABLE IF EXISTS HOME;

CREATE TABLE HOME (
  ID INT NOT NULL AUTO_INCREMENT,
  ADDRESS VARCHAR(255) not null default '',
  HOME_NUMBER varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (ID)
);

INSERT INTO HOME (ADDRESS, HOME_NUMBER) VALUES
('36 E. Bayberry Rd. Savannah, GA 31404', '565-6895'),
('11 Essex Dr. Farmingdale, NY 11735', '454-4544'),
('920 Arlington Street Clifton, NJ 07011', '985-4515'),
('234 High Street, PA 19159', '267-3940');

-- ALTER TABLE PERSON
--     ADD FOREIGN KEY (HOME_ID)
--         REFERENCES HOME(ID);

DROP TABLE IF EXISTS movies;

CREATE TABLE movies (
  id INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(100) NOT NULL UNIQUE,
  runtime SMALLINT NOT NULL,
  genre VARCHAR(50),
  imdb_score SMALLINT,
  rating VARCHAR(100)
);

-- Tables for in-class example

DROP TABLE IF EXISTS cars;

CREATE TABLE cars (
  id INT NOT NULL AUTO_INCREMENT,
  make VARCHAR(50) NOT NULL DEFAULT '',
  model VARCHAR(50) NOT NULL DEFAULT '',
  year VARCHAR(5) NOT NULL DEFAULT '01907',
  PRIMARY KEY (id),
  CONSTRAINT unique_make_model_year UNIQUE (make, model, year)

);

DROP TABLE IF EXISTS auto_prices;

CREATE TABLE auto_prices (
  id INT PRIMARY KEY AUTO_INCREMENT,
  car_id INT REFERENCES cars(id),
  package VARCHAR(15) NOT NULL,
  price INTEGER NOT NULL CHECK(price > 0),
  CONSTRAINT unique_package_per_car UNIQUE (car_id, package)


);


-- DROP SEQUENCE hibernate_sequence;
--
-- CREATE SEQUENCE hibernate_sequence;
