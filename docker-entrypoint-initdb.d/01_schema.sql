CREATE TABLE insurance_products
(
    id      BIGSERIAL PRIMARY KEY,
    insurance_company_name    TEXT        NOT NULL,
    basic_tariff   INT         NOT NULL CHECK ( basic_tariff >= 0 ),
    image   TEXT        NOT NULL,
    removed BOOL        NOT NULL                    DEFAULT FALSE,
    created timestamptz NOT NULL                    DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE region
(   id_region      BIGSERIAL PRIMARY KEY,
    locality      TEXT        NOT NULL,
    coefficient_tc   DOUBLE PRECISION         NOT NULL CHECK ( coefficient_tc >= 0 ),
    removed BOOL        NOT NULL                    DEFAULT FALSE,
    created timestamptz NOT NULL                    DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE age_and_experience_status
(   id_age_and_experience      BIGSERIAL PRIMARY KEY,
    age_and_experience      TEXT        NOT NULL,
    coefficient_es       DOUBLE PRECISION         NOT NULL CHECK ( coefficient_es >= 0 ),
    removed BOOL        NOT NULL                    DEFAULT FALSE,
    created timestamptz NOT NULL                    DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE engine_power
(   id_engine_power      BIGSERIAL PRIMARY KEY,
    engine_power_horse_power      TEXT        NOT NULL,
    coefficient_ep       DOUBLE PRECISION         NOT NULL CHECK ( coefficient_ep >= 0 ),
    removed BOOL        NOT NULL                    DEFAULT FALSE,
    created timestamptz NOT NULL                    DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE sales
(
    id         BIGSERIAL PRIMARY KEY,
    product_id BIGINT      NOT NULL REFERENCES insurance_products,
    name       TEXT        NOT NULL,
    insurance_premium      INT         NOT NULL CHECK ( insurance_premium >= 0 ),
    created    timestamptz NOT NULL                   DEFAULT CURRENT_TIMESTAMP
);
