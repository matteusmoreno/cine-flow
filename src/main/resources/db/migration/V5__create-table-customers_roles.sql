CREATE TABLE customers_roles (
    customer_id BINARY(16),
    role_id BIGINT,
    PRIMARY KEY (customer_id, role_id),
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);
