/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbl3quanlynhanvien.BLL;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import pbl3quanlynhanvien.DAL.DatabaseHelper;
import static pbl3quanlynhanvien.DAL.QuanLyXepLichDAO.n;
import pbl3quanlynhanvien.DTO.PhucVu;

/**
 *
 * @author PC
 */
public class test {
       public static void main(String[] args) {
           String sql = "insert into lich(ngaylamviec, buoi) values (?,?)";
           try(Connection con = DatabaseHelper.openConnection();
                   PreparedStatement psttm = con.prepareStatement(sql);) {
                PhucVu sv= new PhucVu();
             n=sv.ban.length;
             n=n/3;
            System.out.println("chieu dai cua ban"+n);
           } catch (Exception e) {
           }
    }
}
