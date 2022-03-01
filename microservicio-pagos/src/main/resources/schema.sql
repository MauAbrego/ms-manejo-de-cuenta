DROP TABLE IF EXISTS tbl_recibos;

CREATE TABLE tbl_recibos (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  numero_de_recibo VARCHAR(250) NOT NULL,
  descripcion VARCHAR(250) NOT NULL,
  fechaExperacion VARCHAR(250)NOT NULL,
  cantidad DOUBLE,
  precio DOUBLE,
  estado VARCHAR(250) NOT NULL,
  tipoCuenta VARCHAR(250) NOT NULL,
  recibo_id BIGINT,
  cuentaId BIGINT,
  servicioId BIGINT
);
