CREATE TABLE encomenda (
   id BIGSERIAL PRIMARY KEY,
   descricao VARCHAR(255) NOT NULL,
   chegada TIMESTAMP NOT NULL,
   status VARCHAR(20) NOT NULL DEFAULT 'PENDENTE',
   apartamento_id BIGINT NOT NULL,
   funcionario VARCHAR(100) NOT NULL,
   data_retirada TIMESTAMP,
   CONSTRAINT fk_encomenda_apartamento FOREIGN KEY (apartamento_id) REFERENCES apartamento(id),
   CONSTRAINT chk_encomenda_status CHECK (status IN ('PENDENTE', 'RETIRADA'))
);