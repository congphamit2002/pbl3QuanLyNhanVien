///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package pbl3quanlynhanvien.testQLLich;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Random;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import pbl3quanlynhanvien.DAL.DatabaseHelper;
//
///**
// *
// * @author PC
// */
//public class thuatToanDiTruyen {
//    public int max = 10000;
//        public static String manvpv[];
//	public static String manvpc[];
//      
//	public Random ran = new Random();
//	public static int cathe = 1000;
//        public static int n;
//        public static int phucvus[][];
//        public static int phucvuc[][];
//        public static int phucvut[][];
//        public static int phaches[][];
//        public static int phachec[][];
//        public static int phachet[][];
//	int[][] nghiem = new int [cathe][];
//	int[] thichnghi = new int[n];
//        static public int team[]=new int[cathe];
//        static int chay=0;
//        public int dulieu[][]=new int[2][100];
//        public int luu[][]=new int[2][1000];
//        static int chay2=0;
//        static int best=0;
//   public void quanlylich() throws Exception{
//       service sv= new service();
//             n=sv.ban.length;
//             n=n/3;
//            System.out.println("chieu dai cua ban"+n);
//            int m=0;
//       try(
//               Connection con = DatabaseHelper.openConnection()){
//           //dem so nhan vien co lich ban trong bang ban voi chuc vu la phuc vu
//        String sql = "select COUNT(DISTINCT ban.id_nhanvien) from`ban`"
//                + " join `thongtinchung` ON ban.id_nhanvien=thongtinchung.id_nhanvien "
//                + "join `chucvu` ON chucvu.id_chucvu= thongtinchung.`id_chucvu` "
//                + "where  chucvu.tenchucvu='Phuc Vu'";
//            java.sql.Statement st=con.createStatement();
//                ResultSet rs=st.executeQuery(sql);
//                while(rs.next())
//                { 
//                     m =rs.getInt(1);
//                }
//                
//                System.out.println("So nhan vien phuc vu: " + m);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            
//        
//        } 
//
//            //tao ra mang do thich nghi chua lich ban cua nhan vien theo sang chieu toi
//            phucvus = new int[m][n];
//            phucvuc = new int[m][n];
//            phucvut = new int[m][n];
//            
//            //mang chua ma nhan vien
//         manvpv= new String[m];
//       try(Connection con = DatabaseHelper.openConnection()){
//           int i=0;
//        String sql =  "select DISTINCT ban.id_nhanvien from`ban`"
//                + " join `thongtinchung` ON ban.id_nhanvien=thongtinchung.id_nhanvien "
//                + "join `chucvu` ON chucvu.id_chucvu= thongtinchung.`id_chucvu` "
//                + "where  chucvu.tenchucvu='Phuc Vu'";
//            java.sql.Statement st=con.createStatement();
//                ResultSet rs=st.executeQuery(sql);
//                while(rs.next())
//                { 
//                     manvpv[i]=rs.getString(1);
//                     i+=1;
//                }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            
//        
//        }
//       
//       int h=0;
//       //bartender bt= new bartender();
//           
//try(Connection con = DatabaseHelper.openConnection()){
//        String sql = "select COUNT(DISTINCT ban.id_nhanvien) from`ban`"
//                + " join `thongtinchung` ON ban.id_nhanvien=thongtinchung.id_nhanvien "
//                + "join `chucvu` ON chucvu.id_chucvu= thongtinchung.`id_chucvu` "
//                + "where  chucvu.tenchucvu='Pha Che'";
//            java.sql.Statement st=con.createStatement();
//                ResultSet rs=st.executeQuery(sql);
//                while(rs.next())
//                { 
//                     h =rs.getInt(1);
//                }
//                 System.out.println("So nhan vien pha che" + h);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            
//         
//   }
//
//             manvpc= new String[h];
//       try(Connection con = DatabaseHelper.openConnection()){
//           int i=0;
//        String sql = "select DISTINCT ban.id_nhanvien from`ban`"
//                + " join `thongtinchung` ON ban.id_nhanvien=thongtinchung.id_nhanvien "
//                + "join `chucvu` ON chucvu.id_chucvu= thongtinchung.`id_chucvu` "
//                + "where  chucvu.tenchucvu='Pha Che'";
//            java.sql.Statement st=con.createStatement();
//                ResultSet rs=st.executeQuery(sql);
//                while(rs.next())
//                { 
//                     manvpc[i]=rs.getString(1);
//                     i+=1;
//                }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            
//        
//        }
//         phaches= new int[h][n];
//         phachec= new int[h][n];
//         phachet= new int[h][n];
//       khoitaobangmot(phucvus, m, n);
//       khoitaobangmot(phucvuc, m, n);
//       khoitaobangmot(phucvut, m, n);
//       khoitaobangmot(phaches, h, n);
//       khoitaobangmot(phachec, h, n);
//       khoitaobangmot(phachet, h, n);
//       System.out.println("-------------------------------");
//       System.out.println("lich ban cuar nhan vien pha che");
//       luudulieuthanhvienban(phaches,phachec,phachet, manvpc, h, n);
//       xuat(phaches, h, n);
//       System.out.println();
//       xuat(phachec, h, n);
//       System.out.println();
//       xuat(phachet, h, n);
//       System.out.println();
//       System.out.println("-------------------------------");
//       System.out.println("lich ban cuar nhan vien phuc vu");
//       luudulieuthanhvienban(phucvus,phucvuc,phucvut,manvpv, m, n);
//       xuat(phucvus, m, n);
//       System.out.println();
//       xuat(phucvuc, m, n);
//       System.out.println();
//       xuat(phucvut, m, n);
//    
//}
//   
//   //tao ra mang ban dau voi lich tat ca nhan vien deu ranh
//   public void khoitaobangmot(int a[][],int m,int n){
//       for(int i=0;i<m;i++){
//           for(int j=0;j<n;j++){
//               a[i][j]=1;
//           }
//       }
//   }
//   public void xuat(int a[][],int m,int n){
//       for(int i=0;i<m;i++){
//           for(int j=0;j<n;j++){
//               System.out.print(" "+a[i][j]);
//           }
//           System.out.println("");
//       }
//   }
//   
//   public void luudulieuthanhvienban(int s[][],int c[][],int t[][],String mnv[],int m,int n) throws Exception{
//       thuatToanDiTruyen ditruyen = new thuatToanDiTruyen();
//       //luudulieuthanhvienban(phucvus,phucvuc,phucvut,manvpv, m, n);
//        String sql = "select * from lich where lich.id_lich in (select ban.id_lich from ban where id_nhanvien=?)";
//        try(
//                Connection con = DatabaseHelper.openConnection();//mo ket noi
//                PreparedStatement pre = con.prepareStatement(sql);
//                ){
//            for(int i=0;i<m;i++){
//            pre.setString(1,mnv[i]);
//              try(  ResultSet rs = pre.executeQuery()){
//                while(rs.next())
//                { 
//                    SimpleDateFormat dayFormat =new SimpleDateFormat("dd");
//                    String day = dayFormat.format(rs.getDate("ngaylamviec"));
//                    int result = Integer.parseInt(day);
//                        if(rs.getString("buoi").equals("Sang"))
//                        {
//                            s[i][result-1] = 100;
//                            System.out.println("==Ngay: " + result + " ==Buoi: " + rs.getString("buoi"));
//                            
//                        } else if(rs.getString("buoi").equals("Chieu"))
//                        {
//                            c[i][result-1] = 100;
//                            System.out.println("==Ngay: " + result + " ==Buoi: " + rs.getString("buoi"));
//                            
//                        } else if(rs.getString("buoi").equals("Toi"))
//                        {
//                            t[i][result-1] = 100;
//                            System.out.println("==Ngay: " + result + " ==Buoi: " + rs.getString("buoi"));
//                            
//                        }
//                    
//                    //System.out.print("\nheloo moi nguoi"+rs.getInt(1));
//                     
//                            }              
//        }catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        }
//   
//}
//   }
//   
//   
// public void khoitao(int h)
//	{
//		for (int i=0;i<cathe;i++)
//		{
//			nghiem[i] = new int[2*n];
//			for (int j=0;j<2*n;j++){
//                            nghiem[i][j] = ran.nextInt(h);
//                        } 
//		}
//	}
//    void TangDan(int a[]){
//    int tg;
//    for(int i = 0; i < chay-1; i++){
//        for(int j = i + 1; j < chay; j++){
//            if(a[i] > a[j]){
//                tg = a[i];
//                a[i] = a[j];
//                a[j] = tg;        
//            }
//        }
//    }
//}
//
//        public void danhgia(int mang[][])
//	{
//            chay=0;
//            int tong;
//           
//		for (int k=0;k<cathe;k++)
//                {   
//                    tong =0;
//                      
//                         for (int j=0;j<n;j++)
//                         {
//                           tong+=mang[nghiem[k][j]][j]+mang[nghiem[k][n+j]][j];
//                           if(nghiem[k][j]==nghiem[k][n+j]){
//                               tong+=100000;
//                           }
//                           
//                     }
//                         
//                               team[chay]=tong;
//                                chay++;
//            }
//                    
//        }
//                
//    
//   
//     public void chonloc()
//	{
//		int [] temp = team.clone();
//                TangDan(temp);
//                for(int i=0;i<5;i++){
//                    System.out.print(" "+temp[i]);
//                }
//		int nguong = temp[chay*80/100];
//		for (int i=0;i<chay;i++){
//			if (team[i]>nguong ){
//                            
//				nghiem[i]=nghiem[ran.nextInt(cathe)].clone();// tim cai khac roi moi clone
//			}
//		}
//	}
//      public void laighep() {
//          
//		for (int i=0;i<100;i++){
//			int cha=ran.nextInt(cathe);
//			int me = ran.nextInt(cathe);
//                        
//			for (int j=0;j<nghiem[cha].length;j++)
//				if (ran.nextInt(2)==1){
//					int thu=nghiem[cha][j];
//					nghiem[cha][j]=nghiem[me][j];
//					nghiem[me][j]=thu;
//			}
//                        
//		}
//	}
//      public void dotbien(int songuoi)
//	{
//            
//                
//                int bit;
//                int index;
//                     index=ran.nextInt(cathe);
//                     bit=ran.nextInt(2*n);
//                nghiem[index][bit]= ran.nextInt(songuoi);
//                
//	}
////       public void khoittaomang(int h){
////          for(int i=0;i<2;i++){
////                    for(int j=0;j<h;j++){
////                        dulieu[i][j]=0;
////                    }
////                }
////      }
//      public void Print(int a[][]) {
//		int [] temp = team.clone();
//                TangDan(temp);
//                best = temp[0];
//		System.out.print(" ==best: " + best+" == ");
//                System.out.println();
//                
//		for (int i=0;i<cathe;i++){
//			if (team[i]==best){
//				for (int j=0;j<n;j++){
//                                  //  if(best<=161){
//                                     a[0][j]=nghiem[i][j];
//                                     a[1][j]=nghiem[i][n+j];
//                                }  
//				System.out.println();
//				break;
//			}    
//		}
//	}
//      public void xuatMaNhanVien(String manv[])
//              {
//                  for (String nv : manv) {
//                      System.out.println(nv + " ");
//                  }
//              }
//    
//     public void Xuat(int a[][]){
//         for(int i=0;i<2;i++){
//            for(int j=0;j<n;j++){
//                System.out.print(a[i][j]+" ");
//                
//            }
//            System.out.println();
//        }
//         
//     }   
//
//        public void xeplichphucvu() throws Exception{
//         thuatToanDiTruyen diTruyen = new thuatToanDiTruyen();
//        try {
//            diTruyen.quanlylich();
//        } catch(Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("xuat chieu dai"+thuatToanDiTruyen.phucvus.length);
//        int nhanviendv=thuatToanDiTruyen.phucvus.length;
//        //diTruyen.xuat(diTruyen.phucvu,nhanviendv , diTruyen.n);
//        //buoi sang
//        diTruyen.khoitao(nhanviendv);
//        int sang[][]= new int[2][32];           //bug
//        int solanlap=1000;
//        for(int i=0;i<solanlap;i++){
//            diTruyen.danhgia(thuatToanDiTruyen.phucvus);
//            diTruyen.Print(sang);
//            diTruyen.chonloc();
//            diTruyen.laighep();
//            diTruyen.dotbien(nhanviendv);
//            if(diTruyen.best <=62){
//                break;
//            }
//
//        }
//        System.out.println("ket qua cua buoi sang: ");
//        diTruyen.Xuat(sang);
//            System.out.println("Ma nhan vien");
//           diTruyen.xuatMaNhanVien(thuatToanDiTruyen.manvpv);
//        // buoi chieu
//        diTruyen.khoitao(nhanviendv);
//        int chieu[][]= new int[2][32];
//        for(int i=0;i<solanlap;i++){
//            diTruyen.danhgia(thuatToanDiTruyen.phucvuc);
//            diTruyen.Print(chieu);
//            diTruyen.chonloc();
//            diTruyen.laighep();
//            diTruyen.dotbien(nhanviendv);
//            if(diTruyen.best <=62){
//                break;
//            }
//
//        }
//        System.out.println("ket qua cua buoi chieu: ");
//        diTruyen.Xuat(chieu);
//        // buoi toi
//        diTruyen.khoitao(nhanviendv);
//        int toi[][]= new int[2][32];
//        for(int i=0;i<solanlap;i++){
//            diTruyen.danhgia(thuatToanDiTruyen.phucvut);
//            diTruyen.Print(toi);
//            diTruyen.chonloc();
//            diTruyen.laighep();
//            diTruyen.dotbien(nhanviendv);
//            if(diTruyen.best <=62){
//                break;
//            }
//
//        }
//        System.out.println("ket qua cua buoi toi: ");
//        diTruyen.Xuat(toi);
//        try {
//            luulichlam(sang, chieu, toi, diTruyen.manvpv);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//     }
//         public void luulichlam(int s[][],int c[][],int t[][],String manv[]) throws Exception {
//        
//        
//        String sql = "INSERT INTO `nhanvien_lich`( `id_nhanvien`, `id_lich`)"+ "VALUES (?,?)";
//        
//        try(
//                Connection con = DatabaseHelper.openConnection();//mo ket noi
//                PreparedStatement pre= con.prepareStatement(sql);
//                ){
//                       // for(int i=0;i<(n/3);i++){
//                for(int i=0;i<(n/3);i++){
//                for(int j=0;j<=1;j++)
//                   try {
//            pre.setString(1,manv[s[j][i]]);
//            pre.setInt(2, n+i*3+1);
//            pre.executeUpdate();
//            pre.setString(1,manv[c[j][i]]);
//            pre.setInt(2, n+i*3+2);
//            pre.executeUpdate();
//            pre.setString(1,manv[t[j][i]]);
//            pre.setInt(2, n+i*3+3);
//            pre.executeUpdate();
//            
//                }catch (SQLException ex) {
//            ex.printStackTrace();
//            
//        }
//        }
//
//        }
//}
//    public static void main(String[] args) throws Exception {
//        new thuatToanDiTruyen().xeplichphucvu();
//    }
//}
