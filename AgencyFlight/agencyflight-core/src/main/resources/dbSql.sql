CREATE DATABASE dbagencyflight
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'C'
       LC_CTYPE = 'C'
       CONNECTION LIMIT = -1;
CREATE TABLE users
(
  id serial primary key,
  name character varying(100) NOT NULL,
  password character varying(100),
  role integer, active boolean, createdate date,modifydate date,
  lastmodifier smallint,
  foreign key(lastmodifier) references users(id) on update cascade on delete cascade
);
