SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

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
-- Table `invio`.`login`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`login` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `senha` VARCHAR(45) NOT NULL ,
  `codigoConfirmacao` VARCHAR(100) NULL DEFAULT NULL ,
  `codigoConfirmacaoTemp` VARCHAR(100) NULL DEFAULT NULL ,
  `dtCriacao` DATETIME NULL DEFAULT NULL ,
  `email` VARCHAR(200) NOT NULL ,
  `ativo` BIT NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) )
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
  `numero_end` VARCHAR(50) NULL ,
  `cep` VARCHAR(10) NULL ,
  `bairro` VARCHAR(100) NOT NULL ,
  `cidade` VARCHAR(100) NOT NULL ,
  `estado` VARCHAR(100) NOT NULL ,
  `pais` VARCHAR(100) NOT NULL ,
  `telefone` VARCHAR(20) NULL ,
  `celular` VARCHAR(20) NOT NULL ,
  `email` VARCHAR(150) NOT NULL ,
  `matricula` VARCHAR(20) NOT NULL ,
  `grupo_pesq` VARCHAR(200) NULL ,
  `lattes` VARCHAR(200) NOT NULL ,
  `curso` VARCHAR(200) NULL ,
  `genero` VARCHAR(45) NOT NULL ,
  `area` INT(11) NOT NULL ,
  `instituicao` INT(11) NOT NULL ,
  `login` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_curriculo_area1` (`area` ASC) ,
  INDEX `fk_curriculo_instituicao1` (`instituicao` ASC) ,
  INDEX `fk_curriculo_login1_idx` (`login` ASC) ,
  CONSTRAINT `fk_curriculo_area1`
    FOREIGN KEY (`area` )
    REFERENCES `invio`.`area` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_curriculo_instituicao1`
    FOREIGN KEY (`instituicao` )
    REFERENCES `invio`.`instituicao` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_curriculo_login1`
    FOREIGN KEY (`login` )
    REFERENCES `invio`.`login` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
  `area` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_programa_instituicao1` (`instituicao` ASC) ,
  INDEX `fk_programa_area1` (`area` ASC) ,
  CONSTRAINT `fk_programa_instituicao1`
    FOREIGN KEY (`instituicao` )
    REFERENCES `invio`.`instituicao` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_programa_area1`
    FOREIGN KEY (`area` )
    REFERENCES `invio`.`area` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
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
  `titulo` VARCHAR(200) NOT NULL ,
  `autores` VARCHAR(200) NOT NULL ,
  `ano` CHAR(4) NOT NULL ,
  `revista` VARCHAR(200) NOT NULL ,
  `volume` VARCHAR(45) NULL DEFAULT NULL ,
  `paginainicial` INT NOT NULL ,
  `paginafinal` INT NOT NULL ,
  `arquivo` VARCHAR(300) NULL DEFAULT NULL ,
  `estrato` INT NULL DEFAULT NULL ,
  `avaliacao` VARCHAR(45) NULL DEFAULT NULL ,
  `link` VARCHAR(300) NULL DEFAULT NULL ,
  `doi` VARCHAR(20) NULL DEFAULT NULL ,
  `curriculo` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_producao_curriculo1` (`curriculo` ASC) ,
  CONSTRAINT `fk_producao_curriculo1`
    FOREIGN KEY (`curriculo` )
    REFERENCES `invio`.`curriculo` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `invio`.`livro`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`livro` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `titulo` VARCHAR(150) NULL DEFAULT NULL ,
  `capitulo` VARCHAR(45) NULL DEFAULT NULL ,
  `autor` VARCHAR(150) NULL DEFAULT NULL ,
  `ano` VARCHAR(50) NULL DEFAULT NULL ,
  `estrato` INT NULL DEFAULT NULL ,
  `curriculo` INT(11) NOT NULL ,
  `arquivo` VARCHAR(300) NULL DEFAULT NULL ,
  `avaliacao` VARCHAR(45) NULL DEFAULT NULL ,
  `tipoLivro` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_livro_curriculo1` (`curriculo` ASC) ,
  CONSTRAINT `fk_livro_curriculo1`
    FOREIGN KEY (`curriculo` )
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
  `estrato` VARCHAR(45) NULL DEFAULT NULL ,
  `status` VARCHAR(60) NULL DEFAULT NULL ,
  PRIMARY KEY (`titulo`, `areaAvaliacao`, `issn`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `invio`.`perfil`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`perfil` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `descricao` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


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
-- Table `invio`.`edital`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`edital` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `titulo` VARCHAR(255) NULL DEFAULT NULL ,
  `numero` INT NOT NULL ,
  `ano` INT NOT NULL ,
  `resumo` TEXT NOT NULL ,
  `datainicial` DATETIME NOT NULL ,
  `datafinal` DATETIME NOT NULL ,
  `instituicao` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_edital_instituicao1` (`instituicao` ASC) ,
  CONSTRAINT `fk_edital_instituicao1`
    FOREIGN KEY (`instituicao` )
    REFERENCES `invio`.`instituicao` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `invio`.`plano`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`plano` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `titulo` VARCHAR(255) NOT NULL ,
  `resumo` TEXT NULL DEFAULT NULL ,
  `data` DATE NULL DEFAULT NULL ,
  `edital` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_plano_edital1` (`edital` ASC) ,
  CONSTRAINT `fk_plano_edital1`
    FOREIGN KEY (`edital` )
    REFERENCES `invio`.`edital` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `invio`.`plano_curriculo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`plano_curriculo` (
  `plano_id` INT NOT NULL ,
  `curriculo_id` INT(11) NOT NULL ,
  PRIMARY KEY (`plano_id`, `curriculo_id`) ,
  INDEX `fk_plano_has_curriculo_curriculo1` (`curriculo_id` ASC) ,
  INDEX `fk_plano_has_curriculo_plano1` (`plano_id` ASC) ,
  CONSTRAINT `fk_plano_has_curriculo_plano1`
    FOREIGN KEY (`plano_id` )
    REFERENCES `invio`.`plano` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_plano_has_curriculo_curriculo1`
    FOREIGN KEY (`curriculo_id` )
    REFERENCES `invio`.`curriculo` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `invio`.`orientacao`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `invio`.`orientacao` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `aluno` VARCHAR(100) NOT NULL ,
  `p_inicial` DATE NOT NULL ,
  `p_final` DATE NOT NULL ,
  `tipo_orientacao` INT NOT NULL DEFAULT 2 COMMENT 'IC - Iniciação Científica | ES - Especialização | TC - TCC | ME - Mestrado | DR - Doutorado | BD - Bolsista DTI | TD - Tese de Doutorado | DM - Dissertação de Mestrado' ,
  `tipo_bolsa` VARCHAR(100) NOT NULL ,
  `estrato` INT NULL ,
  `curriculo` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Orientacao_curriculo1` (`curriculo` ASC) ,
  CONSTRAINT `fk_Orientacao_curriculo1`
    FOREIGN KEY (`curriculo` )
    REFERENCES `invio`.`curriculo` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `invio` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
