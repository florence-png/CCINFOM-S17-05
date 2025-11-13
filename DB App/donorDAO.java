import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

public class DonorDAO{ // Adds a new donor to the database
    public void addDonor(String lastName, String firstName, String contactInfo, int age, char sex, java.util.Date dateOfBirth, String bloodType, String remarks){
        String sql = "INSERT INTO Donor (last_name, first_name, contact_info, age, sex, date_of_birth, blood_type, remarks) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, lastName);
            stmt.setString(2, firstName);
            stmt.setString(3, contactInfo);
            stmt.setInt(4, age);
            stmt.setString(5, String.valueOf(sex)); // CHAR(1)
            stmt.setDate(6, new Date(dateOfBirth.getTime())); // convert java.util.Date → java.sql.Date
            stmt.setString(7, bloodType);
            stmt.setString(8, remarks);

            stmt.executeUpdate();
            System.out.println("Donor added successfully!");

        }
        catch(SQLException e){
            e.printStackTrace();
            System.err.println("Failed to add donor.");
        }
    }
}

public void updateDonor(int donorId, String lastName, String firstName, String contactInfo, int age, char sex, java.util.Date dateOfBirth, String bloodType, String remarks){ // Update donor record
    String sql = "UPDATE Donor SET last_name=?, first_name=?, contact_info=?, age=?, sex=?, date_of_birth=?, blood_type=?, remarks=? WHERE donor_id=?";

    try(Connection conn = DBConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

        stmt.setString(1, lastName);
        stmt.setString(2, firstName);
        stmt.setString(3, contactInfo);
        stmt.setInt(4, age);
        stmt.setString(5, String.valueOf(sex));
        stmt.setDate(6, new Date(dateOfBirth.getTime()));
        stmt.setString(7, bloodType);
        stmt.setString(8, remarks);
        stmt.setInt(9, donorId);

        stmt.executeUpdate();
        System.out.println("Donor updated successfully!");
    }
    catch(SQLException e){
        e.printStackTrace();
        System.err.println("Failed to update donor.");
    }
}

public Donor getDonorById(int donorId){ // Search donor by ID
    String sql = "SELECT * FROM Donor WHERE donor_id = ?";
    try(Connection conn = DBConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

        stmt.setInt(1, donorId);
        ResultSet rs = stmt.executeQuery();

        if(rs.next()){
            return new Donor(
                rs.getInt("donor_id"),
                rs.getString("last_name"),
                rs.getString("first_name"),
                rs.getString("contact_info"),
                rs.getInt("age"),
                rs.getString("sex").charAt(0),
                rs.getDate("date_of_birth"),
                rs.getString("blood_type"),
                rs.getString("remarks")
            );
        }
    }
    catch(SQLException e){
        e.printStackTrace();
    }
    return null;
}

public List<Donor> searchDonorByName(String name){ // Search donors by name
    String sql = "SELECT * FROM Donor WHERE last_name LIKE ? OR first_name LIKE ?";
    List<Donor> donors = new ArrayList<>();

    try (Connection conn = DBConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, "%" + name + "%");
        stmt.setString(2, "%" + name + "%");

        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            donors.add(new Donor(
                rs.getInt("donor_id"),
                rs.getString("last_name"),
                rs.getString("first_name"),
                rs.getString("contact_info"),
                rs.getInt("age"),
                rs.getString("sex").charAt(0),
                rs.getDate("date_of_birth"),
                rs.getString("blood_type"),
                rs.getString("remarks")
            ));
        }
    }
    catch(SQLException e){
        e.printStackTrace();
    }
    return donors;
}

public List<Donor> getAllDonors() { // Show all donors
    String sql = "SELECT * FROM Donor";
    List<Donor> donors = new ArrayList<>();

    try(Connection conn = DBConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()){

        while(rs.next()){
            donors.add(new Donor(
                rs.getInt("donor_id"),
                rs.getString("last_name"),
                rs.getString("first_name"),
                rs.getString("contact_info"),
                rs.getInt("age"),
                rs.getString("sex").charAt(0),
                rs.getDate("date_of_birth"),
                rs.getString("blood_type"),
                rs.getString("remarks")
            ));
        }
    }
    catch(SQLException e){
        e.printStackTrace();
    }
    return donors;
}

