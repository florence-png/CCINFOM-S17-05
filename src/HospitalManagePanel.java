import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HospitalManagePanel extends AbstractManagePanel<Hospital> {
    private ActionListener controller;

    public HospitalManagePanel() {
        super("Hospital");

        // Set action commands for buttons from the abstract class
        btnRefresh.setActionCommand("HOSPITAL_REFRESH_LIST");
        btnReturn.setActionCommand("HOSPITAL_RETURN_TO_MENU");
        btnSearch.setActionCommand("HOSPITAL_SEARCH"); // You'll need to implement search
    }

    @Override
    protected JPanel createEntityRowPanel(Hospital hospital) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Hospital Info (Matches model fields)
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        infoPanel.add(new JLabel("Name: " + hospital.getHospitalName()));
        infoPanel.add(new JLabel("City: " + hospital.getCity()));

        panel.add(infoPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnDetails = new JButton("Details");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Delete");

        // Unique action commands using hospitalId
        btnDetails.setActionCommand("HOSPITAL_SHOW_DETAIL_" + hospital.getHospitalId());
        btnEdit.setActionCommand("HOSPITAL_EDIT_" + hospital.getHospitalId());
        btnDelete.setActionCommand("HOSPITAL_DELETE_" + hospital.getHospitalId());

        // Attach controller
        if (controller != null) {
            btnDetails.addActionListener(controller);
            btnEdit.addActionListener(controller);
            btnDelete.addActionListener(controller);
        }

        buttonPanel.add(btnDetails);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        panel.add(buttonPanel, BorderLayout.EAST);

        return panel;
    }

    public void setController(ActionListener controller) { this.controller = controller; }
}
