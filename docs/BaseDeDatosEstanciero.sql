CREATE DATABASE Estanciero;

USE Estanciero;

CREATE TABLE Jugadores (
    id_jugador INT PRIMARY KEY IDENTITY(1,1),
    nombre VARCHAR(100),
    money INT,
    propiedad VARCHAR(100),
    color VARCHAR(50),
    tipo_usuario VARCHAR(50),
    perfil_virtual VARCHAR(100)
);

CREATE TABLE Dados (
    id_dado INT PRIMARY KEY IDENTITY(1,1),
    valor_dador INT
);

CREATE TABLE Tableros (
    id_tablero INT PRIMARY KEY IDENTITY(1,1),
    id_jugador INT,
    id_dado INT,
    FOREIGN KEY (id_jugador) REFERENCES Jugadores(id_jugador),
    FOREIGN KEY (id_dado) REFERENCES Dados(id_dado)
);

CREATE TABLE Bancos (
    id_banco INT PRIMARY KEY IDENTITY(1,1),
    id_tablero INT,
    valor INT,
    FOREIGN KEY (id_tablero) REFERENCES Tableros(id_tablero)
);

CREATE TABLE TipoCasillas (
    id_tipo_casilla INT PRIMARY KEY IDENTITY(1,1),
    description VARCHAR(100)
);

CREATE TABLE Propiedades (
    id_propiedad INT PRIMARY KEY IDENTITY(1,1),
    nombre VARCHAR(100),
    precio INT,
    jugador INT,
    id_tipo_propiedad INT,
    alquiler INT
);

CREATE TABLE TipoPropiedades (
    id_tipo_propiedad INT PRIMARY KEY IDENTITY(1,1),
    descripcion VARCHAR(100)
);

ALTER TABLE Propiedades
ADD CONSTRAINT FK_Propiedades_Tipo_Propiedades
    FOREIGN KEY (id_tipo_propiedad) REFERENCES TipoPropiedades(id_tipo_propiedad);

CREATE TABLE Casilla (
    id_casilla INT PRIMARY KEY IDENTITY(1,1),
    id_tablero INT,
    id_propiedad INT,
    id_tipo INT,
    posicion INT,
    FOREIGN KEY (id_tablero) REFERENCES Tableros(id_tablero),
    FOREIGN KEY (id_propiedad) REFERENCES Propiedades(id_propiedad),
    FOREIGN KEY (id_tipo) REFERENCES TipoCasillas(id_tipo_casilla)
);

CREATE TABLE TipoCartas (
    id_tipo_carta INT PRIMARY KEY IDENTITY(1,1),
    description VARCHAR(100)
);

CREATE TABLE Cartas (
    id_carta INT PRIMARY KEY IDENTITY(1,1),
    idTipo INT,
    description VARCHAR(255),
    FOREIGN KEY (idTipo) REFERENCES TipoCartas(id_tipo_carta)
);

CREATE TABLE Juegos (
    id_juego INT PRIMARY KEY IDENTITY(1,1),
    id_jugador INT,
    id_tablero INT,
	id_carta INT,
    fecha_inicio DATETIME,
    fecha_actualizada DATETIME,
    FOREIGN KEY (id_jugador) REFERENCES Jugadores(id_jugador),
	FOREIGN KEY (id_carta) REFERENCES Cartas(id_carta),
    FOREIGN KEY (id_tablero) REFERENCES Tableros(id_tablero)
);
