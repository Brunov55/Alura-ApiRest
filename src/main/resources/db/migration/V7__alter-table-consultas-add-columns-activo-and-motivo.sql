ALTER TABLE consultas ADD activo TINYINT;
UPDATE consultas SET activo = 1;
ALTER TABLE consultas ADD motivo VARCHAR(255);