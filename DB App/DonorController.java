import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date; // Needed for interacting with the Donor model

public class DonorController implements ActionListener {

    private DonorGUI donorGUI;
    // private DonorData donorModel; // Future: This will be your JDBC/Model class

    /**
     * Constructor for the Donor Controller.
     * Takes the DonorGUI instance to attach listeners to its components.
     */
    public DonorController(DonorGUI donorGUI /*, DonorData donorModel */) {
        this.donorGUI = donorGUI;
        // this.donorModel = donorModel; // Future

        addListeners();
    }

    /**
     * Attaches this controller as the ActionListener to all buttons in the DonorGUI.
     */
    private void addListeners() {
        // Main Donor Menu Buttons
        donorGUI.getBtnAddRecord().addActionListener(this);
        donorGUI.getBtnViewEditRecord().addActionListener(this);
        donorGUI.getBtnSearchDonor().addActionListener(this);
        donorGUI.getBtnViewHistory().addActionListener(this);
        donorGUI.getBtnReturn().addActionListener(this);

        // Sub-Panel Buttons (e.g., the Submit button on the Add form)
        // donorGUI.getBtnSubmitAddDonor().addActionListener(this); // You would add this later
    }

    /**
     * Handles all button clicks from the DonorGUI and directs the application flow.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "DONOR_ADD":
                System.out.println("Action: Displaying Add Donor Form.");
                donorGUI.showPanel("ADD_FORM");
                break;

            case "DONOR_VIEW_EDIT":
                System.out.println("Action: Displaying View/Edit Donor Records.");
                donorGUI.showPanel("VIEW_EDIT");
                break;

            case "DONOR_SEARCH":
                System.out.println("Action: Displaying Search Donor Interface.");
                donorGUI.showPanel("SEARCH");
                break;

            case "DONOR_HISTORY":
                System.out.println("Action: Displaying Donation History.");
                donorGUI.showPanel("HISTORY");
                break;

            case "DONOR_RETURN_MAIN":
                System.out.println("Action: Returning to Main Menu.");
                // Future: This would call the main MenuController or change the main JFrame's card.
                break;

            case "DONOR_SUBMIT_ADD":
                // handleAddDonor(); // Future: This method will call the DonorModel (JDBC)
                JOptionPane.showMessageDialog(donorGUI, "Submit button placeholder clicked.");
                break;

            default:
                System.out.println("[DONOR CONTROLLER] Unhandled command: " + command);
                break;
        }
    }

    // Future: Method to call the model to save data
    /*
    private void handleAddDonor() {
        // 1. Get data from the form (e.g., donorGUI.getLastNameInput())
        String lastName = donorGUI.getLastNameInput();
        // 2. Validate data
        // 3. Create Donor object
        Donor newDonor = new Donor(lastName, ...);
        // 4. Call model to save
        // donorModel.saveDonor(newDonor);
    }
    */
}