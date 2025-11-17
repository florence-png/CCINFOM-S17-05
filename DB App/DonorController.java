import javax.swing.*;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

// import the models and DAO


public class DonorController implements ActionListener {

    // View components
    private DonorGUI donorGUI;
    private JPanel mainCardPanel;

    // The specific sub-panels this controller manages
    private DonorMenuPanel menuPanel;
    private AddDonorPanel addPanel;
    private ManageDonorPanel managePanel;
    private DetailDonorPanel detailPanel;

    // private DonorDAO donorDAO;

    /**
     * Constructor for the Donor Controller.
     */
    public DonorController(DonorGUI donorGUI, JPanel mainCardPanel) {
        this.donorGUI = donorGUI;
        this.mainCardPanel = mainCardPanel;

        // this.donorDAO = new DonorDAO();

        this.menuPanel = donorGUI.getMenuPanel();
        this.addPanel = donorGUI.getAddPanel();
        this.managePanel = donorGUI.getManagePanel();
        this.detailPanel = donorGUI.getDetailPanel();

        this.managePanel.setController(this);
        addListeners();
    }

    private void addListeners() {
        // Menu Panel Buttons
        menuPanel.getBtnAddRecord().addActionListener(this);
        menuPanel.getBtnManageRecord().addActionListener(this);
        menuPanel.getBtnReturn().addActionListener(this);

        // Add Panel Buttons
        addPanel.getBtnSave().addActionListener(this);
        addPanel.getBtnReturn().addActionListener(this);

        // Manage Panel Buttons
        managePanel.getBtnRefresh().addActionListener(this);
        managePanel.getBtnReturn().addActionListener(this);
        managePanel.getBtnSearch().addActionListener(this);

        // --- Detail Panel Buttons ----
        detailPanel.getBtnReturn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // --- Handle Dynamic Commands (from Manage Panel) ---
        // These are commands like "DONOR_SHOW_DETAIL_1" where 1 is the ID
        if (command.startsWith("DONOR_SHOW_DETAIL_")) {
            int id = extractIdFromCommand(command, "DONOR_SHOW_DETAIL_");
            showDonorDetails(id);
            return; // Exit after handling
        }
        else if (command.startsWith("DONOR_DELETE_")) {
            int id = extractIdFromCommand(command, "DONOR_DELETE_");
            deleteDonor(id);
            return; // Exit after handling
        }
        else if (command.startsWith("DONOR_EDIT_")) {
            int id = extractIdFromCommand(command, "DONOR_EDIT_");
            // You would create a showEditForm(id) method
            System.out.println("EDIT_DONOR with ID: " + id);
            return;
        }

        // --- Handle Static Button Clicks ---
        switch (command) {
            // --- Navigation from Menu ---
            case "DONOR_SHOW_ADD_FORM":
                addPanel.clearForm(); // Clear form before showing
                donorGUI.showPanel("ADD_FORM");
                break;

            case "DONOR_SHOW_MANAGE_LIST":
                refreshDonorList(); // Load data before showing
                donorGUI.showPanel("MANAGE_LIST");
                break;

            case "DONOR_RETURN_TO_MAIN":
                CardLayout cl = (CardLayout) mainCardPanel.getLayout();
                cl.show(mainCardPanel, "MENU"); // Assumes main menu is "MENU"
                break;

            // --- Actions from Add Form ---
            case "DONOR_SAVE_NEW":
                saveNewDonor();
                break;

            // --- Actions from Manage List ---
            case "DONOR_REFRESH_LIST":
                refreshDonorList();
                break;

            // --- Common "Return to Menu" (from Add, Manage) ---
            case "DONOR_RETURN_TO_MENU":
                donorGUI.showPanel("MENU");
                break;

            // --- Actions from Detail Panel ---
            case "DONOR_DETAIL_RETURN":
                donorGUI.showPanel("MANAGE_LIST"); // Go back to the list
                break;

            default:
                System.out.println("Unknown command in DonorController: " + command);
                break;
        }
    }

    // --- Helper to parse commands like "DONOR_DETAIL_12" ---
    private int extractIdFromCommand(String command, String prefix) {
        try {
            return Integer.parseInt(command.substring(prefix.length()));
        } catch (NumberFormatException e) {
            System.err.println("Invalid ID in command: " + command);
            return -1; // Return an invalid ID
        }
    }

