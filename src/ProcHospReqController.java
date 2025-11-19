import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

public class ProcHospReqController implements ActionListener {

	private final ProcHospReqGUI gui;
	private final ProcHospReqDAO dao;
	private final JPanel mainCardPanel;

	public ProcHospReqController(ProcHospReqGUI gui, JPanel mainCardPanel) {
		this.gui = gui;
		this.dao = new ProcHospReqDAO(); // DAO for processing
		this.mainCardPanel = mainCardPanel;
		addListeners();
		refresh();
		// Keep list fresh whenever panel is shown
		gui.addComponentListener(new java.awt.event.ComponentAdapter(){@Override public void componentShown(java.awt.event.ComponentEvent e){refresh();}});
	}

	private void addListeners() {
		gui.getConfirmButton().addActionListener(this);
		gui.getReturnButton().addActionListener(this);
	}

	private void refresh() { // load pending requests into GUI
		List<String> rows = dao.getPendingRequests();
		gui.populateRequests(rows);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {
			case "BTN_CONFIRM_PROC_REQ": { // confirm processing
				Integer reqId = gui.getSelectedRequestId();
				if (reqId == null) {
					JOptionPane.showMessageDialog(gui, "Select a request to process.", "Validation", JOptionPane.WARNING_MESSAGE);
					return;
				}
				String result = dao.processRequest(reqId); // returns new status or null on error
				if (result == null) {
					JOptionPane.showMessageDialog(gui, "Failed to process request.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(gui, "Request processed: " + result, "Result", JOptionPane.INFORMATION_MESSAGE);
					refresh(); // refresh list
				}
				break;
			}
			case "BTN_RETURN_TO_TRANSACTIONS": { // return
				CardLayout cl = (CardLayout) (mainCardPanel.getLayout());
				cl.show(mainCardPanel, "TRANSACTIONS_GUI");
				break;
			}
			default:
				System.out.println("Unknown command: " + cmd);
		}
	}
}
