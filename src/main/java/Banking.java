import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Banking {
    public static ArrayList<User> users = new ArrayList<>();
    static User currentUser = null;
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        while (true) {
            System.out.println("Welcome to the Banking Application.");
            System.out.println("1. Signup ");
            System.out.println("2. Login ");
            System.out.println("3. Exit ");
            System.out.println("Choose any option from above: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    signup();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static void signup() {
        System.out.println("--Signup--");
        System.out.println("Enter Username (Lowercase and unique): ");
        String username = scanner.nextLine();
        while (!username.equals(username.toLowerCase()) || username.isEmpty()) {
            System.out.println("Please enter Username in Lowercase.");
            username = scanner.nextLine();
        }


        //Validate unique username:  Must be unique and lowercase.
        while (userExists(username)) {
            System.out.println("Username is already taken. Try again.");
            username = scanner.nextLine();
        }
        //Password: Minimum of 6 characters.
        System.out.println("Enter password (minimum 6 characters): ");
        String password = scanner.nextLine().trim();
        while (password.length() < 6) {     //Password must be at least 6 characters.
            System.out.println("Password must be at least 6 characters long. Try again.");
            password = scanner.nextLine().trim();
        }
        System.out.println("Password accepted.");

        System.out.println("Enter First Name: ");  // //First Name
        String firstName = scanner.nextLine();
        //Initial Deposit Amount
        System.out.println("Enter Initial Deposit Amount: ");
        String depositAmountStr = scanner.nextLine();
        double depositAmount;
        while (isDouble(depositAmountStr)) {
            System.out.println("Please enter a valid number.");
            depositAmountStr = scanner.nextLine();
        }
        depositAmount = Double.parseDouble(depositAmountStr);

        //Withdraw Limit
        System.out.println("Enter withdraw Limit :");
        String withdrawAmountStr = scanner.nextLine();
        double withdrawLimit;
        while (true) {
            if (isDouble(withdrawAmountStr)) {
                System.out.println("Please enter a valid number");
            } else if (Double.parseDouble(withdrawAmountStr) > Account.withdrawLimit_global) {
                System.out.println("Withdraw limit should be less than :" + Account.withdrawLimit_global);
            } else {
                withdrawLimit = Double.parseDouble(withdrawAmountStr);
                break;
            }
            withdrawAmountStr = scanner.nextLine();
        }
        System.out.println("which account would you like to have - a Checking or a Savings?");
        String c = scanner.nextLine();
        Account userAccount;
        while (true) {
            if (Objects.equals(c, "Checking")) {
                userAccount = new CheckingAccount(username, depositAmount, withdrawLimit);
                break;
            } else if (Objects.equals(c, "Savings")) {
                userAccount = new SavingsAccount(username, depositAmount, withdrawLimit);
                break;
            } else {
                System.out.println("please enter valid account type.");
            }
        }
        User user1 = new User(username, password, firstName, userAccount);

        users.add(user1);
        System.out.println("User registered successfully " + user1.getUsername());

    }

    private static boolean isDouble(String depositAmountStr) {
        try {
            Double.parseDouble(depositAmountStr);
            return false;

        } catch (Exception e) {
            return true;
        }
    }

    public static boolean userExists(String user) {
        return users.stream().anyMatch(u -> u.getUsername().equals(user)); //The lambda expression u -> u.getUsername().equals(user) specifies the condition being checked for each user in the stream.
    }

    public static void login() {
        System.out.println("--Login--");
        System.out.println("Enter Username: ");
        String username = scanner.nextLine().toLowerCase(); //Username must be lowercase.

        System.out.println("Enter Password: ");
        String password = scanner.nextLine();
        User user = null;
        for (User oneUser : users) {
            if (oneUser.getUsername().equals(username)) {
                user = oneUser;
            }
        }
        int loginAttempts = 3;
        boolean loggedIn = false;

        //Validate username and password
        if (user == null) {
            System.out.println("Error: Username not found."); //Usernames must be unique.
        } else if (!user.getPassword().equals(password)) {
            while (loginAttempts > 0) {
                if (!user.getPassword().equals(password)) {
                    loginAttempts--;
                    System.out.println("Error : Incorrect password. Attempts remaining: " + loginAttempts);
                    password = scanner.nextLine();
                } else {
                    loggedIn = true;
                    break;
                }
            }

        } else {
            loggedIn = true;
        }
        if (loggedIn) {
            currentUser = user;
            loggedInMenu();
        }
    }

    public static void loggedInMenu() {
        while (true) {
            System.out.println("--Logged In User Menu--");
            System.out.println("1. Show Balance");
            System.out.println("2. Deposit");
            System.out.println("3.Withdraw");
            System.out.println("4.Reset Password");
            System.out.println("5. Account Transfer");
            System.out.println("6.Logout");
            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    resetPassword();
                    break;
                case 5:
                    accountTransfer();
                    break;
                case 6:
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

    }

    public static void showBalance() {
        System.out.println("Current Balance : " + currentUser.getUserAccount().getBalance());
    }

    //Allow users to deposit money up to their specified deposit limit.
    public static void deposit() {
        System.out.println("Enter amount to deposit: ");
        double depositAmount = scanner.nextDouble();
        if (depositAmount < 0) {
            System.out.println("Amount cannot be negative");
        } else if (depositAmount > currentUser.getUserAccount().getDepositLimit()) {
            System.out.println("Amount cannot exceed deposit limit.");

        } else {
            currentUser.getUserAccount().setBalance(currentUser.getUserAccount().getBalance() + depositAmount);
            System.out.println("Deposited " + depositAmount + ". New Balance: " + currentUser.getUserAccount().getBalance());
        }
    }

    private static void withdraw() {
        System.out.println("Enter amount to withdraw: ");
        double withdrawAmount = scanner.nextDouble();
        if (withdrawAmount < 0) {
            System.out.println("Amount cannot be negative.");
        } else if (withdrawAmount > currentUser.getUserAccount().getWithdrawLimit()) {
            System.out.println("Amount cannot exceed withdraw limit.");
        } else {
            currentUser.getUserAccount().setBalance(currentUser.getUserAccount().getBalance() - withdrawAmount);
            System.out.println("Withdrew : " + withdrawAmount + ". New Balance :" + currentUser.getUserAccount().getBalance());
        }
    }

    //Allow users to reset their password by verifying their original password.
    public static void resetPassword() {
        System.out.println("Enter your current password: ");
        String oldPassword = scanner.nextLine().trim();
        if (oldPassword.equals(currentUser.getPassword())) {
            System.out.println("Enter new Password: ");
            String newPassword = scanner.nextLine().trim();

            if (newPassword.length() >= 6) {
                currentUser.setPassword(newPassword);
                System.out.println("Password reset successfully.");
            } else {
                System.out.println("Password must be at least 6 characters.");
            }
        } else {
            System.out.println("Incorrect current password.");
        }
    }

    //Allow transfers between accounts using account IDs.
    private static void accountTransfer() {
        System.out.println("Please enter account id for recipient : ");
        int accountID = scanner.nextInt();
        if(users.stream().noneMatch(u->u.getUserAccount().getAccountID() == accountID)){ // Check if recipient exists
            System.out.println("no such recipient exits");
        } else {
            System.out.println("please enter amount to be transferred: ");
            double transferAmount = scanner.nextDouble();
            if(transferAmount> currentUser.getUserAccount().getBalance()){ // Check if the current user has sufficient balance
                System.out.println("you have no sufficient balance ");
            } else {
                User recipient = null;
                for(User user : users){ //  // Find recipient in the users list
                    if(user.getUserAccount().getAccountID() == accountID){
                        recipient = user;
                        break;
                    }
                } // Perform the transfer if recipient is found
                System.out.println("transferring amount to user" + recipient.getUsername()+ "with account id" + recipient.getUserAccount().getAccountID());
                currentUser.getUserAccount().setBalance(currentUser.getUserAccount().getBalance()-transferAmount);
                recipient.getUserAccount().setBalance(recipient.getUserAccount().getBalance()+transferAmount);
            }

        }
    }
}



