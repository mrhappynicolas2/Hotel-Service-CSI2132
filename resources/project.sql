CREATE SCHEMA IF NOT EXISTS Hotels
    AUTHORIZATION pg_database_owner;

COMMENT ON SCHEMA "Hotels"
    IS 'SET search_path = "Hotels"';

CREATE TABLE IF NOT EXISTS "Hotels"."agreement"
(
    "agreement_num" character varying(20) NOT NULL,
    "startdate" date,
    "enddate" date,
    "status" character varying(20),
    "room" integer,
    "hotel" character varying(20),
    "ssn" integer,
    CONSTRAINT "agreement_pkey" PRIMARY KEY ("agreement_num")
);

CREATE TABLE IF NOT EXISTS "Hotels"."employee"
(
    "ssne" integer NOT NULL,
    "name" character varying(20),
    "adress" character varying(20),
    "username" character varying(20),
    "password" character varying(20),
    "hiredate" date,
    CONSTRAINT employee_pkey PRIMARY KEY ("ssne")
);

CREATE TABLE IF NOT EXISTS "Hotels"."customer"
(
    "ssnc" integer NOT NULL,
    "name" character varying(20),
    "adress" character varying(20),
    "username" character varying(20),
    "password" character varying(20),
    "registrationdate" date,
    "phone" integer,
    CONSTRAINT customer_pkey PRIMARY KEY ("ssnc")
);

