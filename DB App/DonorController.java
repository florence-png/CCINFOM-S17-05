import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class DonorController{

    private DonorGUI view;
    private DonorDAO donorDAO;

    public DonorController(DonorGUI view, DonorDAO donorDAO){
        this.view = view;
        this.donorDAO = donorDAO;

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
            JOptionPane.showMessageDialog(null, 
                "Return to main menu action is not implemented.");
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
        try{
            String lastName = add.getLastName();
            String firstName = add.getFirstName();
            String contact = add.getContact();
            int age = add.getAge();
            char sex = add.getSex();
            java.util.Date dob = add.getDateOfBirth();
            String bloodType = add.getBloodType();
            String remarks = add.getRemarks();

            donorDAO.addDonor(lastName, firstName, contact, age, sex, dob, bloodType, remarks);

            JOptionPane.showMessageDialog(null, "Donor saved successfully!");

            add.clearForm();
        } 
        catch(Exception e){
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

    private void loadDonorList(){
        List<Donor> list = donorDAO.getAllDonors();
        view.getManagePanel().displayEntities(list);
    }

    private void searchDonors(){
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
