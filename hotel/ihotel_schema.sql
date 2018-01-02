-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 02, 2018 at 03:12 AM
-- Server version: 5.6.35
-- PHP Version: 7.0.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `ihotel`
--

-- --------------------------------------------------------

--
-- Table structure for table `facilities`
--

CREATE TABLE `facilities` (
  `id` int(11) NOT NULL,
  `facilityName` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `facilities`
--

INSERT INTO `facilities` (`id`, `facilityName`) VALUES
(1, 'TV'),
(2, 'Safe'),
(3, 'Mini Fridge '),
(4, 'Hair Dryer'),
(5, 'Make Up Room'),
(6, 'DVD player'),
(7, 'Complimentary Drinking Water'),
(8, 'Air-conditioning'),
(9, 'Tea and coffee making facilities'),
(10, 'Bathroom with bath tub and hairdryer'),
(11, 'Bathroom with shower and hairdryer'),
(12, 'Slippers '),
(13, 'Direct dial phone');

-- --------------------------------------------------------

--
-- Table structure for table `guests`
--

CREATE TABLE `guests` (
  `id` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  `forenames` varchar(45) NOT NULL,
  `surename` varchar(45) NOT NULL,
  `dob` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hotel_info`
--

CREATE TABLE `hotel_info` (
  `name` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `intro` text,
  `payment_url` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hotel_info`
--

INSERT INTO `hotel_info` (`name`, `address`, `phone`, `email`, `intro`, `payment_url`) VALUES
('iHote', 'Seoul, South Korea', '0082123456789', 'customer@ihotel.fake', 'Most Luxurious hotel with the royal treatments and excellent customer service', '127.0.0.1:8080');

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `id` varchar(45) NOT NULL,
  `guest_id` int(11) NOT NULL,
  `roomId` int(11) NOT NULL,
  `checkInDate` varchar(45) DEFAULT NULL,
  `checkOutDate` varchar(45) DEFAULT NULL,
  `totalNights` varchar(45) DEFAULT NULL,
  `comments` varchar(45) DEFAULT NULL,
  `guestsNumber` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `id` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `floor` varchar(45) DEFAULT NULL,
  `price` double NOT NULL,
  `description` text,
  `room_type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`id`, `number`, `floor`, `price`, `description`, `room_type`) VALUES
(1, 101, 'First Floor', 100, 'Space in your house How to sell faster than your neighbors How to make a strategic use. To discourage you by telling. To discourage you by telling. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. To discourage you by telling. To discourage you by telling. Space in your house How to sell faster than your neighbors How to make a strategic use. The real goal of any talk or speech.\n\nBy Learning Ways To Become Peaceful. One of the greatest barriers to making the sale is your prospect\'s natural. Don\'t stubbornly. Don\'t stubbornly. Don\'t stubbornly. -And Gain Power By Learning Ways To Become Peaceful.', 1),
(2, 102, 'First Floor', 200, 'Space in your house How to sell faster than your neighbors How to make a strategic use. To discourage you by telling. To discourage you by telling. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. To discourage you by telling. To discourage you by telling. Space in your house How to sell faster than your neighbors How to make a strategic use. The real goal of any talk or speech.\r\n\r\nBy Learning Ways To Become Peaceful. One of the greatest barriers to making the sale is your prospect\'s natural. Don\'t stubbornly. Don\'t stubbornly. Don\'t stubbornly. -And Gain Power By Learning Ways To Become Peaceful.', 2),
(3, 200, 'Second Floor', 250, 'Space in your house How to sell faster than your neighbors How to make a strategic use. To discourage you by telling. To discourage you by telling. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. To discourage you by telling. To discourage you by telling. Space in your house How to sell faster than your neighbors How to make a strategic use. The real goal of any talk or speech.\r\n\r\nBy Learning Ways To Become Peaceful. One of the greatest barriers to making the sale is your prospect\'s natural. Don\'t stubbornly. Don\'t stubbornly. Don\'t stubbornly. -And Gain Power By Learning Ways To Become Peaceful.', 3),
(4, 201, 'Second Floor', 250, 'Space in your house How to sell faster than your neighbors How to make a strategic use. To discourage you by telling. To discourage you by telling. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. To discourage you by telling. To discourage you by telling. Space in your house How to sell faster than your neighbors How to make a strategic use. The real goal of any talk or speech.\r\n\r\nBy Learning Ways To Become Peaceful. One of the greatest barriers to making the sale is your prospect\'s natural. Don\'t stubbornly. Don\'t stubbornly. Don\'t stubbornly. -And Gain Power By Learning Ways To Become Peaceful.', 3),
(5, 300, 'Third Floor', 300, 'Space in your house How to sell faster than your neighbors How to make a strategic use. To discourage you by telling. To discourage you by telling. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. To discourage you by telling. To discourage you by telling. Space in your house How to sell faster than your neighbors How to make a strategic use. The real goal of any talk or speech.\r\n\r\nBy Learning Ways To Become Peaceful. One of the greatest barriers to making the sale is your prospect\'s natural. Don\'t stubbornly. Don\'t stubbornly. Don\'t stubbornly. -And Gain Power By Learning Ways To Become Peaceful.', 4),
(6, 301, 'Third Floor', 350, 'Space in your house How to sell faster than your neighbors How to make a strategic use. To discourage you by telling. To discourage you by telling. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. To discourage you by telling. To discourage you by telling. Space in your house How to sell faster than your neighbors How to make a strategic use. The real goal of any talk or speech.\r\n\r\nBy Learning Ways To Become Peaceful. One of the greatest barriers to making the sale is your prospect\'s natural. Don\'t stubbornly. Don\'t stubbornly. Don\'t stubbornly. -And Gain Power By Learning Ways To Become Peaceful.', 5),
(7, 500, 'Fifth Floor', 600, 'Space in your house How to sell faster than your neighbors How to make a strategic use. To discourage you by telling. To discourage you by telling. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. To discourage you by telling. To discourage you by telling. Space in your house How to sell faster than your neighbors How to make a strategic use. The real goal of any talk or speech.\n\nBy Learning Ways To Become Peaceful. One of the greatest barriers to making the sale is your prospect\'s natural. Don\'t stubbornly. Don\'t stubbornly. Don\'t stubbornly. -And Gain Power By Learning Ways To Become Peaceful.', 6),
(8, 520, 'Fifth Floor', 760, 'Space in your house How to sell faster than your neighbors How to make a strategic use. To discourage you by telling. To discourage you by telling. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. To discourage you by telling. To discourage you by telling. Space in your house How to sell faster than your neighbors How to make a strategic use. The real goal of any talk or speech.\r\n\r\nBy Learning Ways To Become Peaceful. One of the greatest barriers to making the sale is your prospect\'s natural. Don\'t stubbornly. Don\'t stubbornly. Don\'t stubbornly. -And Gain Power By Learning Ways To Become Peaceful.', 6),
(9, 601, 'Sixth Floor', 800, 'Space in your house How to sell faster than your neighbors How to make a strategic use. To discourage you by telling. To discourage you by telling. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. To discourage you by telling. To discourage you by telling. Space in your house How to sell faster than your neighbors How to make a strategic use. The real goal of any talk or speech.\r\n\r\nBy Learning Ways To Become Peaceful. One of the greatest barriers to making the sale is your prospect\'s natural. Don\'t stubbornly. Don\'t stubbornly. Don\'t stubbornly. -And Gain Power By Learning Ways To Become Peaceful.', 5),
(10, 403, 'Fourth Floor', 423, 'Space in your house How to sell faster than your neighbors How to make a strategic use. To discourage you by telling. To discourage you by telling. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. To discourage you by telling. To discourage you by telling. Space in your house How to sell faster than your neighbors How to make a strategic use. The real goal of any talk or speech.\r\n\r\nBy Learning Ways To Become Peaceful. One of the greatest barriers to making the sale is your prospect\'s natural. Don\'t stubbornly. Don\'t stubbornly. Don\'t stubbornly. -And Gain Power By Learning Ways To Become Peaceful.', 4),
(11, 801, 'Eight Floor', 1000, 'Space in your house How to sell faster than your neighbors How to make a strategic use. To discourage you by telling. To discourage you by telling. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. To discourage you by telling. To discourage you by telling. Space in your house How to sell faster than your neighbors How to make a strategic use. The real goal of any talk or speech.\r\n\r\nBy Learning Ways To Become Peaceful. One of the greatest barriers to making the sale is your prospect\'s natural. Don\'t stubbornly. Don\'t stubbornly. Don\'t stubbornly. -And Gain Power By Learning Ways To Become Peaceful.', 5),
(12, 105, 'First Floor', 234, 'Space in your house How to sell faster than your neighbors How to make a strategic use. To discourage you by telling. To discourage you by telling. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. I m going to outline 14 different ways that I ve found you. The real goal of any talk or speech. To discourage you by telling. To discourage you by telling. Space in your house How to sell faster than your neighbors How to make a strategic use. The real goal of any talk or speech.\r\n\r\nBy Learning Ways To Become Peaceful. One of the greatest barriers to making the sale is your prospect\'s natural. Don\'t stubbornly. Don\'t stubbornly. Don\'t stubbornly. -And Gain Power By Learning Ways To Become Peaceful.', 3);

-- --------------------------------------------------------

--
-- Table structure for table `rooms_facilities`
--

CREATE TABLE `rooms_facilities` (
  `roomId` int(11) NOT NULL,
  `facilityId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rooms_facilities`
--

INSERT INTO `rooms_facilities` (`roomId`, `facilityId`) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 2),
(2, 3),
(3, 1),
(3, 2),
(3, 3),
(4, 1),
(4, 2),
(4, 3),
(5, 1),
(5, 2),
(5, 3),
(6, 1),
(6, 2),
(6, 3),
(7, 1),
(7, 2),
(7, 3),
(8, 1),
(8, 2),
(8, 3),
(9, 1),
(9, 2),
(9, 3),
(10, 1),
(10, 2),
(10, 3),
(11, 1),
(11, 2),
(11, 3),
(12, 1),
(12, 2),
(12, 3),
(5, 9),
(10, 12);

-- --------------------------------------------------------

--
-- Table structure for table `rooms_type`
--

CREATE TABLE `rooms_type` (
  `id` int(11) NOT NULL,
  `roomType` varchar(45) NOT NULL,
  `maxCapacity` int(11) NOT NULL,
  `roomSize` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rooms_type`
--

INSERT INTO `rooms_type` (`id`, `roomType`, `maxCapacity`, `roomSize`) VALUES
(1, 'Single Room', 2, '20 sq'),
(2, 'Double Room', 2, '25 sq'),
(3, 'King Size Bedroom', 3, '30 sq'),
(4, 'Business Suite', 4, '40 sql'),
(5, '3 Room Apartment', 6, '50 sq'),
(6, 'Luxurious Suite', 4, '60 sq');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` varchar(20) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `facilities`
--
ALTER TABLE `facilities`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `guests`
--
ALTER TABLE `guests`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hotel_info`
--
ALTER TABLE `hotel_info`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_reservations_1_idx` (`guest_id`),
  ADD KEY `fk_reservations_2_idx` (`roomId`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_rooms_roomsType_idx` (`room_type`);

--
-- Indexes for table `rooms_facilities`
--
ALTER TABLE `rooms_facilities`
  ADD KEY `fk_roomsFacilities_1_idx` (`roomId`),
  ADD KEY `fk_roomsFacilities_2_idx` (`facilityId`);

--
-- Indexes for table `rooms_type`
--
ALTER TABLE `rooms_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `facilities`
--
ALTER TABLE `facilities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `guests`
--
ALTER TABLE `guests`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=124;
--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `rooms_type`
--
ALTER TABLE `rooms_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `fk_reservations_1` FOREIGN KEY (`guest_id`) REFERENCES `guests` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_reservations_2` FOREIGN KEY (`roomId`) REFERENCES `rooms` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `rooms`
--
ALTER TABLE `rooms`
  ADD CONSTRAINT `fk_rooms_roomsType` FOREIGN KEY (`room_type`) REFERENCES `rooms_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `rooms_facilities`
--
ALTER TABLE `rooms_facilities`
  ADD CONSTRAINT `fk_roomsFacilities_1` FOREIGN KEY (`roomId`) REFERENCES `rooms` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_roomsFacilities_2` FOREIGN KEY (`facilityId`) REFERENCES `facilities` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
