create database Estanciero

use Estanciero

-- CREACION DE TABLAS
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

CREATE TABLE TiposDeEscrituras
(
	idTipoDeEscritura int identity(1,1),
	descripcion varchar(50),

	CONSTRAINT PK_TIPOS_DE_ESCRITURAS PRIMARY KEY (idTipoDeEscritura)
);

CREATE TABLE Mejoras
(
	idMejora int identity(1,1),
	descripcion varchar(50),
	valor int

	CONSTRAINT PK_MEJORAS PRIMARY KEY (idMejora)
);

CREATE TABLE TiposDeJugadores
(
	idTipoDeJugadores int identity(1,1),
	tipo varchar(50),

	CONSTRAINT PK_TIPOS_DE_JUGADORES PRIMARY KEY (idTipoDeJugadores)
)

CREATE TABLE Jugadores
(
	idJugador int identity(1,1),
	nombre varchar(50),
	anio int,
	idTipoDeJugadores int

	CONSTRAINT PK_JUGADORES PRIMARY KEY (idJugador),
	CONSTRAINT FK_TIPOS_DE_JUGADORES FOREIGN KEY (idTipoDeJugadores)
	REFERENCES TiposDeJugadores(idTipoDeJugadores)
);

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

CREATE TABLE Casillas
(
	idCasilla int identity(1,1),
	descripcion varchar(50),
	idTipoDeCasilla int,
	disponibilidad bit,
	precio int,
	cobrarPagar bit,
	aceptaMejoras bit,
	idCarta int

	CONSTRAINT PK_SQUARES PRIMARY KEY (idCasilla)

	CONSTRAINT FK_TIPO_DE_CASILLAS FOREIGN KEY (idTipoDeCasilla)
	REFERENCES TiposDeCasillas (idTipoDeCasilla)
);

CREATE TABLE Tableros
(
	idTablero int identity(1,1),
	idCasilla int,

	CONSTRAINT PK_TABLEROS PRIMARY KEY (idTablero),

	CONSTRAINT FK_CASILLAS FOREIGN KEY (idCasilla)
	REFERENCES Casillas (idCasilla)
);

CREATE TABLE Cartas
(
	idCarta int identity(1,1),
	idTablero int,
	descripcion varchar(100),
	idTipoDeCarta int,
	condicion int,
	precio int

	CONSTRAINT PK_CARTAS PRIMARY KEY (idCarta),

	CONSTRAINT FK_TABLEROS FOREIGN KEY (idTablero)
	REFERENCES Tableros (idTablero),

	CONSTRAINT FK_TIPOS_DE_CARTAS FOREIGN KEY (idTipoDeCarta)
	REFERENCES TiposDeCartas (idTipoDeCarta)
);

CREATE TABLE Juegos
(
	idJuego int identity(1,1),
	fechaInicial datetime,
	fechaFinal datetime,
	idGanador int,
	idAjuste int,
	idTablero int,
	idBanco int

	CONSTRAINT PK_JUEGOS PRIMARY KEY (idJuego),

	CONSTRAINT FK_AJUSTES FOREIGN KEY (idAjuste)
	REFERENCES Ajustes (idAjuste),

	CONSTRAINT FK_TABLERO_JUEGOS FOREIGN KEY (idTablero)
	REFERENCES Tableros (idTablero),

	CONSTRAINT FK_BANCOS FOREIGN KEY (idBanco)
	REFERENCES Bancos (idBanco),
);

CREATE TABLE Peones
(
	idPeon int identity(1,1),
	idJugador int,
	saldo int,
	idColor int,
	bancarrota bit,
	posicionActual int,
	esBot bit,
	ordenDeJuego int

	CONSTRAINT PK_PEONES PRIMARY KEY (idPeon),

	CONSTRAINT FK_JUGADORES FOREIGN KEY (idJugador)
	REFERENCES Jugadores (idJugador),

	CONSTRAINT FK_COLORES FOREIGN KEY (idColor)
	REFERENCES Colores (idColor)
);

CREATE TABLE Juegos_Peones
(
	idJuegoPeon int identity(1,1),
	idPeon int,
	idJuego int,
	idEstado int

	CONSTRAINT PK_JUEGOS_PEONES PRIMARY KEY (idJuegoPeon),

	CONSTRAINT FK_PEONES FOREIGN KEY (idPeon)
	REFERENCES Peones (idPeon),

	CONSTRAINT FK_JUEGOS FOREIGN KEY (idJuego) 
	REFERENCES Juegos (idJuego),

	CONSTRAINT FK_ESTADOS FOREIGN KEY (idEstado)
	REFERENCES Estados (idEstado)
);

