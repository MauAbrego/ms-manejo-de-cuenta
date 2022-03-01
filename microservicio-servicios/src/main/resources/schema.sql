DROP TABLE IF EXISTS tbl_servicios;
CREATE TABLE tbl_servicios
(
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   nombre VARCHAR (250) NOT NULL,
   disponible BOOLEAN,
   precio DOUBLE,
   estado VARCHAR (250) NOT NULL,
   fechaDeCreacion DATE NOT NULL,
);
DROP TABLE IF EXISTS tbl_categoria;
CREATE TABLE tbl_categoria
(
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   nombre VARCHAR (250) NOT NULL,
);