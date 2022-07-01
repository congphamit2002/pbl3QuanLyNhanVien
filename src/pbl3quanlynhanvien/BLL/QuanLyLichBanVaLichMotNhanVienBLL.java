/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbl3quanlynhanvien.BLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import pbl3quanlynhanvien.DAL.DatabaseHelper;
import pbl3quanlynhanvien.DAL.LichBanDAO;
import pbl3quanlynhanvien.DAL.LichDAO;
import pbl3quanlynhanvien.DAL.QuanLyXepLichDAO;
import pbl3quanlynhanvien.DTO.Lich;
import pbl3quanlynhanvien.DTO.LichBan;
import pbl3quanlynhanvien.DTO.ShareData;

/**
 *
 * @author PC
 */
public class QuanLyLichBanVaLichMotNhanVienBLL {
    
    private static QuanLyLichBanVaLichMotNhanVienBLL Instance;
    
     public static QuanLyLichBanVaLichMotNhanVienBLL getInstance() {
        if(Instance == null)
        {
            Instance = new QuanLyLichBanVaLichMotNhanVienBLL();
        }
        return Instance;
    }

    private static void setInstance(QuanLyLichBanVaLichMotNhanVienBLL Instance) {
    }
    
    public void renderLichBanToTable(DefaultTableModel model)
    {
        List<LichBan> list = new ArrayList<>();
        
        list = LichBanDAO.getInstance().getAllLichBanCuaNhanVienVuaDangNhap();
        model.setRowCount(0);
        for (LichBan lichBan : list) {
            model.addRow(new Object[] {lichBan.getNgayban(), lichBan.getBuoiban()});
        }
        model.fireTableDataChanged();
    }
    
    public void renderLichLamViecMotNhanVienToTable(DefaultTableModel model)
    {
        List<Lich> list = new ArrayList<>();
        
        list = LichDAO.getInstance().getAllLichLamViecCuaNhanVienVuaDangNhap();
        model.setRowCount(0);
        for (Lich lich : list) {
            model.addRow(new Object[] {lich.getNgaylamviec(), lich.getBuoilam()});
        }
        model.fireTableDataChanged();
    }
            
    public boolean themLichBan(String day, String buoi)
    {
        String sql = "select id_lich from lich where ngaylamviec = ? and buoi = ?";
        String id_lich = "";
        ShareData share = new ShareData();
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(sql);) {
            psttm.setString(1, day);
            psttm.setString(2, buoi);
            
            ResultSet rs = psttm.executeQuery();
            if(rs.next())
            {
                id_lich = rs.getString("id_lich");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sql = "insert into ban(id_lich, id_nhanvien) values(?,?)";
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(sql);) {
            psttm.setString(1, id_lich);
            psttm.setString(2, share.getId_nhanvien());
            return psttm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
         
    public boolean xoaLichBan(String day, String buoi)
    {
        String sql = "select id_lich from lich where ngaylamviec = ? and buoi = ?";
        String id_lich = "";
        ShareData share = new ShareData();
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(sql);) {
            psttm.setString(1, day);
            psttm.setString(2, buoi);
            
            ResultSet rs = psttm.executeQuery();
            if(rs.next())
            {
                id_lich = rs.getString("id_lich");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sql = "delete from ban where id_lich = ? and id_nhanvien = ?";
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(sql);) {
            psttm.setString(1, id_lich);
            psttm.setString(2, share.getId_nhanvien());
            return psttm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
