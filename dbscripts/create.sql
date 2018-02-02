CREATE TABLE esales.products
(
  id integer,
  name varchar,
  description varchar,
  code varchar
)
WITH (
  OIDS=FALSE
);
ALTER TABLE esales.test
  OWNER TO postgres;
CREATE SEQUENCE product_id_seq;

ALTER TABLE esales.products
    ALTER COLUMN id SET DEFAULT nextval('product_id_seq');


create table esales.routes
(
	id integer,
	name varchar,
	description varchar,
	data varchar

) 

WITH (
  OIDS=FALSE
);
ALTER TABLE esales.routes
  OWNER TO postgres;
CREATE SEQUENCE route_id_seq;

ALTER TABLE esales.routes
    ALTER COLUMN id SET DEFAULT nextval('route_id_seq');



create table esales.route_user_mapping
(
   id integer,
   route_id integer,
   user_id integer
)
WITH (
  OIDS=FALSE
);
ALTER TABLE esales.route_user_mapping
OWNER TO postgres;
CREATE SEQUENCE route_user_mapping_seq;

ALTER TABLE esales.routes
    ALTER COLUMN id SET DEFAULT nextval('route_user_mapping_seq');

