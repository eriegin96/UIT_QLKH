# QuanLyCuaHang - Phone Store Management System

## Project Summary

**Project Name:** QuanLyCuaHang (Store Management)  
**Type:** Desktop Application - Inventory Management System  
**Technology:** Java Swing + MySQL  
**Domain:** Mobile Phone Retail Store Management  
**Last Updated:** January 14, 2026

---

## Executive Summary

QuanLyCuaHang is a comprehensive **phone store inventory management system** developed in Java using Swing for the GUI and MySQL for data persistence. The system manages the complete lifecycle of phone retail operations including product inventory, supplier management, import/export transactions, user authentication, and business statistics.

The application implements a role-based access control system with 4 distinct user roles (Admin, Stock Manager, Import Staff, Export Staff) and provides specialized interfaces for each role's responsibilities.

---

## System Architecture

### Architecture Pattern

- **MVC (Model-View-Controller)** pattern
- Clean separation of concerns across layers
- DAO pattern for data access

### Project Structure

```
QuanLyCuaHang/
├── src/
│   ├── model/           # Data models (8 classes)
│   ├── dao/             # Data Access Objects (9 classes)
│   ├── controller/      # Business logic (7 utility classes)
│   ├── view/            # UI components (28 forms, 28 .form files)
│   ├── database/        # JDBC utilities
│   └── icon/            # 67 PNG icons
├── database/
│   └── quanlycuahang.sql   # Database schema + sample data
├── lib/                 # External libraries (10+ JAR files)
├── build/               # Compiled classes
└── dist/                # Distributable JAR

Total Lines of Code: ~15,000+ (estimated)
```

---

## Database Design

### Database: `quanlycuahang`

**Tables:** 6 main tables with foreign key relationships

#### 1. **Account** - User Management

| Column   | Type           | Description            |
| -------- | -------------- | ---------------------- |
| userName | VARCHAR(50) PK | Unique username        |
| fullName | VARCHAR(50)    | Full name              |
| password | VARCHAR(60)    | BCrypt hashed password |
| role     | VARCHAR(50)    | User role              |
| status   | INT            | Active/Inactive (1/0)  |
| email    | VARCHAR(50)    | Email address          |

**Roles:**

- Admin (full access)
- Quản lý kho (Stock Manager)
- Nhân viên nhập (Import Staff)
- Nhân viên xuất (Export Staff)

**Sample Users:** 5 accounts (admin, tin, vu, thao, phuong)

#### 2. **DienThoai** - Phone Products

| Column       | Type           | Description                  |
| ------------ | -------------- | ---------------------------- |
| maDienThoai  | VARCHAR(50) PK | Product code                 |
| tenDienThoai | VARCHAR(100)   | Product name                 |
| soLuong      | INT            | Stock quantity               |
| hang         | VARCHAR(50)    | Brand                        |
| dungLuongPin | VARCHAR(50)    | Battery capacity             |
| ram          | VARCHAR(50)    | RAM                          |
| rom          | VARCHAR(50)    | Storage                      |
| kichThuocMan | DOUBLE         | Screen size                  |
| cameraChinh  | VARCHAR(50)    | Main camera                  |
| cameraPhu    | VARCHAR(50)    | Front camera                 |
| chipXuLy     | VARCHAR(50)    | Processor                    |
| heDieuHanh   | VARCHAR(50)    | Operating system             |
| gia          | DOUBLE         | Price                        |
| xuatXu       | VARCHAR(50)    | Origin country               |
| trangThai    | INT            | Status (1=active, 0=deleted) |

**Sample Data:** 24 phones from 9 brands (Apple, Samsung, Xiaomi, OPPO, Vivo, Honor, Motorola, Nokia, Sony)

#### 3. **NhaCungCap** - Suppliers

| Column        | Type           | Description   |
| ------------- | -------------- | ------------- |
| maNhaCungCap  | VARCHAR(50) PK | Supplier code |
| tenNhaCungCap | VARCHAR(50)    | Supplier name |
| Sdt           | VARCHAR(50)    | Phone number  |
| diaChi        | VARCHAR(150)   | Address       |

**Sample Data:** 8 Vietnamese retailers (FPT, TGDD, Cellphones, Phong Vũ, Hoàng Hà, etc.)

#### 4. **PhieuNhap** - Import Receipts

