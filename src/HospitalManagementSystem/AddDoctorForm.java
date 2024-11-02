package HospitalManagementSystem;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDoctorForm extends JFrame {
    private JTextField nameField, specializationField, phoneField;

    public AddDoctorForm() {
        setTitle("Add Doctor");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizes the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main content panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        // Create the header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 102, 204)); // Blue header
        headerPanel.setPreferredSize(new Dimension(0, 60)); // Set height for header
        JLabel headerLabel = new JLabel("Add Doctor");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(headerLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Form box panel with border
        JPanel formBoxPanel = new JPanel();
        formBoxPanel.setLayout(null);
        formBoxPanel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true)); // Border for form box
        formBoxPanel.setBackground(Color.WHITE); // White background for form box
        formBoxPanel.setPreferredSize(new Dimension(500, 350));

        // Center the form box panel on screen
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(formBoxPanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 30, 100, 25);
        formBoxPanel.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(150, 30, 250, 30);
        formBoxPanel.add(nameField);

        JLabel specializationLabel = new JLabel("Specialization:");
        specializationLabel.setBounds(50, 80, 100, 25);
        formBoxPanel.add(specializationLabel);

        specializationField = new JTextField(20);
        specializationField.setBounds(150, 80, 250, 30);
        formBoxPanel.add(specializationField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 130, 100, 25);
        formBoxPanel.add(phoneLabel);

        phoneField = new JTextField(20);
        phoneField.setBounds(150, 130, 250, 30);
        formBoxPanel.add(phoneField);

        JButton addButton = new JButton("Add");
        addButton.setBounds(150, 200, 150, 40);
        addButton.setBackground(new Color(0, 153, 51)); // Set background color for button
        addButton.setForeground(Color.WHITE); // Set text color
        formBoxPanel.add(addButton);

        // Action listener for add button
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateForm()) {
                    String name = nameField.getText();
                    String specialization = specializationField.getText();
                    String phone = phoneField.getText();

                    Database db = new Database();
                    int affectedRows = db.executeUpdate(
                            "INSERT INTO doctors (name, specialization, phone) VALUES (?, ?, ?)",
                            name, specialization, phone
                    );

                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(null, "Doctor Added Successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to add Doctor!");
                    }
                    db.close();
                    dispose();
                }
            }
        });
    }

    private boolean validateForm() {
        if (nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the doctor's name.");
            nameField.requestFocus();
            return false;
        }

        if (specializationField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the specialization.");
            specializationField.requestFocus();
            return false;
        }

        String phone = phoneField.getText().trim();
        if (phone.isEmpty() || phone.length() < 10) {
            JOptionPane.showMessageDialog(this, "Please enter a valid phone number (10 digits minimum).");
            phoneField.requestFocus();
            return false;
        }

        return true;
    }
}