CREATE TABLE IF NOT EXISTS "Hotels"."chain"
(
    "name" character varying(20) NOT NULL,
    "number_hotels" integer,
    "adress" character varying(20),
    "email" character varying(20),
    "phone" character varying(20),
    CONSTRAINT chain_pkey PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS "Hotels"."hotels"
(
    "name" character varying(20) NOT NULL,
    "number_rooms" integer,
    "chain" character varying(20),
    "stars" integer,
    "adress" character varying(20),
    "email" character varying(20),
    "phone" character varying(20),
    "location" character varying(30),
    CONSTRAINT hotels_pkey PRIMARY KEY (name),
    CONSTRAINT hotels_chain_fkey FOREIGN KEY ("chain")
        REFERENCES "Hotels"."chain" ("name") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "Hotels"."rooms"
(
    "room_num" integer NOT NULL,
    "room_type" character varying(20),
    "room_price" integer,
    "room_capacity" integer,
    "room_status" character varying(20),
    "room_annimities" character varying(20),
    "hotel_name" character varying(20),
    "agreement_num" character varying(20),
    
    CONSTRAINT rooms_pkey PRIMARY KEY ("room_num", "hotel_name"),
    CONSTRAINT rooms_agreement_num_fkey FOREIGN KEY ("agreement_num")
        REFERENCES "Hotels"."agreement" ("agreement_num") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT rooms_hotel_name_fkey FOREIGN KEY ("hotel_name")
        REFERENCES "Hotels"."hotels" (name) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
ALTER TABLE "Hotels"."agreement"
DROP CONSTRAINT IF EXISTS agreement_rooms_fkey;

ALTER TABLE "Hotels"."agreement"
ADD CONSTRAINT agreement_rooms_fkey FOREIGN KEY ("room","hotel") 
        REFERENCES "Hotels"."rooms" ("room_num", "hotel_name") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE;
        

ALTER TABLE "Hotels"."agreement"
DROP CONSTRAINT IF EXISTS agreement_ssn_fkey;

ALTER TABLE "Hotels"."agreement"
ADD CONSTRAINT agreement_ssn_fkey FOREIGN KEY ("ssn") 
        REFERENCES "Hotels"."customer" ("ssnc") MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE;
        
        
 
ALTER TABLE "Hotels"."agreement"
DROP CONSTRAINT IF EXISTS agreement_room_fkey;

ALTER TABLE "Hotels"."agreement"
ADD CONSTRAINT agreement_room_fkey FOREIGN KEY ("room", "hotel")
REFERENCES "Hotels"."rooms" ("room_num", "hotel_name") MATCH SIMPLE
ON UPDATE CASCADE
ON DELETE CASCADE;

ALTER TABLE "Hotels"."agreement"
DROP CONSTRAINT IF EXISTS agreement_customer_fkey;

ALTER TABLE "Hotels"."agreement"
ADD CONSTRAINT agreement_customer_fkey FOREIGN KEY ("ssn")
REFERENCES "Hotels"."customer" ("ssnc") MATCH SIMPLE
ON UPDATE CASCADE
ON DELETE CASCADE;
    

INSERT INTO "Hotels"."chain" ("name", "number_hotels", "adress", "email", "phone") VALUES ('chain1', 5, 'adress1', 'email1', 'phone1')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."chain" ("name", "number_hotels", "adress", "email", "phone") VALUES ('chain2', 5, 'adress2', 'email2', 'phone2')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."chain" ("name", "number_hotels", "adress", "email", "phone") VALUES ('chain3', 5, 'adress3', 'email3', 'phone3')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."chain" ("name", "number_hotels", "adress", "email", "phone") VALUES ('chain4', 5, 'adress4', 'email4', 'phone4')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."chain" ("name", "number_hotels", "adress", "email", "phone") VALUES ('chain5', 5, 'adress5', 'email5', 'phone5')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel1', 5, 'chain1', 1, 'adress1', 'email1', 'phone1', 'Madrid') ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel2', 5, 'chain1', 2, 'adress1', 'email2', 'phone2', 'Paris')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel3', 5, 'chain1', 3, 'adress3', 'email3', 'phone3', 'Opel')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel4', 5, 'chain1', 4, 'adress4', 'email4', 'phone4', 'Madrid')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel5', 5, 'chain1', 5, 'adress5', 'email5', 'phone5', 'Paris')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel6', 5, 'chain1', 1, 'adress6', 'email6', 'phone6', 'Madrid')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel7', 5, 'chain1', 2, 'adress7', 'email7', 'phone7', 'Ottawa')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel8', 5, 'chain1', 3, 'adress8', 'email8', 'phone8', 'Ottawa')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel11', 5, 'chain2', 1, 'adress11', 'email11', 'phone11', 'Paris')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel12', 5, 'chain2', 2, 'adress12', 'email12', 'phone12', 'Opel')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel13', 5, 'chain2', 3, 'adress13', 'email13', 'phone13', 'Madrid')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel14', 5, 'chain2', 4, 'adress14', 'email14', 'phone14', 'Paris')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel15', 5, 'chain2', 5, 'adress15', 'email15', 'phone15', 'Ottawa')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel16', 5, 'chain2', 1, 'adress16', 'email16', 'phone16', 'Madrid')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel17', 5, 'chain2', 2, 'adress17', 'email17', 'phone17', 'Ottawa')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel18', 5, 'chain2', 3, 'adress18', 'email18', 'phone18', 'Paris')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel21', 5, 'chain3', 1, 'adress21', 'email21', 'phone21', 'Madrid')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel22', 5, 'chain3', 2, 'adress22', 'email22', 'phone22', 'Opel')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel23', 5, 'chain3', 3, 'adress23', 'email23', 'phone23', 'Madrid')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel24', 5, 'chain3', 4, 'adress24', 'email24', 'phone24', 'Paris')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel25', 5, 'chain3', 5, 'adress25', 'email25', 'phone25', 'Madrid')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel26', 5, 'chain3', 1, 'adress26', 'email26', 'phone26', 'Ottawa')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel27', 5, 'chain3', 2, 'adress27', 'email27', 'phone27', 'Madrid')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel28', 5, 'chain3', 3, 'adress28', 'email28', 'phone28', 'Paris')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel31', 5, 'chain4', 1, 'adress31', 'email31', 'phone31', 'Paris')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel32', 5, 'chain4', 2, 'adress32', 'email32', 'phone32', 'Madrid')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel33', 5, 'chain4', 3, 'adress33', 'email33', 'phone33', 'Opel')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel34', 5, 'chain4', 4, 'adress34', 'email34', 'phone34', 'Madrid')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel35', 5, 'chain4', 5, 'adress35', 'email35', 'phone35', 'Paris')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel36', 5, 'chain4', 1, 'adress36', 'email36', 'phone36', 'Madrid')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel37', 5, 'chain4', 2, 'adress37', 'email37', 'phone37', 'Ottawa')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel38', 5, 'chain4', 3, 'adress38', 'email38', 'phone38', 'Madrid')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel41', 5, 'chain5', 1, 'adress41', 'email41', 'phone41', 'Paris')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel42', 5, 'chain5', 2, 'adress42', 'email42', 'phone42', 'Madrid')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel43', 5, 'chain5', 3, 'adress43', 'email43', 'phone43', 'Paris')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel44', 5, 'chain5', 4, 'adress44', 'email44', 'phone44', 'Madrid')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel45', 5, 'chain5', 5, 'adress45', 'email45', 'phone45', 'Opel')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel46', 5, 'chain5', 1, 'adress46', 'email46', 'phone46', 'Ottawa')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel47', 5, 'chain5', 2, 'adress47', 'email47', 'phone47', 'Paris')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel48', 5, 'chain5', 3, 'adress48', 'email48', 'phone48', 'Madrid')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."customer" ("ssnc", "name", "adress", "username", "password", "registrationdate", "phone") VALUES (145, 'customer1', 'adress1', 'username1', 'password1', '2018-01-01', 123456789)ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."customer" ("ssnc", "name", "adress", "username", "password", "registrationdate", "phone") VALUES (146, 'customer1', 'adress1', 'username1', 'password1', '2018-01-01', 133456789)ON CONFLICT DO NOTHING;


