-- =============================================
-- QuanLyDienThoai Database Schema
-- Phone Store Inventory Management System
-- Last Updated: January 17, 2026
-- =============================================

CREATE DATABASE IF NOT EXISTS `quanlydienthoai`;
USE `quanlydienthoai`;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

-- =============================================
-- Table: users
-- =============================================
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `location` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(10) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `usertype` VARCHAR(45) NOT NULL,
  `email` VARCHAR(50) DEFAULT NULL,
  `status` INT DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES
(17, 'Administrator', 'Ho Chi Minh City', '0901234567', 'admin', 'admin', 'ADMIN', 'admin@email.com', 1);
UNLOCK TABLES;

-- =============================================
-- Table: suppliers
-- =============================================
DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE `suppliers` (
  `sid` INT NOT NULL AUTO_INCREMENT,
  `suppliercode` VARCHAR(45) NOT NULL,
  `fullname` VARCHAR(100) NOT NULL,
  `location` VARCHAR(150) NOT NULL,
  `mobile` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`sid`),
  UNIQUE KEY `suppliercode_UNIQUE` (`suppliercode`)
) ENGINE=InnoDB AUTO_INCREMENT=409 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `suppliers` WRITE;
INSERT INTO `suppliers` VALUES
(401, 'FPT', 'Công Ty Cổ Phần Bán Lẻ Kỹ Thuật Số FPT', '261-263 Khánh Hội, P2, Q4, TP.HCM', '02873023456'),
(402, 'TGDD', 'Công ty cổ phần Thế Giới Di Động', '128 Trần Quang Khải, P.Tân Định, Q.1, TP.HCM', '02838125960'),
(403, 'CELLPHONES', 'Công ty TNHH Thương Mại Cellphones', '419 Võ Văn Tần, Phường 5, Quận 3, TP.HCM', '02873006688'),
(404, 'PHONGVU', 'Công ty cổ phần dịch vụ - thương mại Phong Vũ', 'Tầng 5, 117-119-121 Nguyễn Du, Q.1, TP.HCM', '0967567567'),
(405, 'HOANGHA', 'Công ty Cổ phần Thương mại Hoàng Hà', '128 Trần Phú, P.Văn Quán, Q.Hà Đông, Hà Nội', '1900 1903'),
(406, 'DIDONGVIET', 'Công ty TNHH Di Động Việt', '25 Trần Hưng Đạo, Quận Hoàn Kiếm, Hà Nội', '1800 6018'),
(407, 'MINHTUONG', 'Công ty TNHH Thương Mại Minh Tường', '77 Nguyễn Huệ, P.Bến Nghé, Q.1, TP.HCM', '02839303456'),
(408, 'VIETTEL', 'Tập đoàn Công nghiệp - Viễn thông Quân đội', '1 Giang Văn Minh, P.Kim Mã, Q.Ba Đình, Hà Nội', '1900 8098');
UNLOCK TABLES;

