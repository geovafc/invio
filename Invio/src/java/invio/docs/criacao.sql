SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `invio` ;
CREATE SCHEMA IF NOT EXISTS `invio` DEFAULT CHARACTER SET utf8 ;
USE `invio` ;

-- -----------------------------------------------------
-- Table `invio`.`area`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`area` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(200) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invio`.`curriculo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`curriculo` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `cpf` VARCHAR(50) NOT NULL ,
  `nome` VARCHAR(100) NOT NULL ,
  `dtNascimento` DATE NULL ,
  `logradouro` VARCHAR(100) NOT NULL ,
  `numero_end` VARCHAR(50) NULL DEFAULT NULL ,
  `cep` VARCHAR(10) NULL DEFAULT NULL ,
  `bairro` VARCHAR(100) NOT NULL ,
  `cidade` VARCHAR(100) NOT NULL ,
  `estado` VARCHAR(100) NOT NULL ,
  `pais` VARCHAR(100) NOT NULL ,
  `telefone` VARCHAR(20) NULL DEFAULT NULL ,
  `celular` VARCHAR(20) NULL DEFAULT NULL ,
  `email` VARCHAR(150) NOT NULL ,
  `matricula` VARCHAR(20) NOT NULL ,
  `lattes` VARCHAR(200) NOT NULL ,
  `curso` VARCHAR(200) NULL DEFAULT NULL ,
  `genero` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invio`.`instituicao`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`instituicao` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(200) NOT NULL ,
  `sigla` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invio`.`programa`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`programa` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(500) NOT NULL ,
  `instituicao` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_programa_instituicao1` (`instituicao` ASC) ,
  CONSTRAINT `fk_programa_instituicao1`
    FOREIGN KEY (`instituicao` )
    REFERENCES `invio`.`instituicao` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invio`.`curriculo_programa`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`curriculo_programa` (
  `curriculo` INT(11) NOT NULL ,
  `programa` INT(11) NOT NULL ,
  PRIMARY KEY (`curriculo`, `programa`) ,
  INDEX `fk_curriculo_has_programa_programa1` (`programa` ASC) ,
  INDEX `fk_curriculo_has_programa_curriculo1` (`curriculo` ASC) ,
  CONSTRAINT `fk_curriculo_has_programa_curriculo1`
    FOREIGN KEY (`curriculo` )
    REFERENCES `invio`.`curriculo` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_curriculo_has_programa_programa1`
    FOREIGN KEY (`programa` )
    REFERENCES `invio`.`programa` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invio`.`login`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`login` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `senha` VARCHAR(45) NOT NULL ,
  `codigoConfirmacao` VARCHAR(100) NULL DEFAULT NULL ,
  `codigoConfimacaoTemp` VARCHAR(100) NULL DEFAULT NULL ,
  `dtCriacao` DATETIME NULL DEFAULT NULL ,
  `email` VARCHAR(200) NOT NULL ,
  `curriculo_id` INT(11) NOT NULL ,
  `perfil` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_login_curriculo1_idx` (`curriculo_id` ASC) ,
  CONSTRAINT `fk_login_curriculo1`
    FOREIGN KEY (`curriculo_id` )
    REFERENCES `invio`.`curriculo` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invio`.`programa_area`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`programa_area` (
  `programa` INT(11) NOT NULL ,
  `area` INT(11) NOT NULL ,
  PRIMARY KEY (`programa`, `area`) ,
  INDEX `fk_Programa_has_area_area1` (`area` ASC) ,
  INDEX `fk_Programa_has_area_Programa1` (`programa` ASC) ,
  CONSTRAINT `fk_Programa_has_area_Programa1`
    FOREIGN KEY (`programa` )
    REFERENCES `invio`.`programa` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Programa_has_area_area1`
    FOREIGN KEY (`area` )
    REFERENCES `invio`.`area` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `invio`.`unidade`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`unidade` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(150) NULL DEFAULT NULL ,
  `instituicao` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_unidade_instituicao1` (`instituicao` ASC) ,
  CONSTRAINT `fk_unidade_instituicao1`
    FOREIGN KEY (`instituicao` )
    REFERENCES `invio`.`instituicao` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
