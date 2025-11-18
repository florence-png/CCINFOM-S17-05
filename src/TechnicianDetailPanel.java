import javax.swing.*;
import java.awt.*;
public class TechnicianDetailPanel extends AbstractDetailPanel<Technician> {

    // --- Technician-Specific Labels ---
    private JLabel lblEmail, lblContact, lblDateEmployed;

    public TechnicianDetailPanel() {
        super();

        btnReturn.setActionCommand("TECHNICIAN_DETAIL_RETURN");

        infoPanel.setLayout(new GridLayout(0, 1, 5, 5)); // Single column list

        lblEmail = new JLabel("E-mail: ");
        lblContact = new JLabel("Contact No.: ");
        lblDateEmployed = new JLabel("Date Employed: ");

        infoPanel.add(lblEmail);
        infoPanel.add(lblContact);
        infoPanel.add(lblDateEmployed);
    }

    @Override
    protected String getHeaderTitle(Technician technician) {
        return "Technician: " + technician.getLastName() + ", " + technician.getFirstName();
    }

    @Override
    protected void populateInfoPanel(Technician technician) {
        lblEmail.setText("E-mail: " + technician.getTechnicianEmail());
        lblContact.setText("Contact No.: " + technician.getContactNumber());
        lblDateEmployed.setText("Date Employed: " + technician.getDateEmployed());
    }

    @Override
    protected void populateHistoryPanel(Technician technician) {
        StringBuilder sb = new StringBuilder();

        // TODO: Replace with actual DAO logic
        sb.append("Extracted Blood on 2025-11-11 at Maple Branch\n");
        sb.append("Extracted Blood on 2025-09-02 at Hamilton\n");

        txtHistory.setText(sb.toString());
    }
}