-- =============================================
-- Table: products
-- =============================================
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `pid` INT NOT NULL AUTO_INCREMENT,
  `productcode` VARCHAR(45) NOT NULL,
  `productname` VARCHAR(100) NOT NULL,
  `costprice` DOUBLE NOT NULL,
  `sellprice` DOUBLE NOT NULL,
  `brand` VARCHAR(45) NOT NULL,
  `battery` VARCHAR(50) DEFAULT NULL,
  `ram` VARCHAR(50) DEFAULT NULL,
  `storage` VARCHAR(50) DEFAULT NULL,
  `screensize` DOUBLE DEFAULT NULL,
  `maincamera` VARCHAR(50) DEFAULT NULL,
  `frontcamera` VARCHAR(50) DEFAULT NULL,
  `processor` VARCHAR(50) DEFAULT NULL,
  `os` VARCHAR(50) DEFAULT NULL,
  `origin` VARCHAR(50) DEFAULT NULL,
  `status` INT DEFAULT 1,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `productcode_UNIQUE` (`productcode`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `products` WRITE;
INSERT INTO `products` VALUES
(111, 'IP13', 'iPhone 13 128GB', 18000000, 19990000, 'Apple', '3240 mAh', '4 GB', '128 GB', 6.1, '12 MP', '12 MP', 'Apple A15 Bionic', 'iOS 15', 'China', 1),
(112, 'IP13PM', 'iPhone 13 Pro Max 256GB', 24000000, 25990000, 'Apple', '4352 mAh', '6 GB', '256 GB', 6.7, '12 MP', '12 MP', 'Apple A15 Bionic', 'iOS 15', 'China', 1),
(113, 'IP14', 'iPhone 14 128GB', 21000000, 22990000, 'Apple', '3279 mAh', '6 GB', '128 GB', 6.1, '12 MP', '12 MP', 'Apple A15 Bionic', 'iOS 16', 'China', 1),
(114, 'IP14P', 'iPhone 14 Plus 128GB', 26000000, 27990000, 'Apple', '4325 mAh', '6 GB', '128 GB', 6.7, '12 MP', '12 MP', 'Apple A15 Bionic', 'iOS 16', 'China', 1),
(115, 'IP14PM', 'iPhone 14 Pro Max 256GB', 28000000, 29990000, 'Apple', '4323 mAh', '6 GB', '256 GB', 6.7, '48 MP', '12 MP', 'Apple A16 Bionic', 'iOS 16', 'China', 1),
(116, 'SS22P', 'Samsung Galaxy S22 Plus 5G', 20000000, 21990000, 'Samsung', '4500 mAh', '8 GB', '256 GB', 6.6, '50 MP', '10 MP', 'Snapdragon 8 Gen 1', 'Android 12', 'Vietnam', 1),
(117, 'SS22U', 'Samsung Galaxy S22 Ultra 5G', 23000000, 24990000, 'Samsung', '5000 mAh', '12 GB', '256 GB', 6.8, '108 MP', '40 MP', 'Snapdragon 8 Gen 1', 'Android 12', 'Vietnam', 1),
(118, 'SS23', 'Samsung Galaxy S23 5G', 17500000, 18990000, 'Samsung', '3900 mAh', '8 GB', '128 GB', 6.1, '50 MP', '12 MP', 'Snapdragon 8 Gen 2', 'Android 13', 'Vietnam', 1),
(119, 'SS23U', 'Samsung Galaxy S23 Ultra 5G', 25000000, 26990000, 'Samsung', '5000 mAh', '12 GB', '256 GB', 6.8, '200 MP', '12 MP', 'Snapdragon 8 Gen 2', 'Android 13', 'Vietnam', 1),
(120, 'XM12', 'Xiaomi 12', 4500000, 4990000, 'Xiaomi', '4500 mAh', '8 GB', '128 GB', 6.28, '50 MP', '32 MP', 'Snapdragon 8 Gen 1', 'Android 12', 'China', 1),
(121, 'XM13P', 'Xiaomi 13 Pro', 11000000, 11990000, 'Xiaomi', '4820 mAh', '12 GB', '256 GB', 6.73, '50 MP', '32 MP', 'Snapdragon 8 Gen 2', 'Android 13', 'China', 1),
(122, 'XM13T', 'Xiaomi 13T', 6500000, 6990000, 'Xiaomi', '5000 mAh', '8 GB', '256 GB', 6.67, '50 MP', '20 MP', 'Dimensity 8200-Ultra', 'Android 13', 'China', 1),
(123, 'RN12P', 'Redmi Note 12 Pro 5G', 7500000, 7990000, 'Xiaomi', '5000 mAh', '8 GB', '256 GB', 6.67, '50 MP', '16 MP', 'Snapdragon 4 Gen 1', 'Android 12', 'China', 1),
(124, 'OP10P', 'OPPO Reno10 Pro+ 5G', 9200000, 9990000, 'OPPO', '4700 mAh', '12 GB', '256 GB', 6.74, '50 MP', '32 MP', 'Snapdragon 8+ Gen 1', 'Android 13', 'China', 1),
(125, 'OP11', 'OPPO Find X5 Pro 5G', 12000000, 12990000, 'OPPO', '5000 mAh', '12 GB', '256 GB', 6.7, '50 MP', '32 MP', 'Snapdragon 8 Gen 1', 'Android 12', 'China', 1),
(126, 'VV25', 'Vivo V25 5G', 8300000, 8990000, 'Vivo', '4500 mAh', '8 GB', '128 GB', 6.44, '64 MP', '50 MP', 'Dimensity 900', 'Android 12', 'China', 1),
(127, 'HN90P', 'Honor 90 Pro', 23000000, 24990000, 'Honor', '5000 mAh', '12 GB', '256 GB', 6.78, '200 MP', '50 MP', 'Snapdragon 8+ Gen 1', 'Android 13', 'China', 1),
(128, 'HNM6P', 'Honor Magic 6 Pro', 17500000, 18990000, 'Honor', '5600 mAh', '12 GB', '512 GB', 6.8, '50 MP', '50 MP', 'Snapdragon 8 Gen 3', 'Android 14', 'China', 1),
(129, 'SX5V', 'Sony Xperia 5 V', 30000000, 31990000, 'Sony', '5000 mAh', '8 GB', '128 GB', 6.1, '48 MP', '12 MP', 'Snapdragon 8 Gen 2', 'Android 13', 'Japan', 1);
UNLOCK TABLES;

-- =============================================
-- Table: currentstock
-- =============================================
DROP TABLE IF EXISTS `currentstock`;
CREATE TABLE `currentstock` (
  `productcode` VARCHAR(45) NOT NULL,
  `quantity` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`productcode`),
  CONSTRAINT `fk_stock_product` FOREIGN KEY (`productcode`) REFERENCES `products` (`productcode`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `currentstock` WRITE;
INSERT INTO `currentstock` VALUES
('IP13', 55),
('IP13PM', 48),
('IP14', 65),
('IP14P', 52),
('IP14PM', 42),
('SS22P', 44),
('SS22U', 38),
('SS23', 72),
('SS23U', 58),
('XM12', 98),
('XM13P', 77),
('XM13T', 88),
('RN12P', 82),
('OP10P', 38),
('OP11', 42),
('VV25', 55),
('HN90P', 55),
('HNM6P', 60),
('SX5V', 50);
UNLOCK TABLES;

-- =============================================
-- Table: purchaseinfo
-- =============================================
DROP TABLE IF EXISTS `purchaseinfo`;
CREATE TABLE `purchaseinfo` (
  `purchaseid` INT NOT NULL AUTO_INCREMENT,
  `suppliercode` VARCHAR(45) NOT NULL,
  `productcode` VARCHAR(45) NOT NULL,
  `date` VARCHAR(45) NOT NULL,
  `quantity` INT NOT NULL,
  `costprice` DOUBLE NOT NULL,
  `totalcost` DOUBLE NOT NULL,
  `purchasedby` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`purchaseid`),
  KEY `fk_purchase_supplier` (`suppliercode`),
  KEY `fk_purchase_product` (`productcode`)
) ENGINE=InnoDB AUTO_INCREMENT=1012 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `purchaseinfo` WRITE;
INSERT INTO `purchaseinfo` VALUES
(1001, 'FPT', 'IP14PM', 'Wed Jan 02 10:15:00 ICT 2026', 10, 28000000, 280000000, 'admin'),
(1002, 'FPT', 'SS23U', 'Wed Jan 02 10:15:00 ICT 2026', 15, 25000000, 375000000, 'admin'),
(1003, 'TGDD', 'IP14', 'Thu Jan 03 09:30:00 ICT 2026', 20, 21000000, 420000000, 'admin'),
(1004, 'TGDD', 'SS23', 'Thu Jan 03 09:30:00 ICT 2026', 25, 17500000, 437500000, 'admin'),
(1005, 'FPT', 'IP13PM', 'Fri Jan 04 14:20:00 ICT 2026', 12, 24000000, 288000000, 'admin'),
(1006, 'PHONGVU', 'XM13P', 'Fri Jan 04 14:20:00 ICT 2026', 30, 11000000, 330000000, 'admin'),
(1007, 'CELLPHONES', 'OP11', 'Sat Jan 05 11:45:00 ICT 2026', 20, 12000000, 240000000, 'admin'),
(1008, 'CELLPHONES', 'RN12P', 'Sat Jan 05 11:45:00 ICT 2026', 35, 7500000, 262500000, 'admin'),
(1009, 'HOANGHA', 'SS22U', 'Sun Jan 06 16:00:00 ICT 2026', 10, 23000000, 230000000, 'admin'),
(1010, 'HOANGHA', 'IP14P', 'Sun Jan 06 16:00:00 ICT 2026', 15, 26000000, 390000000, 'admin'),
(1011, 'DIDONGVIET', 'XM13T', 'Mon Jan 07 13:30:00 ICT 2026', 40, 6500000, 260000000, 'admin'),
(1012, 'PHONGVU', 'VV25', 'Tue Jan 08 10:10:00 ICT 2026', 25, 8300000, 207500000, 'admin'),
(1013, 'FPT', 'IP13', 'Wed Jan 09 15:20:00 ICT 2026', 18, 18000000, 324000000, 'admin'),
(1014, 'TGDD', 'XM12', 'Thu Jan 10 12:00:00 ICT 2026', 45, 4500000, 202500000, 'admin'),
(1015, 'CELLPHONES', 'HN90P', 'Fri Jan 11 14:30:00 ICT 2026', 10, 23000000, 230000000, 'admin');
UNLOCK TABLES;

-- =============================================
-- Table: customers
-- =============================================
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `cid` INT NOT NULL AUTO_INCREMENT,
  `customercode` VARCHAR(45) NOT NULL,
  `fullname` VARCHAR(100) NOT NULL,
  `location` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`cid`),
  UNIQUE KEY `customercode_UNIQUE` (`customercode`)
) ENGINE=InnoDB AUTO_INCREMENT=311 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `customers` WRITE;
INSERT INTO `customers` VALUES
(301, 'CUST001', 'Nguyễn Văn An', 'Quận 1, TP.HCM', '0901234567'),
(302, 'CUST002', 'Trần Thị Bình', 'Quận 3, TP.HCM', '0912345678'),
(303, 'CUST003', 'Lê Hoàng Cường', 'Quận 5, TP.HCM', '0923456789'),
(304, 'CUST004', 'Phạm Thị Dung', 'Quận 10, TP.HCM', '0934567890'),
(305, 'CUST005', 'Hoàng Văn Em', 'Quận Bình Thạnh, TP.HCM', '0945678901'),
(306, 'CUST006', 'Võ Thị Phượng', 'Quận Tân Bình, TP.HCM', '0956789012'),
(307, 'CUST007', 'Trương Trí Tín', 'Quận 7, TP.HCM', '0967890123'),
(308, 'CUST008', 'Trần Hoàn Vũ', 'Quận Phú Nhuận, TP.HCM', '0978901234'),
(309, 'CUST009', 'Lưu Thị Trúc Thảo', 'Quận Gò Vấp, TP.HCM', '0989012345'),
(310, 'CUST010', 'Võ Thị Lê Phương', 'Quận Thủ Đức, TP.HCM', '0990123456');
UNLOCK TABLES;

