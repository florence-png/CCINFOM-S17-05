import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/Bloodbankdb?useSSL=false&serverTimezone=UTC";


    private static final String USER = "root";
    private static final String PASS = "password"; // put password here

    /**
     * Attempts to establish a connection to the database.
     * @return a Connection object
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
            throw new SQLException("JDBC Driver not found", e);
        }

        // Open a connection
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
