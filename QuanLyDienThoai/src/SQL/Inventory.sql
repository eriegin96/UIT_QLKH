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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES
(1, 'Administrator', 'Ho Chi Minh City', '0901234567', 'admin', 'kf/ltukXOhZW+FdLWJSaLg==:06vifonfKu+HyD9z23D2xLjgDYC78G6l3G5GMaEtrSg=', 'ADMIN', 'admin@email.com', 1),
(2, 'Nguyễn Văn Bán', 'Ho Chi Minh City', '0912345678', 'saler', 'WDhVE4SVIKdmb30SInRT/A==:rRklryOgBGFQMqg15aLEsh38NTiQsYniRm+rqoiOalc=', 'SALER', 'saler@email.com', 1),
(3, 'Trần Thị Nhập', 'Ho Chi Minh City', '0923456789', 'purchaser', 'awy38gsMxr6fYbvDeaT7gQ==:KFlRZExL/0BYztaDK7VWf6/nb6I/7e624QtoM+WXutw=', 'PURCHASER', 'purchaser@email.com', 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `suppliers` WRITE;
INSERT INTO `suppliers` VALUES
(1, 'FPT', 'Công Ty Cổ Phần Bán Lẻ Kỹ Thuật Số FPT', '261-263 Khánh Hội, P2, Q4, TP.HCM', '02873023456'),
(2, 'TGDD', 'Công ty cổ phần Thế Giới Di Động', '128 Trần Quang Khải, P.Tân Định, Q.1, TP.HCM', '02838125960'),
(3, 'CELLPHONES', 'Công ty TNHH Thương Mại Cellphones', '419 Võ Văn Tần, Phường 5, Quận 3, TP.HCM', '02873006688'),
(4, 'PHONGVU', 'Công ty cổ phần dịch vụ - thương mại Phong Vũ', 'Tầng 5, 117-119-121 Nguyễn Du, Q.1, TP.HCM', '0967567567'),
(5, 'HOANGHA', 'Công ty Cổ phần Thương mại Hoàng Hà', '128 Trần Phú, P.Văn Quán, Q.Hà Đông, Hà Nội', '1900 1903'),
(6, 'DIDONGVIET', 'Công ty TNHH Di Động Việt', '25 Trần Hưng Đạo, Quận Hoàn Kiếm, Hà Nội', '1800 6018'),
(7, 'MINHTUONG', 'Công ty TNHH Thương Mại Minh Tường', '77 Nguyễn Huệ, P.Bến Nghé, Q.1, TP.HCM', '02839303456'),
(8, 'VIETTEL', 'Tập đoàn Công nghiệp - Viễn thông Quân đội', '1 Giang Văn Minh, P.Kim Mã, Q.Ba Đình, Hà Nội', '1900 8098');
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `products` WRITE;
INSERT INTO `products` VALUES
(1, 'IP13', 'iPhone 13 128GB', '4GB', '128GB', '6.1 inch', 'Apple'),
(2, 'IP13PM', 'iPhone 13 Pro Max 256GB', '6GB', '256GB', '6.7 inch', 'Apple'),
(3, 'IP14', 'iPhone 14 128GB', '6GB', '128GB', '6.1 inch', 'Apple'),
(4, 'IP14P', 'iPhone 14 Plus 128GB', '6GB', '128GB', '6.7 inch', 'Apple'),
(5, 'IP14PM', 'iPhone 14 Pro Max 256GB', '6GB', '256GB', '6.7 inch', 'Apple'),
(6, 'SS22P', 'Samsung Galaxy S22 Plus 5G', '8GB', '128GB', '6.6 inch', 'Samsung'),
(7, 'SS22U', 'Samsung Galaxy S22 Ultra 5G', '12GB', '256GB', '6.8 inch', 'Samsung'),
(8, 'SS23', 'Samsung Galaxy S23 5G', '8GB', '128GB', '6.1 inch', 'Samsung'),
(9, 'SS23U', 'Samsung Galaxy S23 Ultra 5G', '12GB', '256GB', '6.8 inch', 'Samsung'),
(10, 'XM12', 'Xiaomi 12', '8GB', '128GB', '6.28 inch', 'Xiaomi'),
(11, 'XM13P', 'Xiaomi 13 Pro', '12GB', '256GB', '6.73 inch', 'Xiaomi'),
(12, 'XM13T', 'Xiaomi 13T', '8GB', '256GB', '6.67 inch', 'Xiaomi'),
(13, 'RN12P', 'Redmi Note 12 Pro 5G', '8GB', '128GB', '6.67 inch', 'Xiaomi'),
(14, 'OP10P', 'OPPO Reno10 Pro+ 5G', '12GB', '256GB', '6.74 inch', 'OPPO'),
(15, 'OP11', 'OPPO Find X5 Pro 5G', '12GB', '256GB', '6.7 inch', 'OPPO'),
(16, 'VV25', 'Vivo V25 5G', '8GB', '128GB', '6.44 inch', 'Vivo'),
(17, 'HN90P', 'Honor 90 Pro', '12GB', '256GB', '6.78 inch', 'Honor'),
(18, 'HNM6P', 'Honor Magic 6 Pro', '12GB', '512GB', '6.8 inch', 'Honor'),
(19, 'SX5V', 'Sony Xperia 5 V', '8GB', '128GB', '6.1 inch', 'Sony');
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
  `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `quantity` INT NOT NULL,
  `cost_price` DOUBLE NOT NULL,
  `total_cost` DOUBLE NOT NULL,
  `purchased_by` VARCHAR(45) NOT NULL DEFAULT 'admin',
  PRIMARY KEY (`purchase_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `purchase_info` WRITE;
INSERT INTO `purchase_info` VALUES
(1, 'FPT', 'IP14PM', '2025-01-02 10:15:00', 10, 28000000, 280000000, 'admin'),
(2, 'FPT', 'SS23U', '2025-01-02 10:15:00', 15, 25000000, 375000000, 'admin'),
(3, 'TGDD', 'IP14', '2025-01-03 09:30:00', 20, 21000000, 420000000, 'admin'),
(4, 'TGDD', 'SS23', '2025-01-03 09:30:00', 25, 17500000, 437500000, 'admin'),
(5, 'FPT', 'IP13PM', '2025-01-04 14:20:00', 12, 24000000, 288000000, 'admin'),
(6, 'PHONGVU', 'XM13P', '2025-01-04 14:20:00', 30, 11000000, 330000000, 'admin'),
(7, 'CELLPHONES', 'OP11', '2025-01-05 11:45:00', 20, 12000000, 240000000, 'admin'),
(8, 'CELLPHONES', 'RN12P', '2025-01-05 11:45:00', 35, 7500000, 262500000, 'admin'),
(9, 'HOANGHA', 'SS22U', '2025-01-06 16:00:00', 10, 23000000, 230000000, 'admin'),
(10, 'HOANGHA', 'IP14P', '2025-01-06 16:00:00', 15, 26000000, 390000000, 'admin'),
(11, 'DIDONGVIET', 'XM13T', '2025-01-07 13:30:00', 40, 6500000, 260000000, 'admin'),
(12, 'PHONGVU', 'VV25', '2025-01-08 10:10:00', 25, 8300000, 207500000, 'admin'),
(13, 'FPT', 'IP13', '2025-01-09 15:20:00', 18, 18000000, 324000000, 'admin'),
(14, 'TGDD', 'XM12', '2025-01-10 12:00:00', 45, 4500000, 202500000, 'admin'),
(15, 'CELLPHONES', 'HN90P', '2025-01-11 14:30:00', 10, 23000000, 230000000, 'admin');
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `customers` WRITE;
INSERT INTO `customers` VALUES
(1, 'KH001', 'Nguyễn Văn An', 'Quận 1, TP.HCM', '0901234567'),
(2, 'KH002', 'Trần Thị Bình', 'Quận 3, TP.HCM', '0912345678'),
(3, 'KH003', 'Lê Hoàng Cường', 'Quận 5, TP.HCM', '0923456789'),
(4, 'KH004', 'Phạm Thị Dung', 'Quận 10, TP.HCM', '0934567890'),
(5, 'KH005', 'Hoàng Văn Em', 'Quận Bình Thạnh, TP.HCM', '0945678901'),
(6, 'KH006', 'Võ Thị Phượng', 'Quận Tân Bình, TP.HCM', '0956789012'),
(7, 'KH007', 'Trương Trí Tín', 'Quận 7, TP.HCM', '0967890123'),
(8, 'KH008', 'Trần Hoàn Vũ', 'Quận Phú Nhuận, TP.HCM', '0978901234'),
(9, 'KH009', 'Lưu Thị Trúc Thảo', 'Quận Gò Vấp, TP.HCM', '0989012345'),
(10, 'KH010', 'Võ Thị Lê Phương', 'Quận Thủ Đức, TP.HCM', '0990123456');
UNLOCK TABLES;

-- =============================================
-- Table: sales_info
-- =============================================
DROP TABLE IF EXISTS `sales_info`;
CREATE TABLE `sales_info` (
  `sales_id` INT NOT NULL AUTO_INCREMENT,
  `product_code` VARCHAR(45) NOT NULL,
  `customer_code` VARCHAR(45) NOT NULL,
  `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `quantity` INT NOT NULL,
  `sell_price` DOUBLE NOT NULL,
  `revenue` DOUBLE NOT NULL,
  `sold_by` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`sales_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `sales_info` WRITE;
INSERT INTO `sales_info` VALUES
(1, 'IP14PM', 'KH001', '2025-06-02 15:30:00', 5, 29990000, 149950000, 'admin'),
(2, 'SS23U', 'KH001', '2025-06-22 15:30:00', 8, 26990000, 215920000, 'admin'),
(3, 'IP14', 'KH002', '2025-07-03 10:20:00', 10, 22990000, 229900000, 'admin'),
(4, 'SS23', 'KH002', '2025-08-03 10:20:00', 12, 18990000, 227880000, 'admin'),
(5, 'IP13PM', 'KH003', '2025-08-14 14:15:00', 6, 25990000, 155940000, 'admin'),
(6, 'XM13P', 'KH003', '2025-09-09 14:15:00', 15, 11990000, 179850000, 'admin'),
(7, 'OP11', 'KH004', '2025-09-10 11:30:00', 10, 12990000, 129900000, 'admin'),
(8, 'RN12P', 'KH004', '2025-09-15 11:30:00', 18, 7990000, 143820000, 'admin'),
(9, 'SS22U', 'KH005', '2025-09-16 16:45:00', 5, 24990000, 124950000, 'admin'),
(10, 'IP14P', 'KH005', '2025-10-06 16:45:00', 7, 27990000, 195930000, 'admin'),
(11, 'XM13T', 'KH006', '2025-10-18 13:25:00', 20, 6990000, 139800000, 'admin'),
(12, 'VV25', 'KH001', '2025-11-28 15:40:00', 12, 8990000, 107880000, 'admin'),
(13, 'IP13', 'KH002', '2025-12-09 12:20:00', 9, 19990000, 179910000, 'admin'),
(14, 'XM12', 'KH003', '2025-12-10 14:35:00', 25, 4990000, 124750000, 'admin'),
(15, 'HN90P', 'KH004', '2026-01-11 10:15:00', 6, 24990000, 149940000, 'admin');
UNLOCK TABLES;
