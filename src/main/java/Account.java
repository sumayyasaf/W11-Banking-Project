public abstract class Account {
    private String accountHolder;
    private double balance;
    private static int accountID_global = 10001;
    private final int accountID;
    public static double withdrawLimit_global = 100000;  // a class-wide constant
    private double withdrawLimit;

    public Account(String accountHolder, double balance,double withdrawLimit) {
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.accountID = accountID_global++;
        this.withdrawLimit = withdrawLimit;

    }

    public static int getAccountID_global() {
        return accountID_global++;
    }

    public static void setAccountID_global(int accountID_global) {
        Account.accountID_global = accountID_global;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountID() {
        return accountID;
    }

    public abstract boolean isOverdraftsAllowed();


    public double getDepositLimit() {
        return 100000;
    }

    public double getWithdrawLimit() {
        return this.withdrawLimit;
    }

    public void setWithdrawLimit(double withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }
}


