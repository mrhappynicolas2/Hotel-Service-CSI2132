CREATE TABLE IF NOT EXISTS "Hotels"."agreement"
(
    "agreement_num" "char" NOT NULL,
    "startdate" date,
    "enddate" date,
    CONSTRAINT "agreement_pkey" PRIMARY KEY ("agreement_num")
);

INSERT INTO "Hotels"."agreement" ("agreement_num", "startdate", "enddate") VALUES ('1', '2018-01-01', '2018-12-31');
INSERT INTO "Hotels"."agreement" ("agreement_num", "startdate", "enddate") VALUES ('2', '2018-01-01', '2018-12-31');
INSERT INTO "Hotels"."agreement" ("agreement_num", "startdate", "enddate") VALUES ('3', '2018-01-01', '2018-12-31');
INSERT INTO "Hotels"."agreement" ("agreement_num", "startdate", "enddate") VALUES ('4', '2018-01-01', '2018-12-31');
INSERT INTO "Hotels"."agreement" ("agreement_num", "startdate", "enddate") VALUES ('5', '2018-01-01', '2018-12-31');
INSERT INTO "Hotels"."agreement" ("agreement_num", "startdate", "enddate") VALUES ('6', '2018-01-01', '2018-12-31');

