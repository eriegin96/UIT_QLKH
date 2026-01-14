# Migration Plan: Computer Store → Mobile Phone Store

**Target:** Adapt QuanLyCuaHang project to use `quanlydienthoai.sql` database  
**Date:** January 14, 2026  
**Database Change:** `quanlimaytinh` → `quanlydienthoai`

---

## 📋 Overview

This document outlines the step-by-step process to migrate the Computer Warehouse Management System to a Mobile Phone Warehouse Management System.

### Key Changes:

- **Database:** `MayTinh` → `DienThoai`
- **Product Types:** Laptop/PC → Mobile Phones (various brands)
- **Fields:** Computer specs → Mobile phone specs
- **Product Count:** 28 mobile phones (10 brands: Apple, Samsung, Xiaomi, Honor, Nokia, Motorola, Sony, Vivo, OPPO, Redmi)

---

## ✅ What to KEEP (No Changes Needed)

### 1. **Account Management** (100% Reusable)

```
✅ QuanLyCuaHang/src/model/Account.java
✅ QuanLyCuaHang/src/dao/AccountDAO.java
✅ QuanLyCuaHang/src/controller/SearchAccount.java
✅ QuanLyCuaHang/src/view/AccountForm.java + .form
✅ QuanLyCuaHang/src/view/AddAccount.java + .form
✅ QuanLyCuaHang/src/view/UpdateAccount.java + .form
✅ QuanLyCuaHang/src/view/ChangePassword.java + .form
✅ QuanLyCuaHang/src/view/RecoverPassword.java + .form
```

**Reason:** Account structure is identical in both databases.

### 2. **Supplier Management** (100% Reusable)

```
✅ QuanLyCuaHang/src/model/NhaCungCap.java
✅ QuanLyCuaHang/src/dao/NhaCungCapDAO.java
✅ QuanLyCuaHang/src/controller/SearchNhaCungCap.java
✅ QuanLyCuaHang/src/view/NhaCungCapForm.java + .form
✅ QuanLyCuaHang/src/view/AddNhaCungCap.java + .form
✅ QuanLyCuaHang/src/view/UpdateNhaCungCap.java + .form
```

**Reason:** Supplier structure is identical (only data is different).

### 3. **Transaction Base Classes** (100% Reusable)

```
✅ QuanLyCuaHang/src/model/Phieu.java
✅ QuanLyCuaHang/src/model/PhieuNhap.java
✅ QuanLyCuaHang/src/model/PhieuXuat.java
✅ QuanLyCuaHang/src/model/ChiTietPhieu.java
✅ QuanLyCuaHang/src/dao/PhieuNhapDAO.java
✅ QuanLyCuaHang/src/dao/PhieuXuatDAO.java
✅ QuanLyCuaHang/src/dao/ChiTietPhieuNhapDAO.java
✅ QuanLyCuaHang/src/dao/ChiTietPhieuXuatDAO.java
```

**Reason:** Transaction structure is identical (only references product table).

### 4. **Utility Classes** (100% Reusable)

```
✅ QuanLyCuaHang/src/controller/BCrypt.java
✅ QuanLyCuaHang/src/controller/ConvertDate.java
✅ QuanLyCuaHang/src/controller/SendEmailSMTP.java
✅ QuanLyCuaHang/src/controller/WritePDF.java
✅ QuanLyCuaHang/src/database/JDBCUtil.java (needs DB name change only)
```

**Reason:** Generic utilities, not product-specific.

### 5. **UI Framework** (90% Reusable)

```
✅ QuanLyCuaHang/src/view/Login.java + .form
✅ QuanLyCuaHang/src/view/Admin.java + .form
✅ QuanLyCuaHang/src/view/QuanLiKho.java + .form
✅ QuanLyCuaHang/src/view/PhieuNhapForm.java + .form
✅ QuanLyCuaHang/src/view/PhieuXuatForm.java + .form
✅ QuanLyCuaHang/src/view/NhapHangForm.java + .form
✅ QuanLyCuaHang/src/view/XuatHangForm.java + .form
✅ QuanLyCuaHang/src/view/UpdatePhieuNhap.java + .form
✅ QuanLyCuaHang/src/view/UpdatePhieuXuat.java + .form
✅ QuanLyCuaHang/src/view/ThongKeForm.java + .form
✅ All icon files (41 .png files)
```

