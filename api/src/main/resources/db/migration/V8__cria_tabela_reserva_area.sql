CREATE TABLE reserva_area (
      id BIGSERIAL PRIMARY KEY,
      data DATE NOT NULL,
      hora_inicio TIME NOT NULL,
      hora_fim TIME NOT NULL,
      area_id BIGINT NOT NULL,
      morador_id BIGINT NOT NULL,
      CONSTRAINT fk_reserva_area FOREIGN KEY (area_id) REFERENCES area_comum(id),
      CONSTRAINT fk_reserva_morador FOREIGN KEY (morador_id) REFERENCES morador(id)
);