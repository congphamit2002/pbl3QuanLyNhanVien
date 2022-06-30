/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbl3quanlynhanvien.DAL;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author PC
 */
public class test {

    public void insertLichInCurrentMonth() {
        String sql = "insert into lich(ngaylamviec, buoi) values(?,?)";

        if(checkLichCurrentMonth()){
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

    public static void main(String[] args) {
        test x = new test();
        x.insertLichInCurrentMonth();
    }
}
    