| Column       | Type           | Description      |
| ------------ | -------------- | ---------------- |
| maPhieu      | VARCHAR(50) PK | Receipt code     |
| thoiGianTao  | TIMESTAMP      | Creation time    |
| nguoiTao     | VARCHAR(50) FK | Creator username |
| maNhaCungCap | VARCHAR(50) FK | Supplier code    |
| tongTien     | DOUBLE         | Total amount     |

**Sample Data:** 15 import receipts (PN1-PN15)

#### 5. **PhieuXuat** - Export Receipts

| Column      | Type           | Description      |
| ----------- | -------------- | ---------------- |
| maPhieu     | VARCHAR(50) PK | Receipt code     |
| thoiGianTao | TIMESTAMP      | Creation time    |
| nguoiTao    | VARCHAR(50) FK | Creator username |
| tongTien    | DOUBLE         | Total amount     |

**Sample Data:** 15 export receipts (PX1-PX15)

#### 6. **ChiTietPhieuNhap / ChiTietPhieuXuat** - Receipt Line Items

| Column      | Type               | Description  |
| ----------- | ------------------ | ------------ |
| maPhieu     | VARCHAR(50) PK, FK | Receipt code |
| maDienThoai | VARCHAR(50) PK, FK | Product code |
| soLuong     | INT                | Quantity     |
| donGia      | DOUBLE             | Unit price   |

---

## Core Features

### 1. Authentication & Security

- **Login System** with BCrypt password encryption
- **Role-Based Access Control (RBAC)**
- **Password Recovery** via email (SMTP)
- **User Profile Management**
- Session management

**Implementation:**

- `controller/BCrypt.java` - Password hashing (12 rounds, salt)
- `controller/SendEmailSMTP.java` - Email notifications
- `dao/AccountDAO.java` - User CRUD operations
- `view/Login.java` - Login interface

### 2. Product Management

**Features:**

- Add new phone products with full specifications
- Update product information
- Soft delete (status flag)
- View detailed product information
- Search by code, name, or brand
- Real-time stock tracking

**Implementation:**

- `model/DienThoai.java` - Product data model (15 attributes)
- `dao/DienThoaiDAO.java` - CRUD + search operations
- `view/FormAddProduct.java` - Add product form
- `view/FormUpdateProduct.java` - Update product form
- `view/DialogProductDetail.java` - Product details dialog
- `controller/SearchDienThoai.java` - Search functionality

**Key Methods:**

- `selectAll()` - Get all products
- `selectAllExist()` - Get active products only
- `selectById(String id)` - Get product by code
- `selectByBrand(String brand)` - Filter by brand
- `updateSoLuong(String id, int quantity)` - Update stock

### 3. Supplier Management

**Features:**

- Add/Update/Delete suppliers
- Store contact information (name, phone, address)
- Link suppliers to import receipts
- Search suppliers

**Implementation:**

- `model/NhaCungCap.java` - Supplier model
- `dao/NhaCungCapDAO.java` - Supplier CRUD
- `view/FormAddSupplier.java` / `FormUpdateSupplier.java`
- `controller/SearchNhaCungCap.java`

### 4. Import Management (Phiếu Nhập)

**Features:**

- Create import receipts from suppliers
- Multi-line items (multiple products per receipt)
- Auto-calculate total amounts
- Automatic inventory update on import
- View/Edit import history
- Generate PDF receipts

**Implementation:**

- `model/PhieuNhap.java` - Import receipt model (extends Phieu)
- `model/ChiTietPhieu.java` - Line item model
- `dao/PhieuNhapDAO.java` - Import receipt CRUD
- `dao/ChiTietPhieuNhapDAO.java` - Line item CRUD
- `view/LayoutImportReceipt.java` - Import receipt list
- `view/LayoutUpdateImportReceipt.java` - Create/Edit receipt
- `view/DialogImportDetail.java` - Receipt details

**Workflow:**

1. Select supplier
2. Add products with quantities and prices
3. System calculates total
4. Save receipt → Auto-update product stock quantities

### 5. Export Management (Phiếu Xuất)

**Features:**

- Create export receipts for sales
- Multi-product sales
- Auto-calculate totals
- Automatic inventory deduction
- View/Edit export history
- Generate PDF receipts

**Implementation:**

