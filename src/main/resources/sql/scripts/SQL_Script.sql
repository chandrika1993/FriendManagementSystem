
-- -----------------------------------------------------
-- Table `ad_6bc4d13c46fbd3f`.`userprofile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ad_6bc4d13c46fbd3f`.`userprofile` (
  `userProfileId` INT(11) NOT NULL AUTO_INCREMENT,
  `contactNo` VARCHAR(256) NULL DEFAULT NULL,
  `userEmailId` VARCHAR(256) NULL DEFAULT NULL,
  `userName` VARCHAR(256) NULL DEFAULT NULL,
  PRIMARY KEY (`userProfileId`))
ENGINE = InnoDB
AUTO_INCREMENT = 112
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ad_6bc4d13c46fbd3f`.`block`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ad_6bc4d13c46fbd3f`.`block` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `block_status` CHAR(1) NULL DEFAULT NULL,
  `emailId` VARCHAR(256) NOT NULL,
  `userProfileId` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_gq070atf6yam0p89dna243p2y` (`userProfileId` ASC),
  CONSTRAINT `FK_gq070atf6yam0p89dna243p2y`
    FOREIGN KEY (`userProfileId`)
    REFERENCES `ad_6bc4d13c46fbd3f`.`userprofile` (`userProfileId`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ad_6bc4d13c46fbd3f`.`friends`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ad_6bc4d13c46fbd3f`.`friends` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `friendEmailId` VARCHAR(256) NOT NULL,
  `userProfileId` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_fm9np713flxtox10t1lajfgax` (`userProfileId` ASC),
  CONSTRAINT `FK_fm9np713flxtox10t1lajfgax`
    FOREIGN KEY (`userProfileId`)
    REFERENCES `ad_6bc4d13c46fbd3f`.`userprofile` (`userProfileId`))
ENGINE = InnoDB
AUTO_INCREMENT = 192
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ad_6bc4d13c46fbd3f`.`subscription`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ad_6bc4d13c46fbd3f`.`subscription` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `emailId` VARCHAR(256) NOT NULL,
  `subscriptionStatus` CHAR(1) NULL DEFAULT NULL,
  `userProfileId` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_gkfax6xe0avkoe20k0r0to8yy` (`userProfileId` ASC),
  CONSTRAINT `FK_gkfax6xe0avkoe20k0r0to8yy`
    FOREIGN KEY (`userProfileId`)
    REFERENCES `ad_6bc4d13c46fbd3f`.`userprofile` (`userProfileId`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
