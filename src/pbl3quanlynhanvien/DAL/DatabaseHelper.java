/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbl3quanlynhanvien.DAL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author PC
 */
public class DatabaseHelper {
    public static Connection openConnection() throws  Exception{
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");// nạp drive
	        } catch (Exception ex) {
                    ex.printStackTrace();
	        }
	        String conUrl="jdbc:mysql://127.0.0.1:3307/quanlynhanvien?useSSL=false";// tao url de ket noi den csdl
	        Connection connection= (Connection) DriverManager.getConnection(conUrl,"root","admin");// mo ket noi csdl
	        return connection;// tra ve doi tuong connection sau khi da ket noi
	    }
}
