package HospitalManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    public MainMenu() {
        // Set the JFrame to full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Hospital Management System");

        // Main content panel with background image
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("src/background.png"); // Ensure this path is correct
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new GridBagLayout()); // Center the inner panel in main panel
        setContentPane(mainPanel);

        // Add main title label
        JLabel mainTitleLabel = new JLabel("Mubin Charitable Trust");
        mainTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainTitleLabel.setForeground(new Color(0, 0, 0));
        mainTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainTitleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Spacing around the title
        mainPanel.add(mainTitleLabel, createGbc(0, 0, GridBagConstraints.CENTER));

        // Create the inner box panel to hold form elements
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        boxPanel.setBackground(new Color(255, 255, 255, 255)); // Semi-transparent background
        boxPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2, true)); // Blue border
        boxPanel.setPreferredSize(new Dimension(300, 300)); // Fixed size for the box panel
        boxPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Internal padding

        // Add title label
        JLabel titleLabel = new JLabel("Select User Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        boxPanel.add(titleLabel);

        // Buttons with icons (optional icons)
        JButton adminButton = new JButton("Admin");
        adminButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(adminButton, new Color(0, 153, 255));
        boxPanel.add(adminButton);

        boxPanel.add(Box.createVerticalStrut(10)); // Spacer between buttons

        JButton receptionistButton = new JButton("Receptionist");
        receptionistButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(receptionistButton, new Color(0, 153, 76));
        boxPanel.add(receptionistButton);

        boxPanel.add(Box.createVerticalStrut(10)); // Spacer between buttons

        JButton exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(exitButton, new Color(204, 0, 0));
        boxPanel.add(exitButton);

        // Action listeners for buttons
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminLogin().setVisible(true);
                dispose();
            }
        });

        receptionistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReceptionistLogin().setVisible(true);
                dispose();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Center boxPanel within mainPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1; // Adjusted to place below the main title
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(boxPanel, gbc);
    }

    // Helper method to style buttons
    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(180, 40)); // Set consistent button size
    }

    // Helper method to create GridBagConstraints
    private GridBagConstraints createGbc(int gridx, int gridy, int anchor) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = anchor;
        return gbc;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });
    }
}