- `model/PhieuXuat.java` - Export receipt model
- `dao/PhieuXuatDAO.java` - Export receipt CRUD
- `dao/ChiTietPhieuXuatDAO.java` - Line item CRUD
- `view/LayoutExportReceipt.java` - Export receipt list
- `view/LayoutUpdateExportReceipt.java` - Create/Edit receipt
- `view/DialogExportDetail.java` - Receipt details

**Workflow:**

1. Add products to sale with quantities
2. System validates stock availability
3. Calculate total amount
4. Save receipt → Auto-deduct from inventory

### 6. Statistics & Reports (Thống Kê)

**Features:**

- Product-wise import/export statistics
- Date range filtering
- Compare import vs export quantities
- Identify best-selling products
- View detailed statistics by product

**Implementation:**

- `model/ThongKeProduct.java` - Statistics model
- `dao/ThongKeDAO.java` - Complex SQL queries with JOINs
- `view/LayoutStat.java` - Statistics dashboard
- `view/DialogStatDetail.java` - Detailed statistics

**SQL Query Features:**

- Aggregate functions (SUM, GROUP BY)
- Multiple table JOINs
- Date filtering with BETWEEN clause

### 7. Stock Management

**Features:**

- Real-time inventory overview
- View current stock levels for all products
- Filter and search stock
- Track stock movements

**Implementation:**

- `view/LayoutStock.java` - Stock overview panel
- Integration with DienThoaiDAO

### 8. User Account Management (Admin Only)

**Features:**

- Create new user accounts
- Update user information
- Activate/Deactivate accounts
- Assign roles
- Search users

**Implementation:**

- `view/LayoutAccount.java` - User management panel
- `view/FormAddAccount.java` - Create user form
- `view/FormUpdateAccount.java` - Edit user form
- `controller/SearchAccount.java` - Search functionality

### 9. PDF Export

**Features:**

- Generate professional PDF receipts
- Vietnamese font support (Roboto)
- Custom formatting with company header
- Include all receipt details

**Implementation:**

- `controller/WritePDF.java` - PDF generation
- Uses iTextPDF library (5.5.12)
- Custom Vietnamese fonts from `lib/Roboto/`

**PDF Contents:**

- Receipt header with title
- Date and receipt number
- Product details table
- Total amount
- Creator information

### 10. Email Notifications

**Features:**

- Password recovery via email
- SMTP configuration
- HTML email support

**Implementation:**

- `controller/SendEmailSMTP.java` - Email sender
- Uses javax.mail library
- Configurable SMTP settings

---

## User Interface Design

### Modern UI Framework

