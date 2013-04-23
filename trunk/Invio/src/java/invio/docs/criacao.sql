SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `invio` ;
CREATE SCHEMA IF NOT EXISTS `invio` DEFAULT CHARACTER SET utf8 ;
USE `invio` ;

-- -----------------------------------------------------
-- Table `invio`.`perfil`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`perfil` (
  `id` INT NOT NULL ,
  `descricao` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Records of `perfil`
-- -----------------------------------------------------
 BEGIN;
 INSERT INTO `perfil` VALUES ('1', 'ROLE_MASTER');
 COMMIT;


-- -----------------------------------------------------
-- Table `invio`.`perfil_login`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`perfil_login` (
  `perfil` INT NOT NULL ,
  `login` INT(11) NOT NULL ,
  PRIMARY KEY (`perfil`, `login`) ,
  INDEX `fk_perfil_has_login_login1` (`login` ASC) ,
  INDEX `fk_perfil_has_login_perfil1` (`perfil` ASC) ,
  CONSTRAINT `fk_perfil_has_login_perfil1`
    FOREIGN KEY (`perfil` )
    REFERENCES `invio`.`perfil` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_perfil_has_login_login1`
    FOREIGN KEY (`login` )
    REFERENCES `invio`.`login` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Records of `perfil_login`
-- -----------------------------------------------------
 BEGIN;
 INSERT INTO `perfil_login` VALUES ('1', '1');
 COMMIT;
 
 -- -----------------------------------------------------
-- Table `invio`.`login`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`login` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `senha` VARCHAR(45) NOT NULL ,
  `codigoConfirmacao` VARCHAR(100) NULL DEFAULT NULL ,
  `codigoConfirmacaoTemp` VARCHAR(100) NULL DEFAULT NULL ,
  `dtCriacao` DATETIME NULL DEFAULT NULL ,
  `email` VARCHAR(200) NOT NULL ,
  `curriculo_id` INT(11) NOT NULL ,
  `ativo` VARCHAR(10) NULL ,
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
-- Records of `login`
-- -----------------------------------------------------
 BEGIN;
 INSERT INTO `login` VALUES ('1', 'pibic2012', 'EJR8T31W', 'EJR8T31W', '2013-04-08 00:00:00', 'sistema.invio@gmail.com', '1', 'true');
 COMMIT;

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
-- Records of `curriculo`
-- -----------------------------------------------------
 BEGIN;
 INSERT INTO `curriculo` VALUES ('1', '00000000000', 'Invio', '2013-04-08', 'Av. Presidente Tancredo Neves', '', '', 'terra-firme', 'Belem', 'Para', 'Brasil', '', '', 'sistema.invio@gmail.com', '000000', 'nenhum', '', 'male');
 COMMIT;

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
-- Table `invio`.`programa_area`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`programa_area` (
  `programa` INT(11) NOT NULL ,
  `area` INT(11) NOT NULL ,
  PRIMARY KEY (`area`, `programa`) ,
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


-- -----------------------------------------------------
-- Table `invio`.`periodico`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`periodico` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `titulo` VARCHAR(100) NULL ,
  `autor` VARCHAR(150) NULL ,
  `ano` VARCHAR(50) NULL ,
  `revista` VARCHAR(150) NULL ,
  `volume` VARCHAR(45) NULL ,
  `pagina` VARCHAR(45) NULL ,
  `curriculo_id` INT(11) NOT NULL ,
  `arquivo` VARCHAR(300) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_producao_curriculo1` (`curriculo_id` ASC) ,
  CONSTRAINT `fk_producao_curriculo1`
    FOREIGN KEY (`curriculo_id` )
    REFERENCES `invio`.`curriculo` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `invio`.`livro`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`livro` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `titulo` VARCHAR(150) NULL ,
  `capitulo` VARCHAR(45) NULL ,
  `autor` VARCHAR(150) NULL ,
  `ano` VARCHAR(50) NULL ,
  `curriculo_id` INT(11) NOT NULL ,
  `arquivo` VARCHAR(300) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_livro_curriculo1` (`curriculo_id` ASC) ,
  CONSTRAINT `fk_livro_curriculo1`
    FOREIGN KEY (`curriculo_id` )
    REFERENCES `invio`.`curriculo` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `invio`.`qualis`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`qualis` (
  `titulo` VARCHAR(255) NOT NULL ,
  `areaAvaliacao` VARCHAR(255) NOT NULL ,
  `issn` VARCHAR(45) NOT NULL ,
  `estrato` VARCHAR(45) NULL ,
  `status` VARCHAR(60) NULL ,
  PRIMARY KEY (`titulo`, `areaAvaliacao`, `issn`) )
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