**Note:** Only labels need translation (Máy Tính → Điện Thoại).

---

## 🔧 What to MODIFY (Needs Adaptation)

### 1. **Product Model Classes** ⚠️ CRITICAL

**Location:** `QuanLyCuaHang/src/model/`

#### ❌ DELETE These Files:

```
❌ MayTinh.java      (Computer base class)
❌ Laptop.java       (Laptop subclass)
❌ PC.java           (Desktop PC subclass)
```

#### ✅ CREATE New File:

```java
// QuanLyCuaHang/src/model/DienThoai.java
package model;

import java.util.Objects;

public class DienThoai {
    // Primary fields
    private String maDienThoai;
    private String tenDienThoai;
    private int soLuong;
    private String hang;              // Brand (Apple, Samsung, etc.)

    // Specifications
    private String dungLuongPin;      // Battery capacity
    private String ram;
    private String rom;
    private double kichThuocMan;      // Screen size
    private String cameraChinh;       // Main camera
    private String cameraPhu;         // Front camera
    private String chipXuLy;          // Processor
    private String heDieuHanh;        // OS (iOS, Android)

    // Business fields
    private double gia;
    private String xuatXu;            // Origin
    private int trangThai;            // Status (1=active, 0=deleted)

    // Constructors
    public DienThoai() {}

    public DienThoai(String maDienThoai, String tenDienThoai, int soLuong,
                     String hang, String dungLuongPin, String ram, String rom,
                     double kichThuocMan, String cameraChinh, String cameraPhu,
                     String chipXuLy, String heDieuHanh, double gia,
                     String xuatXu, int trangThai) {
        this.maDienThoai = maDienThoai;
        this.tenDienThoai = tenDienThoai;
        this.soLuong = soLuong;
        this.hang = hang;
        this.dungLuongPin = dungLuongPin;
        this.ram = ram;
        this.rom = rom;
        this.kichThuocMan = kichThuocMan;
        this.cameraChinh = cameraChinh;
        this.cameraPhu = cameraPhu;
        this.chipXuLy = chipXuLy;
        this.heDieuHanh = heDieuHanh;
        this.gia = gia;
        this.xuatXu = xuatXu;
        this.trangThai = trangThai;
    }

    // Getters and Setters for all fields
    // ... (generate standard getters/setters)

    public void xuatHang(int sl) {
        this.soLuong -= sl;
    }

    public void nhapHang(int sl) {
        this.soLuong += sl;
    }

    @Override
    public String toString() {
        return "DienThoai{" +
                "maDienThoai='" + maDienThoai + '\'' +
                ", tenDienThoai='" + tenDienThoai + '\'' +
                ", hang='" + hang + '\'' +
                ", gia=" + gia +
                ", soLuong=" + soLuong +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DienThoai that = (DienThoai) o;
        return Objects.equals(maDienThoai, that.maDienThoai);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maDienThoai);
    }
}
```

### 2. **Product DAO Classes** ⚠️ CRITICAL

**Location:** `QuanLyCuaHang/src/dao/`

#### ❌ DELETE These Files:

```
❌ MayTinhDAO.java
❌ LaptopDAO.java
❌ PCDAO.java
```

#### ✅ CREATE New File:

