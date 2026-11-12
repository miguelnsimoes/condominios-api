ALTER TABLE reserva_area ADD COLUMN IF NOT EXISTS hora_inicio TIME;
ALTER TABLE reserva_area ADD COLUMN IF NOT EXISTS hora_fim TIME;

UPDATE reserva_area
SET hora_inicio = '08:00', hora_fim = '12:00'
WHERE hora_inicio IS NULL;

ALTER TABLE reserva_area ALTER COLUMN hora_inicio SET NOT NULL;
ALTER TABLE reserva_area ALTER COLUMN hora_fim SET NOT NULL;

CREATE UNIQUE INDEX IF NOT EXISTS uq_reserva_area_slot
    ON reserva_area (area_id, data, hora_inicio, hora_fim);
