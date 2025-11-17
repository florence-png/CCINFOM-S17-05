//import javax.swing.*;
//import java.awt.*;
//
//public class TechnicianGUI extends JPanel {
//    // Layout Management
//    private JPanel cardPanel;
//    private CardLayout cardLayout;
//
//    // Technician Menu Buttons
//    private JButton btnAddRecord;
//    private JButton btnViewEditRecord;
//    private JButton btnReturn;
//
//    // Sub-Panels (for different functions)
//    private JPanel technicianMenuPanel;
//    private JPanel addTechnicianPanel;
//    private JPanel viewEditTechnicianPanel;
//
//    // --- "Add Technician" Components ---
//    private JTextField txtLastName;
//    private JTextField txtFirstName;
//    private JTextField txtEmail;
//    private JTextField txtContactNumber;
//    private JTextField txtAge;
//    private JComboBox<String> comboSex;
//    private JTextField txtDateEmployed;
//    private JButton btnSubmitAddTechnician;
//    private JButton btnAddReturn;
//
//    // --- "View/Edit Technician" Components ---
//    private JTextField txtSearch; // The search text field
//    private JButton btnSearch; // The "Search" button
//    private JButton btnRefreshList; // The "Refresh" button
//    private JPanel technicianListPanel;
//    private JButton btnViewEditReturn; // Return button for this panel
//
//    // Getters - Technician Menu Buttons
//    public JButton getBtnAddRecord() { return btnAddRecord; }
//    public JButton getBtnViewEditRecord() { return btnViewEditRecord; }
//    public JButton getBtnReturn() { return btnReturn; }
//
//    // Getters - Add Technician Components
//    public JTextField getTxtLastName() { return txtLastName; }
//    public JTextField getTxtFirstName() { return txtFirstName; }
//    public JTextField getTxtEmail() { return txtEmail; }
//    public JTextField getTxtContactNumber() { return txtContactNumber; }
//    public JTextField getTxtAge() { return txtAge; }
//    public JComboBox<String> getComboSex() { return comboSex; }
//    public JTextField getTxtDateEmployed() { return txtDateEmployed; }
//    public JButton getBtnSubmitAddTechnician() { return btnSubmitAddTechnician; }
//    public JButton getBtnAddReturn() { return btnAddReturn; }
//
//    // Getters - View/Edit Technician Components
//    public JButton getBtnViewEditReturn() { return btnViewEditReturn; }
//    public JTextField getTxtSearch() { return txtSearch; }
//    public JButton getBtnSearch() { return btnSearch; }
//    public JButton getBtnRefreshList() { return btnRefreshList; }
//    public JPanel getTechnicianListPanel() { return technicianListPanel; }
//
//    public TechnicianGUI() {
//        this.setLayout(new BorderLayout());
//
//        cardPanel = new JPanel();
//        cardLayout = new CardLayout();
//        cardPanel.setLayout(cardLayout);
//
//        // --- Main menu panel ---
//        technicianMenuPanel = createTechnicianMenuPanel();
//
//        // --- function panels  ---
//        addTechnicianPanel = createAddTechnicianPanel();
//        viewEditTechnicianPanel = createViewEditTechnicianPanel();
//
//        // --- add all panels to the CardLayout ---
//        cardPanel.add(technicianMenuPanel, "MENU");
//        cardPanel.add(addTechnicianPanel, "ADD_FORM");
//        cardPanel.add(viewEditTechnicianPanel, "VIEW_EDIT");
//
//        this.add(cardPanel, BorderLayout.CENTER);
//
//        // show initial panel
//        showPanel("MENU");
//    }
//
//    /****** Technician Menu panel ******/
//    private JPanel createTechnicianMenuPanel() {
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
//
//        // Header
//        panel.add(createHeader("Technician Menu Panel"), BorderLayout.NORTH);
//
//        JPanel centerButtons = new JPanel(new GridLayout(1, 2, 10, 10));
//        centerButtons.setBorder(BorderFactory.createEmptyBorder(70, 0, 70, 0));
//
//        btnAddRecord = new JButton("Add New Technician Record");
//        btnAddRecord.setActionCommand("TECHNICIAN_ADD");
//
//        btnViewEditRecord = new JButton("View/Edit Technician Record");
//        btnViewEditRecord.setActionCommand("TECHNICIAN_VIEW_EDIT");
//
//        centerButtons.add(btnAddRecord);
//        centerButtons.add(btnViewEditRecord);
//        panel.add(centerButtons, BorderLayout.CENTER);
//
//        // Buttons Panel
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
//
//        btnReturn = new JButton("Return to Main Menu");
//        btnReturn.setActionCommand("TECHNICIAN_RETURN_MAIN");
//        buttonPanel.add(btnReturn);
//
//        panel.add(buttonPanel, BorderLayout.SOUTH);
//
//        return panel;
//    }
//
//    /****** Add technician panel  ******/
//    private JPanel createAddTechnicianPanel() {
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40)); // Padding
//
//        // Header
//        panel.add(createHeader("Add Technician Panel"), BorderLayout.NORTH);
//
//        // Forms Panel
//        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
//        txtLastName = new JTextField();
//        txtFirstName = new JTextField();
//        txtEmail = new JTextField();
//        txtContactNumber = new JTextField();
//        txtAge = new JTextField();
//        comboSex = new JComboBox<>(new String[]{"Male", "Female"});
//        txtDateEmployed = new JTextField();
//
//        formPanel.add(new JLabel("Last Name:"));
//        formPanel.add(txtLastName);
//
//        formPanel.add(new JLabel("First Name:"));
//        formPanel.add(txtFirstName);
//
//        formPanel.add(new JLabel("Email:"));
//        formPanel.add(txtEmail);
//
//        formPanel.add(new JLabel("Contact No:"));
//        formPanel.add(txtContactNumber);
//
//        formPanel.add(new JLabel("Sex:"));
//        formPanel.add(comboSex);
//
//        formPanel.add(new JLabel("Date Employed (YYYY-MM-DD):"));
//        formPanel.add(txtDateEmployed);
//
//        panel.add(formPanel, BorderLayout.CENTER);
//
//        // Buttons Panel
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
//
//        btnSubmitAddTechnician = new JButton("Save Record");
//        btnSubmitAddTechnician.setActionCommand("TECHNICIAN_SUBMIT_ADD");
//
//        btnAddReturn = new JButton("Return");
//        btnAddReturn.setActionCommand("TECHNICIAN_RETURN");
//
//        buttonPanel.add(btnSubmitAddTechnician);
//        buttonPanel.add(btnAddReturn);
//
//        panel.add(buttonPanel, BorderLayout.SOUTH);
//
//        return panel;
//    }
//
//    private JPanel createViewEditTechnicianPanel() {
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
//
//        // top panel (header and search panel)
//        JPanel topPanel = new JPanel(new BorderLayout());
//
//        // header
//        topPanel.add(createHeader("View/Edit Technician Record"), BorderLayout.NORTH);
//
//        // search panel
//        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
//        JLabel lblSearch = new JLabel("Search by ID/Name:");
//        txtSearch = new JTextField(20);
//        btnSearch = new JButton("Search");
//        btnSearch.setActionCommand("TECHNICIAN_SEARCH_LIST");
//
//        searchPanel.add(lblSearch);
//        searchPanel.add(txtSearch);
//        searchPanel.add(btnSearch);
//        topPanel.add(searchPanel, BorderLayout.CENTER);
//
//        panel.add(topPanel, BorderLayout.NORTH);
//
//        // scrollable list
//        technicianListPanel = new JPanel();
//        technicianListPanel.setLayout(new BoxLayout(technicianListPanel, BoxLayout.Y_AXIS));
//        technicianListPanel.setBackground(Color.WHITE);
//
//        JScrollPane scrollPane = new JScrollPane(technicianListPanel);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
//        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
//
//        panel.add(scrollPane, BorderLayout.CENTER);
//
//        // TODO: add name and buttons
//
//        // buttons panel
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
//        btnRefreshList = new JButton("Refresh List");
//        btnRefreshList.setActionCommand("TECHNICIAN_REFRESH_LIST");
//
//        btnViewEditReturn = new JButton("Return");
//        btnViewEditReturn.setActionCommand("TECHNICIAN_RETURN");
//
//        buttonPanel.add(btnRefreshList);
//        buttonPanel.add(btnViewEditReturn);
//
//        panel.add(buttonPanel, BorderLayout.SOUTH);
//
//        return panel;
//    }
//
//    // Header helper method
//    private JLabel createHeader(String title) {
//        JLabel lblHeader = new JLabel(title, SwingConstants.CENTER);
//        lblHeader.setFont(new Font("SansSerif", Font.BOLD, 24));
//
//        // Padding at the bottom
//        lblHeader.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
//        return lblHeader;
//    }
//
//    /**
//     * helper method for the controller to switch views
//     */
//    public void showPanel(String panelName) {
//        cardLayout.show(cardPanel, panelName);
//    }
//}