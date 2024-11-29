import java.util.Scanner;

public class ATM {
    private static double balance = 1000.00; 
    private static int pin = 1608;             
    private static boolean isAuthenticated = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.println("Welcome to the ATM!");
        authenticateUser(scanner);

        while (isAuthenticated) {
            showMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: 
                    checkBalance();
                    break;
                case 2: 
                    depositFunds(scanner);
                    break;
                case 3: 
                    withdrawFunds(scanner);
                    break;
                case 4: 
                    System.out.println("Thank you for using the ATM!");
                    isAuthenticated = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    
    private static void authenticateUser(Scanner scanner) {
        int attempt = 0;
        while (attempt < 3) {
            System.out.print("Enter PIN: ");
            int enteredPin = scanner.nextInt();

            if (enteredPin == pin) {
                isAuthenticated = true;
                System.out.println("Authentication successful!");
                return;
            } else {
                attempt++;
                System.out.println("Incorrect PIN. You have " + (3 - attempt) + " attempts left.");
            }
        }

        System.out.println("Too many incorrect attempts. Exiting...");
        System.exit(0);
    }

    
    private static void showMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Funds");
        System.out.println("3. Withdraw Funds");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

   
    private static void checkBalance() {
        System.out.println("Your current balance is: $" + balance);
    }

    
    private static void depositFunds(Scanner scanner) {
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited $" + amount);
            checkBalance();
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    private static void withdrawFunds(Scanner scanner) {
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();

        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew $" + amount);
            checkBalance();
        } else if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }
}
