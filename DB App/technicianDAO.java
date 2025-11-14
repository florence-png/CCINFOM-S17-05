import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TechnicianDAO{
    public void addTechnician(int technicianId, String lastName, String firstName, String contactNumber, int donorId){ // Add a new technician
        String sql = "INSERT INTO technicians (technician_id, last_name, first_name, contact_number, donor_id) VALUES (?, ?, ?, ?, ?)";
        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, technicianId);
            stmt.setString(2, lastName);
            stmt.setString(3, firstName);
            stmt.setString(4, contactNumber);
            stmt.setInt(5, donorId);
            stmt.executeUpdate();
            System.out.println("Technician added successfully!");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Technician getTechnicianById(int technicianId){ // Get technician by ID
        String sql = "SELECT * FROM technicians WHERE technician_id=?";
        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, technicianId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Technician(
                        rs.getInt("technician_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("contact_number"),
                        rs.getInt("donor_id")
                );
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void updateTechnician(int technicianId, String lastName, String firstName, String contactNumber, int donorId){ // Update technician
        String sql = "UPDATE technicians SET last_name=?, first_name=?, contact_number=?, donor_id=? WHERE technician_id=?";
        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, lastName);
            stmt.setString(2, firstName);
            stmt.setString(3, contactNumber);
            stmt.setInt(4, donorId);
            stmt.setInt(5, technicianId);
            stmt.executeUpdate();
            System.out.println("Technician updated successfully!");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Technician> getAllTechnicians(){ // View all technicians
        List<Technician> list = new ArrayList<>();
        String sql = "SELECT * FROM technicians";
        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                list.add(new Technician(
                        rs.getInt("technician_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("contact_number"),
                        rs.getInt("donor_id")
                ));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public List<String[]> getTechnicianAssignments(){ // View technician assignments
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT t.technician_id, t.last_name, t.first_name, t.contact_number, " +
                     "d.donor_id, d.first_name AS donor_first, d.last_name AS donor_last " +
                     "FROM technicians t LEFT JOIN Donor d ON t.donor_id = d.donor_id";
        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                String[] row = {
                        String.valueOf(rs.getInt("technician_id")),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("contact_number"),
                        rs.getString("donor_first"),
                        rs.getString("donor_last")
                };
                list.add(row);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
