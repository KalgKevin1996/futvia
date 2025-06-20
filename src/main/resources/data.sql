-- 🚨 Borra relaciones previas si la tabla existe
DELETE FROM usuario_rol;
DELETE FROM usuario;
DELETE FROM rol;

-- ✅ Insertar roles
INSERT INTO rol (id, nombre) VALUES (1, 'MASTER');
INSERT INTO rol (id, nombre) VALUES (2, 'SUPER_ADMIN');
INSERT INTO rol (id, nombre) VALUES (3, 'ADMIN');
INSERT INTO rol (id, nombre) VALUES (4, 'OPERADOR');
INSERT INTO rol (id, nombre) VALUES (5, 'LIGA_MANAGER');
INSERT INTO rol (id, nombre) VALUES (6, 'TESORERO_LIGA');
INSERT INTO rol (id, nombre) VALUES (7, 'ENTRENADOR');
INSERT INTO rol (id, nombre) VALUES (8, 'CAPITAN_EQUIPO');
INSERT INTO rol (id, nombre) VALUES (9, 'JUGADOR');
INSERT INTO rol (id, nombre) VALUES (10, 'AFICIONADO');
INSERT INTO rol (id, nombre) VALUES (11, 'ALBRITO');
INSERT INTO rol (id, nombre) VALUES (12, 'MODERADOR');
INSERT INTO rol (id, nombre) VALUES (13, 'REPORTERO');
INSERT INTO rol (id, nombre) VALUES (14, 'SOPORTE');
INSERT INTO rol (id, nombre) VALUES (15, 'SCOUT');
INSERT INTO rol (id, nombre) VALUES (16, 'EDITOR_CONTENIDO');
INSERT INTO rol (id, nombre) VALUES (17, 'MARKETING');

-- 🧪 Usuarios de prueba con BCrypt (contraseña común: "123456")

-- Usuario MASTER
INSERT INTO usuario (id, nombre, apellido, email, password, activo) VALUES
(1, 'Kevin', 'Lopez', 'master@futvia.com', '$2a$10$Dnquhm7q2GJXlZSSCDue0.Wtjjw5CmnFETAbq0N6Vqlz/70n5ajQS', true);

-- SUPER_ADMIN
INSERT INTO usuario (id, nombre, apellido, email, password, activo) VALUES
(2, 'Sofia', 'Ramirez', 'superadmin@futvia.com', '$2a$10$Dnquhm7q2GJXlZSSCDue0.Wtjjw5CmnFETAbq0N6Vqlz/70n5ajQS', true);

-- ADMIN
INSERT INTO usuario (id, nombre, apellido, email, password, activo) VALUES
(3, 'Carlos', 'Mendez', 'admin@futvia.com', '$2a$10$Dnquhm7q2GJXlZSSCDue0.Wtjjw5CmnFETAbq0N6Vqlz/70n5ajQS', true);

-- OPERADOR
INSERT INTO usuario (id, nombre, apellido, email, password, activo) VALUES
(4, 'Lucia', 'Perez', 'operador@futvia.com', '$2a$10$Dnquhm7q2GJXlZSSCDue0.Wtjjw5CmnFETAbq0N6Vqlz/70n5ajQS', true);

-- LIGA_MANAGER
INSERT INTO usuario (id, nombre, apellido, email, password, activo) VALUES
(5, 'Miguel', 'Cano', 'liga@futvia.com', '$2a$10$Dnquhm7q2GJXlZSSCDue0.Wtjjw5CmnFETAbq0N6Vqlz/70n5ajQS', true);

-- ENTRENADOR
INSERT INTO usuario (id, nombre, apellido, email, password, activo) VALUES
(6, 'Laura', 'Santos', 'entrenador@futvia.com', '$2a$10$Dnquhm7q2GJXlZSSCDue0.Wtjjw5CmnFETAbq0N6Vqlz/70n5ajQS', true);

-- JUGADOR
INSERT INTO usuario (id, nombre, apellido, email, password, activo) VALUES
(7, 'Diego', 'Castro', 'jugador@futvia.com', '$2a$10$Dnquhm7q2GJXlZSSCDue0.Wtjjw5CmnFETAbq0N6Vqlz/70n5ajQS', true);

-- CAPITAN_EQUIPO
INSERT INTO usuario (id, nombre, apellido, email, password, activo) VALUES
(8, 'Andrea', 'Lopez', 'capitan@futvia.com', '$2a$10$Dnquhm7q2GJXlZSSCDue0.Wtjjw5CmnFETAbq0N6Vqlz/70n5ajQS', true);

-- MODERADOR
INSERT INTO usuario (id, nombre, apellido, email, password, activo) VALUES
(9, 'Felipe', 'Salguero', 'moderador@futvia.com', '$2a$10$Dnquhm7q2GJXlZSSCDue0.Wtjjw5CmnFETAbq0N6Vqlz/70n5ajQS', true);

-- Asociar usuarios con sus roles
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 7),
(7, 9),
(8, 8),
(9, 12);
