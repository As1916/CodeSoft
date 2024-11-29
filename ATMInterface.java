import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMInterface {
    private JFrame frame;
    private JTextField pinField, amountField;
    private JLabel balanceLabel;
    private BankAccount account;
    private boolean isAuthenticated = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                ATMInterface window = new ATMInterface();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ATMInterface() {
        account = new BankAccount(1000.00); // Initial balance
        initialize();
    }

    private void initialize() {
        // Main Frame Setup
        frame = new JFrame("ATM Interface");
        frame.setBounds(100, 100, 400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Login Section (PIN Authentication)
        JLabel pinLabel = new JLabel("Enter PIN:");
        pinLabel.setBounds(30, 30, 100, 25);
        frame.getContentPane().add(pinLabel);

        pinField = new JTextField();
        pinField.setBounds(140, 30, 100, 25);
        frame.getContentPane().add(pinField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(140, 60, 100, 25);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });
        frame.getContentPane().add(loginButton);

        // Main Menu Section (after login)
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setBounds(30, 100, 330, 180);
        frame.getContentPane().add(mainMenuPanel);
        mainMenuPanel.setLayout(new GridLayout(5, 1));

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });
        mainMenuPanel.add(checkBalanceButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDepositForm();
            }
        });
        mainMenuPanel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showWithdrawForm();
            }
        });
        mainMenuPanel.add(withdrawButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        mainMenuPanel.add(logoutButton);

        // Deposit and Withdraw Input Fields (Initially Hidden)
        amountField = new JTextField();
        amountField.setBounds(140, 60, 100, 25);
        amountField.setVisible(false);
        frame.getContentPane().add(amountField);

        balanceLabel = new JLabel("Balance: $0.00");
        balanceLabel.setBounds(30, 70, 200, 25);
        frame.getContentPane().add(balanceLabel);
    }

    private void authenticateUser() {
        String pin = pinField.getText();
        if ("1234".equals(pin)) {
            isAuthenticated = true;
            pinField.setVisible(false);
            frame.getContentPane().removeAll();
            initialize();
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid PIN. Try again.");
        }
    }

    private void checkBalance() {
        JOptionPane.showMessageDialog(frame, "Your current balance is: $" + account.getBalance());
    }

    private void showDepositForm() {
        String depositAmount = JOptionPane.showInputDialog(frame, "Enter deposit amount:");
        try {
            double amount = Double.parseDouble(depositAmount);
            if (amount > 0) {
                account.deposit(amount);
                JOptionPane.showMessageDialog(frame, "Deposited: $" + amount);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid deposit amount.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid amount.");
        }
    }

    private void showWithdrawForm() {
        String withdrawAmount = JOptionPane.showInputDialog(frame, "Enter withdrawal amount:");
        try {
            double amount = Double.parseDouble(withdrawAmount);
            if (account.withdraw(amount)) {
                JOptionPane.showMessageDialog(frame, "Withdrew: $" + amount);
            } else {
                JOptionPane.showMessageDialog(frame, "Insufficient balance.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid amount.");
        }
    }

    private void logout() {
        isAuthenticated = false;
        frame.getContentPane().removeAll();
        initialize();
    }
}
