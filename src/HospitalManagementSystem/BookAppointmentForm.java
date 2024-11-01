package HospitalManagementSystem;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BookAppointmentForm extends JFrame {
    private JTextField patientIdField, doctorIdField, dateField;
    Database db = new Database();

    public BookAppointmentForm() {
        setTitle("Book Appointment");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizes the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main content panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 102, 204)); // Blue header
        headerPanel.setPreferredSize(new Dimension(0, 60)); // Set height for header
        JLabel headerLabel = new JLabel("Book Appointment");
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
        JLabel patientIdLabel = new JLabel("Patient ID:");
        patientIdLabel.setBounds(50, 30, 100, 25);
        formBoxPanel.add(patientIdLabel);

        patientIdField = new JTextField(20);
        patientIdField.setBounds(150, 30, 250, 30);
        formBoxPanel.add(patientIdField);

        JLabel doctorIdLabel = new JLabel("Doctor ID:");
        doctorIdLabel.setBounds(50, 80, 100, 25);
        formBoxPanel.add(doctorIdLabel);

        doctorIdField = new JTextField(20);
        doctorIdField.setBounds(150, 80, 250, 30);
        formBoxPanel.add(doctorIdField);

        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateLabel.setBounds(50, 130, 120, 25);
        formBoxPanel.add(dateLabel);

        dateField = new JTextField(20);
        dateField.setBounds(180, 130, 220, 30);
        formBoxPanel.add(dateField);

        // Book button
        JButton bookButton = new JButton("Book");
        bookButton.setBounds(150, 200, 150, 40);
        bookButton.setBackground(new Color(0, 153, 51)); // Set background color for button
        bookButton.setForeground(Color.WHITE); // Set text color
        formBoxPanel.add(bookButton);

        // Action listener for book button with validation
        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    int patientId = Integer.parseInt(patientIdField.getText());
                    int doctorId = Integer.parseInt(doctorIdField.getText());
                    String date = dateField.getText();

                    if (getPatientById(patientId) && getDoctorById(doctorId)) {
                        if (checkDoctorAvailability(doctorId, date)) {
                            bookAppointment(patientId, doctorId, date);
                        } else {
                            JOptionPane.showMessageDialog(null, "Doctor is not available on this date.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Patient or Doctor ID.");
                    }
                }
            }
        });
    }

    // Validate input fields
    private boolean validateFields() {
        if (patientIdField.getText().isEmpty() || doctorIdField.getText().isEmpty() || dateField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required.");
            return false;
        }

        try {
            Integer.parseInt(patientIdField.getText());
            Integer.parseInt(doctorIdField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Patient ID and Doctor ID must be valid integers.");
            return false;
        }

        try {
            LocalDate.parse(dateField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Date must be in format YYYY-MM-DD.");
            return false;
        }

        return true;
    }

    // Check if the doctor exists in the database
    private boolean getDoctorById(int id) {
        try {
            ResultSet resultSet = db.executeQuery("SELECT * FROM doctors WHERE id = ?", id);
            return resultSet.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error checking doctor ID: " + e.getMessage());
        }
        return false;
    }

    // Check if the patient exists in the database
    private boolean getPatientById(int id) {
        try{
            ResultSet resultSet = db.executeQuery("SELECT * FROM patients WHERE id = ?", id);
            return resultSet.next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error checking patient ID: " + e.getMessage());
        }
        return false;
    }

    // Check doctor's availability
    private boolean checkDoctorAvailability(int doctorId, String appointmentDate) {
        try{
            ResultSet resultSet = db.executeQuery("SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?", doctorId, appointmentDate);
            if (resultSet.next()) {
                return resultSet.getInt(1) == 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error checking doctor availability: " + e.getMessage());
        }
        return false;
    }

    // Book the appointment
    private void bookAppointment(int patientId, int doctorId, String date) {
        int rowsAffected = db.executeUpdate("INSERT INTO appointments (patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)", patientId, doctorId, date);
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Appointment Booked Successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to book Appointment!");
        }
    }
}
