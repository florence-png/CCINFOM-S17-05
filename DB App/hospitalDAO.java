import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalDAO{
    public boolean addHospital(String name, String address, String city, String contactNumber){ // Add a new hospital
        String sql = "INSERT INTO hospitals (name, address, city, contact_number, status) VALUES (?, ?, ?, ?, 'Active')";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, city);
            stmt.setString(4, contactNumber);

            return stmt.executeUpdate() > 0;

        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Hospital> getAllHospitals(){ // Get all active hospitals
        List<Hospital> list = new ArrayList<>();
        String sql = "SELECT * FROM hospitals WHERE status='Active'";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while(rs.next()){
                Hospital h = new Hospital(
                    rs.getInt("hospital_id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getString("contact_number")
                );
                list.add(h);
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public Hospital getHospitalById(int hospitalId){ // Get hospital details by ID
        String sql = "SELECT * FROM hospitals WHERE hospital_id=? AND status='Active'";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, hospitalId);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return new Hospital(
                    rs.getInt("hospital_id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getString("contact_number")
                );
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean archiveHospital(int hospitalId){ // Archive a hospital
        String sql = "UPDATE hospitals SET status='Archived' WHERE hospital_id=?";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, hospitalId);
            return stmt.executeUpdate() > 0;

        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Hospital> searchHospitals(String keyword){ // Search hospitals by name
        List<Hospital> list = new ArrayList<>();
        String sql = "SELECT * FROM hospitals WHERE status='Active' AND name LIKE ?";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, "%" + keyword + "%");

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Hospital h = new Hospital(
                    rs.getInt("hospital_id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getString("contact_number")
                );
                list.add(h);
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public List<Request> getRequestHistory(int hospitalId){ // Get request history for a hospital
        List<Request> list = new ArrayList<>();
        String sql = "SELECT * FROM hospital_requests WHERE hospital_id=? ORDER BY request_date DESC";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, hospitalId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Request r = new Request(
                    rs.getInt("request_id"),
                    rs.getInt("hospital_id"),
                    rs.getString("blood_type"),
                    rs.getString("component_type"),
                    rs.getInt("quantity"),
                    rs.getString("status"),
                    rs.getDate("request_date")
                );
                list.add(r);
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}