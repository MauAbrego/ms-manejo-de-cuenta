DROP TABLE IF EXISTS tbl_cuentas;

CREATE TABLE tbl_cuentas (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  nombreCliente VARCHAR(250) NOT NULL,
  pan VARCHAR(250) NOT NULL,
  fechaExperacion VARCHAR(250)NOT NULL,
  cvv INTEGER,
  pin INTEGER, 
  saldo DOUBLE,
  numeroCuenta VARCHAR(250) NOT NULL,
  bloqueado BOOLEAN,
  limiteRetiro DOUBLE,
  estado VARCHAR(250) NOT NULL,
  tipoCuenta VARCHAR(250) NOT NULL,
  creado TIMESTAMP,
  categoria_id BIGINT AUTO_INCREMENT
);