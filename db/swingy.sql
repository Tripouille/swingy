-- Adminer 4.8.0 PostgreSQL 13.2 dump

\connect "swingy";

DROP TABLE IF EXISTS "aartifact";
DROP SEQUENCE IF EXISTS aartifact_id_seq;
CREATE SEQUENCE aartifact_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

CREATE TABLE "public"."aartifact" (
    "basevalue" bigint NOT NULL,
    "rarity" character varying(100) NOT NULL,
    "totalvalue" bigint NOT NULL,
    "id" bigint DEFAULT nextval('aartifact_id_seq') NOT NULL,
    "dtype" character varying(100),
    CONSTRAINT "aartifact_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


DROP TABLE IF EXISTS "ahero";
CREATE TABLE "public"."ahero" (
    "name" character varying(100) NOT NULL,
    "level" integer,
    "experience" bigint,
    "weapon_id" bigint,
    "dtype" character varying(100),
    CONSTRAINT "ahero_pkey" PRIMARY KEY ("name")
) WITH (oids = false);


ALTER TABLE ONLY "public"."ahero" ADD CONSTRAINT "ahero_aartifact_id_fkey" FOREIGN KEY (weapon_id) REFERENCES aartifact(id) ON DELETE SET NULL NOT DEFERRABLE;

-- 2021-04-30 18:56:46.684528+00
