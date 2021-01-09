-- MySQL Workbench Synchronization
-- Generated: 2021-01-06 21:03
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Necia

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

ALTER TABLE `botanicadb`.`curso` 
DROP FOREIGN KEY `fk_curso_tipo_curso`;

ALTER TABLE `botanicadb`.`Planta` 
DROP FOREIGN KEY `fk_Planta_tipo_planta1`;

ALTER TABLE `botanicadb`.`obra_literaria` 
DROP FOREIGN KEY `fk_obra_literaria_autor1`;

ALTER TABLE `botanicadb`.`Planta` 
ADD COLUMN `descricao` VARCHAR(200) NULL DEFAULT NULL AFTER `fktipo_planta`,
ADD COLUMN `imagem` BLOB NULL DEFAULT NULL AFTER `descricao`,
CHANGE COLUMN `nome` `nome` VARCHAR(100) NULL DEFAULT NULL ;

ALTER TABLE `botanicadb`.`autor` 
ADD COLUMN `sobrenome` VARCHAR(45) NULL DEFAULT NULL AFTER `grau_formacao`,
CHANGE COLUMN `nomeCompleto` `nome` VARCHAR(100) NULL DEFAULT NULL ;

ALTER TABLE `botanicadb`.`obra_literaria` 
ADD COLUMN `descricao` VARCHAR(200) NULL DEFAULT NULL AFTER `fk_autor`,
ADD COLUMN `imagem` BLOB NULL DEFAULT NULL AFTER `descricao`;

CREATE TABLE IF NOT EXISTS `botanicadb`.`evento` (
  `pkevento` INT(11) NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(100) NULL DEFAULT NULL,
  `data_evento` DATE NULL DEFAULT NULL,
  `descricao` VARCHAR(200) NULL DEFAULT NULL,
  `imagem` BLOB NULL DEFAULT NULL,
  PRIMARY KEY (`pkevento`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `botanicadb`.`curso` 
ADD CONSTRAINT `fk_curso_tipo_curso`
  FOREIGN KEY (`fktipo_curso`)
  REFERENCES `botanicadb`.`tipo_curso` (`pktipo_curso`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `botanicadb`.`Planta` 
ADD CONSTRAINT `fk_Planta_tipo_planta1`
  FOREIGN KEY (`fktipo_planta`)
  REFERENCES `botanicadb`.`tipo_planta` (`pktipo_planta`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `botanicadb`.`obra_literaria` 
ADD CONSTRAINT `fk_obra_literaria_autor1`
  FOREIGN KEY (`fk_autor`)
  REFERENCES `botanicadb`.`autor` (`pk_autor`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
