/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pbl3quanlynhanvien.view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pbl3quanlynhanvien.BLL.QuanLyLichBanVaLichMotNhanVienBLL;
import pbl3quanlynhanvien.BLL.QuanLyTienLuong;
import pbl3quanlynhanvien.DAL.ChucVuDAO;
import pbl3quanlynhanvien.DAL.DataValidator;
import pbl3quanlynhanvien.DAL.MessageDialogHelper;
import pbl3quanlynhanvien.DTO.ChucVu;
import pbl3quanlynhanvien.DTO.LuongNhanVien;

/**
 *
 * @author PC
 */
public class TInhLuongPanel extends javax.swing.JPanel {

    /**
     * Creates new form TInhLuongPanel
     */
    DefaultTableModel model;
    private MainForm parent;
    public TInhLuongPanel() {
        initComponents();
        initChucVu();
        initTable();
    }
    
    public void initChucVu(){
        cbxChucVu.removeAllItems();
       List<ChucVu> list = new ArrayList<>();
            list =   ChucVuDAO.getInstance().getAllChucVu();
            
        for (ChucVu chucVu : list) {
            cbxChucVu.addItem(chucVu.getTenchucvu());
        }
    }

    public void initTable(){
        model = new DefaultTableModel();
        
        model.setColumnIdentifiers(new Object[] {"Id nhân viên", "Họ tên", "Lương cơ bản", "Số buổi phải làm", "Số buổi đi làm", "Số buổi nghỉ", "Số giờ làm", "Số phút đi trễ", "Lương"});
        tblLuong.setModel(model);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        cbxChucVu = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtUpdateLuongTheoGio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtLuongTheoGio = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnPayRoll = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLuong = new javax.swing.JTable();

        jLabel4.setText("jLabel4");

        jTextField2.setText("jTextField2");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("Tính lương nhân viên");

        jLabel2.setText("Chức vụ:");

        cbxChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxChucVuActionPerformed(evt);
            }
        });

        jLabel3.setText("Chỉnh sửa lương theo giờ:");

        jLabel5.setText("Lương theo giờ hiện tại:");

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbl3quanlynhanvien/icons/new-32.png"))); // NOI18N
        btnNew.setText("Tạo mới");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbl3quanlynhanvien/icons/save-32.png"))); // NOI18N
        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnPayRoll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbl3quanlynhanvien/icons/payroll-32.png"))); // NOI18N
        btnPayRoll.setText("Tính lương");
        btnPayRoll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayRollActionPerformed(evt);
            }
        });

        tblLuong.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblLuong);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(btnPayRoll)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(62, 62, 62)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbxChucVu, 0, 256, Short.MAX_VALUE)
                                    .addComponent(txtLuongTheoGio)
                                    .addComponent(txtUpdateLuongTheoGio))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLuongTheoGio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUpdateLuongTheoGio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnPayRoll)
                    .addComponent(btnNew))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPayRollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayRollActionPerformed
        // TODO add your handling code here:
        QuanLyTienLuong.getInstance().renderToTable(model, cbxChucVu.getSelectedItem().toString());
    }//GEN-LAST:event_btnPayRollActionPerformed

    private void cbxChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxChucVuActionPerformed
        // TODO add your handling code here:
        if(cbxChucVu.getSelectedItem() != null)
        {
            String chucVu = cbxChucVu.getSelectedItem().toString();
        txtLuongTheoGio.setText(""+ChucVuDAO.getInstance().getLuongCoBanByTenChucVu(chucVu));
        txtLuongTheoGio.setEnabled(false);
        }
        
    }//GEN-LAST:event_cbxChucVuActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        cbxChucVu.setSelectedIndex(0);
        txtLuongTheoGio.setText("");
        txtUpdateLuongTheoGio.setText("");
        model.setRowCount(0);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        StringBuilder sb = new StringBuilder();
        DataValidator.checkEmty(txtUpdateLuongTheoGio, sb, "Lương theo giờ cập nhật không được để trống");
        if(sb.length() > 0)
        {
            MessageDialogHelper.showErrorDialog(parent, sb.toString(), "ERROR");
            return;
        }
        if(MessageDialogHelper.showConfirmDialog(this, "Bạn có muốn cập nhật lương theo giờ không", "Question") == 
                JOptionPane.NO_OPTION)
        {
                return;
        }
        if(QuanLyTienLuong.getInstance().updateLuongCoBan(Double.parseDouble(txtUpdateLuongTheoGio.getText()), cbxChucVu.getSelectedItem().toString()))
            {
                MessageDialogHelper.showMessageDialog(parent, "Lương theo giờ đã được cập nhật thành công", "Thông báo");
                cbxChucVuActionPerformed(evt);
            }else {
            MessageDialogHelper.showMessageDialog(parent, "Không thể cập nhật lương theo giờ, vui lòng thử lại", "Lỗi");
        }
    }//GEN-LAST:event_btnSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnPayRoll;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbxChucVu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable tblLuong;
    private javax.swing.JTextField txtLuongTheoGio;
    private javax.swing.JTextField txtUpdateLuongTheoGio;
    // End of variables declaration//GEN-END:variables
}