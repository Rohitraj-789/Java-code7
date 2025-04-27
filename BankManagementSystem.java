import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.border.TitledBorder;
import java.io.File;
import java.io.IOException;

class RoundImageLabel extends JLabel {
    /**
     * Constructs a RoundImageLabel with a circular image.
     *
     * @param imagePath The file path to the image.
     * @param diameter  The diameter (in pixels) of the circular image.
     */
    public RoundImageLabel(String imagePath, int diameter) {
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (originalImage != null) {
            // Create a new image with transparency
            BufferedImage circularImage = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = circularImage.createGraphics();

            // Enable antialiasing for smooth edges
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Create a circular clipping area
            Ellipse2D circle = new Ellipse2D.Float(0, 0, diameter, diameter);
            g2.setClip(circle);

            // Draw the original image scaled to fit the circle
            g2.drawImage(originalImage, 0, 0, diameter, diameter, null);
            g2.dispose();

            // Set the circular image as the icon of this label
            setIcon(new ImageIcon(circularImage));
        }
    }
}

class BackgroundPanel2 extends JPanel {
    private Image backgroundImage;
    public BackgroundPanel2(String imagepath) {
        try{
            backgroundImage = ImageIO.read(new File(imagepath));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (backgroundImage != null){
            g.drawImage(backgroundImage,0,0,getWidth(),getHeight(),this);
        }
    }
}

public class BankManagementSystem {
    public static void main(String[] args) {
        Details details = new Details();
        String username = Username.getUsername();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create the main frame
                JFrame frame = new JFrame("Bank of BU");

                // setting screen size to full screen
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                Rectangle bounds = ge.getMaximumWindowBounds();
                frame.setBounds(bounds);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(null);
                frame.setResizable(false);

                // Optionally set an icon for the application
                ImageIcon icon = new ImageIcon("Rohit2.png");
                frame.setIconImage(icon.getImage().getScaledInstance(64,64,Image.SCALE_SMOOTH));



                // Create a JSplitPane to divide the window into two parts
                JSplitPane splitPane = new JSplitPane();
                // Because we're using a null layout, we need to manually set bounds
                splitPane.setBounds(0, 0, frame.getWidth(), frame.getHeight());

                // Left panel: User Details with background images
                BackgroundPanel2 userDetailsPanel = new BackgroundPanel2("Rohit11.png");
//                JPanel userDetailsPanel = new JPanel();
                userDetailsPanel.setLayout(null);
                userDetailsPanel.setBounds(500, 50, 400, 400);

                // Create a TitledBorder and set a custom title font (e.g., size 20)
                TitledBorder border = BorderFactory.createTitledBorder("Details");
                border.setTitleFont(new Font("Times new Roman", Font.BOLD, 40));
                border.setTitleColor(Color.BLACK);
                border.setTitleJustification(TitledBorder.CENTER);
                userDetailsPanel.setBorder(border);//


                // Add an image (account icon)
                RoundImageLabel accountIcon = new RoundImageLabel("",248);
                accountIcon.setBounds(70, 100, 250, 250);


                JLabel UserName = new JLabel(username);
                UserName.setBounds(100, 250, 400, 200);
                UserName.setFont(new Font("Times new Roman", Font.BOLD, 30));

                // Sample user details
                String Name = details.getName(username);
                JLabel nameLabel = new JLabel("Name: " + Name);
                nameLabel.setBounds(60, 400, 550, 25);
                nameLabel.setFont(new Font("Times new Roman", Font.BOLD, 20));

                String AccountNumber = details.getAccountNumber(username);
                JLabel accountLabel = new JLabel("Account No: " + AccountNumber);
                accountLabel.setBounds(60, 450, 550, 25);
                accountLabel.setFont(new Font("Times new Roman", Font.BOLD, 20));

                Double Balance = details.getBalance(username);
                JLabel balanceLabel = new JLabel("Balance: " + Balance.toString());
                balanceLabel.setBounds(60, 500, 550, 25);
                balanceLabel.setFont(new Font("Times new Roman", Font.BOLD, 20));


                String Email = details.getEmail(username);
                JLabel emailLabel = new JLabel("Email Id: " + Email);
                emailLabel.setBounds(60, 550, 550, 25);
                emailLabel.setFont(new Font("Times new Roman", Font.BOLD, 20));

                String Phone = details.getrPhone(username);
                JLabel phoneLabel = new JLabel("Phone Number : +91 "+Phone);
                phoneLabel.setBounds(60, 600, 550, 25);
                phoneLabel.setFont(new Font("Times new Roman", Font.BOLD, 20));



                // Add components with some spacing
                userDetailsPanel.add(UserName);

                userDetailsPanel.add(accountIcon);

                userDetailsPanel.add(nameLabel);

                userDetailsPanel.add(emailLabel);

                userDetailsPanel.add(phoneLabel);

                userDetailsPanel.add(accountLabel);

                userDetailsPanel.add(balanceLabel);

/*
            code for the right side of the partition.
 */



                // Right panel: User Activities

//                JPanel activitiesPanel = new JPanel(new FlowLayout());
                BackgroundPanel2 activitiesPanel = new BackgroundPanel2("");
                activitiesPanel.setLayout(null);
//                activitiesPanel.setBorder(BorderFactory.createTitledBorder("Activities"));
//                activitiesPanel.setBounds(10, 10, 50, 50);

                // Create a TitledBorder and set a custom title font (e.g., size 20)
                TitledBorder Header = BorderFactory.createTitledBorder("Bank Of BU");
                Header.setTitleFont(new Font("Times new Roman", Font.BOLD, 40));
                Header.setTitleColor(Color.BLACK);
                Header.setTitleJustification(TitledBorder.CENTER);
                activitiesPanel.setBorder(Header);//

                ImageIcon Exit = new ImageIcon("");
//                Image scaledExitImage = Exit.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
//                ImageIcon scaledExitIcon = new ImageIcon(scaledExitImage);
                RoundedButton LogOutButton = new RoundedButton(Exit,40);
                LogOutButton.setBounds(300, 100, Exit.getIconWidth(), Exit.getIconHeight());
                LogOutButton.setBackground(new Color(0,139,139));
                LogOutButton.setForeground(Color.magenta);

                LogOutButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        Log_In.main(null);
                    }
                });


                // Create buttons for banking operations
                RoundedButton depositButton = new RoundedButton("Deposit",30);
                depositButton.setBounds(50, 600, 250, 170);
                depositButton.setFont(new Font("Serif", Font.BOLD, 28));
                depositButton.setBackground(new Color(207,180,237));
                depositButton.setForeground(Color.WHITE);
