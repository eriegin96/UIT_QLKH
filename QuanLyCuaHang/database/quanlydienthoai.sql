-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 14, 2026 at 02:30 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanlydienthoai`
--

-- --------------------------------------------------------

--
-- Table structure for table `Account`
--

CREATE TABLE `Account` (
  `fullName` varchar(50) DEFAULT NULL,
  `userName` varchar(50) NOT NULL,
  `password` varchar(60) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Account`
--

INSERT INTO `Account` (`fullName`, `userName`, `password`, `role`, `status`, `email`) VALUES
('Admin', 'admin', '$2a$12$Y87zSnx.tpFvieylSeXuo.agjb7swi3UVnoo6KVMY9xP5STj4zJhm', 'Admin', 1, 'admin@email.com'),
('Võ Thị Lê Phương', 'phuong', '$2a$12$PhiTGBbHjHoB3dbS6BmCC.rzdMCBqDrdK9Y8Ae8GPcKe1RpHiWARO', 'Nhân viên xuất', 1, 'phuong@uit.com'),
('Lưu Thị Trúc Thảo', 'thao', '$2a$12$89As1J0AB0yrqGjnQUHtpevc6voGyvzAd8OvzkS1vGDo3YPO2P.Ia', 'Nhân viên nhập', 1, 'thao@uit.com'),
('Trương Trí Tín', 'tin', '$2a$12$Y87zSnx.tpFvieylSeXuo.agjb7swi3UVnoo6KVMY9xP5STj4zJhm', 'Admin', 1, 'tin@uit.com'),
('Trần Hoàn Vũ', 'vu', '$2a$12$myOaq0kATMzNkbxgzQEkPu8ht2K0pXOGzZMZo6nSBowq6EyoLo7tS', 'Quản lý kho', 1, 'vu@uit.com');

-- --------------------------------------------------------

--
-- Table structure for table `ChiTietPhieuNhap`
--