    // ==================================================================
    // --- CONTROLLER LOGIC METHODS (interact with DAO and Panels) ---
    // ==================================================================

    /**
     * Fetches data from the DAO and tells the ManagePanel to display it.
     */
    private void refreshDonorList() {
        System.out.println("Refreshing donor list...");

        try {
            // --- REAL DAO CODE ---
            // When you're ready, uncomment this line:
            // List<Donor> donors = donorDAO.getAllDonors();

            // --- SIMULATION CODE (Delete this when your DAO is ready) ---
            // Use ArrayList for compatibility with all Java versions
            // --- HERE IS THE SIMULATEDDONORS CODE ---
            // It's a temporary, local variable inside this method.

            // Use ArrayList for compatibility with all Java versions
            List<Donor> simulatedDonors = new ArrayList<>();

            // Create a few dummy donors to test the UI
            // (Make sure your Donor.java class is fixed and imported)
            Donor d1 = new Donor();
            d1.setDonorId(1); // The ID is crucial!
            d1.setLastName("Dela Cruz");
            d1.setFirstName("Juan");
            d1.setBloodType("O+");
            // Set other fields if your row panel displays them

            Donor d2 = new Donor();
            d2.setDonorId(2);
            d2.setLastName("Bautista");
            d2.setFirstName("Christian");
            d2.setBloodType("AB");

            // Add the fake donors to the list
            simulatedDonors.add(d1);
            simulatedDonors.add(d2);
            // --- End of Simulation ---
            // ==========================================================


            // Pass the list (either real or simulated) to the panel
            // managePanel.displayEntities(donors); // <-- Use this for REAL data
            managePanel.displayEntities(simulatedDonors); // <-- Use this for SIMULATED data

        } catch (Exception e) {
            e.printStackTrace(); // Always good for debugging
            JOptionPane.showMessageDialog(donorGUI,
                    "Error loading donors: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Gets a Donor object from the AddPanel, validates it, and saves it via the DAO.
     */
    private void saveNewDonor() {
        // 1. Get data from View (AddPanel)
        // Donor newDonor = addPanel.getDonorFromForm(); // UNCOMMENT THIS

        // --- SIMULATION ---
        String lName = addPanel.getTxtLastName().getText();
        if (lName.isEmpty()) {
            JOptionPane.showMessageDialog(donorGUI, "Last Name is required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // --- End Simulation ---


        // 2. Validate
        // if (newDonor.getLastName().isEmpty() || newDonor.getFirstName().isEmpty()) {
        //     JOptionPane.showMessageDialog(donorGUI, "First and Last Name are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
        //     return;
        // }

        // 3. Send data to Model (DAO)
        // donorDAO.addDonor(newDonor);

        // 4. Show success and navigate
        JOptionPane.showMessageDialog(donorGUI, "Donor saved successfully!");
        addPanel.clearForm();
        donorGUI.showPanel("MENU");
    }

    /**
     * Fetches a single donor and tells the DetailPanel to display it.
     */
    private void showDonorDetails(int donorId) {
        System.out.println("Showing details for donor ID: " + donorId);
        // 1. Get data from Model (DAO)
        // Donor donor = donorDAO.getDonorById(donorId);

        // 2. Pass data to View (DetailPanel)
        // detailPanel.loadEntityData(donor); // This is the method from AbstractDetailPanel

        // 3. Switch View
        donorGUI.showPanel("DETAIL_PANEL");
    }

    /**
     * Deletes a donor via the DAO after confirmation.
     */
    private void deleteDonor(int donorId) {
        int choice = JOptionPane.showConfirmDialog(
                donorGUI,
                "Are you sure you want to delete this donor (ID: " + donorId + ")?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION) {
            System.out.println("Deleting donor ID: " + donorId);
            // 1. Send command to Model (DAO)
            // donorDAO.deleteDonor(donorId);

            // 2. Show success
            JOptionPane.showMessageDialog(donorGUI, "Donor deleted.");

            // 3. Refresh the list view
            refreshDonorList();
        }
    }
}