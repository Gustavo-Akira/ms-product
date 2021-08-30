CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS product_type(
    id uuid DEFAULT uuid_generate_v4(),
    name VARCHAR NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS product(
    id uuid DEFAULT uuid_generate_v4(),
    name VARCHAR NOT NULL,
    price FLOAT NOT NULL,
    quantity INTEGER NOT NULL,
    product_type_id uuid NOT NULL,
    FOREIGN KEY (product_type_id) REFERENCES product_type (id),
    PRIMARY KEY (id)
);
