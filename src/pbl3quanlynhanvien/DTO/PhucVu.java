/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbl3quanlynhanvien.DTO;

import java.util.Calendar;

/**
 *
 * @author PC
 */
public class PhucVu extends Person{
    public int ban[];
        public int songay(int m, int y){
        int sn[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if ((y%4==0&&y%100!=0)||(y%400==0)) sn[2] = 29;
        return sn[m];
        }
        public PhucVu() throws Exception{
            Calendar cal = Calendar.getInstance();
            int thang;
            if(cal.get(Calendar.MONTH)==12){
                thang=1;
            }else{
                thang=cal.get(Calendar.MONTH)+1;
            }
            int n=songay(thang,cal.get(Calendar.YEAR));
            n=n*3;
            ban=new int[n+1];
            for(int i=1;i<=n;i++){
                ban[i]=1;
            }
            //laydulieuBan();
        }

}
