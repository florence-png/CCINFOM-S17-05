//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class DonorController implements ActionListener {
//
//    private DonorGUI inventoryGUI;
//    private JPanel mainCardPanel;
//
//    /**
//     * Constructor for the Donor Controller.
//     * Takes the DonorGUI instance to attach listeners to its components.
//     */
//    public DonorController(DonorGUI inventoryGUI, JPanel mainCardPanel) {
//        this.inventoryGUI = inventoryGUI;
//        this.mainCardPanel = mainCardPanel;
//        addListeners();
//    }
//
//    /**
//     * Attaches this controller as the ActionListener to all buttons in the DonorGUI.
//     */
//    private void addListeners() {
//        // Main Donor Menu Buttons
//        inventoryGUI.getBtnAddRecord().addActionListener(this);
//        inventoryGUI.getBtnViewEditRecord().addActionListener(this);
//        inventoryGUI.getBtnReturn().addActionListener(this);
//
//        inventoryGUI.getBtnSubmitAddDonor().addActionListener(this);
//
//        inventoryGUI.getBtnAddReturn().addActionListener(this);
//        inventoryGUI.getBtnViewEditReturn().addActionListener(this);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String command = e.getActionCommand();
//        CardLayout cl;
//
//        switch (command) {
//            // --- Donor Menu GUI  ---
//            case "INVENTORY_ADD":
//                inventoryGUI.showPanel("ADD_FORM");
//                break;
//
//            case "INVENTORY_VIEW_EDIT":
//                inventoryGUI.showPanel("VIEW_EDIT");
//                break;
//
//            case "INVENTORY_SEARCH":
//                inventoryGUI.showPanel("SEARCH");
//                break;
//
//            case "INVENTORY_HISTORY":
//                inventoryGUI.showPanel("HISTORY");
//                break;
//
//            case "INVENTORY_RETURN":
//                // Go back to the local Donor Menu
//                inventoryGUI.showPanel("MENU");
//                break;
//
//            // --- Add Donor ---
//            case "INVENTORY_SUBMIT_ADD":
//                break;
//
//            // --- External Navigation (Back to Main Menu) ---
//            case "INVENTORY_RETURN_MAIN":
//                cl = (CardLayout) mainCardPanel.getLayout();
//                cl.show(mainCardPanel, "MENU");
//                break;
//
//            default:
//                System.out.println("Unknown command: " + command);
//                break;
//        }
//    }
//}