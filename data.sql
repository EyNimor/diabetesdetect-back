CREATE DATABASE IF NOT EXISTS `patientDatabase` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `patientDatabase`;

-- ------------------------------------------------------------------------------------

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `patient_id` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `lastname` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `firstname` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `birth_date` date NOT NULL,
  `sex` varchar(10) NOT NULL,
  `address` varchar(40) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `phone_number` varchar(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  
  PRIMARY KEY (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;