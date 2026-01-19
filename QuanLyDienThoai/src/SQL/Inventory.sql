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
  `user_type` VARCHAR(45) NOT NULL,
  `email` VARCHAR(50) DEFAULT NULL,
  `status` INT DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES
(17, 'Administrator', 'Ho Chi Minh City', '0901234567', 'admin', 'admin', 'ADMIN', 'admin@email.com', 1),
(18, 'Nguyễn Văn Bán', 'Ho Chi Minh City', '0912345678', 'saler', 'saler', 'SALER', 'saler@email.com', 1),
(19, 'Trần Thị Nhập', 'Ho Chi Minh City', '0923456789', 'purchaser', 'purchaser', 'PURCHASER', 'purchaser@email.com', 1);
UNLOCK TABLES;

-- =============================================
-- Table: suppliers
-- =============================================
DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE `suppliers` (
  `sid` INT NOT NULL AUTO_INCREMENT,
  `supplier_code` VARCHAR(45) NOT NULL,
  `full_name` VARCHAR(100) NOT NULL,
  `location` VARCHAR(150) NOT NULL,
  `mobile` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`sid`),
  UNIQUE KEY `supplier_code_UNIQUE` (`supplier_code`)
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
  `product_code` VARCHAR(45) NOT NULL,
  `product_name` VARCHAR(100) NOT NULL,
  `ram` VARCHAR(20) NOT NULL,
  `rom` VARCHAR(20) NOT NULL,
  `screen_size` VARCHAR(20) NOT NULL,
  `brand` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `product_code_UNIQUE` (`product_code`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `products` WRITE;
INSERT INTO `products` VALUES
(111, 'IP13', 'iPhone 13 128GB', '4GB', '128GB', '6.1 inch', 'Apple'),
(112, 'IP13PM', 'iPhone 13 Pro Max 256GB', '6GB', '256GB', '6.7 inch', 'Apple'),
(113, 'IP14', 'iPhone 14 128GB', '6GB', '128GB', '6.1 inch', 'Apple'),
(114, 'IP14P', 'iPhone 14 Plus 128GB', '6GB', '128GB', '6.7 inch', 'Apple'),
(115, 'IP14PM', 'iPhone 14 Pro Max 256GB', '6GB', '256GB', '6.7 inch', 'Apple'),
(116, 'SS22P', 'Samsung Galaxy S22 Plus 5G', '8GB', '128GB', '6.6 inch', 'Samsung'),
(117, 'SS22U', 'Samsung Galaxy S22 Ultra 5G', '12GB', '256GB', '6.8 inch', 'Samsung'),
(118, 'SS23', 'Samsung Galaxy S23 5G', '8GB', '128GB', '6.1 inch', 'Samsung'),
(119, 'SS23U', 'Samsung Galaxy S23 Ultra 5G', '12GB', '256GB', '6.8 inch', 'Samsung'),
(120, 'XM12', 'Xiaomi 12', '8GB', '128GB', '6.28 inch', 'Xiaomi'),
(121, 'XM13P', 'Xiaomi 13 Pro', '12GB', '256GB', '6.73 inch', 'Xiaomi'),
(122, 'XM13T', 'Xiaomi 13T', '8GB', '256GB', '6.67 inch', 'Xiaomi'),
(123, 'RN12P', 'Redmi Note 12 Pro 5G', '8GB', '128GB', '6.67 inch', 'Xiaomi'),
(124, 'OP10P', 'OPPO Reno10 Pro+ 5G', '12GB', '256GB', '6.74 inch', 'OPPO'),
(125, 'OP11', 'OPPO Find X5 Pro 5G', '12GB', '256GB', '6.7 inch', 'OPPO'),
(126, 'VV25', 'Vivo V25 5G', '8GB', '128GB', '6.44 inch', 'Vivo'),
(127, 'HN90P', 'Honor 90 Pro', '12GB', '256GB', '6.78 inch', 'Honor'),
(128, 'HNM6P', 'Honor Magic 6 Pro', '12GB', '512GB', '6.8 inch', 'Honor'),
(129, 'SX5V', 'Sony Xperia 5 V', '8GB', '128GB', '6.1 inch', 'Sony');
UNLOCK TABLES;

-- =============================================
-- Table: inventory
-- =============================================
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory` (
  `product_code` VARCHAR(45) NOT NULL,
  `quantity` INT NOT NULL DEFAULT 0,
  `cost_price` DOUBLE NOT NULL DEFAULT 0,
  PRIMARY KEY (`product_code`),
  CONSTRAINT `fk_inventory_product` FOREIGN KEY (`product_code`) REFERENCES `products` (`product_code`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `inventory` WRITE;
INSERT INTO `inventory` VALUES
('IP13', 55, 18000000),
('IP13PM', 48, 24000000),
('IP14', 65, 21000000),
('IP14P', 52, 26000000),
('IP14PM', 42, 28000000),
('SS22P', 44, 20000000),
('SS22U', 38, 23000000),
('SS23', 72, 17500000),
('SS23U', 58, 25000000),
('XM12', 98, 4500000),
('XM13P', 77, 11000000),
('XM13T', 88, 6500000),
('RN12P', 82, 7500000),
('OP10P', 38, 10000000),
('OP11', 42, 12000000),
('VV25', 55, 8300000),
('HN90P', 55, 23000000),
('HNM6P', 60, 22000000),
('SX5V', 50, 24000000);
UNLOCK TABLES;

-- =============================================
-- Table: purchase_info
-- =============================================
DROP TABLE IF EXISTS `purchase_info`;
CREATE TABLE `purchase_info` (
  `purchase_id` INT NOT NULL AUTO_INCREMENT,
  `supplier_code` VARCHAR(45) NOT NULL,
  `product_code` VARCHAR(45) NOT NULL,
  `date` VARCHAR(45) NOT NULL,
  `quantity` INT NOT NULL,
  `cost_price` DOUBLE NOT NULL,
  `total_cost` DOUBLE NOT NULL,
  `purchased_by` VARCHAR(45) NOT NULL DEFAULT 'admin',
  PRIMARY KEY (`purchase_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1016 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `purchase_info` WRITE;
INSERT INTO `purchase_info` VALUES
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
  `customer_code` VARCHAR(45) NOT NULL,
  `full_name` VARCHAR(100) NOT NULL,
  `location` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`cid`),
  UNIQUE KEY `customer_code_UNIQUE` (`customer_code`)
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
-- Table: sales_info
-- =============================================
DROP TABLE IF EXISTS `sales_info`;
CREATE TABLE `sales_info` (
  `sales_id` INT NOT NULL AUTO_INCREMENT,
  `date` VARCHAR(45) NOT NULL,
  `product_code` VARCHAR(45) NOT NULL,
  `customer_code` VARCHAR(45) NOT NULL,
  `quantity` INT NOT NULL,
  `sell_price` DOUBLE NOT NULL,
  `revenue` DOUBLE NOT NULL,
  `sold_by` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`sales_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2013 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `sales_info` WRITE;
INSERT INTO `sales_info` VALUES
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
