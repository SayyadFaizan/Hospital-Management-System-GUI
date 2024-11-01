package HospitalManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewDoctorsForm extends JFrame {
    private JTable table;

    public ViewDoctorsForm() {
        setTitle("View Doctors");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main content panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 102, 204)); // Blue header color
        headerPanel.setPreferredSize(new Dimension(0, 60));
        JLabel headerLabel = new JLabel("View Doctors");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Header font size
        headerPanel.add(headerLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Center panel for the table with padding
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Table model and table setup
        String[] columnNames = {"ID", "Name", "Specialization", "Phone"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
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

        // Fetch and display doctors from the database
        loadDoctors(model);
    }

    // Method to load doctors from the database
    private void loadDoctors(DefaultTableModel model) {
        Database db = new Database();
        ResultSet rs = db.executeQuery("SELECT * FROM doctors");

        try {
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String specialization = rs.getString("specialization");
                String phone = rs.getString("phone");

                model.addRow(new Object[]{id, name, specialization, phone});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading doctors: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            db.close();
        }
    }
}
