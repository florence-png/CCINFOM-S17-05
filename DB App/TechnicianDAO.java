import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TechnicianDAO {

    // Add new technician
    public void addTechnician(String lastName, String firstName,
                              String technicianEmail, String contactNumber,
                              int age, char sex,
                              java.util.Date dateEmployed) {

        String sql = "INSERT INTO Technician " +
                "(last_name, first_name, technician_email, contact_number, " +
                "age, sex, date_employed, status) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, lastName);
            stmt.setString(2, firstName);
            stmt.setString(3, technicianEmail);
            stmt.setString(4, contactNumber);
            stmt.setInt(5, age);
            stmt.setString(6, String.valueOf(sex));
            stmt.setDate(7, new java.sql.Date(dateEmployed.getTime()));

            stmt.executeUpdate();
            System.out.println("Technician added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to add technician.");
        }
    }


    // Retrieve all technicians
    public List<Technician> getAllTechnicians() {
        List<Technician> list = new ArrayList<>();
        String sql = "SELECT * FROM Technician";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Technician t =  new Technician(
                        rs.getInt("technician_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("technician_email"),
                        rs.getString("contact_number"),
                        rs.getInt("age"),
                        rs.getString("sex").charAt(0),
                        rs.getDate("date_employed"),
                        rs.getString("status")
                );
                list.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // Retrieve technician by ID
    public Technician getTechnicianById(int id) {
        String sql = "SELECT * FROM Technician WHERE technician_id=?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Technician(
                        rs.getInt("technician_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("technician_email"),
                        rs.getString("contact_number"),
                        rs.getInt("age"),
                        rs.getString("sex").charAt(0),
                        rs.getDate("date_employed"),
                        rs.getString("status")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    // Update technician
    public void updateTechnician(Technician t) {
        String sql = "UPDATE Technician SET last_name=?, first_name=?, technician_email=?, " +
                "contact_number=?, date_employed=? WHERE technician_id=?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getLastName());
            stmt.setString(2, t.getFirstName());
            stmt.setString(3, t.getTechnicianEmail());
            stmt.setString(4, t.getContactNumber());
            stmt.setInt(5, t.getAge());
            stmt.setString(6, String.valueOf(t.getSex()));
            stmt.setDate(7, t.getDateEmployed());
            stmt.setString(8, t.getStatus());
            stmt.setInt(9, t.getTechnicianId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Delete technician
    public void deleteTechnician(int id) {
        String sql = "DELETE FROM Technician WHERE technician_id=?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}