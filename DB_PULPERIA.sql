CREATE DATABASE DB_PULPERIA
USE DB_PULPERIA
GO
-- Tabla CLIENTES
CREATE TABLE CLIENTES (
    ID_CLIENTE INT IDENTITY PRIMARY KEY,
    NOMBRE VARCHAR(30) NOT NULL,
    APELLIDO VARCHAR (30) NOT NULL,
    DIRECCION VARCHAR(80) NOT NULL,
    TELEFONO VARCHAR(10) NOT NULL
);

-- Tabla PRODUCTOS
CREATE TABLE PRODUCTOS (
    ID_PRODUCTO INT IDENTITY PRIMARY KEY,
	ID_PROVEEDOR INT,
    DESCRIPCION VARCHAR(30) NOT NULL,
	PRECIOCOMPRA decimal(10,2) NOT NULL,
	PRECIOVENTA decimal(10,2) NOT NULL,
);

-- Tabla FACTURAS
CREATE TABLE FACTURAS (
    ID_FACTURA INT IDENTITY PRIMARY KEY NOT NULL,
    ID_CLIENTE INT NOT NULL,
    ID_USUARIO INT NOT NULL,
    FECHA DATE DEFAULT GETDATE(),
    ESTADO VARCHAR(15) DEFAULT 'PENDIENTE' CHECK (ESTADO IN ('PENDIENTE', 'CANCELADA', 'ANULADA')),
	TOTAL_VENTA DECIMAL(10,2) -- Corregido el nombre de la columna
);


-- Tabla DETALLE_FACTURAS
CREATE TABLE DETALLE_FACTURAS (
	ID_FACTURA int NOT NULL,
    ID_PRODUCTO INT NOT NULL,
    CANTIDAD INT,
	--CONSTRAINT PK_DETALLE_FACTURA PRIMARY KEY (ID_FACTURA,ID_PRODUCTO)
    
);

ALTER TABLE DETALLE_FACTURAS
ADD CONSTRAINT PK_DETALLE_FACTURA PRIMARY KEY (ID_FACTURA, ID_PRODUCTO);


	CREATE TABLE PROVEEDORES (
		ID_PROVEEDOR INT IDENTITY PRIMARY KEY,
		NOMBRE VARCHAR(30) NOT NULL,
		APELLIDO VARCHAR(30) NOT NULL,
		TELEFONO VARCHAR(10) NOT NULL,
		DESCRIPCION VARCHAR(MAX) NOT NULL
	);


CREATE TABLE USUARIOS (
    ID_USUARIO INT IDENTITY PRIMARY KEY,
    NOMBRE VARCHAR(30) NOT NULL,
    PUESTO VARCHAR(15) CHECK (PUESTO IN ('ENCARGADO', 'EMPLEADO')) NOT NULL
);


CREATE TABLE GASTOS (
    ID_GASTO INT IDENTITY PRIMARY KEY,
    FECHA DATE DEFAULT GETDATE(),
    MONTO DECIMAL(10,2) NOT NULL,
    DESCRIPCION VARCHAR(MAX) NOT NULL
);


-- Tabla CIERRE_CAJA
CREATE TABLE CIERRE_CAJA (
    ID_CIERRE INT IDENTITY PRIMARY KEY,
    ID_GASTO INT,
    ID_FACTURA INT,
    FECHA DATE DEFAULT GETDATE(),
    HORA_CIERRE TIME,
    TOTAL_VENTAS DECIMAL(10,2),
);
--DROP





-----------------------------------------------------------------------------
-- Definici�n de la clave for�nea FACTURAS
ALTER TABLE PRODUCTOS
ADD FOREIGN KEY (ID_PROVEEDOR) REFERENCES PROVEEDORES(ID_PROVEEDOR);

-- Agregar las llaves for�neas de la tabla FACTURAS
ALTER TABLE FACTURAS
ADD FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTES(ID_CLIENTE);

