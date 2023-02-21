DELIMITER $$
CREATE TRIGGER TEST 
BEFORE INSERT ON CLIENTES
FOR EACH ROW 
BEGIN
      SET NEW.EDAD = timestampdiff(YEAR,NEW.FECHANACIMIENTO,CURRENT_DATE());
END;$$

DELIMITER $$
CREATE PROCEDURE TRANSACCION(IN CUENTAORIGEN VARCHAR(40), IN CUENTADESTINO VARCHAR(40), IN DINERO DECIMAL)
BEGIN
    DECLARE ORIGEN_SALDO DECIMAL(18,2);
    START TRANSACTION;
    SELECT saldo INTO ORIGEN_SALDO FROM CUENTAS WHERE NUMEROCUENTA = CUENTAORIGEN FOR UPDATE;
    IF (ORIGEN_SALDO >= DINERO) THEN
        UPDATE CUENTAS AS A
            INNER JOIN CUENTAS AS B ON A.NUMEROCUENTA = B.NUMEROCUENTA
            SET A.saldo = A.saldo - DINERO
        WHERE A.NUMEROCUENTA = CUENTAORIGEN;
        UPDATE CUENTAS AS A
            INNER JOIN CUENTAS AS B ON A.NUMEROCUENTA = B.NUMEROCUENTA
            SET A.saldo = A.saldo + DINERO
        WHERE A.NUMEROCUENTA = CUENTADESTINO;
        COMMIT;
    ELSE
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La cuenta de origen no tiene suficiente saldo para realizar la transferencia';
    END IF;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE verificarFechaNacimiento(IN fechaNacimiento DATE)
BEGIN
  DECLARE fechaActual DATE;
  SET fechaActual = CURRENT_DATE();
  IF (fechaNacimiento > fechaActual) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La fecha de nacimiento es mayor a la fecha actual.';
  END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE fechasRecibido(IN CODIGO INT,IN FECHA_INICIO DATE, IN FECHA_FIN DATE)
BEGIN
    SELECT *
        FROM operaciones t
        INNER JOIN Cuentas c ON t.codigoCuentaOrigen = c.numeroCuenta
        WHERE t.codigoCuentaDestino IN (
        SELECT numeroCuenta FROM Cuentas WHERE codigoCliente = CODIGO
        ) AND c.codigoCliente != CODIGO AND t.fechaOperacion BETWEEN FECHA_INICIO AND FECHA_FIN;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE fechasTransferido(IN CODIGO INT,IN FECHA_INICIO DATE, IN FECHA_FIN DATE)
BEGIN
    SELECT *
        FROM operaciones t
        INNER JOIN Cuentas c ON t.codigoCuentaOrigen = c.numeroCuenta
        INNER JOIN Clientes cl ON c.codigoCliente = cl.codigo
    WHERE cl.codigo = CODIGO AND t.fechaOperacion BETWEEN FECHA_INICIO AND FECHA_FIN;
END$$
DELIMITER ;
