CREATE DATABASE javataskdb;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `user_mobile` varchar(11) DEFAULT NULL,
  `user_email` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_email_UNIQUE` (`user_email`),
  UNIQUE KEY `user_mobile_UNIQUE` (`user_mobile`));
  CREATE TABLE `role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name_UNIQUE` (`role_name`));
  CREATE TABLE `permission` (
  `perm_id` int NOT NULL AUTO_INCREMENT,
  `perm_name` varchar(45) NOT NULL,
  `perm_description` varchar(100) NOT NULL,
  PRIMARY KEY (`perm_id`),
  UNIQUE KEY `perm_name_UNIQUE` (`perm_name`));
  CREATE TABLE `user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id_idx` (`role_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE);
  CREATE TABLE `role_perm` (
  `role_id` int NOT NULL,
  `perm_id` int NOT NULL,
  PRIMARY KEY (`role_id`,`perm_id`),
  KEY `perm_id_idx` (`perm_id`),
  CONSTRAINT `perm_id` FOREIGN KEY (`perm_id`) REFERENCES `permission` (`perm_id`) ON DELETE CASCADE,
  CONSTRAINT `role_id_perm` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE);
