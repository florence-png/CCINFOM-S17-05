import javax.swing.*;
import java.awt.*;

public class MenuGUI extends JPanel {
    private JButton BtnDonorManagement;
    private JButton BtnTechnicianManagement;
    private JButton btnBloodInventory;
    private JButton btnBranchManagement;
    private JButton btnHospitalManagement;
    private JButton btnTransactions;
    private JButton btnReports;
    private JButton btnExit;

    public MenuGUI() {
        // menu panel
        this.setLayout(new BorderLayout(5, 5));

        // Title
        JPanel PanelTitle = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        JLabel LabelTitle = new JLabel("Blood Bank Management System");
        LabelTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        PanelTitle.add(LabelTitle);
        this.add(PanelTitle, BorderLayout.NORTH);

        // Buttons panel
        JPanel PanelButtons = new JPanel();
        PanelButtons.setLayout(new GridLayout(8, 1, 5, 5));
        PanelButtons.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));

        // Menu Buttons
        BtnDonorManagement = new JButton("Donor Management");
        BtnDonorManagement.setActionCommand("BTN_DONOR_MGMT");

        BtnTechnicianManagement = new JButton("Technician Management");
        BtnTechnicianManagement.setActionCommand("BTN_TECH_MGMT");

        btnBloodInventory = new JButton("Blood Inventory");
        btnBloodInventory.setActionCommand("BTN_INV_MGMT");

        btnBranchManagement = new JButton("Branch Management");
        btnBranchManagement.setActionCommand("BTN_BRANCH_MGMT");

        btnHospitalManagement = new JButton("Hospital Management");
        btnHospitalManagement.setActionCommand("BTN_HOSP_MGMT");

        btnTransactions = new JButton("Transaction");
        btnTransactions.setActionCommand("BTN_TRANSACTION");

        btnReports = new JButton("Reports");
        btnReports.setActionCommand("BTN_REPORTS");

        btnExit = new JButton("Exit");
        btnExit.setActionCommand("PROGRAM_EXIT");

        // Add buttons to the panel
        PanelButtons.add(BtnDonorManagement);
        PanelButtons.add(BtnTechnicianManagement);
        PanelButtons.add(btnBloodInventory);
        PanelButtons.add(btnBranchManagement);
        PanelButtons.add(btnHospitalManagement);
        PanelButtons.add(btnTransactions);
        PanelButtons.add(btnReports);
        PanelButtons.add(btnExit);

        // Add button panel to the center
        this.add(PanelButtons, BorderLayout.CENTER);
    }

    // Getters
    public JPanel getRootPanel() {
        return this;
    }
    public JButton getBtnDonorManagement() {
        return BtnDonorManagement;
    }
    public JButton getBtnTechnicianManagement() {
        return BtnTechnicianManagement;
    }
    public JButton getBtnBloodInventory() {
        return btnBloodInventory;
    }
    public JButton getBtnBranchManagement() {
        return btnBranchManagement;
    }
    public JButton getBtnHospitalManagement() {
        return btnHospitalManagement;
    }
    public JButton getBtnTransactions() {
        return btnTransactions;
    }
    public JButton getBtnReports() {
        return btnReports;
    }
    public JButton getBtnExit() {
        return btnExit;
    }
}

