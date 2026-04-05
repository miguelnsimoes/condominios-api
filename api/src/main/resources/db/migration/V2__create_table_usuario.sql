CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY,
    login VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

ALTER TABLE morador ADD COLUMN usuario_id BIGINT UNIQUE;
ALTER TABLE morador ADD CONSTRAINT fk_morador_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id);

ALTER TABLE funcionario ADD COLUMN usuario_id BIGINT UNIQUE;
ALTER TABLE funcionario ADD CONSTRAINT fk_funcionario_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id);