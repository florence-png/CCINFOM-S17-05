import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

public class AssignTechController implements ActionListener {

	private AssignTechGUI gui;// GUI reference
	private AssignTechDAO dao;// DAO for DB ops
	private JPanel mainCardPanel;// Main panel reference

	public AssignTechController(AssignTechGUI gui, JPanel mainCardPanel) {
		this.gui = gui;
		this.dao = new AssignTechDAO(); // DAO for DB ops
		this.mainCardPanel = mainCardPanel;
		addListeners();
		populateData();
		// Refresh lists whenever this card becomes visible
		gui.addComponentListener(new java.awt.event.ComponentAdapter(){@Override public void componentShown(java.awt.event.ComponentEvent e){populateData();}});
	}

	private void addListeners() {
		gui.getConfirmButton().addActionListener(this);
		gui.getReturnButton().addActionListener(this);
	}

	private void populateData() {
		List<Technician> techs = dao.getActiveTechnicians(); // load active techs
		gui.populateTechnicians(techs);

		List<String> appts = dao.getUnassignedScheduledAppointments(); // load unassigned appts
		gui.populateAppointments(appts);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

			switch (cmd) {
				case "BTN_CONFIRM_ASSIGN": { // confirm assignment
					Integer techId = gui.getSelectedTechnicianId(); // chosen tech
					Integer apptId = gui.getSelectedAppointmentId(); // chosen appt
					if (techId == null || apptId == null) { // validate
						JOptionPane.showMessageDialog(gui, "Select both a technician and an appointment.", "Validation", JOptionPane.WARNING_MESSAGE);
						return;
					}

					boolean ok = dao.assignTechnicianToAppointment(techId, apptId); // perform update
					if (ok) {
						JOptionPane.showMessageDialog(gui, "Technician assigned successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
						gui.populateAppointments(dao.getUnassignedScheduledAppointments()); // refresh
					} else {
						JOptionPane.showMessageDialog(gui, "Failed to assign technician. The appointment may already have been assigned.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}

				case "BTN_RETURN_TO_TRANSACTIONS": { // return to transactions
					CardLayout cl = (CardLayout) (mainCardPanel.getLayout());
					cl.show(mainCardPanel, "TRANSACTIONS_GUI");
					break;
				}

				default: // fallback
					System.out.println("Unknown command: " + cmd);
					break;
			}
	}
}