//                depositButton.setBackground(Color.CYAN);


                RoundedButton withdrawButton = new RoundedButton("Withdraw Funds",30);
                withdrawButton.setBounds(400, 600, 250, 170);
                withdrawButton.setFont(new Font("Serif", Font.BOLD, 28));
                withdrawButton.setBackground(new Color(244,194,240));
                withdrawButton.setForeground(Color.white);

                RoundedButton TransferButton = new RoundedButton("Transfer Funds",30);
                TransferButton.setBounds(800, 600, 250, 170);
                TransferButton.setFont(new Font("Serif", Font.BOLD, 28));
                TransferButton.setBackground(new Color(207,180,237));
                TransferButton.setForeground(Color.WHITE);

                // Add action listeners for button events
                depositButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Deposite.main(null);
//                        JOptionPane.showMessageDialog(frame, "Deposit functionality is not implemented yet.");
                    }
                });

                withdrawButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
//                        JOptionPane.showMessageDialog(frame, "Withdraw funds functionality is not implemented yet.");
                        Withdraw.main(null);
                    }
                });

                TransferButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
//                        JOptionPane.showMessageDialog(frame, "Transfer funds functionality is not implemented yet.");
                        Transfer.main(null);
                    }
                });

                // Add buttons to the activities panel
                activitiesPanel.add(LogOutButton);
                activitiesPanel.add(depositButton);
                activitiesPanel.add(withdrawButton);
                activitiesPanel.add(TransferButton);

                // Configure the JSplitPane with the two panels
                splitPane.setLeftComponent(userDetailsPanel);
                splitPane.setRightComponent(activitiesPanel);
                splitPane.setDividerLocation(400);
                splitPane.setEnabled(false);

                // Add the split pane to the frame
                frame.add(splitPane);

                // Center the frame on screen and make it visible
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}