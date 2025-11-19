import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

public class SchedAppointController implements ActionListener {

    private final SchedAppointGUI gui;
    private final SchedAppointDAO dao;
    private final JPanel mainCardPanel;

    public SchedAppointController(SchedAppointGUI gui, JPanel mainCardPanel) {
        this.gui = gui;
        this.dao = new SchedAppointDAO(); // Instantiate DAO here
        this.mainCardPanel = mainCardPanel;
        addListeners();
        refreshData();
    }

    private void addListeners() {
        gui.getConfirmButton().addActionListener(this);
        gui.getReturnButton().addActionListener(this);
    }

    private void refreshData() {// refresh data in GUI
        // populate donors and branches
        List<Donor> donors = dao.getEligibleDonors();
        gui.populateDonorCombo(donors);

        List<Bloodbank> branches = dao.getOperationalBranches();
        gui.populateBranchCombo(branches);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "BTN_CONFIRM_APPT" -> {
                Integer donorId = gui.getSelectedDonorId();// get selected donor ID
                Integer branchId = gui.getSelectedBranchId();// get selected branch ID
                java.sql.Date date = gui.getSelectedDateSql();// get selected date

                if (donorId == null || branchId == null) {// check for missing data
                    JOptionPane.showMessageDialog(gui, "Please select a donor and a branch.", "Missing Data", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int apptId = dao.scheduleAppointment(donorId, branchId, date);// schedule appointment
                if (apptId > 0) {// check if scheduling was successful
                    JOptionPane.showMessageDialog(gui, "Appointment scheduled (ID: " + apptId + ").", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // return to Transactions panel
                    CardLayout cl = (CardLayout) mainCardPanel.getLayout();
                    cl.show(mainCardPanel, "TRANSACTIONS_GUI");
                } else {
                    JOptionPane.showMessageDialog(gui, "Failed to schedule appointment.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            case "BTN_RETURN_TO_MENU" -> {
                CardLayout cl = (CardLayout) mainCardPanel.getLayout();
                cl.show(mainCardPanel, "TRANSACTIONS_GUI");
            }
            default -> System.out.println("SchedAppointController: unknown command " + cmd);
        }
    }
}

