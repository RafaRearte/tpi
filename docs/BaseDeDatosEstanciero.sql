create database Estanciero

use Estanciero

CREATE TABLE Dificultades
(
	idDificultad int identity(1,1),
	dificultad varchar(50)

	CONSTRAINT PK_DIFICULTADES PRIMARY KEY (idDificultad)
);

CREATE TABLE Bancos
(
	idBanco int identity(1,1),
	saldo int

	CONSTRAINT PK_BANCOS PRIMARY KEY (idBanco)
);

CREATE TABLE TiposDeCartas
(
	idTipoDeCarta int identity(1,1),
	tipoDeCarta varchar(50)

	CONSTRAINT PK_TIPOS_DE_CARTAS PRIMARY KEY (idTipoDeCarta)
);

CREATE TABLE TiposDeMejoras
(
	idTipoDeMejora int identity(1,1),
	tipoDeMejora varchar(50)

	CONSTRAINT PK_TIPOS_DE_MEJORAS PRIMARY KEY (idTipoDeMejora)
);

CREATE TABLE TiposDeCasillas
(
	idTipoDeCasilla int identity(1,1),
	descripcion varchar(50),
	cantidad int

	CONSTRAINT PK_TIPOS_DE_CASILLAS PRIMARY KEY (idTipoDeCasilla)
);


CREATE TABLE Estados
(
	idEstado int identity(1,1),
	estado varchar(50)

	CONSTRAINT PK_ESTADOS PRIMARY KEY (idEstado)
);

CREATE TABLE TiposDeJugadores
(
	idTipoDeJugadores int identity(1,1),
	tipo varchar(50),

	CONSTRAINT PK_TIPOS_DE_JUGADORES PRIMARY KEY (idTipoDeJugadores)
)

CREATE TABLE Colores
(
	idColor int identity(1,1),
	color varchar(50)

	CONSTRAINT PK_COLORES PRIMARY KEY (idColor)
);

CREATE TABLE Ajustes
(
	idAjuste int identity(1,1),
	idDificultad int,
	montoGanador int

	CONSTRAINT PK_AJUSTES PRIMARY KEY (idAjuste),

	CONSTRAINT FK_DIFICULTADES FOREIGN KEY (idDificultad)
	REFERENCES Dificultades (idDificultad)
);

CREATE TABLE Cartas
(
	idCarta int identity(1,1),
	descripcion varchar(100),
	idTipoDeCarta int,
	condicion int,
	precio int

	CONSTRAINT PK_CARTAS PRIMARY KEY (idCarta),

	CONSTRAINT FK_TIPOS_DE_CARTAS FOREIGN KEY (idTipoDeCarta)
	REFERENCES TiposDeCartas (idTipoDeCarta)
);

CREATE TABLE Casillas
(
	idCasilla int identity(1,1),
	descripcion varchar(50),
	idTipoDeCasilla int,
	cobrarPagar bit

	CONSTRAINT PK_SQUARES PRIMARY KEY (idCasilla)

	CONSTRAINT FK_TIPO_DE_CASILLAS FOREIGN KEY (idTipoDeCasilla)
	REFERENCES TiposDeCasillas (idTipoDeCasilla)
);

CREATE TABLE Escrituras
(
	idEscritura int identity(1,1),
	idCasilla int UNIQUE NOT NULL,
	nombre varchar(50),
	precio int,
	valorAlquiler int,
	valorHipotecario int,
	disponibilidad bit,
	sePuedeMejorar bit

	CONSTRAINT PK_ESCRITURAS PRIMARY KEY (idEscritura),

	CONSTRAINT FK_CASILLAS_ESCRITURAS FOREIGN KEY (idCasilla)
	REFERENCES Casillas (idCasilla)
);

CREATE TABLE Mejoras
(
	idMejora int identity(1,1),
	idTipoDeMejora int,
	descripcion varchar(50),
	idEscritura INT NOT NULL,
	valor int

	CONSTRAINT PK_MEJORAS PRIMARY KEY (idMejora),
	CONSTRAINT FK_MEJORA_ESCRITURA FOREIGN KEY (idEscritura) REFERENCES Escrituras(idEscritura),
	CONSTRAINT FK_TIPO_DE_MEJORAS FOREIGN KEY (idTipoDeMejora)
	REFERENCES TiposDeMejoras (idTipoDeMejora)
);