```java
// QuanLyCuaHang/src/dao/DienThoaiDAO.java
package dao;

import database.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.DienThoai;

public class DienThoaiDAO implements DAOInterface<DienThoai> {

    public static DienThoaiDAO getInstance() {
        return new DienThoaiDAO();
    }

    @Override
    public int insert(DienThoai t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO DienThoai (maDienThoai, tenDienThoai, soLuong, " +
                        "hang, dungLuongPin, ram, rom, kichThuocMan, cameraChinh, " +
                        "cameraPhu, chipXuLy, heDieuHanh, gia, xuatXu, trangThai) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaDienThoai());
            pst.setString(2, t.getTenDienThoai());
            pst.setInt(3, t.getSoLuong());
            pst.setString(4, t.getHang());
            pst.setString(5, t.getDungLuongPin());
            pst.setString(6, t.getRam());
            pst.setString(7, t.getRom());
            pst.setDouble(8, t.getKichThuocMan());
            pst.setString(9, t.getCameraChinh());
            pst.setString(10, t.getCameraPhu());
            pst.setString(11, t.getChipXuLy());
            pst.setString(12, t.getHeDieuHanh());
            pst.setDouble(13, t.getGia());
            pst.setString(14, t.getXuatXu());
            pst.setInt(15, t.getTrangThai());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không thêm được " + t.getMaDienThoai(),
                                        "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    @Override
    public int update(DienThoai t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE DienThoai SET tenDienThoai=?, soLuong=?, hang=?, " +
                        "dungLuongPin=?, ram=?, rom=?, kichThuocMan=?, cameraChinh=?, " +
                        "cameraPhu=?, chipXuLy=?, heDieuHanh=?, gia=?, xuatXu=?, " +
                        "trangThai=? WHERE maDienThoai=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTenDienThoai());
            pst.setInt(2, t.getSoLuong());
            pst.setString(3, t.getHang());
            pst.setString(4, t.getDungLuongPin());
            pst.setString(5, t.getRam());
            pst.setString(6, t.getRom());
            pst.setDouble(7, t.getKichThuocMan());
            pst.setString(8, t.getCameraChinh());
            pst.setString(9, t.getCameraPhu());
            pst.setString(10, t.getChipXuLy());
            pst.setString(11, t.getHeDieuHanh());
            pst.setDouble(12, t.getGia());
            pst.setString(13, t.getXuatXu());
            pst.setInt(14, t.getTrangThai());
            pst.setString(15, t.getMaDienThoai());
            result = pst.executeUpdate(); // FIXED: No sql parameter
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(DienThoai t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE DienThoai SET trangThai = 0 WHERE maDienThoai=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaDienThoai());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<DienThoai> selectAll() {
        ArrayList<DienThoai> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM DienThoai";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DienThoai dt = new DienThoai(
                    rs.getString("maDienThoai"),
                    rs.getString("tenDienThoai"),
                    rs.getInt("soLuong"),
                    rs.getString("hang"),
                    rs.getString("dungLuongPin"),
                    rs.getString("ram"),
                    rs.getString("rom"),
                    rs.getDouble("kichThuocMan"),
                    rs.getString("cameraChinh"),
                    rs.getString("cameraPhu"),
                    rs.getString("chipXuLy"),
                    rs.getString("heDieuHanh"),
                    rs.getDouble("gia"),
                    rs.getString("xuatXu"),
                    rs.getInt("trangThai")
                );
                result.add(dt);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<DienThoai> selectAllExist() {
        ArrayList<DienThoai> result = new ArrayList<>();
        for (DienThoai dt : selectAll()) {
            if (dt.getTrangThai() == 1) {
                result.add(dt);
            }
        }
        return result;
    }

    @Override
    public DienThoai selectById(String id) {
        DienThoai result = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM DienThoai WHERE maDienThoai=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = new DienThoai(
                    rs.getString("maDienThoai"),
                    rs.getString("tenDienThoai"),
                    rs.getInt("soLuong"),
                    rs.getString("hang"),
                    rs.getString("dungLuongPin"),
                    rs.getString("ram"),
                    rs.getString("rom"),
                    rs.getDouble("kichThuocMan"),
                    rs.getString("cameraChinh"),
                    rs.getString("cameraPhu"),
                    rs.getString("chipXuLy"),
                    rs.getString("heDieuHanh"),
                    rs.getDouble("gia"),
                    rs.getString("xuatXu"),
                    rs.getInt("trangThai")
                );
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Additional helper methods
    public ArrayList<DienThoai> selectByBrand(String brand) {
        ArrayList<DienThoai> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM DienThoai WHERE hang=? AND trangThai=1";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, brand);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DienThoai dt = new DienThoai(
                    rs.getString("maDienThoai"),
                    rs.getString("tenDienThoai"),
                    rs.getInt("soLuong"),
                    rs.getString("hang"),
                    rs.getString("dungLuongPin"),
                    rs.getString("ram"),
                    rs.getString("rom"),
                    rs.getDouble("kichThuocMan"),
                    rs.getString("cameraChinh"),
                    rs.getString("cameraPhu"),
                    rs.getString("chipXuLy"),
                    rs.getString("heDieuHanh"),
                    rs.getDouble("gia"),
                    rs.getString("xuatXu"),
                    rs.getInt("trangThai")
                );
                result.add(dt);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
```

