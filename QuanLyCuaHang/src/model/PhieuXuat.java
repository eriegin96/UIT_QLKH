package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;

public class PhieuXuat extends Phieu{

    public PhieuXuat() {
    }  

    public PhieuXuat(String maPhieu, Timestamp thoiGianTao, String nguoiTao, ArrayList<ChiTietPhieu> CTPhieu, double tongTien) {
        super(maPhieu, thoiGianTao, nguoiTao, CTPhieu, tongTien);
    }

    @Override
    public String toString() {
        return "PhieuXuat{" + "maPhieu=" + this.getMaPhieu() + ", tongTien=" + this.getTongTien() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaPhieu(), getTongTien());
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
        final PhieuXuat other = (PhieuXuat) obj;
        return Objects.equals(this.getMaPhieu(), other.getMaPhieu()) 
                && Double.doubleToLongBits(this.getTongTien()) == Double.doubleToLongBits(other.getTongTien());
    }
    
}
