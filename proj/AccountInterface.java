package proj;

public interface AccountInterface {
    void displayDetails();

    void deposit(long n);

    void withdraw(long n);

    void debit(long amt, Account ac);

    void credit(long amt, String nm);

    void printStatement();

    void checkBalance();

    void selfDestruct();

    void fileUpdate();
}
