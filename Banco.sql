DROP DATABASE BANCO;
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
  codigoDireccion INT,
  contrasena BLOB(40),
  FOREIGN KEY (codigoDireccion) REFERENCES Direcciones(codigo)
);

CREATE TABLE Cuentas (
  numeroCuenta VARCHAR(20) PRIMARY KEY,
  fechaApertura DATE DEFAULT (CURRENT_DATE),
  nombre VARCHAR(20),
  saldo DECIMAL,
  estado enum("Activa","Cancelada") DEFAULT "Activa",
  codigoCliente INT,
  FOREIGN KEY (codigoCliente) REFERENCES Clientes(codigo)
);

CREATE TABLE RetirosSinCuenta (
  folio INT PRIMARY KEY auto_increment,
  password VARCHAR(40),
  fechaApertura DATETIME DEFAULT (current_timestamp()),
  numeroCuenta varchar(40),
  estado enum("No cobrado","Activo","Cobrado"),
  FOREIGN KEY (numeroCuenta) REFERENCES Cuentas(numeroCuenta)
);

CREATE TABLE Operaciones (
  codigo INT PRIMARY KEY AUTO_INCREMENT,
  fechaOperacion DATE DEFAULT (CURRENT_DATE),
  descripcion VARCHAR(150),
  monto DECIMAL,
  codigoCuentaOrigen VARCHAR(20),
  codigoCuentaDestino VARCHAR(20),
  FOREIGN KEY (codigoCuentaOrigen) REFERENCES Cuentas(numeroCuenta),
  FOREIGN KEY (codigoCuentaDestino) REFERENCES Cuentas(numeroCuenta)
);