CREATE TABLE Jugadores
(
	idJugador int identity(1,1),
	nombre varchar(200),
	saldo int,
	idColor int,
	bancarrota bit,
	idTipoDeJugador int,
	idCasillaActual int,
	idEscrituras int,
	esBot bit,
	ordenDeJuego int

	CONSTRAINT PK_JUGADORES PRIMARY KEY (idJugador),

	CONSTRAINT FK_CASILLAS_JUGADORES FOREIGN KEY (idCasillaActual)
	REFERENCES Casillas (idCasilla),

	CONSTRAINT FK_ESCRITURAS_JUGADORES FOREIGN KEY (idEscrituras)
	REFERENCES Escrituras (idEscritura),

	CONSTRAINT FK_COLORES FOREIGN KEY (idColor)
	REFERENCES Colores (idColor),

	CONSTRAINT FK_TIPO_DE_JUGADORES FOREIGN KEY (idTipoDeJugador)
	REFERENCES TiposDeJugadores (idTipoDeJugadores)
);

CREATE TABLE Tableros
(
	idTablero int identity(1,1),
	idCasilla int,
	idCarta int

	CONSTRAINT PK_TABLEROS PRIMARY KEY (idTablero),

	CONSTRAINT FK_CASILLAS FOREIGN KEY (idCasilla)
	REFERENCES Casillas (idCasilla),

	CONSTRAINT FK_CARTAS FOREIGN KEY (idCarta)
	REFERENCES Cartas (idCarta)
);

CREATE TABLE Juegos
(
	idJuego int identity(1,1),
	fechaInicial datetime,
	fechaFinal datetime,
	idGanador int,
	idJugadores int,
	idAjuste int,
	idTablero int,
	idBanco int

	CONSTRAINT PK_JUEGOS PRIMARY KEY (idJuego),

	CONSTRAINT FK_JUGADORES FOREIGN KEY (idJugadores)
	REFERENCES Jugadores (idJugador),

	CONSTRAINT FK_AJUSTES FOREIGN KEY (idAjuste)
	REFERENCES Ajustes (idAjuste),

	CONSTRAINT FK_TABLERO_JUEGOS FOREIGN KEY (idTablero)
	REFERENCES Tableros (idTablero),

	CONSTRAINT FK_BANCOS FOREIGN KEY (idBanco)
	REFERENCES Bancos (idBanco),
);

CREATE TABLE Juegos_Jugadores
(
	idJuegoPeon int identity(1,1),
	idJugador int,
	idJuego int,
	idEstado int

	CONSTRAINT PK_JUEGOS_JUGADORES PRIMARY KEY (idJuegoPeon),

	CONSTRAINT FK_JUGADOR FOREIGN KEY (idJugador)
	REFERENCES Jugadores (idJugador),

	CONSTRAINT FK_JUEGOS FOREIGN KEY (idJuego) 
	REFERENCES Juegos (idJuego),

	CONSTRAINT FK_ESTADOS FOREIGN KEY (idEstado)
	REFERENCES Estados (idEstado)
);

CREATE TABLE Movimientos
(
	idMovimiento int identity(1,1),
	idJugador int,
	idJuego int,
	posicionPrevia int,
	nuevaPosicion int

	CONSTRAINT PK_MOVIMIENTOS PRIMARY KEY (idMovimiento),

	CONSTRAINT FK_JUGADORES_MOVIMIENTOS FOREIGN KEY (idJugador)
	REFERENCES Jugadores (idJugador),

	CONSTRAINT FK_JUEGOS_MOVIMIENTOS FOREIGN KEY (idJuego) 
	REFERENCES Juegos (idJuego),
);

-- CARGA DE DATOS
    
--Tipos de cartas
insert into TiposDeCartas(tipoDeCarta)values('SUERTE')
insert into TiposDeCartas(tipoDeCarta)values('DESTINO')

select * from TiposDeCartas

--Tipos de cartas
insert into TiposDeMejoras(tipoDeMejora)values('CHACRA')
insert into TiposDeMejoras(tipoDeMejora)values('ESTANCIA')

select * from TiposDeMejoras

