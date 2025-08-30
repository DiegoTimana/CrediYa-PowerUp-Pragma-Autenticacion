-- ===========================
-- TABLA: usuarios
-- ===========================
CREATE TABLE usuarios (
    id_usuario BIGSERIAL PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    correo_electronico VARCHAR(150) NOT NULL UNIQUE,
    salario_base NUMERIC(15,2) NOT NULL,

    id_rol BIGINT NOT NULL,
    CONSTRAINT fk_usuario_rol FOREIGN KEY (id_rol) REFERENCES roles(id_rol)
);

-- ===========================
-- TABLA: roles
-- ===========================
CREATE TABLE roles (
    id_rol BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
);

-- ===========================
-- TABLA: estados
-- ===========================
CREATE TABLE estados (
    id_estado BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

-- ===========================
-- TABLA: tipo_prestamos
-- ===========================
CREATE TABLE tipo_prestamos (
    id_tipo_prestamos BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    monto_minimo NUMERIC(15,2) NOT NULL,
    monto_maximo NUMERIC(15,2) NOT NULL,
    tasa_interes NUMERIC(5,2) NOT NULL, -- ej. 12.50 %
    validacion_automatica BOOLEAN NOT NULL DEFAULT FALSE
);

-- ===========================
-- TABLA: solicitud
-- ===========================
CREATE TABLE solicitud (
    id_solicitud BIGSERIAL PRIMARY KEY,
    monto NUMERIC(15,2) NOT NULL,
    plazo DATE NOT NULL,
    email VARCHAR(150) NOT NULL,
    id_estado BIGINT NOT NULL,
    id_tipo_prestamos BIGINT NOT NULL,

    CONSTRAINT fk_solicitud_estado FOREIGN KEY (id_estado) REFERENCES estados(id_estado),
    CONSTRAINT fk_solicitud_tipo FOREIGN KEY (id_tipo_prestamos) REFERENCES tipo_prestamos(id_tipo_prestamos)
);
