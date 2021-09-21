package org.acm.store.model.user.admin;

import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public interface AdminDAO {

    List<Admin> getAllAdmins();

    Admin getAdmin(long id);

    Admin getAdminEmailPassword(String email, String password);

    Admin getAdminEmailPhoneNumber(String email, String phoneNumber);

    long addAdmin(Admin admin);

    void updateAdmin(Admin admin);

    void deleteAdmin(long id);
}
