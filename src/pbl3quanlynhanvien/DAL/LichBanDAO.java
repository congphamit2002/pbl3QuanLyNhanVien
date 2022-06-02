/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbl3quanlynhanvien.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import pbl3quanlynhanvien.DTO.LichBan;
import pbl3quanlynhanvien.DTO.ShareData;

/**
 *
 * @author PC
 */
public class LichBanDAO {
    
    private static LichBanDAO Instance;
    
     public static LichBanDAO getInstance() {
        if(Instance == null)
        {
            Instance = new LichBanDAO();
        }
        return Instance;
    }

    private static void setInstance(LichBanDAO Instance) {
    }
    public List<LichBan> getAllLichBanCuaNhanVienVuaDangNhap()
    {
        List<LichBan> list = new ArrayList<>();
        
        String sql = "select lich.id_lich,lich.ngaylamviec, lich.buoi from lich "
                + "where lich.id_lich in "
                + "(select ban.id_lich from ban where id_nhanvien = ?) "
                + "order by lich.ngaylamviec asc";
        ShareData share = new ShareData();
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(sql);) {
            
            psttm.setString(1, share.getId_nhanvien());
            ResultSet rs =  psttm.executeQuery();
            while(rs.next())
            {
                LichBan lich = new LichBan();
                lich.setId_lich_ban(rs.getString("id_lich"));
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                String strDate = df.format(rs.getDate("ngaylamviec"));
                
                lich.setNgayban(strDate);
                lich.setBuoiban(rs.getString("buoi"));
                list.add(lich);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for (LichBan lichBan : list) {
            System.out.println("id_lich" + lichBan.getId_lich_ban());
            System.out.println("ngay ban" + lichBan.getNgayban());
            System.out.println("Buoi ban" + lichBan.getBuoiban());
        }
        return list;
    }
}
