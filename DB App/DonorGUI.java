import javax.swing.*;
import java.awt.*;

public class DonorGUI extends JPanel {

    // Donor Menu Buttons
    private JButton btnAddRecord;
    private JButton btnViewEditRecord;
    private JButton btnReturn;

    // Layout Management
    private JPanel cardPanel;
    private CardLayout cardLayout;

    // Sub-Panels (for different functions)
    private JPanel donorMenuPanel;
    private JPanel addDonorPanel;
    private JPanel viewEditDonorPanel;

    // --- "Add Donor" Components ---
    private JTextField txtLastName;
    private JTextField txtFirstName;
    private JTextField txtEmail;
    private JTextField txtContactNumber;
    private JTextField txtAge;
    private JComboBox<String> comboSex;
    private JTextField txtBirthdate;
    private JComboBox<String> comboBloodType;
    private JTextArea txtRemarks;
    private JButton btnSubmitAddDonor;
    private JButton btnAddReturn;

    // --- "View/Edit Donor" Components ---
    private JTextField txtSearch; // The search text field
    private JButton btnSearch; // The "Search" button
    private JButton btnRefreshList; // The "Refresh" button
    private JPanel donorListPanel;
    private JButton btnViewEditReturn; // Return button for this panel

    // Getters - Donor Menu Buttons
    public JButton getBtnAddRecord() { return btnAddRecord; }
    public JButton getBtnViewEditRecord() { return btnViewEditRecord; }
    public JButton getBtnReturn() { return btnReturn; }

    // Getters - Add Donor Components
    public JTextField getTxtLastName() { return txtLastName; }
    public JTextField getTxtFirstName() { return txtFirstName; }
    public JTextField getTxtEmail() { return txtEmail; }
    public JTextField getTxtContactNumber() { return txtContactNumber; }
    public JTextField getTxtAge() { return txtAge; }
    public JComboBox<String> getComboSex() { return comboSex; }
    public JTextField getTxtBirthdate() { return txtBirthdate; }
    public JComboBox<String> getComboBloodType() { return comboBloodType; }
    public JTextArea getTxtRemarks() { return txtRemarks; }
    public JButton getBtnSubmitAddDonor() { return btnSubmitAddDonor; }
    public JButton getBtnAddReturn() { return btnAddReturn; }

    // Getters - View/Edit Donor Components
    public JButton getBtnViewEditReturn() { return btnViewEditReturn; }
    public JTextField getTxtSearch() { return txtSearch; }
    public JButton getBtnSearch() { return btnSearch; }
    public JButton getBtnRefreshList() { return btnRefreshList; }
    public JPanel getDonorListPanel() { return donorListPanel; }

    public DonorGUI() {
        this.setLayout(new BorderLayout());

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        // --- Main menu panel ---
        donorMenuPanel = createDonorMenuPanel();

        // --- function panels  ---
        addDonorPanel = createAddDonorPanel();
        viewEditDonorPanel = createViewEditDonorPanel();

        // --- add all panels to the CardLayout ---
        cardPanel.add(donorMenuPanel, "MENU");
        cardPanel.add(addDonorPanel, "ADD_FORM");
        cardPanel.add(viewEditDonorPanel, "VIEW_EDIT");

        this.add(cardPanel, BorderLayout.CENTER);

        // show initial panel
        showPanel("MENU");
    }

    /****** Donor Menu panel ******/
    private JPanel createDonorMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        // Header
        panel.add(createHeader("Donor Menu Panel"), BorderLayout.NORTH);

        JPanel centerButtons = new JPanel(new GridLayout(1, 2, 10, 10));
        centerButtons.setBorder(BorderFactory.createEmptyBorder(70, 0, 70, 0));

        btnAddRecord = new JButton("Add New Donor Record");
        btnAddRecord.setActionCommand("DONOR_ADD");

        btnViewEditRecord = new JButton("View/Edit Donor Record");
        btnViewEditRecord.setActionCommand("DONOR_VIEW_EDIT");

