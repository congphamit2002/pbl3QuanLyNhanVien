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
import pbl3quanlynhanvien.DTO.Person;
import pbl3quanlynhanvien.view.MainForm;

/**
 *
 * @author PC
 */
public class PersonDAO {
    
      private MainForm parent;
      
       private static PersonDAO Instance;

    public static PersonDAO getInstance() {
        if(Instance == null)
        {
            Instance = new PersonDAO();
        }
        return Instance;
    }

    private static void setInstance(PersonDAO Instance) {
    }
    
      public boolean insertPerson(Person person) throws Exception
      {
          
          String sql = "insert into thongtinchung(id_nhanvien, hoten, username, diachi, cccd, ngaysinh, gioitinh, id_chucvu, sdt, email)" +
                            " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
          try (
                  Connection con =  DatabaseHelper.openConnection();
                  PreparedStatement psttm = con.prepareStatement(sql);
                  )
          {
              psttm.setString(1, person.getId_nhanvien());
              psttm.setString(2, person.getHoten());
              psttm.setString(3, person.getUsername());
              psttm.setString(4, person.getDiachi());
              psttm.setString(5, person.getCccd());
              psttm.setDate(6, (java.sql.Date) person.getNgaysinh());
              psttm.setInt(7, person.isGioitinh() == true ? 1 : 0);
              psttm.setInt(8, person.getId_chucvu());
              psttm.setString(9, person.getSdt());
              psttm.setString(10, person.getEmail());
              
              return psttm.executeUpdate() > 0;
          } catch ( Exception e) {
              e.printStackTrace();
              System.out.println("Khong the insert");
          }
          return false;
       }
      
      public boolean updatePerson(Person person)
      {
          String sql = "update thongtinchung set hoten = ?, username = ?, diachi = ?, cccd = ?, ngaysinh = ?, gioitinh = ?,"
                  + " id_chucvu = ?, sdt =?, email = ? where id_nhanvien = ?";
          try (
                  Connection con =  DatabaseHelper.openConnection();
                  PreparedStatement psttm = con.prepareStatement(sql);
                  )
          {
              psttm.setString(10, person.getId_nhanvien());
              psttm.setString(1, person.getHoten());
              psttm.setString(2, person.getUsername());
              psttm.setString(3, person.getDiachi());
              psttm.setString(4, person.getCccd());
              psttm.setDate(5, (java.sql.Date) person.getNgaysinh());
              psttm.setInt(6, person.isGioitinh() == true ? 1 : 0);
              psttm.setInt(7, person.getId_chucvu());
              psttm.setString(8, person.getSdt());
              psttm.setString(9, person.getEmail());
              
              return psttm.executeUpdate() > 0;
          } catch ( Exception e) {
              e.printStackTrace();
              System.out.println("Khong the update");
          }
          return false;
      }
      
      public boolean deletePerson(String id_nhanvien)
      {
          String sql = "delete from thongtinchung where id_nhanvien = ?";
          
          try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(sql);
                  ) {
              psttm.setString(1, id_nhanvien);
              return psttm.executeUpdate() > 0;
          } catch (Exception e) {
              e.printStackTrace();
              
          }
          return false;
      }
      
      public Person findPersonByID(String id)
    {
        String sql = "select * from thongtinchung where id_nhanvien = ?";
        
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(sql);
                ) {
            psttm.setString(1, id);
            try(
                    ResultSet rs = psttm.executeQuery();
                    ) {
                    if(rs.next())
                    {
                        Person per = new Person();
                        per.setId_nhanvien(rs.getString("id_nhanvien"));
                        per.setHoten(rs.getString("hoten"));
                        per.setUsername(rs.getString("username"));
                        per.setDiachi(rs.getString("diachi"));
                        per.setCccd(rs.getString("cccd"));
                        per.setNgaysinh(rs.getDate("ngaysinh"));
                        per.setGioitinh(rs.getInt("gioitinh") == 1 ? true : false);
                        per.setId_chucvu(rs.getInt("id_chucvu"));
                        per.setSdt(rs.getString("sdt"));
                        per.setEmail(rs.getString("email"));
                        return per;
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(parent, e.getMessage(), "ERROR");
        }
        return null;
    }
      
      public List<Person> getAllPerson()
      {
          List<Person> list = new ArrayList<>();
          
          String query = "select * from thongtinchung";
          
          try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(query);
                  ){
              ResultSet rs = psttm.executeQuery();
              while(rs.next())
              {
                  Person per = new Person();
                  per.setId_nhanvien(rs.getString("id_nhanvien"));
                  per.setHoten(rs.getString("hoten"));
                  per.setUsername(rs.getString("username"));
                  per.setDiachi(rs.getString("diachi"));
                  per.setCccd(rs.getString("cccd"));
                  per.setNgaysinh(rs.getDate("ngaysinh"));
                  per.setGioitinh(rs.getInt("gioitinh") == 1 ? true : false);
                  per.setId_chucvu(rs.getInt("id_chucvu"));
                  per.setSdt(rs.getString("sdt"));
                  per.setEmail(rs.getString("email"));
                  list.add(per);
              }
          } catch (Exception e) {
              e.printStackTrace();
              MessageDialogHelper.showErrorDialog(parent, e.getMessage(), "Error");
          }
          
          return list;
      }
      
      public List<String> getAllId_nhanvienByChucVu(String tenchucvu)
      {
          List<String> list = new ArrayList<>();
          
          String query = "select id_nhanvien from thongtinchung "
                  + "join chucvu on thongtinchung.id_chucvu = chucvu.id_chucvu "
                  + "where chucvu.tenchucvu = ?";
          
          try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(query);
                  ){
              psttm.setString(1, tenchucvu);
              ResultSet rs = psttm.executeQuery();
              while(rs.next())
              {
                  list.add(rs.getString("id_nhanvien"));
              }
          } catch (Exception e) {
              e.printStackTrace();
              MessageDialogHelper.showErrorDialog(parent, e.getMessage(), "Error");
          }
          
          return list;
      }
      
      public String getHoTenById_nhanvien(String id_nhanvien)
      {
          String hoten = "";
          
          String query = "select hoten from thongtinchung where id_nhanvien = ?";
          
          try (
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(query);
                  ){
              psttm.setString(1, id_nhanvien);
              ResultSet rs = psttm.executeQuery();
              while(rs.next())
              {
                  hoten = rs.getString("hoten");
              }
          } catch (Exception e) {
              e.printStackTrace();
              MessageDialogHelper.showErrorDialog(parent, e.getMessage(), "Error");
          }
          return hoten;
      }
      
      
}
