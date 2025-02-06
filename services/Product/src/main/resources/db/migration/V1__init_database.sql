CREATE TABLE IF NOT EXISTS category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(250)
    );

CREATE TABLE IF NOT EXISTS product (
        id SERIAL PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        description VARCHAR(250),
        available_quantity INTEGER NOT NULL,
        price NUMERIC(38,2) NOT NULL,
        category_id INTEGER NOT NULL,
        CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE
    );

CREATE SEQUENCE IF NOT EXISTS category_seq INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS product_seq INCREMENT BY 50;
