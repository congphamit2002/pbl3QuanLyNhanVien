/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pbl3quanlynhanvien.view;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;
import pbl3quanlynhanvien.BLL.QuanLyLichBanVaLichMotNhanVienBLL;
import pbl3quanlynhanvien.DAL.MessageDialogHelper;

/**
 *
 * @author PC
 */
public class DangKyLichBanPanel extends javax.swing.JPanel {

    /**
     * Creates new form DangKyLichBanPanel
     */
    private DefaultTableModel model;
    
    public DangKyLichBanPanel() {
        initComponents();
        initLabel();
        initTable();
    }
    
    public void initLabel()
            {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");  
                    LocalDateTime now = LocalDateTime.now();  
                    String month = dtf.format(now);
                    
                    lblLichBan.setText("Lịch bận tháng " + month);
            }
    
    public void initTable(){
        model = new DefaultTableModel();
        
        model.setColumnIdentifiers(new Object[] {"Ngày Bận", "Buổi Bận"});
        QuanLyLichBanVaLichMotNhanVienBLL.getInstance().renderLichBanToTable(model);
        tblLichBan.setModel(model);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dateChoose = new com.toedter.calendar.JDateChooser();
        cbxBuoi = new javax.swing.JComboBox<>();
        btnConfirm = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLichBan = new javax.swing.JTable();
        lblLichBan = new javax.swing.JLabel();

        jLabel1.setBackground(new java.awt.Color(187, 187, 187));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Đăng Ký Lịch Bận");

        jLabel2.setText("Đăng ký bận");

        jLabel3.setText("Ngày Bận");

        jLabel4.setText("Buổi");

        cbxBuoi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sang", "Chieu", "Toi" }));

        btnConfirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbl3quanlynhanvien/icons/diemdanh.png"))); // NOI18N
        btnConfirm.setText("Xác Nhận");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbl3quanlynhanvien/icons/Actions-edit-delete-icon-16.png"))); // NOI18N
        btnCancel.setText("Hủy");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        tblLichBan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblLichBan);

        lblLichBan.setText("jLabel5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(78, 78, 78)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3))
                            .addComponent(btnConfirm))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCancel)
                            .addComponent(jLabel4)
                            .addComponent(cbxBuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(117, 195, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addComponent(jScrollPane1))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLichBan)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(dateChoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxBuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirm)
                    .addComponent(btnCancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLichBan)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        // TODO add your handling code here:
        
                    SimpleDateFormat dayFormat =new SimpleDateFormat("yyyy-MM-dd");
                    String day = dayFormat.format(dateChoose.getDate());
                    String buoi = cbxBuoi.getSelectedItem().toString();
                    System.out.println("day " + day);
                    
                    if(!QuanLyLichBanVaLichMotNhanVienBLL.getInstance().themLichBan(day, buoi))
                    {
                        MessageDialogHelper.showErrorDialog(this, "Không thể thêm lịch bận do lỗi", "Lỗi");
                    }else {
                        QuanLyLichBanVaLichMotNhanVienBLL.getInstance().renderLichBanToTable(model);
                    }
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
        String day = dayFormat.format(dateChoose.getDate());
        String buoi = cbxBuoi.getSelectedItem().toString();
        System.out.println("day " + day);
        
        if(day == null)
        {
             MessageDialogHelper.showErrorDialog(this, "Ngày bận để xóa không được để trống", "Lỗi");
             return;
        }
        if (!QuanLyLichBanVaLichMotNhanVienBLL.getInstance().xoaLichBan(day, buoi)) {
            MessageDialogHelper.showErrorDialog(this, "Không thể xóa lịch bận do lỗi", "Lỗi");
        } else {
            QuanLyLichBanVaLichMotNhanVienBLL.getInstance().renderLichBanToTable(model);
        }
    }//GEN-LAST:event_btnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JComboBox<String> cbxBuoi;
    private com.toedter.calendar.JDateChooser dateChoose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblLichBan;
    private javax.swing.JTable tblLichBan;
    // End of variables declaration//GEN-END:variables
}
