import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonorDAO{

    // Adds new donor
    public void addDonor(String lastName, String firstName, String donorEmail,
                         String contactNumber, int age, char sex, java.util.Date birthdate,
                         String bloodType, String remarks) {
        String sql = "INSERT INTO Donor " +
                     "(last_name, first_name, donor_email, contact_info, age, sex, " +
                     "date_of_birth, blood_type, remarks, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, lastName);
            stmt.setString(2, firstName);
            stmt.setString(3, donorEmail);
            stmt.setString(4, contactNumber);
            stmt.setInt(5, age);
            stmt.setString(6, String.valueOf(sex));
            stmt.setDate(7, new java.sql.Date(birthdate.getTime()));
            stmt.setString(8, bloodType);
            stmt.setString(9, remarks);
            stmt.setString(10, "Active");

            stmt.executeUpdate();
            System.out.println("Donor added successfully!");

        }
        catch(SQLException e){
            e.printStackTrace();
            System.err.println("Failed to add donor.");
        }
    }

    // Retrieve all donors
    public List<Donor> getAllDonors(){
        List<Donor> list = new ArrayList<>();
        String sql = "SELECT * FROM donors";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while (rs.next()) {
                Donor d = new Donor(
                        rs.getInt("donor_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("donor_email"),
                        rs.getString("contact_number"),
                        rs.getInt("age"),
                        rs.getString("sex").charAt(0),
                        rs.getDate("birthdate"),
                        rs.getString("blood_type"),
                        rs.getString("remarks"),
                        rs.getString("status")
                );
                list.add(d);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public Donor getDonorById(int id){
        String sql = "SELECT * FROM donors WHERE donor_id=?";
        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return new Donor(
                        rs.getInt("donor_id"),
                        rs.getString("last_name"),
                        rs.getString("first_name"),
                        rs.getString("donor_email"),
                        rs.getString("contact_number"),
                        rs.getInt("age"),
                        rs.getString("sex").charAt(0),
                        rs.getDate("birthdate"),
                        rs.getString("blood_type"),
                        rs.getString("remarks"),
                        rs.getString("status")
                );
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    // Update donor
    public void updateDonor(Donor d){
        String sql = "UPDATE Donor SET last_name=?, first_name=?, donor_email=?, " +
                     "contact_number=?, age=?, sex=?, " +
                     "birthdate=?, blood_type=?, remarks=?, status=? WHERE donor_id=?";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, d.getLastName());
            stmt.setString(2, d.getFirstName());
            stmt.setString(3, d.getDonorEmail());
            stmt.setString(4, d.getContactNumber());
            stmt.setInt(5, d.getAge());
            stmt.setString(6, String.valueOf(d.getSex()));
            stmt.setDate(7, d.getBirthdate());
            stmt.setString(8, d.getBloodType());
            stmt.setString(9, d.getRemarks());
            stmt.setString(10, d.getStatus());
            stmt.setInt(11, d.getDonorId());

            stmt.executeUpdate();
        }
        catch
        (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteDonor(int id){
        String sql = "DELETE FROM donors WHERE donor_id=?";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
