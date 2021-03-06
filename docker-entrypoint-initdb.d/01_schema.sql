CREATE TABLE insurance_products
(
    id                     BIGSERIAL PRIMARY KEY,
    insurance_company_name TEXT        NOT NULL,
    basic_tariff           INT         NOT NULL CHECK ( basic_tariff >= 0 ),
    image                  TEXT        NOT NULL,
    removed                BOOL        NOT NULL DEFAULT FALSE,
    created                timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE region
(
    id_region      BIGSERIAL PRIMARY KEY,
    locality       TEXT             NOT NULL,
    coefficient_tc DOUBLE PRECISION NOT NULL CHECK ( coefficient_tc >= 0 ),
    removed        BOOL             NOT NULL DEFAULT FALSE,
    created        timestamptz      NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE age_and_experience_status
(
    id_age_and_experience BIGSERIAL PRIMARY KEY,
    age_and_experience    TEXT             NOT NULL,
    coefficient_es        DOUBLE PRECISION NOT NULL CHECK ( coefficient_es >= 0 ),
    removed               BOOL             NOT NULL DEFAULT FALSE,
    created               timestamptz      NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE engine_power
(
    id_engine_power          BIGSERIAL PRIMARY KEY,
    engine_power_horse_power TEXT             NOT NULL,
    coefficient_ep           DOUBLE PRECISION NOT NULL CHECK ( coefficient_ep >= 0 ),
    removed                  BOOL             NOT NULL DEFAULT FALSE,
    created                  timestamptz      NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE limit_status
(
    id_limit_status BIGSERIAL PRIMARY KEY,
    limit_drivers   TEXT             NOT NULL,
    coefficient_cc  DOUBLE PRECISION NOT NULL CHECK ( coefficient_cc >= 0 ),
    removed         BOOL             NOT NULL DEFAULT FALSE,
    created         timestamptz      NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE seasonality_status
(
    id_seasonality_status BIGSERIAL PRIMARY KEY,
    months                TEXT             NOT NULL,
    coefficient_cs        DOUBLE PRECISION NOT NULL CHECK ( coefficient_cs >= 0 ),
    removed               BOOL             NOT NULL DEFAULT FALSE,
    created               timestamptz      NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE sales_insurance_products
(
    id                      BIGSERIAL PRIMARY KEY,
    insurance_company_name  TEXT        NOT NULL,
    user_name               TEXT        NOT NULL,
    user_surname            TEXT        NOT NULL,
    vin_number_car          TEXT        NOT NULL,
    phone_number            TEXT        NOT NULL,
    email                   TEXT        NOT NULL,
    region                  TEXT        NOT NULL,
    bank_card_number        TEXT        NOT NULL,
    insurance_premium_price INT         NOT NULL CHECK ( insurance_premium_price >= 0 ),
    created                 timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP
);
