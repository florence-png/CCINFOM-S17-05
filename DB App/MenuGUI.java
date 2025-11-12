import javax.swing.*;
import java.awt.*;

public class MenuGUI extends JFrame {
    private JPanel PanelMenu; // Main root panel

    private JButton BtnDonorManagement;
    private JButton BtnTechnicianManagement;
    private JButton btnBloodInventory;
    private JButton btnBranchManagement;
    private JButton btnHospitalManagement;
    private JButton btnTransactions;
    private JButton btnReports;
    private JButton btnExit;

    public MenuGUI() {
        super("Blood Bank Management System");

        this.setSize(400, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        // menu Main panel
        PanelMenu = new JPanel(new BorderLayout(5, 5));

        // Title
        JPanel PanelTitle = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        JLabel LabelTitle = new JLabel("Blood Bank Management System");
        LabelTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        PanelTitle.add(LabelTitle);
        PanelMenu.add(PanelTitle, BorderLayout.NORTH);

        // Buttons panel
        JPanel PanelButtons = new JPanel();
        PanelButtons.setLayout(new GridLayout(8, 1, 5, 5));
        PanelButtons.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));

        // Create and set up all your buttons
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

        // Add the button panel to the center
        PanelMenu.add(PanelButtons, BorderLayout.CENTER);

        this.setContentPane(PanelMenu);
    }

    // Getters
    public JPanel getRootPanel() {
        return PanelMenu;
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

