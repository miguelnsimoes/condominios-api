ALTER TABLE encomenda
ADD COLUMN funcionario_id BIGINT;

ALTER TABLE encomenda
ADD CONSTRAINT fk_encomenda_funcionario
FOREIGN KEY (funcionario_id) REFERENCES funcionario(id);