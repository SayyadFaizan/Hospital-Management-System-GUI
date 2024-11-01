package HospitalManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReceptionistLogin extends JFrame {

    public ReceptionistLogin() {
        setTitle("Receptionist Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main content panel with background image
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("src/background.png"); // Ensure this path is correct
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        setContentPane(mainPanel);

        // Create the inner box panel to hold login elements
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBackground(new Color(255, 255, 255, 255)); // Semi-transparent background

        // Add internal padding using EmptyBorder
        loginPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40)); // Internal padding

        // Create and add the username label and field
        JLabel userLabel = new JLabel("USER ID");
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25)); // Allow the text field to expand
        loginPanel.add(userText);

        // Create and add the password label and field
        JLabel passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25)); // Allow the password field to expand
        loginPanel.add(passwordText);

        // Create and add the Login button
        JButton loginButton = new JButton("LOGIN");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setBackground(Color.GREEN);
        loginButton.setForeground(Color.WHITE);
        loginPanel.add(loginButton);

        // Create and add the Cancel button
        JButton cancelButton = new JButton("CANCEL");
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);
        loginPanel.add(cancelButton);

        // Action listener for Login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                if (username.equals("Receptionist") && password.equals("4321")) {
                    // Open Receptionist Panel if login is successful
                    new ReceptionistPanel().setVisible(true);
                    dispose();
                } else {
                    // Show error message if login fails
                    JOptionPane.showMessageDialog(null, "Invalid credentials. Try again.");
                }
            }
        });

        // Action listener for Cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the login form when the Cancel button is clicked
                dispose();
            }
        });

        // Center the loginPanel within the mainPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginPanel, gbc);
    }
}
