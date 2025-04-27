import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Code for making the round Password input box
class RoundedPasswordField extends JPasswordField {
    private int cornerRadius = 25; // Corner radius for rounded edges

    public RoundedPasswordField(int columns) {
        super(columns);
        setOpaque(false); // Make the component's background transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.GRAY); // Border color
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        g2.dispose();
    }
}

// Code for making the round Username input box
class RoundedTextField extends JTextField {
    private int cornerRadius = 25; // Corner radius for rounded edges

    public RoundedTextField(int columns) {
        super(columns);
        setOpaque(false); // Make the component's background transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.GRAY); // Border color
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        g2.dispose();
    }
}

// Code for making the round Login Button
class RoundedButton extends JButton {
    private int cornerRadius;
    private Image image; // Image for the button

    // Constructor for text-only button
    public RoundedButton(String text, int radius) {
        super(text);
        this.cornerRadius = radius;
        setFocusPainted(false);
        setContentAreaFilled(false);
    }

    // Constructor for image-based button
    public RoundedButton(ImageIcon icon, int radius) {
        super(icon);
        this.cornerRadius = radius;
        this.image = icon.getImage();
        setFocusPainted(false);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the button background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // Draw the border
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        // Draw the image if available
        if (image != null) {
            int imgX = (getWidth() - image.getWidth(null)) / 2;
            int imgY = (getHeight() - image.getHeight(null)) / 2;
            g2.drawImage(image, imgX, imgY, this);
        } else {
            // Draw the text if no image is provided
            FontMetrics fm = g.getFontMetrics();
            Rectangle r = fm.getStringBounds(getText(), g).getBounds();
            int textX = (getWidth() - r.width) / 2;
            int textY = (getHeight() - r.height) / 2 + fm.getAscent();
            g.setColor(getForeground());
            g.drawString(getText(), textX, textY);
        }

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Avoid default border painting
    }
}

// Code for setting custom background image
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

public class Log_In {

    public static void main(String[] args) {
        Username name = new Username();

        // Create frame
        JFrame frame = new JFrame("Login");
        frame.setSize(478, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        // Set Icon of the application
        ImageIcon icon = new ImageIcon("Rohit.png");
        frame.setIconImage(icon.getImage());

        // Set background image
        BackgroundPanel backgroundPanel = new BackgroundPanel("Rohit4.png");
        backgroundPanel.setLayout(null);
        frame.setContentPane(backgroundPanel);

        // Add a custom message
        JLabel label = new JLabel("Welcome To Bank Of BU ",SwingConstants.CENTER);
        label.setForeground(Color.magenta);
        label.setFont(new Font("Times new Roman", Font.BOLD, 24));
        label.setBounds(0, 20, frame.getWidth(), 50);

        // Username Text
        JLabel Username = new JLabel("Username:");
        Username.setBounds(10, 50, 120, 60);
        Username.setForeground(Color.WHITE);
        Username.setFont(new Font("Times new Roman",Font.BOLD, 20));

        // Taking Username as Input
        JTextField Username_Input = new RoundedTextField(15);
        Username_Input.setBounds(10, 100, 450, 30);
        Username_Input.setMargin(new Insets(5, 10, 5, 10));

        // Password Text
        JLabel Password = new JLabel("Password: ");
        Password.setBounds(10, 150, 120, 60);
        Password.setForeground(Color.WHITE);
        Password.setFont(new Font("Times new Roman",Font.BOLD,20));

        // Taking Password as Input
        JPasswordField Password_Input = new RoundedPasswordField(15);
        Password_Input.setBounds(10, 200, 450, 30);
        Password_Input.setEchoChar('#');
        Password_Input.setMargin(new Insets(5, 10, 5, 10));


        // Login Button
        RoundedButton Login = new RoundedButton("Login ", 30);
        Login.setBackground(new Color(0,139,139));
        Login.setForeground(Color.WHITE);
        Login.setFont(new Font("Times new Roman", Font.BOLD, 18));
        Login.setBounds(200, 300, 80, 30);

        // Create a BankOperations instance to check credentials
        BankOperations bankOps = new BankOperations();



        // Add action listener to the button
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get user inputs
                String username = Username_Input.getText();

                String password = String.valueOf(Password_Input.getPassword());

                if(username.equals("")){
                    JOptionPane.showMessageDialog(frame, "Username cannot be empty!");
                    return;
                }

                // Validate credentials
                if (bankOps.validateCredentials(username, password)) {
//                    JOptionPane.showMessageDialog(frame, "Login Successful!");
                    name.setUsername(username);
                    frame.dispose();
                    BankManagementSystem.main(null);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password!");
                    return;
                }
            }
        });

        JLabel CreateAccount = new JLabel("Don't have an account?");
        CreateAccount.setForeground(Color.magenta);
        CreateAccount.setFont(new Font("Serif", Font.BOLD, 18));
        CreateAccount.setBounds(10, 600, 200, 30);

        RoundedButton CreateAccountButton = new RoundedButton("Create Account", 30);
        CreateAccountButton.setBackground(new Color(0,139,139));
        CreateAccountButton.setForeground(Color.WHITE);
        CreateAccountButton.setBackground(Color.BLACK);
        CreateAccountButton.setBounds(230, 600, 200, 30);
        CreateAccountButton.setFont(new Font("Times new Roman", Font.BOLD, 18));

        CreateAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Sign_Up.main(null);
            }
        });



        // Add components to frame
        frame.add(label);
        frame.add(Login);
        frame.add(Username);
        frame.add(Username_Input);
        frame.add(Password);
        frame.add(Password_Input);
        frame.add(CreateAccount);
        frame.add(CreateAccountButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}