-- =============================================
-- Table: salesinfo
-- =============================================
DROP TABLE IF EXISTS `salesinfo`;
CREATE TABLE `salesinfo` (
  `salesid` INT NOT NULL AUTO_INCREMENT,
  `date` VARCHAR(45) NOT NULL,
  `productcode` VARCHAR(45) NOT NULL,
  `customercode` VARCHAR(45) NOT NULL,
  `quantity` INT NOT NULL,
  `sellprice` DOUBLE NOT NULL,
  `revenue` DOUBLE NOT NULL,
  `soldby` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`salesid`),
  KEY `fk_sales_product` (`productcode`),
  KEY `fk_sales_customer` (`customercode`)
) ENGINE=InnoDB AUTO_INCREMENT=2013 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `salesinfo` WRITE;
INSERT INTO `salesinfo` VALUES
(2001, 'Wed Jan 02 15:30:00 ICT 2026', 'IP14PM', 'CUST001', 5, 29990000, 149950000, 'admin'),
(2002, 'Wed Jan 02 15:30:00 ICT 2026', 'SS23U', 'CUST001', 8, 26990000, 215920000, 'admin'),
(2003, 'Thu Jan 03 10:20:00 ICT 2026', 'IP14', 'CUST002', 10, 22990000, 229900000, 'admin'),
(2004, 'Thu Jan 03 10:20:00 ICT 2026', 'SS23', 'CUST002', 12, 18990000, 227880000, 'admin'),
(2005, 'Fri Jan 04 14:15:00 ICT 2026', 'IP13PM', 'CUST003', 6, 25990000, 155940000, 'admin'),
(2006, 'Fri Jan 04 14:15:00 ICT 2026', 'XM13P', 'CUST003', 15, 11990000, 179850000, 'admin'),
(2007, 'Sat Jan 05 11:30:00 ICT 2026', 'OP11', 'CUST004', 10, 12990000, 129900000, 'admin'),
(2008, 'Sat Jan 05 11:30:00 ICT 2026', 'RN12P', 'CUST004', 18, 7990000, 143820000, 'admin'),
(2009, 'Sun Jan 06 16:45:00 ICT 2026', 'SS22U', 'CUST005', 5, 24990000, 124950000, 'admin'),
(2010, 'Sun Jan 06 16:45:00 ICT 2026', 'IP14P', 'CUST005', 7, 27990000, 195930000, 'admin'),
(2011, 'Mon Jan 07 13:25:00 ICT 2026', 'XM13T', 'CUST006', 20, 6990000, 139800000, 'admin'),
(2012, 'Tue Jan 08 15:40:00 ICT 2026', 'VV25', 'CUST001', 12, 8990000, 107880000, 'admin'),
(2013, 'Wed Jan 09 12:20:00 ICT 2026', 'IP13', 'CUST002', 9, 19990000, 179910000, 'admin'),
(2014, 'Thu Jan 10 14:35:00 ICT 2026', 'XM12', 'CUST003', 25, 4990000, 124750000, 'admin'),
(2015, 'Fri Jan 11 10:15:00 ICT 2026', 'HN90P', 'CUST004', 6, 24990000, 149940000, 'admin');
UNLOCK TABLES;
