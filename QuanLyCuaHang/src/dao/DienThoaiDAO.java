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