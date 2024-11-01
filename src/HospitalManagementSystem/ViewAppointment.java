package HospitalManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewAppointment extends JFrame {
    private JTable table;

    public ViewAppointment() {
        setTitle("View Appointments");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main content panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 102, 204)); // Blue header color
        headerPanel.setPreferredSize(new Dimension(0, 60));
        JLabel headerLabel = new JLabel("View Appointments");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Header font size
        headerPanel.add(headerLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Center panel for the table with padding
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Table model and table setup
        String[] columns = {"Appointment ID", "Patient ID", "Doctor ID", "Appointment Date"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table = new JTable(model);
        table.setFillsViewportHeight(true); // Make the table fill the scroll pane height

        // Set font for table rows
        table.setFont(new Font("Arial", Font.PLAIN, 16)); // Change font size for table rows

        // Set font for table headers
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 18)); // Change font size for table headers

        // Scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove border around the scroll pane
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Add center panel to the main panel
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Fetch and display appointments from the database
        loadAppointments(model);
    }

    // Method to load appointments from the database
    private void loadAppointments(DefaultTableModel model) {
        Database db = new Database();
        ResultSet rs = db.executeQuery("SELECT * FROM appointments");

        try {
            while (rs.next()) {
                int appointmentId = rs.getInt("id");
                int patientId = rs.getInt("patient_id");
                int doctorId = rs.getInt("doctor_id");
                String appointmentDate = rs.getString("appointment_date");

                model.addRow(new Object[]{appointmentId, patientId, doctorId, appointmentDate});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading appointments: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            db.close();
        }
    }
}
