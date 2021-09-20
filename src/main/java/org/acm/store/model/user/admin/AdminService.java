package org.acm.store.model.user.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Service
public class AdminService {

    @Autowired
    AdminDAO adminDAO;

    @Transactional
    public List<Admin> getAllAdmins() {
        return adminDAO.getAllAdmins();
    }

    @Transactional
    public Admin getAdmin(long id) {
        return adminDAO.getAdmin(id);
    }

    @Transactional
    public Admin getAdminEmailPassword(String email, String password) {
        return adminDAO.getAdminEmailPassword(email, password);
    }

    @Transactional
    public Admin getAdminEmailPhoneNumber(String email, String phoneNumber) {
        return adminDAO.getAdminEmailPhoneNumber(email, phoneNumber);
    }

    @Transactional
    public long addAdmin(Admin admin) {
        return adminDAO.addAdmin(admin);
    }

    @Transactional
    public void updateAdmin(Admin admin) {
        adminDAO.updateAdmin(admin);
    }

    @Transactional
    public void deleteAdmin(long id) {
        adminDAO.deleteAdmin(id);
    }
}
