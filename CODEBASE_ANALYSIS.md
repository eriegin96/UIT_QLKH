# Codebase Analysis: UIT_QLKH (Quản Lý Kho Hàng Máy Tính)

**Generated:** January 14, 2026  
**Project:** Computer Warehouse Management System  
**Technology Stack:** Java Swing, MySQL, JDBC

---

## 📋 Executive Summary

This is a **Computer Warehouse Management System** built with Java Swing for desktop GUI. The application manages inventory of computers (Laptops & PCs), tracks import/export transactions, manages suppliers, handles user accounts with role-based access control, and provides statistical reporting.

### Key Features:

- ✅ Product Management (Laptops & Desktop PCs)
- ✅ Import/Export Transaction Management
- ✅ Supplier Management
- ✅ User Account & Role Management (Admin, Warehouse Manager, Import Staff, Export Staff)
- ✅ Statistical Reporting & Analytics
- ✅ PDF Export functionality
- ✅ Email notifications (password recovery with OTP)
- ✅ BCrypt password hashing for security

---

## 🏗️ Project Structure

```
netbeans/src/
├── controller/         # Business logic & utilities (8 files)
├── dao/               # Data Access Objects (11 files)
├── database/          # Database connection utility (1 file)
├── icon/              # UI icons (41 PNG files)
├── model/             # Entity/Domain models (10 files)
└── view/              # Swing GUI forms (29 forms + 29 Java files)
```

---

## 📦 Components Analysis

### 1. **Model Layer** (`model/`)

#### Core Entities:

| Entity           | Purpose                                     | Status    |
| ---------------- | ------------------------------------------- | --------- |
| `MayTinh`        | Base class for all computers                | ✅ Active |
| `Laptop`         | Extends MayTinh, adds screen size & battery | ✅ Active |
| `PC`             | Extends MayTinh, adds mainboard & PSU       | ✅ Active |
| `Account`        | User accounts with roles                    | ✅ Active |
| `NhaCungCap`     | Supplier information                        | ✅ Active |
| `Phieu`          | Base class for transactions                 | ✅ Active |
| `PhieuNhap`      | Import receipt                              | ✅ Active |
| `PhieuXuat`      | Export receipt                              | ✅ Active |
| `ChiTietPhieu`   | Transaction line items                      | ✅ Active |
| `ThongKeProduct` | Statistics model                            | ✅ Active |

#### Issues & Observations:

- ✅ **Good:** Uses inheritance (MayTinh → Laptop/PC)
- ⚠️ **Issue:** `MayTinh` has an empty constructor at line 26 that serves no purpose
- ⚠️ **Issue:** Inconsistent naming: `Rom` should be `rom` (violates Java naming conventions)
- ⚠️ **Issue:** Missing JavaDoc comments on most classes

### 2. **DAO Layer** (`dao/`)

#### Data Access Objects:

| DAO                   | Purpose                    | Implementation Status       |
| --------------------- | -------------------------- | --------------------------- |
| `DAOInterface`        | Generic CRUD interface     | ✅ Complete                 |
| `MayTinhDAO`          | Computer CRUD operations   | ⚠️ insert() not implemented |
| `LaptopDAO`           | Laptop-specific operations | ✅ Complete                 |
| `PCDAO`               | PC-specific operations     | ✅ Complete                 |
| `AccountDAO`          | User account operations    | ✅ Complete                 |
| `NhaCungCapDAO`       | Supplier operations        | ✅ Complete                 |
| `PhieuNhapDAO`        | Import receipt operations  | ✅ Complete                 |
| `PhieuXuatDAO`        | Export receipt operations  | ✅ Complete                 |
| `ChiTietPhieuNhapDAO` | Import details             | ✅ Complete                 |
| `ChiTietPhieuXuatDAO` | Export details             | ✅ Complete                 |
| `ThongKeDAO`          | Statistics queries         | ✅ Complete                 |

#### Issues & Observations:

- ❌ **Critical Bug:** `MayTinhDAO.update()` line 48 has wrong method call:
  ```java
  ketqua = pst.executeUpdate(sql); // WRONG - sql parameter should not be passed
  ```
  Should be: `ketqua = pst.executeUpdate();`
- ⚠️ **Issue:** `MayTinhDAO.insert()` throws `UnsupportedOperationException` - not implemented
- ⚠️ **Redundant:** Both `MayTinhDAO`, `LaptopDAO`, and `PCDAO` exist - should consolidate
- ⚠️ **Missing:** No connection pooling - creates new connection for each operation
- ⚠️ **Missing:** No transaction management for multi-step operations
- ⚠️ **Missing:** No prepared statement parameter validation

### 3. **Database Layer** (`database/`)

#### JDBCUtil.java

```java
Database: quanlimaytinh
Host: localhost:3306
User: root
Password: (empty)
Driver: com.mysql.jdbc.Driver (deprecated)
```

#### Critical Issues:

- ❌ **Deprecated Driver:** Using old `com.mysql.jdbc.Driver`
  - Should use: `com.mysql.cj.jdbc.Driver`
