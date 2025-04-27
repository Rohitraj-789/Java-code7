import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Deposite extends JFrame {
    public Deposite() {

        Username username = new Username();   // Assuming this class exists and holds the username
        Details details = new Details();        // Assuming this class exists and can provide account details

        // Set up the frame
        setTitle("Deposit");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a panel for the input field
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel amountLabel = new JLabel("Enter amount: ");
        JTextField amountField = new JTextField(10);
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);

        // Create a panel for the button
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton closeButton = new JButton("Deposite");

        // Add action listener to the button
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the input is empty BEFORE trying to parse it
                if(amountField.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(Deposite.this, "Amount cannot be empty!");
                    return;
                }

                try {
                    // Parse the amount from the text field
                    double amount = Double.parseDouble(amountField.getText().trim());

                    // Retrieve the account number using the actual username value
                    String account  = details.getAccountNumber(username.getUsername());
                    BankOperations bankop = new BankOperations();

                    // Attempt deposit operation
                    if(bankop.deposit(account, amount)){
                        JOptionPane.showMessageDialog(Deposite.this, "Deposit Successful!");
                    } else {
                        JOptionPane.showMessageDialog(Deposite.this, "Deposit failed!");
                    }

                    dispose(); // Close the window after operation

                } catch (NumberFormatException ex) {
                    // This catches cases where the input is not a valid double
                    JOptionPane.showMessageDialog(Deposite.this, "Invalid Amount!");
                }
            }
        });
        buttonPanel.add(closeButton);

        // Add panels to the frame
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Centers the window on the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Deposite());
    }
}