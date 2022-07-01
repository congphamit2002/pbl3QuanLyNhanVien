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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pbl3quanlynhanvien.DTO.Lich;
import pbl3quanlynhanvien.DTO.LichBan;
import pbl3quanlynhanvien.DTO.ShareData;

/**
 *
 * @author PC
 */
public class LichDAO {
    private static LichDAO Instance;
    
     public static LichDAO getInstance() {
        if(Instance == null)
        {
            Instance = new LichDAO();
        }
        return Instance;
    }

    private static void setInstance(LichDAO Instance) {
    }
    public List<Lich> getAllLichLamViecCuaNhanVienVuaDangNhap()
    {
        List<Lich> list = new ArrayList<>();
        
        String sql = "select lich.id_lich, lich.ngaylamviec, lich.buoi from lich "
                + "join nhanvien_lich on nhanvien_lich.id_lich = lich.id_lich "
                + "where nhanvien_lich.id_nhanvien = ?  and month(ngaylamviec) = ?";
        ShareData share = new ShareData();
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(sql);) {
            
            psttm.setString(1, share.getId_nhanvien());
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");  
                    LocalDateTime now = LocalDateTime.now();  
                    String month = dtf.format(now);
                    
            psttm.setString(2, month);
            ResultSet rs =  psttm.executeQuery();
            while(rs.next())
            {
                Lich lich = new Lich();
                lich.setId_lich(rs.getInt("id_lich"));
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                String strDate = df.format(rs.getDate("ngaylamviec"));
                
                lich.setNgaylamviec(strDate);
                lich.setBuoilam(rs.getString("buoi"));
                list.add(lich);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    //Khong can
    
    public String checkBuoiLam(Date time)
    {
        try {
            String time1 = "00:00:00";
            String time2 = "12:00:00";
            String time3 = "17:30:00";
            String time4 = "23:59:59";

            DateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date date1 = format.parse(time1);
            Date date2 = format.parse(time2);
            Date date3 = format.parse(time3);
            Date date4 = format.parse(time4);
            
            if(time.getTime() - date1.getTime() >= 0 && time.getTime() - date2.getTime() < 0)
                {
                    return "Sang";
                } else if(time.getTime() - date2.getTime() >= 0 && time.getTime() - date3.getTime() < 0)
                {
                    return "Chieu";
                }
                else if(time.getTime() - date3.getTime() >= 0 && time.getTime() - date4.getTime() < 0)
                {
                    return "Toi";
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public int getID_lichByNgayVaBuoi(String buoi){
        
                    int id_lich =0;
            String sql = "select id_lich from lich where ngaylamviec = ? and buoi = ?";
                    try(
                            Connection con = DatabaseHelper.openConnection();
                            PreparedStatement psttm = con.prepareStatement(sql);) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
                    LocalDateTime now = LocalDateTime.now();  
                    String day = dtf.format(now);
                    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                    
                         Date date = new Date();
                    Date timeNow = dateFormat.parse(dateFormat.format(date));
                    psttm.setString(1, day);
                    psttm.setString(2, buoi);
                    ResultSet rs = psttm.executeQuery();
                    if(rs.next())
                    {
                         id_lich = rs.getInt("id_lich");
                    }
        } catch (Exception e) {
            e.printStackTrace();
        }
                    return id_lich;
    }
    
    public String getBuoiLamByID_Lich(int id_lich)
    {
        String buoi = "";
            String sql = "select buoi from lich where id_lich = ? order by id_lich asc";
                    try(
                            Connection con = DatabaseHelper.openConnection();
                            PreparedStatement psttm = con.prepareStatement(sql);) {
                    psttm.setInt(1, id_lich);
                    ResultSet rs = psttm.executeQuery();
                    if(rs.next())
                    {
                         buoi = rs.getString("buoi");
                    }
        } catch (Exception e) {
            e.printStackTrace();
        }
                    return buoi;
    }
    
    
           
    public int getIdLichDauThang()
    {
        String sql = "select id_lich from lich where ngaylamviec = ? and buoi = 'Sang'";
        
                int id = -1; 
           try(Connection con = DatabaseHelper.openConnection();
                   PreparedStatement psttm = con.prepareStatement(sql);) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-01");
                Date date = new Date();
                String s = dateFormat.format(date);
                psttm.setString(1, s);
                ResultSet rs = psttm.executeQuery();
                if(rs.next())
                {
                    id = rs.getInt(1);
                    System.out.println("id " + rs.getInt(1));
                }
           } catch (Exception e) {
           }
           return id;
    }
    
    
    public void insertLichInCurrentMonth() {
        String sql = "insert into lich(ngaylamviec, buoi) values(?,?)";

        if(LichDAO.getInstance().checkLichCurrentMonth()){
            return;
        }
        try (
                 Connection con = DatabaseHelper.openConnection();  PreparedStatement psttm = con.prepareStatement(sql);) {
            
            DateFormat dateFormatYear = new SimpleDateFormat("yyyy");
            DateFormat dateFormatMonth = new SimpleDateFormat("MM");

            Date date = new Date();
            String month = dateFormatMonth.format(date);
            String year = dateFormatYear.format(date);

            int dayOfMonth = QuanLyXepLichDAO.getDayInMonths(Integer.parseInt(month), Integer.parseInt(year));
            System.out.println("Day " + dayOfMonth);

            for (int i = 1; i <= dayOfMonth; i++) {
                String dateInsertFormate = "yyyy-MM-" + i;
                DateFormat dateFormat = new SimpleDateFormat(dateInsertFormate);
                String dateInsert = dateFormat.format(date);
                psttm.setString(1, dateInsert);
                psttm.setString(2, "Sang");
                psttm.executeUpdate();
                psttm.setString(1, dateInsert);
                psttm.setString(2, "Chieu");
                psttm.executeUpdate();
                psttm.setString(1, dateInsert);
                psttm.setString(2, "Toi");
                psttm.executeUpdate();
            }

        } catch (Exception e) {
        }
    }

    public boolean checkLichCurrentMonth() {
        String sql = "select id_lich from lich where ngaylamviec = ?";
        boolean flag = false;
        try ( Connection con = DatabaseHelper.openConnection();  PreparedStatement psttm = con.prepareStatement(sql);) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-01");
            java.util.Date date = new java.util.Date();
            String s = dateFormat.format(date);
            psttm.setString(1, s);
            ResultSet rs = psttm.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
