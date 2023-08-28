-- MySQL Script generated by MySQL Workbench
-- Tue Aug  1 20:10:32 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema controle_portaria
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `controle_portaria` ;

-- -----------------------------------------------------
-- Schema controle_portaria
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `controle_portaria` DEFAULT CHARACTER SET utf8 ;
USE `controle_portaria` ;

-- -----------------------------------------------------
-- Table `controle_portaria`.`tipo_pessoa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `controle_portaria`.`tipo_pessoa` ;

CREATE TABLE IF NOT EXISTS `controle_portaria`.`tipo_pessoa` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `decricao` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `controle_portaria`.`pessoa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `controle_portaria`.`pessoa` ;

CREATE TABLE IF NOT EXISTS `controle_portaria`.`pessoa` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(150) NOT NULL,
  `rg` VARCHAR(20) NULL,
  `cpf` VARCHAR(11) NULL,
  `tipo_pessoa_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_pessoa_tipo_pessoa_idx` (`tipo_pessoa_id` ASC) ,
  CONSTRAINT `fk_pessoa_tipo_pessoa`
    FOREIGN KEY (`tipo_pessoa_id`)
    REFERENCES `controle_portaria`.`tipo_pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `controle_portaria`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `controle_portaria`.`usuario` ;

CREATE TABLE IF NOT EXISTS `controle_portaria`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `pessoa_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_usuario_pessoa1_idx` (`pessoa_id` ASC) ,
  CONSTRAINT `fk_usuario_pessoa1`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `controle_portaria`.`pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `controle_portaria`.`imovel_condominio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `controle_portaria`.`imovel_condominio` ;

CREATE TABLE IF NOT EXISTS `controle_portaria`.`imovel_condominio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(150) NULL,
  `logradouro` VARCHAR(300) NOT NULL,
  `cidade` VARCHAR(150) NULL,
  `uf` VARCHAR(2) NULL,
  `pessoa_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_imovel_condominio_pessoa1_idx` (`pessoa_id` ASC) ,
  CONSTRAINT `fk_imovel_condominio_pessoa1`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `controle_portaria`.`pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `controle_portaria`.`controle_registro`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `controle_portaria`.`controle_registro` ;

CREATE TABLE IF NOT EXISTS `controle_portaria`.`controle_registro` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_hora_reg` DATETIME NULL,
  `pessoa_id` INT NOT NULL,
  `usuario_id` INT NOT NULL,
  `imovel_condominio_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_controle_pessoa1_idx` (`pessoa_id` ASC) ,
  INDEX `fk_controle_registro_usuario1_idx` (`usuario_id` ASC) ,
  INDEX `fk_controle_registro_imovel_condominio1_idx` (`imovel_condominio_id` ASC) ,
  CONSTRAINT `fk_controle_pessoa1`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `controle_portaria`.`pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_controle_registro_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `controle_portaria`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_controle_registro_imovel_condominio1`
    FOREIGN KEY (`imovel_condominio_id`)
    REFERENCES `controle_portaria`.`imovel_condominio` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `controle_portaria`.`veiculo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `controle_portaria`.`veiculo` ;

CREATE TABLE IF NOT EXISTS `controle_portaria`.`veiculo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `marca` VARCHAR(100) NULL,
  `modelo` VARCHAR(150) NULL,
  `cor` VARCHAR(50) NULL,
  `placa` VARCHAR(8) NULL,
  `pessoa_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_veiculo_pessoa1_idx` (`pessoa_id` ASC) ,
  CONSTRAINT `fk_veiculo_pessoa1`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `controle_portaria`.`pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Inserts
-- -----------------------------------------------------
insert into `controle_portaria`.`tipo_pessoa` (`decricao`)
values ('Usuario Sistema');

insert into `controle_portaria`.`pessoa` (`nome`, `tipo_pessoa_id`)
values ('Usuário Teste', 1);

--password inicial eh 123456
insert into `controle_portaria`.`usuario` (`login`, `password`, `pessoa_id`)
values ('userTeste', 'E10ADC3949BA59ABBE56E057F20F883E', 1);
