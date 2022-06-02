/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbl3quanlynhanvien.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import javax.swing.table.DefaultTableModel;
import pbl3quanlynhanvien.DAL.DatabaseHelper;
import pbl3quanlynhanvien.DTO.PhucVu;

/**
 *
 * @author PC
 */
public class QuanLyXepLichDAO {
    
    private static QuanLyXepLichDAO Instance;
    
     public static QuanLyXepLichDAO getInstance() {
        if(Instance == null)
        {
            Instance = new QuanLyXepLichDAO();
        }
        return Instance;
    }

    private static void setInstance(QuanLyXepLichDAO Instance) {
    }
    
    public int max = 10000;
        public static String manvpv[];
	public static String manvpc[];
        
        
      
	public Random ran = new Random();
	public static int cathe = 1000;
        public static int n;
        public static int phucvus[][];
        public static int phucvuc[][];
        public static int phucvut[][];
        public static int phaches[][];
        public static int phachec[][];
        public static int phachet[][];
	int[][] nghiem = new int [cathe][];
	int[] thichnghi = new int[n];
        static public int team[]=new int[cathe];
        static int chay=0;
        public int dulieu[][]=new int[2][100];
        public int luu[][]=new int[2][1000];
        static int chay2=0;
        static int best=0;
   public void quanlylich() throws Exception{
       PhucVu sv= new PhucVu();
             n=sv.ban.length;
             n=n/3;
            System.out.println("chieu dai cua ban"+n);
            int m=0;
            
 String sql = "select count(distinct id_nhanvien) from thongtinchung where id_chucvu in "
                    + "(select id_chucvu from chucvu where tenchucvu = 'Phuc Vu')";
       try(
               Connection con = DatabaseHelper.openConnection();
               PreparedStatement psttm = con.prepareStatement(sql);)
               {
           //dem so nhan vien co lich ban trong bang ban voi chuc vu la phuc vu
                ResultSet rs=psttm.executeQuery(sql);
                while(rs.next())
                { 
                     m =rs.getInt(1);
                     System.out.println("M = " + m);
                }
                
                System.out.println("So nhan vien phuc vu: " + m);
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        
        } 

            //tao ra mang do thich nghi chua lich ban cua nhan vien theo sang chieu toi
            phucvus = new int[m][n];
            phucvuc = new int[m][n];
            phucvut = new int[m][n];
            
            //mang chua ma nhan vien
            
         manvpv= new String[m];
         
         
             sql = "select id_nhanvien from thongtinchung where id_chucvu in "
                    + "(select id_chucvu from chucvu where tenchucvu = 'Phuc Vu')";
       try(Connection con = DatabaseHelper.openConnection();
               PreparedStatement psttm = con.prepareStatement(sql);){
           int i=0;
                ResultSet rs=psttm.executeQuery(sql);
                while(rs.next())
                { 
                     manvpv[i]=rs.getString(1);
                     i+=1;
                }
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        
        }
       
       int h=0;
       //bartender bt= new bartender();
           
try(Connection con = DatabaseHelper.openConnection()){
         sql = "select count(distinct id_nhanvien) from thongtinchung where id_chucvu in "
                    + "(select id_chucvu from chucvu where tenchucvu = 'Pha Che')";
           
            java.sql.Statement st=con.createStatement();
                ResultSet rs=st.executeQuery(sql);
                while(rs.next())
                { 
                     h =rs.getInt(1);
                }
                 System.out.println("So nhan vien pha che" + h);
        } catch (SQLException ex) {
            ex.printStackTrace();
            
         
   }

             manvpc= new String[h];
       try(Connection con = DatabaseHelper.openConnection()){
           int i=0;
         sql = "select id_nhanvien from thongtinchung where id_chucvu in "
                    + "(select id_chucvu from chucvu where tenchucvu = 'Pha Che')";
            java.sql.Statement st=con.createStatement();
                ResultSet rs=st.executeQuery(sql);
                while(rs.next())
                { 
                     manvpc[i]=rs.getString(1);
                     i+=1;
                }
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        
        }
         phaches= new int[h][n];
         phachec= new int[h][n];
         phachet= new int[h][n];
       khoitaobangmot(phucvus, m, n);
       khoitaobangmot(phucvuc, m, n);
       khoitaobangmot(phucvut, m, n);
       khoitaobangmot(phaches, h, n);
       khoitaobangmot(phachec, h, n);
       khoitaobangmot(phachet, h, n);
       System.out.println("-------------------------------");
       System.out.println("lich ban cuar nhan vien pha che");
       luudulieuthanhvienban(phaches,phachec,phachet, manvpc, h, n);
       xuat(phaches, h, n);
       System.out.println();
       xuat(phachec, h, n);
       System.out.println();
       xuat(phachet, h, n);
       System.out.println();
       System.out.println("-------------------------------");
       System.out.println("lich ban cuar nhan vien phuc vu");
       luudulieuthanhvienban(phucvus,phucvuc,phucvut,manvpv, m, n);
       xuat(phucvus, m, n);
       System.out.println();
       xuat(phucvuc, m, n);
       System.out.println();
       xuat(phucvut, m, n);
    
}
   
