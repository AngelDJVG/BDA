
CREATE DATABASE BANCO;
USE BANCO;
CREATE TABLE Direcciones (
  codigo INT PRIMARY KEY AUTO_INCREMENT,
  calle VARCHAR(50),
  colonia VARCHAR(50),
  numeroCasa VARCHAR(50)
);

CREATE TABLE Clientes (
  codigo INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  apellidoPaterno VARCHAR(40) NOT NULL,
  apellidoMaterno VARCHAR(40) NOT NULL,
  fechaNacimiento DATE,
  edad INT,
  codigoDireccion INT UNIQUE,
  contrasena VARCHAR(40),
  FOREIGN KEY (codigoDireccion) REFERENCES Direcciones(codigo)
);

CREATE TABLE Cuentas (
  numeroCuenta VARCHAR(20) PRIMARY KEY,
  fechaApertura DATE,
  saldo DECIMAL,
  estado enum("Activa","Cancelada"),
  codigoCliente INT UNIQUE,
  FOREIGN KEY (codigoCliente) REFERENCES Clientes(codigo)
);

CREATE TABLE RetirosSinCuenta (
  folio INT PRIMARY KEY,
  password VARCHAR(40),
  numeroCuenta varchar(40) UNIQUE,
  estado enum("No cobrado","Activo"),
  FOREIGN KEY (numeroCuenta) REFERENCES Cuentas(numeroCuenta)
);

CREATE TABLE Operaciones (
  codigo INT PRIMARY KEY AUTO_INCREMENT,
  fechaOperacion DATE ,
  descripcion VARCHAR(150),
  monto DECIMAL,
  codigoCuentaOrigen VARCHAR(20),
  codigoCuentaDestino VARCHAR(20),
  FOREIGN KEY (codigoCuentaOrigen) REFERENCES Cuentas(numeroCuenta),
  FOREIGN KEY (codigoCuentaDestino) REFERENCES Cuentas(numeroCuenta)
);
DELIMITER $$
CREATE TRIGGER TEST 
BEFORE INSERT ON CLIENTES
FOR EACH ROW 
BEGIN
      SET NEW.EDAD = timestampdiff(YEAR,NEW.FECHANACIMIENTO,CURRENT_DATE());
END;$$
