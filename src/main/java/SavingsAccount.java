public class SavingsAccount extends Account{
    public SavingsAccount(String accountHolder, double balance, double withdrawLimit) {
        super(accountHolder, balance,withdrawLimit);
    }

    @Override
    public boolean isOverdraftsAllowed() {
        return false;
    }
}
