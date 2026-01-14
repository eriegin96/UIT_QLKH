package controller;

import dao.DienThoaiDAO;
import java.util.ArrayList;
import model.DienThoai;

public class SearchDienThoai {

    public static SearchDienThoai getInstance() {
        return new SearchDienThoai();
    }

    public ArrayList<DienThoai> searchTatCa(String text) {
        ArrayList<DienThoai> result = new ArrayList<>();
        ArrayList<DienThoai> armt = DienThoaiDAO.getInstance().selectAllExist();
        for (var dt : armt) {
            if (dt.getTrangThai() == 1) {
                if (dt.getMaDienThoai().toLowerCase().contains(text.toLowerCase()) 
                        || dt.getTenDienThoai().toLowerCase().contains(text.toLowerCase())
                        || dt.getHang().toLowerCase().contains(text.toLowerCase())
                        || dt.getChipXuLy().toLowerCase().contains(text.toLowerCase())
                        || dt.getCameraChinh().toLowerCase().contains(text.toLowerCase())
                        || dt.getXuatXu().toLowerCase().contains(text.toLowerCase())) {
                    result.add(dt);
                }
            }
        }
        return result;
    }

    public ArrayList<DienThoai> searchMaDienThoai(String text) {
        ArrayList<DienThoai> result = new ArrayList<>();
        ArrayList<DienThoai> armt = DienThoaiDAO.getInstance().selectAllExist();
        for (var dt : armt) {
            if (dt.getTrangThai() == 1) {
                if (dt.getMaDienThoai().toLowerCase().contains(text.toLowerCase())) {
                    result.add(dt);
                }
            }
        }
        return result;
    }

    public ArrayList<DienThoai> searchTenDienThoai(String text) {
        ArrayList<DienThoai> result = new ArrayList<>();
        ArrayList<DienThoai> armt = DienThoaiDAO.getInstance().selectAllExist();
        for (var dt : armt) {
            if (dt.getTrangThai() == 1) {
                if (dt.getTenDienThoai().toLowerCase().contains(text.toLowerCase())) {
                    result.add(dt);
                }
            }
        }
        return result;
    }

    public ArrayList<DienThoai> searchSoLuong(String text) {
        ArrayList<DienThoai> result = new ArrayList<>();
        ArrayList<DienThoai> armt = DienThoaiDAO.getInstance().selectAllExist();
        for (var dt : armt) {
            if (dt.getTrangThai() == 1) {
                if (text.length() != 0) {
                    if (dt.getSoLuong() > Integer.parseInt(text)) {
                        result.add(dt);
                    }
                } else {
                    result.add(dt);
                }
            }
        }
        return result;
    }

    public ArrayList<DienThoai> searchDonGia(String text) {
        ArrayList<DienThoai> result = new ArrayList<>();
        ArrayList<DienThoai> armt = DienThoaiDAO.getInstance().selectAllExist();
        for (var dt : armt) {
            if (dt.getTrangThai() == 1) {
                if (text.length() != 0) {
                    if (dt.getGia() > Integer.parseInt(text)) {
                        result.add(dt);
                    }
                } else {
                    result.add(dt);
                }
            } 
        }
        return result;
    }

    public ArrayList<DienThoai> searchRam(String text) {
        ArrayList<DienThoai> result = new ArrayList<>();
        ArrayList<DienThoai> armt = DienThoaiDAO.getInstance().selectAllExist();
        for (var dt : armt) {
            if (dt.getRam().toLowerCase().contains(text.toLowerCase())) {
                result.add(dt);
            }
        }
        return result;
    }

    public ArrayList<DienThoai> searchChipXuLy(String text) {
        ArrayList<DienThoai> result = new ArrayList<>();
        ArrayList<DienThoai> armt = DienThoaiDAO.getInstance().selectAllExist();
        for (var dt : armt) {
            if (dt.getChipXuLy().toLowerCase().contains(text.toLowerCase())) {
                result.add(dt);
            }
        }
        return result;
    }

    public ArrayList<DienThoai> searchDungLuong(String text) {
        ArrayList<DienThoai> result = new ArrayList<>();
        ArrayList<DienThoai> armt = DienThoaiDAO.getInstance().selectAllExist();
        for (var dt : armt) {
            if (dt.getRom().toLowerCase().contains(text.toLowerCase())) {
                result.add(dt);
            }
        }
        return result;
    }

    public ArrayList<DienThoai> searchCamera(String text) {
        ArrayList<DienThoai> result = new ArrayList<>();
        ArrayList<DienThoai> armt = DienThoaiDAO.getInstance().selectAllExist();
        for (var dt : armt) {
            if (dt.getCameraChinh().toLowerCase().contains(text.toLowerCase())
                    || dt.getCameraPhu().toLowerCase().contains(text.toLowerCase())) {
                result.add(dt);
            }
        }
        return result;
    }

    public ArrayList<DienThoai> searchHang(String text) {
        ArrayList<DienThoai> result = new ArrayList<>();
        ArrayList<DienThoai> armt = DienThoaiDAO.getInstance().selectAllExist();
        for (var dt : armt) {
            if (dt.getHang().toLowerCase().contains(text.toLowerCase())) {
                result.add(dt);
            }
        }
        return result;
    }

    public ArrayList<DienThoai> searchXuatXu(String text) {
        ArrayList<DienThoai> result = new ArrayList<>();
        ArrayList<DienThoai> armt = DienThoaiDAO.getInstance().selectAllExist();
        for (var dt : armt) {
            if (dt.getXuatXu().toLowerCase().contains(text.toLowerCase())) {
                result.add(dt);
            }
        }
        return result;
    }

    public ArrayList<DienThoai> searchDaXoa(String text) {
        ArrayList<DienThoai> result = new ArrayList<>();
        ArrayList<DienThoai> armt = DienThoaiDAO.getInstance().selectAll();
        for (var dt : armt) {
            if (dt.getTrangThai() == 0) {
                if (dt.getMaDienThoai().toLowerCase().contains(text.toLowerCase())) {
                    result.add(dt);
                }
            }
        }
        return result;
    }

    public DienThoai searchId(String text) {
        DienThoai result = new DienThoai();
        ArrayList<DienThoai> armt = DienThoaiDAO.getInstance().selectAllExist();
        for (var dt : armt) {
            if (dt.getMaDienThoai().toLowerCase().contains(text.toLowerCase())) {
                return dt;
            }
        }
        return null;
    }
}
