package proj;

import java.util.*;

// import javax.print.attribute.standard.MediaSize.NA;

public class Bank {
    static ArrayList<Account> accounts;
    static ArrayList<Long> accNo;
    static ArrayList<String> passwordList;
    static int size = 0;

    Bank() {
        accounts = new ArrayList<>();
        accNo = new ArrayList<>();
        passwordList = new ArrayList<>();
    }

    static void addAccount(Account ac) {
        accounts.add(ac);
        accNo.add(ac.accountNumber);
        passwordList.add(ac.password);
    }

    static void removeAccount(Account ac) {
        accounts.remove(ac);
        accNo.remove(ac.accountNumber);
        passwordList.remove(ac.password);
        ac.selfDestruct();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank onlineBankingSystem = new Bank();
        System.out.println("\fWelcome to Online Banking Services.\n\n");
        System.out.println(
                "--------------------------------------------------------------------------------------------------------\n\n");
        while (true) {
            // Designing Menu
            System.out.println("Select your Choices:-");
            System.out.println("Press 1: to Login/Signin A/C.");
            System.out.println("Press 2: to Create an A/C");
            System.out.println("Press 0: To Exit.");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Please Enter your Account Number: ");
                    long account = sc.nextLong();
                    if (!accNo.contains(account)) {
                        System.out.println("Invalid Account Number!\n\n");
                        System.out.println("Press Enter to return to Menu.....");
                        String s = sc.next();
                        System.out.println(s);
                        continue;
                    } else {
                        System.out.println("Enter the Password: ");
                        String password = sc.next();
                        if (password.equals(passwordList.get(accNo.indexOf(account)))) {
                            System.out.println("Successful Login.");
                            Account ac = accounts.get(accNo.indexOf(account));
                            Login log = new Login(ac.name, ac.password, ac.accountType, ac.accountNumber);
                            System.out.println("Press 1: to Deposit the Amount to the A/C.");
                            System.out.println("Press 2: to Withdraw the Amount from the A/C.");
                            System.out.println("Press 3: to Debit the Amount from the A/C.");
                            System.out.println("Press 4: to Remove the A/C.");
                            System.out.println("Press 0: to Log Out.");
                            int n = sc.nextInt();
                            switch (n) {
                                case 1:
                                    log.deposit();
                                    continue;
                                case 2:
                                    log.withdraw();
                                    continue;
                                case 3:
                                    log.debit(accNo, accounts);
                                    continue;
                                case 4:
                                    removeAccount(ac);
                                    System.out.println("Rs." + ac.balance + " has been withdrawn.");
                                    System.out.println("Account has been successfully closed.");
                                    break;

                                default:
                                    System.out.println("Successful Log Out.");
                                    System.out.println("Press enter to continue.");
                                    continue;
                            }
                            continue;
                            // continue;
                        } else {
                            System.out.println("Invalid Password!\n\n");
                            System.out.println("Press Enter to return to Menu.....");
                            String s = sc.next();
                            System.out.println(s);
                            continue;
                        }
                    }
                case 2:
                    System.out.println("\fPlease Enter your first name: ");
                    String fname = sc.next();
                    System.out.println("Please Enter your last name:");
                    String lname = sc.next();
                    String name = fname + " " + lname;
                    System.out.println(
                            "Account Type:\n\tPress 1: For Savings A/C\n\tPress 2: For Checkings A/C\n\tPress 3: For Money Market A/C\n\tIf you Press anything else, It will count as Savings A/C");
                    int n = sc.nextInt();
                    String accType;
                    switch (n) {
                        case 1:
                            accType = "Savings A/C";
                        case 2:
                            accType = "Checkings A/C";
                        case 3:
                            accType = "Money Market A/C";
                        default:
                            accType = "Savings A/C";
                    }
                    String password, password2;
                    System.out.println("Enter the Password: ");
                    password = sc.next();
                    System.out.println("Rewrite the Password:");
                    password2 = sc.next();
                    if (!password2.equals(password)) {
                        System.out.println("Passwords does not Match!!");
                        System.out.println("Press Enter to return to Menu.....");
                        String s = sc.next();
                        System.out.println(s);
                        continue;
                    }
                    long accNum = 20230000 + size + 1;
                    size++;
                    Account ac = new Account(name, password, accType, accNum);
                    accounts.add(ac);
                    accNo.add(accNum);
                    passwordList.add(password);
                    System.out.println("\f***********Congratulations***********");
                    System.out.println("***********ACCOUNT CREATED***********");
                    ac.displayDetails();
                    System.out.println("Press Enter to return to Menu.....");
                    String s = sc.next();
                    System.out.println(s);
                    continue;

                default:

            }
            break;
        }
        sc.close();
    }
}