### 3. **Search Controller** ⚠️ CRITICAL

**Location:** `QuanLyCuaHang/src/controller/`

#### 🔄 MODIFY This File:

```
🔄 SearchProduct.java → Rename to SearchDienThoai.java
```

**Changes Needed:**

- Replace `MayTinh` with `DienThoai`
- Replace `MayTinhDAO` with `DienThoaiDAO`
- Update field names (e.g., `cardManHinh` → `cameraChinh`)
- Add new search methods for mobile-specific fields

### 4. **Database Configuration** ⚠️ CRITICAL

**Location:** `QuanLyCuaHang/src/database/JDBCUtil.java`

**Line 18 - Change:**

```java
// OLD:
String url = "jdbc:mySQL://localhost:3306/quanlimaytinh";

// NEW:
String url = "jdbc:mySQL://localhost:3306/quanlydienthoai";
```

**Line 16 - Update Driver (IMPORTANT):**

```java
// OLD (deprecated):
DriverManager.registerDriver(new com.mysql.jdbc.Driver());

// NEW:
DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
```

### 5. **Statistics DAO** ⚠️ MODERATE

**Location:** `QuanLyCuaHang/src/dao/ThongKeDAO.java`

**Changes Needed:**

- Update SQL queries: `MayTinh` → `DienThoai`
- Update field references: `maMay` → `maDienThoai`

### 6. **View Forms** ⚠️ MODERATE

**Location:** `QuanLyCuaHang/src/view/`

#### Files that need GUI updates:

```
🔄 ProductForm.java/.form           - Update table columns for phone specs
🔄 AddProduct.java/.form            - Update input fields for phone specs
🔄 UpdateProduct.java/.form         - Update input fields for phone specs
🔄 DetailProduct.java/.form         - Update display fields for phone specs
🔄 TonKhoForm.java/.form            - Update table columns
🔄 NhapKho.java/.form               - Update product selection/display
🔄 XuatKho.java/.form               - Update product selection/display
🔄 CTPhieuNhap.java/.form           - Update table columns
🔄 CTPhieuXuat.java/.form           - Update table columns
🔄 CTThongKe.java/.form             - Update statistics display
```

**GUI Changes Required:**

1. **Table Columns** - Replace:

   - `tenCpu` → `chipXuLy` (Processor)
   - `cardManHinh` → `cameraChinh` (Main Camera)
   - Add: `hang` (Brand)
   - Add: `cameraPhu` (Front Camera)
   - Add: `heDieuHanh` (Operating System)
   - Remove: `loaiMay` (no product type distinction)

2. **Input Forms** - Update JComboBox options:

   - Brand dropdown: Apple, Samsung, Xiaomi, Honor, Nokia, Motorola, Sony, Vivo, OPPO, Redmi
   - Operating System: iOS, Android

