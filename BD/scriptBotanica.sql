-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema botanicadb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema botanicadb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `botanicadb` DEFAULT CHARACTER SET utf8 ;
USE `botanicadb` ;

-- -----------------------------------------------------
-- Table `botanicadb`.`tipo_curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `botanicadb`.`tipo_curso` (
  `pktipo_curso` INT NOT NULL AUTO_INCREMENT,
  `designacao` VARCHAR(200) NULL,
  PRIMARY KEY (`pktipo_curso`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `botanicadb`.`curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `botanicadb`.`curso` (
  `idcurso` INT NOT NULL AUTO_INCREMENT,
  `nome_curso` VARCHAR(150) NULL,
  `data_inicio` DATE NULL,
  `data_fim` DATE NULL,
  `preco` DOUBLE NULL,
  `descricao` VARCHAR(200) NULL,
  `fktipo_curso` INT NOT NULL,
  PRIMARY KEY (`idcurso`),
  INDEX `fk_curso_tipo_curso_idx` (`fktipo_curso` ASC) VISIBLE,
  CONSTRAINT `fk_curso_tipo_curso`
    FOREIGN KEY (`fktipo_curso`)
    REFERENCES `botanicadb`.`tipo_curso` (`pktipo_curso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `botanicadb`.`contacto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `botanicadb`.`contacto` (
  `pk_contacto` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(150) NULL,
  `telefone` VARCHAR(45) NULL,
  `email` VARCHAR(100) NULL,
  `endereco` VARCHAR(200) NULL,
  `mensagem` VARCHAR(300) NULL,
  PRIMARY KEY (`pk_contacto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `botanicadb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `botanicadb`.`usuario` (
  `pkusuario` INT NOT NULL AUTO_INCREMENT,
  `nomeCompleto` VARCHAR(100) NULL,
  `username` VARCHAR(100) NULL,
  `senha` VARCHAR(100) NULL,
  PRIMARY KEY (`pkusuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `botanicadb`.`tipo_planta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `botanicadb`.`tipo_planta` (
  `pktipo_planta` INT NOT NULL AUTO_INCREMENT,
  `designacao` VARCHAR(45) NULL,
  PRIMARY KEY (`pktipo_planta`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `botanicadb`.`Planta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `botanicadb`.`Planta` (
  `pk_Planta` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `fktipo_planta` INT NOT NULL,
  PRIMARY KEY (`pk_Planta`),
  INDEX `fk_Planta_tipo_planta1_idx` (`fktipo_planta` ASC) VISIBLE,
  CONSTRAINT `fk_Planta_tipo_planta1`
    FOREIGN KEY (`fktipo_planta`)
    REFERENCES `botanicadb`.`tipo_planta` (`pktipo_planta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `botanicadb`.`autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `botanicadb`.`autor` (
  `pk_autor` INT NOT NULL AUTO_INCREMENT,
  `nomeCompleto` VARCHAR(200) NULL,
  `data_nascimento` DATETIME NULL,
  `nacionalidade` VARCHAR(45) NULL,
  `grau_formacao` VARCHAR(200) NULL,
  PRIMARY KEY (`pk_autor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `botanicadb`.`obra_literaria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `botanicadb`.`obra_literaria` (
  `pkobra_literaria` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(45) NULL,
  `data_lancamento` VARCHAR(45) NULL,
  `fk_autor` INT NOT NULL,
  PRIMARY KEY (`pkobra_literaria`),
  INDEX `fk_obra_literaria_autor1_idx` (`fk_autor` ASC) VISIBLE,
  CONSTRAINT `fk_obra_literaria_autor1`
    FOREIGN KEY (`fk_autor`)
    REFERENCES `botanicadb`.`autor` (`pk_autor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
