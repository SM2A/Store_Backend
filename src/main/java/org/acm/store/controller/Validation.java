package org.acm.store.controller;

import org.acm.store.model.DataBase;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */
public class Validation {

    public static boolean isTaken(String email, String phoneNumber) {
        return DataBase.getInstance().isTaken(email, phoneNumber);
    }
}