   //tao ra mang ban dau voi lich tat ca nhan vien deu ranh
   public void khoitaobangmot(int a[][],int m,int n){
       for(int i=0;i<m;i++){
           for(int j=0;j<n;j++){
               a[i][j]=1;
           }
       }
   }
   public void xuat(int a[][],int m,int n){
       for(int i=0;i<m;i++){
           for(int j=0;j<n;j++){
               System.out.print(" "+a[i][j]);
           }
           System.out.println("");
       }
   }
   
   public void luudulieuthanhvienban(int s[][],int c[][],int t[][],String mnv[],int m,int n) throws Exception{
       QuanLyXepLichDAO ditruyen = new QuanLyXepLichDAO();
       //luudulieuthanhvienban(phucvus,phucvuc,phucvut,manvpv, m, n);
        String sql = "select * from lich where lich.id_lich in (select ban.id_lich from ban where id_nhanvien=?)";
        try(
                Connection con = DatabaseHelper.openConnection();//mo ket noi
                PreparedStatement pre = con.prepareStatement(sql);
                ){
            for(int i=0;i<m;i++){
            pre.setString(1,mnv[i]);
              try(  ResultSet rs = pre.executeQuery()){
                while(rs.next())
                { 
                    SimpleDateFormat dayFormat =new SimpleDateFormat("dd");
                    String day = dayFormat.format(rs.getDate("ngaylamviec"));
                    int result = Integer.parseInt(day);
                        if(rs.getString("buoi").equals("Sang"))
                        {
                            s[i][result-1] = 100;
                            System.out.println("==Ngay: " + result + " ==Buoi: " + rs.getString("buoi"));
                            
                        } else if(rs.getString("buoi").equals("Chieu"))
                        {
                            c[i][result-1] = 100;
                            System.out.println("==Ngay: " + result + " ==Buoi: " + rs.getString("buoi"));
                            
                        } else if(rs.getString("buoi").equals("Toi"))
                        {
                            t[i][result-1] = 100;
                            System.out.println("==Ngay: " + result + " ==Buoi: " + rs.getString("buoi"));
                            
                        }
                    
                    //System.out.print("\nheloo moi nguoi"+rs.getInt(1));
                     
                            }              
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        }
   
}
   }
   
   
    public void khoitao(int h)
	{
            int count = 0;
		for (int i=0;i<cathe;i++)
		{
			nghiem[i] = new int[2*n];
			for (int j=0;j<2*n;j++){
                            nghiem[i][j] = ran.nextInt(h);
                            count++;
                        }
		}
                System.out.println("COUNT: " + count);
	}
    void TangDan(int a[]){
    int tg;
    for(int i = 0; i < chay-1; i++){
        for(int j = i + 1; j < chay; j++){
            if(a[i] > a[j]){
                tg = a[i];
                a[i] = a[j];
                a[j] = tg;        
            }
        }
    }
}

        public void danhgia(int mang[][])
	{
            chay=0;
            int tong;
           
		for (int k=0;k<cathe;k++)
                {   
                    tong =0;
                      
                         for (int j=0;j<n;j++)
                         {
                           tong+=mang[nghiem[k][j]][j]+mang[nghiem[k][n+j]][j];
                           if(nghiem[k][j]==nghiem[k][n+j]){
                               tong+=100000;
                           }
                           
                     }
                         
                               team[chay]=tong;
                                chay++;
            }
                    
        }
                
    
   
     public void chonloc()
	{
		int [] temp = team.clone();
                TangDan(temp);
                for(int i=0;i<5;i++){
                    System.out.print(" "+temp[i]);
                }
		int nguong = temp[chay*80/100];
		for (int i=0;i<chay;i++){
			if (team[i]>nguong ){
                            
				nghiem[i]=nghiem[ran.nextInt(cathe)].clone();// tim cai khac roi moi clone
			}
		}
	}
      public void laighep() {
          
		for (int i=0;i<100;i++){
			int cha=ran.nextInt(cathe);
			int me = ran.nextInt(cathe);
                        
			for (int j=0;j<nghiem[cha].length;j++)
				if (ran.nextInt(2)==1){
					int thu=nghiem[cha][j];
					nghiem[cha][j]=nghiem[me][j];
					nghiem[me][j]=thu;
			}
                        
		}
	}
      public void dotbien(int songuoi)
	{
            
                
                int bit;
                int index;
                     index=ran.nextInt(cathe);
                     bit=ran.nextInt(2*n);
                nghiem[index][bit]= ran.nextInt(songuoi);
                
	}
      public void Print(int a[][]) {
		int [] temp = team.clone();
                TangDan(temp);
                best = temp[0];
		System.out.print(" ==best: " + best+" == ");
                System.out.println();
                
		for (int i=0;i<cathe;i++){
			if (team[i]==best){
				for (int j=0;j<n;j++){
                                  //  if(best<=161){
                                     a[0][j]=nghiem[i][j];
                                     a[1][j]=nghiem[i][n+j];
                                }  
				System.out.println();
				break;
			}    
		}
	}
      public void xuatMaNhanVien(String manv[])
              {
                  for (String nv : manv) {
                      System.out.println(nv + " ");
                  }
              }
    
     public void Xuat(int a[][]){
         for(int i=0;i<2;i++){
            for(int j=0;j<n;j++){
                System.out.print(a[i][j]+" ");
                
            }
            System.out.println();
        }
         
     }   

        public void xepLichPhaChe() throws Exception{
         QuanLyXepLichDAO diTruyen = new QuanLyXepLichDAO();
        try {
            diTruyen.quanlylich();
        } catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("xuat chieu dai"+QuanLyXepLichDAO.phaches.length);
        int nhanviendv=QuanLyXepLichDAO.phaches.length;
        //diTruyen.xuat(diTruyen.phucvu,nhanviendv , diTruyen.n);
        //buoi sang
        diTruyen.khoitao(nhanviendv);
        int sang[][]= new int[2][32];           //bug
        int solanlap=1000;
        for(int i=0;i<solanlap;i++){
            diTruyen.danhgia(QuanLyXepLichDAO.phaches);
            diTruyen.Print(sang);
            diTruyen.chonloc();
            diTruyen.laighep();
            diTruyen.dotbien(nhanviendv);
            if(diTruyen.best <=62){
                break;
            }

        }
        System.out.println("ket qua cua buoi sang: ");
        diTruyen.Xuat(sang);
            System.out.println("Ma nhan vien");
           diTruyen.xuatMaNhanVien(QuanLyXepLichDAO.manvpc);
        // buoi chieu
        diTruyen.khoitao(nhanviendv);
        int chieu[][]= new int[2][32];
        for(int i=0;i<solanlap;i++){
            diTruyen.danhgia(QuanLyXepLichDAO.phachec);
            diTruyen.Print(chieu);
            diTruyen.chonloc();
            diTruyen.laighep();
            diTruyen.dotbien(nhanviendv);
            if(diTruyen.best <=62){
                break;
            }

        }
        System.out.println("ket qua cua buoi chieu: ");
        diTruyen.Xuat(chieu);
        // buoi toi
        diTruyen.khoitao(nhanviendv);
        int toi[][]= new int[2][32];
        for(int i=0;i<solanlap;i++){
            diTruyen.danhgia(QuanLyXepLichDAO.phachet);
            diTruyen.Print(toi);
            diTruyen.chonloc();
            diTruyen.laighep();
            diTruyen.dotbien(nhanviendv);
            if(diTruyen.best <=62){
                break;
            }
 
        }
        System.out.println("ket qua cua buoi toi: ");
        diTruyen.Xuat(toi);
        try {
            luulichlam(sang, chieu, toi, diTruyen.manvpc);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

     }
        
        public void xeplichphucvu() throws Exception{
         QuanLyXepLichDAO diTruyen = new QuanLyXepLichDAO();
        try {
            diTruyen.quanlylich();
        } catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("xuat chieu dai"+QuanLyXepLichDAO.phucvus.length);
        int nhanviendv=QuanLyXepLichDAO.phucvus.length;
        //diTruyen.xuat(diTruyen.phucvu,nhanviendv , diTruyen.n);
        //buoi sang
        diTruyen.khoitao(nhanviendv);
        int sang[][]= new int[2][32];           //bug
        int solanlap=1000;
        for(int i=0;i<solanlap;i++){
            diTruyen.danhgia(QuanLyXepLichDAO.phucvus);
            diTruyen.Print(sang);
            diTruyen.chonloc();
            diTruyen.laighep();
            diTruyen.dotbien(nhanviendv);
            if(diTruyen.best <=62){
                break;
            }

        }
        System.out.println("ket qua cua buoi sang: ");
        diTruyen.Xuat(sang);
            System.out.println("Ma nhan vien");
           diTruyen.xuatMaNhanVien(QuanLyXepLichDAO.manvpv);
        // buoi chieu
        diTruyen.khoitao(nhanviendv);
        int chieu[][]= new int[2][32];
        for(int i=0;i<solanlap;i++){
            diTruyen.danhgia(QuanLyXepLichDAO.phucvuc);
            diTruyen.Print(chieu);
            diTruyen.chonloc();
            diTruyen.laighep();
            diTruyen.dotbien(nhanviendv);
            if(diTruyen.best <=62){
                break;
            }

        }
        System.out.println("ket qua cua buoi chieu: ");
        diTruyen.Xuat(chieu);
        // buoi toi
        diTruyen.khoitao(nhanviendv);
        int toi[][]= new int[2][32];
        for(int i=0;i<solanlap;i++){
            diTruyen.danhgia(QuanLyXepLichDAO.phucvut);
            diTruyen.Print(toi);
            diTruyen.chonloc();
            diTruyen.laighep();
            diTruyen.dotbien(nhanviendv);
            if(diTruyen.best <=62){
                break;
            }

        }
        System.out.println("ket qua cua buoi toi: ");
        diTruyen.Xuat(toi);
        try {
            luulichlam(sang, chieu, toi, diTruyen.manvpv);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

     }
        
         public void luulichlam(int s[][],int c[][],int t[][],String manv[]) throws Exception {
        
        
        String sql = "INSERT INTO `nhanvien_lich`( `id_nhanvien`, `id_lich`)"+ "VALUES (?,?)";
        
        try(
                Connection con = DatabaseHelper.openConnection();//mo ket noi
                PreparedStatement pre= con.prepareStatement(sql);
                ){
                for(int i=0;i<n;i++){
                for(int j=0;j<=1;j++)
                   try {
            pre.setString(1,manv[s[j][i]]);
            pre.setInt(2, i*3+1);
             pre.executeUpdate();
            pre.setString(1,manv[c[j][i]]);
            pre.setInt(2, i*3+2);
            pre.executeUpdate();
            pre.setString(1,manv[t[j][i]]);
            pre.setInt(2, i*3+3);
            pre.executeUpdate();
            
                }catch (SQLException ex) {
            ex.printStackTrace();
            
        }
        }

        }
}
         
         
         public void RenderToTable(DefaultTableModel modelpv, DefaultTableModel modelpc)
         {
             modelpv.setRowCount(0);
             modelpc.setRowCount(0);
             
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
                     Connection con = DatabaseHelper.openConnection();
                     PreparedStatement psttm = con.prepareStatement(sql);
                     ){
                 psttm.setString(1, month);
                 ResultSet rs = psttm.executeQuery();
                 while(rs.next())
                 {
                     DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                     String strDate = df.format(rs.getDate("ngaylamviec"));
                     
                    headerModelPV[count] = strDate;
                    headerModelPC[count] = strDate;
                    count++;
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             }
             
             modelpv.setColumnIdentifiers(headerModelPV);
             modelpc.setColumnIdentifiers(headerModelPC);
             
             Object[][] lichPhucVu = new Object[6][32];
             Object[][] lichPhaChe = new Object[6][32];
             
             //Ghi cac buoi Sang Chieu Toi vao mang phuc vu va pha che
             for(int i = 0; i < 6; i++)
                            {
                               if(i < 2)
                               {
                                   lichPhucVu[i][0] = "Sang";
                                   lichPhaChe[i][0] = "Sang";
                               } else if(i < 4)
                               {
                                   lichPhucVu[i][0] = "Chieu";
                                   lichPhaChe[i][0] = "Chieu";
                               } else if(i < 6)
                               {
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
                     + "where tenchucvu = 'Phuc Vu') "
                     + "order by id_lich asc";
             
             try(Connection con = DatabaseHelper.openConnection();
                     PreparedStatement psttm = con.prepareStatement(sql);
                     ){
                        ResultSet rs = psttm.executeQuery();
                        
                         int  countSang=0, countChieu=2, countToi=4;
                        
                        while(rs.next())
                        {
                            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                            String strDate = df.format(rs.getDate("ngaylamviec"));
                            for(int i = 1; i < headerModelPV.length; i++)
                                {
                                    if(strDate.equals(headerModelPV[i]) && rs.getString("buoi").equals("Sang"))
                                    {
                                        lichPhucVu[countSang][i] = rs.getString("hoten");
                                        if(countSang == 1)
                                        {
                                            countSang = 0;
                                        }else {
                                            countSang++;
                                        }
                                        break;
                                        
                                    }else if(strDate.equals(headerModelPV[i]) && rs.getString("buoi").equals("Chieu"))
                                    {
                                        lichPhucVu[countChieu][i] = rs.getString("hoten");
                                        if(countChieu == 3)
                                        {
                                            countChieu = 2;
                                        }else
                                        
                                            countChieu++;
                                        break;
                                    }else if(strDate.equals(headerModelPV[i]) && rs.getString("buoi").equals("Toi"))
                                    {
                                        lichPhucVu[countToi][i] = rs.getString("hoten");
                                        if(countToi == 5)
                                        {
                                            countToi = 4;
                                        }else
                                        
                                        countToi++;
                                        break;
                                    }
                                }
                        }
             }catch(Exception e)
             {
                 e.printStackTrace();
             }
             
             for(int i = 0; i < 6;i++)
             {
                 Object[] temp = new Object[32];
                 for(int j = 0; j < 32; j++)
                 {
                     temp[j] = lichPhucVu[i][j];
                 System.out.println(temp[j]);
                 }
                 modelpv.addRow(temp);
                 
             modelpv.fireTableDataChanged();
             }
             
             //=======
             sql = "select thongtinchung.hoten, lich.ngaylamviec, lich.buoi,lich.id_lich from thongtinchung "
                     + "join nhanvien_lich on thongtinchung.id_nhanvien = nhanvien_lich.id_nhanvien "
                     + "join lich on lich.id_lich = nhanvien_lich.id_lich "
                     + "where thongtinchung.id_nhanvien in ("
                     + "select id_nhanvien from thongtinchung "
                     + "join chucvu on thongtinchung.id_chucvu = chucvu.id_chucvu "
                     + "where tenchucvu = 'Pha Che') "
                     + "order by id_lich asc";
             
             try(Connection con = DatabaseHelper.openConnection();
                     PreparedStatement psttm = con.prepareStatement(sql);
                     ){
                        ResultSet rs = psttm.executeQuery();
                        
                         int  countSang=0, countChieu=2, countToi=4;
                        
                        while(rs.next())
                        {
                            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                            String strDate = df.format(rs.getDate("ngaylamviec"));
                            for(int i = 1; i < headerModelPV.length; i++)
                                {
                                    if(strDate.equals(headerModelPV[i]) && rs.getString("buoi").equals("Sang"))
                                    {
                                        lichPhaChe[countSang][i] = rs.getString("hoten");
                                        if(countSang == 1)
                                        {
                                            countSang = 0;
                                        }else {
                                            countSang++;
                                        }
                                        break;
                                        
                                    }else if(strDate.equals(headerModelPV[i]) && rs.getString("buoi").equals("Chieu"))
                                    {
                                        lichPhaChe[countChieu][i] = rs.getString("hoten");
                                        if(countChieu == 3)
                                        {
                                            countChieu = 2;
                                        }else
                                        
                                            countChieu++;
                                        break;
                                    }else if(strDate.equals(headerModelPV[i]) && rs.getString("buoi").equals("Toi"))
                                    {
                                        lichPhaChe[countToi][i] = rs.getString("hoten");
                                        if(countToi == 5)
                                        {
                                            countToi = 4;
                                        }else
                                        
                                        countToi++;
                                        break;
                                    }
                                }
                        }
             }catch(Exception e)
             {
                 e.printStackTrace();
             }
             
             for(int i = 0; i < 6;i++)
             {
                 Object[] temp = new Object[32];
                 for(int j = 0; j < 32; j++)
                 {
                     temp[j] = lichPhaChe[i][j];
                 System.out.println(temp[j]);
                 }
                 modelpc.addRow(temp);
                 
             modelpc.fireTableDataChanged();
             }
             
         }
         
         public boolean checkXepLich(){
             boolean flag = false;
             
             String sql = "select count(distinct nhanvien_lich.id_lich) from nhanvien_lich " 
                     +"where nhanvien_lich.id_lich in(" 
                     +"select id_lich from lich where month(lich.ngaylamviec) = ?)";
             try(
                     Connection con = DatabaseHelper.openConnection();
                     PreparedStatement psttm = con.prepareStatement(sql);) 
             {
                    
                    
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");  
                    LocalDateTime now = LocalDateTime.now();  
                    String month = dtf.format(now);
                    DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy");  
                    String year = dtf1.format(now);
                    int sl = getDayInMonths(Integer.parseInt(month), Integer.parseInt(year))* 3;
                    psttm.setString(1, month);
                    ResultSet rs = psttm.executeQuery();
                    while(rs.next())
                    {
                        System.out.println("count" + rs.getInt(1));
                        System.out.println("sl" + sl);
                    if(rs.getInt(1) == sl)
                        flag = true;
                    break;
                    }
                 
             } catch (Exception e) {
                 e.printStackTrace();
             }
             
             return flag;
         }
    
    public static int getDayInMonths(int month, int year) {
    int daysInMonth ;
    if (month == 4 || month == 6 || month == 9 || month == 11) {
        daysInMonth = 30;
    }
    else {
        if (month == 2) {
            daysInMonth = (year % 4 == 0) ? 29 : 28;
        } else {
            daysInMonth = 31;
        }
    }
    return daysInMonth;
    }
    
    public boolean deleteNhanVienLich()
    {
        String sql = "delete from nhanvien_lich";
        try(Connection con = DatabaseHelper.openConnection();
                PreparedStatement psttm = con.prepareStatement(sql);) {
              return  psttm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
