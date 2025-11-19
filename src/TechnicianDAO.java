import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TechnicianDAO {

    // Add new technician
    public void addTechnician(String lastName, String firstName,
                              String technicianEmail, String contactNumber,
                              int age, char sex,
                              java.util.Date dateEmployed) {

        String sql = "INSERT INTO technicians " +
                "(last_name, first_name, technician_email, contact_number, " +
                "age, sex, date_employed, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnector.getConnection()){
            int nextId = 0;
            String getIdQuery = "SELECT MAX(technician_id) FROM technicians";
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(getIdQuery);
                if(rs.next()){
                    nextId = rs.getInt(1) + 1;
                }
            }

            String insertSql = "INSERT INTO technicians (technician_id, last_name, first_name, technician_email, contact_number, age, sex, date_employed, status) "
                             + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try(PreparedStatement stmt = conn.prepareStatement(insertSql)){
                stmt.setInt(1, nextId);
                stmt.setString(2, lastName);
                stmt.setString(3, firstName);
                stmt.setString(4, technicianEmail);
                stmt.setString(5, contactNumber);
                stmt.setInt(6, age);
                stmt.setString(7, String.valueOf(sex));
                stmt.setDate(8, new java.sql.Date(dateEmployed.getTime()));
                stmt.setString(9, "Active");

                stmt.executeUpdate();
                System.out.println("Technician added successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to add technician.");
        }
    }


    // Retrieve all technicians
    public List<Technician> getAllTechnicians() {
        List<Technician> list = new ArrayList<>();
        String sql = "SELECT * FROM technicians";

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
        String sql = "SELECT * FROM technicians WHERE technician_id=?";

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
        String sql = "UPDATE technicians SET last_name=?, first_name=?, technician_email=?, " +
                "contact_number=?, age=?, sex=?, date_employed=?, status=? WHERE technician_id=?";

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
        String sql = "DELETE FROM technicians WHERE technician_id=?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