-- Cartas destino
insert into Cartas(descripcion, idTipoDeCarta, condicion, precio)
values ('5% de interés sobre cédulas hipotecarias. Cobre 500.', 2, 0, 500),
       ('Devolución de impuesto. Cobre 400.', 2, 0, 400),
       ('Marche preso directamente.', 2, 0, 0),
       ('Pague 200 de multa o levante una tarjeta de SUERTE.', 2, 0, 200),
       ('5% de interés sobre cédulas hipotecarias. Cobre 500.', 2, 0, 500),
       ('Devolución de impuesto. Cobre 400.', 2, 0, 400),
       ('Error en los cálculos del Banco. Cobre 4.000.', 2, 0, 4000),
       ('Es su cumpleaños. Cobre 200 de cada uno de sus jugadores.', 2, 0, 200),
       ('Pague su póliza de seguro con 1.000.', 2, 0, 1000),
       ('Por venta de acciones. Cobre 1.000.', 2, 0, 1000),
       ('Error en los cálculos del Banco. Cobre 4.000.', 2, 0, 4000),
       ('Es su cumpleaños. Cobre 2000 de cada uno de sus jugadores.', 2, 0, 2000),
       ('Gastos de Farmacia. Pague 1.000.', 2, 0, 1000),
       ('Ha ganado un concurso agrícola. Cobre 2.000.', 2, 0, 2000),
       ('Siga hasta la salida.', 2, 0, 0),
       ('Vuelve atrás hasta Formosa Zona Sur.', 2, 0, 0),
       ('Gastos de Farmacia. Pague 1.000.', 2, 0, 1000),
       ('Ha ganado un concurso agrícola. Cobre 2.000.', 2, 0, 2000),
       ('Ha obtenido un segundo premio de belleza. Cobre 200.', 2, 0, 200),
       ('Hereda 2.000.', 2, 0, 2000),
       ('Devolución de impuesto. Cobre 4000.', 2, 0, 4000),
       ('Ha obtenido un segundo premio de belleza. Cobre 2000.', 2, 0, 2000),
       ('Ha obtenido un segundo premio de belleza. Cobre 2.000.', 2, 0, 2000),
       ('Hereda 2.000.', 2, 0, 2000)

-- Cartas suerte
insert into Cartas(descripcion, idTipoDeCarta, condicion, precio)
values ('Cobre 1.000 por intereses bancarios.', 1, 0, 1000),
       ('Ganó en las carreras. Cobre 3.000.', 1, 0, 3000),
       ('Multa por exceso de velocidad. Pague 300.', 1, 0, 300),
       ('Pague 3.000 por gastos colegiales.', 1, 0, 3000),
       ('Pague 5.000 por gastos colegiales.', 1, 0, 5000),
       ('Vuelva tres pasos atrás.', 1, 0, 0),
       --('Habeas Corpus concedido. Con esta tarjeta sale usted gratuitamente de la Comisaria. Consérvela o véndala.', 1, 0, 0),
       ('Ha ganado la grande. Cobre 10.000.', 1, 0, 10000),
       ('Siga hasta santa Fe, Zona Norte. Si pasa por la salida cobre 5.000.', 1, 0, 5000),
       ('Por compra de semilla pague al Banco 800 por cada chacra. 4000 por cada estancia.', 1, 0, 800),
       ('Multa caminera. Pague 4000.', 1, 0, 4000),
       ('Multa por exceso de velocidad. Pague 3000.', 1, 0, 3000),
       ('Marche preso directamente.', 1, 0, 0),
       ('Haga un paseo hasta la Bodega. Si pasa por la salida cobre 5.000.', 1, 0, 5000),
       ('Siga hasta la salida.', 1, 0, 0),
       ('Siga hasta Buenos Aires, Zona Norte.', 1, 0, 0),
       ('Ganó en las carreras. Cobre 5.000.', 1, 0, 5000),
       ('Cobre 3000 por intereses bancarios.', 1, 0, 3000),
       ('Multa por exceso de velocidad. Pague 300.', 1, 0, 300),
       ('Multa caminera. Pague 400.', 1, 0, 400),
       ('Siga hasta Salta, Zona Norte. Si pasa por la salida cobre 5.000.', 1, 0, 5000),
       --('Sus propiedades tienen que ser reparadas. Pague al Banco 500 por cada chacra y 2.500 por cada estancia.', 1, 0, 500),
       ('Ha ganado la grande. Cobre 10.000.', 1, 0, 10000),
       ('Por compra de semilla pague al Banco 1.000 por cada chacra. 5.000 por cada estancia.', 1, 0, 1000)

select * from Cartas

--Tipos de jugadores
insert into TiposDeJugadores(tipo) values('HUMANO')
insert into TiposDeJugadores(tipo) values('BOT_CONSERVADOR')
insert into TiposDeJugadores(tipo) values('BOT_MODERADO')
insert into TiposDeJugadores(tipo) values('BOT_AGRESIVO')

select * from TiposDeJugadores


--Tipos de casillas
/*
	Se asume que los tipos de casillas son:
	-Escrituras
	-Servicios publicos -> impuestos a pagar
	-Especial -> las casillas como salida, premio ganadero, comisaria, marche preso, descanso y libre estacionamiento
	-Suerte
	-Destino
*/
insert into TiposDeCasillas(descripcion, cantidad) values('ESCRITURAS', 29)
insert into TiposDeCasillas(descripcion, cantidad) values('ESPECIAL',6)
insert into TiposDeCasillas(descripcion, cantidad) values('SERVICIOS_PUBLICOS',2)
insert into TiposDeCasillas(descripcion, cantidad) values('SUERTE', 2)
insert into TiposDeCasillas(descripcion, cantidad) values('DESTINO', 3)

