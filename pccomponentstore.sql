-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 21, 2023 at 10:30 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pccomponentstore`
--

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `ID` int(11) NOT NULL,
  `userID` int(11) DEFAULT NULL,
  `customer_name` varchar(200) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `note` varchar(1000) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `discount` int(11) DEFAULT 0,
  `totalAfterDiscount` int(11) DEFAULT NULL,
  `created_on` datetime DEFAULT current_timestamp(),
  `end_on` datetime DEFAULT NULL,
  `reason` varchar(200) DEFAULT NULL,
  `statusID` int(11) NOT NULL,
  `is_calculated` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bills`
--

INSERT INTO `bills` (`ID`, `userID`, `customer_name`, `email`, `phone`, `address`, `note`, `total`, `discount`, `totalAfterDiscount`, `created_on`, `end_on`, `reason`, `statusID`, `is_calculated`) VALUES
(13, 42, 'Trần Quốc Anh', 'thedarknight968', '478923', 'Đại Mỗ', '', 21795866, 0, 21795866, '2023-12-21 03:39:27', NULL, NULL, 1, 0),
(14, 42, 'Nguyên Văn A', 'nguyenvana@gmail.com', '8374622991', 'Hồ Chí Minh', '', 8195444, 0, 8195444, '2023-12-21 04:45:03', NULL, NULL, 2, 0),
(15, 37, 'Staff', 'staff@gmail.com', '0936245722', 'Triều Khúc, Hà Nội', '', 26490000, 0, 26490000, '2023-12-21 08:09:29', NULL, NULL, 2, 0),
(16, 37, 'Test tên', 'theda@gmail.com', '8345726334', 'Hà Nội', '', 10000222, 0, 10000222, '2023-12-21 18:26:31', NULL, NULL, 8, 0),
(17, 37, 'Trần Nam Em', 'thedarknight968@gmail.com', '29834762', 'Hà Nội', '', 2000111, 0, 2000111, '2023-12-21 18:28:36', NULL, NULL, 7, 0),
(18, 37, 'Trần Quốc Anh', 'thedarknight968', '0839396226', 'Hà Nội', '', 27240000, 0, 27240000, '2023-12-22 00:10:00', '2023-12-22 00:10:00', NULL, 2, 1),
(20, 33, '', '', '', '', '', 3500777, 0, 3500777, '2023-12-22 04:20:00', '2023-12-22 04:20:00', NULL, 8, 1);

--
-- Triggers `bills`
--
DELIMITER $$
CREATE TRIGGER `update_end_on` BEFORE UPDATE ON `bills` FOR EACH ROW BEGIN
   IF NEW.statusID = 2 OR NEW.statusID = 7 OR NEW.statusID = 8 THEN
      SET NEW.end_on = CURRENT_TIMESTAMP;
   END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `bill_product`
--

CREATE TABLE `bill_product` (
  `ID` int(11) NOT NULL,
  `billID` int(11) NOT NULL,
  `productID` int(11) DEFAULT NULL,
  `productname` varchar(500) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bill_product`
--

INSERT INTO `bill_product` (`ID`, `billID`, `productID`, `productname`, `quantity`, `price`, `total`) VALUES
(1, 13, 6, NULL, 5, 1500000, 7500000),
(2, 13, NULL, NULL, 1, 4535533, 4535533),
(3, 13, 61, NULL, 4, 2065000, 8260000),
(5, 14, 4, NULL, 4, 500111, 2000444),
(6, 14, 61, NULL, 3, 2065000, 6195000),
(7, 15, 1, NULL, 1, 24990000, 24990000),
(8, 15, 6, NULL, 1, 1500000, 1500000),
(9, 16, 4, NULL, 2, 500111, 1000222),
(10, 16, 6, NULL, 6, 1500000, 9000000),
(11, 17, 4, NULL, 1, 500111, 500111),
(12, 17, 6, NULL, 1, 1500000, 1500000),
(13, 18, 1, 'Bàn phím Corsair K70 PRO 1', 1, 24990000, 24990000),
(14, 18, 6, 'Nguồn Antec 650W CST 80 Bronze ', 1, 1500000, 1500000),
(15, 18, NULL, 'Bàn Phím Cơ DareU EK1280s V2', 1, 750000, 750000),
(18, 20, 4, 'Chuột Fuhlen 502 ', 7, 500111, 3500777);

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `ID` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`ID`, `total`) VALUES
(1, 0),
(2, 3565000),
(3, 0),
(4, 0),
(5, 0),
(6, 0),
(7, 0),
(10, 0),
(11, 38455882),
(12, 0);

-- --------------------------------------------------------

--
-- Table structure for table `cart_product`
--

CREATE TABLE `cart_product` (
  `ID` int(11) NOT NULL,
  `cartID` int(11) DEFAULT NULL,
  `productID` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart_product`
--

INSERT INTO `cart_product` (`ID`, `cartID`, `productID`, `quantity`, `total`) VALUES
(17, 11, 6, 3, 4500000),
(22, 11, 1, 1, 24990000),
(25, 11, 4, 4, 2000444),
(40, 2, 61, 1, 2065000),
(41, 2, 6, 1, 1500000);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `ID` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `seo` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`ID`, `name`, `seo`) VALUES
(1, 'Chuột', 'chuot'),
(2, 'Bàn phím', 'ban-phim'),
(3, 'Màn hình', 'man-hinh'),
(4, 'CPU', 'cpu'),
(5, 'RAM', 'ram'),
(6, 'Mainboard', 'mainboard'),
(7, 'PSU', 'psu'),
(8, 'Tai nghe', 'tai-nghe'),
(9, 'Micro', 'micro'),
(11, 'USB', 'usb'),
(15, 'Laptop', 'laptop'),
(16, 'Cấy máy tính', 'cay-may-tinh');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `ID` int(11) NOT NULL,
  `userID` int(11) DEFAULT NULL,
  `productID` int(11) DEFAULT NULL,
  `comment` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `manufactory`
