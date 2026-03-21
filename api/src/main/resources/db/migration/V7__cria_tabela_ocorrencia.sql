CREATE TABLE ocorrencia (
    id BIGSERIAL PRIMARY KEY,
    descricao TEXT NOT NULL,
    data DATE NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ABERTA',
    morador_id BIGINT NOT NULL,
    CONSTRAINT fk_ocorrencia_morador FOREIGN KEY (morador_id) REFERENCES morador(id),
    CONSTRAINT chk_ocorrencia_status CHECK (status IN ('ABERTA', 'EM_ANALISE', 'RESOLVIDA'))
);