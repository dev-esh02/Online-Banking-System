package proj;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Account extends Thread implements AccountInterface {
    String name, password, accountType;
    long balance, accountNumber;
    Scanner sc = new Scanner(System.in);
    long totalDep = 0, totalWdr = 0, totalDeb = 0, totalCre = 0;
    ArrayList<String> statement = new ArrayList<>();
    ArrayList<String> fileContent = new ArrayList<>();
    File f;
    FileWriter fw;
    BufferedWriter br;

    Account(String name, String password, String accountType, long accountNumber) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.balance = 0;
        this.accountNumber = accountNumber;
        String path = "C:\\Users\\deves\\OneDrive\\Desktop\\Second Year Assignments\\Files\\" + name + "_file.txt";
        f = new File(path);
        try {
            f.createNewFile();
            fw = new FileWriter(f);
            br = new BufferedWriter(fw);
            try {
                String line = "Name          : " + this.name + "\nAccount No.   : " + this.accountNumber + "\n"
                        + "Account Type  : " + this.accountType + "\n" + "Current Amount: " + this.balance
                        + "\n\n";
                br.write(line);
                br.close();
                fileContent.add(line);
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void displayDetails() {
        System.out.println("Name          : " + this.name);
        System.out.println("Account No.   : " + this.accountNumber);
        System.out.println("Account Type  : " + this.accountType);
        System.out.println("Current Amount: Rs." + this.balance);
    }

    public void deposit(long n) {
        this.balance += n;
        System.out.println("Rs." + n + " has been deposited in your A/C.");
        totalDep += n;
        Date dr = new Date();
        DateFormat df = new SimpleDateFormat("HH:mm:ss dd/MM/yy");
        String s = "Rs." + n + " Deposited on ." + df.format(dr);
        statement.add(s);
        fileContent.add(s);
        fileUpdate();
        System.out.println("Enter 1 to check Balance.\n      0 to return to Menu.");
        int choice = sc.nextInt();
        if (choice == 1) {
            checkBalance();
        }
    }

    public void withdraw(long n) {
        if (n > this.balance) {
            System.out.println("Insufficient Balance!");
        } else {
            this.balance -= n;
            totalWdr += n;
            System.out.println("Rs." + n + " has been withdrawn from your account.");
            Date dr = new Date();
            DateFormat df = new SimpleDateFormat("HH:mm:ss dd/MM/yy");
            String s = "Rs." + n + " Withdrawn on ." + df.format(dr);
            statement.add(s);
            fileContent.add(s);
            fileUpdate();
        }
        System.out.println("Enter 1 to check Balance.\n      0 to return to Menu.");
        int choice = sc.nextInt();
        if (choice == 1) {
            checkBalance();
        }
    }

    public void debit(long amt, Account ac) {
        if (amt > this.balance) {
            System.out.println("Insufficient Balance! Transaction Unsuccessful!");
        } else {
            balance -= amt;
            System.out.println("Rs." + amt + " has been debited form your account to " + ac.name + ".");
            totalDeb += amt;
            Date dr = new Date();
            DateFormat df = new SimpleDateFormat("HH:mm:ss dd/MM/yy");
            String s = "Rs." + amt + " debited on ." + df.format(dr) + " by " + ac.name;
            statement.add(s);
            fileContent.add(s);
            fileUpdate();
            ac.credit(amt, this.name);
        }
        System.out.println("Enter 1 to check Balance.\n      0 to return to Menu.");
        int choice = sc.nextInt();
        if (choice == 1) {
            checkBalance();
        }
    }

    public void credit(long amt, String nm) {
        this.balance += amt;
        System.out.println("Rs." + amt + " has been credited to your account from " + nm + ".");
        totalCre += amt;
        Date dr = new Date();
        DateFormat df = new SimpleDateFormat("HH:mm:ss dd/MM/yy");
        String s = "Rs." + amt + " Credited on ." + df.format(dr) + " by " + nm;
        statement.add(s);
        fileContent.add(s);
        fileUpdate();
        System.out.println("Enter 1 to check Balance.\n      0 to return to Menu.");
        int choice = sc.nextInt();
        if (choice == 1) {
            checkBalance();
        }
    }

    public void printStatement() {
        try {
            Scanner fsc = new Scanner(f);
            while (fsc.hasNextLine()) {
                System.out.println(fsc.nextLine());
            }
            fsc.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void checkBalance() {
        System.out.println("Your Current Balance is Rs." + this.balance);
    }

    public void selfDestruct() {
        try {
            f.delete();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Deleting Account!");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("ACCOUNT DELETED.");
    }

    public void fileUpdate() {
        String line = "Name          : " + this.name + "\nAccount No.   : " + this.accountNumber + "\n"
                + "Account Type  : " + this.accountType + "\n" + "Current Amount: " + this.balance
                + "\n\n";
        fileContent.set(0, line);
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter br = new BufferedWriter(fw);
            for (int i = 0; i < fileContent.size(); i++) {
                br.write(fileContent.get(i) + "\n");
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Update unsuccessfull.");
        }
    }
}