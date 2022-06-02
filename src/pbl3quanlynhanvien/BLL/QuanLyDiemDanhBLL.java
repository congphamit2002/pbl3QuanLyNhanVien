/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbl3quanlynhanvien.BLL;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import pbl3quanlynhanvien.DAL.LichDAO;
import pbl3quanlynhanvien.DAL.NhanVien_LichDAO;
import pbl3quanlynhanvien.DTO.Nhanvien_Lich;

/**
 *
 * @author PC
 */
public class QuanLyDiemDanhBLL {
    private static QuanLyDiemDanhBLL Instance;

    public static QuanLyDiemDanhBLL getInstance() {
        if(Instance == null)
        {
            Instance = new QuanLyDiemDanhBLL();
        }
        return Instance;
    }

    private static void setInstance(QuanLyDiemDanhBLL Instance) {
    }
    
    public boolean thucHienDiemDanh(String id_nhanvien){
            boolean isSuccess = false;
            try {
                        DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");

                        Date date1 = new Date();
                        Date timeNow = dateFormat1.parse(dateFormat1.format(date1));
                        
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    Date now = dateFormat.parse(dateFormat.format(date));
                    java.sql.Timestamp sqlTS = new java.sql.Timestamp(now.getTime());
            String buoi = LichDAO.getInstance().checkBuoiLam(timeNow);
            int id_lich = LichDAO.getInstance().getID_lichByNgayVaBuoi(buoi);
            Nhanvien_Lich nhanvien = NhanVien_LichDAO.getInstance().getNhanVienLichByIdLichAndIdNhanvien(id_lich, id_nhanvien);
           

            if(nhanvien != null)
            {
                if(nhanvien.getThoigianbatdau() == null)
                {
                    nhanvien.setThoigianbatdau(sqlTS);
                    nhanvien.setThoigianketthuc(null);
                }else if(nhanvien.getThoigianketthuc() == null)
                {
                    nhanvien.setThoigianketthuc(sqlTS);
                } else {
                    return false;
                }
                if(NhanVien_LichDAO.getInstance().updateThoiGianNhanVien_Lich(nhanvien))
                    isSuccess = true;
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return isSuccess;
    }
}
