ALTER TABLE morador
ADD COLUMN bloco_id BIGINT;

ALTER TABLE morador
ADD CONSTRAINT fk_morador_bloco
FOREIGN KEY (bloco_id) REFERENCES bloco(id);