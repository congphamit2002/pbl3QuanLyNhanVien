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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import pbl3quanlynhanvien.BLL.QuanLyDiemDanhBLL;
import pbl3quanlynhanvien.BLL.QuanLyTienLuongBLL;
import pbl3quanlynhanvien.DAL.DatabaseHelper;
import pbl3quanlynhanvien.DAL.LichChiTietDAO;
import pbl3quanlynhanvien.DAL.LichDAO;
import pbl3quanlynhanvien.DAL.NhanVien_LichDAO;
import static pbl3quanlynhanvien.DAL.QuanLyXepLichDAO.n;
import pbl3quanlynhanvien.DTO.LichChiTiet;
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

    public String checkBuoiLam(Date time) {
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

            if (time.getTime() - date1.getTime() >= 0 && time.getTime() - date2.getTime() < 0) {
                return "Sang";
            } else if (time.getTime() - date2.getTime() >= 0 && time.getTime() - date3.getTime() < 0) {
                return "Chieu";
            } else if (time.getTime() - date3.getTime() >= 0 && time.getTime() - date4.getTime() < 0) {
                return "Toi";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public int getID_lichByNgayVaBuoi() {
        test x = new test();

        int id_lich = 0;
        String sql = "select id_lich from lich where ngaylamviec = ? and buoi = ?";
        try (
                 Connection con = DatabaseHelper.openConnection();  PreparedStatement psttm = con.prepareStatement(sql);) {
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
            if (rs.next()) {
                id_lich = rs.getInt("id_lich");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id_lich;
    }

    public List<Integer> getID_lichInCurrentDay() {
        List<Integer> list = new ArrayList<>();
        String sql = "select id_lich from lich where ngaylamviec = ? order by id_lich asc";
        try (
                 Connection con = DatabaseHelper.openConnection();  PreparedStatement psttm = con.prepareStatement(sql);) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            String day = dtf.format(now);

            psttm.setString(1, "2022-06-11");
            ResultSet rs = psttm.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("id_lich"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Integer> getAllIdLichCurentDayByIdNhanVien(String id_nhanvien) {
        test x = new test();

        List<Integer> list = new ArrayList<>();
        List<Integer> list_id_lich_current_day = new ArrayList<>();
        list_id_lich_current_day = x.getID_lichInCurrentDay();

        String sql = "select * from nhanvien_lich where id_lich = ? and id_nhanvien = ?";
        for (Integer integer : list_id_lich_current_day) {
            try (
                     Connection con = DatabaseHelper.openConnection();  PreparedStatement psttm = con.prepareStatement(sql);) {

                psttm.setInt(1, integer);
                psttm.setString(2, id_nhanvien);
                ResultSet rs = psttm.executeQuery();
                if (rs.next()) {
                    list.add(rs.getInt("id_lich"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return list;

    }

    public boolean checkIn(String id_nhanvien) {
        boolean isSuccess = false;
        test x = new test();
        try {
            String time1 = "00:00:00";
            String time2 = "06:10:00";
            String time3 = "17:30:00";
            String time4 = "23:59:59";

            DateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date timeTest1 = format.parse(time1);
            Date timeNow = format.parse(time2);
            Date timeTest3 = format.parse(time3);
            Date timeTest4 = format.parse(time4);

//            DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
//            Date date1 = new Date();
//            Date timeNow = dateFormat1.parse(dateFormat1.format(date1));
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            Date now = dateFormat.parse(dateFormat.format(date));
            now.setHours(6);
            now.setMinutes(10);
            now.setSeconds(0);
            java.sql.Timestamp sqlTS = new java.sql.Timestamp(now.getTime());

            Date chieu = dateFormat.parse(dateFormat.format(date));
            chieu.setHours(12);
            chieu.setMinutes(0);
            chieu.setSeconds(0);
            java.sql.Timestamp timeChieu = new java.sql.Timestamp(chieu.getTime());

            Date toi = dateFormat.parse(dateFormat.format(date));
            toi.setHours(17);
            toi.setMinutes(30);
            toi.setSeconds(0);
            java.sql.Timestamp timeToi = new java.sql.Timestamp(chieu.getTime());

            List<Integer> list_id_lich_current_day = new ArrayList<>();
            list_id_lich_current_day = x.getAllIdLichCurentDayByIdNhanVien("001");

            List<LichChiTiet> list_lich_chi_tiet = LichChiTietDAO.getInstance().getAllLichChiTiet();
            int count = 0;
            if (list_id_lich_current_day.size() == 2) {
                count = list_id_lich_current_day.get(1) - list_id_lich_current_day.get(0);
                System.out.println("Count = " + count);
            }

            try {
                if (list_id_lich_current_day.size() == 1) {
                    String buoiLam = LichDAO.getInstance().getBuoiLamByID_Lich(list_id_lich_current_day.get(0));
                    for (LichChiTiet lichChiTiet : list_lich_chi_tiet) {
                        if (lichChiTiet.getBuoi().equals(buoiLam)
                                && NhanVien_LichDAO.getInstance().checkTimeStartExist(list_id_lich_current_day.get(0), id_nhanvien) == false) {

                            if ((timeNow.getTime() - (lichChiTiet.getThoigianbatdau().getTime() - 1800000)) >= 0
                                    && (timeNow.getTime() - lichChiTiet.getThoigianketthuc().getTime()) < 0) {
                                Nhanvien_Lich nhanvien = NhanVien_LichDAO.getInstance().getNhanVienLichByIdLichAndIdNhanvien(list_id_lich_current_day.get(0), id_nhanvien);
                                nhanvien.setThoigianbatdau(sqlTS);
                                if (NhanVien_LichDAO.getInstance().updateThoiGianNhanVien_Lich(nhanvien)) {
                                    return true;
                                }
                            }
                            break;
                        }
                    }
                }

                //======
                boolean isSecond = false;
                if (list_id_lich_current_day.size() == 2) {
                    if (count == 1) {
                        for (int i = 0; i < 2; i++) {
                            String buoiLam = LichDAO.getInstance().getBuoiLamByID_Lich(list_id_lich_current_day.get(i));
                            System.out.println("Buoi lam " + buoiLam);
                            for (LichChiTiet lichChiTiet : list_lich_chi_tiet) {
                                if (lichChiTiet.getBuoi().equals(buoiLam)
                                        && NhanVien_LichDAO.getInstance().checkTimeStartExist(list_id_lich_current_day.get(i), id_nhanvien) == false) {
                                    if ((timeNow.getTime() - (lichChiTiet.getThoigianbatdau().getTime() - 1800000)) >= 0
                                            && timeNow.getTime() - (lichChiTiet.getThoigianketthuc().getTime()) >= 0) {
                                        System.out.println("Time 1 " + (timeNow.getTime() - (lichChiTiet.getThoigianbatdau().getTime() - 1800000)));
                                        System.out.println("Time 2" + (timeNow.getTime() - (lichChiTiet.getThoigianketthuc().getTime())));
                                        isSecond = true;
                                    }
                                    break;
                                }
                            }
                            if (isSecond) {
                                break;
                            }
                        }

                        if (isSecond) {
                            Nhanvien_Lich nhanvienCaSau = NhanVien_LichDAO.getInstance().getNhanVienLichByIdLichAndIdNhanvien(list_id_lich_current_day.get(1), id_nhanvien);
                            nhanvienCaSau.setThoigianbatdau(sqlTS);
                            if (NhanVien_LichDAO.getInstance().updateThoiGianNhanVien_Lich(nhanvienCaSau)) {
                                return true;
                            }
                            System.out.println("Diem danh cho ca sau");
                        } else {
                            Nhanvien_Lich nhanvienCaTrc = NhanVien_LichDAO.getInstance().getNhanVienLichByIdLichAndIdNhanvien(list_id_lich_current_day.get(0), id_nhanvien);
                            nhanvienCaTrc.setThoigianbatdau(sqlTS);
                            NhanVien_LichDAO.getInstance().updateThoiGianNhanVien_Lich(nhanvienCaTrc);

                            Nhanvien_Lich nhanvienCaSau = NhanVien_LichDAO.getInstance().getNhanVienLichByIdLichAndIdNhanvien(list_id_lich_current_day.get(1), id_nhanvien);
                            if (LichDAO.getInstance().getBuoiLamByID_Lich(list_id_lich_current_day.get(1)).equals("Chieu")) {

                                nhanvienCaSau.setThoigianbatdau(timeChieu);
                            } else if (LichDAO.getInstance().getBuoiLamByID_Lich(list_id_lich_current_day.get(1)).equals("Toi")) {
                                nhanvienCaSau.setThoigianbatdau(timeToi);
                            }
                            NhanVien_LichDAO.getInstance().updateThoiGianNhanVien_Lich(nhanvienCaSau);
                            System.out.println("Diem danh cho ca trc");
                            return true;
                        }

                    } else if (count == 2) {
                        for (int i = 0; i < 2; i++) {
                            String buoiLam = LichDAO.getInstance().getBuoiLamByID_Lich(list_id_lich_current_day.get(i));
                            for (LichChiTiet lichChiTiet : list_lich_chi_tiet) {
                                if (lichChiTiet.getBuoi().equals(buoiLam)
                                        && NhanVien_LichDAO.getInstance().checkTimeStartExist(list_id_lich_current_day.get(i), id_nhanvien) == false) {

                                    if ((timeNow.getTime() - (lichChiTiet.getThoigianbatdau().getTime() - 1800000)) >= 0
                                            && (timeNow.getTime() - lichChiTiet.getThoigianketthuc().getTime()) < 0) {
                                        Nhanvien_Lich nhanvien = NhanVien_LichDAO.getInstance().getNhanVienLichByIdLichAndIdNhanvien(list_id_lich_current_day.get(i), id_nhanvien);
                                        nhanvien.setThoigianbatdau(sqlTS);
                                        if (NhanVien_LichDAO.getInstance().updateThoiGianNhanVien_Lich(nhanvien)) {
                                            return true;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    public boolean checkOut(String id_nhanvien) {
        boolean isSuccess = false;
        test x = new test();
        try {
            String time2 = "23:30:00";
            String time3 = "12:00:00";
            String time4 = "17:30:00";

            DateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date timeNow = format.parse(time2);
            Date timeEndSang = format.parse(time3);
            Date timeEndChieu = format.parse(time4);
//            DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
//            Date date1 = new Date();
//            Date timeNow = dateFormat1.parse(dateFormat1.format(date1));

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            Date now = dateFormat.parse(dateFormat.format(date));
            now.setHours(17);
            now.setMinutes(30);
            now.setSeconds(0);
            java.sql.Timestamp sqlTS = new java.sql.Timestamp(now.getTime());

            Date chieu = dateFormat.parse(dateFormat.format(date));
            chieu.setHours(11);
            chieu.setMinutes(59);
            chieu.setSeconds(59);
            java.sql.Timestamp timeChieu = new java.sql.Timestamp(chieu.getTime());

            Date toi = dateFormat.parse(dateFormat.format(date));
            toi.setHours(17);
            toi.setMinutes(29);
            toi.setSeconds(59);
            java.sql.Timestamp timeToi = new java.sql.Timestamp(chieu.getTime());

            List<Integer> list_id_lich_current_day = new ArrayList<>();
            list_id_lich_current_day = x.getAllIdLichCurentDayByIdNhanVien("001");

            List<LichChiTiet> list_lich_chi_tiet = LichChiTietDAO.getInstance().getAllLichChiTiet();
            int count = 0;
            if (list_id_lich_current_day.size() == 2) {
                count = list_id_lich_current_day.get(1) - list_id_lich_current_day.get(0);
                System.out.println("Count = " + count);
            }

            try {

                //Hom do nhan vien chi lam 1 ca
                if (list_id_lich_current_day.size() == 1) {

                    String buoiLam = LichDAO.getInstance().getBuoiLamByID_Lich(list_id_lich_current_day.get(0));
                    Nhanvien_Lich nhanvien = NhanVien_LichDAO.getInstance().getNhanVienLichByIdLichAndIdNhanvien(list_id_lich_current_day.get(0), id_nhanvien);

                    Date timeStart = null;
                    if (nhanvien.getThoigianbatdau() != null) {
                        String timeStartString = nhanvien.getThoigianbatdau().getHours() + ":" + nhanvien.getThoigianbatdau().getMinutes() + ":"
                                + nhanvien.getThoigianbatdau().getSeconds();
                        timeStart = format.parse(timeStartString);
                    }

                    if (nhanvien.getThoigianbatdau() == null) {
                        return false;
                    } else if (timeNow.getTime() - timeStart.getTime() > 0
                            && nhanvien.getThoigianketthuc() == null) {
                        nhanvien.setThoigianketthuc(sqlTS);
                        if (NhanVien_LichDAO.getInstance().updateThoiGianNhanVien_Lich(nhanvien)) {
                            return true;
                        }
                    } else {
                        return false;
                    }
                }

                //====================================================
                boolean isSecond = false;
                //hom do nhan vien lam 2 ca
                if (list_id_lich_current_day.size() == 2) {

                    //2 ca lam ke tiep nhau
                    if (count == 1) {
                        Nhanvien_Lich nhanvienCaTrc = NhanVien_LichDAO.getInstance().getNhanVienLichByIdLichAndIdNhanvien(list_id_lich_current_day.get(0), id_nhanvien);
                        Nhanvien_Lich nhanvienCaSau = NhanVien_LichDAO.getInstance().getNhanVienLichByIdLichAndIdNhanvien(list_id_lich_current_day.get(1), id_nhanvien);
                        Date timeStartCaTrc = null;
                        Date timeStartCaSau = null;

                        if (nhanvienCaTrc.getThoigianbatdau() != null) {

                            String timeStartCaTrcString = nhanvienCaTrc.getThoigianbatdau().getHours() + ":" + nhanvienCaTrc.getThoigianbatdau().getMinutes() + ":"
                                    + nhanvienCaTrc.getThoigianbatdau().getSeconds();
                            timeStartCaTrc = format.parse(timeStartCaTrcString);
                        }

                        if (nhanvienCaSau.getThoigianbatdau()!= null) {
                            String timeStartCaSauString = nhanvienCaSau.getThoigianbatdau().getHours() + ":" + nhanvienCaSau.getThoigianbatdau().getMinutes() + ":"
                                    + nhanvienCaSau.getThoigianbatdau().getSeconds();
                            timeStartCaSau = format.parse(timeStartCaSauString);
                        }

                        if (nhanvienCaTrc.getThoigianbatdau() != null && nhanvienCaSau.getThoigianbatdau() != null
                                && nhanvienCaTrc.getThoigianketthuc() == null && nhanvienCaSau.getThoigianketthuc() == null
                                && (timeNow.getTime() - timeStartCaTrc.getTime()) > 0) {
                            String buoiLam = LichDAO.getInstance().getBuoiLamByID_Lich(list_id_lich_current_day.get(0));
                            if (buoiLam.equals("Sang")) {

                                //Thoi gian checkout chi phu hop voi ca trc
                                if ((timeNow.getTime() - timeEndSang.getTime()) < 0) {
                                    nhanvienCaTrc.setThoigianketthuc(sqlTS);

                                    if (NhanVien_LichDAO.getInstance().updateThoiGianNhanVien_Lich(nhanvienCaTrc)) {
                                        return true;
                                    }
                                } else { //thoi gian checkout phu hop voi ca sau
                                    nhanvienCaTrc.setThoigianketthuc(timeChieu);
                                    nhanvienCaSau.setThoigianketthuc(sqlTS);
                                    if (NhanVien_LichDAO.getInstance().updateThoiGianNhanVien_Lich(nhanvienCaTrc) == true
                                            && NhanVien_LichDAO.getInstance().updateThoiGianNhanVien_Lich(nhanvienCaSau) == true) {
                                        return true;
                                    }
                                }
                            } else if (buoiLam.equals("Chieu")) {
                                //Thoi gian diem danh chi phu hop voi ca trc
                                if ((timeNow.getTime() - timeEndChieu.getTime()) < 0) {
                                    nhanvienCaTrc.setThoigianketthuc(sqlTS);

                                    if (NhanVien_LichDAO.getInstance().updateThoiGianNhanVien_Lich(nhanvienCaTrc)) {
                                        return true;
                                    }
                                } else {  //thoi gian diem danh phu hop voi ca sau
                                    nhanvienCaTrc.setThoigianketthuc(timeToi);
                                    nhanvienCaSau.setThoigianketthuc(sqlTS);
                                    if (NhanVien_LichDAO.getInstance().updateThoiGianNhanVien_Lich(nhanvienCaTrc) == true
                                            && NhanVien_LichDAO.getInstance().updateThoiGianNhanVien_Lich(nhanvienCaSau) == true) {
                                        return true;
                                    }
                                }
                            }   // ca truoc khong checkin nen chi co the checkout cho ca sau
                        } else if (nhanvienCaTrc.getThoigianbatdau() == null && nhanvienCaSau.getThoigianbatdau() != null
                                && timeNow.getTime() - timeStartCaSau.getTime() > 0) {
                            nhanvienCaSau.setThoigianketthuc(sqlTS);
                            if (NhanVien_LichDAO.getInstance().updateThoiGianNhanVien_Lich(nhanvienCaSau) == true) {
                                return true;
                            }
                        }
                        // 2 ca lam cach biet nhau
                    } else if (count == 2) {
                        Nhanvien_Lich nhanvienCaTrc = NhanVien_LichDAO.getInstance().getNhanVienLichByIdLichAndIdNhanvien(list_id_lich_current_day.get(0), id_nhanvien);
                        Nhanvien_Lich nhanvienCaSau = NhanVien_LichDAO.getInstance().getNhanVienLichByIdLichAndIdNhanvien(list_id_lich_current_day.get(1), id_nhanvien);

                        Date timeStartCaTrc = null;
                        Date timeStartCaSau = null;

                        if (nhanvienCaTrc.getThoigianbatdau() != null) {

                            String timeStartCaTrcString = nhanvienCaTrc.getThoigianbatdau().getHours() + ":" + nhanvienCaTrc.getThoigianbatdau().getMinutes() + ":"
                                    + nhanvienCaTrc.getThoigianbatdau().getSeconds();
                            timeStartCaTrc = format.parse(timeStartCaTrcString);
                        }

                        if (nhanvienCaSau.getThoigianbatdau()!= null) {
                            String timeStartCaSauString = nhanvienCaSau.getThoigianbatdau().getHours() + ":" + nhanvienCaSau.getThoigianbatdau().getMinutes() + ":"
                                    + nhanvienCaSau.getThoigianbatdau().getSeconds();
                            timeStartCaSau = format.parse(timeStartCaSauString);
                        };

                        //ca trc checkin chua checkout
                        if (nhanvienCaTrc.getThoigianketthuc() == null
                                && timeNow.getTime() - timeStartCaTrc.getTime() > 0) {
                            nhanvienCaTrc.setThoigianketthuc(sqlTS);
                            if (NhanVien_LichDAO.getInstance().updateThoiGianNhanVien_Lich(nhanvienCaTrc)) {
                                return true;
                            }  //ca trc da checkin + checkout nen checkout cho ca sau
                        } else if (nhanvienCaTrc.getThoigianketthuc() != null && nhanvienCaSau.getThoigianketthuc() == null
                                && timeNow.getTime() - timeStartCaSau.getTime() > 0) {
                            nhanvienCaSau.setThoigianketthuc(sqlTS);
                            if (NhanVien_LichDAO.getInstance().updateThoiGianNhanVien_Lich(nhanvienCaSau)) {
                                return true;
                            }
                        }

                    }

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    public static void main(String[] args) {

        test x = new test();

        if (x.checkOut("001")) {
            System.out.println("Check out success");
        } else {
            System.out.println("Check out fail");
        }
        List<LuongNhanVien> list = QuanLyTienLuongBLL.getInstance().getAllLuongNhanVien("Pha Che");
    }
}
