-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 13, 2022 at 06:57 PM
-- Server version: 10.2.44-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `xuxlffke_scrapingdb_final`
--

-- --------------------------------------------------------

--
-- Table structure for table `changeLog`
--

CREATE TABLE `changeLog` (
  `ID` int(11) NOT NULL,
  `EventType` varchar(100) NOT NULL,
  `TableName` varchar(100) NOT NULL,
  `Value` varchar(200) NOT NULL,
  `CollumnName` varchar(100) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `contributions`
--

CREATE TABLE `contributions` (
  `ID` int(11) NOT NULL,
  `PeopleID` int(11) NOT NULL,
  `ProductionID` int(11) NOT NULL,
  `RoleID` int(11) NOT NULL,
  `subRole` varchar(100) DEFAULT NULL,
  `SystemID` int(10) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `contributions`
--
DELIMITER $$
CREATE TRIGGER `contributions_delete_trigger` AFTER DELETE ON `contributions` FOR EACH ROW BEGIN
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'contributions', OLD.ID, 'ID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'contributions', OLD.PeopleID, 'PeopleID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'contributions', OLD.ProductionID, 'ProductionID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'contributions', OLD.RoleID, 'RoleID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'contributions', OLD.subRole, 'subRole');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'contributions', OLD.SystemID, 'SystemID');
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `contributions_insert_trigger` AFTER INSERT ON `contributions` FOR EACH ROW BEGIN
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'contributions', NEW.ID , 'ID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'contributions', NEW.PeopleID, 'PeopleID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'contributions', NEW.ProductionID, 'ProductionID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'contributions', NEW.RoleID, 'RoleID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'contributions', NEW.subRole, 'subRole');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'contributions', NEW.SystemID , 'SystemID');
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `contributions_update_trigger` AFTER UPDATE ON `contributions` FOR EACH ROW BEGIN
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'contributions', CONCAT(OLD.ID, ' >> ', NEW.ID), 'ID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'contributions', CONCAT(OLD.PeopleID, ' >> ', NEW.PeopleID), 'PeopleID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'contributions', CONCAT(OLD.ProductionID, ' >> ', NEW.ProductionID), 'ProductionID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'contributions', CONCAT(OLD.RoleID, ' >> ', NEW.RoleID), 'RoleID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'contributions', CONCAT(OLD.subRole, ' >> ', NEW.subRole), 'subRole');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'contributions', CONCAT(OLD.SystemID, ' >> ', NEW.SystemID), 'SystemID');
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE `events` (
  `ID` int(11) NOT NULL,
  `ProductionID` int(11) NOT NULL,
  `VenueID` int(11) NOT NULL,
  `DateEvent` datetime NOT NULL,
  `PriceRange` varchar(30) NOT NULL,
  `SystemID` int(10) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `events`
--
DELIMITER $$
CREATE TRIGGER `events_delete_trigger` AFTER DELETE ON `events` FOR EACH ROW BEGIN
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'events', OLD.ID, 'ID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'events', OLD.ProductionID, 'ProductionID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'events', OLD.VenueID, 'VenueID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'events', OLD.DateEvent, 'DateEvent');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'events', OLD.PriceRange, 'PriceRange');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'events', OLD.SystemID, 'SystemID');
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `events_insert_trigger` AFTER INSERT ON `events` FOR EACH ROW BEGIN
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'events', NEW.ID , 'ID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'events', NEW.ProductionID, 'ProductionID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'events', NEW.VenueID, 'VenueID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'events', NEW.DateEvent, 'DateEvent');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'events', NEW.PriceRange, 'PriceRange');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'events', NEW.SystemID , 'SystemID');
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `events_update_trigger` AFTER UPDATE ON `events` FOR EACH ROW BEGIN
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'events', CONCAT(OLD.ID, ' >> ', NEW.ID), 'ID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'events', CONCAT(OLD.ProductionID, ' >> ', NEW.ProductionID), 'ProductionID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'events', CONCAT(OLD.VenueID, ' >> ', NEW.VenueID), 'VenueID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'events', CONCAT(OLD.DateEvent, ' >> ', NEW.DateEvent), 'DateEvent');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'events', CONCAT(OLD.PriceRange, ' >> ', NEW.PriceRange), 'PriceRange');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'events', CONCAT(OLD.SystemID, ' >> ', NEW.SystemID), 'SystemID');
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE `images` (
  `ID` int(11) NOT NULL,
  `imageURL` varchar(100) DEFAULT NULL,
  `personID` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `organizer`
--

CREATE TABLE `organizer` (
  `ID` int(11) NOT NULL,
  `Name` varchar(80) NOT NULL,
  `Address` varchar(50) NOT NULL,
  `Town` varchar(100) NOT NULL,
  `postcode` varchar(20) NOT NULL,
  `Phone` varchar(30) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Doy` varchar(30) NOT NULL,
  `Afm` varchar(30) NOT NULL,
  `SystemID` int(10) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Triggers `organizer`
--
DELIMITER $$
CREATE TRIGGER `organizer_delete_trigger` AFTER DELETE ON `organizer` FOR EACH ROW BEGIN
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'organizer', OLD.ID, 'ID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'organizer', OLD.Name, 'Name');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'organizer', OLD.Address, 'Address');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'organizer', OLD.Town, 'Town');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'organizer', OLD.postcode, 'postcode');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'organizer', OLD.Phone, 'Phone');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'organizer', OLD.Email, 'Email');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'organizer', OLD.Doy, 'Doy');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'organizer', OLD.Afm, 'Afm');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'organizer', OLD.SystemID, 'SystemID');
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `organizer_insert_trigger` AFTER INSERT ON `organizer` FOR EACH ROW BEGIN
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'organizer', NEW.ID , 'ID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'organizer', NEW.Name, 'Name');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'organizer', NEW.Address, 'Address');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'organizer', NEW.Town, 'Town');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'organizer', NEW.postcode, 'postcode');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'organizer', NEW.Phone, 'Phone');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'organizer', NEW.Email, 'Email');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'organizer', NEW.Doy, 'Doy');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'organizer', NEW.Afm, 'Afm');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'organizer', NEW.SystemID , 'SystemID');
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `organizer_update_trigger` AFTER UPDATE ON `organizer` FOR EACH ROW BEGIN
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'organizer', CONCAT(OLD.ID, ' >> ', NEW.ID), 'ID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'organizer', CONCAT(OLD.Name, ' >> ', NEW.Name), 'Name');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'organizer', CONCAT(OLD.Address, ' >> ', NEW.Address), 'Address');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'organizer', CONCAT(OLD.Town, ' >> ', NEW.Town), 'Town');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'organizer', CONCAT(OLD.postcode, ' >> ', NEW.postcode), 'postcode');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'organizer', CONCAT(OLD.Phone, ' >> ', NEW.Phone), 'Phone');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'organizer', CONCAT(OLD.Email, ' >> ', NEW.Email), 'Email');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'organizer', CONCAT(OLD.Doy, ' >> ', NEW.Doy), 'Doy');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'organizer', CONCAT(OLD.Afm, ' >> ', NEW.Afm), 'Afm');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'organizer', CONCAT(OLD.SystemID, ' >> ', NEW.SystemID), 'SystemID');
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `persons`
--

CREATE TABLE `persons` (
  `ID` int(11) NOT NULL,
  `Fullname` varchar(600) NOT NULL,
  `SystemID` int(10) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `persons`
--
DELIMITER $$
CREATE TRIGGER `persons_delete_trigger` AFTER DELETE ON `persons` FOR EACH ROW BEGIN
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'persons', OLD.ID, 'ID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'persons', OLD.Fullname, 'Fullname');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'persons', OLD.SystemID, 'SystemID');
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `persons_insert_trigger` AFTER INSERT ON `persons` FOR EACH ROW BEGIN
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'persons', NEW.ID , 'ID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'persons', NEW.Fullname , 'Fullname');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'persons', NEW.SystemID , 'SystemID');
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `persons_update_trigger` AFTER UPDATE ON `persons` FOR EACH ROW BEGIN
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'persons', CONCAT(OLD.ID, ' >> ', NEW.ID), 'ID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'persons', CONCAT(OLD.Fullname, ' >> ', NEW.Fullname), 'Fullname');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'persons', CONCAT(OLD.SystemID, ' >> ', NEW.SystemID), 'SystemID');
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `production`
--

CREATE TABLE `production` (
  `ID` int(11) NOT NULL,
  `OrganizerID` int(11) DEFAULT NULL,
  `Title` varchar(255) NOT NULL,
  `Description` longtext NOT NULL,
  `URL` varchar(256) NOT NULL,
  `Producer` varchar(255) NOT NULL,
  `MediaURL` varchar(500) NOT NULL,
  `Duration` varchar(30) NOT NULL,
  `SystemID` int(10) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `production`
--
DELIMITER $$
CREATE TRIGGER `production_delete_trigger` AFTER DELETE ON `production` FOR EACH ROW BEGIN
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'production', OLD.ID, 'ID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'production', OLD.OrganizerID, 'OrganizerID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'production', OLD.Title, 'Title');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'production', OLD.Description, 'Description');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'production', OLD.URL, 'URL');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'production', OLD.Producer, 'Producer');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'production', OLD.MediaURL, 'MediaURL');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'production', OLD.Duration, 'Duration');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'production', OLD.SystemID, 'SystemID');
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `production_insert_trigger` AFTER INSERT ON `production` FOR EACH ROW BEGIN
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'production', NEW.ID , 'ID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'production', NEW.OrganizerID, 'OrganizerID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'production', NEW.Title, 'Title');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'production', NEW.Description, 'Description');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'production', NEW.URL, 'URL');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'production', NEW.Producer, 'Producer');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'production', NEW.MediaURL, 'MediaURL');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'production', NEW.Duration, 'Duration');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'production', NEW.SystemID , 'SystemID');
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `production_update_trigger` AFTER UPDATE ON `production` FOR EACH ROW BEGIN
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'production', CONCAT(OLD.ID, ' >> ', NEW.ID), 'ID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'production', CONCAT(OLD.OrganizerID, ' >> ', NEW.OrganizerID), 'OrganizerID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'production', CONCAT(OLD.Title, ' >> ', NEW.Title), 'Title');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'production', CONCAT(OLD.Description, ' >> ', NEW.Description), 'Description');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'production', CONCAT(OLD.URL, ' >> ', NEW.URL), 'URL');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'production', CONCAT(OLD.Producer, ' >> ', NEW.Producer), 'Producer');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'production', CONCAT(OLD.MediaURL, ' >> ', NEW.MediaURL), 'MediaURL');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'production', CONCAT(OLD.Duration, ' >> ', NEW.Duration), 'Duration');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'production', CONCAT(OLD.SystemID, ' >> ', NEW.SystemID), 'SystemID');
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `ID` int(11) NOT NULL,
  `Role` varchar(150) NOT NULL,
  `SystemID` int(10) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `roles`
--
DELIMITER $$
CREATE TRIGGER `role_delete_trigger` AFTER DELETE ON `roles` FOR EACH ROW BEGIN
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'roles', OLD.ID, 'ID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'roles', OLD.Role, 'Role');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'roles', OLD.SystemID, 'SystemID');
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `role_insert_trigger` AFTER INSERT ON `roles` FOR EACH ROW BEGIN
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'roles', NEW.ID , 'ID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'roles', NEW.Role , 'Role');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'roles', NEW.SystemID , 'SystemID');
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `role_update_trigger` AFTER UPDATE ON `roles` FOR EACH ROW BEGIN
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'roles', CONCAT(OLD.ID, ' >> ', NEW.ID), 'ID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'roles', CONCAT(OLD.Role, ' >> ', NEW.Role), 'Role');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'roles', CONCAT(OLD.SystemID, ' >> ', NEW.SystemID), 'SystemID');
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `system`
--

CREATE TABLE `system` (
  `ID` int(255) NOT NULL,
  `name` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `venue`
--

CREATE TABLE `venue` (
  `ID` int(11) NOT NULL,
  `Title` varchar(60) DEFAULT NULL,
  `Address` varchar(60) DEFAULT NULL,
  `SystemID` int(10) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `venue`
--
DELIMITER $$
CREATE TRIGGER `venue_delete_trigger` AFTER DELETE ON `venue` FOR EACH ROW BEGIN
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'venue', OLD.ID, 'ID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'venue', OLD.Title, 'Title');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'venue', OLD.Address, 'Address');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('delete', 'venue', OLD.SystemID, 'SystemID');
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `venue_insert_trigger` AFTER INSERT ON `venue` FOR EACH ROW BEGIN
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'venue', NEW.ID , 'ID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'venue', NEW.Title , 'Title');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'venue', NEW.Address , 'Address');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('insert', 'venue', NEW.SystemID , 'SystemID');
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `venue_update_trigger` AFTER UPDATE ON `venue` FOR EACH ROW BEGIN
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'venue', CONCAT(OLD.ID, ' >> ', NEW.ID), 'ID');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'venue', CONCAT(OLD.Title, ' >> ', NEW.Title), 'Title');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'venue', CONCAT(OLD.Address, ' >> ', NEW.Address), 'Address');
  INSERT INTO changeLog (EventType, TableName, Value, CollumnName)
  VALUES('update', 'venue', CONCAT(OLD.SystemID, ' >> ', NEW.SystemID), 'SystemID');
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `changeLog`
--
ALTER TABLE `changeLog`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `contributions`
--
ALTER TABLE `contributions`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `PeopleID` (`PeopleID`),
  ADD KEY `RoleID` (`RoleID`),
  ADD KEY `ProductionID` (`ProductionID`),
  ADD KEY `SystemID` (`SystemID`);

--
-- Indexes for table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ProductionID` (`ProductionID`),
  ADD KEY `VenueID` (`VenueID`),
  ADD KEY `SystemID` (`SystemID`);

--
-- Indexes for table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `personID` (`personID`);

--
-- Indexes for table `organizer`
--
ALTER TABLE `organizer`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `SystemID` (`SystemID`);

--
-- Indexes for table `persons`
--
ALTER TABLE `persons`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `SystemID` (`SystemID`);

--
-- Indexes for table `production`
--
ALTER TABLE `production`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `OrganizerID` (`OrganizerID`),
  ADD KEY `SystemID` (`SystemID`),
  ADD KEY `SystemID_2` (`SystemID`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `SystemID` (`SystemID`);

--
-- Indexes for table `system`
--
ALTER TABLE `system`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `venue`
--
ALTER TABLE `venue`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `SystemID` (`SystemID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `changeLog`
--
ALTER TABLE `changeLog`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `contributions`
--
ALTER TABLE `contributions`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `events`
--
ALTER TABLE `events`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `organizer`
--
ALTER TABLE `organizer`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `persons`
--
ALTER TABLE `persons`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `production`
--
ALTER TABLE `production`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `system`
--
ALTER TABLE `system`
  MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `venue`
--
ALTER TABLE `venue`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `contributions`
--
ALTER TABLE `contributions`
  ADD CONSTRAINT `contributions_ibfk_1` FOREIGN KEY (`RoleID`) REFERENCES `roles` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `contributions_ibfk_2` FOREIGN KEY (`PeopleID`) REFERENCES `persons` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `contributions_ibfk_3` FOREIGN KEY (`ProductionID`) REFERENCES `production` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `contributions_ibfk_4` FOREIGN KEY (`SystemID`) REFERENCES `system` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `events_ibfk_1` FOREIGN KEY (`ProductionID`) REFERENCES `production` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `events_ibfk_2` FOREIGN KEY (`VenueID`) REFERENCES `venue` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `events_ibfk_3` FOREIGN KEY (`SystemID`) REFERENCES `system` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `organizer`
--
ALTER TABLE `organizer`
  ADD CONSTRAINT `organizer_ibfk_1` FOREIGN KEY (`SystemID`) REFERENCES `system` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `persons`
--
ALTER TABLE `persons`
  ADD CONSTRAINT `persons_ibfk_1` FOREIGN KEY (`SystemID`) REFERENCES `system` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `production`
--
ALTER TABLE `production`
  ADD CONSTRAINT `production_ibfk_1` FOREIGN KEY (`OrganizerID`) REFERENCES `organizer` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `production_ibfk_2` FOREIGN KEY (`SystemID`) REFERENCES `system` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `roles`
--
ALTER TABLE `roles`
  ADD CONSTRAINT `roles_ibfk_1` FOREIGN KEY (`SystemID`) REFERENCES `system` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `venue`
--
ALTER TABLE `venue`
  ADD CONSTRAINT `venue_ibfk_1` FOREIGN KEY (`SystemID`) REFERENCES `system` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
