DROP DATABASE IF EXISTS pokedexdb;
CREATE DATABASE pokedexdb;
USE pokedexdb;

CREATE TABLE privilegio(
	id INTEGER auto_increment,
    nombre VARCHAR(100),
    PRIMARY KEY (id)
);


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
    privilegio_ID INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (privilegio_ID) REFERENCES privilegio(id)
);


CREATE TABLE usuario(
	id INTEGER auto_increment,
    fechaCreacion DATETIME default current_timestamp,
    numVictorias INTEGER default 0,
    nombreUsuario VARCHAR(10),
    correo VARCHAR(200),
    contrasena VARCHAR(20),
    avatar_ID INTEGER default 1,
    privilegio_ID INTEGER default 1,
    PRIMARY KEY (id),
    FOREIGN KEY (avatar_ID) REFERENCES avatarUsuario(id),
    FOREIGN KEY (privilegio_ID) REFERENCES privilegio(id)
);

CREATE TABLE equipo(
	id INTEGER auto_increment,
    nombre VARCHAR(100),
    usuario_ID INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (usuario_ID) REFERENCES usuario(id)
);

CREATE TABLE equipoPokemon(
	pokemon_ID INTEGER,
    equipo_ID INTEGER,
    PRIMARY KEY (pokemon_ID, equipo_ID),
    FOREIGN KEY (pokemon_ID) REFERENCES pokemon(id),
    FOREIGN KEY (equipo_ID) REFERENCES equipo(id)
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
    FOREIGN KEY (movimiento_ID) REFERENCES movimiento(id),
    FOREIGN KEY (pokemon_ID) REFERENCES pokemon(id)
);

CREATE TABLE combate(
	id INTEGER auto_increment,
    ganador INTEGER,
    jugador1 INTEGER,
    jugador2 INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (ganador) REFERENCES usuario(id),
    FOREIGN KEY (jugador1) REFERENCES usuario(id),
    FOREIGN KEY (jugador2) REFERENCES usuario(id)
);

CREATE TABLE turnos(
	idCombate int,
    numeroTurno int,
    idUsuario int,
    idMovimiento int,
    idPokemon int,
    FOREIGN KEY(idCombate) REFERENCES combate(id),
    FOREIGN KEY(idUsuario) REFERENCES usuario(id),
    FOREIGN KEY(idMovimiento) REFERENCES movimiento(id),
    FOREIGN KEY(idPokemon) REFERENCES pokemon(id)
);

INSERT INTO privilegio (nombre) VALUES ("básico");
INSERT INTO avatarUsuario (rutaImagen, privilegio_ID) VALUES ("../Imagenes/EntrenadorBaseChico.png", 1);
INSERT INTO avatarUsuario (rutaImagen, privilegio_ID) VALUES ("../Imagenes/EntrenadorBaseChica.png", 1);

-- Agua
INSERT INTO pokemon (nombre, vida, ataque, defensa, velocidad, rutaImagen) VALUES ("Floatzel", 280, 193, 103, 211, "../Imagenes/Floatzel.png");
INSERT INTO pokemon (nombre, vida, ataque, defensa, velocidad, rutaImagen) VALUES ("Empoleon", 278, 159, 162, 112, "../Imagenes/Empoleon.png");
INSERT INTO pokemon (nombre, vida, ataque, defensa, velocidad, rutaImagen) VALUES ("Gyarados", 300, 229, 146, 150, "../Imagenes/Gyarados.png");

-- Fuego
INSERT INTO pokemon (nombre, vida, ataque, defensa, velocidad, rutaImagen) VALUES ("Magmortar", 260, 175, 125, 153, "../Imagenes/Magmortar.png");
INSERT INTO pokemon (nombre, vida, ataque, defensa, velocidad, rutaImagen) VALUES ("Arcanine", 290 , 202, 148, 175, "../Imagenes/Arcanine.png");
INSERT INTO pokemon (nombre, vida, ataque, defensa, velocidad, rutaImagen) VALUES ("Entei", 340, 211, 157, 184, "../Imagenes/Entei.png");

-- Planta
INSERT INTO pokemon (nombre, vida, ataque, defensa, velocidad, rutaImagen) VALUES ("Serperior", 260, 139, 175, 207, "../Imagenes/Serperior.png");
INSERT INTO pokemon (nombre, vida, ataque, defensa, velocidad, rutaImagen) VALUES ("Sceptile", 250, 157, 121, 220, "../Imagenes/Sceptile.png");
INSERT INTO pokemon (nombre, vida, ataque, defensa, velocidad, rutaImagen) VALUES ("Simisage", 260, 180, 117, 186, "../Imagenes/Simisage.png");

delimiter $$
DROP TRIGGER IF EXISTS revisarUsuarios $$
CREATE TRIGGER revisarUsuarios before insert on usuario
FOR EACH ROW
BEGIN
	DECLARE contador int;
    set contador = (select COUNT(nombreUsuario) from usuario where lower(nombreUsuario)=lower(new.nombreUsuario));
	IF(contador > 0) then
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nombre de usuario repetido';
	END IF;
END; $$

delimiter ;