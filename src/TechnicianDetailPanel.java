import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Period;

public class TechnicianDetailPanel extends AbstractDetailPanel<Technician> {
    public TechnicianDetailPanel() {
        super();
        btnReturn.setActionCommand("TECHNICIAN_DETAIL_RETURN");
    }

    @Override
    protected String getHeaderTitle(Technician technician) {
        return "Technician: " + technician.getLastName() + ", " + technician.getFirstName();
    }

    @Override
    protected void populateInfoPanel(Technician t) {
        infoPanel.removeAll();

        // Sex
        String sexString = (t.getSex() == 'M') ? "Male" : "Female";
        addLabel("Sex: " + sexString);

        // Age
        addLabel("Age: " + t.getAge());
        // Date Employed
        addLabel("Date Employed: " + t.getDateEmployed()); // Date Employed

        // Years Employed (Calculation)
        if (t.getDateEmployed() != null) {
            // Convert Date to LocalDate
            LocalDate start = t.getDateEmployed().toLocalDate();
            LocalDate now = LocalDate.now();
            int years = Period.between(start, now).getYears();
            addLabel("Years Employed: " + years);
        }

        // Contact Info
        addLabel("E-mail: " + t.getTechnicianEmail());
        addLabel("Contact No.: " + t.getContactNumber());

        // Refresh the UI to show new labels
        infoPanel.revalidate();
        infoPanel.repaint();
    }

    @Override
    protected void populateHistoryPanel(Technician technician) {
        List<String> history = technicianDAO.getExtractionHistory(technician.getTechnicianId());
        StringBuilder sb = new StringBuilder();
        
        for(String record : history){
            sb.append(record).append("\n");
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
