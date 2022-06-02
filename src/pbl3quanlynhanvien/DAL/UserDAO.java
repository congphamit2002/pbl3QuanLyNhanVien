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
import pbl3quanlynhanvien.DTO.ShareData;
import pbl3quanlynhanvien.DTO.Users;

/**
 *
 * @author PC
 */
public class UserDAO {
    
    private static UserDAO Instance;

    public static UserDAO getInstance() {
        if(Instance == null)
        {
            Instance = new UserDAO();
        }
        return Instance;
    }

    private static void setInstance(UserDAO Instance) {
    }
    
    public Users checkLogin(String username, String password)
    {
        String sql = "select * from role where username = ?";
        
            Users user = new Users();
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(sql);) {
            
            psttm.setString(1, username);
            
            ResultSet rs = psttm.executeQuery();
            if(rs.next())
            {
                if(rs.getString("username").equals(username) && rs.getString("password").equals(password))
                {
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setRole(rs.getString("role"));
                    return user;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean insertUser(Users user) throws Exception
      {
          
          String sql = "insert into role(username, password, role)" +
                            " values (?, ?, ?)";
          try (
                  Connection con =  DatabaseHelper.openConnection();
                  PreparedStatement psttm = con.prepareStatement(sql);
                  )
                {
                    psttm.setString(1, user.getUsername());
                    psttm.setString(2, user.getPassword());
                    psttm.setString(3, user.getRole());

                    return psttm.executeUpdate() > 0;
                } catch ( Exception e) {
              e.printStackTrace();
              System.out.println("Khong the insert");
          }
          return false;
       }
    
    public boolean findUserByUsername(String id)
    {
        String sql = "select * from role where username = ?";
        Users user = null;
        try
            (
                  Connection con =  DatabaseHelper.openConnection();
                  PreparedStatement psttm = con.prepareStatement(sql);)
        {
            psttm.setString(1, id);
            return psttm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean updateRole(String username, String role) throws Exception
      {
          
          String sql = "update role set role = ? where username = ?";
          try (
                  Connection con =  DatabaseHelper.openConnection();
                  PreparedStatement psttm = con.prepareStatement(sql);
                  )
                {
                    psttm.setString(1, role);
                    psttm.setString(2, username);

                    return psttm.executeUpdate() > 0;
                } catch ( Exception e) {
              e.printStackTrace();
              System.out.println("Khong the insert");
          }
          return false;
       }
    
    public boolean updateUser(Users user)
    {
        String sql = "update role set role = ?, password = ? where username = ?";
          try (
                  Connection con =  DatabaseHelper.openConnection();
                  PreparedStatement psttm = con.prepareStatement(sql);
                  )
                {
                    psttm.setString(1, user.getRole());
                    psttm.setString(2, user.getPassword());
                    
                    psttm.setString(3, user.getUsername());

                    return psttm.executeUpdate() > 0;
                } catch ( Exception e) {
              e.printStackTrace();
              System.out.println("Khong the insert");
          }
          return false;
    }
    
    public List<String> getAllRole()
    {
        String sql = "select distinct role from role";
        List<String> data = new ArrayList<>();
           try(
                   Connection con = DatabaseHelper.openConnection();
                   PreparedStatement psttm = con.prepareStatement(sql); ) {
            ResultSet rs = psttm.executeQuery();
            while(rs.next())
                {
                    data.add(rs.getString("role"));
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
           return data;
    }
    
    public String getIdNVByUsername(String username)
    {
        String id_nhanvien = "";
        
        String sql = "select id_nhanvien from thongtinchung where username = ?";
        
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(sql);) {
            psttm.setString(1, username);
            ResultSet rs = psttm.executeQuery();
            if(rs.next())
            {
                id_nhanvien = rs.getString("id_nhanvien");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id_nhanvien;
    }
    
    public String getNameByID_Nhanvien(){
        String sql = "select hoten from thongtinchung where id_nhanvien = ?";
        
        String name = "";
        ShareData share = new ShareData();
        
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(sql);) {
            psttm.setString(1, share.getId_nhanvien());
            ResultSet rs = psttm.executeQuery();
            if(rs.next())
            {
                name = rs.getString("hoten");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }
    
    public String getChucVuByID_Nhanvien(){
        String sql = "select chucvu.tenchucvu from chucvu where chucvu.id_chucvu in (select thongtinchung.id_chucvu from thongtinchung where id_nhanvien = ?)";
        
        String name = "";
        ShareData share = new ShareData();
        
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(sql);) {
            psttm.setString(1, share.getId_nhanvien());
            ResultSet rs = psttm.executeQuery();
            if(rs.next())
            {
                name = rs.getString("tenchucvu");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }
    
    public boolean updatePassword (Users user){
        String sql = "update role set password = ? where username = ?";
          try (
                  Connection con =  DatabaseHelper.openConnection();
                  PreparedStatement psttm = con.prepareStatement(sql);
                  )
                {
                    psttm.setString(1, user.getPassword());
                    
                    psttm.setString(2, user.getUsername());
                    System.out.println("Username " + user.getUsername());
                    System.out.println("Pass " + user.getPassword());
                    return psttm.executeUpdate() > 0;
                } catch ( Exception e) {
              e.printStackTrace();
              System.out.println("Khong the insert");
          }
          return false;
    }
    
    public Users getUserByUserName(String username)
     {
        String sql = "select * from role where username = ?";
        
            Users user = new Users();
        try(
                Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(sql);) {
            
            psttm.setString(1, username);
            
            ResultSet rs = psttm.executeQuery();
            if(rs.next())
            {
                    user.setUsername(username);
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                    return user;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
