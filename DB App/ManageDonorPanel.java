import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
// import your.model.Donor; // Import your Donor object

public class ManageDonorPanel extends AbstractManagePanel<Donor> {

    // Store the controller to attach to dynamic buttons
    private ActionListener controller;

    public ManageDonorPanel() {
        super("Donor"); // Pass entity name to abstract class

        // Set action commands for buttons from the abstract class
        btnRefresh.setActionCommand("DONOR_REFRESH_LIST");
        btnReturn.setActionCommand("DONOR_RETURN_TO_MENU");
        btnSearch.setActionCommand("DONOR_SEARCH"); // You'll need to implement search
    }

    /**
     * This is the required method from AbstractManagePanel.
     * It builds one row for the list.
     */
    @Override
    protected JPanel createEntityRowPanel(Donor donor) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Donor Info (Name, Blood Type)
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(new JLabel("Name: " + donor.getLastName() + ", " + donor.getFirstName()));
        infoPanel.add(new JLabel("Type: " + donor.getBloodType()));
        panel.add(infoPanel, BorderLayout.CENTER);

        // Button Panel (Details, Edit, Delete)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnDetails = new JButton("Details");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Delete");

        // --- Set unique commands with the Donor's ID ---
        btnDetails.setActionCommand("DONOR_SHOW_DETAIL_" + donor.getDonorId());
        btnEdit.setActionCommand("DONOR_EDIT_" + donor.getDonorId());
        btnDelete.setActionCommand("DONOR_DELETE_" + donor.getDonorId());

        // --- Attach the MAIN controller ---
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

    /**
     * Called by the Controller so this panel can
     * attach listeners to its dynamic buttons.
     */
    public void setController(ActionListener controller) {
        this.controller = controller;
    }
}