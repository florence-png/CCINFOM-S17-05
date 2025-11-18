import javax.swing.*;
import java.awt.*;
public class DonorDetailPanel extends AbstractDetailPanel<Donor> {

    // --- Donor-Specific Labels ---
    private JLabel lblBloodType, lblSex, lblBirthdate, lblAge, lblEmail, lblContact;

    public DonorDetailPanel() {
        super(); // Calls constructor of AbstractDetailPanel

        // Set action command for the "Return" button
        btnReturn.setActionCommand("DONOR_DETAIL_RETURN");

        // --- Setup the infoPanel (from abstract class) ---
        infoPanel.setLayout(new GridLayout(0, 1, 5, 5)); // 1 column
        lblBloodType = new JLabel("Type: ");
        lblSex = new JLabel("Sex: ");
        lblBirthdate = new JLabel("Birthdate: ");
        lblAge = new JLabel("Age: ");
        lblEmail = new JLabel("E-mail: ");
        lblContact = new JLabel("Contact No.: ");

        infoPanel.add(lblBloodType);
        infoPanel.add(lblSex);
        infoPanel.add(lblBirthdate);
        infoPanel.add(lblAge);
        infoPanel.add(lblEmail);
        infoPanel.add(lblContact);
    }

    @Override
    protected String getHeaderTitle(Donor donor) {
        return "Donor: " + donor.getLastName() + ", " + donor.getFirstName();
    }

    @Override
    protected void populateInfoPanel(Donor donor) {
        // Set text for all the labels
        lblBloodType.setText("Type: " + donor.getBloodType());
        lblSex.setText("Sex: " + donor.getSex());
        lblBirthdate.setText("Birthdate: " + donor.getBirthdate().toString());
        lblAge.setText("Age: " + donor.getAge());
        lblEmail.setText("E-mail: " + donor.getDonorEmail());
        lblContact.setText("Contact No.: " + donor.getContactNumber());
    }

    @Override
    protected void populateHistoryPanel(Donor donor) {
        // 1. Fetch history from DAO
        // DonationHistoryDAO historyDAO = new DonationHistoryDAO();
        // List<Donation> history = historyDAO.getHistoryForDonor(donor.getDonorId());

        // 2. Build a string and set it
        StringBuilder sb = new StringBuilder();
        // for (Donation d : history) {
        //    sb.append("Donated on " + d.getDate() + " at " + d.getBranchName() + "\n");
        // }

        // Simulation:
        sb.append("Donated on 2025-11-11 at Maple Branch\n");
        sb.append("Donated on 2025-09-02 at Hamilton\n");

        txtHistory.setText(sb.toString());
    }
}