INSERT INTO "Hotels"."employee" ("ssne", "name", "adress", "username", "password", "hiredate") VALUES (201, 'employee1', 'adress1', 'employee1', 'password', '2018-01-01')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."employee" ("ssne", "name", "adress", "username", "password", "hiredate") VALUES (202, 'employee2', 'adress2', 'employee2', 'password', '2018-01-01')ON CONFLICT DO NOTHING;



INSERT INTO "Hotels"."agreement" ("agreement_num", "startdate", "enddate", "status", "room", "hotel", "ssn") VALUES ('0', '1999-01-01', '2018-12-31', 'empty', NULL, NULL, 145)ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."agreement" ("agreement_num", "startdate", "enddate", "status", "room", "hotel", "ssn") VALUES ('1', '2018-01-01', '2018-12-31', 'empty', NULL, NULL, 146)ON CONFLICT DO NOTHING;



INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 100, 1, 'free', 'tv', 'hotel1', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'sea view', 150, 2, 'free', 'tv', 'hotel1', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 170, 3, 'free', 'tv', 'hotel1', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'mountain view', 120, 4, 'free', 'tv', 'hotel1', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'sea view', 130, 5, 'free', 'tv', 'hotel1', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 120, 1, 'free', 'tv', 'hotel2', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 150, 2, 'free', 'tv', 'hotel2', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'sea view', 160, 3, 'free', 'tv', 'hotel2', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'mountain view', 130, 4, 'free', 'tv', 'hotel2', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 150, 5, 'free', 'tv', 'hotel2', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'sea view', 210, 1, 'free', 'tv', 'hotel3', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 130, 2, 'free', 'tv', 'hotel3', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'sea view', 130, 3, 'free', 'tv', 'hotel3', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 110, 4, 'free', 'tv', 'hotel3', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel3', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 210, 1, 'free', 'tv', 'hotel4', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'sea view', 130, 2, 'free', 'tv', 'hotel4', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel4', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'mountain view', 110, 4, 'free', 'tv', 'hotel4', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel4', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'sea view', 210, 1, 'free', 'tv', 'hotel5', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 130, 2, 'free', 'tv', 'hotel5', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'sea view', 130, 3, 'free', 'tv', 'hotel5', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'mountain view', 110, 4, 'free', 'tv', 'hotel5', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel5', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'sea view', 210, 1, 'free', 'tv', 'hotel6', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 130, 2, 'free', 'tv', 'hotel6', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel6', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 110, 4, 'free', 'tv', 'hotel6', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel6', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'sea view', 210, 1, 'free', 'tv', 'hotel7', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 130, 2, 'free', 'tv', 'hotel7', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel7', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 210, 4, 'free', 'tv', 'hotel7', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel7', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 110, 1, 'free', 'tv', 'hotel8', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'sea view', 130, 2, 'free', 'tv', 'hotel8', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel8', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 110, 4, 'free', 'tv', 'hotel8', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel8', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 210, 1, 'free', 'tv', 'hotel11', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'sea view', 130, 2, 'free', 'tv', 'hotel11', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel11', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 210, 4, 'free', 'tv', 'hotel11', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel11', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 110, 1, 'free', 'tv', 'hotel12', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'sea view', 130, 2, 'free', 'tv', 'hotel12', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel12', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'mountain view', 110, 4, 'free', 'tv', 'hotel12', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'sea view', 140, 5, 'free', 'tv', 'hotel12', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 210, 1, 'free', 'tv', 'hotel13', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'sea view', 180, 2, 'free', 'tv', 'hotel13', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel13', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'mountain view', 110, 4, 'free', 'tv', 'hotel13', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'sea view', 140, 5, 'free', 'tv', 'hotel13', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 210, 1, 'free', 'tv', 'hotel14', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 180, 2, 'free', 'tv', 'hotel14', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel14', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 110, 4, 'free', 'tv', 'hotel14', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel14', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 110, 1, 'free', 'tv', 'hotel15', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'sea view', 130, 2, 'free', 'tv', 'hotel15', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 180, 3, 'free', 'tv', 'hotel15', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 110, 4, 'free', 'tv', 'hotel15', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel15', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'sea view', 210, 1, 'free', 'tv', 'hotel16', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 130, 2, 'free', 'tv', 'hotel16', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel16', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'mountain view', 110, 4, 'free', 'tv', 'hotel16', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'sea view', 140, 5, 'free', 'tv', 'hotel16', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 110, 1, 'free', 'tv', 'hotel17', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'sea view', 180, 2, 'free', 'tv', 'hotel17', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel17', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 110, 4, 'free', 'tv', 'hotel17', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel17', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 210, 1, 'free', 'tv', 'hotel18', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'sea view', 130, 2, 'free', 'tv', 'hotel18', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 180, 3, 'free', 'tv', 'hotel18', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'mountain view', 110, 4, 'free', 'tv', 'hotel18', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel18', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'sea view', 110, 1, 'free', 'tv', 'hotel21', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'sea view', 130, 2, 'free', 'tv', 'hotel21', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel21', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 110, 4, 'free', 'tv', 'hotel21', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel21', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 110, 1, 'free', 'tv', 'hotel21', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 180, 2, 'free', 'tv', 'hotel21', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel21', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 210, 4, 'free', 'tv', 'hotel21', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel21', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'sea view', 110, 1, 'free', 'tv', 'hotel22', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 130, 2, 'free', 'tv', 'hotel22', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 180, 3, 'free', 'tv', 'hotel22', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 110, 4, 'free', 'tv', 'hotel22', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel22', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 110, 1, 'free', 'tv', 'hotel23', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 130, 2, 'free', 'tv', 'hotel23', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'sea view', 130, 3, 'free', 'tv', 'hotel23', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'mountain view', 110, 4, 'free', 'tv', 'hotel23', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel23', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'sea view', 110, 1, 'free', 'tv', 'hotel24', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 130, 2, 'free', 'tv', 'hotel24', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 180, 3, 'free', 'tv', 'hotel24', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 210, 4, 'free', 'tv', 'hotel24', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel24', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 110, 1, 'free', 'tv', 'hotel25', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 130, 2, 'free', 'tv', 'hotel25', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'sea view', 130, 3, 'free', 'tv', 'hotel25', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'mountain view', 110, 4, 'free', 'tv', 'hotel25', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel25', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'sea view', 110, 1, 'free', 'tv', 'hotel26', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 130, 2, 'free', 'tv', 'hotel26', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 180, 3, 'free', 'tv', 'hotel26', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 110, 4, 'free', 'tv', 'hotel26', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel26', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'sea view', 110, 1, 'free', 'tv', 'hotel27', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 130, 2, 'free', 'tv', 'hotel27', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel27', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 210, 4, 'free', 'tv', 'hotel27', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel27', '0')ON CONFLICT DO NOTHING;


INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 110, 1, 'free', 'tv', 'hotel28', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'sea view', 180, 2, 'free', 'tv', 'hotel28', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel28', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 110, 4, 'free', 'tv', 'hotel28', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel28', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 110, 1, 'free', 'tv', 'hotel31', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 130, 2, 'free', 'tv', 'hotel31', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'sea view', 130, 3, 'free', 'tv', 'hotel31', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'mountain view', 110, 4, 'free', 'tv', 'hotel31', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel31', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'sea view', 110, 1, 'free', 'tv', 'hotel32', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'sea view', 180, 2, 'free', 'tv', 'hotel32', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel32', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'mountain view', 210, 4, 'free', 'tv', 'hotel32', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel32', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'sea view', 110, 1, 'free', 'tv', 'hotel33', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 130, 2, 'free', 'tv', 'hotel33', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel33', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 110, 4, 'free', 'tv', 'hotel33', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel33', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 110, 1, 'free', 'tv', 'hotel34', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'sea view', 180, 2, 'free', 'tv', 'hotel34', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel34', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 110, 4, 'free', 'tv', 'hotel34', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel34', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'sea view', 210, 1, 'free', 'tv', 'hotel35', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 130, 2, 'free', 'tv', 'hotel35', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'sea view', 130, 3, 'free', 'tv', 'hotel35', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'mountain view', 110, 4, 'free', 'tv', 'hotel35', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel35', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'sea view', 110, 1, 'free', 'tv', 'hotel36', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'sea view', 180, 2, 'free', 'tv', 'hotel36', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel36', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'mountain view', 110, 4, 'free', 'tv', 'hotel36', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'sea view', 140, 5, 'free', 'tv', 'hotel36', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 210, 1, 'free', 'tv', 'hotel37', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'sea view', 130, 2, 'free', 'tv', 'hotel37', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'sea view', 180, 3, 'free', 'tv', 'hotel37', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'mountain view', 110, 4, 'free', 'tv', 'hotel37', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel37', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'sea view', 110, 1, 'free', 'tv', 'hotel38', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 130, 2, 'free', 'tv', 'hotel38', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel38', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 210, 4, 'free', 'tv', 'hotel38', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'sea view', 140, 5, 'free', 'tv', 'hotel38', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 110, 1, 'free', 'tv', 'hotel41', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'sea view', 180, 2, 'free', 'tv', 'hotel41', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel41', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 110, 4, 'free', 'tv', 'hotel41', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'sea view', 140, 5, 'free', 'tv', 'hotel41', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 110, 1, 'free', 'tv', 'hotel42', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 130, 2, 'free', 'tv', 'hotel42', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'sea view', 180, 3, 'free', 'tv', 'hotel42', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 210, 4, 'free', 'tv', 'hotel42', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel42', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'sea view', 110, 1, 'free', 'tv', 'hotel43', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 130, 2, 'free', 'tv', 'hotel43', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel43', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 110, 4, 'free', 'tv', 'hotel43', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel43', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'sea view', 110, 1, 'free', 'tv', 'hotel44', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 180, 2, 'free', 'tv', 'hotel44', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel44', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 110, 4, 'free', 'tv', 'hotel44', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'sea view', 140, 5, 'free', 'tv', 'hotel44', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 210, 1, 'free', 'tv', 'hotel45', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'sea view', 130, 2, 'free', 'tv', 'hotel45', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 180, 3, 'free', 'tv', 'hotel45', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 110, 4, 'free', 'tv', 'hotel45', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'sea view', 140, 5, 'free', 'tv', 'hotel45', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 110, 1, 'free', 'tv', 'hotel46', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 130, 2, 'free', 'tv', 'hotel46', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'sea view', 130, 3, 'free', 'tv', 'hotel46', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 110, 4, 'free', 'tv', 'hotel46', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel46', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'mountain view', 110, 1, 'free', 'tv', 'hotel47', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'sea view', 180, 2, 'free', 'tv', 'hotel47', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'mountain view', 130, 3, 'free', 'tv', 'hotel47', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'sea view', 210, 4, 'free', 'tv', 'hotel47', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel47', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'sea view', 110, 1, 'free', 'tv', 'hotel48', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'mountain view', 180, 2, 'free', 'tv', 'hotel48', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'sea view', 130, 3, 'free', 'tv', 'hotel48', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'mountain view', 110, 4, 'free', 'tv', 'hotel48', '0')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'mountain view', 140, 5, 'free', 'tv', 'hotel48', '0')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."chain" ("name", "number_hotels", "adress", "email", "phone") VALUES ('chain6', 5, 'adress5', 'email5', 'phone5')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_rooms", "chain", "stars", "adress", "email", "phone", "location") VALUES ('hotel101', 5, 'chain6', 1, 'adress41', 'email41', 'phone41', 'Paris')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'sea view', 140, 1, 'used', 'tv', 'hotel101', '1')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."agreement" ("agreement_num", "startdate", "enddate", "status", "room", "hotel") VALUES ('3', '2018-01-01', '2018-12-31', 'empty', 2, 'hotel1')ON CONFLICT DO NOTHING;

