-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema pokedexdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pokedexdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pokedexdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `pokedexdb` ;

-- -----------------------------------------------------
-- Table `pokedexdb`.`avatarUsuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pokedexdb`.`avatarUsuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rutaImagen` VARCHAR(150) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pokedexdb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pokedexdb`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fechaCreacion` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `numVictorias` INT NULL DEFAULT '0',
  `nombreUsuario` VARCHAR(10) NULL DEFAULT NULL,
  `correo` VARCHAR(200) NULL DEFAULT NULL,
  `contrasena` VARCHAR(20) NULL DEFAULT NULL,
  `avatar_ID` INT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  INDEX `avatar_ID` (`avatar_ID` ASC) VISIBLE,
  CONSTRAINT `usuario_ibfk_1`
    FOREIGN KEY (`avatar_ID`)
    REFERENCES `pokedexdb`.`avatarUsuario` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pokedexdb`.`combate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pokedexdb`.`combate` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ganador` INT NULL DEFAULT NULL,
  `jugador1` INT NULL DEFAULT NULL,
  `jugador2` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `ganador` (`ganador` ASC) VISIBLE,
  INDEX `jugador1` (`jugador1` ASC) VISIBLE,
  INDEX `jugador2` (`jugador2` ASC) VISIBLE,
  CONSTRAINT `combate_ibfk_1`
    FOREIGN KEY (`ganador`)
    REFERENCES `pokedexdb`.`usuario` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `combate_ibfk_2`
    FOREIGN KEY (`jugador1`)
    REFERENCES `pokedexdb`.`usuario` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `combate_ibfk_3`
    FOREIGN KEY (`jugador2`)
    REFERENCES `pokedexdb`.`usuario` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pokedexdb`.`pokemon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pokedexdb`.`pokemon` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `vida` INT NULL DEFAULT NULL,
  `nombre` VARCHAR(100) NULL DEFAULT NULL,
  `rutaImagen` VARCHAR(150) NULL DEFAULT NULL,
  `defensa` INT NULL DEFAULT NULL,
  `ataque` INT NULL DEFAULT NULL,
  `velocidad` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pokedexdb`.`movimiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pokedexdb`.`movimiento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NULL DEFAULT NULL,
  `potencia` INT NULL DEFAULT NULL,
  `probCritico` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pokedexdb`.`equipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pokedexdb`.`equipo` (
  `idUsuario` INT NULL DEFAULT NULL,
  `idPokemon` INT NULL DEFAULT NULL,
  `movimiento1` INT NULL DEFAULT NULL,
  `movimiento2` INT NULL DEFAULT NULL,
  `movimiento3` INT NULL DEFAULT NULL,
  `movimiento4` INT NULL DEFAULT NULL,
  INDEX `idUsuario` (`idUsuario` ASC) VISIBLE,
  INDEX `idPokemon` (`idPokemon` ASC) VISIBLE,
  INDEX `movimiento1` (`movimiento1` ASC) VISIBLE,
  INDEX `movimiento2` (`movimiento2` ASC) VISIBLE,
  INDEX `movimiento3` (`movimiento3` ASC) VISIBLE,
  INDEX `movimiento4` (`movimiento4` ASC) VISIBLE,
  CONSTRAINT `equipo_ibfk_1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `pokedexdb`.`usuario` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `equipo_ibfk_2`
    FOREIGN KEY (`idPokemon`)
    REFERENCES `pokedexdb`.`pokemon` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `equipo_ibfk_3`
    FOREIGN KEY (`movimiento1`)
    REFERENCES `pokedexdb`.`movimiento` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `equipo_ibfk_4`
    FOREIGN KEY (`movimiento2`)
    REFERENCES `pokedexdb`.`movimiento` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `equipo_ibfk_5`
    FOREIGN KEY (`movimiento3`)
    REFERENCES `pokedexdb`.`movimiento` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `equipo_ibfk_6`
    FOREIGN KEY (`movimiento4`)
    REFERENCES `pokedexdb`.`movimiento` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pokedexdb`.`turnos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pokedexdb`.`turnos` (
  `idCombate` INT NULL DEFAULT NULL,
  `numeroTurno` INT NULL DEFAULT NULL,
  `idUsuario` INT NULL DEFAULT NULL,
  `idMovimiento` INT NULL DEFAULT NULL,
  `idPokemon` INT NULL DEFAULT NULL,
  `cambioPokemon` TINYINT(1) NULL DEFAULT NULL,
  INDEX `idCombate` (`idCombate` ASC) VISIBLE,
  INDEX `idUsuario` (`idUsuario` ASC) VISIBLE,
  INDEX `idMovimiento` (`idMovimiento` ASC) VISIBLE,
  INDEX `idPokemon` (`idPokemon` ASC) VISIBLE,
  CONSTRAINT `turnos_ibfk_1`
    FOREIGN KEY (`idCombate`)
    REFERENCES `pokedexdb`.`combate` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `turnos_ibfk_2`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `pokedexdb`.`usuario` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `turnos_ibfk_3`
    FOREIGN KEY (`idMovimiento`)
    REFERENCES `pokedexdb`.`movimiento` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `turnos_ibfk_4`
    FOREIGN KEY (`idPokemon`)
    REFERENCES `pokedexdb`.`pokemon` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

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

USE `pokedexdb`;

DELIMITER $$
USE `pokedexdb`$$
CREATE
DEFINER=`root`@`%`
TRIGGER `pokedexdb`.`revisarUsuarios`
BEFORE INSERT ON `pokedexdb`.`usuario`
FOR EACH ROW
BEGIN
	DECLARE contador int;
    set contador = (select COUNT(nombreUsuario) from usuario where lower(nombreUsuario)=lower(new.nombreUsuario));
	IF(contador > 0) then
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Nombre de usuario repetido';
	END IF;
END$$


DELIMITER ;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
