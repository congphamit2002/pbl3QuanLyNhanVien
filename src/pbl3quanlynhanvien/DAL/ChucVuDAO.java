/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbl3quanlynhanvien.DAL;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pbl3quanlynhanvien.DTO.ChucVu;

/**
 *
 * @author PC
 */
public class ChucVuDAO {
    private static ChucVuDAO Instance;

    public static ChucVuDAO getInstance() {
        if(Instance == null)
        {
            Instance = new ChucVuDAO();
        }
        return Instance;
    }

    private static void setInstance(ChucVuDAO Instance) {
    }
    
    public List<ChucVu> getAllChucVu()
    {
        List<ChucVu> list = new ArrayList<>();
        
        String query = "select * from chucvu";
        
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(query);
                ) {
            ResultSet rs =  psttm.executeQuery();
            while (rs.next())
            {
                ChucVu cv = new ChucVu();
                cv.setId_chucvu(rs.getInt("id_chucvu"));
                cv.setTenchucvu(rs.getString("tenchucvu"));
                cv.setLuongcoban(rs.getDouble("luongcoban"));
                list.add(cv);
            }
        } catch (Exception e) {
        }
        
        return list;
    }
    
    public double getLuongCoBanByTenChucVu(String tenchucvu)
      {
          double luongcoban = 0;
          String query = "select luongcoban from chucvu chung where tenchucvu = ?";
          
          try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(query);
                  ){
              psttm.setString(1, tenchucvu);
              ResultSet rs = psttm.executeQuery();
              while(rs.next())
              {
                  luongcoban = rs.getDouble("luongcoban");
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          return luongcoban;
      }
    
    public boolean updateLuongCoBan(double luongcoban, String tenchucvu)
    {
            boolean isSuccess = false;
          String query = "update chucvu set luongcoban = ? where tenchucvu = ?";
          
          try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(query);
                  ){
              psttm.setDouble(1, luongcoban);
              psttm.setString(2, tenchucvu);
              if(psttm.executeUpdate() > 0)
              {
                  isSuccess = true;
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          return isSuccess;
    }
}
