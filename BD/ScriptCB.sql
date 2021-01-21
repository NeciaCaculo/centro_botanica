CREATE DATABASE  IF NOT EXISTS `botanicadb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `botanicadb`;
-- MySQL dump 10.13  Distrib 8.0.17, for macos10.14 (x86_64)
--
-- Host: localhost    Database: botanicadb
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `autor`
--

DROP TABLE IF EXISTS `autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autor` (
  `pk_autor` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `sobrenome` varchar(100) DEFAULT NULL,
  `data_nascimento` datetime DEFAULT NULL,
  `nacionalidade` varchar(45) DEFAULT NULL,
  `grau_formacao` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`pk_autor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autor`
--

LOCK TABLES `autor` WRITE;
/*!40000 ALTER TABLE `autor` DISABLE KEYS */;
/*!40000 ALTER TABLE `autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacto`
--

DROP TABLE IF EXISTS `contacto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contacto` (
  `pk_contacto` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) DEFAULT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `endereco` varchar(200) DEFAULT NULL,
  `mensagem` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`pk_contacto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacto`
--

LOCK TABLES `contacto` WRITE;
/*!40000 ALTER TABLE `contacto` DISABLE KEYS */;
/*!40000 ALTER TABLE `contacto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso` (
  `idcurso` int(11) NOT NULL AUTO_INCREMENT,
  `nome_curso` varchar(150) DEFAULT NULL,
  `data_inicio` date DEFAULT NULL,
  `data_fim` date DEFAULT NULL,
  `preco` double DEFAULT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  `fk_tipo_curso` int(11) NOT NULL,
  `modalidade_pagto` varchar(150) DEFAULT NULL,
  `documentos` varchar(100) DEFAULT NULL,
  `topicos` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idcurso`),
  KEY `fk_curso_tipo_curso_idx` (`fk_tipo_curso`),
  CONSTRAINT `fk_curso_tipo_curso` FOREIGN KEY (`fk_tipo_curso`) REFERENCES `tipo_curso` (`pk_tipo_curso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_conservacao`
--

DROP TABLE IF EXISTS `estado_conservacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado_conservacao` (
  `pk_estado_conservacao` int(11) NOT NULL AUTO_INCREMENT,
  `designacao` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pk_estado_conservacao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_conservacao`
--

LOCK TABLES `estado_conservacao` WRITE;
/*!40000 ALTER TABLE `estado_conservacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `estado_conservacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventos`
--

DROP TABLE IF EXISTS `eventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eventos` (
  `pk_eventos` int(11) NOT NULL,
  `titulo` varchar(45) DEFAULT NULL,
  `data_evento` date DEFAULT NULL,
  `descricao` varchar(400) DEFAULT NULL,
  `nome_imagem` varchar(100) DEFAULT NULL,
  `caminho_imagem` varchar(200) DEFAULT NULL,
  `fk_usuario` int(11) NOT NULL,
  PRIMARY KEY (`pk_eventos`),
  KEY `fk_eventos_usuario1_idx` (`fk_usuario`),
  CONSTRAINT `fk_eventos_usuario1` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`pkusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventos`
--

LOCK TABLES `eventos` WRITE;
/*!40000 ALTER TABLE `eventos` DISABLE KEYS */;
/*!40000 ALTER TABLE `eventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_planta`
--

DROP TABLE IF EXISTS `grupo_planta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo_planta` (
  `pk_grupo_planta` int(11) NOT NULL AUTO_INCREMENT,
  `designacao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pk_grupo_planta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_planta`
--

LOCK TABLES `grupo_planta` WRITE;
/*!40000 ALTER TABLE `grupo_planta` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupo_planta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obra_literaria`
--

DROP TABLE IF EXISTS `obra_literaria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `obra_literaria` (
  `pk_obra_literaria` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) DEFAULT NULL,
  `data_lancamento` datetime DEFAULT NULL,
  `nome_imagem` varchar(150) DEFAULT NULL,
  `caminho_imagem` varchar(200) DEFAULT NULL,
  `descricao` varchar(400) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
  `fk_usuario` int(11) NOT NULL,
  `edicao` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pk_obra_literaria`),
  KEY `fk_obra_literaria_usuario1_idx` (`fk_usuario`),
  CONSTRAINT `fk_obra_literaria_usuario1` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`pkusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obra_literaria`
--

LOCK TABLES `obra_literaria` WRITE;
/*!40000 ALTER TABLE `obra_literaria` DISABLE KEYS */;
/*!40000 ALTER TABLE `obra_literaria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obra_literaria_autor`
--

DROP TABLE IF EXISTS `obra_literaria_autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `obra_literaria_autor` (
  `pk_obra_literaria_autor` int(11) NOT NULL AUTO_INCREMENT,
  `fk_obra_literaria` int(11) NOT NULL,
  `fk_autor` int(11) NOT NULL,
  PRIMARY KEY (`pk_obra_literaria_autor`),
  KEY `fk_obra_literaria_autor_obra_literaria1_idx` (`fk_obra_literaria`),
  KEY `fk_obra_literaria_autor_autor1_idx` (`fk_autor`),
  CONSTRAINT `fk_obra_literaria_autor_autor1` FOREIGN KEY (`fk_autor`) REFERENCES `autor` (`pk_autor`),
  CONSTRAINT `fk_obra_literaria_autor_obra_literaria1` FOREIGN KEY (`fk_obra_literaria`) REFERENCES `obra_literaria` (`pk_obra_literaria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obra_literaria_autor`
--

LOCK TABLES `obra_literaria_autor` WRITE;
/*!40000 ALTER TABLE `obra_literaria_autor` DISABLE KEYS */;
/*!40000 ALTER TABLE `obra_literaria_autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planta`
--

DROP TABLE IF EXISTS `planta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `planta` (
  `pk_planta` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `fk_grupo_planta` int(11) NOT NULL,
  `descricao` varchar(200) DEFAULT NULL,
  `nome_imagem` varchar(150) DEFAULT NULL,
  `caminho_imagem` varchar(200) DEFAULT NULL,
  `localizacao` varchar(150) DEFAULT NULL,
  `utilidade` varchar(150) DEFAULT NULL,
  `fk_estado_conservacao` int(11) NOT NULL,
  PRIMARY KEY (`pk_planta`),
  KEY `fk_Planta_tipo_planta1_idx` (`fk_grupo_planta`),
  KEY `fk_planta_estado_conservacao1_idx` (`fk_estado_conservacao`),
  CONSTRAINT `fk_Planta_tipo_planta1` FOREIGN KEY (`fk_grupo_planta`) REFERENCES `grupo_planta` (`pk_grupo_planta`),
  CONSTRAINT `fk_planta_estado_conservacao1` FOREIGN KEY (`fk_estado_conservacao`) REFERENCES `estado_conservacao` (`pk_estado_conservacao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planta`
--

LOCK TABLES `planta` WRITE;
/*!40000 ALTER TABLE `planta` DISABLE KEYS */;
/*!40000 ALTER TABLE `planta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planta_has_obra_literaria`
--

DROP TABLE IF EXISTS `planta_has_obra_literaria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `planta_has_obra_literaria` (
  `pk_iplanta_has_obra_literaria` varchar(45) NOT NULL,
  `fk_planta` int(11) NOT NULL,
  `fk_obra_literaria` int(11) NOT NULL,
  PRIMARY KEY (`pk_iplanta_has_obra_literaria`),
  KEY `fk_planta_has_obra_literaria_obra_literaria1_idx` (`fk_obra_literaria`),
  KEY `fk_planta_has_obra_literaria_planta1_idx` (`fk_planta`),
  CONSTRAINT `fk_planta_has_obra_literaria_obra_literaria1` FOREIGN KEY (`fk_obra_literaria`) REFERENCES `obra_literaria` (`pk_obra_literaria`),
  CONSTRAINT `fk_planta_has_obra_literaria_planta1` FOREIGN KEY (`fk_planta`) REFERENCES `planta` (`pk_planta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planta_has_obra_literaria`
--

LOCK TABLES `planta_has_obra_literaria` WRITE;
/*!40000 ALTER TABLE `planta_has_obra_literaria` DISABLE KEYS */;
/*!40000 ALTER TABLE `planta_has_obra_literaria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_curso`
--

DROP TABLE IF EXISTS `tipo_curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_curso` (
  `pk_tipo_curso` int(11) NOT NULL AUTO_INCREMENT,
  `designacao` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`pk_tipo_curso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_curso`
--

LOCK TABLES `tipo_curso` WRITE;
/*!40000 ALTER TABLE `tipo_curso` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `pkusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nomeCompleto` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `senha` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pkusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'botanicadb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-15 18:46:56
