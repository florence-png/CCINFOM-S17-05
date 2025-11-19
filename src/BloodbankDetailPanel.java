import javax.swing.*;
import java.awt.*;

public class BloodbankDetailPanel extends AbstractDetailPanel<Bloodbank> {
    public BloodbankDetailPanel() {
        super();
        btnReturn.setActionCommand("BLOODBANK_DETAIL_RETURN");
    }

    @Override
    protected String getHeaderTitle(Bloodbank bloodbank) {
        return "Branch: " + bloodbank.getBranchName();
    }

    @Override
    protected void populateInfoPanel(Bloodbank b) {
        infoPanel.removeAll();

        addLabel("Street: " + b.getStreet());
        addLabel("City: " + b.getCity());
        addLabel("Contact No.: " + b.getContactNumber());

        infoPanel.revalidate();
        infoPanel.repaint();
    }

    protected void populateHistoryPanel(Bloodbank bloodbank) {
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