select * from TiposDeCasillas

-- Casillas
-- Casillas del tablero Estanciero (en orden de recorrido)
insert into Casillas(descripcion, idTipoDeCasilla, cobrarPagar)
values ('SALIDA', 2, 0),                              -- 0. Esquina inferior derecha
       ('FORMOSA ZONA SUR', 1, 1),                    -- 1. Provincia Formosa Zona Sur
       ('FORMOSA ZONA CENTRO', 1, 1),                 -- 2. Provincia Formosa Zona Centro
       ('FORMOSA ZONA NORTE', 1, 1),                  -- 3. Provincia Formosa Zona Norte
       ('IMPUESTO A LOS REDITOS', 3, 1),              -- 4. Impuesto
       ('RIO NEGRO ZONA SUR', 1, 1),                  -- 5. Provincia Rio Negro Zona Sur
       ('RIO NEGRO ZONA NORTE', 1, 1),                -- 6. Provincia Rio Negro Zona Norte
       ('PREMIO GANADERO', 2, 0),                     -- 7. Premio Ganadero                  
       ('COMPAÑIA PETROLERA', 1, 1),                  -- 8. Compañía Petrolera
	   ('SALTA ZONA SUR', 1, 1),                      -- 9. Provincia Salta Zona Sur
	   ('DESTINO', 5, 0),                             -- 10. Carta Destino
	   ('SALTA ZONA CENTRO', 1, 1),                   -- 11. Provincia Salta Zona Centro
	   ('GENERAL BELGRANO', 1, 1),                    -- 12. Ferrocarril General Belgrano
	   ('SALTA ZONA NORTE', 1, 1),                    -- 13. Provincia Salta Zona Norte
       ('COMISARIA', 2, 0),                           -- 14. Esquina inferior izquierda - Comisaría                
       ('SUERTE', 4, 0),                              -- 15. Carta Suerte
       ('BODEGA', 1, 1),                              -- 16. Bodega
       ('MENDOZA ZONA SUR', 1, 1),                    -- 17. Provincia Mendoza Zona Sur
       ('GENERAL S. MARTIN', 1, 1),                   -- 18. Ferrocarril General San Martín
       ('MENDOZA ZONA CENTRO', 1, 1),                 -- 19. Provincia Mendoza Zona Centro
       ('MENDOZA ZONA NORTE', 1, 1),                  -- 20. Provincia Mendoza Zona Norte
	   ('DESCANSO', 2, 1),                            -- 21. Ferrocarril General San Martín
	   ('GENERAL B. MITRE', 1, 1),                    -- 22. Ferrocarril General Bartolomé Mitre       
       ('SANTA FE ZONA SUR', 1, 1),                   -- 23. Provincia Santa Fe Zona Sur    
       ('SANTA FE ZONA CENTRO', 1, 1),                -- 24. Provincia Santa Fe Zona Centro
	   ('DESTINO', 5, 0),                             -- 25. Carta Destino
       ('SANTA FE ZONA NORTE', 1, 1),                 -- 26. Provincia Santa Fe Zona Norte
       ('GENERAL URQUIZA', 1, 1),                     -- 27. Ferrocarril General Urquiza
	   ('LIBRE ESTACIONAMIENTO', 2, 0),               -- 28. Esquina superior izquierda - Libre Estacionamiento
       ('TUCUMAN ZONA SUR', 1, 1),                    -- 29. Provincia Tucuman Zona Sur	 
       ('TUCUMAN ZONA NORTE', 1, 1),                  -- 30. Provincia Tucuman Zona Norte
       ('INGENIO', 1, 1),                             -- 31. Ingenio   
       ('CORDOBA ZONA SUR', 1, 1),                    -- 32. Provincia Córdoba Zona Sur
       ('CORDOBA ZONA CENTRO', 1, 1),                 -- 33. Provincia Córdoba Zona Centro       
       ('CORDOBA ZONA NORTE', 1, 1),                  -- 34. Provincia Córdoba Zona Norte
       ('MARCHE PRESO', 2, 0),                        -- 35. Esquina superior derecha - Marche Preso
	   ('SUERTE', 4, 0),                              -- 36. Carta Suerte    
       ('BUENOS AIRES ZONA SUR', 1, 1),               -- 37. Provincia Buenos Aires Zona Sur
	   ('DESTINO', 5, 0),                             -- 38. Carta Destino     
       ('BUENOS AIRES ZONA CENTRO', 1, 1),            -- 39. Provincia Buenos Aires Zona Centro
       ('BUENOS AIRES ZONA NORTE', 1, 1),             -- 40. Provincia Buenos Aires Zona Norte
	   ('IMPUESTO DE LUJO', 3, 1)                     -- 41. Impuesto de lujo
       
