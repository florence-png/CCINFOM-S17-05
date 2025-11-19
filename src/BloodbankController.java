import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class BloodbankController {
    private BloodbankGUI view;
    private BloodbankDAO bloodbankDAO;
    private JPanel mainCardPanel;

    public BloodbankController(BloodbankGUI view, JPanel mainCardPanel) {
        this.view = view;
        this.bloodbankDAO = new BloodbankDAO();
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
            loadBloodbankList();
            view.showPanel("MANAGE_LIST");
        });

        // Return to Main Menu
        menu.getBtnReturn().addActionListener(e -> {
            CardLayout cl = (CardLayout) mainCardPanel.getLayout();
            cl.show(mainCardPanel, "MENU");
        });
    }

    // Add Bloodbank Panel
    private void setupAddPanel() {
        var add = view.getAddPanel();

        // Save button
        add.getBtnSave().addActionListener(e -> saveBloodbank());

        // Return to Menu
        add.getBtnReturn().addActionListener(e -> view.showPanel("MENU"));
    }

    private void saveBloodbank(){
        var add = view.getAddPanel();
        try{
            String hospitalName = add.getTxtBranchName().getText().trim();
            String street = add.getTxtStreet().getText().trim();
            String city = add.getTxtCity().getText().trim();
            String contact = add.getTxtContactNumber().getText().trim();

            // Save to database
            bloodbankDAO.addBloodbank(hospitalName, street, city, contact);

            JOptionPane.showMessageDialog(null, "Bloodbank saved successfully!");
            add.clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(),
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Manage Bloodbank Panel
    private void setupManagePanel() {
        var manage = view.getManagePanel();

        ActionListener listButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();

                // Handle "Details" button
                if (command.startsWith("HOSPITAL_SHOW_DETAIL_")) {
                    int id = Integer.parseInt(command.substring("HOSPITAL_SHOW_DETAIL_".length()));
                    openDetails(id);
                }
                // Handle "Delete" button
                else if (command.startsWith("HOSPITAL_DELETE_")) {
                    int id = Integer.parseInt(command.substring("HOSPITAL_DELETE_".length()));
                    deleteBloodbank(id);
                }
                // Handle "Edit" button (to be made)
                else if (command.startsWith("HOSPITAL_EDIT_")) {
                    int id = Integer.parseInt(command.substring("HOSPITAL_EDIT_".length()));
                    System.out.println("Edit requested for ID: " + id);
                    // TODO: make this button
                }
            }
        };

        // Pass this specific listener to the ManagePanel
        manage.setController(listButtonListener);

        // Static buttons (Refresh, Return, Search)
        manage.getBtnReturn().addActionListener(e -> view.showPanel("MENU"));
        manage.getBtnRefresh().addActionListener(e -> loadBloodbankList());
        manage.getBtnSearch().addActionListener(e -> searchBloodbanks());
    }

    private void openDetails(int bloodbankId) {
        Bloodbank bloodbank = bloodbankDAO.getBloodbankById(bloodbankId);
        if (bloodbank != null) {
            view.getDetailPanel().loadEntityData(hospital);
            view.showPanel("DETAIL_PANEL");
        } else {
            JOptionPane.showMessageDialog(null, "Bloodbank not found.");
        }
    }

    private void deleteBloodbank(int bloodbankId) {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete this hospital?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            bloodbankDAO.deleteBloodbank(bloodbankId);
            loadBloodbankList(); // Refresh the list
        }
    }

    private void loadBloodbankList() {
        List<Bloodbank> list = bloodbankDAO.getAllBloodbankhs();
        view.getManagePanel().displayEntities(list);
    }

    private void searchBloodbanks() {
        String query = view.getManagePanel().getTxtSearch().getText().trim();
        if(query.isEmpty()){
            loadBloodbankList();
            return;
        }

        // Search filter
        List<Bloodbank> all = bloodbankDAO.getAllBloodbank();
        List<Bloodbank> filtered = all.stream()
                .filter(t -> (t.getBranchName()).toLowerCase().contains(query.toLowerCase()))
                .toList();

        view.getManagePanel().displayEntities(filtered);
    }

    // Detail Panel
    private void setupDetailPanel(){
        var detail = view.getDetailPanel();
        detail.getBtnReturn().addActionListener(e -> view.showPanel("MANAGE_LIST"));
    }
}
