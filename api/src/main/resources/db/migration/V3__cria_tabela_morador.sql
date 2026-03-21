CREATE TABLE morador (
     id BIGSERIAL PRIMARY KEY,
     nome VARCHAR(100) NOT NULL,
     cpf VARCHAR(11) NOT NULL UNIQUE,
     telefone VARCHAR(20) NOT NULL,
     apartamento_id BIGINT NOT NULL,
     CONSTRAINT fk_morador_apartamento FOREIGN KEY (apartamento_id) REFERENCES apartamento(id)
);