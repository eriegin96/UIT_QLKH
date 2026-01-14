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
    public String getMaDienThoai() {
        return maDienThoai;
    }

    public void setMaDienThoai(String maDienThoai) {
        this.maDienThoai = maDienThoai;
    }

    public String getTenDienThoai() {
        return tenDienThoai;
    }

    public void setTenDienThoai(String tenDienThoai) {
        this.tenDienThoai = tenDienThoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public String getDungLuongPin() {
        return dungLuongPin;
    }

    public void setDungLuongPin(String dungLuongPin) {
        this.dungLuongPin = dungLuongPin;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public double getKichThuocMan() {
        return kichThuocMan;
    }

    public void setKichThuocMan(double kichThuocMan) {
        this.kichThuocMan = kichThuocMan;
    }

    public String getCameraChinh() {
        return cameraChinh;
    }

    public void setCameraChinh(String cameraChinh) {
        this.cameraChinh = cameraChinh;
    }

    public String getCameraPhu() {
        return cameraPhu;
    }

    public void setCameraPhu(String cameraPhu) {
        this.cameraPhu = cameraPhu;
    }

    public String getChipXuLy() {
        return chipXuLy;
    }

    public void setChipXuLy(String chipXuLy) {
        this.chipXuLy = chipXuLy;
    }

    public String getHeDieuHanh() {
        return heDieuHanh;
    }

    public void setHeDieuHanh(String heDieuHanh) {
        this.heDieuHanh = heDieuHanh;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

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