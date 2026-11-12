CREATE TABLE bloco (
   id BIGSERIAL PRIMARY KEY,
   nome VARCHAR(50) NOT NULL
);

CREATE TABLE area_comum (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE funcionario (
     id BIGSERIAL PRIMARY KEY,
     nome VARCHAR(255) NOT NULL,
     cpf VARCHAR(14) NOT NULL UNIQUE,
     idade INTEGER NOT NULL,
     cargo VARCHAR(100),
     salario DECIMAL(10,2),
     telefone VARCHAR(20)
);

CREATE TABLE apartamento (
     id BIGSERIAL PRIMARY KEY,
     numero VARCHAR(10) NOT NULL,
     bloco_id BIGINT NOT NULL,
     CONSTRAINT fk_apartamento_bloco FOREIGN KEY (bloco_id) REFERENCES bloco(id),
     CONSTRAINT uq_apartamento UNIQUE (numero, bloco_id)
);

CREATE TABLE morador (
     id BIGSERIAL PRIMARY KEY,
     nome VARCHAR(100) NOT NULL,
     cpf VARCHAR(11) NOT NULL UNIQUE,
     telefone VARCHAR(20) NOT NULL,
     idade INTEGER NOT NULL,
     apartamento_id BIGINT NOT NULL,
     CONSTRAINT fk_morador_apartamento FOREIGN KEY (apartamento_id) REFERENCES apartamento(id)
);

CREATE TABLE encomenda (
   id BIGSERIAL PRIMARY KEY,
   descricao VARCHAR(255) NOT NULL,
   chegada TIMESTAMP NOT NULL,
   status VARCHAR(20) NOT NULL DEFAULT 'PENDENTE',
   data_retirada TIMESTAMP,
   apartamento_id BIGINT NOT NULL,
   funcionario_id BIGINT,
   CONSTRAINT fk_encomenda_apartamento FOREIGN KEY (apartamento_id) REFERENCES apartamento(id),
   CONSTRAINT fk_encomenda_funcionario FOREIGN KEY (funcionario_id) REFERENCES funcionario(id),
   CONSTRAINT chk_encomenda_status CHECK (status IN ('PENDENTE', 'RETIRADA'))
);

CREATE TABLE pagamento (
   id BIGSERIAL PRIMARY KEY,
   valor DECIMAL(10,2) NOT NULL,
   data_pagamento DATE,
   data_vencimento DATE,
   referencia VARCHAR(7),
   morador_id BIGINT NOT NULL,
   CONSTRAINT fk_pagamento_morador FOREIGN KEY (morador_id) REFERENCES morador(id)
);

CREATE TABLE ocorrencia (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao VARCHAR(500) NOT NULL,
    data_registro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) NOT NULL DEFAULT 'ABERTA',
    morador_id BIGINT NOT NULL,
    CONSTRAINT fk_ocorrencia_morador FOREIGN KEY (morador_id) REFERENCES morador(id),
    CONSTRAINT chk_ocorrencia_status CHECK (status IN ('ABERTA', 'EM_ANALISE', 'RESOLVIDA', 'CANCELADA'))
);

CREATE TABLE reserva_area (
  id BIGSERIAL PRIMARY KEY,
  data DATE NOT NULL,
  area_id BIGINT NOT NULL,
  morador_id BIGINT NOT NULL,
  CONSTRAINT fk_reserva_area FOREIGN KEY (area_id) REFERENCES area_comum(id),
  CONSTRAINT fk_reserva_morador FOREIGN KEY (morador_id) REFERENCES morador(id)
);