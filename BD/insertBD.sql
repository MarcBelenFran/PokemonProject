use pokedexdb;

INSERT INTO avatarUsuario (rutaImagen) VALUES ("./Imagenes/EntrenadorBaseChico.png");
INSERT INTO avatarUsuario (rutaImagen) VALUES ("./Imagenes/EntrenadorBaseChica.png");

-- Agua
INSERT INTO pokemon (nombre, vida, ataque, defensa, velocidad, rutaImagen) VALUES ("Floatzel", 280, 193, 103, 211, "./Imagenes/Floatzel.png");
INSERT INTO pokemon (nombre, vida, ataque, defensa, velocidad, rutaImagen) VALUES ("Empoleon", 278, 159, 162, 112, "./Imagenes/Empoleon.png");
INSERT INTO pokemon (nombre, vida, ataque, defensa, velocidad, rutaImagen) VALUES ("Gyarados", 300, 229, 146, 150, "./Imagenes/Gyarados.png");

-- Movimiento / Agua
INSERT INTO movimiento (nombre, potencia, probCritico) VALUES ("Hidrobomba", 120, 80);
INSERT INTO movimiento (nombre, potencia, probCritico) VALUES ("Acua Cola", 90, 90);
INSERT INTO movimiento (nombre, potencia, probCritico) VALUES ("Triturar", 80, 100);
INSERT INTO movimiento (nombre, potencia, probCritico) VALUES ("Pico Taladro", 80, 100);

-- Fuego
INSERT INTO pokemon (nombre, vida, ataque, defensa, velocidad, rutaImagen) VALUES ("Magmortar", 260, 175, 125, 153, "./Imagenes/Magmortar.png");
INSERT INTO pokemon (nombre, vida, ataque, defensa, velocidad, rutaImagen) VALUES ("Arcanine", 290 , 202, 148, 175, "./Imagenes/Arcanine.png");
INSERT INTO pokemon (nombre, vida, ataque, defensa, velocidad, rutaImagen) VALUES ("Entei", 340, 211, 157, 184, "./Imagenes/Entei.png");

-- Movimiento / Fuego
INSERT INTO movimiento (nombre, potencia, probCritico) VALUES ("Pirotecnia", 70, 100);
INSERT INTO movimiento (nombre, potencia, probCritico) VALUES ("Puño Trueno", 75, 120);
INSERT INTO movimiento (nombre, potencia, probCritico) VALUES ("Llamarada", 120, 85);
INSERT INTO movimiento (nombre, potencia, probCritico) VALUES ("Hiperrayo", 150, 90);

-- Planta
INSERT INTO pokemon (nombre, vida, ataque, defensa, velocidad, rutaImagen) VALUES ("Serperior", 260, 139, 175, 207, "./Imagenes/Serperior.png");
INSERT INTO pokemon (nombre, vida, ataque, defensa, velocidad, rutaImagen) VALUES ("Sceptile", 250, 157, 121, 220, "./Imagenes/Sceptile.png");
INSERT INTO pokemon (nombre, vida, ataque, defensa, velocidad, rutaImagen) VALUES ("Simisage", 260, 180, 117, 186, "./Imagenes/Simisage.png");

-- Movimiento / Planta
INSERT INTO movimiento (nombre, potencia, probCritico) VALUES ("Gigadrenado", 75, 100);
INSERT INTO movimiento (nombre, potencia, probCritico) VALUES ("Lluevehojas", 140, 90);
INSERT INTO movimiento (nombre, potencia, probCritico) VALUES ("Portazo", 80, 75);
INSERT INTO movimiento (nombre, potencia, probCritico) VALUES ("Hoja Aguda", 90, 100);

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


delimiter $$
DROP TRIGGER IF EXISTS eliminarTurnos $$
CREATE TRIGGER eliminarTurnos after update on combate
FOR EACH ROW
BEGIN
	DECLARE combate_ID int;
    SET combate_ID = (Select id from combate where ganador = new.ganador);
	DELETE FROM turnos where idCombate = combate_ID;
END $$

delimiter ;


delimiter $$
DROP TRIGGER IF EXISTS revisarGanador $$
CREATE TRIGGER revisarGanador before delete on turnos
FOR EACH ROW
BEGIN
	DECLARE combate_ID int;
    SET combate_ID = (Select idCombate from turnos where idCombate = old.idCombate);
	IF (select ganador from combates where id = combate_ID is null) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No hay ganador del combate';
    END IF;
END $$

delimiter ;