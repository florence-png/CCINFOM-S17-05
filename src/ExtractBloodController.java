import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

public class ExtractBloodController implements ActionListener {

	private final ExtractBloodGUI gui;
	private final ExtractBloodDAO dao;
	private final JPanel mainCardPanel;

	public ExtractBloodController(ExtractBloodGUI gui, JPanel mainCardPanel) {
		this.gui = gui;
		this.dao = new ExtractBloodDAO();
		this.mainCardPanel = mainCardPanel;
		addListeners();
		populate();
		// Ensure appointments list is current whenever this card shows
		gui.addComponentListener(new java.awt.event.ComponentAdapter(){@Override public void componentShown(java.awt.event.ComponentEvent e){populate();}});
	}

	private void addListeners() {
		gui.getConfirmButton().addActionListener(this);
		gui.getReturnButton().addActionListener(this);
	}

	private void populate() {
		List<String> appts = dao.getExtractableAppointments(); // load scheduled+assigned appts
		gui.populateAppointments(appts); // fill combo
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
			switch (cmd) {
				case "BTN_CONFIRM_EXTRACT": { // confirm extraction
					Integer apptId = gui.getSelectedAppointmentId(); // chosen appt
					String remark = gui.getSelectedRemark(); // chosen remark
					if (apptId == null) { // validate
						JOptionPane.showMessageDialog(gui, "Select an appointment first.", "Validation", JOptionPane.WARNING_MESSAGE);
						return;
					}

					boolean ok = dao.performExtraction(apptId, remark); // perform transaction
					if (ok) {
						JOptionPane.showMessageDialog(gui, "Extraction recorded and inventory updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
						gui.populateAppointments(dao.getExtractableAppointments()); // refresh
					} else {
						JOptionPane.showMessageDialog(gui, "Failed to perform extraction.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}

				case "BTN_RETURN_TO_TRANSACTIONS": { // return
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
