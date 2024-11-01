package HospitalManagementSystem;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteDoctorForm extends JFrame {
    private JTextField idField;
    private Database db = new Database();

    public DeleteDoctorForm() {
        setTitle("Delete Doctor");
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
        JLabel headerLabel = new JLabel("Delete Doctor");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(headerLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Form box panel with border
        JPanel formBoxPanel = new JPanel();
        formBoxPanel.setLayout(null);
        formBoxPanel.setBorder(new LineBorder(new Color(128, 128, 128), 2, true));
        formBoxPanel.setBackground(Color.WHITE);
        formBoxPanel.setPreferredSize(new Dimension(400, 200));

        // Center the form box panel on screen
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(formBoxPanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Doctor ID label and input field
        JLabel idLabel = new JLabel("Doctor ID:");
        idLabel.setBounds(50, 50, 100, 25);
        formBoxPanel.add(idLabel);

        idField = new JTextField(20);
        idField.setBounds(150, 50, 200, 30);
        formBoxPanel.add(idField);

        // Delete button
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(150, 100, 100, 40);
        deleteButton.setBackground(new Color(204, 0, 0)); // Red background for delete button
        deleteButton.setForeground(Color.WHITE);
        formBoxPanel.add(deleteButton);

        // Delete button action listener
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Doctor ID is required.");
                    return;
                }

                int affectedRows = db.executeUpdate("DELETE FROM doctors WHERE id = ?", id);
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Doctor Deleted Successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete Doctor or Doctor ID not found.");
                }
                db.close();
                dispose();
            }
        });
    }
}
