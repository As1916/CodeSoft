import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // 
    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false; 
    }

   
    public void deposit(double amount) {
        balance += amount;
    }

   
    public double getBalance() {
        return balance;
    }
}

public class ATMSystem {

    private static BankAccount account;
    private static JFrame frame;
    private static JPanel cardPanel;
    private static JTextArea displayArea;

    public static void main(String[] args) {
        
        account = new BankAccount(1000.00);  

        // Create the main frame for the ATM system
        frame = new JFrame("ATM System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

      
        cardPanel = new JPanel(new CardLayout());
        
       
        cardPanel.add(createLoginScreen(), "LoginScreen");

       
        frame.setContentPane(cardPanel);
        frame.setVisible(true);
    }

    
    private static JPanel createLoginScreen() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel pinLabel = new JLabel("Enter PIN:");
        JPasswordField pinField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enteredPin = new String(pinField.getPassword());
                if (enteredPin.equals("1234")) { // Hardcoded PIN for simplicity
                    // Show main menu after successful login
                    CardLayout cl = (CardLayout) (cardPanel.getLayout());
                    cl.show(cardPanel, "MainMenu");
                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect PIN. Try again.");
                }
            }
        });

        loginPanel.add(pinLabel);
        loginPanel.add(pinField);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        return loginPanel;
    }

    
    private static JPanel createMainMenu() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton checkBalanceButton = new JButton("Check Balance");
        JButton withdrawButton = new JButton("Withdraw Money");
        JButton depositButton = new JButton("Deposit Money");
        JButton exitButton = new JButton("Exit");

        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Your balance is: $" + account.getBalance());
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog(frame, "Enter withdrawal amount:");
                try {
                    double amount = Double.parseDouble(amountStr);
                    if (account.withdraw(amount)) {
                        JOptionPane.showMessageDialog(frame, "You have withdrawn: $" + amount);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Insufficient funds.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid amount.");
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog(frame, "Enter deposit amount:");
                try {
                    double amount = Double.parseDouble(amountStr);
                    account.deposit(amount);
                    JOptionPane.showMessageDialog(frame, "You have deposited: $" + amount);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid amount.");
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuPanel.add(checkBalanceButton);
        menuPanel.add(withdrawButton);
        menuPanel.add(depositButton);
        menuPanel.add(exitButton);

        return menuPanel;
    }

    
    private static void showMainMenu() {
        JPanel mainMenuPanel = createMainMenu();
        cardPanel.add(mainMenuPanel, "MainMenu");
    }
}