3. **Labels** - Update text:
   - "Máy Tính" → "Điện Thoại"
   - "Loại Máy" → "Hãng"
   - "CPU" → "Chip Xử Lý"
   - "Card Màn Hình" → "Camera"

---

## 📋 Step-by-Step Migration Plan

### Phase 1: Database Setup (Day 1)

```bash
1. ✅ Import quanlydienthoai.sql to MySQL
   - mysql -u root -p
   - CREATE DATABASE quanlydienthoai;
   - USE quanlydienthoai;
   - SOURCE /path/to/quanlydienthoai.sql;

2. ✅ Verify data
   - SELECT COUNT(*) FROM DienThoai;  # Should return 28
   - SELECT COUNT(*) FROM Account;    # Should return 5
```

### Phase 2: Model Layer Changes (Day 1-2)

```
Step 1: Delete old model files
   ❌ Delete: QuanLyCuaHang/src/model/MayTinh.java
   ❌ Delete: QuanLyCuaHang/src/model/Laptop.java
   ❌ Delete: QuanLyCuaHang/src/model/PC.java

Step 2: Create new model
   ✅ Create: QuanLyCuaHang/src/model/DienThoai.java
   ✅ Add all fields (maDienThoai, tenDienThoai, hang, etc.)
   ✅ Generate getters/setters
   ✅ Implement toString(), equals(), hashCode()

Step 3: Update statistics model
   🔄 Modify: QuanLyCuaHang/src/model/ThongKeProduct.java
   - Change references from MayTinh to DienThoai
```

### Phase 3: DAO Layer Changes (Day 2-3)

```
Step 1: Delete old DAO files
   ❌ Delete: QuanLyCuaHang/src/dao/MayTinhDAO.java
   ❌ Delete: QuanLyCuaHang/src/dao/LaptopDAO.java
   ❌ Delete: QuanLyCuaHang/src/dao/PCDAO.java

Step 2: Create new DAO
   ✅ Create: QuanLyCuaHang/src/dao/DienThoaiDAO.java
   ✅ Implement all CRUD operations
   ✅ Fix executeUpdate() bug (don't pass sql parameter)
   ✅ Add selectAllExist() method
   ✅ Add selectByBrand() helper method

Step 3: Update database config
   🔄 Modify: QuanLyCuaHang/src/database/JDBCUtil.java
   - Line 16: Update to com.mysql.cj.jdbc.Driver
   - Line 18: Change database name to quanlydienthoai

Step 4: Update transaction DAOs
   🔄 Modify: QuanLyCuaHang/src/dao/ChiTietPhieuNhapDAO.java
   - Update SQL: maMay → maDienThoai

   🔄 Modify: QuanLyCuaHang/src/dao/ChiTietPhieuXuatDAO.java
   - Update SQL: maMay → maDienThoai

Step 5: Update statistics DAO
   🔄 Modify: QuanLyCuaHang/src/dao/ThongKeDAO.java
   - Update SQL: MayTinh → DienThoai
   - Update SQL: maMay → maDienThoai
```

### Phase 4: Controller Layer Changes (Day 3)

```
Step 1: Rename and update search controller
   🔄 Rename: SearchProduct.java → SearchDienThoai.java
   - Replace: import model.MayTinh → import model.DienThoai
   - Replace: MayTinhDAO → DienThoaiDAO
   - Replace: ArrayList<MayTinh> → ArrayList<DienThoai>
   - Replace: MayTinh mt → DienThoai dt
   - Update: getCardManHinh() → getCameraChinh()
   - Add: new searchByBrand() method
   - Add: new searchByOS() method

Step 2: Delete test file
   ❌ Delete: QuanLyCuaHang/src/controller/Test.java

Step 3: Keep utility controllers (no changes needed)
   ✅ BCrypt.java
   ✅ ConvertDate.java
   ✅ SendEmailSMTP.java
   ✅ WritePDF.java
```

### Phase 5: View Layer Changes (Day 4-6)

