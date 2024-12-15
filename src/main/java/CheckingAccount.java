public class CheckingAccount extends Account{
    public static int OVERDRAFT_LIMIT = 2; //static variables
    private int overDraftCount;
    public static int PENALTY = 50;

    public CheckingAccount(String accountHolder, double balance, double withdrawLimit) {
        super(accountHolder, balance, withdrawLimit);
        this.overDraftCount = 0;
    }

    public int getOverDraftCount() {
        return overDraftCount;
    }

    @Override
    public boolean isOverdraftsAllowed() {

        return true;
    }
    public void incrementOverDraftCount(){
        this.overDraftCount++;
    }
    public void resetOverDraftCount(){
        this.overDraftCount = 0;
    }
}

