package proj;

import java.util.*;

public class Login extends Account {
    Scanner sc = new Scanner(System.in);

    Login(String name, String password, String accountType, long accountNumber) {
        super(name, password, accountType, accountNumber);
    }

    void withdraw() {

        System.out.print("Please Enter the amount you want to withdraw: ");
        long amt = sc.nextLong();
        withdraw(amt);
        System.out.println("Money has been withdrawn form your account.");
    }

    void deposit() {
        System.out.print("Please Enter the amount you want to deposit: ");
        long amt = sc.nextLong();
        deposit(amt);
        // System.out.println("Money has been deposited to your account.");
    }

    void debit(ArrayList<Long> accountsNum, ArrayList<Account> accounts) {
        System.out.print("Please enter Account Number of the A/C you want to deposit cash: ");
        long accountNumber = sc.nextLong();
        if (accountsNum.contains(accountNumber)) {
            Account ac = accounts.get(accountsNum.indexOf(accountNumber));
            System.out.println("Please Enter the amount you want to debit to" + ac.name);
            long amt = sc.nextLong();
            ac.debit(amt, ac);
        } else {
            System.out.println("No such A/C exist!!");
        }
    }
}
