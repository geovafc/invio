SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- -----------------------------------------------------
-- Table `invio`.`curriculo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `invio`.`curriculo` ;

CREATE  TABLE IF NOT EXISTS `invio`.`curriculo` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `cpf` INT NOT NULL ,
  `nome` VARCHAR(100) NOT NULL ,
  `logradouro` VARCHAR(100) NOT NULL ,
  `numero_end` VARCHAR(50) NULL ,
  `cep` INT NULL ,
  `bairro` VARCHAR(100) NOT NULL ,
  `cidade` VARCHAR(100) NOT NULL ,
  `estado` VARCHAR(100) NOT NULL ,
  `pais` VARCHAR(100) NOT NULL ,
  `telefone` INT NULL ,
  `celular` INT NULL ,
  `email` VARCHAR(150) NOT NULL ,
  `matricula` VARCHAR(20) NOT NULL ,
  `lattes` VARCHAR(200) NOT NULL ,
  `curso` VARCHAR(200) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `invio`.`instituicao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `invio`.`instituicao` ;

CREATE  TABLE IF NOT EXISTS `invio`.`instituicao` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(200) NOT NULL ,
  `sigla` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `invio`.`programa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `invio`.`programa` ;

CREATE  TABLE IF NOT EXISTS `invio`.`programa` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(500) NOT NULL ,
  `instituicao` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_programa_instituicao1` (`instituicao` ASC) ,
  CONSTRAINT `fk_programa_instituicao1`
    FOREIGN KEY (`instituicao` )
    REFERENCES `invio`.`instituicao` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `invio`.`area`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `invio`.`area` ;

CREATE  TABLE IF NOT EXISTS `invio`.`area` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(200) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `invio`.`unidade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `invio`.`unidade` ;

CREATE  TABLE IF NOT EXISTS `invio`.`unidade` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(150) NULL ,
  `instituicao` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_unidade_instituicao1` (`instituicao` ASC) ,
  CONSTRAINT `fk_unidade_instituicao1`
    FOREIGN KEY (`instituicao` )
    REFERENCES `invio`.`instituicao` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `invio`.`programa_area`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `invio`.`programa_area` ;

CREATE  TABLE IF NOT EXISTS `invio`.`programa_area` (
  `programa` INT NOT NULL ,
  `area` INT NOT NULL ,
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
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `invio`.`curriculo_programa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `invio`.`curriculo_programa` ;

CREATE  TABLE IF NOT EXISTS `invio`.`curriculo_programa` (
  `curriculo` INT NOT NULL ,
  `programa` INT NOT NULL ,
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
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
