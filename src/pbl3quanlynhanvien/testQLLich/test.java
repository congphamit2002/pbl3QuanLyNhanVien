/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbl3quanlynhanvien.testQLLich;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import pbl3quanlynhanvien.BLL.QuanLyDiemDanhBLL;
import pbl3quanlynhanvien.BLL.QuanLyTienLuong;
import pbl3quanlynhanvien.DAL.DatabaseHelper;
import pbl3quanlynhanvien.DAL.NhanVien_LichDAO;
import static pbl3quanlynhanvien.DAL.QuanLyXepLichDAO.n;
import pbl3quanlynhanvien.DTO.LuongNhanVien;
import pbl3quanlynhanvien.DTO.LuongNhanVien_ChiTiet;
import pbl3quanlynhanvien.DTO.Nhanvien_Lich;

/**
 *
 * @author PC
 */
public class test {
    public int getDay() {
        //String sql = "select * from lich where lich.id_lich in (select ban.id_lich from ban where id_nhanvien=?)";
        String sql = "select count(distinct id_nhanvien) from thongtinchung where id_chucvu in "
                + "(select id_chucvu from chucvu where tenchucvu = 'Phuc Vu')";
        try (
                 Connection con = DatabaseHelper.openConnection();  PreparedStatement psttm = con.prepareStatement(sql);) {
            ResultSet rs = psttm.executeQuery(sql);
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }

            System.out.println("count: " + count);
        } catch (Exception e) {
        }
        return 0;
    }

    public void getAllDay() {

        Object[] headerModelPV = new Object[32];
        Object[] headerModelPC = new Object[32];
        headerModelPV[0] = "Buoi";
        headerModelPC[0] = "Buoi";
        //String sql = "select distinct ngaylamviec from lich";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");
        LocalDateTime now = LocalDateTime.now();
        String month = dtf.format(now);
        String sql = "select distinct ngaylamviec from lich where month(ngaylamviec) = ?";
        int count = 1;
        try (
                 Connection con = DatabaseHelper.openConnection();  PreparedStatement psttm = con.prepareStatement(sql);) {
            psttm.setString(1, month);
            ResultSet rs = psttm.executeQuery();
            while (rs.next()) {
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                String strDate = df.format(rs.getDate("ngaylamviec"));

                headerModelPV[count] = strDate;
                headerModelPC[count] = strDate;
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object[][] lichPhucVu = new Object[6][32];
        Object[][] lichPhaChe = new Object[6][32];

        //Ghi cac buoi Sang Chieu Toi vao mang phuc vu va pha che
        for (int i = 0; i < 6; i++) {
            if (i < 2) {
                lichPhucVu[i][0] = "Sang";
                lichPhaChe[i][0] = "Sang";
            } else if (i < 4) {
                lichPhucVu[i][0] = "Chieu";
                lichPhaChe[i][0] = "Chieu";
            } else if (i < 6) {
                lichPhucVu[i][0] = "Toi";
                lichPhaChe[i][0] = "Toi";
            }
        }

        sql = "select thongtinchung.hoten, lich.ngaylamviec, lich.buoi,lich.id_lich from thongtinchung "
                + "join nhanvien_lich on thongtinchung.id_nhanvien = nhanvien_lich.id_nhanvien "
                + "join lich on lich.id_lich = nhanvien_lich.id_lich "
                + "where thongtinchung.id_nhanvien in ("
                + "select id_nhanvien from thongtinchung "
                + "join chucvu on thongtinchung.id_chucvu = chucvu.id_chucvu "
                + "where tenchucvu = 'Pha Che') "
                + "order by id_lich asc";

        try ( Connection con = DatabaseHelper.openConnection();  PreparedStatement psttm = con.prepareStatement(sql);) {
            ResultSet rs = psttm.executeQuery();

            int countSang = 0, countChieu = 2, countToi = 4;

            while (rs.next()) {
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                String strDate = df.format(rs.getDate("ngaylamviec"));
                for (int i = 1; i < headerModelPV.length; i++) {
                    if (strDate.equals(headerModelPV[i]) && rs.getString("buoi").equals("Sang")) {
                        lichPhucVu[countSang][i] = rs.getString("hoten");
                        if (countSang == 1) {
                            countSang = 0;
                        } else {
                            countSang++;
                        }
                        break;

                    } else if (strDate.equals(headerModelPV[i]) && rs.getString("buoi").equals("Chieu")) {
                        lichPhucVu[countChieu][i] = rs.getString("hoten");
                        if (countChieu == 3) {
                            countChieu = 2;
                        } else {
                            countChieu++;
                        }
                        break;
                    } else if (strDate.equals(headerModelPV[i]) && rs.getString("buoi").equals("Toi")) {
                        lichPhucVu[countToi][i] = rs.getString("hoten");
                        if (countToi == 5) {
                            countToi = 4;
                        } else {
                            countToi++;
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 6; i++) {

            for (int j = 0; j < 32; j++) {
                System.out.println(lichPhucVu[i][j]);
            }
        }

    }

    public boolean checkXepLich() {
        boolean flag = false;

        String sql = "select count(distinct nhanvien_lich.id_lich) from nhanvien_lich "
                + "where nhanvien_lich.id_lich in("
                + "select id_lich from lich where month(lich.ngaylamviec) = ?)";
        try (
                 Connection con = DatabaseHelper.openConnection();  PreparedStatement psttm = con.prepareStatement(sql);) {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");
            LocalDateTime now = LocalDateTime.now();
            String month = dtf.format(now);
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy");
            String year = dtf1.format(now);
            int sl = getDayInMonths(Integer.parseInt(month), Integer.parseInt(year)) * 3;
            psttm.setString(1, month);
            ResultSet rs = psttm.executeQuery();
            while (rs.next()) {
                System.out.println("count" + rs.getInt(1));
                System.out.println("sl" + sl);
                if (rs.getInt(1) == sl) {
                    flag = true;
                }
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    public static int getDayInMonths(int month, int year) {
        int daysInMonth;
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            daysInMonth = 30;
        } else {
            if (month == 2) {
                daysInMonth = (year % 4 == 0) ? 29 : 28;
            } else {
                daysInMonth = 31;
            }
        }
        return daysInMonth;
    }
    
    public String checkBuoiLam(Date time)
    {
        try {
            String time1 = "00:00:00";
            String time2 = "12:00:00";
            String time3 = "17:30:00";
            String time4 = "23:59:59";

            DateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date date1 = format.parse(time1);
            Date date2 = format.parse(time2);
            Date date3 = format.parse(time3);
            Date date4 = format.parse(time4);
            
            if(time.getTime() - date1.getTime() >= 0 && time.getTime() - date2.getTime() < 0)
                {
                    return "Sang";
                } else if(time.getTime() - date2.getTime() >= 0 && time.getTime() - date3.getTime() < 0)
                {
                    return "Chieu";
                }
                else if(time.getTime() - date3.getTime() >= 0 && time.getTime() - date4.getTime() < 0)
                {
                    return "Toi";
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public int getID_lichByNgayVaBuoi(){
        test x = new test();
        
                    int id_lich =0;
        String sql = "select id_lich from lich where ngaylamviec = ? and buoi = ?";
                    try(
                            Connection con = DatabaseHelper.openConnection();
                            PreparedStatement psttm = con.prepareStatement(sql);) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
                    LocalDateTime now = LocalDateTime.now();  
                    String day = dtf.format(now);
                    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                    
                         Date date = new Date();
                    Date timeNow = dateFormat.parse(dateFormat.format(date));
                    String buoi = x.checkBuoiLam(timeNow);
                    
                    psttm.setString(1, day);
                    psttm.setString(2, buoi);
                    ResultSet rs = psttm.executeQuery();
                    if(rs.next())
                    {
                         id_lich = rs.getInt("id_lich");
                    }
        } catch (Exception e) {
            e.printStackTrace();
        }
                    return id_lich;
    }

    public static void main(String[] args) {
       // test x = new test();
//        try {
//            
//            
//             String time1 = "00:00:00";
//            String time2 = "12:00:00";
//            String time3 = "23:00:00";
//            DateFormat format = new SimpleDateFormat("HH:mm:ss");
//            Date date1 = format.parse(time1);
//            Date date2 = format.parse(time2);
//            Date date3 = format.parse(time3);
            
//            
//            double difference = date.getTime() - date3.getTime();
//            System.out.println("======" + difference);
//            System.out.println("Date 1 : " + x.checkBuoiLam(date1));
//            System.out.println("Date 2 : " + x.checkBuoiLam(date2));
//            System.out.println("Date 3 : " + x.checkBuoiLam(date3));
//            System.out.println("Date now : " + x.checkBuoiLam(now));
//             
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
               //System.out.println(x.getID_lichByNgayVaBuoi());
               
               NhanVien_LichDAO nv = new NhanVien_LichDAO();
               
//               if(nvl != null)
//               {
//                   System.out.println("ID Lich " + nvl.getId_lich());
//                   System.out.println("ID nhan vien " + nvl.getId_nhanvien()  );
//                   System.out.println("thoi gian bat dau " + nvl.getThoigianbatdau());
//                   System.out.println("thoi gian ket thuc  " + nvl.getThoigianketthuc());
//                   
//               }
               
//               try {
//                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//                    Date date = new Date();
//                    Date now = dateFormat.parse(dateFormat.format(date));
//                    java.sql.Timestamp sqlTS = new java.sql.Timestamp(now.getTime());
//                    Nhanvien_Lich nvl =  new Nhanvien_Lich();
//                    nvl.setId_lich(66);
//                    nvl.setId_nhanvien("002");
//                    nvl.setThoigianbatdau(sqlTS);
//                    nvl.setThoigianketthuc(sqlTS);
//                    
//                    if(nv.updateThoiGianNhanVien_Lich(nvl))
//                    {
//                        System.out.println("success");
//                    }else {
//                        System.out.println("fail");
//                    };
//                    
//        } catch (Exception e) {
//        }
//        if(QuanLyDiemDanhBLL.getInstance().thucHienDiemDanh("010"))
//        {
//            
//            System.out.println("success");
//        }else {
//            System.out.println("fail");
//        };

//        try {
//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");  
//                    LocalDateTime now = LocalDateTime.now();  
//                    String day = dtf.format(now);
//                         String timestart = "12:00:00";
//            String timeend = "17:30:00";
//            String time3 = "23:00:00";
//            DateFormat format = new SimpleDateFormat("HH:mm:ss");
//            Date date1 = format.parse(timestart);
//            Date date2 = format.parse(timeend);
//            Date date3 = format.parse(time3);
//            System.out.println("day " + day);
//            
//            LuongNhanVien_ChiTiet luong = QuanLyTienLuong.getInstance().tinhTimeLamVaSoPhutDiTre("Chieu", date1, null);
//            System.out.println("Hour " + luong.getSoGioLam());
//            System.out.println("phut " + luong.getSoPhutDiTre());
//        } catch (Exception e) {
//        }
//
        List<LuongNhanVien> list = QuanLyTienLuong.getInstance().getAllLuongNhanVien("Phuc Vu");
        for (LuongNhanVien luongNhanVien : list) {
            System.out.println("id nhan vien " + luongNhanVien.getId_nhanvien());
            System.out.println("Ho ten " + luongNhanVien.getHoTen());
            System.out.println("Luong co ban " + luongNhanVien.getLuongCoBan());
            System.out.println("So buoi phai lam " + luongNhanVien.getSoNgayPhaiLam());
            System.out.println("So buoi di lam  " + luongNhanVien.getSoNgayDiLam());
            System.out.println("So buoi ngi  " + luongNhanVien.getSoNgayNghi());
            System.out.println("So gio lam  " + luongNhanVien.getSoGioLam());
            System.out.println("So phut di tre  " + luongNhanVien.getSoPhutDiTre());
            System.out.println("Luong  " + luongNhanVien.getLuong());
        }

//        List<Nhanvien_Lich> list = NhanVien_LichDAO.getInstance().getAllNhanVienLichByIDAndThang("002", "05");
//        for (Nhanvien_Lich nhanvien_Lich : list) {
//            if(nhanvien_Lich.getThoigianbatdau() != null && nhanvien_Lich.getThoigianketthuc() != null)
//            {
//                            try {
//                            DateFormat format = new SimpleDateFormat("HH:mm:ss");
//                            int hourStart = nhanvien_Lich.getThoigianbatdau().getHours();
//                             int minuteStart = nhanvien_Lich.getThoigianbatdau().getMinutes();
//                              int secondStart = nhanvien_Lich.getThoigianbatdau().getSeconds();
//                              
//                              int hourEnd = nhanvien_Lich.getThoigianketthuc().getHours();
//                             int minuteEnd = nhanvien_Lich.getThoigianketthuc().getMinutes();
//                              int secondEnd = nhanvien_Lich.getThoigianketthuc().getSeconds();
//                            String timeStart = "" + hourStart + ":" +minuteStart + ":" + secondStart;
//                            String timeEnd = "" + hourEnd + ":" +minuteEnd + ":" + secondEnd;
//                                System.out.println("Time start " + timeStart);
//                                System.out.println("Time end " + timeEnd);
//                            
//                            Date date1 = format.parse(timeStart);
//                            Date date2 = format.parse(timeEnd);
//                            LuongNhanVien_ChiTiet luong = QuanLyTienLuong.getInstance().tinhTimeLamVaSoPhutDiTre("Toi", date1, date2);
//                            System.out.println("Hour " + luong.getSoGioLam());
//                            System.out.println("phut " + luong.getSoPhutDiTre());
//                } catch (Exception e) {
//                }
//
//                System.out.println("time start " + nhanvien_Lich.getThoigianbatdau().toString());
//                System.out.println("time end " + nhanvien_Lich.getThoigianketthuc().toString());
//            }
//        }
    }
}
