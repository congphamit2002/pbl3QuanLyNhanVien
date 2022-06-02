/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbl3quanlynhanvien.DAL;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author PC
 */
public class DataValidator {
        public static void checkEmty(JTextField field, StringBuilder sb, String message)
        {
            if(field.getText().equals(""))
            {
                sb.append(message);
                sb.append("\n");
                field.setBackground(Color.red);
                field.requestFocus();
            }else {
                field.setBackground(Color.white);
            }
        }
        
        public static void checkEmty(JPasswordField field, StringBuilder sb, String message)
        {
            if((new String (field.getPassword())).equals(""))
            {
                sb.append(message);
                sb.append("\n");
                field.setBackground(Color.red);
                field.requestFocus();
            }else {
                field.setBackground(Color.white);
            }
        }

    public static void checkEmty(JTextArea field, StringBuilder sb, String message) {
        if(field.getText().equals(""))
            {
                sb.append(message);
                sb.append("\n");
                field.setBackground(Color.red);
                field.requestFocus();
            }else {
                field.setBackground(Color.white);
            }
    }
    
    public static void checkEmty(JDateChooser field, StringBuilder sb, String message) {
        if(field.getDate() == null)
            {
                sb.append(message);
                sb.append("\n");
                field.setBackground(Color.red);
                field.requestFocus();
            }else {
                field.setBackground(Color.white);
            }
    }
}
