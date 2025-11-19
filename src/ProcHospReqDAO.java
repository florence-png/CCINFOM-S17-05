import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO for processing hospital requests.
 */
public class ProcHospReqDAO {

	private static final Logger LOGGER = Logger.getLogger(ProcHospReqDAO.class.getName());

	// Return pending hospital requests formatted for display
	public List<String> getPendingRequests() {
		List<String> list = new ArrayList<>();
		String sql = "SELECT r.request_id, b.branch_name, r.blood_type, r.component_requested, r.units_requested, r.request_date, r.status "
				+ "FROM hospital_request r JOIN blood_banks b ON r.branch_id = b.branch_id "
				+ "WHERE r.status = 'Pending' ORDER BY r.request_date";

		try (Connection conn = DBConnector.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("request_id");
				String branch = rs.getString("branch_name");
				String blood = rs.getString("blood_type");
				String comp = rs.getString("component_requested");
				int units = rs.getInt("units_requested");
				Date date = rs.getDate("request_date");
				String status = rs.getString("status");
				String row = id + " | " + branch + " | " + blood + " | " + comp + " | " + units + " | " + date + " | " + status;
				list.add(row);
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "DB error fetching pending requests", e);
		}

		return list;
	}

	/**
	 * Process a hospital request: find available inventory matching the
	 * request (branch, blood type, component), mark up to requested units
	 * as 'Used', then update the request status to 'Fulfilled' or 'Cancelled'.
	 * Returns the new status string or null on error.
	 */
	public String processRequest(int requestId) {
		String selectReq = "SELECT branch_id, blood_type, component_requested, units_requested FROM hospital_request WHERE request_id = ? FOR UPDATE";

		try (Connection conn = DBConnector.getConnection();
			 PreparedStatement pr = conn.prepareStatement(selectReq)) {

			conn.setAutoCommit(false);
			pr.setInt(1, requestId);
			try (ResultSet rs = pr.executeQuery()) {
				if (!rs.next()) { conn.rollback(); return null; }

				int branchId = rs.getInt("branch_id");
				String blood = rs.getString("blood_type");
				String comp = rs.getString("component_requested");
				int unitsRequested = rs.getInt("units_requested");

				// Find available inventory rows ordered by expiry
				String selInv = "SELECT inventory_id FROM inventory WHERE branch_id = ? AND status = 'Available' AND blood_type = ? AND component_type = ? ORDER BY expiry_date ASC";
				try (PreparedStatement psi = conn.prepareStatement(selInv)) {
					psi.setInt(1, branchId);
					psi.setString(2, blood);
					psi.setString(3, comp);
					try (ResultSet r2 = psi.executeQuery()) {
						List<Integer> ids = new ArrayList<>();
						while (r2.next() && ids.size() < unitsRequested) ids.add(r2.getInt("inventory_id"));

						int found = ids.size();

						// If any found, mark them Used
						if (found > 0) {
							// Build IN clause
							StringBuilder sb = new StringBuilder();
							sb.append("UPDATE inventory SET status = 'Used' WHERE inventory_id IN (");
							for (int i = 0; i < ids.size(); i++) {
								if (i > 0) sb.append(',');
								sb.append('?');
							}
							sb.append(')');
							try (PreparedStatement pup = conn.prepareStatement(sb.toString())) {
								for (int i = 0; i < ids.size(); i++) pup.setInt(i + 1, ids.get(i));
								pup.executeUpdate();
							}
						}

						// Update request status depending on fulfillment
						String newStatus = (found >= unitsRequested) ? "Fulfilled" : "Cancelled";
						String updReq = "UPDATE hospital_request SET status = ?, date_fulfilled = CASE WHEN ? = 'Fulfilled' THEN CURDATE() ELSE NULL END WHERE request_id = ?";
						try (PreparedStatement pru = conn.prepareStatement(updReq)) {
							pru.setString(1, newStatus);
							pru.setString(2, newStatus);
							pru.setInt(3, requestId);
							pru.executeUpdate();
						}

						conn.commit();
						return newStatus; // return status to caller
					}
				}
			} catch (SQLException ex) {
				conn.rollback();
				LOGGER.log(Level.SEVERE, "Error processing request", ex);
				return null;
			} finally {
				conn.setAutoCommit(true);
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "DB error processing hospital request", e);
			return null;
		}
	}
}
