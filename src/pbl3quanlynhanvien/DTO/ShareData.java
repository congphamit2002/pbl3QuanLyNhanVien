/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbl3quanlynhanvien.DTO;

/**
 *
 * @author PC
 */
public class ShareData {
    private static Users nguoiDangNhap;
    private static String id_nhanvien;

    public static Users getNguoiDangNhap() {
        return nguoiDangNhap;
    }

    public static void setNguoiDangNhap(Users nguoiDangNhap) {
        ShareData.nguoiDangNhap = nguoiDangNhap;
    }

    public String getId_nhanvien() {
        return id_nhanvien;
    }

    public void setId_nhanvien(String id_nhanvien) {
        this.id_nhanvien = id_nhanvien;
    }
}
