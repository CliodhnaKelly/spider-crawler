
CREATE SCHEMA IF NOT EXISTS spiderdb;

SET search_path TO spiderdb;

CREATE TABLE webpage(
   page_id serial PRIMARY KEY,
   url VARCHAR UNIQUE NOT NULL,
   time_indexed_at TIMESTAMP NOT NULL,
   raw_data VARCHAR NOT NULL
);

CREATE TABLE ads(
    keyword VARCHAR PRIMARY KEY,
    url VARCHAR NOT NULL
);

