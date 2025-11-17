import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.List;
import javax.swing.*;
import java.text.SimpleDateFormat;

public class DonorController{

    private DonorGUI view;
    private DonorDAO donorDAO;
    private JPanel mainCardPanel;

    public DonorController(DonorGUI view, JPanel mainCardPanel){
        this.view = view;
        this.donorDAO = new DonorDAO();
        this.mainCardPanel = mainCardPanel;

        setupMenuPanel();
        setupAddPanel();
        setupManagePanel();
        setupDetailPanel();
    }

    // Menu Panel
    private void setupMenuPanel(){
        var menu = view.getMenuPanel();

        // Add Donor -> Add Form
        menu.getBtnAddRecord().addActionListener(e -> {
            view.getAddPanel().clearForm();
            view.showPanel("ADD_FORM");
        });

        // Manage Donors -> Manage List
        menu.getBtnManageRecord().addActionListener(e -> {
            loadDonorList();
            view.showPanel("MANAGE_LIST");
        });

        // Return to Main Menu
        menu.getBtnReturn().addActionListener(e -> {
            CardLayout cl = (CardLayout) mainCardPanel.getLayout();
            cl.show(mainCardPanel, "MENU");
        });
    }

    // Add Donor Panel
    private void setupAddPanel(){
        var add = view.getAddPanel();

        // Save button
        add.getBtnSave().addActionListener(e -> saveDonor());

        // Return to Menu
        add.getBtnReturn().addActionListener(e -> view.showPanel("MENU"));
    }

    private void saveDonor(){
        var add = view.getAddPanel();
        try {
            String lastName = add.getTxtLastName().getText().trim();
            String firstName = add.getTxtFirstName().getText().trim();
            String email = add.getTxtEmail().getText().trim();
            String contactNumber = add.getTxtContactNumber().getText().trim();

            // Get sex from combo box
            String sexStr = (String) add.getComboSex().getSelectedItem();
            char sex = sexStr.equals("Male") ? 'M' : 'F';

            // Get birthdate from JDatePicker
            Calendar cal = (Calendar) add.getDatePicker().getModel().getValue();
            if (cal == null) {
                throw new IllegalArgumentException("Birthdate is required.");
            }
            java.util.Date birthdate = cal.getTime();

            // Calculate age from birthdate
            Calendar birthCal = Calendar.getInstance();
            birthCal.setTime(birthdate);
            Calendar today = Calendar.getInstance();
            int age = today.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
            if (today.get(Calendar.DAY_OF_YEAR) < birthCal.get(Calendar.DAY_OF_YEAR)) {
                age--;
            }

            // Get blood type
            String bloodType = (String) add.getComboBloodType().getSelectedItem();

            // Get remarks
            String remarks = add.getTxtRemarks().getText().trim();

            // Save to database
            donorDAO.addDonor(lastName, firstName, email, contactNumber, age, sex,
                    birthdate, bloodType, remarks);

            JOptionPane.showMessageDialog(null, "Donor saved successfully!");
            add.clearForm();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(),
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Manage Donor Panel
    private void setupManagePanel() {
        var manage = view.getManagePanel();

        // Pass this controller to the panel so it can attach to dynamic buttons
        manage.setController(e -> handleManageAction(e));

        // Refresh list
        manage.getBtnRefresh().addActionListener(e -> loadDonorList());

        // Return -> Menu
        manage.getBtnReturn().addActionListener(e -> view.showPanel("MENU"));

        // Search
        manage.getBtnSearch().addActionListener(e -> searchDonors());
    }

    // Handle actions from dynamic buttons in ManageDonorPanel
    private void handleManageAction(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.startsWith("DONOR_SHOW_DETAIL_")) {
            int id = Integer.parseInt(cmd.replace("DONOR_SHOW_DETAIL_", ""));
            Donor donor = donorDAO.getDonorById(id);
            if (donor != null) {
                openDetails(donor);
            }
        } else if (cmd.startsWith("DONOR_EDIT_")) {
            int id = Integer.parseInt(cmd.replace("DONOR_EDIT_", ""));
            Donor donor = donorDAO.getDonorById(id);
            if (donor != null) {
                // TODO: Implement edit functionality
                JOptionPane.showMessageDialog(null, "Edit functionality not yet implemented.");
            }
        } else if (cmd.startsWith("DONOR_DELETE_")) {
            int id = Integer.parseInt(cmd.replace("DONOR_DELETE_", ""));
            Donor donor = donorDAO.getDonorById(id);
            if (donor != null) {
                deleteDonor(donor);
            }
        }
    }

    private void loadDonorList() {
        List<Donor> list = donorDAO.getAllDonors();
        view.getManagePanel().displayEntities(list);
    }

    private void searchDonors() {
        String query = view.getManagePanel().getTxtSearch().getText().trim();
        if (query.isEmpty()) {
            loadDonorList();
            return;
        }

        // Search filter
        List<Donor> all = donorDAO.getAllDonors();
        List<Donor> filtered = all.stream()
                .filter(d -> (d.getFirstName() + " " + d.getLastName()).toLowerCase().contains(query.toLowerCase()))
                .toList();

        view.getManagePanel().displayEntities(filtered);
    }

    // Detail Panel
    private void setupDetailPanel() {
        var detail = view.getDetailPanel();
        detail.getBtnReturn().addActionListener(e -> view.showPanel("MANAGE_LIST"));
    }

    // Called by ManageDonorPanel row buttons
    public void openDetails(Donor donor) {
        view.getDetailPanel().loadEntityData(donor);
        view.showPanel("DETAIL_PANEL");
    }

    // Called by ManageDonorPanel row buttons
    public void deleteDonor(Donor donor) {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Delete donor " + donor.getFirstName() + " " + donor.getLastName() + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            donorDAO.deleteDonor(donor.getDonorId());
            loadDonorList();
        }
    }
}