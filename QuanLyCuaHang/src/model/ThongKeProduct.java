package model;

import java.util.Objects;

public class ThongKeProduct {
    private String maDienThoai;
    private String tenDienThoai;
    private int slNhap;
    private int slXuat;

    public ThongKeProduct() {
    }

    public ThongKeProduct(String maDienThoai, String tenDienThoai, int slNhap, int slXuat) {
        this.maDienThoai = maDienThoai;
        this.tenDienThoai = tenDienThoai;
        this.slNhap = slNhap;
        this.slXuat = slXuat;
    }

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

    public int getSlNhap() {
        return slNhap;
    }

    public void setSlNhap(int slNhap) {
        this.slNhap = slNhap;
    }

    public int getSlXuat() {
        return slXuat;
    }

    public void setSlXuat(int slXuat) {
        this.slXuat = slXuat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maDienThoai, tenDienThoai, slNhap, slXuat);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ThongKeProduct other = (ThongKeProduct) obj;
        if (this.slNhap != other.slNhap) {
            return false;
        }
        if (this.slXuat != other.slXuat) {
            return false;
        }
        if (!Objects.equals(this.maDienThoai, other.maDienThoai)) {
            return false;
        }
        return Objects.equals(this.tenDienThoai, other.tenDienThoai);
    }

    @Override
    public String toString() {
        return "ThongKeProduct{" + "maDienThoai=" + maDienThoai + ", tenDienThoai=" + tenDienThoai + ", slNhap=" + slNhap + ", slXuat=" + slXuat + '}';
    }
    
    
}
