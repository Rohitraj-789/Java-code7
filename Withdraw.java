import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Withdraw extends JFrame {
    public Withdraw() {

        Username username = new Username();   // Assuming this class exists and holds the username
        Details details = new Details();        // Assuming this class exists and can provide account details

        // Set up the frame
        setTitle("Withdraw");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a panel for the input field
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel amountLabel = new JLabel("Enter Amount: ");
        JTextField amountField = new JTextField(10);
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);

        // Create a panel for the button
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton closeButton = new JButton("Withdraw");

        // Add action listener to the button
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the input is empty BEFORE trying to parse it
                if(amountField.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(Withdraw.this, "Amount cannot be empty!");
                    return;
                }

                try {
                    // Parse the amount from the text field
                    double amount = Double.parseDouble(amountField.getText().trim());

                    
                    // Retrieve the account number using the actual username value
                    String account  = details.getAccountNumber(username.getUsername());
                    BankOperations bankop = new BankOperations();

                    // Attempt deposit operation
                    if(bankop.withdraw(account, amount)){
                        JOptionPane.showMessageDialog(Withdraw.this, "Withdraw Successful!");
                    } else {
                        JOptionPane.showMessageDialog(Withdraw.this, "Withdraw failed!");
                    }

                    dispose(); // Close the window after operation

                } catch (NumberFormatException ex) {
                    // This catches cases where the input is not a valid double
                    JOptionPane.showMessageDialog(Withdraw.this, "Invalid Amount!");
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
        SwingUtilities.invokeLater(() -> new Withdraw());
    }
}