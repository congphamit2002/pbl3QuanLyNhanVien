/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbl3quanlynhanvien.DAL;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import pbl3quanlynhanvien.DTO.Nhanvien_Lich;

/**
 *
 * @author PC
 */
public class NhanVien_LichDAO {
    
    private static NhanVien_LichDAO Instance;

    public static NhanVien_LichDAO getInstance() {
        if(Instance == null)
        {
            Instance = new NhanVien_LichDAO();
        }
        return Instance;
    }

    private static void setInstance(NhanVien_LichDAO Instance) {
    }
    
    public Nhanvien_Lich getNhanVienLichByIdLichAndIdNhanvien(int id_lich, String id_nhanvien){
        Nhanvien_Lich nv_lich = null;
        
        String sql = "select * from nhanvien_lich where id_lich = ? and id_nhanvien = ?";
        
        try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(sql)){
            psttm.setInt(1, id_lich);
            psttm.setString(2, id_nhanvien);
            
            ResultSet rs = psttm.executeQuery();
            if(rs.next())
            {
                nv_lich = new Nhanvien_Lich();
                nv_lich.setId_lich(rs.getInt("id_lich"));
                nv_lich.setId_nhanvien(rs.getString("id_nhanvien"));
                nv_lich.setThoigianbatdau(rs.getTimestamp("thoigianbatdau"));
                nv_lich.setThoigianketthuc(rs.getTimestamp("thoigianketthuc"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nv_lich;
    }
    
    public boolean updateThoiGianNhanVien_Lich(Nhanvien_Lich nvl)
    {
        String sql = "update nhanvien_lich set thoigianbatdau = ?, thoigianketthuc = ? where id_lich = ? and id_nhanvien = ?";
        
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(sql);) {
                psttm.setTimestamp(1, (Timestamp) nvl.getThoigianbatdau());
                psttm.setTimestamp(2, (Timestamp) nvl.getThoigianketthuc());
                psttm.setInt(3, nvl.getId_lich());
                psttm.setString(4, nvl.getId_nhanvien());
                
                return psttm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Nhanvien_Lich> getAllNhanVienLichByIDAndThang(String id_nhanvien, String thang)
    {
        List<Nhanvien_Lich> list = new ArrayList<>();
        String sql = "select * from nhanvien_lich "
                + "join lich on nhanvien_lich.id_lich = lich.id_lich "
                + "where id_nhanvien = ? and nhanvien_lich.id_lich in "
                + "(select lich.id_lich from lich where month(ngaylamviec) = ?)";
        
        try(    
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(sql);) {
            psttm.setString(1, id_nhanvien);
            psttm.setString(2, thang);
            ResultSet rs = psttm.executeQuery();
            
            while(rs.next())
            {
                Nhanvien_Lich nv_lich = new Nhanvien_Lich();
                nv_lich.setId_lich(rs.getInt("id_lich"));
                nv_lich.setId_nhanvien(rs.getString("id_nhanvien"));
                nv_lich.setThoigianbatdau(rs.getTimestamp("thoigianbatdau"));
                nv_lich.setThoigianketthuc(rs.getTimestamp("thoigianketthuc"));
                list.add(nv_lich);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public int getCountBuoiLamByIDAndThang(String id_nhanvien, String thang)
    {
        int count = 0;
        String sql = "select count(id_nhanvien) from nhanvien_lich "
                + "join lich on nhanvien_lich.id_lich = lich.id_lich "
                + "where id_nhanvien = ? and nhanvien_lich.id_lich in "
                + "(select lich.id_lich from lich where month(ngaylamviec) = ?)";
        
        try(    
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(sql);) {
            psttm.setString(1, id_nhanvien);
            psttm.setString(2, thang);
            ResultSet rs = psttm.executeQuery();
            
            while(rs.next())
            {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public List<Integer> getID_lichInCurrentDay(){
        List<Integer> list = new ArrayList<>();
        String sql = "select id_lich from lich where ngaylamviec = ?";
                    try(
                            Connection con = DatabaseHelper.openConnection();
                            PreparedStatement psttm = con.prepareStatement(sql);) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
                    LocalDateTime now = LocalDateTime.now();  
                    String day = dtf.format(now);
               
                    
                    psttm.setString(1, day);
                    ResultSet rs = psttm.executeQuery();
                    while(rs.next())
                    {
                         list.add(rs.getInt("id_lich"));
                    }
        } catch (Exception e) {
            e.printStackTrace();
        }
                    return list;
    }
    
    //list_id_lich_current_day get from getId_lichInCurrentDay
    public List<Integer> getAllIdLichCurentDayByIdNhanVien(String id_nhanvien, List<Integer> list_id_lich_current_day)
    {  
 
        List<Integer> list = new ArrayList<>();
        
            String sql = "select * from nhanvien_lich where id_lich = ? and id_nhanvien = ?";
            for (Integer integer : list_id_lich_current_day) {
            try(
                            Connection con = DatabaseHelper.openConnection();
                            PreparedStatement psttm = con.prepareStatement(sql);) {
                    
                    psttm.setInt(1, integer);
                    psttm.setString(2, id_nhanvien);
                    ResultSet rs = psttm.executeQuery();
                    if(rs.next())
                    {
                        list.add(rs.getInt("id_lich")) ;
                    }
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
                    
        
        return list;
        
    }
    
    public boolean checkTimeStartExist(int id_lich, String id_nhan_vien)
    {
        boolean flag = false;
        
        String sql = "select * from nhanvien_lich where id_lich = ? and id_nhanvien = ?";
        
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(sql);) {
            
            psttm.setInt(1, id_lich);
            psttm.setString(2, id_nhan_vien);
            ResultSet rs = psttm.executeQuery();
            
            if(rs.next())
            {
                if(rs.getTimestamp("thoigianbatdau") != null)
                {
                    flag = true;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return flag;
    }
    
    public boolean checkTimeEndtExist(int id_lich, String id_nhan_vien)
    {
        boolean flag = false;
        
        String sql = "select * from nhanvien_lich where id_lich = ? and id_nhanvien = ?";
        
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(sql);) {
            
            psttm.setInt(1, id_lich);
            psttm.setString(2, id_nhan_vien);
            ResultSet rs = psttm.executeQuery();
            
            if(rs.next())
            {
                if(rs.getTimestamp("thoigianketthuc") != null)
                {
                    flag = true;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return flag;
    }
}
