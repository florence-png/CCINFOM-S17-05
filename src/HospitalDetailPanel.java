import javax.swing.*;
import java.awt.*;

public class HospitalDetailPanel extends AbstractDetailPanel<Hospital> {
    public HospitalDetailPanel() {
        super();
        btnReturn.setActionCommand("HOSPITAL_DETAIL_RETURN");
    }

    @Override
    protected String getHeaderTitle(Hospital hospital) {
        return "Hospital: " + hospital.getHospitalName();
    }

    @Override
    protected void populateInfoPanel(Hospital h) {
        infoPanel.removeAll();

        addLabel("Street: " + h.getStreet());
        addLabel("City: " + h.getCity());
        addLabel("Region: " + h.getRegion());
        addLabel("Contact No.: " + h.getContactNumber());

        infoPanel.revalidate();
        infoPanel.repaint();
    }

    protected void populateHistoryPanel(Hospital hospital) {
        StringBuilder sb = new StringBuilder();

        // TODO: Replace wit actual DAO logic
        sb.append("Requested (Units), Blood Type: (Blood Type), Component: (Component)\n");
        sb.append("Requested on (Date) from (Branch)\n");
        sb.append("Requested (Units) of (Blood Type) (Component)\n");
        sb.append("Requested on (Date) from (Branch)\n");
    }

    // helper method to keep fonts consistent
    private void addLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lbl.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0)); // Little spacing
        infoPanel.add(lbl);
    }
}
