DROP SCHEMA IF EXISTS `rentroom`;

CREATE SCHEMA IF NOT EXISTS `rentroom` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `rentroom`;

-- -----------------------------------------------------
-- Table `rentroom`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rentroom`.`user` ;

CREATE  TABLE IF NOT EXISTS `rentroom`.`user` (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	`login` VARCHAR(45) NOT NULL,
	`password` VARCHAR(50) NOT NULL,
	`email` VARCHAR(45) NOT NULL,
	`phone` VARCHAR(45) NOT NULL,
	`city` VARCHAR(30) NOT NULL ,
	PRIMARY KEY (`id`))
COMMENT = 'maintains user details';

-- -----------------------------------------------------
-- Table `rentroom`.`room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rentroom`.`room` ;

CREATE  TABLE IF NOT EXISTS `rentroom`.`room` (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	`tittle` VARCHAR(45) NOT NULL,
	`description` VARCHAR(500) NOT NULL,
	`owner` INT UNSIGNED NOT NULL,
	`address` VARCHAR(100) NOT NULL,
	`city` VARCHAR(30) NOT NULL,
	`prize` DECIMAL(6,2) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `fk_room_2_user`
		FOREIGN KEY (`owner` )
		REFERENCES `rentroom`.`user` (`id` ))
ENGINE = InnoDB
COMMENT = 'maintains room details';

-- -----------------------------------------------------
-- Table `rentroom`.`rented`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rentroom`.`rented` ;

CREATE  TABLE IF NOT EXISTS `rentroom`.`rented` (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	`renter` INT UNSIGNED NOT NULL,
	`room` INT UNSIGNED NOT NULL,
	`stat_date` TIMESTAMP NOT NULL,
	`nigths` INT UNSIGNED NOT NULL,
	`total_prize` DECIMAL(6,2) NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `fk_rented_2_user`
		FOREIGN KEY (`renter` )
		REFERENCES `rentroom`.`user` (`id` ),
	CONSTRAINT `fk_rented_2_room`
		FOREIGN KEY (`room` )
		REFERENCES `rentroom`.`room` (`id` ))
ENGINE = InnoDB
COMMENT = 'rent relations';

-- -----------------------------------------------------
-- Table `rentroom`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rentroom`.`comment` ;

CREATE  TABLE IF NOT EXISTS `rentroom`.`comment` (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	`comment` VARCHAR(500) NOT NULL,
	`author` INT UNSIGNED NOT NULL,
	`room`  INT UNSIGNED NOT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `fk_comment_2_user`
		FOREIGN KEY (`author` )
		REFERENCES `rentroom`.`user` (`id` ),
	CONSTRAINT `fk_comment_2_room`
		FOREIGN KEY (`room` )
		REFERENCES `rentroom`.`room` (`id` ))
ENGINE = InnoDB
COMMENT = 'room comments';