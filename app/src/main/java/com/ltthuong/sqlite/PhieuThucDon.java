package com.ltthuong.sqlite;

public class PhieuThucDon {
    public int ID;
    public String soBan;
    public String thucDon;
    public String soLuong;
    public String donGia;
    public int tienPhuThu;
    public int thanhTien;
    public int tongTien;
    public int trangThai;
    public String ngayDat;

    public PhieuThucDon() {
    }
    public PhieuThucDon(int ID, String soBan, String thucDon, String soLuong, String donGia, int tienPhuThu, int thanhTien, int tongTien, String ngayDat, int trangThai) {

        this.ID = ID;
        this.soBan = soBan;
        this.thucDon = thucDon;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tienPhuThu = tienPhuThu;
        this.thanhTien = thanhTien;
        this.tongTien = tongTien;
        this.ngayDat = ngayDat;
        this.trangThai = trangThai;
    }
    public PhieuThucDon(String soBan, String thucDon, String soLuong, String donGia, int tienPhuThu, int thanhTien, int tongTien, String ngayDat, int trangThai) {
        this.soBan = soBan;
        this.thucDon = thucDon;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tienPhuThu = tienPhuThu;
        this.thanhTien = thanhTien;
        this.tongTien = tongTien;
        this.ngayDat = ngayDat;
        this.trangThai = trangThai;
    }

    public int getID( )
    {
        return ID;
    }
    public String getSoBan( )
    {
        return soBan;
    }
    public String getThucDon( )
    {
        return thucDon;
    }
    public String getSoLuong( )
    {
        return soLuong;
    }
    public String getDonGia( )
    {
        return donGia;
    }
    public int getTienPhuThu( )
    {
        return tienPhuThu;
    }
    public int getThanhTien( )
    {
        return thanhTien;
    }
    public int getTongTien( )
    {
        return tongTien;
    }
    public String getNgayDat( )
    {
        return ngayDat;
    }
    public int getTrangThai () {return trangThai;}

    public void setID (int ID)
    {
        this.ID = ID;
    }
    public void setSoBan(String soBan )
    {
        this.soBan = soBan;
    }
    public void setThucDon(String thucDon )
    {
        this.thucDon = thucDon;
    }
    public void setSoLuong(String soLuong )
    {
        this.soLuong = soLuong;
    }
    public void setDonGia(String donGia )
    {
        this.donGia = donGia;
    }
    public void setTienPhuThu(int tienPhuThu )
    {
        this.tienPhuThu = tienPhuThu;
    }
    public void setThanhTien(int thanhTien )
    {
        this.thanhTien = thanhTien;
    }
    public void setTongTien(int tongTien )
    {
        this.tongTien = tongTien;
    }
    public void setNgayDat(String ngayDat )
    {
        this.ngayDat = ngayDat;
    }
    public void setTrangThai(int trangThai )
    {
        this.trangThai = trangThai;
    }

}

