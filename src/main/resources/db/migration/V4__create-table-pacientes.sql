CREATE TABLE pacientes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(20) NOT NULL,
    documento VARCHAR(18) NOT NULL UNIQUE,
    calle VARCHAR(100) NOT NULL,
    colonia VARCHAR(100) NOT NULL,
    codigo_postal VARCHAR(12) NOT NULL,
    complemento VARCHAR(100),
    numero VARCHAR(20),
    estado VARCHAR(100) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id)
);
