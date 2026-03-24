CREATE TABLE funcionario (
     id BIGSERIAL PRIMARY KEY,
     nome VARCHAR(255) NOT NULL,
     cpf VARCHAR(14) NOT NULL UNIQUE,
     idade INTEGER NOT NULL,
     cargo VARCHAR(100),
     salario DECIMAL(10,2)
);