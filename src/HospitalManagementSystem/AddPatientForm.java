package HospitalManagementSystem;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPatientForm extends JFrame {
    private JTextField nameField, ageField, diseaseField, phoneField;

    public AddPatientForm() {
        setTitle("Add Patient");
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
        JLabel headerLabel = new JLabel("Add Patient");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(headerLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Form box panel with border
        JPanel formBoxPanel = new JPanel();
        formBoxPanel.setLayout(null);
        formBoxPanel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true)); // Border for form box
        formBoxPanel.setBackground(Color.WHITE); // White background for form box
        formBoxPanel.setPreferredSize(new Dimension(500, 400));

        // Center the form box panel on screen
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(formBoxPanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Form fields inside the box
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 30, 100, 25);
        formBoxPanel.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(150, 30, 250, 30);
        formBoxPanel.add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(50, 80, 100, 25);
        formBoxPanel.add(ageLabel);

        ageField = new JTextField(20);
        ageField.setBounds(150, 80, 250, 30);
        formBoxPanel.add(ageField);

        JLabel diseaseLabel = new JLabel("Disease:");
        diseaseLabel.setBounds(50, 130, 100, 25);
        formBoxPanel.add(diseaseLabel);

        diseaseField = new JTextField(20);
        diseaseField.setBounds(150, 130, 250, 30);
        formBoxPanel.add(diseaseField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 180, 100, 25);
        formBoxPanel.add(phoneLabel);

        phoneField = new JTextField(20);
        phoneField.setBounds(150, 180, 250, 30);
        formBoxPanel.add(phoneField);

        // Add button
        JButton addButton = new JButton("Add");
        addButton.setBounds(150, 250, 150, 40);
        addButton.setBackground(new Color(0, 153, 51)); // Set background color for button
        addButton.setForeground(Color.WHITE); // Set text color
        formBoxPanel.add(addButton);

        // Action listener for add button with validation
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateForm()) {
                    String name = nameField.getText();
                    String age = ageField.getText();
                    String disease = diseaseField.getText();
                    String phone = phoneField.getText();

                    Database db = new Database();
                    int affectedRows = db.executeUpdate(
                            "INSERT INTO patients (name, age, disease, phone) VALUES (?, ?, ?, ?)",
                            name, age, disease, phone
                    );

                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(null, "Patient Added Successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to add Patient!");
                    }
                    db.close();
                    dispose();
                }
            }
        });
    }

    private boolean validateForm() {
        if (nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the patient's name.");
            nameField.requestFocus();
            return false;
        }

        if (ageField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the patient's age.");
            ageField.requestFocus();
            return false;
        }

        if (diseaseField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the disease.");
            diseaseField.requestFocus();
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
