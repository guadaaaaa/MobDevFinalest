-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 22, 2024 at 06:36 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbdelish`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblfood`
--

CREATE TABLE `tblfood` (
  `food_id` int NOT NULL,
  `food_name` varchar(255) NOT NULL,
  `food_description` varchar(255) NOT NULL,
  `food_category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `num_orders` int NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tblfood`
--

INSERT INTO `tblfood` (`food_id`, `food_name`, `food_description`, `food_category`, `num_orders`) VALUES
(1, 'Burger Steak', 'Rice topped with Burger Steak that is super moist and flavorful, with tender beef patties smothered in a rich mushroom gravy.', 'Sulit Deals', 0),
(2, 'Shawarma Rice', 'This Pinoy-style shawarma rice comes with buttery yellow rice, a veggie side salad, and garlic yogurt sauce.', 'Sulit Deals', 0),
(3, 'Steamed Rice', 'Try this homemade dim sum recipe for Cebu City steamed rice, a Filipino favorite that tops fried rice with tasty pork belly and shrimp stew.', 'Sulit Deals', 0),
(4, 'TapSilog', 'Sweet-salty peppery beef, crunchy garlic rice, and a runny fried egg make this Filipino breakfast perfect for any meal of the day.', 'Sulit Deals', 0),
(5, 'HotSilog', 'A meal composed of hotdogs, garlic fried rice, and fried egg. In a Filipino household, this is commonly eaten for breakfast.', 'Sulit Deals', 0),
(6, 'CornSilog', 'A meal composed of sautéed corned beef with onion, garlic fried rice, and fried egg. The corned beef used are the ones in can.', 'Sulit Deals', 0),
(7, 'TociLog', 'A meal composed of sweet tocino, garlic fried rice, and fried egg. It\'s a classic Filipino combo that\'s both flavorful', 'Sulit Deals', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tblorders`
--

CREATE TABLE `tblorders` (
  `order_id` int NOT NULL,
  `user_id` int NOT NULL,
  `food_id` int NOT NULL,
  `pick` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tblusers`
--

CREATE TABLE `tblusers` (
  `id` int NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tblusers`
--

INSERT INTO `tblusers` (`id`, `firstname`, `lastname`, `username`, `password`) VALUES
(1, 'Nicole', 'Ejares', 'nicole', 'chickenshots'),
(2, 'Jeremy', 'Lee', 'jirimi', 'burgersteak');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblfood`
--
ALTER TABLE `tblfood`
  ADD PRIMARY KEY (`food_id`);

--
-- Indexes for table `tblusers`
--
ALTER TABLE `tblusers`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblfood`
--
ALTER TABLE `tblfood`
  MODIFY `food_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tblusers`
--
ALTER TABLE `tblusers`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