--Reset identity seed to 1 (run this only if the table already exists and has had records)       
DBCC CHECKIDENT ('Casillas', RESEED, 0);

delete from Casillas

select * from Casillas

--Estados
insert into Estados(estado) values('EN_JUEGO')
insert into Estados(estado) values('BANCARROTA')

select * from Estados

--Dificultades
insert into Dificultades(dificultad) values('FACIL')
insert into Dificultades(dificultad) values('MEDIO')
insert into Dificultades(dificultad) values('DIFICIL')

--Colores
insert into Colores(color) values('ROJO')
insert into Colores(color) values('AZUL')
insert into Colores(color) values('VERDE')
insert into Colores(color) values('NARANJA')
insert into Colores(color) values('BLANCO')
insert into Colores(color) values('NEGRO')

-- Mejoras
insert into Mejoras(descripcion, valor)--No la pude insertar por los cambios
values ('FORMOSA', 1000),
		('RIO NEGRO', 1000),
		('SALTA', 1500),
		('MENDOZA', 2000),
		('SANTA FE', 2500),
		('TUCUMAN', 3000),
		('CORDOBA', 3000),
		('BUENOS AIRES', 4000)

select * from Mejoras

--Escrituras
insert into Escrituras(nombre, precio, valorHipotecario, disponibilidad, valorAlquiler, sePuedeMejorar, idCasilla)
values 
    ('FORMOSA ZONA SUR', 1000, 500, 0, 40, 0, 2),
    ('FORMOSA ZONA CENTRO', 1000, 500, 0, 40, 0, 3),
    ('FORMOSA ZONA NORTE', 1200, 600, 0, 80, 0, 4),
    ('RIO NEGRO ZONA SUR', 2000, 1000, 0, 110, 0, 6),
    ('RIO NEGRO ZONA NORTE', 2200, 1100, 0, 150, 0, 7),
    ('COMPAÑIA PETROLERA', 3600, 1800, 0, null, 1, 9),
    ('SALTA ZONA SUR', 2600, 1300, 0, 200, 0, 10),
    ('SALTA ZONA CENTRO', 2600, 1300, 0, 200, 0, 12),
    ('GENERAL BELGRANO', 3600, 1800, 0, 500, 1, 13),
    ('SALTA ZONA NORTE', 3000, 1500, 0, 230, 0, 14),
    ('BODEGA', 3600, 1800, 0, null, 1, 17),
    ('MENDOZA ZONA SUR', 3400, 1700, 0, 250, 0, 18),
    ('GENERAL S. MARTIN', 3600, 1800, 0, 500, 1, 19),
    ('MENDOZA ZONA CENTRO', 3400, 1700, 0, 250, 0, 20),
    ('MENDOZA ZONA NORTE', 3600, 1800, 0, 300, 0, 21),
    ('GENERAL B. MITRE', 3600, 1800, 0, 500, 1, 23),
    ('SANTA FE ZONA SUR', 4200, 2100, 0, 350, 0, 24),
    ('SANTA FE ZONA CENTRO', 4200, 2100, 0, 350, 0, 25),
    ('SANTA FE ZONA NORTE', 4600, 2300, 0, 400, 0, 27),
    ('GENERAL URQUIZA', 3600, 1800, 0, 500, 1, 28),
    ('TUCUMAN ZONA SUR', 5000, 2500, 0, 400, 0, 30),
    ('TUCUMAN ZONA NORTE', 5400, 2700, 0, 450, 0, 31),
    ('INGENIO', 3200, 1600, 0, null, 1, 32),
    ('CORDOBA ZONA SUR', 6000, 3000, 0, 500, 0, 33),
    ('CORDOBA ZONA CENTRO', 6000, 3000, 0, 450, 0, 34),
    ('CORDOBA ZONA NORTE', 6400, 3200, 0, 550, 0, 35),
    ('BUENOS AIRES ZONA SUR', 7000, 3500, 0, 650, 0, 38),
    ('BUENOS AIRES ZONA CENTRO', 7000, 3500, 0, 650, 0, 40),
    ('BUENOS AIRES ZONA NORTE', 7400, 3700, 0, 1000, 0, 41);

select * from Escrituras
