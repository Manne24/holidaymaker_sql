-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.29-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table holidaymaker.accommodations
CREATE TABLE IF NOT EXISTS `accommodations` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '',
  `rating` int(11) DEFAULT NULL,
  `pool` tinyint(1) unsigned DEFAULT NULL,
  `evening_enterainment` tinyint(1) unsigned DEFAULT NULL,
  `child_club` tinyint(1) unsigned DEFAULT NULL,
  `resutrant` tinyint(1) unsigned DEFAULT NULL,
  `adress_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_accommodations_adress` (`adress_id`),
  CONSTRAINT `FK_accommodations_adress` FOREIGN KEY (`adress_id`) REFERENCES `adress` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table holidaymaker.adress
CREATE TABLE IF NOT EXISTS `adress` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `adress` varchar(50) NOT NULL,
  `number` varchar(50) DEFAULT NULL,
  `postal_code` varchar(50) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table holidaymaker.bookings
CREATE TABLE IF NOT EXISTS `bookings` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `date_from` date NOT NULL,
  `date_to` date NOT NULL,
  `customers_id` int(11) unsigned NOT NULL,
  `accommodation_id` int(11) unsigned NOT NULL,
  `room_number_id` int(11) unsigned DEFAULT NULL,
  `halfpension` tinyint(1) unsigned DEFAULT NULL,
  `fullpension` tinyint(1) unsigned DEFAULT NULL,
  `extrabed` tinyint(1) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bookings_customers` (`customers_id`),
  KEY `FK_bookings_accommodations` (`accommodation_id`),
  KEY `FK_bookings_rooms` (`room_number_id`),
  CONSTRAINT `FK_bookings_accommodations` FOREIGN KEY (`accommodation_id`) REFERENCES `accommodations` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_bookings_customers` FOREIGN KEY (`customers_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_bookings_rooms` FOREIGN KEY (`room_number_id`) REFERENCES `rooms` (`room_number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table holidaymaker.customers
CREATE TABLE IF NOT EXISTS `customers` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL DEFAULT '',
  `last_name` varchar(50) NOT NULL DEFAULT '',
  `email` varchar(50) NOT NULL DEFAULT '',
  `phone_nr` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table holidaymaker.rooms
CREATE TABLE IF NOT EXISTS `rooms` (
  `room_number` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `room_type_id` int(11) unsigned NOT NULL,
  `booking_id` int(11) unsigned NOT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`room_number`),
  KEY `FK_rooms_bookings` (`booking_id`),
  KEY `FK_rooms_room_types` (`room_type_id`),
  CONSTRAINT `FK_rooms_bookings` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_rooms_room_types` FOREIGN KEY (`room_type_id`) REFERENCES `room_types` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table holidaymaker.room_types
CREATE TABLE IF NOT EXISTS `room_types` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `size_type` enum('SINGLE','DOUBLE','SUITE') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
