-- phpMyAdmin SQL Dump
-- version 4.0.8
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 27, 2014 at 09:05 PM
-- Server version: 5.5.34-cll-lve
-- PHP Version: 5.3.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `crs_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `EquipmentUsed`
--

CREATE TABLE IF NOT EXISTS `EquipmentUsed` (
  `rentID` int(11) NOT NULL,
  `equipmentID` varchar(30) NOT NULL,
  PRIMARY KEY (`rentID`,`equipmentID`),
  KEY `EquipmentUsed_ibfk_1` (`equipmentID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `EquipmentUsed`
--

INSERT INTO `EquipmentUsed` (`rentID`, `equipmentID`) VALUES
(9, '1'),
(10, '1'),
(17, '1'),
(19, '1'),
(20, '1'),
(21, '1'),
(23, '1'),
(24, '1'),
(25, '1'),
(26, '1'),
(29, '1'),
(31, '1'),
(17, '2'),
(19, '2'),
(20, '2'),
(21, '2'),
(23, '2'),
(25, '2'),
(26, '2'),
(27, '2'),
(29, '2'),
(31, '2'),
(28, '3'),
(28, '4'),
(32, '4');

-- --------------------------------------------------------

--
-- Table structure for table `RentPayment`
--

CREATE TABLE IF NOT EXISTS `RentPayment` (
  `custID` int(11) NOT NULL,
  `vehicleID` varchar(17) NOT NULL,
  `dateTo` date NOT NULL,
  `dateFrom` date NOT NULL,
  `returnDate` date NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `rentID` int(11) NOT NULL,
  PRIMARY KEY (`custID`,`vehicleID`,`dateTo`,`dateFrom`),
  KEY `vehicleID` (`vehicleID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `RentPayment`
--

INSERT INTO `RentPayment` (`custID`, `vehicleID`, `dateTo`, `dateFrom`, `returnDate`, `amount`, `rentID`) VALUES
(2, 'KL5JJ56Z46K320185', '2014-02-12', '2014-02-02', '2014-04-25', '9044.56', 11),
(2, 'SAJDA42C332092132', '2014-04-10', '2014-04-01', '2014-04-25', '2816.80', 1),
(3, '1FTZR44U88P493587', '2014-04-09', '2014-04-17', '2014-04-27', '2000.00', 101);

-- --------------------------------------------------------

--
-- Table structure for table `Rents`
--

CREATE TABLE IF NOT EXISTS `Rents` (
  `rentID` int(11) NOT NULL AUTO_INCREMENT,
  `vehicleID` varchar(17) NOT NULL,
  `custID` int(11) NOT NULL,
  `rentStart` datetime NOT NULL,
  `rentEnd` datetime NOT NULL,
  `isBooked` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`rentID`),
  KEY `vehicleID` (`vehicleID`),
  KEY `custID` (`custID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=36 ;

--
-- Dumping data for table `Rents`
--

INSERT INTO `Rents` (`rentID`, `vehicleID`, `custID`, `rentStart`, `rentEnd`, `isBooked`) VALUES
(1, 'SAJDA42C332092132', 2, '2014-04-01 10:30:00', '2014-04-10 11:00:00', 0),
(2, '1GCEK19T42E330958', 3, '2014-04-09 14:12:00', '2014-04-14 11:00:00', 0),
(4, '3VWSB69M51M801157', 1, '2014-04-10 11:12:00', '2014-04-17 11:00:00', 0),
(5, 'WP1AB2A21BL031829', 2, '2014-04-10 13:00:00', '2014-04-12 11:00:00', 0),
(6, 'SAJDA42C332092132', 4, '2014-04-14 12:13:23', '2014-04-15 11:00:00', 0),
(7, 'SAJDA42C332092132', 3, '2014-04-15 15:13:36', '2014-04-17 11:00:00', 0),
(8, 'KNDJD733135961687', 8, '2014-04-21 10:15:38', '2014-04-30 00:00:00', 0),
(9, 'WAUKFBFM6AA968187', 5, '2014-04-28 00:00:00', '2014-04-30 00:00:00', 0),
(10, 'WAUKFBFM6AA968187', 5, '2014-04-22 00:00:00', '2014-04-26 00:00:00', 1),
(13, '1GKUKEEF6AR164764', 11, '2014-01-01 04:00:00', '2014-02-02 11:00:00', 0),
(17, '1GKUKEEF6AR164764', 11, '2014-04-10 06:00:00', '2014-04-18 14:00:00', 0),
(19, '1GCEK19T42E330958', 1, '2014-05-01 07:00:00', '2014-07-14 14:00:00', 0),
(20, '1GKUKEEF6AR164764', 11, '2014-03-02 05:00:00', '2014-03-27 19:00:00', 0),
(21, '1FTWW31507E361601', 6, '2014-04-04 06:00:00', '2014-04-16 12:00:00', 0),
(23, '3FTSF30S3YM077137', 11, '2014-04-14 11:00:00', '2014-04-23 16:00:00', 0),
(24, '3FTSF30S3YM077137', 6, '2014-04-26 05:00:00', '2014-05-10 09:00:00', 0),
(25, '1D8HN44E19B705619', 6, '2014-04-26 03:00:00', '2014-04-30 17:00:00', 0),
(26, '1GKUKEEF6AR164764', 11, '2014-04-30 07:00:00', '2014-05-03 15:00:00', 0),
(27, '1FTZR44U88P493587', 1, '2014-04-26 08:00:00', '2014-04-30 10:00:00', 0),
(28, 'KNDJD733135961687', 8, '2014-05-22 12:00:00', '2014-05-24 16:00:00', 1),
(29, '1FTWW31507E361601', 1, '2014-04-26 08:00:00', '2014-04-26 19:00:00', 0),
(30, 'WAUKFBFM6AA968187', 8, '2014-04-22 00:00:00', '2014-04-26 00:00:00', 0),
(31, '1GCEK19T42E330958', 6, '2014-04-26 10:00:00', '2014-04-26 23:00:00', 0),
(32, 'WP1AB2A21BL031829', 1, '2014-04-26 15:00:00', '2014-04-26 20:00:00', 0),
(33, '1FTWW31507E361601', 6, '2014-04-29 07:00:00', '2014-04-30 21:00:00', 0),
(34, 'SCA1S685X8U747697', 18, '2014-04-26 02:00:00', '2014-05-01 15:00:00', 0),
(35, '2CNFLNEW9A6936151', 1, '2014-04-27 07:00:00', '2014-04-30 10:00:00', 0);

-- --------------------------------------------------------

--
-- Table structure for table `Sells`
--

CREATE TABLE IF NOT EXISTS `Sells` (
  `empID` varchar(30) NOT NULL,
  `vehicleID` varchar(17) NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `sell_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`empID`,`vehicleID`),
  KEY `Sells_ibfk_1` (`vehicleID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Vehicle_Category`
--

CREATE TABLE IF NOT EXISTS `Vehicle_Category` (
  `vehicleID` varchar(17) NOT NULL,
  `rentCategory` set('Economy','Compact','Mid-size','Standard','Full-size','Premium','Luxury','SUV','Van','24-Foot','15-Foot','12-Foot','Box Truck','Cargo Van') NOT NULL,
  PRIMARY KEY (`vehicleID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Vehicle_Category`
--

INSERT INTO `Vehicle_Category` (`vehicleID`, `rentCategory`) VALUES
('1D8HN44E19B705619', 'Economy'),
('1FTWW31507E361601', 'Premium'),
('1FTZR44U88P493587', 'Premium'),
('1GCEK19T42E330958', 'Full-size'),
('1GKUKEEF6AR164764', 'Luxury'),
('2CNFLNEW9A6936151', 'Full-size'),
('3FTNX21L01M964390', '15-Foot'),
('3FTSF30S3YM077137', 'Premium'),
('3VWSB69M51M801157', 'Luxury'),
('4S4BRCGC5A1071279', 'Economy'),
('KL5JJ56Z46K320185', 'Compact'),
('KNDJD733135961687', '15-Foot'),
('SAJDA42C332092132', 'Economy'),
('SCA1S685X8U747697', '15-Foot'),
('WAUKFBFM6AA968187', 'Compact'),
('WP1AB2A21BL031829', '15-Foot');

-- --------------------------------------------------------

--
-- Table structure for table `Vehicle_Rent`
--

CREATE TABLE IF NOT EXISTS `Vehicle_Rent` (
  `vehicleID` varchar(17) NOT NULL,
  `model` varchar(10) DEFAULT NULL,
  `make` varchar(10) DEFAULT NULL,
  `vehicle_year` year(4) DEFAULT NULL,
  `vehicle_type` set('Car','Truck') NOT NULL,
  `colour` varchar(10) DEFAULT NULL,
  `license_plate` varchar(10) DEFAULT NULL,
  `odometer` int(11) NOT NULL DEFAULT '0',
  `full_tank` set('empty','full') NOT NULL DEFAULT 'full',
  PRIMARY KEY (`vehicleID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Vehicle_Rent`
--

INSERT INTO `Vehicle_Rent` (`vehicleID`, `model`, `make`, `vehicle_year`, `vehicle_type`, `colour`, `license_plate`, `odometer`, `full_tank`) VALUES
('1D8HN44E19B705619', 'i30', 'Hyundai', 2013, 'Car', 'Yellow', 'IND050', 0, 'full'),
('1FTWW31507E361601', 'Cooper', 'Mini', 2013, 'Car', 'Black', '231PSA', 0, 'full'),
('1FTZR44U88P493587', '350z', 'Nissan', 2010, 'Car', 'White', '094CAN', 2500, 'full'),
('1GCEK19T42E330958', 'Passat', 'Volkswagen', 2012, 'Car', 'Black', '453EEN', 64211, 'full'),
('1GKUKEEF6AR164764', 'F450', 'Ferrari', 2011, 'Car', 'Red', '222JDA', 0, 'full'),
('2CNFLNEW9A6936151', 'Nova', 'Chevrolet', 1977, 'Car', 'Red', '321CBA', 0, 'full'),
('3FTNX21L01M964390', 'F150', 'Ford', 2010, 'Truck', 'Blue', '000FFF', 0, 'full'),
('3FTSF30S3YM077137', '315i', 'BMW', 2012, 'Car', 'White', '881QAZ', 13464, 'full'),
('3VWSB69M51M801157', 'GTR R35', 'Nissan', 2012, 'Car', 'Black', '432FAT', 0, 'full'),
('4S4BRCGC5A1071279', 'i20', 'Hyundai', 2013, 'Car', 'Red', '555HHH', 0, 'full'),
('KL5JJ56Z46K320185', 'i20', 'Honda', 2010, 'Car', 'Blue', '007IND', 0, 'full'),
('KNDJD733135961687', 'Silverado', 'Chevrolet', 2012, 'Truck', 'Navy Blue', '522TWE', 23123, 'full'),
('SAJDA42C332092132', 'Corolla', 'Toyota', 2012, 'Car', 'Silver', '823DDA', 0, 'full'),
('SCA1S685X8U747697', 'Ridgeline', 'Honda', 2013, 'Truck', 'Navy Blue', '910FAX', 85420, 'full'),
('WAUKFBFM6AA968187', 'Fiesta', 'Ford', 2011, 'Car', 'Grey', '838ASD', 0, 'full'),
('WP1AB2A21BL031829', 'Tundra', 'Toyota', 2011, 'Truck', 'Dark Green', '123XAS', 0, 'full');

-- --------------------------------------------------------

--
-- Table structure for table `Vehicle_Sale`
--

CREATE TABLE IF NOT EXISTS `Vehicle_Sale` (
  `vehicleID` varchar(17) NOT NULL,
  `model` varchar(10) DEFAULT NULL,
  `make` varchar(10) DEFAULT NULL,
  `vehicle_year` year(4) DEFAULT NULL,
  `vehicle_type` set('Car','Truck') NOT NULL,
  `colour` varchar(10) DEFAULT NULL,
  `license_plate` varchar(10) DEFAULT NULL,
  `sellPrice` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`vehicleID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Vehicle_Sale`
--

INSERT INTO `Vehicle_Sale` (`vehicleID`, `model`, `make`, `vehicle_year`, `vehicle_type`, `colour`, `license_plate`, `sellPrice`) VALUES
('4S4BRCGC5A1071279', 'i10', 'Hyundai', 2013, 'Car', 'Blue', '101DGD', '2000.00');

-- --------------------------------------------------------

--
-- Table structure for table `cosigner`
--

CREATE TABLE IF NOT EXISTS `cosigner` (
  `custID` int(11) NOT NULL DEFAULT '0',
  `driverLicenseNo` varchar(7) NOT NULL DEFAULT '',
  `birthdate` date DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`custID`,`driverLicenseNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cosigner`
--

INSERT INTO `cosigner` (`custID`, `driverLicenseNo`, `birthdate`, `name`) VALUES
(1, '33321', '1984-01-01', 'yip, alex'),
(1, '3334441', '1976-01-01', 'Erlend, Ooye'),
(6, '2323233', '1980-01-01', 'sadsada, ddddddd'),
(11, '2222222', '1981-01-01', 'Smith, John'),
(11, '2377', '1964-01-01', 'Booooy, Hooo');

-- --------------------------------------------------------

--
-- Table structure for table `custAddress`
--

CREATE TABLE IF NOT EXISTS `custAddress` (
  `custID` int(11) NOT NULL DEFAULT '0',
  `street` varchar(50) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `province` varchar(3) DEFAULT NULL,
  `postalcode` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`custID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `custAddress`
--

INSERT INTO `custAddress` (`custID`, `street`, `city`, `province`, `postalcode`) VALUES
(1, '205 Main St.', 'Vancouver', 'BC', 'V6T 2WT'),
(2, '1077 Cambie St.', 'Vancouver', 'BC', 'V6T R5T'),
(4, 'tt', 'tt', 'ttt', '123-123'),
(5, 'tt', 'tt', 'ttt', '123-123'),
(6, 'test', 'test', 'tes', '232-55-'),
(7, 'sdf', 'sdf', 'sdf', '23-665-'),
(8, '', '', '', '--'),
(9, '', '', '', '--'),
(10, 'tere', '', '', '--'),
(11, 'ubc', 'ubc', '', '--'),
(18, '1234 Sesame Street', 'Vancouver', 'BRI', 'V5Y2L7-'),
(19, 'Kung Fu Street', 'Vancouver', 'BRI', 'V5Y2L7-'),
(20, 'B Street', 'Chile', 'BRI', 'V5Y2L7-'),
(21, 'hey there', 'hey there ', 'hey', '12-32-'),
(22, 'sdf', 'sdf', 'sdf', '23-23-2'),
(23, 'fdg', 'sdf', 'sdf', '12-12-1'),
(24, 'sdf', 'sdf', 'sdf', 'dsf-f-f');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `custID` int(11) NOT NULL AUTO_INCREMENT,
  `custUsername` varchar(15) NOT NULL,
  `phoneNo` varchar(15) NOT NULL,
  `custPassword` varchar(64) NOT NULL,
  `salt` double NOT NULL,
  `driverLicenseNo` varchar(7) NOT NULL,
  `custFname` varchar(50) NOT NULL,
  `custLname` varchar(50) NOT NULL,
  `custRegistrationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`custID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`custID`, `custUsername`, `phoneNo`, `custPassword`, `salt`, `driverLicenseNo`, `custFname`, `custLname`, `custRegistrationDate`) VALUES
(1, 'liperezq', '778 683 123', '1234', 1234, '1234567', 'Ignacio', 'Perez', '0000-00-00 00:00:00'),
(2, 'rzboot', '778 683 987', '4321', 4321, '7654321', 'Robert', 'Zachs', '0000-00-00 00:00:00'),
(3, 'yasminf@yasmin.', '123-4567-89', '123', 0, '1234567', 'yasmin', 'fa', '2014-04-12 07:00:00'),
(4, 'yasminf@yasmin.', '123-4567-89', '123', 0, '1234567', 'yasmin', 'fa', '2014-04-12 07:00:00'),
(5, 'yasminf@yasmin.', '123-4567-89', '123', 0, '1234567', 'yasmin', 'fa', '2014-04-12 07:00:00'),
(6, 'yas@yas.com', '456-454-622', 'yas', 0, '1234567', 'yas', 'yas', '2014-04-12 07:00:00'),
(7, 'yasm@yasm.com', '121-3232-32', '1234', 0, '1234567', 'yyyy', 'yyyy', '2014-04-12 07:00:00'),
(8, 'docaholic@gmail', '123-456-7890', 'af62ddac2c7569babda8d42428cdc008c6f883e5619a9d8f2f5e34b20a4f44ba', 1397779949.59, '1231231', 'Aaron', 'Cheng', '2014-04-17 07:00:00'),
(9, 'alexyip@yahoo.c', '123-456-7891', '4c66b972f1997a30dd5d7a58d097855d1c3d2c9832acbf73901b96c9bcdd3897', 1398410738.45, '1234567', 'alex', 'yip', '2014-04-25 07:00:00'),
(10, 'jazz@jazz.com', '123-456-7891', '1563fe5fbaf51fa0038abf3634fa16e51bfb0bbcc92135f3ea77976292b799dd', 1398414234.34, '1234567', 'jazz', 'jazz', '2014-04-25 07:00:00'),
(11, 'alex1@alex.com', '123-456-7891', '802d57d50ca6da4e9004fbd62c590f16f7037c81af5f71adaa80d1717cc44fec', 1398461521.17, '1234567', 'alex', 'alex', '2014-04-25 07:00:00'),
(13, 'null', '6041234567', '12345678', 342572450638705, 'null', 'Darth', 'Vader', '2014-04-26 01:25:28'),
(14, 'null', '', '', 345194260695911, 'null', 'a', '', '2014-04-26 02:09:10'),
(15, '', '', '', 418396150302435, '', '', '', '2014-04-26 22:29:14'),
(16, '', '', '', 418397674846764, '', '', '', '2014-04-26 22:29:15'),
(17, '007Bond', '6040000007', '007', 418473326019751, 'B007', 'James', 'Bond', '2014-04-26 22:30:31'),
(18, 'Bond007@hotmail', '604-007-0070', 'ef6f77544595d3bfdf8b6051f81092db14e5b641f3b19645b9220aa4b249712c', 1398567710.18, '1234567', 'James', 'Bond', '2014-04-27 03:01:50'),
(19, 'jackiechan@hotm', '604-000-0000', 'cbf22f3c7cf770f94c7fb3b6e9f48c891cf3272ea00889e21549e9b426bfef34', 1398567967.42, '1234567', 'Jackie', 'Chan', '2014-04-27 03:06:07'),
(20, 'IgancioP@hotmai', '604-000-0000', '5ad6dc6350dddfd32bebcc5b16f0983990c5ff190d521d37f13d1aee8bc48b71', 1398568069.49, '1234567', 'Ignacio', 'Perez', '2014-04-27 03:07:49'),
(21, 'saru@saru.com', '12-345-5678', 'cdfbea0625f9a0f9a5ff03ac5b5a0fc2d76850489c6ead2c09c504f82065f3a4', 1398615178.5, '1234567', 'sara', 'f', '2014-04-27 16:12:58'),
(22, 'sky@sky.com', '123-456-7891', '8862a059c21d9193078e68b2382e1932f8656987ef07cf481cfc62827b1d85fa', 1398636572.92, '1234567', 'sky', 'sky', '2014-04-27 22:09:32'),
(23, 'tudor@tudor.com', '604-123-4567', '3e98fb00752d9f04220c729660f6c5dee2e1161dedf6291d430a7c9eb412fefc', 1398636702.61, '1234567', 'Tudor', 'Costin', '2014-04-27 22:11:42'),
(24, 'prateek@prateek', '123-456-7891', '789dc5b8d40335e1e0beeb1df3b348f78d5eb006a119039a7d4c3ffddc5ff85f', 1398639768.41, '1234567', 'prateek', 'prateek', '2014-04-27 23:02:48'),
(32, '', '', '', 13731507241212, '', '', '', '2014-04-28 02:58:47');

-- --------------------------------------------------------

--
-- Table structure for table `customer_points`
--

CREATE TABLE IF NOT EXISTS `customer_points` (
  `custID` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  PRIMARY KEY (`custID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer_points`
--

INSERT INTO `customer_points` (`custID`, `points`) VALUES
(1, 1500),
(2, 1000),
(8, 500),
(9, 500),
(10, 500),
(11, 500),
(18, 500),
(19, 500),
(20, 500),
(21, 500),
(22, 500),
(23, 500),
(24, 500);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `empID` varchar(30) NOT NULL,
  `empPwd` varchar(150) NOT NULL,
  `empFname` text NOT NULL,
  `empLname` text NOT NULL,
  `pwdSalt` bigint(20) NOT NULL,
  `empType` set('sysadmin','manager','clerk') NOT NULL,
  PRIMARY KEY (`empID`),
  UNIQUE KEY `empID` (`empID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`empID`, `empPwd`, `empFname`, `empLname`, `pwdSalt`, `empType`) VALUES
('101', 'eded0d577a181b11bd3b96c5753fa1d872fa3ff6ec540fb4bda4e8ad615c9f97', 'Punit', 'Dimri', 1398648449996798000, 'manager'),
('108', '00ecdf0fa579940a813e8d062595c3d91d7a2e9d985c9a7dd56b17f741ed9306', 'Prateek', 'kumar', 1398647669227967000, 'sysadmin'),
('201', '417681b918d92186359eed37095e7f3ab2931a3cd6f9bd7165aca6a3cd3ce8c4', 'Prateek', 'Kumar', 21, 'sysadmin'),
('202', 'aa17fed895bec03ce8e8cc68739433c85d5c197045875e855892ca4af25029af', 'Aaron', 'Cheng', 610971382432934, 'clerk');

-- --------------------------------------------------------

--
-- Table structure for table `equipment`
--

CREATE TABLE IF NOT EXISTS `equipment` (
  `equpimentID` varchar(30) NOT NULL,
  `equipmentType` varchar(30) NOT NULL,
  `vehicleType` varchar(30) NOT NULL,
  `dailyRent` decimal(10,2) NOT NULL,
  `weeklyRent` decimal(10,2) NOT NULL,
  PRIMARY KEY (`equpimentID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `equipment`
--

INSERT INTO `equipment` (`equpimentID`, `equipmentType`, `vehicleType`, `dailyRent`, `weeklyRent`) VALUES
('1', 'Ski Rack', 'car', '30.00', '40.00'),
('2', 'Child Safety Seat', 'car', '40.00', '50.00'),
('3', 'Lift Gate', 'truck', '60.00', '80.00'),
('4', 'Tow Cable ', 'truck', '10.00', '100.00');

-- --------------------------------------------------------

--
-- Table structure for table `rentCardInfo`
--

CREATE TABLE IF NOT EXISTS `rentCardInfo` (
  `rentID` int(11) NOT NULL,
  `cardNo` varchar(16) NOT NULL,
  `cardType` set('MasterCard','American Express','Visa') NOT NULL,
  `expYear` year(2) NOT NULL,
  `expMonth` int(2) NOT NULL,
  PRIMARY KEY (`rentID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rentCardInfo`
--

INSERT INTO `rentCardInfo` (`rentID`, `cardNo`, `cardType`, `expYear`, `expMonth`) VALUES
(1, '5500005555555559', 'MasterCard', 15, 10),
(17, '4321123412344321', 'Visa', 17, 5),
(19, '9870123456433218', 'Visa', 16, 3),
(20, '3499623455123121', 'American Express', 18, 8),
(21, '1231234444333311', 'American Express', 17, 2),
(23, '1234455311123355', 'MasterCard', 15, 5),
(24, '1111122235555678', 'MasterCard', 19, 4),
(25, '1135555678908643', 'American Express', 20, 4),
(26, '2223231344443356', 'MasterCard', 23, 3),
(27, '1422222222222222', 'MasterCard', 20, 3),
(28, '9987676666666666', 'American Express', 17, 2),
(29, '8962213133445667', 'American Express', 20, 5),
(30, '2323232323232323', 'American Express', 21, 7),
(31, '3232323232323232', 'Visa', 20, 4),
(32, '3423423424324324', 'American Express', 24, 5),
(33, '2231414422122223', 'MasterCard', 19, 1),
(34, '0007000700070007', 'MasterCard', 23, 6),
(35, '6666663636333332', 'MasterCard', 16, 2);

-- --------------------------------------------------------

--
-- Table structure for table `rentalrates`
--

CREATE TABLE IF NOT EXISTS `rentalrates` (
  `rentCategory` varchar(30) NOT NULL,
  `hourlyPrice` decimal(10,2) NOT NULL,
  `dailyPrice` decimal(10,2) NOT NULL,
  `weeklyPrice` decimal(10,2) NOT NULL,
  `insuranceRate` decimal(10,2) NOT NULL,
  PRIMARY KEY (`rentCategory`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rentalrates`
--

INSERT INTO `rentalrates` (`rentCategory`, `hourlyPrice`, `dailyPrice`, `weeklyPrice`, `insuranceRate`) VALUES
('12-Foot', '25.00', '125.00', '575.00', '50.00'),
('15-Foot', '29.00', '145.00', '600.00', '50.00'),
('24-Foot', '30.00', '150.00', '650.00', '100.00'),
('Box Truck', '60.00', '300.00', '1000.00', '150.00'),
('Cargo Van', '40.00', '200.00', '800.00', '150.00'),
('Compact', '15.00', '98.00', '450.00', '20.00'),
('Economy', '15.00', '100.00', '500.00', '25.00'),
('Full-size', '25.00', '200.00', '800.00', '30.00'),
('Luxury', '50.00', '500.00', '1000.00', '150.00'),
('Mid-size', '17.00', '150.00', '600.00', '50.00'),
('Premium', '30.00', '250.00', '900.00', '75.00'),
('Standard', '20.00', '175.00', '650.00', '40.00'),
('SUV', '22.00', '240.00', '800.00', '45.00'),
('Van', '22.00', '240.00', '800.00', '55.00');

-- --------------------------------------------------------

--
-- Table structure for table `session`
--

CREATE TABLE IF NOT EXISTS `session` (
  `sessionID` int(11) NOT NULL AUTO_INCREMENT,
  `custID` int(11) NOT NULL,
  `expiration` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sessionID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=48 ;

--
-- Dumping data for table `session`
--

INSERT INTO `session` (`sessionID`, `custID`, `expiration`) VALUES
(23, 9, '2014-04-24 23:20:05'),
(41, 21, '2014-04-27 16:28:27'),
(43, 10, '2014-04-27 18:00:53'),
(44, 22, '2014-04-27 22:24:45'),
(47, 23, '2014-04-27 23:20:26');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `EquipmentUsed`
--
ALTER TABLE `EquipmentUsed`
  ADD CONSTRAINT `EquipmentUsed_ibfk_1` FOREIGN KEY (`equipmentID`) REFERENCES `equipment` (`equpimentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `EquipmentUsed_ibfk_2` FOREIGN KEY (`rentID`) REFERENCES `Rents` (`rentID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `RentPayment`
--
ALTER TABLE `RentPayment`
  ADD CONSTRAINT `RentPayment_ibfk_1` FOREIGN KEY (`custID`) REFERENCES `customer` (`custID`) ON DELETE CASCADE,
  ADD CONSTRAINT `RentPayment_ibfk_2` FOREIGN KEY (`vehicleID`) REFERENCES `Vehicle_Rent` (`vehicleID`) ON DELETE CASCADE;

--
-- Constraints for table `Rents`
--
ALTER TABLE `Rents`
  ADD CONSTRAINT `Rents_ibfk_1` FOREIGN KEY (`vehicleID`) REFERENCES `Vehicle_Rent` (`vehicleID`),
  ADD CONSTRAINT `Rents_ibfk_2` FOREIGN KEY (`custID`) REFERENCES `customer` (`custID`);

--
-- Constraints for table `Vehicle_Category`
--
ALTER TABLE `Vehicle_Category`
  ADD CONSTRAINT `Vehicle_Category_ibfk_1` FOREIGN KEY (`vehicleID`) REFERENCES `Vehicle_Rent` (`vehicleID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `cosigner`
--
ALTER TABLE `cosigner`
  ADD CONSTRAINT `cosigner_ibfk_1` FOREIGN KEY (`custID`) REFERENCES `customer` (`custID`) ON DELETE CASCADE;

--
-- Constraints for table `custAddress`
--
ALTER TABLE `custAddress`
  ADD CONSTRAINT `custAddress_ibfk_1` FOREIGN KEY (`custID`) REFERENCES `customer` (`custID`) ON DELETE CASCADE;

--
-- Constraints for table `customer_points`
--
ALTER TABLE `customer_points`
  ADD CONSTRAINT `FK_customer_points_custID` FOREIGN KEY (`custID`) REFERENCES `customer` (`custID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `rentCardInfo`
--
ALTER TABLE `rentCardInfo`
  ADD CONSTRAINT `rentCardInfo_ibfk_1` FOREIGN KEY (`rentID`) REFERENCES `Rents` (`rentID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
