import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalDAO {
    // Add new hospital
    public void addHospital(String hospitalName, String street,
                               String city, String contactNumber) {
        String sql = "INSERT INTO hospitals (name, street, city, contact_number, status)" +
                     "VALUES (?, ?, ?, ?, ?)";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, hospitalName);
            stmt.setString(2, street);
            stmt.setString(3, city);
            stmt.setString(4, contactNumber);
            stmt.setString(5, "Operational");

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
//    public boolean archiveHospital(int hospitalId){ // Archive a hospital
//        String sql = "UPDATE hospitals SET status='Archived' WHERE hospital_id=?";
//
//        try(Connection conn = DBConnector.getConnection();
//            PreparedStatement stmt = conn.prepareStatement(sql)){
//
//            stmt.setInt(1, hospitalId);
//            return stmt.executeUpdate() > 0;
//
//        }
//        catch(SQLException e){
//            e.printStackTrace();
//            return false;
//        }
//    }

//    public List<Hospital> searchHospitals(String keyword){ // Search hospitals by name
//        List<Hospital> list = new ArrayList<>();
//        String sql = "SELECT * FROM hospitals WHERE status='Active' AND name LIKE ?";
//
//        try(Connection conn = DBConnector.getConnection();
//            PreparedStatement stmt = conn.prepareStatement(sql)){
//
//            stmt.setString(1, "%" + keyword + "%");
//
//            ResultSet rs = stmt.executeQuery();
//
//            while(rs.next()){
//                Hospital h = new Hospital(
//                    rs.getInt("hospital_id"),
//                    rs.getString("name"),
//                    rs.getString("street"),
//                    rs.getString("city"),
//                    rs.getString("contact_number")
//                );
//                list.add(h);
//            }
//
//        }
//        catch(SQLException e){
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public List<Request> getRequestHistory(int hospitalId){ // Get request history for a hospital
//        List<Request> list = new ArrayList<>();
//        String sql = "SELECT * FROM hospital_requests WHERE hospital_id=? ORDER BY request_date DESC";
//
//        try(Connection conn = DBConnector.getConnection();
//            PreparedStatement stmt = conn.prepareStatement(sql)){
//
//            stmt.setInt(1, hospitalId);
//            ResultSet rs = stmt.executeQuery();
//
//            while(rs.next()){
//                Request r = new Request(
//                    rs.getInt("request_id"),
//                    rs.getInt("hospital_id"),
//                    rs.getString("blood_type"),
//                    rs.getString("component_type"),
//                    rs.getInt("quantity"),
//                    rs.getString("status"),
//                    rs.getDate("request_date")
//                );
//                list.add(r);
//            }
//
//        }
//        catch(SQLException e){
//            e.printStackTrace();
//        }
//        return list;
//    }
