-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema tooldragondb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `tooldragondb` ;

-- -----------------------------------------------------
-- Schema tooldragondb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tooldragondb` DEFAULT CHARACTER SET utf8 ;
USE `tooldragondb` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street1` VARCHAR(100) NULL,
  `street2` VARCHAR(100) NULL,
  `city` VARCHAR(100) NULL,
  `state` VARCHAR(45) NULL,
  `postal_code` VARCHAR(45) NULL,
  `active` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `active` TINYINT NULL,
  `image_url` VARCHAR(2000) NULL,
  `description` TEXT NULL,
  `address_id` INT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `last_login` DATETIME NULL,
  `background_image_url` VARCHAR(2000) NULL,
  `enabled` TINYINT NOT NULL,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project` ;

CREATE TABLE IF NOT EXISTS `project` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` TEXT NULL,
  `start_date` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `estimated_end_date` DATETIME NULL,
  `completed` TINYINT NULL,
  `cancelled` TINYINT NULL,
  `active` TINYINT(1) NULL,
  `image_url` VARCHAR(2000) NULL,
  `address_id` INT NOT NULL,
  `owner_id` INT NOT NULL,
  `created_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_project_address1_idx` (`address_id` ASC),
  INDEX `fk_project_user1_idx` (`owner_id` ASC),
  CONSTRAINT `fk_project_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_project_user1`
    FOREIGN KEY (`owner_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `status` ;

CREATE TABLE IF NOT EXISTS `status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tool_condition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tool_condition` ;

CREATE TABLE IF NOT EXISTS `tool_condition` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tool`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tool` ;

CREATE TABLE IF NOT EXISTS `tool` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `availability` VARCHAR(45) NULL,
  `training_required` TINYINT NULL,
  `operators` INT NULL,
  `active` TINYINT NULL,
  `image_url` VARCHAR(2000) NULL,
  `status_id` INT NULL,
  `tool_condition_id` INT NULL,
  `owner_id` INT NOT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `available` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tool_status1_idx` (`status_id` ASC),
  INDEX `fk_tool_tool_condition1_idx` (`tool_condition_id` ASC),
  INDEX `fk_tool_user1_idx` (`owner_id` ASC),
  CONSTRAINT `fk_tool_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tool_tool_condition1`
    FOREIGN KEY (`tool_condition_id`)
    REFERENCES `tool_condition` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tool_user1`
    FOREIGN KEY (`owner_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project_comment` ;

CREATE TABLE IF NOT EXISTS `project_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment_body` TEXT NULL,
  `created_date` DATETIME NULL,
  `active` TINYINT NULL,
  `project_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_project_comment_project1_idx` (`project_id` ASC),
  INDEX `fk_project_comment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_project_comment_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_project_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `material`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `material` ;

CREATE TABLE IF NOT EXISTS `material` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `active` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `participant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `participant` ;

CREATE TABLE IF NOT EXISTS `participant` (
  `project_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `participant_comment` TEXT NULL,
  `date_created` DATETIME NULL,
  `date_approved` DATETIME NULL,
  `rating` INT NULL,
  `rating_comment` TEXT NULL,
  `rating_date` DATETIME NULL,
  PRIMARY KEY (`project_id`, `user_id`),
  INDEX `fk_Project_has_User_User1_idx` (`user_id` ASC),
  INDEX `fk_Project_has_User_Project_idx` (`project_id` ASC),
  CONSTRAINT `fk_Project_has_User_Project`
    FOREIGN KEY (`project_id`)
    REFERENCES `project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Project_has_User_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project_tool`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project_tool` ;

CREATE TABLE IF NOT EXISTS `project_tool` (
  `tool_id` INT NOT NULL,
  `project_id` INT NOT NULL,
  `project_comment` TEXT NULL,
  `date_created` DATETIME NULL,
  `date_approved` DATETIME NULL,
  `project_owner_rating` INT NULL,
  `tool_owner_rating` INT NULL,
  `project_owner_rating_date` DATETIME NULL,
  `tool_owner_rating_date` DATETIME NULL,
  `project_owner_rating_comment` TEXT NULL,
  `tool_owner_rating_comment` TEXT NULL,
  PRIMARY KEY (`tool_id`, `project_id`),
  INDEX `fk_Tool_has_Project_Project1_idx` (`project_id` ASC),
  INDEX `fk_Tool_has_Project_Tool1_idx` (`tool_id` ASC),
  CONSTRAINT `fk_Tool_has_Project_Tool1`
    FOREIGN KEY (`tool_id`)
    REFERENCES `tool` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tool_has_Project_Project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `category_tools`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category_tools` ;

CREATE TABLE IF NOT EXISTS `category_tools` (
  `category_id` INT NOT NULL,
  `tool_id` INT NOT NULL,
  PRIMARY KEY (`category_id`, `tool_id`),
  INDEX `fk_Category_has_Tool_Tool1_idx` (`tool_id` ASC),
  INDEX `fk_Category_has_Tool_Category1_idx` (`category_id` ASC),
  CONSTRAINT `fk_Category_has_Tool_Category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Category_has_Tool_Tool1`
    FOREIGN KEY (`tool_id`)
    REFERENCES `tool` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project_category` ;

CREATE TABLE IF NOT EXISTS `project_category` (
  `category_id` INT NOT NULL,
  `project_id` INT NOT NULL,
  PRIMARY KEY (`category_id`, `project_id`),
  INDEX `fk_Category_has_Project_Project1_idx` (`project_id` ASC),
  INDEX `fk_Category_has_Project_Category1_idx` (`category_id` ASC),
  CONSTRAINT `fk_Category_has_Project_Category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Category_has_Project_Project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `category_materials`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category_materials` ;

CREATE TABLE IF NOT EXISTS `category_materials` (
  `category_id` INT NOT NULL,
  `material_id` INT NOT NULL,
  PRIMARY KEY (`category_id`, `material_id`),
  INDEX `fk_Category_has_Material_Material1_idx` (`material_id` ASC),
  INDEX `fk_Category_has_Material_Category1_idx` (`category_id` ASC),
  CONSTRAINT `fk_Category_has_Material_Category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Category_has_Material_Material1`
    FOREIGN KEY (`material_id`)
    REFERENCES `material` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project_material`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project_material` ;

CREATE TABLE IF NOT EXISTS `project_material` (
  `material_id` INT NOT NULL,
  `project_id` INT NOT NULL,
  `id` INT NOT NULL,
  `quantity` VARCHAR(45) NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Material_has_Project_Project1_idx` (`project_id` ASC),
  INDEX `fk_Material_has_Project_Material1_idx` (`material_id` ASC),
  INDEX `fk_project_materials_status1_idx` (`status_id` ASC),
  CONSTRAINT `fk_Material_has_Project_Material1`
    FOREIGN KEY (`material_id`)
    REFERENCES `material` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Material_has_Project_Project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_project_materials_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tool_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tool_comment` ;

CREATE TABLE IF NOT EXISTS `tool_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment_body` TEXT NULL,
  `created_date` DATETIME NULL,
  `active` TINYINT NULL,
  `tool_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tool_comment_tool1_idx` (`tool_id` ASC),
  INDEX `fk_tool_comment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_tool_comment_tool1`
    FOREIGN KEY (`tool_id`)
    REFERENCES `tool` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tool_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vote`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vote` ;

CREATE TABLE IF NOT EXISTS `vote` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project_comment_vote`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `project_comment_vote` ;

CREATE TABLE IF NOT EXISTS `project_comment_vote` (
  `project_comment_id` INT NOT NULL,
  `vote_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `vote_date` DATETIME NULL,
  `reported_for` TEXT NULL,
  PRIMARY KEY (`project_comment_id`, `user_id`),
  INDEX `fk_project_comment_has_vote_comment_vote_comment1_idx` (`vote_id` ASC),
  INDEX `fk_project_comment_has_vote_comment_project_comment1_idx` (`project_comment_id` ASC),
  INDEX `fk_project_comment_vote_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_project_comment_has_vote_comment_project_comment1`
    FOREIGN KEY (`project_comment_id`)
    REFERENCES `project_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_project_comment_has_vote_comment_vote_comment1`
    FOREIGN KEY (`vote_id`)
    REFERENCES `vote` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_project_comment_vote_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tool_comment_vote`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tool_comment_vote` ;

CREATE TABLE IF NOT EXISTS `tool_comment_vote` (
  `vote_id` INT NOT NULL,
  `tool_comment_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `vote_date` DATETIME NULL,
  `reported_for` TEXT NULL,
  PRIMARY KEY (`tool_comment_id`, `user_id`),
  INDEX `fk_vote_has_tool_comment_tool_comment1_idx` (`tool_comment_id` ASC),
  INDEX `fk_vote_has_tool_comment_vote1_idx` (`vote_id` ASC),
  INDEX `fk_tool_comment_vote_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_vote_has_tool_comment_vote1`
    FOREIGN KEY (`vote_id`)
    REFERENCES `vote` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vote_has_tool_comment_tool_comment1`
    FOREIGN KEY (`tool_comment_id`)
    REFERENCES `tool_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tool_comment_vote_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS tooldragonuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'tooldragonuser'@'localhost' IDENTIFIED BY 'tooldragon';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'tooldragonuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `tooldragondb`;
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `postal_code`, `active`) VALUES (1, '123 chase lane', NULL, 'el paso', 'texas', '79928', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `tooldragondb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `active`, `image_url`, `description`, `address_id`, `created_at`, `updated_at`, `last_login`, `background_image_url`, `enabled`, `role`) VALUES (1, 'Angel', 'Casillas', 'acadmin', '$2a$10$iLZuVQKCjz0QH0xGZFuUeeXuZm7orxQzNlMCKINdR/DvvJIvwX0he', 1, NULL, 'desc', NULL, NULL, NULL, NULL, NULL, 1, 'role_admin');

COMMIT;


-- -----------------------------------------------------
-- Data for table `project`
-- -----------------------------------------------------
START TRANSACTION;
USE `tooldragondb`;
INSERT INTO `project` (`id`, `description`, `start_date`, `updated_at`, `estimated_end_date`, `completed`, `cancelled`, `active`, `image_url`, `address_id`, `owner_id`, `created_at`) VALUES (1, 'landscaping my yard', NULL, NULL, NULL, NULL, NULL, 1, NULL, 1, 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `status`
-- -----------------------------------------------------
START TRANSACTION;
USE `tooldragondb`;
INSERT INTO `status` (`id`, `name`) VALUES (1, 'requested');
INSERT INTO `status` (`id`, `name`) VALUES (2, 'available');
INSERT INTO `status` (`id`, `name`) VALUES (3, 'unavailable');

COMMIT;


-- -----------------------------------------------------
-- Data for table `tool_condition`
-- -----------------------------------------------------
START TRANSACTION;
USE `tooldragondb`;
INSERT INTO `tool_condition` (`id`, `name`) VALUES (1, 'new');
INSERT INTO `tool_condition` (`id`, `name`) VALUES (2, 'lightly used');
INSERT INTO `tool_condition` (`id`, `name`) VALUES (3, 'needs repair');
INSERT INTO `tool_condition` (`id`, `name`) VALUES (4, 'broken');

COMMIT;


-- -----------------------------------------------------
-- Data for table `tool`
-- -----------------------------------------------------
START TRANSACTION;
USE `tooldragondb`;
INSERT INTO `tool` (`id`, `name`, `description`, `availability`, `training_required`, `operators`, `active`, `image_url`, `status_id`, `tool_condition_id`, `owner_id`, `created_at`, `updated_at`, `available`) VALUES (1, 'hammer', NULL, NULL, NULL, NULL, 1, NULL, 1, 1, 1, NULL, NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `project_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `tooldragondb`;
INSERT INTO `project_comment` (`id`, `comment_body`, `created_date`, `active`, `project_id`, `user_id`) VALUES (1, 'project comment', '2022-06-22', 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `material`
-- -----------------------------------------------------
START TRANSACTION;
USE `tooldragondb`;
INSERT INTO `material` (`id`, `name`, `description`, `active`) VALUES (1, 'nails', NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `tooldragondb`;
INSERT INTO `category` (`id`, `name`) VALUES (1, 'Wood Working');
INSERT INTO `category` (`id`, `name`) VALUES (2, 'Blacksmithing');
INSERT INTO `category` (`id`, `name`) VALUES (3, 'Wielding');

COMMIT;


-- -----------------------------------------------------
-- Data for table `participant`
-- -----------------------------------------------------
START TRANSACTION;
USE `tooldragondb`;
INSERT INTO `participant` (`project_id`, `user_id`, `participant_comment`, `date_created`, `date_approved`, `rating`, `rating_comment`, `rating_date`) VALUES (1, 1, NULL, '2022-06-22', '2022-06-22', NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `project_tool`
-- -----------------------------------------------------
START TRANSACTION;
USE `tooldragondb`;
INSERT INTO `project_tool` (`tool_id`, `project_id`, `project_comment`, `date_created`, `date_approved`, `project_owner_rating`, `tool_owner_rating`, `project_owner_rating_date`, `tool_owner_rating_date`, `project_owner_rating_comment`, `tool_owner_rating_comment`) VALUES (1, 1, NULL, '2022-06-22', '2022-06-22', NULL, NULL, NULL, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category_tools`
-- -----------------------------------------------------
START TRANSACTION;
USE `tooldragondb`;
INSERT INTO `category_tools` (`category_id`, `tool_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `project_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `tooldragondb`;
INSERT INTO `project_category` (`category_id`, `project_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category_materials`
-- -----------------------------------------------------
START TRANSACTION;
USE `tooldragondb`;
INSERT INTO `category_materials` (`category_id`, `material_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `project_material`
-- -----------------------------------------------------
START TRANSACTION;
USE `tooldragondb`;
INSERT INTO `project_material` (`material_id`, `project_id`, `id`, `quantity`, `status_id`) VALUES (1, 1, 1, '250', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `tool_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `tooldragondb`;
INSERT INTO `tool_comment` (`id`, `comment_body`, `created_date`, `active`, `tool_id`, `user_id`) VALUES (1, 'hello', '2022-06-22', 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `vote`
-- -----------------------------------------------------
START TRANSACTION;
USE `tooldragondb`;
INSERT INTO `vote` (`id`, `name`) VALUES (1, 'upvote');
INSERT INTO `vote` (`id`, `name`) VALUES (2, 'downvote');
INSERT INTO `vote` (`id`, `name`) VALUES (3, 'reported');

COMMIT;


-- -----------------------------------------------------
-- Data for table `project_comment_vote`
-- -----------------------------------------------------
START TRANSACTION;
USE `tooldragondb`;
INSERT INTO `project_comment_vote` (`project_comment_id`, `vote_id`, `user_id`, `vote_date`, `reported_for`) VALUES (1, 1, 1, '2022-06-22', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `tool_comment_vote`
-- -----------------------------------------------------
START TRANSACTION;
USE `tooldragondb`;
INSERT INTO `tool_comment_vote` (`vote_id`, `tool_comment_id`, `user_id`, `vote_date`, `reported_for`) VALUES (1, 1, 1, '2022-06-22', NULL);

COMMIT;

