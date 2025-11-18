import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class DonorController {

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
    private void setupAddPanel() {
        var add = view.getAddPanel();

        // Save button
        add.getBtnSave().addActionListener(e -> saveDonor());

        // Return to Menu
        add.getBtnReturn().addActionListener(e -> view.showPanel("MENU"));
    }

    private void saveDonor() {
        var add = view.getAddPanel();
        try {
            String lastName = add.getTxtLastName().getText().trim();
            String firstName = add.getTxtFirstName().getText().trim();
            String email = add.getTxtEmail().getText().trim();
            String contactNumber = add.getTxtContactNumber().getText().trim();

            // Get sex from combo box
            String sexStr = (String) add.getComboSex().getSelectedItem();
            char sex = sexStr.equals("Male") ? 'M' : 'F'; // M is male, anything else is female

            // Parse birthdate (format: yyyy-MM-dd)
            String birthdateStr = add.getTxtBirthdate().getText().trim();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date birthdate = sdf.parse(birthdateStr);

            // Calculate age from birthdate
            java.util.Calendar birthCal = java.util.Calendar.getInstance();
            birthCal.setTime(birthdate);
            java.util.Calendar today = java.util.Calendar.getInstance();
            int age = today.get(java.util.Calendar.YEAR) - birthCal.get(java.util.Calendar.YEAR);
            if (today.get(java.util.Calendar.DAY_OF_YEAR) < birthCal.get(java.util.Calendar.DAY_OF_YEAR)) {
                age--;
            }

            // Get blood type
            String bloodType = (String) add.getComboBloodType().getSelectedItem();

            // Get remarks
            String remarks = add.getTxtRemarks().getText().trim();

            // Validate required fields
            if (lastName.isEmpty() || firstName.isEmpty()) {
                throw new IllegalArgumentException("Last name and first name are required.");
            }
            if (birthdateStr.isEmpty()) {
                throw new IllegalArgumentException("Birthdate is required (format: yyyy-MM-dd).");
            }

            // Save to database
            donorDAO.addDonor(lastName, firstName, email, contactNumber, age, sex,
                    birthdate, bloodType, remarks);

            JOptionPane.showMessageDialog(null, "Donor saved successfully!");
            add.clearForm();

        } catch (java.text.ParseException e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid date format. Please use yyyy-MM-dd (e.g., 2000-01-15)",
                    "Date Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(),
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Manage Donor Panel
    private void setupManagePanel(){
        var manage = view.getManagePanel();

        // Refresh list
        manage.getBtnRefresh().addActionListener(e -> loadDonorList());

        // Return -> Menu
        manage.getBtnReturn().addActionListener(e -> view.showPanel("MENU"));

        // Search
        manage.getBtnSearch().addActionListener(e -> searchDonors());
    }

    private void loadDonorList() {
        List<Donor> list = donorDAO.getAllDonors();
        view.getManagePanel().displayEntities(list);
    }

    private void searchDonors() {
        String query = view.getManagePanel().getTxtSearch().getText().trim();
        if(query.isEmpty()){
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
    private void setupDetailPanel(){
        var detail = view.getDetailPanel();
        detail.getBtnReturn().addActionListener(e -> view.showPanel("MANAGE_LIST"));
    }

    // Called by ManageDonorPanel row buttons
    public void openDetails(Donor donor){
        view.getDetailPanel().loadEntityData(donor);
        view.showPanel("DETAIL_PANEL");
    }

    // Called by ManageDonorPanel row buttons
    public void deleteDonor(Donor donor){
        int confirm = JOptionPane.showConfirmDialog(null,
                "Delete donor " + donor.getFirstName() + " " + donor.getLastName() + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if(confirm == JOptionPane.YES_OPTION){
            donorDAO.deleteDonor(donor.getDonorId());
            loadDonorList();
        }
    }
}