- ❌ **Hardcoded Credentials:** Database credentials in source code
  - Should use: Properties file or environment variables
- ❌ **No Connection Pool:** Creates new connection each time
  - Should use: HikariCP or Apache DBCP2
- ⚠️ **Commented Error Handler:** Line 25 has commented JOptionPane error message
  - Should properly log errors or show user-friendly messages
- ⚠️ **Security Risk:** Empty database password in production code

### 4. **Controller Layer** (`controller/`)

| Controller         | Purpose                       | Status                 |
| ------------------ | ----------------------------- | ---------------------- |
| `BCrypt`           | Password hashing (737 lines!) | ✅ Works but oversized |
| `ConvertDate`      | Date formatting utilities     | ✅ Active              |
| `SearchAccount`    | Account search functionality  | ✅ Active              |
| `SearchNhaCungCap` | Supplier search               | ✅ Active              |
| `SearchProduct`    | Product search (14 methods!)  | ⚠️ Needs refactoring   |
| `SendEmailSMTP`    | Email notifications           | ✅ Active              |
| `WritePDF`         | PDF export functionality      | ✅ Active              |
| `Test`             | Testing class                 | ❌ Should be removed   |

#### Issues & Observations:

**BCrypt.java (737 lines):**

- ✅ **Good:** Provides secure password hashing
- ⚠️ **Issue:** Should be external library (use `org.mindrot:jbcrypt` or Spring Security)
- 📦 **Recommendation:** Replace with Maven dependency

**SearchProduct.java:**

- ⚠️ **Code Duplication:** 14 nearly identical search methods
- ⚠️ **Performance:** Fetches all data then filters in memory
- ⚠️ **Should:** Use SQL WHERE clauses instead
- 📦 **Recommendation:** Refactor to single parameterized search method

**Test.java:**

- ❌ **Should Remove:** Contains test code with hardcoded email
- ❌ **Security Risk:** Exposes email address in source
- 📦 **Recommendation:** Delete this file entirely

### 5. **View Layer** (`view/`)

#### GUI Components (29 Forms):

**Main Windows:**

- `Login` - User authentication
- `Admin` - Admin dashboard
- `QuanLiKho` - Warehouse manager dashboard

**Product Management:**

- `ProductForm` - Product listing
- `AddProduct` - Add new product
- `UpdateProduct` - Edit product
- `DetailProduct` - View product details
- `TonKhoForm` - Inventory status

**Transaction Management:**

- `PhieuNhapForm` - Import receipts list
- `PhieuXuatForm` - Export receipts list
- `NhapHangForm` - Create import
- `XuatHangForm` - Create export
- `NhapKho` - Import to warehouse
- `XuatKho` - Export from warehouse
- `CTPhieuNhap` - Import details
- `CTPhieuXuat` - Export details
- `UpdatePhieuNhap` - Edit import
- `UpdatePhieuXuat` - Edit export

**Supplier Management:**

- `NhaCungCapForm` - Supplier list
- `AddNhaCungCap` - Add supplier
- `UpdateNhaCungCap` - Edit supplier

**Account Management:**

- `AccountForm` - User list
- `AddAccount` - Add user
- `UpdateAccount` - Edit user
- `ChangePassword` - Change password
- `RecoverPassword` - Password recovery

**Statistics:**

- `ThongKeForm` - Statistics dashboard
- `CTThongKe` - Product statistics details
- `CTThongKeAcc` - Account statistics

#### Issues & Observations:

- ✅ **Good:** Comprehensive UI coverage
- ⚠️ **Too Many Forms:** 29 forms might indicate lack of reusability
- ⚠️ **Naming:** Mix of Vietnamese and English naming
- 📦 **Recommendation:** Consider using dialog boxes instead of separate forms

---

## 🐛 Critical Issues to Fix

### Priority 1: MUST FIX

1. **MayTinhDAO.update() Bug (Line 48)**

   ```java
   // WRONG:
   ketqua = pst.executeUpdate(sql);
   // CORRECT:
   ketqua = pst.executeUpdate();
   ```

2. **Update MySQL Driver**

   ```java
   // OLD (deprecated):
   DriverManager.registerDriver(new com.mysql.jdbc.Driver());
   // NEW:
   DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
   ```

3. **Remove Hardcoded Database Credentials**
   - Move to `config.properties` file
   - Use environment variables for production

### Priority 2: SHOULD FIX

4. **Implement MayTinhDAO.insert()**

   - Currently throws UnsupportedOperationException

5. **Refactor SearchProduct**

   - Consolidate 14 methods into parameterized search
   - Move filtering to SQL queries

6. **Add Connection Pooling**

   - Implement HikariCP or C3P0

7. **Fix Naming Conventions**
   - `Rom` → `rom` in MayTinh
   - Consistent use of English or Vietnamese

### Priority 3: NICE TO HAVE

8. **Add Logging Framework**

   - Replace printStackTrace() with Log4j2 or SLF4J

9. **Add Input Validation**

   - Validate user inputs before database operations

10. **Add Transaction Management**
    - Wrap multi-step operations in transactions

---

