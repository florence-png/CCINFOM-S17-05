import javax.swing.*;
import java.awt.*;

public class DonorGUI extends JPanel {

    // Donor Buttons
    private JButton btnAddRecord;
    private JButton btnViewEditRecord;
    private JButton btnSearchDonor;
    private JButton btnViewHistory;
    private JButton btnReturn;

    // --- Layout Management ---
    private JPanel cardPanel;
    private CardLayout cardLayout;

    // --- Sub-Panels (for different functions) ---
    private JPanel donorMenuPanel;
    private JPanel addDonorPanel;
    private JPanel viewEditDonorPanel;
    private JPanel searchDonorPanel;
    private JPanel historyDonorPanel;

    // Button Getters
    public JButton getBtnAddRecord() { return btnAddRecord; }
    public JButton getBtnViewEditRecord() { return btnViewEditRecord; }
    public JButton getBtnSearchDonor() { return btnSearchDonor; }
    public JButton getBtnViewHistory() { return btnViewHistory; }
    public JButton getBtnReturn() { return btnReturn; }

    public DonorGUI() {
        this.setLayout(new BorderLayout());

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        // --- Build the main menu panel ---
        donorMenuPanel = createDonorMenuPanel();

        // --- function panels  ---
        addDonorPanel = createAddDonorPanel();
        viewEditDonorPanel = createPlaceholderPanel("View/Edit Donor Panel");
        searchDonorPanel = createPlaceholderPanel("Search Donor Panel");
        historyDonorPanel = createPlaceholderPanel("Donation History Panel");

        // --- add all panels to the CardLayout ---
        cardPanel.add(donorMenuPanel, "MENU");
        cardPanel.add(addDonorPanel, "ADD_FORM");
        cardPanel.add(viewEditDonorPanel, "VIEW_EDIT");
        cardPanel.add(searchDonorPanel, "SEARCH");
        cardPanel.add(historyDonorPanel, "HISTORY");

        this.add(cardPanel, BorderLayout.CENTER);

        // show initial panel
        showPanel("MENU");
    }

    /**
     * Creates the main menu panel with the functional buttons.
     */
    private JPanel createDonorMenuPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10)); // 6 rows for buttons + padding
        panel.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));

        btnAddRecord = new JButton("Add New Donor Record");
        btnAddRecord.setActionCommand("DONOR_ADD");

        btnViewEditRecord = new JButton("View/Edit Donor Record");
        btnViewEditRecord.setActionCommand("DONOR_VIEW_EDIT");

        btnSearchDonor = new JButton("Search Donor by ID/Name");
        btnSearchDonor.setActionCommand("DONOR_SEARCH");

        btnViewHistory = new JButton("View Donation History");
        btnViewHistory.setActionCommand("DONOR_HISTORY");

        btnReturn = new JButton("Return to Main Menu");
        btnReturn.setActionCommand("DONOR_RETURN_MAIN");

        panel.add(btnAddRecord);
        panel.add(btnViewEditRecord);
        panel.add(btnSearchDonor);
        panel.add(btnViewHistory);
        panel.add(new JLabel("")); // spacer
        panel.add(btnReturn);

        return panel;
    }

    /**
     * Creates the panel where the Add Donor form will go.
     */
    private JPanel createAddDonorPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10)); // 10 fields (including ID/Remarks)

        formPanel.add(new JLabel("Last Name:"));
        formPanel.add(new JTextField(20));

        formPanel.add(new JLabel("Sex:"));
        formPanel.add(new JComboBox<>(new String[]{"Male", "Female"}));

        formPanel.add(new JLabel("Blood Type:"));
        formPanel.add(new JComboBox<>(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"}));

        // to add fields "First Name", Contact Number, Age, Birthdate, Remarks...

        panel.add(formPanel, BorderLayout.NORTH);

        // back Button for the form (needs a getter later for the controller)
        JButton btnSubmit = new JButton("Submit Record");
        btnSubmit.setActionCommand("DONOR_SUBMIT_ADD");

        JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonWrapper.add(btnSubmit);

        panel.add(buttonWrapper, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createPlaceholderPanel(String name) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(name, SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    /**
     * helper method for the controller to switch views
     */
    public void showPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
    }
}