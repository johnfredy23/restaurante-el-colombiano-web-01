DROP DATABASE IF EXISTS elcolombiano_restaurante;
CREATE DATABASE elcolombiano_restaurante;
USE elcolombiano_restaurante;

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    tipo_usuario VARCHAR(20) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    documento VARCHAR(20) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    correo VARCHAR(100) UNIQUE,
    fechaNacimiento DATE,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE mesa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_mesa INT NOT NULL UNIQUE,
    capacidad INT NOT NULL,
    estado VARCHAR(20) DEFAULT 'LIBRE',
    ubicacion VARCHAR(50)
);

CREATE TABLE producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    categoria VARCHAR(30) NOT NULL,
    disponible BOOLEAN DEFAULT TRUE
);

CREATE TABLE pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(20) DEFAULT 'NUEVO',
    total DECIMAL(10,2) DEFAULT 0,
    id_cliente INT,
    id_mesa INT,
    id_mesero INT
);

CREATE TABLE detalle_pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL
);

CREATE TABLE factura (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_factura VARCHAR(20) UNIQUE NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_pedido INT NOT NULL UNIQUE,
    subtotal DECIMAL(10,2) NOT NULL,
    iva DECIMAL(10,2) DEFAULT 0,
    total DECIMAL(10,2) NOT NULL,
    metodo_pago VARCHAR(20) NOT NULL
);

INSERT INTO usuario (nombre, apellidos, email, contraseña, tipo_usuario) VALUES
('Carlos', 'Mesa', 'carlos.mesa@restaurante.com', '123456', 'ADMINISTRADOR'),
('Ana', 'García', 'ana.garcia@restaurante.com', '123456', 'MESERO'),
('Luis', 'Martínez', 'luis.martinez@restaurante.com', '123456', 'MESERO'),
('María', 'López', 'maria.lopez@restaurante.com', '123456', 'COCINA');

INSERT INTO cliente (nombre, apellidos, documento, telefono, correo, fechaNacimiento) VALUES
('Juan', 'Pérez', '12345678', '3001234567', 'juan.perez@email.com', '1990-05-15'),
('María', 'Gómez', '87654321', '3017654321', 'maria.gomez@email.com', '1985-08-22'),
('Pedro', 'Infante', '11223344', '3101122334', 'pedro.infante@email.com', '1978-03-10'),
('Lucía', 'Ramírez', '44332211', '3204433221', 'lucia.ramirez@email.com', '1995-11-30');

INSERT INTO mesa (numero_mesa, capacidad, estado, ubicacion) VALUES
(1, 2, 'LIBRE', 'Interior'),
(2, 4, 'LIBRE', 'Interior'),
(3, 4, 'OCUPADA', 'Terraza'),
(4, 6, 'LIBRE', 'Terraza'),
(5, 8, 'RESERVADA', 'VIP'),
(6, 2, 'LIBRE', 'Barra');

INSERT INTO producto (nombre, descripcion, precio, categoria, disponible) VALUES
('Bandeja Paisa', 'Frijoles, arroz, carne molida, chicharrón, huevo, arepa, aguacate, plátano', 28000, 'PLATO FUERTE', TRUE),
('Ajiaco', 'Sopa de pollo con tres tipos de papa, mazorca, guascas, acompañado de arroz, aguacate y crema', 25000, 'PLATO FUERTE', TRUE),
('Limonada de Coco', 'Limonada natural con crema de coco', 9000, 'BEBIDA', TRUE),
('Postre de Natas', 'Postre típico colombiano a base de leche y canela', 7000, 'POSTRE', TRUE),
('Arepa de Huevo', 'Arepa frita rellena de huevo', 5000, 'ENTRADA', TRUE),
('Tamal', 'Tamal tolimense con pollo, cerdo, zanahoria, arveja, huevo', 18000, 'PLATO FUERTE', TRUE),
('Champus', 'Bebida tradicional de frutas y maíz', 6000, 'BEBIDA', TRUE);

INSERT INTO pedido (estado, id_cliente, id_mesa, id_mesero) 
VALUES ('NUEVO', 1, 3, 2);

INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_unitario, subtotal) VALUES
(1, 1, 2, 28000, 56000),
(1, 3, 2, 9000, 18000);

UPDATE pedido SET total = 56000 + 18000 WHERE id = 1;

INSERT INTO factura (numero_factura, id_pedido, subtotal, iva, total, metodo_pago) 
VALUES ('FAC-001', 1, 74000, 14800, 88800, 'TARJETA');

SELECT * FROM producto;
SELECT * FROM cliente;
SELECT * FROM mesa;
SELECT * FROM usuario;
describe cliente;
SELECT * FROM cliente;
