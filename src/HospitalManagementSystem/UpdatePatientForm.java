package HospitalManagementSystem;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdatePatientForm extends JFrame {
    private JTextField idField, nameField, ageField, diseaseField, phoneField;
    private Database db = new Database();

    public UpdatePatientForm() {
        setTitle("Update Patient");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main content panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 102, 204)); // Blue header color
        headerPanel.setPreferredSize(new Dimension(0, 60));
        JLabel headerLabel = new JLabel("Update Patient");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(headerLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Form box panel with border
        JPanel formBoxPanel = new JPanel();
        formBoxPanel.setLayout(null);
        formBoxPanel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
        formBoxPanel.setBackground(Color.WHITE);
        formBoxPanel.setPreferredSize(new Dimension(400, 350));

        // Center the form box panel on screen
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(formBoxPanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Patient ID label and input field
        JLabel idLabel = new JLabel("Patient ID:");
        idLabel.setBounds(50, 30, 100, 25);
        formBoxPanel.add(idLabel);

        idField = new JTextField(20);
        idField.setBounds(150, 30, 200, 30);
        formBoxPanel.add(idField);

        // Key listener to fetch patient details on key press
        idField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                loadPatientDetails();
            }
        });

        // Name label and input field
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 80, 80, 25);
        formBoxPanel.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(150, 80, 200, 30);
        formBoxPanel.add(nameField);

        // Age label and input field
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(50, 130, 80, 25);
        formBoxPanel.add(ageLabel);

        ageField = new JTextField(20);
        ageField.setBounds(150, 130, 200, 30);
        formBoxPanel.add(ageField);

        // Disease label and input field
        JLabel diseaseLabel = new JLabel("Disease:");
        diseaseLabel.setBounds(50, 180, 80, 25);
        formBoxPanel.add(diseaseLabel);

        diseaseField = new JTextField(20);
        diseaseField.setBounds(150, 180, 200, 30);
        formBoxPanel.add(diseaseField);

        // Phone label and input field
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 230, 80, 25);
        formBoxPanel.add(phoneLabel);

        phoneField = new JTextField(20);
        phoneField.setBounds(150, 230, 200, 30);
        formBoxPanel.add(phoneField);

        // Update button
        JButton updateButton = new JButton("Update");
        updateButton.setBounds(150, 280, 100, 40);
        updateButton.setBackground(new Color(0, 204, 0)); // Green background for update button
        updateButton.setForeground(Color.WHITE);
        formBoxPanel.add(updateButton);

        // Update button action listener
        updateButton.addActionListener(e -> updatePatientDetails());
    }

    // Load patient details based on ID
    private void loadPatientDetails() {
        String id = idField.getText();
        if (id.isEmpty()) {
            // Clear fields if ID is empty
            nameField.setText("");
            ageField.setText("");
            diseaseField.setText("");
            phoneField.setText("");
            return;
        }

        try {
            ResultSet resultSet = db.executeQuery("SELECT * FROM patients WHERE id = ?", id);
            if (resultSet.next()) {
                nameField.setText(resultSet.getString("name"));
                ageField.setText(resultSet.getString("age"));
                diseaseField.setText(resultSet.getString("disease"));
                phoneField.setText(resultSet.getString("phone"));
            } else {
                // Clear fields if no patient found for the ID
                nameField.setText("");
                ageField.setText("");
                diseaseField.setText("");
                phoneField.setText("");
                JOptionPane.showMessageDialog(null, "Patient ID not found.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading patient details: " + e.getMessage());
        }
    }

    // Update patient details in the database
    private void updatePatientDetails() {
        String id = idField.getText();
        String name = nameField.getText();
        String age = ageField.getText();
        String disease = diseaseField.getText();
        String phone = phoneField.getText();

        int affectedRows = db.executeUpdate("UPDATE patients SET name = ?, age = ?, disease = ?, phone = ? WHERE id = ?", name, age, disease, phone, id);
        if (affectedRows > 0) {
            JOptionPane.showMessageDialog(null, "Patient Updated Successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update Patient!");
        }
        db.close();
        dispose();
    }
}