CREATE TABLE Movimientos
(
	idMovimiento int identity(1,1),
	idPeon int,
	idJuego int,
	posicionPrevia int,
	nuevaPosicion int

	CONSTRAINT PK_MOVIMIENTOS PRIMARY KEY (idMovimiento),

	CONSTRAINT FK_PEONES_MOVIMIENTOS FOREIGN KEY (idPeon)
	REFERENCES Peones (idPeon),

	CONSTRAINT FK_JUEGOS_MOVIMIENTOS FOREIGN KEY (idJuego) 
	REFERENCES Juegos (idJuego),
);

CREATE TABLE Escrituras
(
	idEscritura int identity(1,1),
	idPeon int,
	nombre varchar(50),
	idTipoDeEscritura int,
	precio int,
	valorHipotecario int,
	disponibilidad bit,
	idMejora int

	CONSTRAINT PK_ESCRITURAS PRIMARY KEY (idEscritura),

	CONSTRAINT FK_PEONES_ESCRITURAS FOREIGN KEY (idPeon)
	REFERENCES Peones (idPeon),

	CONSTRAINT FK_TIPO_DE_ESCRITURA FOREIGN KEY (idTipoDeEscritura) 
	REFERENCES TiposDeEscrituras (idTipoDeEscritura),

	CONSTRAINT FK_MEJORAS FOREIGN KEY (idMejora)
	REFERENCES Mejoras (idMejora)
);

-- CARGA DE DATOS
    
--Tipos de cartas
insert into TiposDeCartas(tipoDeCarta)values('SUERTE')
insert into TiposDeCartas(tipoDeCarta)values('DESTINO')

--Tipos de jugadores
insert into TiposDeJugadores(tipo) values('HUMANO')
insert into TiposDeJugadores(tipo) values('BOT_CONSERVADOR')
insert into TiposDeJugadores(tipo) values('BOT_MODERADO')
insert into TiposDeJugadores(tipo) values('BOT_AGRESIVO')

--Tipos de casillas
insert into TiposDeCasillas(descripcion, cantidad) values('CAMPO',14)
insert into TiposDeCasillas(descripcion, cantidad) values('ESPECIAL',20)
insert into TiposDeCasillas(descripcion, cantidad) values('SERVICIOS_PUBLICOS',2)
insert into TiposDeCasillas(descripcion, cantidad) values('ESTACIONES',4)

--Estados
insert into Estados(estado) values('EN_JUEGO')
insert into Estados(estado) values('BANCARROTA')

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

--Tipos de escrituras
insert into TiposDeEscrituras (descripcion) values ('CAMPO')
insert into TiposDeEscrituras (descripcion) values ('FERROCARRIL')
insert into TiposDeEscrituras (descripcion) values ('BODEGA')
insert into TiposDeEscrituras (descripcion) values ('PETROLERA')
insert into TiposDeEscrituras (descripcion) values ('INGENIO')

--Escrituras
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'FORMOSA ZONA SUR', 1, 1000, 500, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'FORMOSA ZONA CENTRO', 1, 1000, 500, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'FORMOSA ZONA NORTE', 1, 1200, 600, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'RIO NEGRO ZONA SUR', 1, 2000, 1000, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'RIO NEGRO ZONA NORTE', 1, 2200, 1100, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'COMPAÑIA PETROLERA', 4, 3600, 1800, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'SALTA ZONA SUR', 1, 2600, 1300, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'SALTA ZONA CENTRO', 1, 2600, 1300, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'GENERAL BELGRANO', 2, 3600, 1800, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'SALTA ZONA NORTE', 1, 3000, 1500, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'BODEGA', 3, 3600, 1800, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'MENDOZA ZONA SUR', 1, 3400, 1700, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'GENERAL S. MARTIN', 2, 3600, 1800, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'MENDOZA ZONA CENTRO', 1, 3400, 1700, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'MENDOZA ZONA NORTE', 1, 3600, 1800, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'GENERAL B. MITRE', 2, 3600, 1800, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'SANTA FE ZONA SUR', 1, 4200, 2100, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'SANTA FE ZONA CENTRO', 1, 4200, 2100, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'SANTA FE ZONA NORTE', 1, 4600, 2300, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'GENERAL URQUIZA', 2, 3600, 1800, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'TUCUMAN ZONA SUR', 1, 5000, 2500, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'TUCUMAN ZONA NORTE', 1, 5400, 2700, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'INGENIO', 5, 3200, 1600, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'CORDOBA ZONA SUR', 1, 6000, 3000, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'CORDOBA ZONA CENTRO', 1, 6000, 3000, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'CORDOBA ZONA NORTE', 1, 6400, 3200, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'BUENOS AIRES ZONA SUR', 1, 7000, 3500, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'BUENOS AIRES ZONA CENTRO', 1, 7000, 3500, 0, 0)
insert into Escrituras (idPeon, nombre, idTipoDeEscritura, precio, valorHipotecario, disponibilidad, idMejora)
values (NULL, 'BUENOS AIRES ZONA NORTE', 1, 7400, 3700, 0, 0)
