package HospitalManagementSystem;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateDoctorForm extends JFrame {
    private JTextField idField, nameField, specializationField, phoneField;
    private Database db = new Database();

    public UpdateDoctorForm() {
        setTitle("Update Doctor");
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
        JLabel headerLabel = new JLabel("Update Doctor");
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

        // Doctor ID label and input field
        JLabel idLabel = new JLabel("Doctor ID:");
        idLabel.setBounds(50, 30, 100, 25);
        formBoxPanel.add(idLabel);

        idField = new JTextField(20);
        idField.setBounds(150, 30, 200, 30);
        formBoxPanel.add(idField);

        // Key listener to fetch doctor details on key press
        idField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                loadDoctorDetails();
            }
        });

        // Name label and input field
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 80, 80, 25);
        formBoxPanel.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(150, 80, 200, 30);
        formBoxPanel.add(nameField);

        // Specialization label and input field
        JLabel specializationLabel = new JLabel("Specialization:");
        specializationLabel.setBounds(50, 130, 100, 25);
        formBoxPanel.add(specializationLabel);

        specializationField = new JTextField(20);
        specializationField.setBounds(150, 130, 200, 30);
        formBoxPanel.add(specializationField);

        // Phone label and input field
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 180, 80, 25);
        formBoxPanel.add(phoneLabel);

        phoneField = new JTextField(20);
        phoneField.setBounds(150, 180, 200, 30);
        formBoxPanel.add(phoneField);

        // Update button
        JButton updateButton = new JButton("Update");
        updateButton.setBounds(150, 230, 100, 40);
        updateButton.setBackground(new Color(0, 204, 0)); // Green background for update button
        updateButton.setForeground(Color.WHITE);
        formBoxPanel.add(updateButton);

        // Update button action listener
        updateButton.addActionListener(e -> updateDoctorDetails());
    }

    // Load doctor details based on ID
    private void loadDoctorDetails() {
        String id = idField.getText();
        if (id.isEmpty()) {
            // Clear fields if ID is empty
            nameField.setText("");
            specializationField.setText("");
            phoneField.setText("");
            return;
        }

        try {
            ResultSet resultSet = db.executeQuery("SELECT * FROM doctors WHERE id = ?", id);
            if (resultSet.next()) {
                nameField.setText(resultSet.getString("name"));
                specializationField.setText(resultSet.getString("specialization"));
                phoneField.setText(resultSet.getString("phone"));
            } else {
                // Clear fields if no doctor found for the ID
                nameField.setText("");
                specializationField.setText("");
                phoneField.setText("");
                JOptionPane.showMessageDialog(null, "Doctor ID not found.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading doctor details: " + e.getMessage());
        }
    }

    // Update doctor details in the database
    private void updateDoctorDetails() {
        String id = idField.getText();
        String name = nameField.getText();
        String specialization = specializationField.getText();
        String phone = phoneField.getText();

        int affectedRows = db.executeUpdate("UPDATE doctors SET name = ?, specialization = ?, phone = ? WHERE id = ?", name, specialization, phone, id);
        if (affectedRows > 0) {
            JOptionPane.showMessageDialog(null, "Doctor Updated Successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update Doctor!");
        }
        db.close();
        dispose();
    }
}
