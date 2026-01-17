CREATE DATABASE IF NOT EXISTS `inventory`;
USE `inventory`;

-- =========================
-- Table: currentstock
-- =========================
DROP TABLE IF EXISTS `currentstock`;
CREATE TABLE `currentstock` (
  `productcode` VARCHAR(45) NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`productcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `currentstock` WRITE;
INSERT INTO `currentstock` VALUES
('prod1',146),
('prod2',100),
('prod3',202),
('prod4',172),
('prod5',500),
('prod6',500),
('prod7',10),
('prod8',20);
UNLOCK TABLES;

-- =========================
-- Table: customers
-- =========================
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `cid` INT NOT NULL AUTO_INCREMENT,
  `customercode` VARCHAR(45) NOT NULL,
  `fullname` VARCHAR(45) NOT NULL,
  `location` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=307 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `customers` WRITE;
INSERT INTO `customers` VALUES
(301,'cust1','AD ZAFAR','New York','9818562354');
UNLOCK TABLES;

-- =========================
-- Table: products
-- =========================
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `pid` INT NOT NULL AUTO_INCREMENT,
  `productcode` VARCHAR(45) NOT NULL,
  `productname` VARCHAR(45) NOT NULL,
  `costprice` DOUBLE NOT NULL,
  `sellprice` DOUBLE NOT NULL,
  `brand` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `productcode_UNIQUE` (`productcode`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `products` WRITE;
INSERT INTO `products` VALUES
(111,'prod1','Laptop',85000,90000,'Dell'),
(112,'prod2','Laptop',70000,72000,'HP'),
(113,'prod3','Mobile',60000,64000,'Apple'),
(114,'prod4','Mobile',50000,51000,'Samsung'),
(121,'prod5','Charger',2000,2100,'Apple');
UNLOCK TABLES;

-- =========================
-- Table: purchaseinfo
-- =========================
DROP TABLE IF EXISTS `purchaseinfo`;
CREATE TABLE `purchaseinfo` (
  `purchaseid` INT NOT NULL AUTO_INCREMENT,
  `suppliercode` VARCHAR(45) NOT NULL,
  `productcode` VARCHAR(45) NOT NULL,
  `date` VARCHAR(45) NOT NULL,
  `quantity` INT NOT NULL,
  `totalcost` DOUBLE NOT NULL,
  PRIMARY KEY (`purchaseid`)
) ENGINE=InnoDB AUTO_INCREMENT=1012 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `purchaseinfo` WRITE;
INSERT INTO `purchaseinfo` VALUES
(1001,'sup1','prod1','Wed Nov 23 00:15:19 IST 2021',10,850000),
(1002,'sup1','prod6','Wed Nov 23 00:15:19 IST 2021',20,34000),
(1003,'sup2','prod3','Wed Nov 23 00:15:19 IST 2021',5,300000);
UNLOCK TABLES;

-- =========================
-- Table: salesinfo
-- =========================
DROP TABLE IF EXISTS `salesinfo`;
CREATE TABLE `salesinfo` (
  `salesid` INT NOT NULL AUTO_INCREMENT,
  `date` VARCHAR(45) NOT NULL,
  `productcode` VARCHAR(45) NOT NULL,
  `customercode` VARCHAR(45) NOT NULL,
  `quantity` INT NOT NULL,
  `revenue` DOUBLE NOT NULL,
  `soldby` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`salesid`)
) ENGINE=InnoDB AUTO_INCREMENT=2013 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `salesinfo` WRITE;
INSERT INTO `salesinfo` VALUES
(2001,'Fri Jan 16 23:12:40 IST 2021','prod1','cust1',3,270000,'admin');
UNLOCK TABLES;

-- =========================
-- Table: suppliers
-- =========================
DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE `suppliers` (
  `sid` INT NOT NULL AUTO_INCREMENT,
  `suppliercode` VARCHAR(45) NOT NULL,
  `fullname` VARCHAR(45) NOT NULL,
  `location` VARCHAR(45) NOT NULL,
  `mobile` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=409 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `suppliers` WRITE;
INSERT INTO `suppliers` VALUES
(401,'sup1','Dell Inc.','Newyork','1800560001'),
(402,'sup2','iworld Stores','Newyork','1800560041'),
(403,'sup3','Samsung Appliances','Newyork','6546521234'),
(404,'sup4','Hewlett-Packard','Newyork','85552022');
UNLOCK TABLES;

-- =========================
-- Table: users
-- =========================
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `location` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(10) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `usertype` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES
(17,'Adnan Zafar','Sydney','123456789','admin','admin','ADMINISTRATOR');
UNLOCK TABLES;
