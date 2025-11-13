import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DonorController implements ActionListener {

    private DonorGUI donorGUI;
    private JPanel mainCardPanel;

    /**
     * Constructor for the Donor Controller.
     * Takes the DonorGUI instance to attach listeners to its components.
     */
    public DonorController(DonorGUI donorGUI, JPanel mainCardPanel) {
        this.donorGUI = donorGUI;
        this.mainCardPanel = mainCardPanel;
        addListeners();
    }

    /**
     * Attaches this controller as the ActionListener to all buttons in the DonorGUI.
     */
    private void addListeners() {
        // Main Donor Menu Buttons
        donorGUI.getBtnAddRecord().addActionListener(this);
        donorGUI.getBtnViewEditRecord().addActionListener(this);
        donorGUI.getBtnReturn().addActionListener(this);

        donorGUI.getBtnSubmitAddDonor().addActionListener(this);

        donorGUI.getBtnAddReturn().addActionListener(this);
        donorGUI.getBtnViewEditReturn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            // --- Donor Menu GUI  ---
            case "DONOR_ADD":
                donorGUI.showPanel("ADD_FORM");
                break;

            case "DONOR_VIEW_EDIT":
                donorGUI.showPanel("VIEW_EDIT");
                break;

            case "DONOR_SEARCH":
                donorGUI.showPanel("SEARCH");
                break;

            case "DONOR_HISTORY":
                donorGUI.showPanel("HISTORY");
                break;

            // --- Add Donor ---
            case "DONOR_RETURN":
                // Go back to the local Donor Menu
                donorGUI.showPanel("MENU");
                break;

            case "DONOR_SUBMIT_ADD":
                saveDonorData();
                break;

            // --- External Navigation (Back to Main Menu) ---
            case "DONOR_RETURN_MAIN":
                CardLayout cl = (CardLayout) mainCardPanel.getLayout();
                cl.show(mainCardPanel, "MENU");
                break;

            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }

    private void saveDonorData() {
        // 1. Get Data from GUI
        String lName = donorGUI.getTxtLastName().getText();
        String fName = donorGUI.getTxtFirstName().getText();

        // 2. Validate
        if (lName.isEmpty() || fName.isEmpty()) {
            JOptionPane.showMessageDialog(donorGUI, "Please enter First and Last Name.");
            return;
        }

        // 3. Success Simulation
        JOptionPane.showMessageDialog(donorGUI, "Success! Donor " + fName + " " + lName + " added.");

        // 4. Return to Donor Menu
        donorGUI.showPanel("MENU");

        // 5. Clear fields (Optional helper method could go here)
        donorGUI.getTxtFirstName().setText("");
        donorGUI.getTxtLastName().setText("");
    }
}