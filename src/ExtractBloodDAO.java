import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExtractBloodDAO {// DAO for blood extraction operations

	private static final Logger LOGGER = Logger.getLogger(ExtractBloodDAO.class.getName());

	public List<String> getExtractableAppointments() {// fetch appointments ready for extraction
		List<String> list = new ArrayList<>();
		String sql = "SELECT a.appointment_id, CONCAT(d.first_name, ' ', d.last_name) AS donor_name, "
				+ "b.branch_name, a.appointment_date, a.status "
				+ "FROM appointments a "
				+ "JOIN donors d ON a.donor_id = d.donor_id "
				+ "JOIN blood_banks b ON a.branch_id = b.branch_id "
				+ "WHERE a.technician_id IS NOT NULL AND a.status = 'Scheduled' "
				+ "ORDER BY a.appointment_date";

		try (Connection conn = DBConnector.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {// process each appointment
				int id = rs.getInt("appointment_id");
				String donor = rs.getString("donor_name");
				String branch = rs.getString("branch_name");
				Date date = rs.getDate("appointment_date");
				String status = rs.getString("status");
				String line = id + " | " + donor + " | " + branch + " | " + date + " | " + status;
				list.add(line);
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Database error fetching extractable appointments", e);
		}

		return list;
	}

	public boolean performExtraction(int appointmentId, String remark) {// execute blood extraction and update inventory
		String selectSql = "SELECT d.blood_type, a.appointment_date, a.donor_id, a.technician_id, a.branch_id "
				+ "FROM appointments a JOIN donors d ON a.donor_id = d.donor_id WHERE a.appointment_id = ?";

		try (Connection conn = DBConnector.getConnection();
			 PreparedStatement sel = conn.prepareStatement(selectSql)) {

			sel.setInt(1, appointmentId);
			try (ResultSet rs = sel.executeQuery()) {
				if (!rs.next()) return false;

				String bloodType = rs.getString("blood_type");
				Date appointDate = rs.getDate("appointment_date");
				int donorId = rs.getInt("donor_id");
				int techId = rs.getInt("technician_id");
				int branchId = rs.getInt("branch_id");

				if (techId == 0 || appointDate == null) return false;

				// Begin transaction
				conn.setAutoCommit(false);
				try {
					// Insert components
					insertInventoryRow(conn, bloodType, "red blood cells", appointDate, addDays(appointDate, 42), remark, donorId, techId, branchId);
					insertInventoryRow(conn, bloodType, "plasma", appointDate, addDays(appointDate, 365), remark, donorId, techId, branchId);
					insertInventoryRow(conn, bloodType, "platelets", appointDate, addDays(appointDate, 7), remark, donorId, techId, branchId);
					insertInventoryRow(conn, bloodType, "white blood cells", appointDate, addDays(appointDate, 1), remark, donorId, techId, branchId);

					// Update appointment status
					String upd = "UPDATE appointments SET status = 'Completed' WHERE appointment_id = ?";
					try (PreparedStatement psUpd = conn.prepareStatement(upd)) {
						psUpd.setInt(1, appointmentId);
						psUpd.executeUpdate();
					}

					conn.commit();
					return true;
				} catch (SQLException ex) {
					conn.rollback();
					LOGGER.log(Level.SEVERE, "Error during extraction transaction", ex);
					return false;
				} finally {
					conn.setAutoCommit(true);
				}
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Database error performing extraction", e);
			return false;
		}
	}

	private void insertInventoryRow(Connection conn, String bloodType, String component, Date receivedDate, Date expiryDate, String remark, int donorId, int techId, int branchId) throws SQLException {
		String ins = "INSERT INTO inventory (blood_type, component_type, received_date, expiry_date, remarks, donor_id, technician_id, branch_id, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'Available')";
		try (PreparedStatement ps = conn.prepareStatement(ins)) {
			ps.setString(1, bloodType);
			ps.setString(2, component);
			ps.setDate(3, receivedDate);
			ps.setDate(4, expiryDate);
			ps.setString(5, remark);
			ps.setInt(6, donorId);
			ps.setInt(7, techId);
			ps.setInt(8, branchId);
			ps.executeUpdate();
		}
	}

	private Date addDays(Date date, int days) {
		LocalDate ld = date.toLocalDate().plusDays(days);
		return Date.valueOf(ld);
	}
}
