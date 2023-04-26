CREATE TABLE buy_report (
    id SERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    project_id BIGINT NOT NULL,
    project_name BIGINT NOT NULL,
    quantity INTEGER NOT NULL
);

CREATE TABLE final_order (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    order_date DATE NOT NULL,
    quantity INTEGER NOT NULL,
    total_price NUMERIC(15,2) NOT NULL,
    status VARCHAR(255) NOT NULL
);

CREATE TABLE order_items (
    id SERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    price NUMERIC(15,2) NOT NULL
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    order_date DATE NOT NULL,
    status VARCHAR(255) NOT NULL
);

CREATE TABLE product_report (
    id SERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    order_count INTEGER NOT NULL,
    price NUMERIC(15,2) NOT NULL
);

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    price NUMERIC(15,2) NOT NULL
);
