import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {

    private MenuGUI menuGUI;
    private JPanel mainCardPanel;

    public MenuController(MenuGUI menuGUI, JPanel mainCardPanel ) {
        this.menuGUI = menuGUI;
        this.mainCardPanel = mainCardPanel;
        addListeners();
    }

    private void addListeners() {
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
        CardLayout cl;
        switch (command) {
            case "BTN_DONOR_MGMT":
                cl = (CardLayout) (mainCardPanel.getLayout());
                cl.show(mainCardPanel, "DONOR_GUI");
                break;
            case "BTN_TECH_MGMT":
                cl = (CardLayout) (mainCardPanel.getLayout());
                cl.show(mainCardPanel, "TECHNICIAN_GUI");
                break;
            case "BTN_BRANCH_MGMT":
                JOptionPane.showMessageDialog(menuGUI.getRootPanel(), "Branch Management Clicked!");
                break;

            case "BTN_HOSP_MGMT":
                cl = (CardLayout) (mainCardPanel.getLayout());
                cl.show(mainCardPanel, "HOSPITAL_GUI");
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