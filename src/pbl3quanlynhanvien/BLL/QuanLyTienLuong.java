/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbl3quanlynhanvien.BLL;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import pbl3quanlynhanvien.DAL.ChucVuDAO;
import pbl3quanlynhanvien.DAL.LichBanDAO;
import pbl3quanlynhanvien.DAL.LichDAO;
import pbl3quanlynhanvien.DAL.NhanVien_LichDAO;
import pbl3quanlynhanvien.DAL.PersonDAO;
import pbl3quanlynhanvien.DTO.LuongNhanVien;
import pbl3quanlynhanvien.DTO.LuongNhanVien_ChiTiet;
import pbl3quanlynhanvien.DTO.Nhanvien_Lich;

/**
 *
 * @author PC
 */
public class QuanLyTienLuong {
//    .

    private static QuanLyTienLuong Instance;

    public static QuanLyTienLuong getInstance() {
        if (Instance == null) {
            Instance = new QuanLyTienLuong();
        }
        return Instance;
    }

    private static void setInstance(QuanLyTienLuong Instance) {
    }

    public LuongNhanVien_ChiTiet tinhTimeLamVaSoPhutDiTre(String buoi, Date timeStart, Date timeEnd) {
        double hour = 0;
        double phut = 0;
        LuongNhanVien_ChiTiet luong = new LuongNhanVien_ChiTiet();
        try {
            String time1 = "06:30:00";
            String time2 = "12:00:00";
            String time3 = "17:30:00";
            String time4 = "23:00:00";

            DateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date date1 = format.parse(time1);
            Date date2 = format.parse(time2);
            Date date3 = format.parse(time3);
            Date date4 = format.parse(time4);
            switch (buoi) {
                case "Sang": {
                    if (timeStart != null && timeEnd != null) {
                        if (date1.getTime() - timeStart.getTime() >= 0 && timeEnd.getTime() - date2.getTime() >= 0) //di som ve tre
                        {
                            hour = (date2.getTime() - date1.getTime()) / 3600000.0;
                        } else if (date1.getTime() - timeStart.getTime() >= 0 && timeEnd.getTime() - date2.getTime() <= 0) //di som ve som
                        {
                            hour = (timeEnd.getTime() - date1.getTime()) / 3600000.0;
                            phut = (date2.getTime() - timeEnd.getTime()) / 60000.0;

                        } else if (date1.getTime() - timeStart.getTime() <= 0 && timeEnd.getTime() - date2.getTime() >= 0) //di tre ve tre
                        {
                            hour = (date2.getTime() - timeStart.getTime()) / 3600000.0;
                            phut = (timeStart.getTime() - date1.getTime()) / 60000.0;
                        } else //di tre ve som
                        {

                            hour = (timeEnd.getTime() - timeStart.getTime()) / 3600000.0;
                            phut = (timeStart.getTime() - date1.getTime()) / 60000.0 + (date2.getTime() - timeEnd.getTime()) / 60000.0;
                        }
                    }

                    break;
                }

                case "Chieu": {
                    if (timeStart != null && timeEnd != null) {
                        if (date2.getTime() - timeStart.getTime() >= 0 && timeEnd.getTime() - date3.getTime() >= 0) //di som ve tre
                        {
                            hour = (date3.getTime() - date2.getTime()) / 3600000.0;
                        } //neu di som ve som
                        else if (date2.getTime() - timeStart.getTime() >= 0 && timeEnd.getTime() - date3.getTime() <= 0) {
                            hour = (timeEnd.getTime() - date2.getTime()) / 3600000.0;
                            phut = (date3.getTime() - timeEnd.getTime()) / 60000.0;

                        } else if (date2.getTime() - timeStart.getTime() <= 0 && timeEnd.getTime() - date3.getTime() >= 0) //di tre ve tre
                        {
                            hour = (date3.getTime() - timeStart.getTime()) / 3600000.0;
                            phut = (timeStart.getTime() - date2.getTime()) / 60000.0;
                        } else {

                            hour = (timeEnd.getTime() - timeStart.getTime()) / 3600000.0;
                            phut = (timeStart.getTime() - date2.getTime()) / 60000.0 + (date3.getTime() - timeEnd.getTime()) / 60000.0;
                        }

                    }
                    break;
                }

                case "Toi": {
                    if (timeStart != null && timeEnd != null) {
                        if (date3.getTime() - timeStart.getTime() >= 0 && timeEnd.getTime() - date4.getTime() >= 0) //di som ve tre
                        {
                            hour = (date4.getTime() - date3.getTime()) / 3600000.0;
                        } //neu di som ve som
                        else if (date3.getTime() - timeStart.getTime() >= 0 && timeEnd.getTime() - date4.getTime() <= 0) {
                            hour = (timeEnd.getTime() - date3.getTime()) / 3600000.0;
                            phut = (date4.getTime() - timeEnd.getTime()) / 60000.0;

                        } else if (date3.getTime() - timeStart.getTime() <= 0 && timeEnd.getTime() - date4.getTime() >= 0) //di tre ve tre
                        {
                            hour = (date4.getTime() - timeStart.getTime()) / 3600000.0;
                            phut = (timeStart.getTime() - date3.getTime()) / 60000.0;
                        } else {

                            hour = (timeEnd.getTime() - timeStart.getTime()) / 3600000.0;
                            phut = (timeStart.getTime() - date3.getTime()) / 60000.0 + (date4.getTime() - timeEnd.getTime()) / 60000.0;
                        }
                    }
                    break;
                }

                default:
                    throw new AssertionError();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        luong.setSoGioLam(hour);
        luong.setSoPhutDiTre(phut);
        System.out.println("\t\t hour" + hour);
        System.out.println("\t\t minute " + phut);
        return luong;
    }

    public List<LuongNhanVien> getAllLuongNhanVien(String tenchucvu) {
        List<LuongNhanVien> listLuongNhanVien = new ArrayList<LuongNhanVien>();
        List<String> listId_nhanvien = PersonDAO.getInstance().getAllId_nhanvienByChucVu(tenchucvu);
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");
            LocalDateTime now = LocalDateTime.now();
            String day = dtf.format(now);
            for (String id_nhanvien : listId_nhanvien) {
                List<Nhanvien_Lich> listNVL = NhanVien_LichDAO.getInstance().getAllNhanVienLichByIDAndThang(id_nhanvien, day);
                double hour = 0, minute = 0;
                int songaylam = 0, songaynghi = 0;
                for (Nhanvien_Lich nhanvien_Lich : listNVL) {

                    if (nhanvien_Lich.getThoigianbatdau() != null && nhanvien_Lich.getThoigianketthuc() != null) {
                        DateFormat format = new SimpleDateFormat("HH:mm:ss");
                        int hourStart = nhanvien_Lich.getThoigianbatdau().getHours();
                        int minuteStart = nhanvien_Lich.getThoigianbatdau().getMinutes();
                        int secondStart = nhanvien_Lich.getThoigianbatdau().getSeconds();

                        int hourEnd = nhanvien_Lich.getThoigianketthuc().getHours();
                        int minuteEnd = nhanvien_Lich.getThoigianketthuc().getMinutes();
                        int secondEnd = nhanvien_Lich.getThoigianketthuc().getSeconds();
                        String timeStart = "" + hourStart + ":" + minuteStart + ":" + secondStart;
                        String timeEnd = "" + hourEnd + ":" + minuteEnd + ":" + secondEnd;
                        System.out.println("Time start " + timeStart);
                        System.out.println("Time end " + timeEnd);

                        Date date1 = format.parse(timeStart);
                        Date date2 = format.parse(timeEnd);
                        LuongNhanVien_ChiTiet luongchitiet = QuanLyTienLuong.getInstance().tinhTimeLamVaSoPhutDiTre(LichDAO.getInstance().getBuoiLamByID_Lich(nhanvien_Lich.getId_lich()),
                                 date1, date2);
                        if ((ChucVuDAO.getInstance().getLuongCoBanByTenChucVu(tenchucvu) * 5.5 - (luongchitiet.getSoPhutDiTre() / 10.0) * 5000) < 0) {
                            hour += 0;
                            minute += 0;
                            songaylam += 1;
                        } else {
                            if(hour >= 0)
                                hour += luongchitiet.getSoGioLam();
                            if(minute >= 0)
                                minute += luongchitiet.getSoPhutDiTre();
                            System.out.println("\thour " + hour);
                            System.out.println("\tminute " + minute);

                            songaylam += 1;

                            System.out.println("\t so ngay lam " + songaylam);
                        }

                    } else {
                        songaynghi++;
                    }
                }

                LuongNhanVien luongnhanvien = new LuongNhanVien();
                luongnhanvien.setId_nhanvien(id_nhanvien);
                luongnhanvien.setHoTen(PersonDAO.getInstance().getHoTenById_nhanvien(id_nhanvien));
                luongnhanvien.setLuongCoBan(ChucVuDAO.getInstance().getLuongCoBanByTenChucVu(tenchucvu));
                luongnhanvien.setSoNgayPhaiLam(NhanVien_LichDAO.getInstance().getCountBuoiLamByIDAndThang(id_nhanvien, day));
                luongnhanvien.setSoNgayDiLam(songaylam);
                luongnhanvien.setSoNgayNghi(songaynghi);
                luongnhanvien.setSoGioLam(hour);
                luongnhanvien.setSoPhutDiTre(minute);
                double luong = ChucVuDAO.getInstance().getLuongCoBanByTenChucVu(tenchucvu) * hour - (minute / 10.0) * 5000;
                luongnhanvien.setLuong(luong);
                listLuongNhanVien.add(luongnhanvien);
            }

        } catch (Exception e) {
        }
        return listLuongNhanVien;
    }

    public void renderToTable(DefaultTableModel model, String tenchucvu) {
        model.setRowCount(0);
        DecimalFormat df = new DecimalFormat("#.###");
        for (LuongNhanVien luongNhanVien : QuanLyTienLuong.getInstance().getAllLuongNhanVien(tenchucvu)) {
            model.addRow(new Object[]{luongNhanVien.getId_nhanvien(), luongNhanVien.getHoTen(), luongNhanVien.getLuongCoBan(),
                 luongNhanVien.getSoNgayPhaiLam(), luongNhanVien.getSoNgayDiLam(), luongNhanVien.getSoNgayNghi(), 
                 df.format(luongNhanVien.getSoGioLam()), df.format(luongNhanVien.getSoPhutDiTre()), df.format(luongNhanVien.getLuong())});
        }
        model.fireTableDataChanged();
    }

    public boolean updateLuongCoBan(double luongcoban, String tenchucvu) {
        return ChucVuDAO.getInstance().updateLuongCoBan(luongcoban, tenchucvu);
    }
}
