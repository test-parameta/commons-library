
CREATE DATABASE parameta;


CREATE TABLE parameta.tipo_documento (
    id_tipo_documento INT PRIMARY KEY AUTO_INCREMENT,
    nombre_tipo_documento VARCHAR(5) NOT NULL,
    UNIQUE INDEX idx_nombre_tipo_documento (nombre_tipo_documento)
);


CREATE TABLE parameta.cargo (
    id_cargo INT PRIMARY KEY AUTO_INCREMENT,
    nombre_cargo VARCHAR(5) NOT NULL,
    UNIQUE INDEX idx_nombre_cargo (nombre_cargo)
);


CREATE TABLE parameta.empleado (
    codigo_empleado VARCHAR(5) PRIMARY KEY,
    nombre_empleado VARCHAR(50) NOT NULL,
    apellidos_empleado VARCHAR(50) NOT NULL,
    tipo_documento_fk INT NOT NULL,
    numero_documento_empleado VARCHAR(20) NOT NULL,
    fecha_nacimiento_empleado DATE NOT NULL,
    fecha_vinculacion_compania_empleado DATE NOT NULL,
    cargo_fk INT NOT NULL,
    salario_empleado DECIMAL(10, 2) NOT NULL,
    correo_empleado VARCHAR(80) NOT NULL,
    hash_password VARCHAR(100) NOT NULL, 
    CONSTRAINT fk_tipo_documento FOREIGN KEY (tipo_documento_fk) REFERENCES tipo_documento(id_tipo_documento),
    CONSTRAINT fk_cargo FOREIGN KEY (cargo_fk) REFERENCES cargo(id_cargo),
    UNIQUE INDEX idx_numero_documento (tipo_documento_fk, numero_documento_empleado),
    UNIQUE INDEX idx_correo_empleado (correo_empleado),
    INDEX idx_fecha_vinculacion (fecha_vinculacion_compania_empleado),
    INDEX idx_cargo_salario (cargo_fk, salario_empleado) 
);

INSERT INTO parameta.tipo_documento (nombre_tipo_documento)
VALUES 
    ('CC'),
    ('CE'),
    ('PAS');

   
INSERT INTO parameta.cargo (nombre_cargo)
VALUES 
    ('GG'),
    ('DT'),
    ('AS'),
    ('IS'),
    ('ABD'),
    ('DF'),
    ('DB'),
    ('ESI'),
    ('SM'),
    ('PO');
