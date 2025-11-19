import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExtractionHistoryDAO{
    public List<String> getTechnicianHistory(int technicianId) {
        List<String> history = new ArrayList<>();

        String sql = "SELECT a.appointment_date, b.branch_name " + "FROM appointments a " + "JOIN blood_banks b ON a.branch_id = b.branch_id " + "WHERE a.technician_id = ? " + "ORDER BY a.appointment_date DESC";

        try(Connection conn = DBConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, technicianId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String date = rs.getString("appointment_date");
                String branch = rs.getString("branch_name");
                history.add("Extracted Blood on " + date + " at " + branch);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        if(history.isEmpty()){
            history.add("No extraction history available.");
        }
        return history;
    }
}