```
Step 1: Update ProductForm (List View)
   🔄 ProductForm.java
   - Replace: MayTinh → DienThoai
   - Replace: MayTinhDAO → DienThoaiDAO
   - Update table model columns:
     • Mã → Tên → Hãng → RAM → ROM → Camera → Chip → Giá → Số Lượng

   🔄 ProductForm.form
   - Update JTable column headers
   - Resize columns for new fields

Step 2: Update AddProduct (Add New Product)
   🔄 AddProduct.java
   - Replace: MayTinh → DienThoai
   - Replace: LaptopDAO/PCDAO → DienThoaiDAO
   - Update: Remove loaiMay dropdown
   - Update: Add hang (brand) dropdown
   - Update: Change CPU field → chipXuLy field
   - Update: Replace cardManHinh → cameraChinh
   - Update: Add cameraPhu field
   - Update: Add heDieuHanh dropdown

   🔄 AddProduct.form
   - Remove: loaiMay JComboBox
   - Add: hang JComboBox (Apple, Samsung, Xiaomi, Honor, Nokia, Motorola, Sony)
   - Add: heDieuHanh JComboBox (iOS, Android)
   - Rename labels: CPU → Chip Xử Lý
   - Add: cameraPhu JTextField
   - Update: dungLuongPin (keep existing)
   - Update: kichThuocMan (keep existing)

Step 3: Update UpdateProduct (Edit Product)
   🔄 UpdateProduct.java
   - Same changes as AddProduct.java

   🔄 UpdateProduct.form
   - Same changes as AddProduct.form

Step 4: Update DetailProduct (View Details)
   🔄 DetailProduct.java
   - Replace: MayTinh → DienThoai
   - Update display fields

   🔄 DetailProduct.form
   - Update labels and text fields

Step 5: Update Inventory Forms
   🔄 TonKhoForm.java/.form
   - Update table columns

   🔄 NhapKho.java/.form
   - Update product selection table

   🔄 XuatKho.java/.form
   - Update product selection table

Step 6: Update Transaction Detail Forms
   🔄 CTPhieuNhap.java/.form
   - Update table: maMay → maDienThoai

   🔄 CTPhieuXuat.java/.form
   - Update table: maMay → maDienThoai

Step 7: Update Statistics Forms
   🔄 CTThongKe.java/.form
   - Update display for phone data

   🔄 CTThongKeAcc.java/.form
   - No changes (account-based)

Step 8: Update Main Dashboards
   🔄 Admin.java/.form
   - Update labels: "Quản Lý Máy Tính" → "Quản Lý Điện Thoại"

   🔄 QuanLiKho.java/.form
   - Update labels: "Kho Máy Tính" → "Kho Điện Thoại"
```

### Phase 6: Testing & Bug Fixes (Day 7-8)

```
Test 1: Database Connection
   ✅ Login to system
   ✅ Verify database connection works

Test 2: Product CRUD Operations
   ✅ List all phones (28 items)
   ✅ View phone details
   ✅ Add new phone
   ✅ Update phone information
   ✅ Delete phone (soft delete)
   ✅ Restore deleted phone

Test 3: Search Functionality
   ✅ Search by phone ID
   ✅ Search by phone name
   ✅ Search by brand
   ✅ Search by price range
   ✅ Search by RAM/ROM
   ✅ Search by processor

Test 4: Transaction Operations
   ✅ Create import receipt (Phiếu Nhập)
   ✅ Create export receipt (Phiếu Xuất)
   ✅ Update import receipt
   ✅ Update export receipt
   ✅ View transaction details
   ✅ Verify inventory updates

Test 5: Statistics & Reports
   ✅ View product statistics
   ✅ View sales statistics
   ✅ Export to PDF
   ✅ Export to Excel

Test 6: Account Management
   ✅ Create account
   ✅ Update account
   ✅ Change password
   ✅ Recover password (email OTP)
   ✅ Role-based access control
```

### Phase 7: Final Cleanup (Day 9)

