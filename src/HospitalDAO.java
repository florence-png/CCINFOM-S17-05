import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalDAO {
    // Add new hospital
    public void addHospital(String hospitalName, String street,
                               String city, String region, String contactNumber) {
        String sql = "INSERT INTO hospitals (hospital_id, hospital_name, street, city, region, contact_number, status)" +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

        	int nextId = 0;
            String query = "SELECT MAX(technician_id) FROM technicians";
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                nextId = rs.getInt(1) + 1;
            }
            
            stmt.setInt(1, nextId);
            stmt.setString(2, hospitalName);
            stmt.setString(3, street);
            stmt.setString(4, city);
            stmt.setString(5, region);
            stmt.setString(6, contactNumber);
            stmt.setString(7, "Operational");

            stmt.executeUpdate();
            System.out.println("Hospital added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to add hospital.");
        }
    }

    // Retrieve all hospitals
    public List<Hospital> getAllHospitals() {
        List<Hospital> list = new ArrayList<>();
        String sql = "SELECT * FROM hospitals";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while(rs.next()){
                Hospital h = new Hospital(
                    rs.getInt("hospital_id"),
                    rs.getString("hospital_name"),
                    rs.getString("street"),
                    rs.getString("city"),
                    rs.getString("contact_number"),
                    rs.getString("status")
                );
                list.add(h);
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    // Retrieve hospital by ID
    public Hospital getHospitalById(int id){
        String sql = "SELECT * FROM hospitals WHERE hospital_id=?";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return new Hospital(
                        rs.getInt("hospital_id"),
                        rs.getString("hospital_name"),
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("contact_number"),
                        rs.getString("status")
                );
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    // Update Hospital
    public void updateHospital(Hospital h) {
        String sql = "UPDATE hospitals SET hospital_name=?, street=?, city=?, contact_number=?, status=? WHERE hospital_id=?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, h.getHospitalName());
            stmt.setString(2, h.getStreet());
            stmt.setString(3, h.getCity());
            stmt.setString(4, h.getContactNumber());
            stmt.setString(5, h.getStatus());
            stmt.setInt(6, h.getHospitalId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete hospital
    public void deleteHospital(int id) {
        String sql = "DELETE FROM hospitals WHERE hospital_id=?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