        centerButtons.add(btnAddRecord);
        centerButtons.add(btnViewEditRecord);
        panel.add(centerButtons, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        btnReturn = new JButton("Return to Main Menu");
        btnReturn.setActionCommand("DONOR_RETURN_MAIN");
        buttonPanel.add(btnReturn);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    /****** Add donor panel  ******/
    private JPanel createAddDonorPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40)); // Padding

        // Header
        panel.add(createHeader("Add Donor Panel"), BorderLayout.NORTH);

        // Form Fields Panel (Grid Layout)
        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        txtLastName = new JTextField();
        txtFirstName = new JTextField();
        txtEmail = new JTextField();
        txtContactNumber = new JTextField();
        txtAge = new JTextField();
        comboSex = new JComboBox<>(new String[]{"Male", "Female"});
        txtBirthdate = new JTextField();
        comboBloodType = new JComboBox<>(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"});
        txtRemarks = new JTextArea(3, 20);
        txtRemarks.setLineWrap(true);
        JScrollPane scrollRemarks = new JScrollPane(txtRemarks); // Scroll pane for remarks

        // --- Add to Form Panel ---
        formPanel.add(new JLabel("Last Name:"));
        formPanel.add(txtLastName);

        formPanel.add(new JLabel("First Name:"));
        formPanel.add(txtFirstName);

        formPanel.add(new JLabel("Email:"));
        formPanel.add(txtEmail);

        formPanel.add(new JLabel("Contact No:"));
        formPanel.add(txtContactNumber);

        formPanel.add(new JLabel("Age:"));
        formPanel.add(txtAge);

        formPanel.add(new JLabel("Sex:"));
        formPanel.add(comboSex);

        formPanel.add(new JLabel("Birthdate (YYYY-MM-DD):"));
        formPanel.add(txtBirthdate);

        formPanel.add(new JLabel("Blood Type:"));
        formPanel.add(comboBloodType);

        formPanel.add(new JLabel("Remarks:"));
        formPanel.add(scrollRemarks);

        panel.add(formPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        btnSubmitAddDonor = new JButton("Save Record");
        btnSubmitAddDonor.setActionCommand("DONOR_SUBMIT_ADD");

        btnAddReturn = new JButton("Return");
        btnAddReturn.setActionCommand("DONOR_RETURN");

        buttonPanel.add(btnSubmitAddDonor);
        buttonPanel.add(btnAddReturn);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createViewEditDonorPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        // top panel (header and search panel)
        JPanel topPanel = new JPanel(new BorderLayout());

        // header
        topPanel.add(createHeader("View/Edit Donor Record"), BorderLayout.NORTH);

        // search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel lblSearch = new JLabel("Search by ID/Name:");
        txtSearch = new JTextField(20);
        btnSearch = new JButton("Search");
        btnSearch.setActionCommand("DONOR_SEARCH_LIST");

        searchPanel.add(lblSearch);
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        panel.add(topPanel, BorderLayout.NORTH);

        // scrollable list
        donorListPanel = new JPanel();
        donorListPanel.setLayout(new BoxLayout(donorListPanel, BoxLayout.Y_AXIS));
        donorListPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(donorListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        panel.add(scrollPane, BorderLayout.CENTER);

        // buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnRefreshList = new JButton("Refresh List");
        btnRefreshList.setActionCommand("DONOR_REFRESH_LIST");

        btnViewEditReturn = new JButton("Return");
        btnViewEditReturn.setActionCommand("DONOR_RETURN");

        buttonPanel.add(btnRefreshList);
        buttonPanel.add(btnViewEditReturn);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        // empty for now

        return panel;
    }

    // Header helper method
    private JLabel createHeader(String title) {
        JLabel lblHeader = new JLabel(title, SwingConstants.CENTER);
        lblHeader.setFont(new Font("SansSerif", Font.BOLD, 24));
        // Add padding to the bottom
        lblHeader.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        return lblHeader;
    }

    /**
     * Placeholder Panel
     */
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