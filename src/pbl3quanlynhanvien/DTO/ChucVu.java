/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbl3quanlynhanvien.DTO;

/**
 *
 * @author PC
 */
public class ChucVu {
    private int id_chucvu;
    private String tenchucvu;
    private double luongcoban;

    public int getId_chucvu() {
        return id_chucvu;
    }

    public void setId_chucvu(int id_chucvu) {
        this.id_chucvu = id_chucvu;
    }

    public String getTenchucvu() {
        return tenchucvu;
    }

    public void setTenchucvu(String tenchucvu) {
        this.tenchucvu = tenchucvu;
    }

    public double getLuongcoban() {
        return luongcoban;
    }

    public void setLuongcoban(double luongcoban) {
        this.luongcoban = luongcoban;
    }

    @Override
    public String toString() {
        return "ID: " + id_chucvu + " - Ten chuc vu: " + tenchucvu + " - luong co ban: " + luongcoban;
    }
    
    
}
