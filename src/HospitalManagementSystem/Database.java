package HospitalManagementSystem;

import java.sql.*;

public class Database {
    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String name = "root";
    private static final String password = "Faizan@2006";
    private Connection conn;

    // Constructor for setting up the connection
    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, name, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method to insert data (Add Patient/Doctor) with PreparedStatement
    public int executeUpdate(String query, Object... params) {
        int affectedRows = 0;
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            setParameters(pstmt, params);
            affectedRows = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    // Method to retrieve data (View Patients/Doctors) with PreparedStatement
    public ResultSet executeQuery(String query, Object... params) {
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            setParameters(pstmt, params);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // Helper method to set parameters for PreparedStatement
    private void setParameters(PreparedStatement pstmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }
    }

    // Close connection
    public void close() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
