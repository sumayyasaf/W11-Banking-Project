public abstract class Account  {
    private String accountHolder;
    private double balance;
    private static int ACCOUNTID_COUNTER = 10001;
    private final int accountID;
    public static double WITHDRAW_LIMIT = 100000;  // a class-wide constant
    private double withdrawLimit;

    public Account(String accountHolder, double balance,double withdrawLimit) {
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.accountID = ACCOUNTID_COUNTER++;
        this.withdrawLimit = withdrawLimit;

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

}


