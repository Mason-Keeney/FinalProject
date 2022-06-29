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
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `postal_code`, `active`) VALUES (1, '123 chase lane', NULL, 'Denver', 'Colorado', '80014', 1);
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `postal_code`, `active`) VALUES (2, '7839 Evergreen Ave', NULL, 'Denver', 'Colorado', '80019', 1);
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `postal_code`, `active`) VALUES (3, '527 Depot St', NULL, 'Denver', 'Colorado', '80203', 1);
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `postal_code`, `active`) VALUES (4, '173 Brickyard St', NULL, 'Denver', 'Colorado', '80207', 1);
INSERT INTO `address` (`id`, `street1`, `street2`, `city`, `state`, `postal_code`, `active`) VALUES (5, '672 High Noon Dr', NULL, 'Denver', 'Colorado', '80202', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `tooldragondb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `image_url`, `description`, `address_id`, `created_at`, `updated_at`, `last_login`, `background_image_url`, `enabled`, `role`) VALUES (1, 'Angel', 'Casillas', 'acadmin', '$2a$10$iLZuVQKCjz0QH0xGZFuUeeXuZm7orxQzNlMCKINdR/DvvJIvwX0he', NULL, 'Looking to meet new poeple on projects!', 1, NULL, NULL, NULL, NULL, 1, 'role_admin');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `image_url`, `description`, `address_id`, `created_at`, `updated_at`, `last_login`, `background_image_url`, `enabled`, `role`) VALUES (2, 'Mason', 'Keeney', 'mkadmin', '$2a$10$v48I6trqwt1KPFukmfuBuOkI0bYZZibw8GkFzISGF5vDdnKI8RriW', NULL, 'Love making new projects and love lending a helping hand', 2, NULL, NULL, NULL, NULL, 1, 'role_admin');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `image_url`, `description`, `address_id`, `created_at`, `updated_at`, `last_login`, `background_image_url`, `enabled`, `role`) VALUES (3, 'Devan', 'Mapp', 'dmadmin', '$2a$10$x4GpEEF.G5bc2/vjJRTLIeRyFanS/gB39S.gPZGCBda3u87KyiJga', NULL, 'Here to help anyone with projects or just using some of the many tools i have!', 3, NULL, NULL, NULL, NULL, 1, 'role_admin');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `image_url`, `description`, `address_id`, `created_at`, `updated_at`, `last_login`, `background_image_url`, `enabled`, `role`) VALUES (4, 'User', 'Mann', 'userman', '$2a$10$1GY7ASeEFXni70jhl/xnJuBMfdykhBuNVWLwA/7lE9hWL3mouyG4y', NULL, 'Hello Mann here, Join for free moneys!', 4, NULL, NULL, NULL, NULL, 1, 'user');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `image_url`, `description`, `address_id`, `created_at`, `updated_at`, `last_login`, `background_image_url`, `enabled`, `role`) VALUES (5, 'Noah', 'Johnson', 'noahj', '$2a$10$nrCN0cm5ogd90tEF5nURjOLx8Kwp8BRMCsWmPyKljBD/WJGefxwa.', NULL, 'Many questions and Many answers!', 5, NULL, NULL, NULL, NULL, 1, 'user');

COMMIT;


-- -----------------------------------------------------
-- Data for table `project`
-- -----------------------------------------------------
START TRANSACTION;
USE `tooldragondb`;
INSERT INTO `project` (`id`, `description`, `start_date`, `updated_at`, `estimated_end_date`, `completed`, `cancelled`, `active`, `image_url`, `address_id`, `owner_id`, `created_at`) VALUES (1, 'landscaping my yard', '2022-06-29', NULL, '2022-07-10', NULL, NULL, 1, NULL, 1, 1, '2022-06-25');
INSERT INTO `project` (`id`, `description`, `start_date`, `updated_at`, `estimated_end_date`, `completed`, `cancelled`, `active`, `image_url`, `address_id`, `owner_id`, `created_at`) VALUES (2, 'Cedar Potting Bench', '2022-07-11', NULL, '2022-07-14', NULL, NULL, 1, NULL, 2, 2, '2022-07-7');
INSERT INTO `project` (`id`, `description`, `start_date`, `updated_at`, `estimated_end_date`, `completed`, `cancelled`, `active`, `image_url`, `address_id`, `owner_id`, `created_at`) VALUES (3, 'Install Vinyl Flooring', '2022-07-5', NULL, '2022-07-11', NULL, NULL, 1, NULL, 2, 2, '2022-07-3');
INSERT INTO `project` (`id`, `description`, `start_date`, `updated_at`, `estimated_end_date`, `completed`, `cancelled`, `active`, `image_url`, `address_id`, `owner_id`, `created_at`) VALUES (4, 'Building a shed', '2022-07-2', NULL, '2022-07-12', NULL, NULL, 1, NULL, 3, 3, '2022-07-1');
INSERT INTO `project` (`id`, `description`, `start_date`, `updated_at`, `estimated_end_date`, `completed`, `cancelled`, `active`, `image_url`, `address_id`, `owner_id`, `created_at`) VALUES (5, 'Sealing Drafty Windows and Doors', '2022-07-1', NULL, '2022-07-14', NULL, NULL, 1, NULL, 4, 4, '2022-06-28');
INSERT INTO `project` (`id`, `description`, `start_date`, `updated_at`, `estimated_end_date`, `completed`, `cancelled`, `active`, `image_url`, `address_id`, `owner_id`, `created_at`) VALUES (6, 'Building a Built-in Bookcase', '2022-07-17', NULL, '2022-07-23', NULL, NULL, 1, NULL, 5, 5, '2022-07-15');
INSERT INTO `project` (`id`, `description`, `start_date`, `updated_at`, `estimated_end_date`, `completed`, `cancelled`, `active`, `image_url`, `address_id`, `owner_id`, `created_at`) VALUES (7, 'Prepping a deck for staning', '2022-07-21', NULL, '2022-07-28', NULL, NULL, 1, NULL, 4, 4, '2022-07-17');
INSERT INTO `project` (`id`, `description`, `start_date`, `updated_at`, `estimated_end_date`, `completed`, `cancelled`, `active`, `image_url`, `address_id`, `owner_id`, `created_at`) VALUES (8, 'Building a Raised Garden Bed', '2022-07-24', NULL, '2022-07-28', NULL, NULL, 1, NULL, 5, 5, '2022-07-20');
INSERT INTO `project` (`id`, `description`, `start_date`, `updated_at`, `estimated_end_date`, `completed`, `cancelled`, `active`, `image_url`, `address_id`, `owner_id`, `created_at`) VALUES (9, 'Painting my house', '2022-07-9', NULL, '2022-07-14', NULL, NULL, 1, NULL, 5, 5, '2022-07-3');

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
INSERT INTO `tool` (`id`, `name`, `description`, `availability`, `training_required`, `operators`, `active`, `image_url`, `status_id`, `tool_condition_id`, `owner_id`, `created_at`, `updated_at`, `available`) VALUES (1, 'Hammer', 'Decent shape but works!', 'available', 1, 1, 1, NULL, 2, 1, 1, '2022-06-25', NULL, 3);
INSERT INTO `tool` (`id`, `name`, `description`, `availability`, `training_required`, `operators`, `active`, `image_url`, `status_id`, `tool_condition_id`, `owner_id`, `created_at`, `updated_at`, `available`) VALUES (2, 'Screwdriver', 'Some that i dont use', 'available', 1, 1, 1, NULL, 1, 1, 1, '2022-06-20', NULL, 4);
INSERT INTO `tool` (`id`, `name`, `description`, `availability`, `training_required`, `operators`, `active`, `image_url`, `status_id`, `tool_condition_id`, `owner_id`, `created_at`, `updated_at`, `available`) VALUES (3, 'Saw', 'Works like a charm', 'available', 2, 1, 1, NULL, 2, 2, 2, '2022-06-10', NULL, 2);
INSERT INTO `tool` (`id`, `name`, `description`, `availability`, `training_required`, `operators`, `active`, `image_url`, `status_id`, `tool_condition_id`, `owner_id`, `created_at`, `updated_at`, `available`) VALUES (4, 'Sander', 'Helps get the job done', 'available', 2, 1, 1, NULL, 2, 4, 2, '2022-06-11', NULL, 1);
INSERT INTO `tool` (`id`, `name`, `description`, `availability`, `training_required`, `operators`, `active`, `image_url`, `status_id`, `tool_condition_id`, `owner_id`, `created_at`, `updated_at`, `available`) VALUES (5, 'Paint Sprayer', 'Faster than doing it with a brush', 'available', 1, 1, 1, NULL, 3, 3, 3, '2022-06-03', NULL, 1);
INSERT INTO `tool` (`id`, `name`, `description`, `availability`, `training_required`, `operators`, `active`, `image_url`, `status_id`, `tool_condition_id`, `owner_id`, `created_at`, `updated_at`, `available`) VALUES (6, 'Nailer', 'Who needs a hammer', 'available', 1, 1, 1, NULL, 2, 2, 3, '2022-06-14', NULL, 3);
INSERT INTO `tool` (`id`, `name`, `description`, `availability`, `training_required`, `operators`, `active`, `image_url`, `status_id`, `tool_condition_id`, `owner_id`, `created_at`, `updated_at`, `available`) VALUES (7, 'Air Compressor', 'Have it dont use it much', 'available', 2, 1, 1, NULL, 1, 1, 3, '2022-06-12', NULL, 1);
INSERT INTO `tool` (`id`, `name`, `description`, `availability`, `training_required`, `operators`, `active`, `image_url`, `status_id`, `tool_condition_id`, `owner_id`, `created_at`, `updated_at`, `available`) VALUES (8, 'Lawn Mower', 'Gas powered mower', 'available', 2, 1, 1, NULL, 2, 2, 4, '2022-06-21', NULL, 1);
INSERT INTO `tool` (`id`, `name`, `description`, `availability`, `training_required`, `operators`, `active`, `image_url`, `status_id`, `tool_condition_id`, `owner_id`, `created_at`, `updated_at`, `available`) VALUES (9, 'Chainsaw', 'Electric powerd', 'available', 2, 1, 1, NULL, 2, 2, 5, '2022-06-15', NULL, 2);
INSERT INTO `tool` (`id`, `name`, `description`, `availability`, `training_required`, `operators`, `active`, `image_url`, `status_id`, `tool_condition_id`, `owner_id`, `created_at`, `updated_at`, `available`) VALUES (10, 'Heater', 'it heats and thats about it', 'available', 2, 1, 1, NULL, 3, 3, 5, '2022-06-18', NULL, 2);
INSERT INTO `tool` (`id`, `name`, `description`, `availability`, `training_required`, `operators`, `active`, `image_url`, `status_id`, `tool_condition_id`, `owner_id`, `created_at`, `updated_at`, `available`) VALUES (11, 'Generator', 'Incase people use this for remote sites', 'available', 2, 2, 1, NULL, 1, 3, 3, '2022-06-07', NULL, 1);
INSERT INTO `tool` (`id`, `name`, `description`, `availability`, `training_required`, `operators`, `active`, `image_url`, `status_id`, `tool_condition_id`, `owner_id`, `created_at`, `updated_at`, `available`) VALUES (12, 'Power Drill', 'Who doesnt need one right...right?', 'available', 1, 1, 1, NULL, 1, 4, 1, '2022-06-10', NULL, 4);
INSERT INTO `tool` (`id`, `name`, `description`, `availability`, `training_required`, `operators`, `active`, `image_url`, `status_id`, `tool_condition_id`, `owner_id`, `created_at`, `updated_at`, `available`) VALUES (13, 'Tile Saw', 'Help keep tiles neatly straight', 'available', 2, 1, 1, NULL, 1, 1, 2, '2022-06-13', NULL, 3);
INSERT INTO `tool` (`id`, `name`, `description`, `availability`, `training_required`, `operators`, `active`, `image_url`, `status_id`, `tool_condition_id`, `owner_id`, `created_at`, `updated_at`, `available`) VALUES (14, 'Table Saw', 'Moveable and usefull', 'available', 1, 1, 1, NULL, 1, 1, 5, '2022-06-13', NULL, 1);
INSERT INTO `tool` (`id`, `name`, `description`, `availability`, `training_required`, `operators`, `active`, `image_url`, `status_id`, `tool_condition_id`, `owner_id`, `created_at`, `updated_at`, `available`) VALUES (15, 'Wrench', 'Have plenty pls take', 'available', 1, 1, 1, NULL, 1, 2, 5, '2022-06-13', NULL, 4);
INSERT INTO `tool` (`id`, `name`, `description`, `availability`, `training_required`, `operators`, `active`, `image_url`, `status_id`, `tool_condition_id`, `owner_id`, `created_at`, `updated_at`, `available`) VALUES (16, 'Welder', 'Userfull for smaller projects', 'available', 1, 1, 1, NULL, 1, 2, 2, '2022-06-13', NULL, 2);
INSERT INTO `tool` (`id`, `name`, `description`, `availability`, `training_required`, `operators`, `active`, `image_url`, `status_id`, `tool_condition_id`, `owner_id`, `created_at`, `updated_at`, `available`) VALUES (17, 'Vaccum', 'Small and usefull for smaller areas', 'available', 1, 1, 1, NULL, 2, 1, 2, '2022-06-13', NULL, 1);

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
INSERT INTO `material` (`id`, `name`, `description`, `active`) VALUES (1, 'nails', 'i have plenty', 1);
INSERT INTO `material` (`id`, `name`, `description`, `active`) VALUES (2, 'wood', 'various types and lengths', 1);
INSERT INTO `material` (`id`, `name`, `description`, `active`) VALUES (3, 'stone', 'they would go great for decoration', 1);
INSERT INTO `material` (`id`, `name`, `description`, `active`) VALUES (4, 'concrete mix', 'lots of concrete bags', 1);
INSERT INTO `material` (`id`, `name`, `description`, `active`) VALUES (5, 'paint', 'differnt colors of paint', 1);
INSERT INTO `material` (`id`, `name`, `description`, `active`) VALUES (6, 'metal', 'metal for reinforcement', 1);
INSERT INTO `material` (`id`, `name`, `description`, `active`) VALUES (7, 'plumbing', 'lots of pipes', 1);
INSERT INTO `material` (`id`, `name`, `description`, `active`) VALUES (8, 'tiles', 'some left over tiles', 1);
INSERT INTO `material` (`id`, `name`, `description`, `active`) VALUES (9, 'insulation', 'have insulation thats in good condition', 1);
INSERT INTO `material` (`id`, `name`, `description`, `active`) VALUES (10, 'drywall', 'have plenty of drywalls', 1);
INSERT INTO `material` (`id`, `name`, `description`, `active`) VALUES (11, 'ladders', 'have a few i can lend', 1);
INSERT INTO `material` (`id`, `name`, `description`, `active`) VALUES (12, 'roofing shingles', 'good shingles that can be used', 1);
INSERT INTO `material` (`id`, `name`, `description`, `active`) VALUES (13, 'concrete tools', 'tools to mix concrete', 1);
INSERT INTO `material` (`id`, `name`, `description`, `active`) VALUES (14, 'lifting equipment', 'lifting or moving heavy objects', 1);

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
INSERT INTO `participant` (`project_id`, `user_id`, `participant_comment`, `date_created`, `date_approved`, `rating`, `rating_comment`, `rating_date`) VALUES (2, 2, NULL, '2022-07-7', '2022-07-7', NULL, NULL, NULL);
INSERT INTO `participant` (`project_id`, `user_id`, `participant_comment`, `date_created`, `date_approved`, `rating`, `rating_comment`, `rating_date`) VALUES (3, 2, NULL, '2022-07-3', '2022-07-3', NULL, NULL, NULL);
INSERT INTO `participant` (`project_id`, `user_id`, `participant_comment`, `date_created`, `date_approved`, `rating`, `rating_comment`, `rating_date`) VALUES (4, 3, NULL, '2022-07-1', '2022-07-1', NULL, NULL, NULL);
INSERT INTO `participant` (`project_id`, `user_id`, `participant_comment`, `date_created`, `date_approved`, `rating`, `rating_comment`, `rating_date`) VALUES (5, 4, NULL, '2022-06-28', '2022-06-28', NULL, NULL, NULL);
INSERT INTO `participant` (`project_id`, `user_id`, `participant_comment`, `date_created`, `date_approved`, `rating`, `rating_comment`, `rating_date`) VALUES (6, 5, NULL, '2022-07-15', '2022-07-15', NULL, NULL, NULL);
INSERT INTO `participant` (`project_id`, `user_id`, `participant_comment`, `date_created`, `date_approved`, `rating`, `rating_comment`, `rating_date`) VALUES (7, 4, NULL, '2022-07-17', '2022-07-17', NULL, NULL, NULL);
INSERT INTO `participant` (`project_id`, `user_id`, `participant_comment`, `date_created`, `date_approved`, `rating`, `rating_comment`, `rating_date`) VALUES (8, 5, NULL, '2022-07-20', '2022-07-20', NULL, NULL, NULL);
INSERT INTO `participant` (`project_id`, `user_id`, `participant_comment`, `date_created`, `date_approved`, `rating`, `rating_comment`, `rating_date`) VALUES (9, 5, NULL, '2022-07-03', '2022-07-03', NULL, NULL, NULL);
INSERT INTO `participant` (`project_id`, `user_id`, `participant_comment`, `date_created`, `date_approved`, `rating`, `rating_comment`, `rating_date`) VALUES (8, 1, NULL, '2022-07-20', '2022-07-20', NULL, NULL, NULL);
INSERT INTO `participant` (`project_id`, `user_id`, `participant_comment`, `date_created`, `date_approved`, `rating`, `rating_comment`, `rating_date`) VALUES (6, 2, NULL, '2022-07-15', '2022-07-15', NULL, NULL, NULL);
INSERT INTO `participant` (`project_id`, `user_id`, `participant_comment`, `date_created`, `date_approved`, `rating`, `rating_comment`, `rating_date`) VALUES (7, 3, NULL, '2022-07-17', '2022-07-17', NULL, NULL, NULL);
INSERT INTO `participant` (`project_id`, `user_id`, `participant_comment`, `date_created`, `date_approved`, `rating`, `rating_comment`, `rating_date`) VALUES (8, 3, NULL, '2022-07-20', '2022-07-20', NULL, NULL, NULL);
INSERT INTO `participant` (`project_id`, `user_id`, `participant_comment`, `date_created`, `date_approved`, `rating`, `rating_comment`, `rating_date`) VALUES (2, 4, NULL, '2022-07-7', '2022-07-7', NULL, NULL, NULL);

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

