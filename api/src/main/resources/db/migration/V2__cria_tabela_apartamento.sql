CREATE TABLE apartamento (
     id BIGSERIAL PRIMARY KEY,
     numero VARCHAR(10) NOT NULL,
     bloco_id BIGINT NOT NULL,
     CONSTRAINT fk_apartamento_bloco FOREIGN KEY (bloco_id) REFERENCES bloco(id),
     CONSTRAINT uq_apartamento UNIQUE (numero, bloco_id)
);