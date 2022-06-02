/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbl3quanlynhanvien.BLL;

import javax.swing.table.DefaultTableModel;
import pbl3quanlynhanvien.DAL.QuanLyXepLichDAO;

/**
 *
 * @author PC
 */
public class QuanLyXepLichBLL {
    private static QuanLyXepLichBLL Instance;

    public static QuanLyXepLichBLL getInstance() {
        if(Instance == null)
        {
            Instance = new QuanLyXepLichBLL();
        }
        return Instance;
    }

    private static void setInstance(QuanLyXepLichBLL Instance) {
    }
    
    public boolean checkXepLich(){
        return QuanLyXepLichDAO.getInstance().checkXepLich();
    }
    
    public boolean deleteNhanVienLich(){
        return QuanLyXepLichDAO.getInstance().deleteNhanVienLich();
    }
    
    public void xeplichphucvu() throws Exception
    {
        QuanLyXepLichDAO.getInstance().xeplichphucvu();
    }
    
    public void xepLichPhaChe() throws Exception
    {
        QuanLyXepLichDAO.getInstance().xepLichPhaChe();
    }
    
    public void RenderToTable(DefaultTableModel modelpv,DefaultTableModel modelpc)
    {
        QuanLyXepLichDAO.getInstance().RenderToTable(modelpv, modelpc);
    }
}