```
Cleanup Tasks:
   ✅ Remove all unused import statements
   ✅ Fix compiler warnings
   ✅ Update comments and JavaDoc
   ✅ Test all user roles:
      - Admin: Full access
      - Quản lý kho: View all, limited edit
      - Nhân viên nhập: Import operations only
      - Nhân viên xuất: Export operations only
   ✅ Create user manual (if needed)
```

---

## 🗂️ File-by-File Checklist

### ❌ DELETE (8 files)

```
[ ] QuanLyCuaHang/src/model/MayTinh.java
[ ] QuanLyCuaHang/src/model/Laptop.java
[ ] QuanLyCuaHang/src/model/PC.java
[ ] QuanLyCuaHang/src/dao/MayTinhDAO.java
[ ] QuanLyCuaHang/src/dao/LaptopDAO.java
[ ] QuanLyCuaHang/src/dao/PCDAO.java
[ ] QuanLyCuaHang/src/controller/Test.java
[ ] QuanLyCuaHang/src/controller/SearchProduct.java (will be replaced)
```

### ✅ CREATE (2 files)

```
[ ] QuanLyCuaHang/src/model/DienThoai.java
[ ] QuanLyCuaHang/src/dao/DienThoaiDAO.java
[ ] QuanLyCuaHang/src/controller/SearchDienThoai.java
```

### 🔄 MODIFY (25 files)

```
Database:
[ ] QuanLyCuaHang/src/database/JDBCUtil.java (2 lines changed)

DAOs:
[ ] QuanLyCuaHang/src/dao/ChiTietPhieuNhapDAO.java
[ ] QuanLyCuaHang/src/dao/ChiTietPhieuXuatDAO.java
[ ] QuanLyCuaHang/src/dao/ThongKeDAO.java

Models:
[ ] QuanLyCuaHang/src/model/ThongKeProduct.java

Views (Java):
[ ] QuanLyCuaHang/src/view/ProductForm.java
[ ] QuanLyCuaHang/src/view/AddProduct.java
[ ] QuanLyCuaHang/src/view/UpdateProduct.java
[ ] QuanLyCuaHang/src/view/DetailProduct.java
[ ] QuanLyCuaHang/src/view/TonKhoForm.java
[ ] QuanLyCuaHang/src/view/NhapKho.java
[ ] QuanLyCuaHang/src/view/XuatKho.java
[ ] QuanLyCuaHang/src/view/CTPhieuNhap.java
[ ] QuanLyCuaHang/src/view/CTPhieuXuat.java
[ ] QuanLyCuaHang/src/view/CTThongKe.java
[ ] QuanLyCuaHang/src/view/Admin.java
[ ] QuanLyCuaHang/src/view/QuanLiKho.java

Views (Forms):
[ ] QuanLyCuaHang/src/view/ProductForm.form
[ ] QuanLyCuaHang/src/view/AddProduct.form
[ ] QuanLyCuaHang/src/view/UpdateProduct.form
[ ] QuanLyCuaHang/src/view/DetailProduct.form
[ ] QuanLyCuaHang/src/view/TonKhoForm.form
[ ] QuanLyCuaHang/src/view/NhapKho.form
[ ] QuanLyCuaHang/src/view/XuatKho.form
[ ] QuanLyCuaHang/src/view/Admin.form
[ ] QuanLyCuaHang/src/view/QuanLiKho.form
```

### ✅ KEEP AS-IS (30+ files)

```
All Account-related files
All NhaCungCap-related files
All Phieu base files
All utility controllers (BCrypt, SendEmail, WritePDF, ConvertDate)
All icon files (41 .png)
Login, ChangePassword, RecoverPassword forms
```

---

## 🔍 Quick Reference: Field Mapping

### Database Table Name

```
OLD: MayTinh
NEW: DienThoai
```

### Primary Key

```
OLD: maMay
NEW: maDienThoai
```

### Product Fields Comparison

