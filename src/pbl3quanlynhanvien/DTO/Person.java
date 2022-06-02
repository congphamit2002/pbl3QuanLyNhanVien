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
public class Person {
    private String id_nhanvien, hoten, sdt, diachi, cccd, email, username;
    private int socalam,id_chucvu;
    private boolean gioitinh;
    private Date ngaysinh;

    public String getId_nhanvien() {
        return id_nhanvien;
    }

    public void setId_nhanvien(String id_nhanvien) {
        this.id_nhanvien = id_nhanvien;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSocalam() {
        return socalam;
    }

    public void setSocalam(int socalam) {
        this.socalam = socalam;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }



    public int getId_chucvu() {
        return id_chucvu;
    }

    public void setId_chucvu(int id_chucvu) {
        this.id_chucvu = id_chucvu;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    @Override
    public String toString() {
        return "Id nhan vien: " + id_nhanvien + " - Ho ten: " + hoten + " - username: " + username + " - Dia chi: " + diachi
                + " - cccd: " + cccd + " - ngay sinh: " + ngaysinh + " - Gioi tinh: " + gioitinh + " - Id chuc vu : " + id_chucvu
                + " - sdt: " + sdt + " - email " + email
                ;
    }
    
    
}
