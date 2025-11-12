import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {

    // Call MenuGUI
    private MenuGUI menuGUI;
    private DonorGUI donorGUI;

    /**
     * The constructor. It takes the View as a parameter
     * so it can connect to it.
     */
    public MenuController(MenuGUI menuGUI) {
        this.menuGUI = menuGUI;

        // Call the method to attach all listeners
        addListeners();
    }

    /**
     * This private method attaches THIS controller
     * as the listener for all buttons in the view.
     */
    private void addListeners() {
        // Use the view's getters to access the buttons and add this controller as the listener
        menuGUI.getBtnDonorManagement().addActionListener(this);
        menuGUI.getBtnTechnicianManagement().addActionListener(this);
        menuGUI.getBtnBloodInventory().addActionListener(this);
        menuGUI.getBtnBranchManagement().addActionListener(this);
        menuGUI.getBtnHospitalManagement().addActionListener(this);
        menuGUI.getBtnTransactions().addActionListener(this);
        menuGUI.getBtnReports().addActionListener(this);
        menuGUI.getBtnExit().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "BTN_DONOR_MGMT":
                JOptionPane.showMessageDialog(menuGUI.getRootPanel(), "Donor Management Clicked!");
                // Future: new DonorController(new DonorView(), ...);
                DonorController donorController = new DonorController(donorGUI);
                break;

            case "BTN_TECH_MGMT":
                JOptionPane.showMessageDialog(menuGUI.getRootPanel(), "Technician Management Clicked!");
                break;

            case "BTN_INV_MGMT":
                JOptionPane.showMessageDialog(menuGUI.getRootPanel(), "Blood Inventory Clicked!");
                break;

            case "BTN_BRANCH_MGMT":
                JOptionPane.showMessageDialog(menuGUI.getRootPanel(), "Branch Management Clicked!");
                break;

            case "BTN_HOSP_MGMT":
                JOptionPane.showMessageDialog(menuGUI.getRootPanel(), "Hospital Management Clicked!");
                break;

            case "BTN_TRANSACTION":
                JOptionPane.showMessageDialog(menuGUI.getRootPanel(), "Transaction Clicked!");
                break;

            case "BTN_REPORTS":
                JOptionPane.showMessageDialog(menuGUI.getRootPanel(), "Reports Clicked!");
                break;

            case "PROGRAM_EXIT":
                handleExit();
                break;

            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }


    /**
     * Helper method to exit
     */
    private void handleExit() {
        int choice = JOptionPane.showConfirmDialog(
                menuGUI.getRootPanel(),
                "Are you sure you want to exit?",
                "Exit Application",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            // Get the top-level window (JFrame) and close it
            Window window = SwingUtilities.getWindowAncestor(menuGUI.getRootPanel());
            if (window != null) {
                window.dispose();
            }
        }
    }
}