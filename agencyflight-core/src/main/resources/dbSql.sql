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

CREATE TABLE products
(
  id serial NOT NULL,
  code character varying(100) NOT NULL,
  name character varying(100) NOT NULL,
  price double precision NOT NULL,
  service_charge double precision,
  active boolean,
  createdate date,
  modifydate date,
  lastmodifier smallint NOT NULL,
  CONSTRAINT products_pkey PRIMARY KEY (id),
  CONSTRAINT "products_lastmodifier_fkey" FOREIGN KEY (lastmodifier)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT products_code_key UNIQUE (code)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE products
  OWNER TO postgres;