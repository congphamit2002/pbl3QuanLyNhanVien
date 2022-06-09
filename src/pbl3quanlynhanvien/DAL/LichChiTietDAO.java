/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbl3quanlynhanvien.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pbl3quanlynhanvien.DTO.LichChiTiet;

/**
 *
 * @author PC
 */
public class LichChiTietDAO {
    
    private static LichChiTietDAO Instance;
    
     public static LichChiTietDAO getInstance() {
        if(Instance == null)
        {
            Instance = new LichChiTietDAO();
        }
        return Instance;
    }

    private static void setInstance(LichChiTietDAO Instance) {
    }
    
    public List<LichChiTiet> getAllLichChiTiet()
    {
        List<LichChiTiet> list = new ArrayList<>();
        String sql = "select * from lich_chi_tiet";
        try (Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(sql);
                ) {
            ResultSet rs = psttm.executeQuery();
            
            while(rs.next())
            {
                LichChiTiet lich = new LichChiTiet();
                lich.setBuoi(rs.getString("buoi"));
                lich.setThoigianbatdau(rs.getTime("thoigianbatdau"));
                lich.setThoigianketthuc(rs.getTime("thoigianketthuc"));
                list.add(lich);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
