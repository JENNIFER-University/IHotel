--
-- Facilities table structure
--

CREATE TABLE `facilities` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `guests`
--

CREATE TABLE `guests` (
  `id` int(11) NOT NULL PRIMARY KEY  AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `id` varchar(45) NOT NULL PRIMARY KEY,
  `guest_id` int(11) NOT NULL,
  `roomId` int(11) NOT NULL,
  `checkInDate` varchar(45) DEFAULT NULL,
  `checkOutDate` varchar(45) DEFAULT NULL,
  `totalNights` varchar(45) DEFAULT NULL,
  `comments` varchar(45) DEFAULT NULL,
  `guestsNumber` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `floor` varchar(45) DEFAULT NULL,
  `price` double NOT NULL,
  `description` text,
  `room_type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `rooms_facilities`
--

CREATE TABLE `rooms_facilities` (
  `roomId` int(11) NOT NULL,
  `facilityId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `rooms_facilities`
  ADD KEY `fk_roomsFacilities_1_idx` (`roomId`),
  ADD KEY `fk_roomsFacilities_2_idx` (`facilityId`);


--
-- Table structure for table `rooms_type`
--

CREATE TABLE `rooms_type` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `roomType` varchar(45) NOT NULL,
  `maxCapacity` int(11) NOT NULL,
  `roomSize` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Users Table structure
--

CREATE TABLE `users` (
  `id` varchar(20) NOT NULL PRIMARY KEY,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;