/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbl3quanlynhanvien.BLL;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import pbl3quanlynhanvien.DAL.ChucVuDAO;
import pbl3quanlynhanvien.DAL.PersonDAO;
import pbl3quanlynhanvien.DAL.UserDAO;
import pbl3quanlynhanvien.DTO.CBBChucVuItem;
import pbl3quanlynhanvien.DTO.ChucVu;
import pbl3quanlynhanvien.DTO.Person;
import pbl3quanlynhanvien.DTO.Users;

/**
 *
 * @author PC
 */
public class QLNVBLL {
    private static QLNVBLL Instance;

    public static QLNVBLL getInstance() {
        if(Instance == null)
        {
            Instance = new QLNVBLL();
        }
        return Instance;
    }

    private static void setInstance(QLNVBLL Instance) {
    }
    
    public List<CBBChucVuItem> getAllCBBChucVu()
    {
        List<CBBChucVuItem> list = new ArrayList<CBBChucVuItem>();
        
        for(ChucVu cv : ChucVuDAO.getInstance().getAllChucVu())
        {
            CBBChucVuItem item = new CBBChucVuItem();
            item.setId_chucvu(cv.getId_chucvu());
            item.setTenchucvu(cv.getTenchucvu());
            item.setLuongcoban(cv.getLuongcoban());
            list.add(item);
        }
        return list;
    }
    

    
    public Person getPersonByID(String id)
    {
        Person per = null;
        for (Person person : PersonDAO.getInstance().getAllPerson()) {
            if(person.getId_nhanvien().equals(id))
            {
                per = new Person();
                per = person;
                break;
            }
        }
        
        return per;
    }
    
    
    public boolean insertPerson(Person per) throws Exception
    {
        return PersonDAO.getInstance().insertPerson(per);
    }
    
    public boolean updatePerson(Person per) throws Exception
    {
        return PersonDAO.getInstance().updatePerson(per);
    }
    
    public boolean deletePerson(String id)
    {
        return PersonDAO.getInstance().deletePerson(id);
    }
    
    public void RenderToTable(DefaultTableModel model)
    {
        model.setRowCount(0);
        for (Person per : PersonDAO.getInstance().getAllPerson()) {
            model.addRow(new Object[]{per.getId_nhanvien(), per.getHoten(), per.isGioitinh() == true ? "Nam" : "Nu", per.getNgaysinh(), per.getSdt(), per.getDiachi()
            , per.getCccd(), per.getEmail(), per.getUsername()});
        }
        model.fireTableDataChanged();
    }
    
    public List<String> getAllRole()
    {
        List<String> data = new ArrayList<>();
        data = UserDAO.getInstance().getAllRole();
        return data;
    }
    
    public boolean updatePassword(Users user)
    {
        return UserDAO.getInstance().updatePassword(user);
    }
    
}
