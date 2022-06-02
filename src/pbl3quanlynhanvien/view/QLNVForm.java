/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pbl3quanlynhanvien.view;

import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pbl3quanlynhanvien.BLL.QLNVBLL;
import pbl3quanlynhanvien.DAL.DataValidator;
import pbl3quanlynhanvien.DAL.MessageDialogHelper;
import pbl3quanlynhanvien.DAL.UserDAO;
import pbl3quanlynhanvien.DTO.CBBChucVuItem;
import pbl3quanlynhanvien.DTO.Person;
import pbl3quanlynhanvien.DTO.Users;

/**
 *
 * @author PC
 */
public class QLNVForm extends javax.swing.JPanel {

    /**
     * Creates new form QLNVForm
     */
    private MainForm parent;
    DefaultTableModel tblModel;
    
    public QLNVForm() {
        initComponents();
        initChucVu();
        initTable();
        initRole();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField4 = new javax.swing.JTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblQLNV = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rdbMale = new javax.swing.JRadioButton();
        rdbFemale = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbxChucVu = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtIDNhanvien = new javax.swing.JTextField();
        cbxRole = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtCCCD = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        date_choose = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        jTextField4.setText("jTextField4");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Quản lý nhân viên");

        tblQLNV.setModel(new javax.swing.table.DefaultTableModel(
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
        tblQLNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLNVMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblQLNV);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtAddress.setColumns(20);
        txtAddress.setRows(5);
        jScrollPane1.setViewportView(txtAddress);

        jLabel6.setText("Ngày sinh");

        jLabel7.setText("Giới tính");

        buttonGroup1.add(rdbMale);
        rdbMale.setSelected(true);
        rdbMale.setText("Nam");

        buttonGroup1.add(rdbFemale);
        rdbFemale.setText("Nữ");

        jLabel8.setText("Chức vụ");

        jLabel2.setText("Mã nhân viên: ");

        cbxChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Vai trò");

        jLabel3.setText("Họ và tên:");

        jLabel10.setText("Số điện thoại");

        jLabel4.setText("Số CMND/CCCD");

        jLabel11.setText("Email");

        jLabel12.setText("Tên tài khoản");

        jLabel5.setText("Địa chỉ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName)
                            .addComponent(txtIDNhanvien)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(rdbMale)
                                    .addGap(41, 41, 41)
                                    .addComponent(rdbFemale))
                                .addComponent(cbxChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(127, 127, 127)
                                    .addComponent(jLabel9)
                                    .addGap(18, 18, 18)
                                    .addComponent(cbxRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtSDT)
                                .addComponent(txtEmail))
                            .addComponent(date_choose, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel12))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                                .addComponent(txtUsername))
                            .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIDNhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(date_choose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rdbMale)
                        .addComponent(rdbFemale)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbxRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)))))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(10, 10, 10))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbl3quanlynhanvien/icons/search-32.png"))); // NOI18N
        btnSearch.setText("Tìm kiếm ");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbl3quanlynhanvien/icons/save-32.png"))); // NOI18N
        btnSave.setText("Lưu");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbl3quanlynhanvien/icons/new-32.png"))); // NOI18N
        btnNew.setText("Tạo mới");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbl3quanlynhanvien/icons/update-32.png"))); // NOI18N
        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbl3quanlynhanvien/icons/delete-32.png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnSearch)
                .addGap(30, 30, 30)
                .addComponent(btnSave)
                .addGap(33, 33, 33)
                .addComponent(btnNew)
                .addGap(27, 27, 27)
                .addComponent(btnUpdate)
                .addGap(26, 26, 26)
                .addComponent(btnDelete)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 40, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void initTable()
    {
        try {
            tblModel = new DefaultTableModel();

            tblModel.setColumnIdentifiers(new Object[] {"ID Nhan Vien", "Ho ten", "Gioi Tinh", "Ngay Sinh", "SDT", "Dia Chi", "CCCD/CMND", "Email", "UserName"});
            QLNVBLL.getInstance().RenderToTable(tblModel);
            
            tblQLNV.setModel(tblModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void initChucVu()
    { 
        cbxChucVu.removeAllItems();
        for (CBBChucVuItem item : QLNVBLL.getInstance().getAllCBBChucVu()) {
            cbxChucVu.addItem(item.getTenchucvu());
        }
    }
    
    private void initRole()
    {
        for(String item : QLNVBLL.getInstance().getAllRole())
        {
            cbxRole.addItem(item);
        }
    }
    
    
    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        txtAddress.setText("");
        txtCCCD.setText("");
        txtEmail.setText("");
        txtIDNhanvien.setText("");
        txtName.setText("");
        txtSDT.setText("");
        txtUsername.setText("");
        txtAddress.setText("");
        rdbMale.setSelected(true);
        date_choose.setDate(null);
        txtIDNhanvien.enable(true);
        txtUsername.enable(true);
        
//        int id = ((CBBChucVuItem)cbxChucVu.getSelectedItem()).getId_chucvu();
//        String ten = ((CBBChucVuItem)cbxChucVu.getSelectedItem()).getTenchucvu();
//        double luong = ((CBBChucVuItem)cbxChucVu.getSelectedItem()).getLuongcoban();
//        System.out.println(id);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        
        StringBuilder sb = new StringBuilder();
        DataValidator.checkEmty(txtIDNhanvien, sb, "ID nhân viên không được để trống");
        
        if(sb.length() > 0)
        {
            MessageDialogHelper.showErrorDialog(parent, sb.toString(), "ERROR");
            return;
        }
        
        try {
            Person per = new Person();
            per = QLNVBLL.getInstance().getPersonByID(txtIDNhanvien.getText());
            if(per != null)
            {
                txtIDNhanvien.setText(per.getId_nhanvien());
                txtName.setText(per.getHoten());
                txtUsername.setText(per.getUsername());
                txtAddress.setText(per.getDiachi());
                txtCCCD.setText(per.getCccd());
                date_choose.setDate(per.getNgaysinh());
                rdbMale.setSelected(per.isGioitinh());
                txtSDT.setText(per.getSdt());
                txtEmail.setText(per.getEmail());
                txtIDNhanvien.enable(false);
                txtUsername.enable(false);
            } else {
                MessageDialogHelper.showErrorDialog(parent, "Không có nhân viên này", "ERROR");
            }
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        StringBuilder sb = new StringBuilder();
        DataValidator.checkEmty(txtIDNhanvien, sb, "ID nhân viên không được để trống");
        DataValidator.checkEmty(txtName, sb, "Họ tên không được để trống");
        DataValidator.checkEmty(txtUsername, sb, "Tên tài khoản không được để trống");
        DataValidator.checkEmty(txtAddress, sb, "Địa chỉ không được để trống");
        DataValidator.checkEmty(txtCCCD, sb, "CCCD/CMND không được để trống");
        DataValidator.checkEmty(txtName, sb, "Họ tên không được để trống");
        if(sb.length() > 0)
        {
            MessageDialogHelper.showErrorDialog(parent, sb.toString(), "ERROR");
            return;
        }
        if(MessageDialogHelper.showConfirmDialog(this, "Bạn có muốn thêm nhân viên không", "Question") == 
                JOptionPane.NO_OPTION)
        {
                return;
        }
        try {
            Users user = new Users();
            user.setUsername(txtUsername.getText());
            user.setPassword("123");
            user.setRole(cbxRole.getSelectedItem().toString());
            if(UserDAO.getInstance().findUserByUsername(txtUsername.getText()))
            {
                MessageDialogHelper.showMessageDialog(parent, "Username đã tồn tại, vui lòng thử lại", "ERROR");
            } else {
                UserDAO.getInstance().insertUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(parent, e.getMessage(), "ERROR");
        }
        try {
                  Person per = new Person();
                  per.setId_nhanvien(txtIDNhanvien.getText());
                  per.setHoten(txtName.getText());
                  per.setUsername(txtUsername.getText());
                  per.setDiachi(txtAddress.getText());
                  per.setCccd(txtCCCD.getText());
                  SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
                  String date = sdf.format(date_choose.getDate());
                  java.util.Date myDate = sdf.parse(date); 
                  java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
                  per.setNgaysinh(sqlDate);
                  per.setGioitinh(rdbMale.isSelected() ? true : false);
                  //
                  per.setId_chucvu(1);
                  per.setSdt(txtSDT.getText());
                  per.setEmail(txtEmail.getText());
            if(QLNVBLL.getInstance().insertPerson(per)){
                MessageDialogHelper.showMessageDialog(parent, "Nhân viên đã được thêm thành công", "Thông báo");
                QLNVBLL.getInstance().RenderToTable(tblModel);
            } else {
                MessageDialogHelper.showMessageDialog(parent, "Nhân viên không được thêm do lỗi ", "ERROR");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(parent, e.getMessage(), "ERROR");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        StringBuilder sb = new StringBuilder();
        DataValidator.checkEmty(txtIDNhanvien, sb, "ID nhân viên không được để trống");
        DataValidator.checkEmty(txtName, sb, "Họ tên không được để trống");
        DataValidator.checkEmty(txtUsername, sb, "Tên tài khoản không được để trống");
        DataValidator.checkEmty(txtAddress, sb, "Địa chỉ không được để trống");
        DataValidator.checkEmty(txtCCCD, sb, "CCCD/CMND không được để trống");
        DataValidator.checkEmty(txtName, sb, "Họ tên không được để trống");
        if(sb.length() > 0)
        {
            MessageDialogHelper.showErrorDialog(parent, sb.toString(), "ERROR");
            return;
        }
        if(MessageDialogHelper.showConfirmDialog(this, "Bạn có cập nhật nhân viên này không", "Question") == 
                JOptionPane.NO_OPTION)
        {
                return;
        }
        try {
                  Person per = new Person();
                  per.setId_nhanvien(txtIDNhanvien.getText());
                  per.setHoten(txtName.getText());
                  per.setUsername(txtUsername.getText());
                  per.setDiachi(txtAddress.getText());
                  per.setCccd(txtCCCD.getText());
                  SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
                  String date = sdf.format(date_choose.getDate());
                  java.util.Date myDate = sdf.parse(date); 
                  java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
                  per.setNgaysinh(sqlDate);
                  per.setGioitinh(rdbMale.isSelected() ? true : false);
                  //
                  per.setId_chucvu(1);
                  per.setSdt(txtSDT.getText());
                  per.setEmail(txtEmail.getText());
            if(QLNVBLL.getInstance().updatePerson(per)){
                MessageDialogHelper.showMessageDialog(parent, "Nhân viên đã được cập nhât thành công", "Thông báo");
                QLNVBLL.getInstance().RenderToTable(tblModel);
            } else {
                MessageDialogHelper.showMessageDialog(parent, "Nhân viên không được cập nhât do lỗi ", "ERROR");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(parent, e.getMessage(), "ERROR");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        StringBuilder sb = new StringBuilder();
        DataValidator.checkEmty(txtIDNhanvien, sb, "ID nhân viên không được để trống");
        
        if(sb.length() > 0)
        {
            MessageDialogHelper.showErrorDialog(parent, sb.toString(), "ERROR");
            return;
        }
        
        if(MessageDialogHelper.showConfirmDialog(this, "Bạn có xóa nhân viên này không", "Question") == 
                JOptionPane.NO_OPTION)
            {
                return;
            }
        
        try {
            
            if(QLNVBLL.getInstance().deletePerson(txtIDNhanvien.getText())){
                MessageDialogHelper.showMessageDialog(parent, "Sinh viên đã được xóa thành công", "Thông báo");
                
                QLNVBLL.getInstance().RenderToTable(tblModel);                  
            } else {
                MessageDialogHelper.showMessageDialog(parent, "Sinh viên không được xóa do lỗi ", "ERROR");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            MessageDialogHelper.showErrorDialog(parent, e.getMessage(), "ERROR");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblQLNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLNVMouseClicked
        // TODO add your handling code here:
        int row = tblQLNV.getSelectedRow();
        
        if(row >= 0)
        {
            Person p = QLNVBLL.getInstance().getPersonByID((String) tblModel.getValueAt(row, 0));
            
            if(p != null)
            {
                FillToTable(p);
            }
            
        }
    }//GEN-LAST:event_tblQLNVMouseClicked

    private void FillToTable(Person per)
    {
                txtIDNhanvien.setText(per.getId_nhanvien());
                txtName.setText(per.getHoten());
                txtUsername.setText(per.getUsername());
                txtAddress.setText(per.getDiachi());
                txtCCCD.setText(per.getCccd());
                date_choose.setDate(per.getNgaysinh());
                rdbMale.setSelected(per.isGioitinh());
                txtSDT.setText(per.getSdt());
                txtEmail.setText(per.getEmail());
                txtIDNhanvien.enable(false);
                txtUsername.enable(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxChucVu;
    private javax.swing.JComboBox<String> cbxRole;
    private com.toedter.calendar.JDateChooser date_choose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JRadioButton rdbFemale;
    private javax.swing.JRadioButton rdbMale;
    private javax.swing.JTable tblQLNV;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIDNhanvien;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