| OLD (Computer)  | NEW (Mobile Phone) | Notes                 |
| --------------- | ------------------ | --------------------- |
| `maMay`         | `maDienThoai`      | Primary key           |
| `tenMay`        | `tenDienThoai`     | Product name          |
| `soLuong`       | `soLuong`          | ✅ Same               |
| `gia`           | `gia`              | ✅ Same               |
| `loaiMay`       | `hang`             | Type → Brand          |
| `tenCpu`        | `chipXuLy`         | Renamed               |
| `ram`           | `ram`              | ✅ Same               |
| `cardManHinh`   | `cameraChinh`      | GPU → Main Camera     |
| -               | `cameraPhu`        | ✅ NEW: Front camera  |
| -               | `heDieuHanh`       | ✅ NEW: OS            |
| `rom`           | `rom`              | ✅ Same               |
| `dungLuongPin`  | `dungLuongPin`     | ✅ Same (Laptop only) |
| `kichThuocMan`  | `kichThuocMan`     | ✅ Same (Laptop only) |
| `mainBoard`     | -                  | ❌ REMOVED (PC only)  |
| `congSuatNguon` | -                  | ❌ REMOVED (PC only)  |
| `xuatXu`        | `xuatXu`           | ✅ Same               |
| `trangThai`     | `trangThai`        | ✅ Same               |

---

## ⚠️ Common Pitfalls to Avoid

1. **Don't forget to update ChiTietPhieuNhap/Xuat foreign keys**

   - `maMay` → `maDienThoai` in SQL queries

2. **Update ALL references in view code**

   - Search for "MayTinh", "Laptop", "PC" across all files
   - Use Find & Replace (Ctrl+Shift+H in NetBeans)

3. **Test database connection first**

   - Ensure `quanlydienthoai` database exists
   - Verify credentials in JDBCUtil.java

4. **GUI Form files (.form) require NetBeans**

   - Open in NetBeans IDE Design view
   - Cannot edit .form files directly in text editor

5. **Remember to update MySQL driver**
   - Old driver won't work with newer MySQL versions
   - Add mysql-connector-j-8.2.0.jar to libraries

---

## 📚 Additional Resources

### SQL Verification Queries

```sql
-- Check phone count
SELECT COUNT(*) FROM DienThoai;

-- Check brands
SELECT DISTINCT hang FROM DienThoai;

-- Check inventory
SELECT maDienThoai, tenDienThoai, soLuong FROM DienThoai WHERE trangThai = 1;

-- Verify transactions
SELECT * FROM PhieuNhap ORDER BY thoiGianTao DESC LIMIT 5;
SELECT * FROM PhieuXuat ORDER BY thoiGianTao DESC LIMIT 5;
```

### Useful NetBeans Shortcuts

```
Ctrl+Shift+H  : Find & Replace in Project
Ctrl+Shift+O  : Organize Imports
Alt+Shift+F   : Format Code
F6            : Run Project
Shift+F6      : Run File
Ctrl+Space    : Code Completion
```

---

## ✅ Final Checklist

Before declaring migration complete:

```
[ ] All compiler errors fixed
[ ] All deprecated warnings addressed
[ ] Database connection successful
[ ] Can login with existing accounts
[ ] Can view all 28 phones
[ ] Can add new phone
[ ] Can update phone
[ ] Can delete phone (soft delete)
[ ] Can create import receipt
[ ] Can create export receipt
[ ] Inventory updates correctly
[ ] Search works for all fields
[ ] Statistics display correctly
[ ] PDF export works
[ ] Email notifications work
[ ] All user roles tested
[ ] No Test.java remnants
[ ] MySQL driver updated
[ ] Database name updated in JDBCUtil
```

---

**Estimated Total Time:** 7-9 days (56-72 hours)

- Model Layer: 8 hours
- DAO Layer: 12 hours
- Controller Layer: 6 hours
- View Layer: 24-32 hours
- Testing: 8-12 hours
- Bug fixes: 4-8 hours

**End of Migration Plan**
