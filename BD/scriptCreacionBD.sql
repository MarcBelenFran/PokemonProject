DROP DATABASE IF EXISTS PokedexDB;
CREATE DATABASE PokedexDB;
USE PokedexDB;

CREATE TABLE pasiva(
	id INTEGER auto_increment,
    nombre VARCHAR(100),
    PRIMARY KEY (ID)
);

CREATE TABLE tipo(
	id INTEGER auto_increment,
    nombre VARCHAR(100),
    PRIMARY KEY (ID)
);
CREATE TABLE objeto(
	id INTEGER auto_increment,
    rutaImagen VARCHAR(150),
    nombre VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE privilegio(
	id INTEGER auto_increment,
    nombre VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE categoria(
	id INTEGER auto_increment,
    nombre VARCHAR(100),
    PRIMARY KEY (id)
);


CREATE TABLE efecto(
	id INTEGER auto_increment,
    valor INTEGER,
    estadistica VARCHAR(50),
    descripcion VARCHAR(300),
    pasiva_ID INTEGER,
    objeto_ID INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (pasiva_ID) REFERENCES pasiva(id),
    FOREIGN KEY (objeto_ID) REFERENCES objeto(id)
);

CREATE TABLE pokemon(
	id INTEGER auto_increment,
    vida INTEGER,
    nombre VARCHAR(100),
	rutaImagen VARCHAR(150),
    defensaEspecial INTEGER,
    defensa INTEGER,
    ataqueEspecial INTEGER,
    ataque INTEGER,
    velocidad INTEGER,
    pasivaID INTEGER,
    objetoID INTEGER,
    tipoID INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (pasivaID) REFERENCES pasiva(id),
    FOREIGN KEY (objetoID) REFERENCES objeto(id),
    FOREIGN KEY (tipoID) REFERENCES tipo(id)
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
    probFallo INTEGER,
    categoria_ID INTEGER,
    tipo_ID INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (categoria_ID) REFERENCES categoria(id),
    FOREIGN KEY (tipo_ID) REFERENCES tipo(id)
);

CREATE TABLE movimientoPokemon(
	movimiento_ID INTEGER auto_increment,
    pokemon_ID INTEGER,
    PRIMARY KEY (movimiento_ID, pokemon_ID),
    FOREIGN KEY (movimiento_ID) REFERENCES movimiento(id),
    FOREIGN KEY (pokemon_ID) REFERENCES pokemon(id)
);

CREATE TABLE eficaz(
	id INTEGER auto_increment,
    tipo_ID INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (tipo_ID) REFERENCES tipo(id)
);

CREATE TABLE combate(
	id INTEGER auto_increment,
    ganador INTEGER,
    fechaHora DATETIME,
    equipo1_ID INTEGER,
    equipo2_ID INTEGER,
    PRIMARY KEY (id),
    FOREIGN KEY (ganador) REFERENCES usuario(id),
    FOREIGN KEY (equipo1_ID) REFERENCES equipo(id),
    FOREIGN KEY (equipo2_ID) REFERENCES equipo(id)
);


INSERT INTO privilegio (nombre) VALUES ("básico");
INSERT INTO avatarusuario (rutaImagen, privilegio_ID) VALUES ("../Imagenes/EntrenadorBaseChico.png", 1);
INSERT INTO avatarusuario (rutaImagen, privilegio_ID) VALUES ("../Imagenes/EntrenadorBaseChica.png", 1);

delimiter $$
DROP TRIGGER IF EXISTS revisarUsuarios $$
CREATE TRIGGER revisarUsuarios before insert on usuario
FOR EACH ROW
BEGIN
	DECLARE contador int;
    set contador = (select COUNT(nombreUsuario) from usuario where nombreUsuario=new.nombreUsuario);
	IF(contador > 0) then
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nombre de usuario repetido';
	END IF;
END; $$

delimiter ;