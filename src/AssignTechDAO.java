import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AssignTechDAO {

	private static final Logger LOGGER = Logger.getLogger(AssignTechDAO.class.getName());// logger

	public List<Technician> getActiveTechnicians() {// return active technicians
		List<Technician> list = new ArrayList<>();
		String sql = "SELECT * FROM technicians WHERE status = 'Active'";

		try (Connection conn = DBConnector.getConnection();// get DB connection
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {// iterate results
				Technician t = new Technician(
						rs.getInt("technician_id"),
						rs.getString("last_name"),
						rs.getString("first_name"),
						rs.getString("technician_email"),
						rs.getString("contact_number"),
						rs.getInt("age"),
						rs.getString("sex").charAt(0),
						rs.getDate("date_employed"),
						rs.getString("status")
				);
				list.add(t);
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Database error fetching technicians", e);
		}

		return list;
	}

	public List<String> getUnassignedScheduledAppointments() { // return unassigned scheduled appts
		List<String> list = new ArrayList<>();
		String sql = "SELECT a.appointment_id, CONCAT(d.first_name, ' ', d.last_name) AS donor_name, "
				+ "b.branch_name, a.appointment_date, a.status "
				+ "FROM appointments a "
				+ "JOIN donors d ON a.donor_id = d.donor_id "
				+ "JOIN blood_banks b ON a.branch_id = b.branch_id "
				+ "WHERE a.technician_id IS NULL AND a.status = 'Scheduled' "
				+ "ORDER BY a.appointment_date";

		try (Connection conn = DBConnector.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {// iterate results
				int id = rs.getInt("appointment_id");
				String donor = rs.getString("donor_name");
				String branch = rs.getString("branch_name");
				Date date = rs.getDate("appointment_date");
				String status = rs.getString("status");

				String line = id + " | " + donor + " | " + branch + " | " + date + " | " + status;
				list.add(line);
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Database error fetching appointments", e);
		}

		return list;
	}

	public boolean assignTechnicianToAppointment(int technicianId, int appointmentId) { // assign tech to appointment
		String sql = "UPDATE appointments SET technician_id = ? "
				+ "WHERE appointment_id = ? AND technician_id IS NULL AND status = 'Scheduled'";

		try (Connection conn = DBConnector.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, technicianId);
			ps.setInt(2, appointmentId);
			int affected = ps.executeUpdate();
			return affected > 0;
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Database error assigning technician", e);
			return false;
		}
	}
}
