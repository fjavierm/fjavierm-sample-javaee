DROP SCHEMA IF EXISTS `quotewall`;

CREATE SCHEMA IF NOT EXISTS `quotewall` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `quotewall`;

-- -----------------------------------------------------
-- Table `quotewall`.`quotes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `quotewall`.`quotes` ;

CREATE  TABLE IF NOT EXISTS `quotewall`.`quotes` (
	`id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	`text` VARCHAR(300) NOT NULL,
	`author` VARCHAR(30) NOT NULL ,
	`created` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`))
COMMENT = 'Stored quotes';