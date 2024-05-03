CREATE TABLE addresses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cep VARCHAR(20),
    logradouro VARCHAR(255),
    bairro VARCHAR(255),
    localidade VARCHAR(255),
    uf VARCHAR(2)
);
