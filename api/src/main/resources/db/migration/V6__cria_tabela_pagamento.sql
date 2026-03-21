CREATE TABLE pagamento (
   id BIGSERIAL PRIMARY KEY,
   valor DECIMAL(10,2) NOT NULL,
   data_pagamento DATE NOT NULL,
   status VARCHAR(20) NOT NULL DEFAULT 'PENDENTE',
   morador_id BIGINT NOT NULL,
   CONSTRAINT fk_pagamento_morador FOREIGN KEY (morador_id) REFERENCES morador(id),
   CONSTRAINT chk_pagamento_status CHECK (status IN ('PENDENTE', 'PAGO', 'ATRASADO'))
);