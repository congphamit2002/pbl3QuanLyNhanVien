/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pbl3quanlynhanvien.view;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pbl3quanlynhanvien.BLL.QuanLyXepLichBLL;
import pbl3quanlynhanvien.DAL.MessageDialogHelper;
import pbl3quanlynhanvien.DAL.QuanLyXepLichDAO;
import pbl3quanlynhanvien.DTO.ShareData;

/**
 *
 * @author PC
 */
public class LichLamViecNhanVienPanel extends javax.swing.JPanel {

    /**
     * Creates new form LichLamViecNhanVienPanel
     */
    public LichLamViecNhanVienPanel() {
        initComponents();
        showLich();
        ShareData data = new ShareData();
        if(ShareData.getNguoiDangNhap().getRole().equals("User"))
            btnXepLich.setEnabled(false);
        else 
            btnXepLich.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnXepLich = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLichPhucVu = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLichPhaChe = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 0));
        jLabel1.setText("Lịch làm việc của nhân viên");

        btnXepLich.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbl3quanlynhanvien/icons/xeplichlamviec.png"))); // NOI18N
        btnXepLich.setText("Xếp lịch làm việc");
        btnXepLich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXepLichActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Chức vụ: Phục vụ");

        tblLichPhucVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblLichPhucVu);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Chức vụ: Pha chế");

        tblLichPhaChe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblLichPhaChe);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXepLich, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1)
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnXepLich, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    private boolean check = false;
    private void btnXepLichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXepLichActionPerformed
        // TODO add your handling code here:
        
            QuanLyXepLichBLL.getInstance().insertDayOfCurrentMonth();
            if(QuanLyXepLichBLL.getInstance().checkXepLich())
            {
                int choice = MessageDialogHelper.showConfirmDialog(this, "Bạn đã thực hiện xếp lịch rồi, có muốn xếp lịch lại không?"
                        , "Thông Báo");
                if(choice == JOptionPane.YES_OPTION){
                try {
                    QuanLyXepLichBLL.getInstance().deleteNhanVienLich();
                    QuanLyXepLichBLL.getInstance().xeplichphucvu();
                QuanLyXepLichBLL.getInstance().xepLichPhaChe();
                showLich();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                } else showLich();
            }
            else {
                try {
                    QuanLyXepLichBLL.getInstance().xeplichphucvu();
                QuanLyXepLichBLL.getInstance().xepLichPhaChe();
                showLich();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }//GEN-LAST:event_btnXepLichActionPerformed

    public void showLich(){
        DefaultTableModel modelpv = new DefaultTableModel();
        DefaultTableModel modelpc = new DefaultTableModel();   
        
        QuanLyXepLichBLL.getInstance().RenderToTable(modelpv, modelpc);
        
        tblLichPhucVu.setModel(modelpv);
        tblLichPhaChe.setModel(modelpc);
        
        tblLichPhucVu.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblLichPhaChe.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXepLich;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tblLichPhaChe;
    private javax.swing.JTable tblLichPhucVu;
    // End of variables declaration//GEN-END:variables
}
