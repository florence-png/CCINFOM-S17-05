import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TechnicianController implements ActionListener {

    private TechnicianGUI technicianGUI;
    private JPanel mainCardPanel;

    /**
     * Constructor for the Technician Controller.
     * Takes the TechnicianGUI instance to attach listeners to its components.
     */
    public TechnicianController(TechnicianGUI technicianGUI, JPanel mainCardPanel) {
        this.technicianGUI = technicianGUI;
        this.mainCardPanel = mainCardPanel;
        addListeners();
    }

    /**
     * Attaches this controller as the ActionListener to all buttons in the TechnicianGUI.
     */
    private void addListeners() {
        // Main Technician Menu Buttons
        technicianGUI.getBtnAddRecord().addActionListener(this);
        technicianGUI.getBtnViewEditRecord().addActionListener(this);
        technicianGUI.getBtnReturn().addActionListener(this);

        technicianGUI.getBtnSubmitAddTechnician().addActionListener(this);

        technicianGUI.getBtnAddReturn().addActionListener(this);
        technicianGUI.getBtnViewEditReturn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        CardLayout cl;

        switch (command) {
            // --- Technician Menu GUI  ---
            case "TECHNICIAN_ADD":
                technicianGUI.showPanel("ADD_FORM");
                break;

            case "TECHNICIAN_VIEW_EDIT":
                technicianGUI.showPanel("VIEW_EDIT");
                break;

            case "TECHNICIAN_SEARCH":
                technicianGUI.showPanel("SEARCH");
                break;

            case "TECHNICIAN_HISTORY":
                technicianGUI.showPanel("HISTORY");
                break;

            case "TECHNICIAN_RETURN":
                // Go back to the local Technician Menu
                technicianGUI.showPanel("MENU");
                break;

            // --- Add Technician ---
            case "TECHNICIAN_SUBMIT_ADD":
                saveTechnicianData();
                break;

            // --- External Navigation (Back to Main Menu) ---
            case "TECHNICIAN_RETURN_MAIN":
                cl = (CardLayout) mainCardPanel.getLayout();
                cl.show(mainCardPanel, "MENU");
                break;

            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }

    private void saveTechnicianData() {
        // 1. Get Data from GUI
        String lName = technicianGUI.getTxtLastName().getText();
        String fName = technicianGUI.getTxtFirstName().getText();

        // 2. Validate
        if (lName.isEmpty() || fName.isEmpty()) {
            JOptionPane.showMessageDialog(technicianGUI, "Please enter First and Last Name.");
            return;
        }

        // 3. Success Simulation
        JOptionPane.showMessageDialog(technicianGUI, "Success! Technician " + fName + " " + lName + " added.");

        // 4. Return to Technician Menu
        technicianGUI.showPanel("MENU");

        // 5. Clear fields (Optional helper method could go here)
        technicianGUI.getTxtFirstName().setText("");
        technicianGUI.getTxtLastName().setText("");
    }
}