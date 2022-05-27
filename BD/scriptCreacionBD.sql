DROP DATABASE IF EXISTS pokedexdb;
CREATE DATABASE pokedexdb;
USE pokedexdb;

CREATE TABLE pokemon(
	id INTEGER auto_increment,
    vida INTEGER,
    nombre VARCHAR(100),
	rutaImagen VARCHAR(150),
    defensa INTEGER,
    ataque INTEGER,
    velocidad INTEGER,
    PRIMARY KEY (id)
);

CREATE TABLE avatarUsuario(
	id INTEGER auto_increment,
    rutaImagen VARCHAR(150),
    PRIMARY KEY (id)
);


CREATE TABLE usuario(
	id INTEGER auto_increment,
    fechaCreacion DATETIME default current_timestamp,
    numVictorias INTEGER default 0,
    nombreUsuario VARCHAR(10),
    correo VARCHAR(200),
    contrasena VARCHAR(20),
    avatar_ID INTEGER default 1,
    PRIMARY KEY (id),
    FOREIGN KEY (avatar_ID) REFERENCES avatarUsuario(id)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE equipo(
	idUsuario int,
    idPokemon int,
    FOREIGN KEY (idUsuario) REFERENCES usuario(id)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (idPokemon) REFERENCES pokemon(id)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);


CREATE TABLE movimiento(
	id INTEGER auto_increment,
    nombre VARCHAR(100),
    potencia INTEGER,
    probCritico INTEGER,
    PRIMARY KEY (id)
);

CREATE TABLE movimientoPokemon(
	movimiento_ID INTEGER auto_increment,
    pokemon_ID INTEGER,
    PRIMARY KEY (movimiento_ID, pokemon_ID),
    FOREIGN KEY (movimiento_ID) REFERENCES movimiento(id)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (pokemon_ID) REFERENCES pokemon(id)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE combate(
	id INTEGER auto_increment,
    ganador INTEGER,
    jugador1 INTEGER,
    jugador2 INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (ganador) REFERENCES usuario(id)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (jugador1) REFERENCES usuario(id)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (jugador2) REFERENCES usuario(id)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE turnos(
	idCombate int,
    numeroTurno int,
    idUsuario int,
    idMovimiento int,
    idPokemon int,
    FOREIGN KEY(idCombate) REFERENCES combate(id)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY(idUsuario) REFERENCES usuario(id)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY(idMovimiento) REFERENCES movimiento(id)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY(idPokemon) REFERENCES pokemon(id)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);