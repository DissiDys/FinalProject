-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`category` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 9
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`activity` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `category_id` INT(11) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
    INDEX `fk_activity_category_idx` (`category_id` ASC) VISIBLE,
    CONSTRAINT `fk_activity_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `mydb`.`category` (`id`)
    ON DELETE CASCADE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 5
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`unconfirmed_activities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`unconfirmed_activities` (
    `activity_id` INT(11) NOT NULL,
    `user_id` INT(11) NOT NULL,
    `operation` ENUM('ADD', 'DELETE') NULL DEFAULT 'ADD',
    PRIMARY KEY (`activity_id`, `user_id`),
    INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `activity_id`
    FOREIGN KEY (`activity_id`)
    REFERENCES `mydb`.`activity` (`id`)
    ON DELETE CASCADE,
    CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`user_has_activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user_has_activity` (
    `user_id` INT(11) NOT NULL,
    `activity_id` INT(11) NOT NULL,
    `time_spent` INT(11) NULL DEFAULT NULL,
    PRIMARY KEY (`user_id`, `activity_id`),
    INDEX `fk_user_has_activity_activity1_idx` (`activity_id` ASC) VISIBLE,
    INDEX `fk_user_has_activity_user1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_user_has_activity_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE CASCADE,
    CONSTRAINT `fk_user_has_activity_activity1`
    FOREIGN KEY (`activity_id`)
    REFERENCES `mydb`.`activity` (`id`)
    ON DELETE CASCADE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
