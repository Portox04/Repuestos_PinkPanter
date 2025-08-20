-- ===== Pink Panther - Esquema MySQL =====
DROP DATABASE IF EXISTS pink_panther;
CREATE DATABASE pink_panther CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE pink_panther;

-- ===== Tablas =====
CREATE TABLE usuarios (
  id_usuario INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(120) NOT NULL,
  rol ENUM('ADMIN','CLIENTE','VENDEDOR') NOT NULL DEFAULT 'CLIENTE',
  correo VARCHAR(30),
  clave varchar(30)
);

create TABLE clientes (
    id_cliente INT PRIMARY KEY, -- será igual al id_usuario
    cedula VARCHAR(30) NOT NULL UNIQUE,
    telefono VARCHAR(30),
    credito_restante DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    CONSTRAINT fk_clientes_usuario FOREIGN KEY (id_cliente) REFERENCES usuarios(id_usuario) ON DELETE CASCADE
);


CREATE TABLE productos (
  id_producto INT AUTO_INCREMENT PRIMARY KEY,
  codigo VARCHAR(40) NOT NULL UNIQUE,
  nombre VARCHAR(200) NOT NULL,
  precioVenta DECIMAL(12,2) NOT NULL,
  stockActual int,
  categoria VARCHAR(120),
  descripcion TEXT,
  id_proveedor INT NULL,
  creado_en TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE ventas (
    id_venta INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    fecha DATE NOT NULL,
    total DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    CONSTRAINT fk_ventas_cliente FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente) ON DELETE CASCADE
);

CREATE TABLE detalles_venta (
  id_detalle INT AUTO_INCREMENT PRIMARY KEY,
  id_venta INT NOT NULL,
  id_producto INT NOT NULL,
  cantidad INT NOT NULL CHECK (cantidad > 0),
  precio_unitario DECIMAL(12,2) NOT NULL,
  subtotal DECIMAL(12,2) GENERATED ALWAYS AS (cantidad * precio_unitario) STORED,
  CONSTRAINT fk_detalle_venta FOREIGN KEY (id_venta) REFERENCES ventas(id_venta) ON DELETE CASCADE,
  CONSTRAINT fk_detalle_producto FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

CREATE TABLE movimientos_inventario (
  id_mov INT AUTO_INCREMENT PRIMARY KEY,
  id_producto INT NOT NULL,
  tipo ENUM('ENTRADA','SALIDA','AJUSTE') NOT NULL,
  cantidad INT NOT NULL,
  motivo VARCHAR(200),
  id_usuario INT NULL,
  fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_mov_producto FOREIGN KEY (id_producto) REFERENCES productos(id_producto),
  CONSTRAINT fk_mov_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

-- ===== Vistas =====
CREATE OR REPLACE VIEW vw_inventario_bajo AS
SELECT id_producto, codigo, nombre, stock_actual, stock_minimo
FROM productos
WHERE stock_actual <= stock_minimo;

CREATE OR REPLACE VIEW vw_ventas_resumen AS
SELECT v.id_venta, v.fecha, u.nombre AS cliente, v.total,
       SUM(d.cantidad) AS items, SUM(d.subtotal) AS subtotal_calc
FROM ventas v
LEFT JOIN clientes c ON c.id_cliente = v.id_cliente
LEFT JOIN usuarios u ON u.id_usuario = c.id_cliente
LEFT JOIN detalles_venta d ON d.id_venta = v.id_venta
GROUP BY v.id_venta;

-- ===== Triggers =====
DELIMITER $$
CREATE TRIGGER trg_detalle_venta_ai
AFTER INSERT ON detalles_venta
FOR EACH ROW
BEGIN
  IF (SELECT stock_actual FROM productos WHERE id_producto = NEW.id_producto) < NEW.cantidad THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Stock insuficiente';
  END IF;

  UPDATE productos
     SET stock_actual = stock_actual - NEW.cantidad
   WHERE id_producto = NEW.id_producto;

  INSERT INTO movimientos_inventario(id_producto, tipo, cantidad, motivo)
  VALUES (NEW.id_producto, 'SALIDA', NEW.cantidad, CONCAT('Venta #', NEW.id_venta));
END$$

CREATE TRIGGER trg_detalle_venta_ad
AFTER DELETE ON detalles_venta
FOR EACH ROW
BEGIN
  UPDATE productos
     SET stock_actual = stock_actual + OLD.cantidad
   WHERE id_producto = OLD.id_producto;

  INSERT INTO movimientos_inventario(id_producto, tipo, cantidad, motivo)
  VALUES (OLD.id_producto, 'ENTRADA', OLD.cantidad, CONCAT('Eliminación detalle venta #', OLD.id_venta));
END$$
DELIMITER ;

-- ===== Procedimientos almacenados =====
DELIMITER $$
CREATE PROCEDURE sp_crear_producto(
  IN p_codigo VARCHAR(40),
  IN p_nombre VARCHAR(200),
  IN p_categoria VARCHAR(120),
  IN p_descripcion TEXT,
  IN p_precio DECIMAL(12,2),
  IN p_stock INT,
  IN p_stock_minimo INT
)
BEGIN
  INSERT INTO productos(codigo, nombre, categoria, descripcion, precio, stock_actual, stock_minimo)
  VALUES(p_codigo, p_nombre, p_categoria, p_descripcion, p_precio, p_stock, p_stock_minimo);
END$$

CREATE PROCEDURE sp_registrar_venta(
  IN p_id_cliente INT,
  IN p_total DECIMAL(12,2),
  OUT p_id_venta_new INT
)
BEGIN
  INSERT INTO ventas(id_cliente, total) VALUES(p_id_cliente, p_total);
  SET p_id_venta_new = LAST_INSERT_ID();
END$$
DELIMITER ;

-- ===== Datos de prueba =====
INSERT INTO usuarios (nombre, username, clave_hash, rol) VALUES
('Admin', 'admin', '$2a$10$hashDeEjemploNoSeguro', 'ADMIN'),
('Cliente Demo', 'cliente', '$2a$10$hashDeEjemploNoSeguro', 'CLIENTE');

INSERT INTO clientes (id_cliente, cedula, telefono, credito_restante) VALUES
(2, '1-1111-1111', '8888-8888', 100000.00);

INSERT INTO productos (codigo, nombre, categoria, descripcion, precio, stock_actual, stock_minimo) VALUES
('P-001', 'Filtro de aceite', 'Filtros', 'Filtro estándar', 3500.00, 40, 5),
('P-002', 'Bujía NGK', 'Encendido', 'Bujía estándar NGK', 2500.00, 100, 10);
