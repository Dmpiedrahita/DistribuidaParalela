CREATE TABLE clientes (
                          id SERIAL PRIMARY KEY,
                          cedula VARCHAR(20) UNIQUE NOT NULL,
                          nombres VARCHAR(100) NOT NULL,
                          apellidos VARCHAR(100) NOT NULL,
                          direccion VARCHAR(200),
                          telefono VARCHAR(20)
);

CREATE TABLE alimentacion (
                              id SERIAL PRIMARY KEY,
                              descripcion VARCHAR(200),
                              dosis DECIMAL(10,2)
);

CREATE TABLE porcinos (
                          id SERIAL PRIMARY KEY,
                          identificacion VARCHAR(20) NOT NULL,
                          raza INT NOT NULL,
                          edad INT,
                          peso DECIMAL(10,2),
                          cliente_id VARCHAR(20) REFERENCES clientes(cedula),
                          alimentacion_id INT REFERENCES alimentacion(id)
);