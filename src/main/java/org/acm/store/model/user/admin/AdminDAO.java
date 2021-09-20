package org.acm.store.model.user.admin;

import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public interface AdminDAO {

    public List<Admin> getAllAdmins();

    public Admin getAdmin(long id);

    public Admin getAdminEmailPassword(String email, String password);

    public Admin getAdminEmailPhoneNumber(String email, String phoneNumber);

    public long addAdmin(Admin admin);

    public void updateAdmin(Admin admin);

    public void deleteAdmin(long id);
}
