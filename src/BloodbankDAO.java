import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BloodbankDAO {
    // Add new bloodbank
    public void addBloodbank(String branchName, String street,
                             String city, String contactNumber) {
        String sql = "INSERT INTO blood_banks (name, street, city, contact_number, status)" +
                "VALUES (?, ?, ?, ?, ?)";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, branchName);
            stmt.setString(2, street);
            stmt.setString(3, city);
            stmt.setString(4, contactNumber);
            stmt.setString(5, "Operational");

            stmt.executeUpdate();
            System.out.println("Blood bank added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to add blood bank.");
        }
    }

    // Retrieve all bloodbanks
    public List<Bloodbank> getAllBloodbanks() {
        List<Bloodbank> list = new ArrayList<>();
        String sql = "SELECT * FROM blood_banks";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while(rs.next()){
                Bloodbank h = new Bloodbank(
                        rs.getInt("branch_id"),
                        rs.getString("branch_name"),
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

    // Retrieve branch by ID
    public Bloodbank getBloodbankById(int id){
        String sql = "SELECT * FROM blood_banks WHERE branch_id=?";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return new Bloodbank(
                        rs.getInt("branch_id"),
                        rs.getString("branch_name"),
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

    // Update bloodbank
    public void updateBloodbank(Bloodbank h) {
        String sql = "UPDATE blood_banks SET branch_name=?, street=?, city=?, contact_number=?, status=? WHERE branch_id=?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, h.getBranchName());
            stmt.setString(2, h.getStreet());
            stmt.setString(3, h.getCity());
            stmt.setString(4, h.getContactNumber());
            stmt.setString(5, h.getStatus());
            stmt.setInt(6, h.getBranchId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete bloodbank
    public void deleteBloodbank(int id) {
        String sql = "DELETE FROM blood_banks WHERE branch_id=?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
