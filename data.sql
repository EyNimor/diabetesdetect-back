CREATE DATABASE IF NOT EXISTS `patientDatabase` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `patientDatabase`;

-- ------------------------------------------------------------------------------------

DROP TABLE IF EXISTS `User`;
CREATE TABLE User (
    `patientId` VARCHAR(30) NOT NULL,
    `lastName` VARCHAR(30) NOT NULL,
    `firstName` VARCHAR(30) NOT NULL,
    `birthDate` TIMESTAMP NOT NULL,
    `sex` VARCHAR(10) NOT NULL,
    `address` VARCHAR(30) NOT NULL,
    `phoneNumber` VARCHAR(10) NOT NULL,

    PRIMARY KEY (`patientId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;