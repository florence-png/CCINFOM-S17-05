import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TechnicianManagePanel extends AbstractManagePanel<Technician> {
    private ActionListener controller;

    public TechnicianManagePanel() {
        super("Technician");

        // Set action commands for buttons from the abstract class
        btnRefresh.setActionCommand("TECHNICIAN_REFRESH_LIST");
        btnReturn.setActionCommand("TECHNICIAN_RETURN_TO_MENU");
        btnSearch.setActionCommand("TECHNICIAN_SEARCH"); // You'll need to implement search
    }

    @Override
    protected JPanel createEntityRowPanel(Technician technician) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Technician Info (Matches model fields)
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        infoPanel.add(new JLabel("Name: " + technician.getLastName() + ", " + technician.getFirstName()));
        infoPanel.add(new JLabel("Email: " + technician.getTechnicianEmail()));
        infoPanel.add(new JLabel("Contact: " + technician.getContactNumber()));
        infoPanel.add(new JLabel("Employed: " + technician.getDateEmployed()));

        panel.add(infoPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnDetails = new JButton("Details");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Delete");

        // Unique action commands using technicianId
        btnDetails.setActionCommand("TECHNICIAN_SHOW_DETAIL_" + technician.getTechnicianId());
        btnEdit.setActionCommand("TECHNICIAN_EDIT_" + technician.getTechnicianId());
        btnDelete.setActionCommand("TECHNICIAN_DELETE_" + technician.getTechnicianId());

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

    public void setController(ActionListener controller) {
        this.controller = controller;
    }
}