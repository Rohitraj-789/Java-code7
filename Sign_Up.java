import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Sign_Up{

    public static void main(String[] args) {
        BankOperations bankop = new BankOperations();
        JFrame frame = new JFrame("Sign Up");
        frame.setSize(478, 815);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        // Set Icon of the application
        ImageIcon icon = new ImageIcon("");
        frame.setIconImage(icon.getImage());

        // Set background image
        BackgroundPanel backgroundPanel = new BackgroundPanel("Rohit6.png");
        backgroundPanel.setLayout(null);
        frame.setContentPane(backgroundPanel);

        // Add a custom message
        JLabel label = new JLabel("Welcome To Bank Of BU ",SwingConstants.CENTER);
        label.setForeground(Color.RED);
        label.setFont(new Font("Serif", Font.BOLD, 32));
        label.setBounds(0, 80, frame.getWidth(), 50);

        // Add an image to the window
        ImageIcon pic = new ImageIcon(""); // Replace with your image path
        JLabel imageLabel = new JLabel(pic);
        imageLabel.setBounds(200, 100, 80, 80); // Position and size of the image


        // Name Text
        JLabel Name = new JLabel("Name:");
        Name.setBounds(10, 200, 120, 60);
        Name.setForeground(Color.BLACK);
        Name.setFont(new Font("Times new Roman", Font.BOLD, 18));

        // Taking Name as Input
        JTextField Name_Input = new RoundedTextField(15);
        Name_Input.setBounds(10, 250, 450, 30);
        Name_Input.setMargin(new Insets(5, 10, 5, 10));

        // Username Text
        JLabel Username = new JLabel("Username:");
        Username.setBounds(10, 300, 120, 60);
        Username.setForeground(Color.BLACK);
        Username.setFont(new Font("Times new Roman", Font.BOLD, 18));

        // Taking Username as Input
        JTextField Username_Input = new RoundedTextField(15);
        Username_Input.setBounds(10, 350, 450, 30);
        Username_Input.setMargin(new Insets(5, 10, 5, 10));

        // Password Text
        JLabel Password = new JLabel("Password:");
        Password.setBounds(10, 400, 120, 60);
        Password.setForeground(Color.BLACK);
        Password.setFont(new Font("Times new Roman", Font.BOLD, 18));

        // Taking Password as Input
        JPasswordField Password_Input = new RoundedPasswordField(15);
        Password_Input.setBounds(10, 450, 450, 30);
        Password_Input.setEchoChar('#');
        Password_Input.setMargin(new Insets(5, 10, 5, 10));

        // Email Text
        JLabel Email = new JLabel("Email:");
        Email.setBounds(10, 500, 120, 60);
        Email.setForeground(Color.BLACK);
        Email.setFont(new Font("Times new Roman", Font.BOLD, 18));

        // Taking Email as Input
        JTextField Email_Input = new RoundedTextField(15);
        Email_Input.setBounds(10, 550, 450, 30);
        Email_Input.setMargin(new Insets(5, 10, 5, 10));

        // Phone number text
        JLabel Phone_Number = new JLabel("Phone No:");
        Phone_Number.setBounds(10, 600, 120, 60);
        Phone_Number.setForeground(Color.BLACK);
        Phone_Number.setFont(new Font("Times new Roman", Font.BOLD, 18));

        // Taking Phone number as Input
        JTextField Phone_Number_Input = new RoundedTextField(15);
        Phone_Number_Input.setBounds(10, 650, 450, 30);
        Phone_Number_Input.setMargin(new Insets(5, 10, 5, 10));




        // Sign Up Button
        RoundedButton Sign_Up = new RoundedButton("Sign Up", 30);
        Sign_Up.setBackground(new Color(0,139,139));
        Sign_Up.setForeground(Color.RED);
        Sign_Up.setBackground(Color.CYAN);
        Sign_Up.setFont(new Font("Times new Roman", Font.BOLD, 24));
        Sign_Up.setBounds(200, 730, 100, 40);


        // Add action listener to the button
        Sign_Up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = Username_Input.getText();
                String password = String.valueOf(Password_Input.getPassword());
                String name = Name_Input.getText();
                String Email = Email_Input.getText();
                String phone_number = Phone_Number_Input.getText();

                if(name.equals("")){
                    JOptionPane.showMessageDialog(frame, "Name cannot be empty!");
                    return;
                }
                if(username.equals("")){
                    JOptionPane.showMessageDialog(frame, "Username cannot be empty!");
                    return;
                }

                if(password.length()<8){
                    JOptionPane.showMessageDialog(frame, "Password must be at least 8 characters long!");
                    return;
                }

                if(Email.equals("")){
                    JOptionPane.showMessageDialog(frame, "Email cannot be empty!");
                    return;
                }
                if(phone_number.length()!=10){
                    JOptionPane.showMessageDialog(frame, "Invalid Phone Number!");
                    return;
                }
                if (bankop.createAccount(name, username, password, phone_number, Email)) {
                    label.setText("Account created successfully!");

                    Timer timer = new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            frame.dispose();
                            Log_In.main(null); // Launch login screen
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();

                }
            }

        });

        ImageIcon Back = new ImageIcon("");
        RoundedButton backButton = new RoundedButton(Back,40);
        backButton.setBounds(5, 6, Back.getIconWidth(), Back.getIconHeight());
        backButton.setBackground(new Color(0,139,139));
        backButton.setForeground(Color.WHITE);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Log_In.main(null);
            }
        });


        // Add components to frame
        frame.add(label);
        frame.add(imageLabel);
        frame.add(Name);
        frame.add(Name_Input);
        frame.add(Sign_Up);
        frame.add(Username);
        frame.add(Username_Input);
        frame.add(Password);
        frame.add(Password_Input);
        frame.add(Email);
        frame.add(Email_Input);
        frame.add(Phone_Number);
        frame.add(Phone_Number_Input);
        frame.add(backButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
