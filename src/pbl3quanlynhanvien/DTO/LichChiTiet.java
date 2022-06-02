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
public class LichChiTiet {
    private String buoi;
    private Date thoigianbatdau, thoigianketthuc;

    public String getBuoi() {
        return buoi;
    }

    public void setBuoi(String buoi) {
        this.buoi = buoi;
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
