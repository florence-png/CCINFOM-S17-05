//import javax.swing.*;
//import java.awt.*;
//
//public class InventoryGUI extends JPanel {
//    // Layout Management
//    private JPanel cardPanel;
//    private CardLayout cardLayout;
//
//    // Inventory Menu Buttons
//    private JButton btnAddRecord;
//    private JButton btnViewEditRecord;
//    private JButton btnReturn;
//
//    // Sub-Panels (for different functions)
//    private JPanel inventoryMenuPanel;
//    private JPanel addInventoryPanel;
//    private JPanel viewEditInventoryPanel;
//
//    // --- "Add Inventory" Components ---
//    private JTextField txtName;
//    private JTextField txtEmail;
//    private JTextField txtContactNumber;
//    private JTextField txtAge;
//    private JComboBox<String> comboSex;
//    private JTextField txtBirthdate;
//    private JComboBox<String> comboBloodType;
//    private JTextArea txtRemarks;
//    private JButton btnSubmitAddInventory;
//    private JButton btnAddReturn;
//
//    // --- "View/Edit Inventory" Components ---
//    private JTextField txtSearch; // The search text field
//    private JButton btnSearch; // The "Search" button
//    private JButton btnRefreshList; // The "Refresh" button
//    private JPanel inventoryListPanel;
//    private JButton btnViewEditReturn; // Return button for this panel
//
//    // Getters - Inventory Menu Buttons
//    public JButton getBtnAddRecord() { return btnAddRecord; }
//    public JButton getBtnViewEditRecord() { return btnViewEditRecord; }
//    public JButton getBtnReturn() { return btnReturn; }
//
//    // Getters - Add Inventory Components
//    public JTextField getTxtName() { return txtName; }
//    public JTextField getTxtEmail() { return txtEmail; }
//    public JTextField getTxtContactNumber() { return txtContactNumber; }
//    public JTextField getTxtAge() { return txtAge; }
//    public JComboBox<String> getComboSex() { return comboSex; }
//    public JTextField getTxtBirthdate() { return txtBirthdate; }
//    public JComboBox<String> getComboBloodType() { return comboBloodType; }
//    public JTextArea getTxtRemarks() { return txtRemarks; }
//    public JButton getBtnSubmitAddInventory() { return btnSubmitAddInventory; }
//    public JButton getBtnAddReturn() { return btnAddReturn; }
//
//    // Getters - View/Edit Inventory Components
//    public JButton getBtnViewEditReturn() { return btnViewEditReturn; }
//    public JTextField getTxtSearch() { return txtSearch; }
//    public JButton getBtnSearch() { return btnSearch; }
//    public JButton getBtnRefreshList() { return btnRefreshList; }
//    public JPanel getInventoryListPanel() { return inventoryListPanel; }
//
//    public InventoryGUI() {
//        this.setLayout(new BorderLayout());
//
//        cardPanel = new JPanel();
//        cardLayout = new CardLayout();
//        cardPanel.setLayout(cardLayout);
//
//        // --- Main menu panel ---
//        inventoryMenuPanel = createInventoryMenuPanel();
//
//        // --- function panels  ---
//        addInventoryPanel = createAddInventoryPanel();
//        viewEditInventoryPanel = createViewEditInventoryPanel();
//
//        // --- add all panels to the CardLayout ---
//        cardPanel.add(inventoryMenuPanel, "MENU");
//        cardPanel.add(addInventoryPanel, "ADD_FORM");
//        cardPanel.add(viewEditInventoryPanel, "VIEW_EDIT");
//
//        this.add(cardPanel, BorderLayout.CENTER);
//
//        // show initial panel
//        showPanel("MENU");
//    }
//
//    /****** Inventory Menu panel ******/
//    private JPanel createInventoryMenuPanel() {
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
//
//        // Header
//        panel.add(createHeader("Inventory Menu Panel"), BorderLayout.NORTH);
//
//        JPanel centerButtons = new JPanel(new GridLayout(1, 2, 10, 10));
//        centerButtons.setBorder(BorderFactory.createEmptyBorder(70, 0, 70, 0));
//
//        btnAddRecord = new JButton("Add New Inventory Record");
//        btnAddRecord.setActionCommand("INVENTORY_ADD");
//
//        btnViewEditRecord = new JButton("View/Edit Inventory Record");
//        btnViewEditRecord.setActionCommand("INVENTORY_VIEW_EDIT");
//
//        centerButtons.add(btnAddRecord);
//        centerButtons.add(btnViewEditRecord);
//        panel.add(centerButtons, BorderLayout.CENTER);
//
//        // Buttons Panel
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
//
//        btnReturn = new JButton("Return to Main Menu");
//        btnReturn.setActionCommand("INVENTORY_RETURN_MAIN");
//        buttonPanel.add(btnReturn);
//
//        panel.add(buttonPanel, BorderLayout.SOUTH);
//
//        return panel;
//    }
//
//    /****** Add inventory panel  ******/
//    private JPanel createAddInventoryPanel() {
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40)); // Padding
//
//        // Header
//        panel.add(createHeader("Add Inventory Panel"), BorderLayout.NORTH);
//
//        // Forms Panel
//        JPanel formPanel = new JPanel(new GridLayout(9, 2, 10, 10));
//        txtName = new JTextField();
//        txtEmail = new JTextField();
//        txtContactNumber = new JTextField();
//        txtAge = new JTextField();
//        comboSex = new JComboBox<>(new String[]{"Male", "Female"});
//        txtBirthdate = new JTextField();
//        comboBloodType = new JComboBox<>(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"});
//        txtRemarks = new JTextArea(3, 20);
//        txtRemarks.setLineWrap(true);
//        JScrollPane scrollRemarks = new JScrollPane(txtRemarks); // Scroll pane for remarks
//
//        formPanel.add(new JLabel("Name:"));
//        formPanel.add(txtName);
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
//        formPanel.add(new JLabel("Birthdate (YYYY-MM-DD):"));
//        formPanel.add(txtBirthdate);
//
//        formPanel.add(new JLabel("Blood Type:"));
//        formPanel.add(comboBloodType);
//
//        formPanel.add(new JLabel("Remarks:"));
//        formPanel.add(scrollRemarks);
//
//        panel.add(formPanel, BorderLayout.CENTER);
//
//        // Buttons Panel
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
//
//        btnSubmitAddInventory = new JButton("Save Record");
//        btnSubmitAddInventory.setActionCommand("INVENTORY_SUBMIT_ADD");
//
//        btnAddReturn = new JButton("Return");
//        btnAddReturn.setActionCommand("INVENTORY_RETURN");
//
//        buttonPanel.add(btnSubmitAddInventory);
//        buttonPanel.add(btnAddReturn);
//
//        panel.add(buttonPanel, BorderLayout.SOUTH);
//
//        return panel;
//    }
//
//    private JPanel createViewEditInventoryPanel() {
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
//
//        // top panel (header and search panel)
//        JPanel topPanel = new JPanel(new BorderLayout());
//
//        // header
//        topPanel.add(createHeader("View/Edit Inventory Record"), BorderLayout.NORTH);
//
//        // search panel
//        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
//        JLabel lblSearch = new JLabel("Search by ID/Name:");
//        txtSearch = new JTextField(20);
//        btnSearch = new JButton("Search");
//        btnSearch.setActionCommand("INVENTORY_SEARCH_LIST");
//
//        searchPanel.add(lblSearch);
//        searchPanel.add(txtSearch);
//        searchPanel.add(btnSearch);
//        topPanel.add(searchPanel, BorderLayout.CENTER);
//
//        panel.add(topPanel, BorderLayout.NORTH);
//
//        // scrollable list
//        inventoryListPanel = new JPanel();
//        inventoryListPanel.setLayout(new BoxLayout(inventoryListPanel, BoxLayout.Y_AXIS));
//        inventoryListPanel.setBackground(Color.WHITE);
//
//        JScrollPane scrollPane = new JScrollPane(inventoryListPanel);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
//        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
//
//        // TODO: add name and buttons
//
//        panel.add(scrollPane, BorderLayout.CENTER);
//
//        // buttons panel
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
//        btnRefreshList = new JButton("Refresh List");
//        btnRefreshList.setActionCommand("INVENTORY_REFRESH_LIST");
//
//        btnViewEditReturn = new JButton("Return");
//        btnViewEditReturn.setActionCommand("INVENTORY_RETURN");
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