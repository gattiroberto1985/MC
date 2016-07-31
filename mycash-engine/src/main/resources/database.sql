
create user mycash_admin with password 'mycash_admin';



CREATE TABLE public.users
(
   id serial, 
   username character(10)[], 
   salt character(64)[], 
   password character varying[], 
   tmst_ins timestamp without time zone NOT NULL DEFAULT current_timestamp, 
   tmst_upd timestamp without time zone NOT NULL DEFAULT current_timestamp, 
   CONSTRAINT pk_users PRIMARY KEY (id)
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE public.users
  OWNER TO mycash_admin;


-- Table: accounts

-- DROP TABLE accounts;

CREATE TABLE accounts
(
  id serial NOT NULL,
  name character varying[] NOT NULL,
  type character varying NOT NULL,
  amount double precision NOT NULL DEFAULT 0,
  tmst_ins timestamp without time zone NOT NULL DEFAULT now(),
  tmst_upd timestamp without time zone,
  CONSTRAINT pk_accounts PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE accounts
  OWNER TO mycash_admin;


-- Table: portfolios

-- DROP TABLE portfolios;

CREATE TABLE portfolios
(
  id serial NOT NULL,
  name character varying[] NOT NULL,
  amount double precision NOT NULL DEFAULT 0,
  tmst_ins timestamp without time zone NOT NULL DEFAULT now(),
  tmst_upd timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT pk_portfolios PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE portfolios
  OWNER TO mycash_admin;

-- Table: transactions

-- DROP TABLE transactions;

CREATE TABLE transactions
(
  id serial NOT NULL,
  from_acn integer,
  to_acn integer,
  date date NOT NULL,
  description text NOT NULL,
  type character(1)[] NOT NULL,
  amount double precision NOT NULL,
  tmst_ins timestamp without time zone NOT NULL DEFAULT now(),
  tmst_upd timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT pk_transactions PRIMARY KEY (id),
  CONSTRAINT fk_transactions_account_to FOREIGN KEY (to_acn)
      REFERENCES accounts (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_transactions_accounts_from FOREIGN KEY (from_acn)
      REFERENCES accounts (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE transactions
  OWNER TO mycash_admin;