CREATE TABLE `ChiTietPhieuNhap` (
  `maPhieu` varchar(50) NOT NULL,
  `maDienThoai` varchar(50) NOT NULL,
  `soLuong` int(11) DEFAULT NULL,
  `donGia` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ChiTietPhieuNhap`
--

INSERT INTO `ChiTietPhieuNhap` (`maPhieu`, `maDienThoai`, `soLuong`, `donGia`) VALUES
('PN1', 'IP14PM', 10, 29990000),
('PN1', 'SS23U', 15, 26990000),
('PN2', 'IP14', 20, 22990000),
('PN2', 'SS23', 25, 18990000),
('PN3', 'IP13PM', 12, 25990000),
('PN3', 'XM13P', 30, 11990000),
('PN4', 'OP11', 20, 12990000),
('PN4', 'RN12P', 35, 7990000),
('PN5', 'SS22U', 10, 24990000),
('PN5', 'IP14P', 15, 27990000),
('PN6', 'XM13T', 40, 6990000),
('PN6', 'RN11', 30, 5990000),
('PN7', 'VV25', 25, 8990000),
('PN7', 'OP10P', 20, 9990000),
('PN8', 'IP13', 18, 19990000),
('PN8', 'SS22P', 12, 21990000),
('PN9', 'XM12', 45, 4990000),
('PN9', 'RN10P', 30, 6990000),
('PN10', 'IP14PM', 8, 29990000),
('PN10', 'SS23U', 10, 26990000),
('PN11', 'ME50P', 20, 24990000),
('PN11', 'ME40N', 15, 22990000),
('PN12', 'SX1V', 10, 34990000),
('PN12', 'SX5V', 8, 31990000),
('PN13', 'MR40', 25, 16990000),
('PN13', 'HNM6P', 12, 18990000),
('PN14', 'NK400', 30, 11990000),
('PN14', 'HNX9B', 20, 12990000),
('PN15', 'NKXR21', 40, 8990000),
('PN15', 'HN90P', 10, 24990000);

-- --------------------------------------------------------

--
-- Table structure for table `ChiTietPhieuXuat`
--

CREATE TABLE `ChiTietPhieuXuat` (
  `maPhieu` varchar(50) NOT NULL,
  `maDienThoai` varchar(50) NOT NULL,
  `soLuong` int(11) DEFAULT NULL,
  `donGia` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ChiTietPhieuXuat`
--

INSERT INTO `ChiTietPhieuXuat` (`maPhieu`, `maDienThoai`, `soLuong`, `donGia`) VALUES
('PX1', 'IP14PM', 5, 29990000),
('PX1', 'SS23U', 8, 26990000),
('PX2', 'IP14', 10, 22990000),
('PX2', 'SS23', 12, 18990000),
('PX3', 'IP13PM', 6, 25990000),
('PX3', 'XM13P', 15, 11990000),
('PX4', 'OP11', 10, 12990000),
('PX4', 'RN12P', 18, 7990000),
('PX5', 'SS22U', 5, 24990000),
('PX5', 'IP14P', 7, 27990000),
('PX6', 'XM13T', 20, 6990000),
('PX6', 'RN11', 15, 5990000),
('PX7', 'VV25', 12, 8990000),
('PX7', 'OP10P', 10, 9990000),
('PX8', 'IP13', 9, 19990000),
('PX8', 'SS22P', 6, 21990000),
('PX9', 'XM12', 25, 4990000),
('PX9', 'RN10P', 15, 6990000),
('PX10', 'IP14PM', 3, 29990000),
('PX10', 'SS23U', 5, 26990000),
('PX11', 'ME50P', 12, 24990000),
('PX11', 'ME40N', 8, 22990000),
('PX12', 'SX1V', 5, 34990000),
('PX12', 'SX5V', 4, 31990000),
('PX13', 'MR40', 15, 16990000),
('PX13', 'HNM6P', 6, 18990000),
('PX14', 'NK400', 18, 11990000),
('PX14', 'HNX9B', 10, 12990000),
('PX15', 'NKXR21', 25, 8990000),
('PX15', 'HN90P', 6, 24990000);

-- --------------------------------------------------------

--
-- Table structure for table `DienThoai`
--

CREATE TABLE `DienThoai` (
  `maDienThoai` varchar(50) NOT NULL,
  `tenDienThoai` varchar(100) DEFAULT NULL,
  `soLuong` int(11) NOT NULL DEFAULT 0,
  `hang` varchar(50) NOT NULL,
  `dungLuongPin` varchar(50) DEFAULT NULL,
  `ram` varchar(50) NOT NULL DEFAULT '0',
  `rom` varchar(50) DEFAULT NULL,
  `kichThuocMan` double DEFAULT NULL,
  `cameraChinh` varchar(50) DEFAULT NULL,
  `cameraPhu` varchar(50) DEFAULT NULL,
  `chipXuLy` varchar(50) DEFAULT NULL,
  `heDieuHanh` varchar(50) DEFAULT NULL,
  `gia` double NOT NULL DEFAULT 0,
  `xuatXu` varchar(50) DEFAULT NULL,
  `trangThai` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `DienThoai`
--

INSERT INTO `DienThoai` (`maDienThoai`, `tenDienThoai`, `soLuong`, `hang`, `dungLuongPin`, `ram`, `rom`, `kichThuocMan`, `cameraChinh`, `cameraPhu`, `chipXuLy`, `heDieuHanh`, `gia`, `xuatXu`, `trangThai`) VALUES
('IP13', 'iPhone 13 128GB', 55, 'Apple', '3240 mAh', '4 GB', '128 GB', 6.1, '12 MP', '12 MP', 'Apple A15 Bionic', 'iOS 15', 19990000, 'Trung Quốc', 1),
('IP13PM', 'iPhone 13 Pro Max 256GB', 48, 'Apple', '4352 mAh', '6 GB', '256 GB', 6.7, '12 MP', '12 MP', 'Apple A15 Bionic', 'iOS 15', 25990000, 'Trung Quốc', 1),
('IP14', 'iPhone 14 128GB', 65, 'Apple', '3279 mAh', '6 GB', '128 GB', 6.1, '12 MP', '12 MP', 'Apple A15 Bionic', 'iOS 16', 22990000, 'Trung Quốc', 1),
('IP14P', 'iPhone 14 Plus 128GB', 52, 'Apple', '4325 mAh', '6 GB', '128 GB', 6.7, '12 MP', '12 MP', 'Apple A15 Bionic', 'iOS 16', 27990000, 'Trung Quốc', 1),
('IP14PM', 'iPhone 14 Pro Max 256GB', 42, 'Apple', '4323 mAh', '6 GB', '256 GB', 6.7, '48 MP', '12 MP', 'Apple A16 Bionic', 'iOS 16', 29990000, 'Trung Quốc', 1),
('OP10P', 'OPPO Reno10 Pro+ 5G', 38, 'OPPO', '4700 mAh', '12 GB', '256 GB', 6.74, '50 MP', '32 MP', 'Snapdragon 8+ Gen 1', 'Android 13', 9990000, 'Trung Quốc', 1),
('OP11', 'OPPO Find X5 Pro 5G', 42, 'OPPO', '5000 mAh', '12 GB', '256 GB', 6.7, '50 MP', '32 MP', 'Snapdragon 8 Gen 1', 'Android 12', 12990000, 'Trung Quốc', 1),
('RN10P', 'Redmi Note 10 Pro', 65, 'Xiaomi', '5020 mAh', '6 GB', '128 GB', 6.67, '64 MP', '16 MP', 'Snapdragon 732G', 'Android 11', 6990000, 'Trung Quốc', 1),
('RN11', 'Redmi Note 11', 75, 'Xiaomi', '5000 mAh', '4 GB', '128 GB', 6.43, '50 MP', '13 MP', 'Snapdragon 680', 'Android 11', 5990000, 'Trung Quốc', 1),
('RN12P', 'Redmi Note 12 Pro 5G', 82, 'Xiaomi', '5000 mAh', '8 GB', '256 GB', 6.67, '50 MP', '16 MP', 'Snapdragon 4 Gen 1', 'Android 12', 7990000, 'Trung Quốc', 1),
('SS22P', 'Samsung Galaxy S22 Plus 5G', 44, 'Samsung', '4500 mAh', '8 GB', '256 GB', 6.6, '50 MP', '10 MP', 'Snapdragon 8 Gen 1', 'Android 12', 21990000, 'Việt Nam', 1),
('SS22U', 'Samsung Galaxy S22 Ultra 5G', 38, 'Samsung', '5000 mAh', '12 GB', '256 GB', 6.8, '108 MP', '40 MP', 'Snapdragon 8 Gen 1', 'Android 12', 24990000, 'Việt Nam', 1),
('SS23', 'Samsung Galaxy S23 5G', 72, 'Samsung', '3900 mAh', '8 GB', '128 GB', 6.1, '50 MP', '12 MP', 'Snapdragon 8 Gen 2', 'Android 13', 18990000, 'Việt Nam', 1),
('SS23U', 'Samsung Galaxy S23 Ultra 5G', 58, 'Samsung', '5000 mAh', '12 GB', '256 GB', 6.8, '200 MP', '12 MP', 'Snapdragon 8 Gen 2', 'Android 13', 26990000, 'Việt Nam', 1),
('VV25', 'Vivo V25 5G', 55, 'Vivo', '4500 mAh', '8 GB', '128 GB', 6.44, '64 MP', '50 MP', 'Dimensity 900', 'Android 12', 8990000, 'Trung Quốc', 1),
('XM12', 'Xiaomi 12', 98, 'Xiaomi', '4500 mAh', '8 GB', '128 GB', 6.28, '50 MP', '32 MP', 'Snapdragon 8 Gen 1', 'Android 12', 4990000, 'Trung Quốc', 1),
('XM13P', 'Xiaomi 13 Pro', 77, 'Xiaomi', '4820 mAh', '12 GB', '256 GB', 6.73, '50 MP', '32 MP', 'Snapdragon 8 Gen 2', 'Android 13', 11990000, 'Trung Quốc', 1),
('XM13T', 'Xiaomi 13T', 88, 'Xiaomi', '5000 mAh', '8 GB', '256 GB', 6.67, '50 MP', '20 MP', 'Dimensity 8200-Ultra', 'Android 13', 6990000, 'Trung Quốc', 1),
('HN90P', 'Honor 90 Pro', 55, 'Honor', '5000 mAh', '12 GB', '256 GB', 6.78, '200 MP', '50 MP', 'Snapdragon 8+ Gen 1', 'Android 13', 24990000, 'Trung Quốc', 1),
('HNM6P', 'Honor Magic 6 Pro', 60, 'Honor', '5600 mAh', '12 GB', '512 GB', 6.8, '50 MP', '50 MP', 'Snapdragon 8 Gen 3', 'Android 14', 18990000, 'Trung Quốc', 1),
('HNX9B', 'Honor X9b 5G', 62, 'Honor', '5800 mAh', '12 GB', '256 GB', 6.78, '108 MP', '16 MP', 'Snapdragon 6 Gen 1', 'Android 13', 12990000, 'Trung Quốc', 1),
('ME40N', 'Motorola Edge 40 Neo', 68, 'Motorola', '5000 mAh', '12 GB', '256 GB', 6.55, '50 MP', '32 MP', 'Dimensity 7030', 'Android 13', 22990000, 'Trung Quốc', 1),
('ME50P', 'Motorola Edge 50 Pro', 75, 'Motorola', '4500 mAh', '12 GB', '256 GB', 6.7, '50 MP', '50 MP', 'Snapdragon 7 Gen 3', 'Android 14', 24990000, 'Trung Quốc', 1),
('MR40', 'Motorola Razr 40', 80, 'Motorola', '4200 mAh', '8 GB', '256 GB', 6.9, '64 MP', '32 MP', 'Snapdragon 7 Gen 1', 'Android 13', 16990000, 'Trung Quốc', 1),
('NK400', 'Nokia G400 5G', 70, 'Nokia', '5000 mAh', '6 GB', '128 GB', 6.58, '48 MP', '16 MP', 'Snapdragon 480+', 'Android 12', 11990000, 'Phần Lan', 1),
('NKXR21', 'Nokia XR21 5G', 90, 'Nokia', '4800 mAh', '6 GB', '128 GB', 6.49, '64 MP', '16 MP', 'Snapdragon 695', 'Android 13', 8990000, 'Phần Lan', 1),
('SX1V', 'Sony Xperia 1 VI', 45, 'Sony', '5000 mAh', '12 GB', '256 GB', 6.5, '48 MP', '12 MP', 'Snapdragon 8 Gen 3', 'Android 14', 34990000, 'Nhật Bản', 1),
('SX5V', 'Sony Xperia 5 V', 50, 'Sony', '5000 mAh', '8 GB', '128 GB', 6.1, '48 MP', '12 MP', 'Snapdragon 8 Gen 2', 'Android 13', 31990000, 'Nhật Bản', 1);

-- --------------------------------------------------------

--
-- Table structure for table `NhaCungCap`
--

CREATE TABLE `NhaCungCap` (
  `maNhaCungCap` varchar(50) NOT NULL,
  `tenNhaCungCap` varchar(50) DEFAULT NULL,
  `Sdt` varchar(50) DEFAULT NULL,
  `diaChi` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `NhaCungCap`
--

INSERT INTO `NhaCungCap` (`maNhaCungCap`, `tenNhaCungCap`, `Sdt`, `diaChi`) VALUES
('CELLPHONES', 'Công ty TNHH Thương Mại Cellphones', '028 7300 6688', '419 Võ Văn Tần, Phường 5, Quận 3, TP. Hồ Chí Minh'),
('DIDONGVIET', 'Công ty TNHH Di Động Việt', '1800 6018', '25 Trần Hưng Đạo, Quận Hoàn Kiếm, Hà Nội'),
('FPT', 'Công Ty Cổ Phần Bán Lẻ Kỹ Thuật Số FPT', '02873023456', '261 - 263 Khánh Hội, P2, Q4, TP. Hồ Chí Minh'),
('HOANGHA', 'Công ty Cổ phần Thương mại Hoàng Hà', '1900 1903', '128 Trần Phú, Phường Văn Quán, Quận Hà Đông, Hà Nội'),
('MINHTUONG', 'Công ty TNHH Thương Mại Minh Tường', '028 3930 3456', '77 Nguyễn Huệ, Phường Bến Nghé, Quận 1, TP. Hồ Chí Minh'),
('PHONGVU', 'Công ty cổ phần dịch vụ - thương mại Phong Vũ', '0967567567', 'Tầng 5, Số 117-119-121 Nguyễn Du, Phường Bến Thành, Quận 1, Thành Phố Hồ Chí Minh'),
('TGDD', 'Công ty cổ phần Thế Giới Di Động', '028 38125960', '128 Trần Quang Khải, P. Tân Định, Q.1, TP.Hồ Chí Minh'),
('VIETTEL', 'Tập đoàn Công nghiệp - Viễn thông Quân đội', '1900 8098', '1 Giang Văn Minh, Phường Kim Mã, Quận Ba Đình, Hà Nội');

-- --------------------------------------------------------

--
-- Table structure for table `PhieuNhap`
--

CREATE TABLE `PhieuNhap` (
  `maPhieu` varchar(50) NOT NULL,
  `thoiGianTao` timestamp NULL DEFAULT NULL,
  `nguoiTao` varchar(50) DEFAULT NULL,
  `maNhaCungCap` varchar(50) DEFAULT NULL,
  `tongTien` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `PhieuNhap`
--

INSERT INTO `PhieuNhap` (`maPhieu`, `thoiGianTao`, `nguoiTao`, `maNhaCungCap`, `tongTien`) VALUES
('PN1', '2026-01-02 13:59:09', 'admin', 'FPT', 704750000),
('PN2', '2026-01-02 15:30:23', 'admin', 'TGDD', 934550000),
('PN3', '2026-01-03 09:15:18', 'admin', 'FPT', 671580000),
('PN4', '2026-01-03 14:20:37', 'thao', 'PHONGVU', 539450000),
('PN5', '2026-01-05 10:30:02', 'admin', 'FPT', 669750000),
('PN6', '2026-01-05 16:45:32', 'thao', 'CELLPHONES', 459300000),
('PN7', '2026-01-06 11:20:43', 'admin', 'HOANGHA', 424550000),
('PN8', '2026-01-06 14:35:01', 'admin', 'FPT', 623700000),
('PN9', '2026-01-07 09:40:44', 'thao', 'TGDD', 434250000),
('PN10', '2026-01-08 13:15:19', 'admin', 'DIDONGVIET', 509820000),
('PN11', '2026-01-09 10:25:30', 'thao', 'FPT', 844650000),
('PN12', '2026-01-10 15:40:15', 'admin', 'TGDD', 605820000),
('PN13', '2026-01-11 11:30:45', 'thao', 'PHONGVU', 652630000),
('PN14', '2026-01-12 14:20:25', 'admin', 'HOANGHA', 619500000),
('PN15', '2026-01-13 16:50:10', 'thao', 'CELLPHONES', 609500000);

-- --------------------------------------------------------

--
-- Table structure for table `PhieuXuat`
--

CREATE TABLE `PhieuXuat` (
  `maPhieu` varchar(50) NOT NULL,
  `thoiGianTao` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `nguoiTao` varchar(50) NOT NULL,
  `tongTien` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `PhieuXuat`
--

INSERT INTO `PhieuXuat` (`maPhieu`, `thoiGianTao`, `nguoiTao`, `tongTien`) VALUES
('PX1', '2026-01-02 15:30:44', 'admin', 365870000),
('PX2', '2026-01-03 10:20:43', 'phuong', 457780000),
('PX3', '2026-01-04 14:15:52', 'admin', 335790000),
('PX4', '2026-01-05 11:30:59', 'tin', 273720000),
('PX5', '2026-01-06 16:45:13', 'phuong', 320880000),
('PX6', '2026-01-07 13:25:12', 'admin', 229650000),
('PX7', '2026-01-08 15:40:43', 'tin', 207780000),
('PX8', '2026-01-09 12:20:54', 'phuong', 311850000),
('PX9', '2026-01-10 14:35:22', 'tin', 229600000),
('PX10', '2026-01-11 10:15:08', 'admin', 224920000),
('PX11', '2026-01-12 11:40:30', 'phuong', 483800000),
('PX12', '2026-01-12 16:25:15', 'tin', 302910000),
('PX13', '2026-01-13 13:50:45', 'phuong', 368790000),
('PX14', '2026-01-13 17:30:25', 'tin', 345720000),
('PX15', '2026-01-14 09:20:10', 'phuong', 374690000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Account`
--
ALTER TABLE `Account`
  ADD PRIMARY KEY (`userName`) USING BTREE;

--
-- Indexes for table `ChiTietPhieuNhap`
--
ALTER TABLE `ChiTietPhieuNhap`
  ADD PRIMARY KEY (`maPhieu`,`maDienThoai`),
  ADD KEY `FK_ChiTietPhieuNhap_DienThoai` (`maDienThoai`);

--
-- Indexes for table `ChiTietPhieuXuat`
--
ALTER TABLE `ChiTietPhieuXuat`
  ADD PRIMARY KEY (`maPhieu`,`maDienThoai`),
  ADD KEY `FK_ChiTietPhieuXuat_DienThoai` (`maDienThoai`);

--
-- Indexes for table `DienThoai`
--
ALTER TABLE `DienThoai`
  ADD PRIMARY KEY (`maDienThoai`);

--
-- Indexes for table `NhaCungCap`
--
ALTER TABLE `NhaCungCap`
  ADD PRIMARY KEY (`maNhaCungCap`);

--
-- Indexes for table `PhieuNhap`
--
ALTER TABLE `PhieuNhap`
  ADD PRIMARY KEY (`maPhieu`),
  ADD KEY `FK_PhieuNhap_NhaCungCap` (`maNhaCungCap`),
  ADD KEY `FK_PhieuNhap_Account` (`nguoiTao`);

--
-- Indexes for table `PhieuXuat`
--
ALTER TABLE `PhieuXuat`
  ADD PRIMARY KEY (`maPhieu`),
  ADD KEY `FK_PhieuXuat_Account` (`nguoiTao`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ChiTietPhieuNhap`
--
ALTER TABLE `ChiTietPhieuNhap`
  ADD CONSTRAINT `FK_ChiTietPhieuNhap_DienThoai` FOREIGN KEY (`maDienThoai`) REFERENCES `DienThoai` (`maDienThoai`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_ChiTietPhieuNhap_PhieuNhap` FOREIGN KEY (`maPhieu`) REFERENCES `PhieuNhap` (`maPhieu`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `ChiTietPhieuXuat`
--
ALTER TABLE `ChiTietPhieuXuat`
  ADD CONSTRAINT `FK_ChiTietPhieuXuat_DienThoai` FOREIGN KEY (`maDienThoai`) REFERENCES `DienThoai` (`maDienThoai`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_ChiTietPhieuXuat_PhieuXuat` FOREIGN KEY (`maPhieu`) REFERENCES `PhieuXuat` (`maPhieu`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `PhieuNhap`
--
ALTER TABLE `PhieuNhap`
  ADD CONSTRAINT `FK_PhieuNhap_Account` FOREIGN KEY (`nguoiTao`) REFERENCES `Account` (`userName`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_PhieuNhap_NhaCungCap` FOREIGN KEY (`maNhaCungCap`) REFERENCES `NhaCungCap` (`maNhaCungCap`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `PhieuXuat`
--
ALTER TABLE `PhieuXuat`
  ADD CONSTRAINT `FK_PhieuXuat_Account` FOREIGN KEY (`nguoiTao`) REFERENCES `Account` (`userName`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
