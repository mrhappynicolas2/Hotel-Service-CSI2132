CREATE SCHEMA IF NOT EXISTS "Hotels"
    AUTHORIZATION pg_database_owner;

COMMENT ON SCHEMA "Hotels"
    IS 'SET search_path = "Hotels"';

CREATE TABLE IF NOT EXISTS "Hotels"."agreement"
(
    "agreement_num" "char" NOT NULL,
    "startdate" date,
    "enddate" date,
    CONSTRAINT "agreement_pkey" PRIMARY KEY ("agreement_num")
);

CREATE TABLE IF NOT EXISTS "Hotels"."hotels"
(
    "name" character varying(20) NOT NULL,
    "number_hotels" integer,
    "adress" character varying(20),
    "email" character varying(20),
    "phone" character varying(20),
    CONSTRAINT hotels_pkey PRIMARY KEY (name)
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
    "agreement_num" "char",
    
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

INSERT INTO  "Hotels"."hotels" ("name", "number_hotels", "adress", "email", "phone") VALUES ('hotel1', 1, 'adress1', 'email1', 'phone1') ON CONFLICT DO NOTHING;

INSERT INTO  "Hotels"."hotels" ("name", "number_hotels", "adress", "email", "phone") VALUES ('hotel1', 1, 'adress1', 'email1', 'phone1') ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_hotels", "adress", "email", "phone") VALUES ('hotel2', 1, 'adress1', 'email2', 'phone2')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_hotels", "adress", "email", "phone") VALUES ('hotel3', 1, 'adress3', 'email3', 'phone3')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_hotels", "adress", "email", "phone") VALUES ('hotel4', 1, 'adress4', 'email4', 'phone4')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."hotels" ("name", "number_hotels", "adress", "email", "phone") VALUES ('hotel5', 1, 'adress5', 'email5', 'phone5')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."agreement" ("agreement_num", "startdate", "enddate") VALUES ('1', '2018-01-01', '2018-12-31')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."agreement" ("agreement_num", "startdate", "enddate") VALUES ('2', '2018-01-01', '2018-12-31')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."agreement" ("agreement_num", "startdate", "enddate") VALUES ('3', '2018-01-01', '2018-12-31')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."agreement" ("agreement_num", "startdate", "enddate") VALUES ('4', '2018-01-01', '2018-12-31')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."agreement" ("agreement_num", "startdate", "enddate") VALUES ('5', '2018-01-01', '2018-12-31')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."agreement" ("agreement_num", "startdate", "enddate") VALUES ('6', '2018-01-01', '2018-12-31')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'single', 100, 1, 'free', 'tv', 'hotel1', '1')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'double', 150, 1, 'free', 'tv', 'hotel1', '1')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'triple', 170, 1, 'free', 'tv', 'hotel1', '1')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'four', 120, 1, 'free', 'tv', 'hotel1', '1')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'five', 130, 1, 'free', 'tv', 'hotel1', '1')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'single', 120, 1, 'free', 'tv', 'hotel2', '2')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'double', 150, 1, 'free', 'tv', 'hotel2', '2')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'triple', 160, 1, 'free', 'tv', 'hotel2', '2')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'four', 130, 1, 'free', 'tv', 'hotel2', '2')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'five', 150, 1, 'free', 'tv', 'hotel2', '2')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'single', 110, 1, 'free', 'tv', 'hotel3', '3')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'double', 130, 1, 'free', 'tv', 'hotel3', '3')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'triple', 130, 1, 'free', 'tv', 'hotel3', '3')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (4, 'four', 110, 1, 'free', 'tv', 'hotel3', '3')ON CONFLICT DO NOTHING;
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (5, 'five', 140, 1, 'free', 'tv', 'hotel3', '3')ON CONFLICT DO NOTHING;

INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (1, 'five', 140, 1, 'free', 'tv', 'hotel4', NULL );
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (2, 'five', 140, 1, 'free', 'tv', 'hotel4', NULL );
INSERT INTO "Hotels"."rooms" ("room_num", "room_type", "room_price", "room_capacity", "room_status", "room_annimities", "hotel_name", "agreement_num") VALUES (3, 'five', 140, 1, 'free', 'tv', 'hotel4', NULL );
