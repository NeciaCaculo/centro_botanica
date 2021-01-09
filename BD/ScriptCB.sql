-- MySQL Workbench Synchronization
-- Generated: 2021-01-09 15:11
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

ALTER TABLE `botanicadb`.`tipo_curso` 
CHANGE COLUMN `pktipo_curso` `pk_tipo_curso` INT(11) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `botanicadb`.`curso` 
CHANGE COLUMN `fktipo_curso` `fk_tipo_curso` INT(11) NOT NULL ;

ALTER TABLE `botanicadb`.`Planta` 
DROP COLUMN `imagem`,
DROP COLUMN `descricao`,
CHANGE COLUMN `pk_Planta` `pk_planta` INT(11) NOT NULL AUTO_INCREMENT ,
CHANGE COLUMN `nome` `nome` VARCHAR(45) NULL DEFAULT NULL ;

ALTER TABLE `botanicadb`.`autor` 
DROP COLUMN `nome`,
ADD COLUMN `nome` VARCHAR(100) NULL DEFAULT NULL AFTER `pk_autor`,
CHANGE COLUMN `sobrenome` `sobrenome` VARCHAR(100) NULL DEFAULT NULL AFTER `nome`;

ALTER TABLE `botanicadb`.`obra_literaria` 
DROP COLUMN `imagem`,
DROP COLUMN `fk_autor`,
ADD COLUMN `nome_imagem` VARCHAR(150) NULL DEFAULT NULL AFTER `data_lancamento`,
ADD COLUMN `caminho_imagem` VARCHAR(200) NULL DEFAULT NULL AFTER `nome_imagem`,
ADD COLUMN `status` TINYINT(4) NULL DEFAULT 1 AFTER `descricao`,
ADD COLUMN `fk_planta` INT(11) NOT NULL AFTER `status`,
ADD COLUMN `fk_usuario` INT(11) NOT NULL AFTER `fk_planta`,
CHANGE COLUMN `pkobra_literaria` `pk_obra_literaria` INT(11) NOT NULL AUTO_INCREMENT ,
CHANGE COLUMN `data_lancamento` `data_lancamento` DATETIME NULL DEFAULT NULL ,
CHANGE COLUMN `descricao` `descricao` VARCHAR(400) NULL DEFAULT NULL ,
ADD INDEX `fk_obra_literaria_Planta1_idx` (`fk_planta` ASC) VISIBLE,
ADD INDEX `fk_obra_literaria_usuario1_idx` (`fk_usuario` ASC) VISIBLE,
DROP INDEX `fk_obra_literaria_autor1_idx` ;
;

CREATE TABLE IF NOT EXISTS `botanicadb`.`obra_literaria_autor` (
  `pk_obra_literaria_autor` INT(11) NOT NULL AUTO_INCREMENT,
  `fk_obra_literaria` INT(11) NOT NULL,
  `fk_autor` INT(11) NOT NULL,
  PRIMARY KEY (`pk_obra_literaria_autor`),
  INDEX `fk_obra_literaria_autor_obra_literaria1_idx` (`fk_obra_literaria` ASC) VISIBLE,
  INDEX `fk_obra_literaria_autor_autor1_idx` (`fk_autor` ASC) VISIBLE,
  CONSTRAINT `fk_obra_literaria_autor_obra_literaria1`
    FOREIGN KEY (`fk_obra_literaria`)
    REFERENCES `botanicadb`.`obra_literaria` (`pk_obra_literaria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_obra_literaria_autor_autor1`
    FOREIGN KEY (`fk_autor`)
    REFERENCES `botanicadb`.`autor` (`pk_autor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `botanicadb`.`eventos` (
  `pk_eventos` INT(11) NOT NULL,
  `titulo` VARCHAR(45) NULL DEFAULT NULL,
  `data_evento` DATE NULL DEFAULT NULL,
  `descricao` VARCHAR(400) NULL DEFAULT NULL,
  `nome_imagem` VARCHAR(100) NULL DEFAULT NULL,
  `caminho_imagem` VARCHAR(200) NULL DEFAULT NULL,
  `fk_usuario` INT(11) NOT NULL,
  PRIMARY KEY (`pk_eventos`),
  INDEX `fk_eventos_usuario1_idx` (`fk_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_eventos_usuario1`
    FOREIGN KEY (`fk_usuario`)
    REFERENCES `botanicadb`.`usuario` (`pkusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS `botanicadb`.`evento` ;

ALTER TABLE `botanicadb`.`curso` 
ADD CONSTRAINT `fk_curso_tipo_curso`
  FOREIGN KEY (`fk_tipo_curso`)
  REFERENCES `botanicadb`.`tipo_curso` (`pk_tipo_curso`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `botanicadb`.`Planta` 
ADD CONSTRAINT `fk_Planta_tipo_planta1`
  FOREIGN KEY (`fktipo_planta`)
  REFERENCES `botanicadb`.`tipo_planta` (`pktipo_planta`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `botanicadb`.`obra_literaria` 
ADD CONSTRAINT `fk_obra_literaria_Planta1`
  FOREIGN KEY (`fk_planta`)
  REFERENCES `botanicadb`.`planta` (`pk_planta`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_obra_literaria_usuario1`
  FOREIGN KEY (`fk_usuario`)
  REFERENCES `botanicadb`.`usuario` (`pkusuario`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
