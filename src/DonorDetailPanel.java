import javax.swing.*;
import java.awt.*;
public class DonorDetailPanel extends AbstractDetailPanel<Donor> {
    public DonorDetailPanel() {
        super();
        btnReturn.setActionCommand("DONOR_DETAIL_RETURN");
    }

    @Override
    protected String getHeaderTitle(Donor donor) {
        return "Donor: " + donor.getLastName() + ", " + donor.getFirstName();
    }

    @Override
    protected void populateInfoPanel(Donor d) {
        infoPanel.removeAll();

        // Contact Info
        addLabel("E-mail: " + d.getDonorEmail());
        addLabel("Contact No.: " + d.getContactNumber());

        // Age
        addLabel("Age: " + d.getAge());

        // Sex
        String sexString = (d.getSex() == 'M') ? "Male" : "Female";
        addLabel("Sex: " + sexString);

        // Birthdate
        addLabel("Birthdate: " + d.getBirthdate());

        // Blood type
        String bloodTypeLabel = switch (d.getBloodType()) {
            case "A+" -> "A Positive (A+)";
            case "A-" -> "A Negative (A-)";
            case "B+" -> "B Positive (B+)";
            case "B-" -> "B Negative (B-)";
            case "AB+" -> "AB Positive (AB+)";
            case "AB-" -> "AB Negative (AB-)";
            case "O+" -> "O Positive (O+)";
            case "O-" -> "O Negative (O-)";
            default -> "Unknown";
        };
        addLabel("Blood Type: " + bloodTypeLabel);

        // Remarks
        addLabel("Remarks: " + d.getRemarks());
    }

    @Override
    protected void populateHistoryPanel(Donor donor) {
        DonorHistoryDAO historyDAO = new DonorHistoryDAO();
        List<String> logs = historyDAO.getDonationHistory(donor.getDonorId());

        StringBuilder sb = new StringBuilder();
        for(String log : logs){
            sb.append(log).append("\n");
        }
        txtHistory.setText(sb.toString());
    }

    // helper method to keep fonts consistent
    private void addLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lbl.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0)); // Little spacing
        infoPanel.add(lbl);
    }
}
