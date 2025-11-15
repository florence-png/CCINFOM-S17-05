import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TechnicianDAO{
    public void addTechnician(String lastName, String firstName, String contactNumber, Integer donorId){ // donorId can be null
        String sql = "INSERT INTO technicians (last_name, first_name, contact_number, donor_id, status) " + "VALUES (?, ?, ?, ?, 'Active')";
        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, lastName);
            stmt.setString(2, firstName);
            stmt.setString(3, contactNumber);

            if(donorId == null){
                stmt.setNull(4, Types.INTEGER);
            }
            else{
                stmt.setInt(4, donorId);
            }
            
            stmt.executeUpdate();
            System.out.println("Technician added successfully!");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Technician> getAllTechnicians(){ // Active and Inactive only
        List<Technician> list = new ArrayList<>();
        String sql = "SELECT * FROM technicians WHERE status != 'Deleted'";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                Technician t = new Technician(
                    rs.getInt("technician_id"),
                    rs.getString("last_name"),
                    rs.getString("first_name"),
                    rs.getString("contact_number"),
                    rs.getObject("donor_id") == null ? null : rs.getInt("donor_id"),
                    rs.getString("status")
                );
                list.add(t);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public void setTechnicianStatus(int technicianId, String newStatus){ // Active, Inactive, Deleted status
        String sql = "UPDATE technicians SET status = ? WHERE technician_id = ?";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, newStatus);
            stmt.setInt(2, technicianId);
            stmt.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Technician> searchTechnicians(String keyword){ // search by first name or last name
        List<Technician> list = new ArrayList<>();
        String sql = "SELECT * FROM technicians WHERE status != 'Deleted' AND (first_name LIKE ? OR last_name LIKE ?)";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
        
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Technician t = new Technician(
                    rs.getInt("technician_id"),
                    rs.getString("last_name"),
                    rs.getString("first_name"),
                    rs.getString("contact_number"),
                    rs.getInt("donor_id"),
                    rs.getString("status")
                );
                list.add(t);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