- **FlatLaf** Look and Feel (modern, clean design)
- **67 custom icons** for visual appeal
- Responsive layouts with NetBeans GUI Builder
- Color scheme: Green accent (#59A869), Dark backgrounds

### Menu Systems (Role-Based)

#### 1. MenuAdmin.java - Administrator Menu

**Full Access:**

- Product Management
- Supplier Management
- Import/Export Management
- User Account Management
- Statistics
- Stock Overview

#### 2. MenuStock.java - Stock Manager Menu

**Access:**

- Product Management
- Stock Overview
- View Reports

#### 3. MenuImport.java - Import Staff Menu

**Access:**

- Create/View Import Receipts
- View Products
- View Suppliers

#### 4. MenuExport.java - Export Staff Menu

**Access:**

- Create/View Export Receipts
- View Products
- Check Stock

### Layout Panels (Content Areas)

| Panel                     | Purpose              | Features                                    |
| ------------------------- | -------------------- | ------------------------------------------- |
| LayoutProduct             | Product listing      | Table view, search, add/edit/delete buttons |
| LayoutSupplier            | Supplier listing     | Table view, CRUD operations                 |
| LayoutAccount             | User management      | User list, role assignment                  |
| LayoutImportReceipt       | Import history       | Receipt list, view details                  |
| LayoutExportReceipt       | Export history       | Receipt list, view details                  |
| LayoutUpdateImportReceipt | Create/Edit import   | Multi-line item form                        |
| LayoutUpdateExportReceipt | Create/Edit export   | Multi-line item form                        |
| LayoutStock               | Inventory overview   | Current stock levels                        |
| LayoutStat                | Statistics dashboard | Charts, filters                             |

### Dialog Windows

| Dialog              | Purpose                                     |
| ------------------- | ------------------------------------------- |
| DialogProductDetail | View complete product specifications        |
| DialogImportDetail  | View import receipt details with line items |
| DialogExportDetail  | View export receipt details with line items |
| DialogStatDetail    | View detailed statistics for a product      |

### Forms (CRUD Operations)

| Form                | Purpose               |
| ------------------- | --------------------- |
| FormAddProduct      | Add new phone product |
| FormUpdateProduct   | Edit existing product |
| FormAddSupplier     | Add new supplier      |
| FormUpdateSupplier  | Edit supplier info    |
| FormAddAccount      | Create user account   |
| FormUpdateAccount   | Edit user account     |
| FormChangeInfo      | User profile update   |
| FormRecoverPassword | Password reset        |

---

## Technical Implementation

### Technologies & Libraries

```xml
Core Technologies:
- Java SE 8+
- Swing (GUI framework)
- MySQL 8.0 (Database)
- NetBeans IDE (Development)

External Libraries (lib/):
├── mysql-connector-java-8.0.30.jar      # MySQL JDBC driver
├── itextpdf-5.5.12.jar                  # PDF generation
├── javax.mail.jar                        # Email functionality
├── javax.activation-1.2.0.jar           # Email attachments
├── jcalendar-1.4.jar                    # Date picker component
├── jxl-2.6.jar                          # Excel operations
├── lookandfeel.jar                       # FlatLaf modern UI
├── protobuf-java-3.19.4.jar             # MySQL X Protocol
└── poi-5.2.3/                           # Apache POI (Excel)
    └── [Multiple POI libraries]

Fonts:
└── Roboto/                              # Vietnamese font support
    ├── Roboto-Regular.ttf
    ├── Roboto-Bold.ttf
    └── [Other variants]
```

### Database Connection

**File:** `database/JDBCUtil.java`

```java
Connection Configuration:
- URL: jdbc:mySQL://localhost:3306/quanlycuahang
- Driver: com.mysql.jdbc.Driver
- Default User: root
- Default Password: (empty)
- Character Set: utf8mb4 (Vietnamese support)
```

**Features:**

- Connection pooling pattern
- Auto-close connections
- Database metadata printing
- Error handling

### Data Access Layer (DAO Pattern)

**Interface:** `dao/DAOInterface.java`

```java
Generic DAO Operations:
- int insert(T t)
- int update(T t)
- int delete(T t)
- ArrayList<T> selectAll()
- T selectById(String id)
```

**DAO Implementations:**

- `AccountDAO` - User account operations
- `DienThoaiDAO` - Product operations + custom queries
- `NhaCungCapDAO` - Supplier operations
- `PhieuNhapDAO` - Import receipt operations
- `PhieuXuatDAO` - Export receipt operations
- `ChiTietPhieuNhapDAO` - Import line items
- `ChiTietPhieuXuatDAO` - Export line items
- `ThongKeDAO` - Statistics queries (complex SQL)

### Controller Utilities

| Class                 | Purpose                                          |
| --------------------- | ------------------------------------------------ |
| BCrypt.java           | Password hashing (Blowfish algorithm, 12 rounds) |
| ConvertDate.java      | Date/Timestamp conversion utilities              |
| SendEmailSMTP.java    | Email sending via SMTP                           |
| WritePDF.java         | PDF generation for receipts                      |
| SearchDienThoai.java  | Product search algorithms                        |
| SearchAccount.java    | User search algorithms                           |
| SearchNhaCungCap.java | Supplier search algorithms                       |

### Model Classes (Data Transfer Objects)

**Core Models:**

```
Account.java          - User account (6 fields)
DienThoai.java        - Phone product (15 fields)
NhaCungCap.java       - Supplier (4 fields)
Phieu.java            - Abstract receipt class
PhieuNhap.java        - Import receipt (extends Phieu)
PhieuXuat.java        - Export receipt (extends Phieu)
ChiTietPhieu.java     - Receipt line item
ThongKeProduct.java   - Statistics aggregate
```

**Model Features:**

- Proper encapsulation (private fields, public getters/setters)
- Override equals() and hashCode() for collections
- Business logic methods (e.g., nhapHang(), xuatHang())
- toString() for debugging

---

## Key Design Patterns & Best Practices

### 1. MVC Pattern

- **Model:** POJOs with business logic
- **View:** Swing components (completely separated)
- **Controller:** DAO + utility classes

### 2. DAO Pattern

- Generic interface for consistency
- Specific implementations per entity
- Centralized data access

### 3. Singleton Pattern

- `getInstance()` methods in DAO classes
- Ensures single DAO instance per entity

### 4. Soft Delete Pattern

- Products use `trangThai` field (1=active, 0=deleted)
- No physical deletion from database
- Allows data recovery and audit trails

### 5. Builder Pattern (Partial)

- Complex model constructors
- Multiple constructor overloads

### 6. Factory Pattern (Database)

- `JDBCUtil` acts as connection factory
- Centralized connection management

### 7. Security Best Practices

- **Password Hashing:** BCrypt with salt
- **SQL Injection Prevention:** PreparedStatement
- **Role-Based Access Control**
- **Email validation**

### 8. Code Organization

- Package by layer (model, view, dao, controller)
- Clear naming conventions
- Consistent code style

---

## Business Logic & Workflows

### Import Workflow

```
1. User selects supplier
2. User adds products to receipt:
   - Select product from dropdown
   - Enter quantity
   - Enter unit price
   - Add to receipt line items
3. System calculates subtotal per line
4. System calculates receipt total
5. User saves receipt
6. System executes transaction:
   a. Insert PhieuNhap record
   b. Insert ChiTietPhieuNhap records
   c. Update DienThoai.soLuong (+=)
7. Generate PDF (optional)
```

### Export Workflow

```
1. User creates new export receipt
2. User adds products to sale:
   - Select product from dropdown
   - Enter quantity (validates against stock)
   - Price auto-filled from product
   - Add to receipt line items
3. System calculates totals
4. User confirms sale
5. System executes transaction:
   a. Insert PhieuXuat record
   b. Insert ChiTietPhieuXuat records
   c. Update DienThoai.soLuong (-=)
6. Generate PDF receipt (optional)
```

### Statistics Workflow

```
1. User opens statistics panel
2. User selects date range (optional)
3. System executes complex SQL:
   - JOIN PhieuNhap, ChiTietPhieuNhap, DienThoai
   - JOIN PhieuXuat, ChiTietPhieuXuat, DienThoai
   - GROUP BY product
   - SUM quantities
4. Display results in table
5. User can view detailed stats per product
```

---

## Sample Data Overview

### Users (5 accounts)

```
admin   - Admin          - admin@email.com      - Password: admin
tin     - Admin          - tin@uit.com          - Password: admin
vu      - Quản lý kho    - vu@uit.com           - Password: 123
thao    - Nhân viên nhập - thao@uit.com         - Password: 123
phuong  - Nhân viên xuất - phuong@uit.com       - Password: 123
```

### Products (24 phones)

**Brands:** Apple (5), Samsung (4), Xiaomi (4), OPPO (2), Vivo (1), Honor (3), Motorola (3), Nokia (2), Sony (2)

**Price Range:** 4,990,000 VND - 34,990,000 VND

**Example Products:**

- iPhone 14 Pro Max 256GB - 29,990,000 VND
- Samsung Galaxy S23 Ultra 5G - 26,990,000 VND
- Xiaomi 12 - 4,990,000 VND

### Suppliers (8)

Major Vietnamese tech retailers:

- FPT Shop
- Thế Giới Di Động (TGDD)
- CellphoneS
- Phong Vũ
- Hoàng Hà Mobile
- Di Động Việt
- Minh Tường
- Viettel Store

### Transactions

- **15 Import Receipts:** PN1-PN15 (Jan 2-13, 2026)
- **15 Export Receipts:** PX1-PX15 (Jan 2-14, 2026)
- **Total Import Value:** ~9.6 billion VND
- **Total Export Value:** ~4.8 billion VND

---

## User Roles & Permissions

| Feature                  | Admin | Stock Manager | Import Staff | Export Staff |
| ------------------------ | ----- | ------------- | ------------ | ------------ |
| View Products            | ✅    | ✅            | ✅           | ✅           |
| Add/Edit/Delete Products | ✅    | ✅            | ❌           | ❌           |
| View Suppliers           | ✅    | ✅            | ✅           | ❌           |
| Manage Suppliers         | ✅    | ❌            | ❌           | ❌           |
| Create Import Receipts   | ✅    | ✅            | ✅           | ❌           |
| View Import History      | ✅    | ✅            | ✅           | ❌           |
| Create Export Receipts   | ✅    | ✅            | ❌           | ✅           |
| View Export History      | ✅    | ✅            | ❌           | ✅           |
| View Stock               | ✅    | ✅            | ✅           | ✅           |
| View Statistics          | ✅    | ✅            | ❌           | ❌           |
| Manage Users             | ✅    | ❌            | ❌           | ❌           |
| Generate Reports         | ✅    | ✅            | ✅           | ✅           |

---

## Installation & Setup

### Prerequisites

```
- Java JDK 8 or higher
- MySQL Server 8.0+
- NetBeans IDE (recommended) or any Java IDE
- Minimum 4GB RAM
- Windows/Mac/Linux OS
```

### Database Setup

```sql
1. Create database:
   CREATE DATABASE quanlycuahang CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

2. Import schema:
   mysql -u root -p quanlycuahang < database/quanlycuahang.sql

3. Verify tables:
   USE quanlycuahang;
   SHOW TABLES;  -- Should show 6 tables
```

### Application Configuration

```
1. Update database credentials in:
   src/database/JDBCUtil.java
   - url: jdbc:mySQL://localhost:3306/quanlycuahang
   - userName: your_mysql_username
   - password: your_mysql_password

2. Configure email settings (for password recovery):
   src/controller/SendEmailSMTP.java
   - SMTP server, port, credentials

3. Ensure all libraries are in classpath:
   - Right-click project > Properties > Libraries
   - Add all JAR files from lib/ folder
```

### Running the Application

```
Method 1 - NetBeans:
1. Open project in NetBeans
2. Clean and Build (F11)
3. Run Main Class: view.Login
4. Login with: admin / admin

Method 2 - Command Line:
1. Compile: javac -cp "lib/*" src/**/*.java
2. Run: java -cp "build/classes:lib/*" view.Login

Method 3 - JAR:
1. Build JAR: ant jar (or use NetBeans)
2. Run: java -jar dist/QuanLyCuaHang.jar
```

---

## System Requirements

### Minimum Requirements

- **CPU:** Dual-core 2.0 GHz
- **RAM:** 4 GB
- **Storage:** 500 MB free space
- **Display:** 1024x768 resolution
- **OS:** Windows 7+, macOS 10.12+, Linux (Ubuntu 16.04+)
- **Java:** JDK 8+
- **Database:** MySQL 5.7+

### Recommended Requirements

- **CPU:** Quad-core 2.5 GHz
- **RAM:** 8 GB
- **Storage:** 1 GB free space
- **Display:** 1920x1080 resolution
- **OS:** Windows 10+, macOS 11+, Linux (Ubuntu 20.04+)
- **Java:** JDK 11+
- **Database:** MySQL 8.0+

---

## Testing Credentials

```
Role: Admin
Username: admin
Password: admin
Access: Full system access

Role: Admin
Username: tin
Password: admin
Access: Full system access

Role: Quản lý kho
Username: vu
Password: 123
Access: Product & stock management

Role: Nhân viên nhập
Username: thao
Password: 123
Access: Import operations

Role: Nhân viên xuất
Username: phuong
Password: 123
Access: Export operations
```

---

## Strengths & Highlights

### ✅ Strengths

1. **Clean Architecture**

   - Well-organized MVC structure
   - Clear separation of concerns
   - Modular design

2. **Security**

   - BCrypt password hashing (industry standard)
   - Prepared statements (SQL injection prevention)
   - Role-based access control

3. **User Experience**

   - Modern UI with FlatLaf
   - Intuitive navigation
   - Role-specific interfaces
   - 67 custom icons

4. **Data Integrity**

   - Foreign key constraints
   - Transaction handling
   - Soft delete pattern
   - Auto-update inventory

5. **Comprehensive Features**

   - Complete CRUD operations
   - Advanced search
   - PDF report generation
   - Email notifications
   - Statistics & analytics

6. **Vietnamese Localization**

   - Full Vietnamese UI
   - Vietnamese font support (Roboto)
   - Currency formatting (VND)

7. **Sample Data**

   - Realistic test data
   - 24 actual phone models
   - Real Vietnamese suppliers
   - Complete transaction history

8. **Extensibility**
   - Generic DAO interface
   - Plugin architecture ready
   - Easy to add new features

---

## Potential Improvements & Future Enhancements

### 🔧 Suggested Improvements

1. **Technical Enhancements**

   - Migrate to Java 17 LTS
   - Implement connection pooling (HikariCP)
   - Add logging framework (Log4j2 or SLF4J)
   - Unit testing (JUnit 5)
   - Exception handling framework

2. **Database Improvements**

   - Add indexes for better query performance
   - Implement database backups
   - Add audit trail table
   - Version control for prices

3. **Security Enhancements**

   - Two-factor authentication (2FA)
   - Session timeout
   - Password complexity rules
   - Login attempt limiting
   - Encrypt database credentials

4. **Feature Additions**

   - Dashboard with charts (JFreeChart)
   - Barcode scanning support
   - Customer management module
   - Purchase orders & invoicing
   - Inventory alerts (low stock warnings)
   - Multi-branch support
   - Data export to Excel
   - Advanced reporting (sales trends, profit margins)
   - SMS notifications
   - Online ordering integration

5. **UI/UX Improvements**

   - Dark mode toggle
   - Customizable themes
   - Keyboard shortcuts
   - Drag-and-drop support
   - Multi-language support (English, Vietnamese)
   - Responsive design for tablets

6. **Performance Optimizations**

   - Lazy loading for large datasets
   - Pagination for tables
   - Caching frequently accessed data
   - Async operations for heavy tasks
   - Database query optimization

7. **DevOps & Deployment**
   - Docker containerization
   - CI/CD pipeline (Jenkins/GitHub Actions)
   - Automated database migrations (Flyway)
   - Configuration externalization
   - Installer creation (Launch4j, IzPack)

---

## Known Limitations

1. **Single Database Connection**

   - No connection pooling
   - Potential performance bottleneck under heavy load

2. **No Concurrent User Support**

   - Desktop application (single instance)
   - Not suitable for multiple simultaneous users

3. **Limited Scalability**

   - Designed for single store operation
   - No multi-branch support

4. **Basic Error Handling**

   - Some exceptions only print stack traces
   - Limited user-friendly error messages

5. **No Data Backup Feature**

   - Manual database backup required
   - No automated backup system

6. **Limited Reporting**
   - Basic statistics only
   - No advanced analytics or visualizations

---

## Conclusion

**QuanLyCuaHang** is a well-structured, feature-complete inventory management system specifically designed for mobile phone retail stores. The application demonstrates solid software engineering principles including:

- Clean MVC architecture
- Secure authentication and authorization
- Comprehensive CRUD operations
- Professional UI design
- Vietnamese localization

The system successfully handles the core business operations of a phone retail store including inventory management, supplier relations, import/export transactions, and basic analytics.

**Target Users:** Small to medium-sized phone retail stores in Vietnam

**Deployment:** Desktop application for on-premise installation

**Status:** Production-ready with sample data for immediate testing

**Estimated Development Time:** 2-3 months (team project)

---

## Project Statistics

```
Database:
- Tables: 6
- Sample Products: 24
- Sample Users: 5
- Sample Suppliers: 8
- Sample Transactions: 30 (15 imports + 15 exports)

Codebase:
- Model Classes: 8
- DAO Classes: 9
- Controller Classes: 7
- View Components: 56 (28 .java + 28 .form)
- Icons: 67 PNG files
- External Libraries: 10+ JAR files

Estimated Lines of Code:
- Models: ~800 lines
- DAOs: ~2,500 lines
- Controllers: ~1,200 lines
- Views: ~10,000+ lines (GUI code)
- Total: ~15,000+ lines
```

---

## Contact & Credits

**Project Type:** Academic Project  
**Institution:** UIT (University of Information Technology)  
**Course:** Java Programming / Software Engineering  
**Development Team:**

- Admin: Trương Trí Tín (tin@uit.com)
- Admin: (Additional team member)
- Stock Manager: Trần Hoàn Vũ (vu@uit.com)
- Import Staff: Lưu Thị Trúc Thảo (thao@uit.com)
- Export Staff: Võ Thị Lê Phương (phuong@uit.com)

**Year:** 2026

---

## License & Usage

This project is an academic exercise for educational purposes. All product names, brands, and company names mentioned are trademarks of their respective owners and used for demonstration purposes only.

---

**Document Version:** 1.0  
**Last Updated:** January 17, 2026  
**Document Author:** AI Analysis based on source code review