--

CREATE TABLE `manufactory` (
  `ID` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `seo` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `manufactory`
--

INSERT INTO `manufactory` (`ID`, `name`, `seo`) VALUES
(1, 'Asus', 'asus'),
(2, 'Gigabyte', ''),
(3, 'MSI', ''),
(4, 'AMD', ''),
(5, 'Asrock', ''),
(6, 'Colorful', ''),
(14, 'Corsair', ''),
(15, 'Antec', ''),
(17, 'Apple', ''),
(18, 'Logitech', ''),
(19, 'Razer', ''),
(20, 'E-dra', ''),
(21, 'Kingston', ''),
(31, 'Hãng test 1', ''),
(32, 'Hãng test 7', ''),
(33, 'testing456', 'testing456'),
(34, 'HP', 'hp');

-- --------------------------------------------------------

--
-- Table structure for table `news`
--

CREATE TABLE `news` (
  `ID` int(11) NOT NULL,
  `title` varchar(200) DEFAULT NULL,
  `content` longtext DEFAULT NULL,
  `created` datetime(6) DEFAULT NULL,
  `seo` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `productimages`
--

CREATE TABLE `productimages` (
  `ID` int(11) NOT NULL,
  `productID` int(11) NOT NULL,
  `imageurl` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `productimages`
--

INSERT INTO `productimages` (`ID`, `productID`, `imageurl`) VALUES
(4, 1, '/productimages/d48a157a09dfe951f2b8a7fd57184855.jpg'),
(5, 1, '/productimages/328240132_5808564982545410_7739918474581760308_n.jpg'),
(6, 1, '/productimages/8ac8b4991f66c1389877.jpg'),
(7, 1, '/productimages/866172748962725909.png'),
(13, 4, '/productimages/IMG_4774.jpg'),
(14, 6, '/productimages/387c040edca010b3ae9b97cce3a49cd4.png'),
(15, 61, '/productimages/967732165334958120.png'),
(26, 1, '/productimages/Untitled-3.png'),
(27, 1, '/productimages/image_2023-12-21_042402379.png'),
(28, 4, '/productimages/image_2023-12-21_042443140.png'),
(29, 6, '/productimages/image_2023-12-21_042500780.png'),
(30, 61, '/productimages/image_2023-12-21_042521029.png');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `ID` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `categoryID` int(11) NOT NULL,
  `manufactoryID` int(11) NOT NULL,
  `description` mediumtext DEFAULT NULL,
  `detail_des` longtext DEFAULT NULL,
  `amount` int(11) NOT NULL,
  `quality` varchar(50) NOT NULL DEFAULT 'Còn mới',
  `price` int(11) NOT NULL,
  `discount` int(11) NOT NULL DEFAULT 0,
  `guarantee` int(11) NOT NULL DEFAULT 12,
  `buyed` int(11) DEFAULT 0,
  `viewed` int(11) DEFAULT 0,
  `rated_total` int(11) DEFAULT 0,
  `rated_count` decimal(2,1) DEFAULT 0.0,
  `created` datetime DEFAULT current_timestamp(),
  `seo` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`ID`, `name`, `categoryID`, `manufactoryID`, `description`, `detail_des`, `amount`, `quality`, `price`, `discount`, `guarantee`, `buyed`, `viewed`, `rated_total`, `rated_count`, `created`, `seo`) VALUES
(1, 'Bàn phím Corsair K70 PRO 1', 8, 21, NULL, NULL, 327, 'Còn mới', 24990000, 0, 121, 0, 8, 0, 0.0, '2023-12-21 18:33:36', 'ban-phim-corsair-k70-pro-1'),
(4, 'Chuột Fuhlen 502 ', 1, 4, NULL, NULL, 120, 'Còn mới', 500111, 0, 12, 0, 11, 0, 0.0, '2023-12-22 01:43:31', 'chuot-fuhlen-502-'),
(6, 'Nguồn Antec 650W CST 80 Bronze ', 7, 15, NULL, NULL, 5, 'Còn mới', 1500000, 0, 12, 0, 11, 0, 0.0, '2023-12-22 04:10:27', 'nguon-antec-650w-cst-80-bronze-'),
(61, 'Laptop Apple có đế tản nhiệt', 15, 17, NULL, NULL, 233, 'Còn mới', 2065000, 0, 0, 0, 5, 0, 0.0, '2023-12-21 05:34:45', 'laptop-apple-co-e-tan-nhiet'),
(77, 'Tessttt', 4, 6, NULL, NULL, 123, 'Còn mới', 123141, 0, 12, 0, 0, 0, 0.0, '2023-12-21 20:09:33', 'tessttt'),
(78, 'Tesstingggg', 2, 5, NULL, NULL, 1234, 'Còn mới', 123133, 0, 12, 0, 1, 0, 0.0, '2023-12-22 03:34:58', 'tesstingggg');

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

CREATE TABLE `report` (
  `id` int(11) NOT NULL,
  `total_income` int(11) NOT NULL DEFAULT 0,
  `month` int(11) NOT NULL,
  `year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `report`
--

INSERT INTO `report` (`id`, `total_income`, `month`, `year`) VALUES
(7, 30740777, 12, 2023);

-- --------------------------------------------------------

--
-- Table structure for table `reportyear`
--

CREATE TABLE `reportyear` (
  `ID` int(11) NOT NULL,
  `total_income` int(11) NOT NULL,
  `year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `ID` int(11) NOT NULL,
  `status` varchar(200) NOT NULL,
  `description` varchar(500) NOT NULL,
  `color` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`ID`, `status`, `description`, `color`) VALUES
(1, 'Chờ xác nhận', 'Đơn hàng chưa xác nhận và đang chờ xác nhận', 'yellow-600'),
(2, 'Đã hủy', 'Bị hủy vì lý do: ', 'red-600'),
(3, 'Đã xác nhận', 'Xác nhận thông tin đơn hàng', 'lime-600'),
(4, 'Đang giao hàng', 'Đơn hàng đang được chuyển đi cho khách hàng', 'orange-600'),
(5, 'Thành công', 'Đơn hàng đã được giao thành công cho khách hàng', 'sky-600'),
(6, 'Thất bại', 'Đơn hàng chưa giao được cho khách hàng.', 'rose-600'),
(7, 'Trả hàng', 'Giao hàng thất bại, hàng đã được trả  về shop', 'teal-600'),
(8, 'Hoàn thành', 'Đơn hàng đã hoàn thành', 'green-600');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `ID` int(11) NOT NULL,
  `username` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `gender` varchar(3) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `avatar` varchar(100) DEFAULT NULL,
  `role` enum('ADMIN','STAFF','CUSTOMER') DEFAULT NULL,
  `join_in` datetime NOT NULL DEFAULT current_timestamp(),
  `cartID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`ID`, `username`, `password`, `name`, `birth`, `gender`, `address`, `email`, `phone`, `avatar`, `role`, `join_in`, `cartID`) VALUES
(32, 'account12', '$2a$10$WqRbsg3EsRrmu6PWC/a1iucNN9uI.45SL8dgAp2.dGUiDTrpkrQj6', 'Tài khoản 12', '2023-12-17', 'Nam', 'Nam Từ Liêm, Hà Nội', 'account12@gmail.com', '0846372996', NULL, 'CUSTOMER', '2023-12-17 11:25:50', 1),
(33, 'admin', '$2a$10$P0d8oL4EOPuBYtjeQWtKnO4Zsk.5mwcpl6RA5ZBAegeHrMNsc4ljm', 'Admin', '2001-02-15', 'Nữ', 'UTT Hà Nội', 'admin@gmail.com', '0673344882', NULL, 'ADMIN', '2023-12-22 01:43:07', 2),
(34, 'account1', '$2a$10$whKaFKLHR8jQbs2bGP2em.5h6twOSvZ07Q9.WLsXUc2kACDLbymlm', 'Tài khoản 1', '2023-12-16', 'Nữ', 'Thủ Đức, Hồ Chí Minh', 'account1@gmail.com', '0846354396', NULL, 'CUSTOMER', '2023-12-17 15:21:56', 3),
(35, 'account2', '$2a$10$sO/JPtdkR46AMCLzfpmNDudI7RgdLgiVq0qbeMOk0fewXaphd6SIu', 'Tài khoản 2', '2023-12-16', 'Nữ', 'Thủ Đức, Hồ Chí Minh', 'account1@gmail.com', '0846354396', NULL, 'CUSTOMER', '2023-12-17 16:02:31', 4),
(36, 'account3', '$2a$10$4qpl1gWbirAENwFid5H28OA7J2PNbNz5xCyALPhGlxO7W7FKCS9wS', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CUSTOMER', '2023-12-17 15:50:08', 5),
(37, 'staff', '$2a$10$gK2iHZZIOm71cBqNn30Oh.Rot5YdxwhaFLCWCRH/w/TVjxU2MMdSS', 'Trần Quốc Anh', '2001-02-15', 'Nam', 'Nam Từ Liêm, Hà Nội', 'thedarknight@gmail.com', '0839398937', NULL, 'STAFF', '2023-12-21 19:15:49', 6),
(41, 'account7', '$2a$10$OMoK4k1/pb3FsFfyl.51r.PKZ5GmiW6WNj6By.INEgNyjcK0IPPjy', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CUSTOMER', '2023-12-19 05:33:36', 10),
(42, 'account8', '$2a$10$hdJd05BBM.dUClt5cAS2LeP801SkXbzKNJ1.X4eE/.hedpH140Ddq', 'Tài khoản 8', '2023-03-31', 'Nữ', 'Hà Nội', 'taikhoan8@gmail.com', '8734452334', NULL, 'CUSTOMER', '2023-12-21 01:30:40', 11),
(43, 'account98', '$2a$10$TQaD6sMca9oPpDSiNoKDfORffQlvC5QuMyDD3IZC9QWNFBn73UR0u', 'TRần Quốc Anh', '2001-02-15', 'Nam', '477, Đại Mỗ', 'ádasd@sdfsdf', '0839396226', '/avatar/328240132_5808564982545410_7739918474581760308_n.jpg', 'CUSTOMER', '2023-12-21 01:18:36', 12);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `statusID` (`statusID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `bill_product`
--
ALTER TABLE `bill_product`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `index2` (`billID`,`productID`) USING BTREE,
  ADD KEY `bills_information_ibfk_2` (`productID`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `cart_product`
--
ALTER TABLE `cart_product`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `productID` (`productID`),
  ADD KEY `cartID` (`cartID`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `productID` (`productID`),
  ADD KEY `userID` (`userID`) USING BTREE;

--
-- Indexes for table `manufactory`
--
ALTER TABLE `manufactory`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `productimages`
--
ALTER TABLE `productimages`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `imageurl` (`imageurl`),
  ADD KEY `productID` (`productID`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `index6` (`categoryID`,`manufactoryID`) USING BTREE,
  ADD KEY `product_ibfk_2` (`manufactoryID`,`categoryID`) USING BTREE;

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reportyear`
--
ALTER TABLE `reportyear`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `unique` (`username`) USING BTREE,
  ADD KEY `cartID` (`cartID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `bill_product`
--
ALTER TABLE `bill_product`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `cart_product`
--
ALTER TABLE `cart_product`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `manufactory`
--
ALTER TABLE `manufactory`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `news`
--
ALTER TABLE `news`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `productimages`
--
ALTER TABLE `productimages`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;

--
-- AUTO_INCREMENT for table `report`
--
ALTER TABLE `report`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `reportyear`
--
ALTER TABLE `reportyear`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bills`
--
ALTER TABLE `bills`
  ADD CONSTRAINT `bills_ibfk_3` FOREIGN KEY (`statusID`) REFERENCES `status` (`ID`),
  ADD CONSTRAINT `bills_ibfk_4` FOREIGN KEY (`userID`) REFERENCES `users` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `bill_product`
--
ALTER TABLE `bill_product`
  ADD CONSTRAINT `bill_product_ibfk_1` FOREIGN KEY (`billID`) REFERENCES `bills` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bill_product_ibfk_2` FOREIGN KEY (`productID`) REFERENCES `products` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `cart_product`
--
ALTER TABLE `cart_product`
  ADD CONSTRAINT `cart_product_ibfk_1` FOREIGN KEY (`productID`) REFERENCES `products` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cart_product_ibfk_2` FOREIGN KEY (`cartID`) REFERENCES `cart` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`productID`) REFERENCES `products` (`ID`),
  ADD CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `users` (`ID`);

--
-- Constraints for table `productimages`
--
ALTER TABLE `productimages`
  ADD CONSTRAINT `productimages_ibfk_1` FOREIGN KEY (`productID`) REFERENCES `products` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`categoryID`) REFERENCES `category` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `products_ibfk_2` FOREIGN KEY (`manufactoryID`) REFERENCES `manufactory` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`cartID`) REFERENCES `cart` (`ID`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
