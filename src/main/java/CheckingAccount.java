public class CheckingAccount extends Account{
    public CheckingAccount(String accountHolder, double balance, double withdrawLimit) {
        super(accountHolder, balance,withdrawLimit);
    }

    @Override
    public boolean isOverdraftsAllowed() {

        return true;
    }
}
