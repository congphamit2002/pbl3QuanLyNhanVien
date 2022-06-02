/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbl3quanlynhanvien.DTO;

import java.util.Date;

/**
 *
 * @author PC
 */
public class Nhanvien_Lich {
    private String id_nhanvien;
    private int id_lich;
    private Date thoigianbatdau, thoigianketthuc;

    public String getId_nhanvien() {
        return id_nhanvien;
    }

    public void setId_nhanvien(String id_nhanvien) {
        this.id_nhanvien = id_nhanvien;
    }

    public int getId_lich() {
        return id_lich;
    }

    public void setId_lich(int id_lich) {
        this.id_lich = id_lich;
    }

    public Date getThoigianbatdau() {
        return thoigianbatdau;
    }

    public void setThoigianbatdau(Date thoigianbatdau) {
        this.thoigianbatdau = thoigianbatdau;
    }

    public Date getThoigianketthuc() {
        return thoigianketthuc;
    }

    public void setThoigianketthuc(Date thoigianketthuc) {
        this.thoigianketthuc = thoigianketthuc;
    }
    
    
}
