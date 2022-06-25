/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbl3quanlynhanvien.BLL;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pbl3quanlynhanvien.DAL.LichChiTietDAO;
import pbl3quanlynhanvien.DAL.LichDAO;
import pbl3quanlynhanvien.DAL.NhanVien_LichDAO;
import pbl3quanlynhanvien.DTO.LichChiTiet;
import pbl3quanlynhanvien.DTO.Nhanvien_Lich;
import pbl3quanlynhanvien.testQLLich.test;

/**
 *
 * @author PC
 */
public class QuanLyDiemDanhBLL {

    private static QuanLyDiemDanhBLL Instance;

    public static QuanLyDiemDanhBLL getInstance() {
        if (Instance == null) {
            Instance = new QuanLyDiemDanhBLL();
        }
        return Instance;
    }

    private static void setInstance(QuanLyDiemDanhBLL Instance) {
    }

    public boolean checkIn(String id_nhanvien) {
        boolean isSuccess = false;

        try {
            DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
            Date date1 = new Date();
            Date timeNow = dateFormat1.parse(dateFormat1.format(date1));

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            Date now = dateFormat.parse(dateFormat.format(date));
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
            list_id_lich_current_day = NhanVien_LichDAO.getInstance().getAllIdLichCurentDayByIdNhanVien(id_nhanvien,
                    NhanVien_LichDAO.getInstance().getID_lichInCurrentDay());

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

            DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
            Date date1 = new Date();
            Date timeNow = dateFormat1.parse(dateFormat1.format(date1));

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            Date now = dateFormat.parse(dateFormat.format(date));
            java.sql.Timestamp sqlTS = new java.sql.Timestamp(now.getTime());

            String time3 = "12:00:00";
            String time4 = "17:30:00";

            DateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date timeEndSang = format.parse(time3);
            Date timeEndChieu = format.parse(time4);

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
            list_id_lich_current_day = NhanVien_LichDAO.getInstance().getAllIdLichCurentDayByIdNhanVien(id_nhanvien,
                    NhanVien_LichDAO.getInstance().getID_lichInCurrentDay());

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

                        if (nhanvienCaSau.getThoigianbatdau() != null) {
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

                        if (nhanvienCaSau.getThoigianbatdau() != null) {
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
}
