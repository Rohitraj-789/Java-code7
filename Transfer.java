import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transfer extends JFrame {
    public Transfer() {
        Username username = new Username();   // you supply this
        Details details = new Details();      // you supply this

        // Frame setup
        setTitle("Transfer");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout());

        JLabel usernameLabel = new JLabel("Enter Username: ");
        JTextField usernameText = new JTextField(10);


        JLabel amountLabel = new JLabel("Enter amount: ");
        JTextField amountField = new JTextField(10);

        inputPanel.add(usernameLabel);
        inputPanel.add(usernameText);
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton transferButton = new JButton("Transfer");
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = amountField.getText().trim();
                if (text.isEmpty()) {
                    JOptionPane.showMessageDialog(Transfer.this, "Amount cannot be empty!");
                    return;
                }

                double amount;
                try {
                    amount = Double.parseDouble(text);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Transfer.this, "Invalid Amount!");
                    return;
                }

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(Transfer.this, "Amount must be positive!");
                    return;
                }

                String RecipientUsername = usernameText.getText();
                String account = details.getAccountNumber(username.getUsername());
                BankOperations bankop = new BankOperations();
                boolean success = bankop.transfer(account , details.getAccountNumber(RecipientUsername) , amount);

                String msg = success ? "Transfer Successful!" : "Transfer Failed!";
                JOptionPane.showMessageDialog(Transfer.this, msg);
                dispose();
            }
        });
        buttonPanel.add(transferButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Transfer());
    }
}