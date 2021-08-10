package model;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */
public class Costumer extends User{
    private long credit;

    public Costumer(long ID, String firstName, String lastName, String password,
                    String email, String phoneNumber, String address) {
        super(ID, firstName, lastName, password, email, phoneNumber, address);
        credit = 0;
    }

    public long getCredit() {
        return credit;
    }

    public void addCredit(long amount) {
        credit += amount;
    }

    public void purchase(long totalPrice) {
        if (credit < totalPrice)
            System.out.println("Insufficient Funds!");
        else
            credit -= totalPrice;
    }
}
