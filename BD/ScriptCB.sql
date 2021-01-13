-- MySQL Workbench Synchronization
-- Generated: 2021-01-13 16:56
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Necia

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

ALTER TABLE `botanicadb`.`curso` 
DROP FOREIGN KEY `fk_curso_tipo_curso`;

ALTER TABLE `botanicadb`.`planta` 
DROP FOREIGN KEY `fk_Planta_tipo_planta1`;

ALTER TABLE `botanicadb`.`obra_literaria` 
DROP FOREIGN KEY `fk_obra_literaria_Planta1`,
DROP FOREIGN KEY `fk_obra_literaria_usuario1`;

ALTER TABLE `botanicadb`.`obra_literaria_autor` 
DROP FOREIGN KEY `fk_obra_literaria_autor_autor1`;

ALTER TABLE `botanicadb`.`eventos` 
DROP FOREIGN KEY `fk_eventos_usuario1`;

ALTER TABLE `botanicadb`.`curso` 
ADD COLUMN `modalidade_pagto` VARCHAR(150) NULL DEFAULT NULL AFTER `fk_tipo_curso`,
ADD COLUMN `documentos` VARCHAR(100) NULL DEFAULT NULL AFTER `modalidade_pagto`,
ADD COLUMN `topicos` VARCHAR(200) NULL DEFAULT NULL AFTER `documentos`;

ALTER TABLE `botanicadb`.`tipo_planta` 
CHANGE COLUMN `pktipo_planta` `pk_grupo_planta` INT(11) NOT NULL AUTO_INCREMENT , RENAME TO  `botanicadb`.`grupo_planta` ;

ALTER TABLE `botanicadb`.`planta` 
ADD COLUMN `descricao` VARCHAR(200) NULL DEFAULT NULL AFTER `fk_grupo_planta`,
ADD COLUMN `nome_imagem` VARCHAR(150) NULL DEFAULT NULL AFTER `descricao`,
ADD COLUMN `caminho_imagem` VARCHAR(200) NULL DEFAULT NULL AFTER `nome_imagem`,
ADD COLUMN `localizacao` VARCHAR(150) NULL DEFAULT NULL AFTER `caminho_imagem`,
ADD COLUMN `utilidade` VARCHAR(150) NULL DEFAULT NULL AFTER `localizacao`,
ADD COLUMN `fk_estado_conservacao` INT(11) NOT NULL AFTER `utilidade`,
CHANGE COLUMN `fktipo_planta` `fk_grupo_planta` INT(11) NOT NULL ,
ADD INDEX `fk_planta_estado_conservacao1_idx` (`fk_estado_conservacao` ASC) VISIBLE;
;

ALTER TABLE `botanicadb`.`obra_literaria` 
ADD COLUMN `edicao` VARCHAR(100) NULL DEFAULT NULL AFTER `fk_usuario`;

CREATE TABLE IF NOT EXISTS `botanicadb`.`estado_conservacao` (
  `pk_estado_conservacao` INT(11) NOT NULL AUTO_INCREMENT,
  `designacao` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`pk_estado_conservacao`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `botanicadb`.`curso` 
ADD CONSTRAINT `fk_curso_tipo_curso`
  FOREIGN KEY (`fk_tipo_curso`)
  REFERENCES `botanicadb`.`tipo_curso` (`pk_tipo_curso`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `botanicadb`.`planta` 
ADD CONSTRAINT `fk_Planta_tipo_planta1`
  FOREIGN KEY (`fk_grupo_planta`)
  REFERENCES `botanicadb`.`grupo_planta` (`pk_grupo_planta`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_planta_estado_conservacao1`
  FOREIGN KEY (`fk_estado_conservacao`)
  REFERENCES `botanicadb`.`estado_conservacao` (`pk_estado_conservacao`)
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

ALTER TABLE `botanicadb`.`obra_literaria_autor` 
DROP FOREIGN KEY `fk_obra_literaria_autor_obra_literaria1`;

ALTER TABLE `botanicadb`.`obra_literaria_autor` ADD CONSTRAINT `fk_obra_literaria_autor_obra_literaria1`
  FOREIGN KEY (`fk_obra_literaria`)
  REFERENCES `botanicadb`.`obra_literaria` (`pk_obra_literaria`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_obra_literaria_autor_autor1`
  FOREIGN KEY (`fk_autor`)
  REFERENCES `botanicadb`.`autor` (`pk_autor`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `botanicadb`.`eventos` 
ADD CONSTRAINT `fk_eventos_usuario1`
  FOREIGN KEY (`fk_usuario`)
  REFERENCES `botanicadb`.`usuario` (`pkusuario`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