## 🗑️ Should Be Removed

### Files to Delete:

1. **`controller/Test.java`** ❌

   - Contains test code
   - Hardcoded email address
   - Not used in production

2. **Empty/Unused Constructors** ⚠️
   - `MayTinh` line 26: Empty constructor with parameters

### Consider Removing:

3. **`controller/BCrypt.java`** 📦
   - 737 lines of code
   - Should use external library instead
   - Replace with Maven dependency: `org.mindrot:jbcrypt:0.4`

---

## ♻️ Redundant Code

### DAO Layer Redundancy:

- **MayTinhDAO + LaptopDAO + PCDAO** are redundant
  - All operate on same `MayTinh` table
  - `LaptopDAO` and `PCDAO` add `loaiMay` field to differentiate
  - **Recommendation:** Keep only `LaptopDAO` and `PCDAO`, remove `MayTinhDAO`

### Search Methods Redundancy:

- **SearchProduct** has 14 almost identical methods
  - All follow same pattern: fetch all → filter in memory
  - **Recommendation:** Single parameterized method with SQL WHERE clause

### View Layer Redundancy:

- Multiple forms for similar operations (Add, Update, Detail)
  - **Recommendation:** Use single form with different modes

---

## 📊 Code Quality Metrics

| Metric             | Count | Status                 |
| ------------------ | ----- | ---------------------- |
| Total Java Files   | 59    | ✅                     |
| Model Classes      | 10    | ✅                     |
| DAO Classes        | 11    | ⚠️ Some incomplete     |
| Controller Classes | 8     | ⚠️ 1 should be removed |
| View Forms         | 29    | ⚠️ Too many            |
| Critical Bugs      | 2     | ❌ Must fix            |
| Code Smells        | 8+    | ⚠️ Should refactor     |
| Security Issues    | 3     | ❌ Must fix            |

---

## 🎯 Recommendations

### Immediate Actions:

1. ✅ Fix `MayTinhDAO.update()` bug
2. ✅ Update MySQL JDBC driver
3. ✅ Remove `Test.java`
4. ✅ Move database credentials to config file
5. ✅ Implement `MayTinhDAO.insert()`

### Short-term Improvements:

1. 📦 Add connection pooling (HikariCP)
2. 📦 Replace BCrypt.java with external library
3. 📦 Refactor SearchProduct methods
4. 📦 Add logging framework
5. 📦 Add input validation layer

### Long-term Enhancements:

1. 🚀 Migrate to Spring Boot for better architecture
2. 🚀 Add REST API layer for mobile/web clients
3. 🚀 Implement caching (Redis/Ehcache)
4. 🚀 Add automated testing (JUnit 5)
5. 🚀 Migrate to modern UI framework (JavaFX or web-based)

---

## 🔐 Security Concerns

1. **Empty Database Password** ❌

   - Production risk
   - Should enforce strong passwords

2. **Hardcoded Credentials** ❌

   - Exposed in source code
   - Should use environment variables

3. **No SQL Injection Protection** ⚠️

   - Uses PreparedStatement (Good!)
   - But no input sanitization

4. **Email Exposed in Test.java** ❌

   - Should be removed

5. **No Session Management** ⚠️
   - Consider adding session timeout

---

## 📚 Dependencies Needed

Current: No external dependencies (all code embedded)

### Recommended Maven Dependencies:

```xml
<!-- MySQL Connector (Updated) -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.2.0</version>
</dependency>

<!-- BCrypt -->
<dependency>
    <groupId>org.mindrot</groupId>
    <artifactId>jbcrypt</artifactId>
    <version>0.4</version>
</dependency>

<!-- Connection Pool -->
<dependency>
    <groupId>com.zaxxer</groupId>
    <artifactId>HikariCP</artifactId>
    <version>5.1.0</version>
</dependency>

<!-- Logging -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>2.0.9</version>
</dependency>

<!-- PDF Generation (iText already included) -->
<!-- Email (JavaMail already included) -->
<!-- Excel (Apache POI already included) -->
```

---

## 🎓 Learning Points

### What This Code Does Well:

✅ Clear separation of concerns (MVC pattern)  
✅ Use of PreparedStatements (SQL injection protection)  
✅ Password hashing with BCrypt  
✅ Role-based access control  
✅ Comprehensive feature set

### Areas for Improvement:

⚠️ Error handling and logging  
⚠️ Code duplication  
⚠️ Hardcoded values  
⚠️ No automated testing  
⚠️ Limited documentation

---

## 📝 Conclusion

This is a **functional but improvable** warehouse management system. The core architecture is solid with proper MVC separation, but there are several critical bugs, security concerns, and code quality issues that should be addressed.

**Overall Grade: B- (75/100)**

- Functionality: A (90/100)
- Code Quality: C+ (70/100)
- Security: C (65/100)
- Maintainability: B- (75/100)

**Primary Focus Areas:**

1. Fix critical bugs immediately
2. Improve security (database credentials, validation)
3. Reduce code redundancy
4. Add proper error handling and logging
5. Consider modernization path

---

**End of Analysis**