ALTER TABLE FACTURAS
ADD FOREIGN KEY (ID_USUARIO) REFERENCES USUARIOS(ID_USUARIO);

-- Agregar las llaves for�neas tabla DETALLE_FACTURAS
ALTER TABLE DETALLE_FACTURAS
ADD FOREIGN KEY (ID_FACTURA) REFERENCES FACTURAS(ID_FACTURA);-------------------------

ALTER TABLE DETALLE_FACTURAS
ADD FOREIGN KEY (ID_PRODUCTO) REFERENCES PRODUCTOS(ID_PRODUCTO);

-- Definici�n de las llaves for�neas
ALTER TABLE CIERRE_CAJA
ADD FOREIGN KEY (ID_FACTURA) REFERENCES FACTURAS(ID_FACTURA);

ALTER TABLE CIERRE_CAJA
ADD FOREIGN KEY (ID_GASTO) REFERENCES GASTOS(ID_GASTO);


--CONSULTAS O M�TODOS �TILES
SELECT * FROM PRODUCTOS
SELECT * FROM CLIENTES
SELECT * FROM PROVEEDORES
SELECT * FROM USUARIOS

--SELECT SUSER_SNAME() AS 'Nombre de Usuario';

--SELECT NUM_FACTURA, FECHA, ESTADO
--FROM FACTURAS
--WHERE ID_CLIENTE = --numero id

-- Insertar proveedores
INSERT INTO PROVEEDORES (NOMBRE, APELLIDO, TELEFONO, DESCRIPCION)
VALUES ('Juan', 'G�mez', '123', 'Coca-Cola');


INSERT INTO PROVEEDORES (NOMBRE, APELLIDO, TELEFONO, DESCRIPCION)
VALUES ('Mar�a', 'L�pez', '456', 'Florida');


INSERT INTO PROVEEDORES (NOMBRE, APELLIDO, TELEFONO, DESCRIPCION)
VALUES ('Pedro', 'Mart�nez', '789', 'Pozuelo');

INSERT INTO USUARIOS (NOMBRE, PUESTO)
VALUES ('Juan Vargas', 'ENCARGADO');
GO

CREATE PROCEDURE EliminarFacturaConDetalles
    @IdFactura INT
AS
BEGIN
    -- Eliminar los detalles de factura asociados a la factura
    DELETE FROM DETALLES_FACTURAS WHERE ID_FACTURA = @IdFactura;

    -- Eliminar la factura
    DELETE FROM FACTURAS WHERE ID_FACTURA = @IdFactura;
	 COMMIT;
END;
go

CREATE TRIGGER ELIMINAR_FACTURA
ON FACTURAS
FOR DELETE
AS
BEGIN
    -- Elimina los detalles de factura asociados a la factura eliminada
    DELETE FROM DETALLES_FACTURAS
    WHERE ID_FACTURA IN (SELECT ID_FACTURA FROM deleted);
END;


SELECT
    name AS TriggerName,
    OBJECT_NAME(parent_id) AS TableName,
    create_date AS CreationDate
FROM sys.triggers;

DROP TRIGGER ELIMINAR_FACTURA;

 SELECT OBJECT_DEFINITION(OBJECT_ID('BORRAR_FACTURAS')) AS 'DefinicionDelTrigger';

 ALTER TABLE DETALLE_FACTURAS
DROP CONSTRAINT PK_DETALLE_FACTURA;

ALTER TABLE DETALLE_FACTURAS
ADD CONSTRAINT PK_DETALLE_FACTURA PRIMARY KEY (ID_FACTURA);
SELECT*FROM FACTURAS
Select*from DETALLE_FACTURAS
SELECT* FROM PRODUCTOS
Select*from GASTOS

-- Agregar la restricci�n de clave primaria utilizando ALTER TABLE
ALTER TABLE DETALLE_FACTURAS
ADD CONSTRAINT PK_DETALLE_FACTURA PRIMARY KEY (ID_FACTURA, ID_PRODUCTO);