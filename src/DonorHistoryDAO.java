import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonorHistoryDAO{
    public List<String> getDonationHistory(int donorId){
        List<String> history = new ArrayList<>();

        String sql = "SELECT a.appointment_date, b.branch_name " + "FROM appointments a " + "JOIN blood_banks b ON a.branch_id = b.branch_id " + "WHERE a.donor_id = ? " + "ORDER BY a.appointment_date DESC";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, donorId);

            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    Date date = rs.getDate("appointment_date");
                    String branch = rs.getString("branch_name");

                    String line = "Donated on " + date + " at " + branch;
                    history.add(line);
                }
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        return history;
    }

}

