package HospitalManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReceptionistPanel extends JFrame {

    public ReceptionistPanel() {
        setTitle("Receptionist Panel");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        // Create the header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 102, 204)); // Blue header
        headerPanel.setPreferredSize(new Dimension(0, 60)); // Set height for header
        JLabel headerLabel = new JLabel("Receptionist Control Panel");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(headerLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Create the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(new Color(255, 255, 255, 180)); // Semi-transparent background

        // Add buttons with icons
        addButtonWithIcon(buttonPanel, "Add Patient", "src/add_patient_icon.png", Color.DARK_GRAY, 0, 0);
        addButtonWithIcon(buttonPanel, "Update Patient", "src/update_patient_icon.png", Color.DARK_GRAY, 0, 1);
        addButtonWithIcon(buttonPanel, "Delete Patient", "src/delete_patient_icon.png", Color.DARK_GRAY, 0, 2);
        addButtonWithIcon(buttonPanel, "View Patients", "src/view_patients_icon.png", Color.DARK_GRAY, 0, 3);
        addButtonWithIcon(buttonPanel, "View Doctors", "src/view_doctor_icon.png", Color.DARK_GRAY, 0, 4);
        addButtonWithIcon(buttonPanel, "Book Appointment", "src/book_appointment_icon.png", Color.DARK_GRAY, 0, 5);
        addButtonWithIcon(buttonPanel, "View Appointment", "src/view_appointment_icon.png", Color.DARK_GRAY, 0, 6);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(200, 30));
        logoutButton.setBackground(Color.RED);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu().setVisible(true);
                dispose();
            }
        });
        buttonPanel.add(logoutButton, new GridBagConstraints(0, 7, 1, 1, 1.0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
    }

    private void addButtonWithIcon(JPanel panel, String buttonText, String iconPath, Color buttonColor, int gridX, int gridY) {
        JButton button = new JButton(buttonText);
        button.setPreferredSize(new Dimension(200, 30));
        button.setBackground(buttonColor); // Set the background color
        button.setForeground(Color.WHITE); // Set the text color

        // Set the button icon
        ImageIcon icon = new ImageIcon(iconPath);
        button.setIcon(icon);
        button.setHorizontalTextPosition(SwingConstants.RIGHT); // Position text to the right of the icon

        // Add action listener for the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (buttonText) {
                    case "Add Patient":
                        new AddPatientForm().setVisible(true);
                        break;
                    case "Update Patient":
                        new UpdatePatientForm().setVisible(true);
                        break;
                    case "Delete Patient":
                        new DeletePatientForm().setVisible(true);
                        break;
                    case "View Patients":
                        new ViewPatientsForm().setVisible(true);
                        break;
                    case "View Doctors":
                        new ViewDoctorsForm().setVisible(true);
                        break;
                    case "Book Appointment":
                        new BookAppointmentForm().setVisible(true);
                        break;
                    case "View Appointment":
                        new ViewAppointment().setVisible(true);
                        break;
                }
            }
        });

        panel.add(button, new GridBagConstraints(gridX, gridY, 1, 1, 1.0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 0, 0), 0, 0));
}
}
