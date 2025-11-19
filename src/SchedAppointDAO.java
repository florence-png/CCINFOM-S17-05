import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SchedAppointDAO {

	/**
	 * Schedule an appointment
	 */
	public int scheduleAppointment(int donorId, int branchId, java.sql.Date appointmentDate) {
		String sql = "INSERT INTO appointments (donor_id, branch_id, appointment_date, status) VALUES (?, ?, ?, ?)";
		try (Connection conn = DBConnector.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {// prepare statement to get generated keys
			ps.setInt(1, donorId);// set donor ID
			ps.setInt(2, branchId);// set branch ID
			ps.setDate(3, appointmentDate);// set appointment date
			ps.setString(4, "Scheduled");// set status
			int affected = ps.executeUpdate();// execute update
			if (affected == 0) return -1;// check if insert was successful
			try (ResultSet keys = ps.getGeneratedKeys()) {
				if (keys.next()) {
					return keys.getInt(1);// return generated appointment ID
				}
			}
		} catch (SQLException e) {
			Logger.getLogger(SchedAppointDAO.class.getName()).log(Level.SEVERE, "Database error", e);
		}
		return -1;
	}

	/**
	 * Return a list of formatted appointment rows for a donor
	 */
	public List<String> getAppointmentsForDonor(int donorId) {// return formatted appointment list
		List<String> list = new ArrayList<>();
		String sql = "SELECT a.appointment_id, CONCAT(d.first_name, ' ', d.last_name) AS donor_name, "
				+ "b.branch_name, a.appointment_date, a.status "
				+ "FROM appointments a "
				+ "JOIN donors d ON a.donor_id = d.donor_id "
				+ "JOIN blood_banks b ON a.branch_id = b.branch_id "
				+ "WHERE a.donor_id = ? "
				+ "ORDER BY a.appointment_date DESC";

		try (Connection conn = DBConnector.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, donorId);// set donor ID parameter
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("appointment_id");
					String donorName = rs.getString("donor_name");
					String branch = rs.getString("branch_name");
					Date date = rs.getDate("appointment_date");
					String status = rs.getString("status");

					String line = id + " | " + donorName + " | " + branch + " | " + date + " | " + status;// format appointment line
					list.add(line);// add to list
				}
			}

		} catch (SQLException e) {
			Logger.getLogger(SchedAppointDAO.class.getName()).log(Level.SEVERE, "Database error", e);
		}
		return list;
	}

	/**
	 * Return active branches (operational) so the UI can present branch choices
	 */
	public List<Bloodbank> getOperationalBranches() {
		List<Bloodbank> list = new ArrayList<>();
		String sql = "SELECT * FROM blood_banks WHERE status = 'Operational'";

		try (Connection conn = DBConnector.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Bloodbank b = new Bloodbank(
						rs.getInt("branch_id"),
						rs.getString("branch_name"),
						rs.getString("street"),
						rs.getString("city"),
						rs.getString("contact_number"),
						rs.getString("status")
				);
				list.add(b);
			}
		} catch (SQLException e) {
			Logger.getLogger(SchedAppointDAO.class.getName()).log(Level.SEVERE, "Database error", e);
		}

		return list;
	}

	/**
	 * Return eligible donors for scheduling (Active and age >= 18)
	 */
	public List<Donor> getEligibleDonors() {
		List<Donor> list = new ArrayList<>();
		String sql = "SELECT * FROM donors WHERE status = 'Active' AND age >= 18";

		try (Connection conn = DBConnector.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

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

		} catch (SQLException e) {
			Logger.getLogger(SchedAppointDAO.class.getName()).log(Level.SEVERE, "Database error", e);
		}
		return list;
	}
}
