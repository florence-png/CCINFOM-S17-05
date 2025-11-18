import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class TechnicianController {

    private TechnicianGUI view;
    private TechnicianDAO technicianDAO;
    private JPanel mainCardPanel;

    public TechnicianController(TechnicianGUI view, JPanel mainCardPanel){
        this.view = view;
        this.technicianDAO = new TechnicianDAO();
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
            loadTechnicianList();
            view.showPanel("MANAGE_LIST");
        });

        // Return to Main Menu
        menu.getBtnReturn().addActionListener(e -> {
            CardLayout cl = (CardLayout) mainCardPanel.getLayout();
            cl.show(mainCardPanel, "MENU");
        });
    }

    // Add Technician Panel
    private void setupAddPanel() {
        var add = view.getAddPanel();

        // Save button
        add.getBtnSave().addActionListener(e -> saveTechnician());

        // Return to Menu
        add.getBtnReturn().addActionListener(e -> view.showPanel("MENU"));
    }

    private void saveTechnician(){
        var add = view.getAddPanel();
        try{
            String lastName = add.getTxtLastName().getText().trim();
            String firstName = add.getTxtFirstName().getText().trim();
            String email = add.getTxtEmail().getText().trim();
            String contact = add.getTxtContactNumber().getText().trim();

            int age = Integer.parseInt(add.getTxtAge().getText().trim());

            // Get sex from combo box
            String sexStr = (String) add.getComboSex().getSelectedItem();
            char sex = sexStr.equals("Male") ? 'M' : 'F';

            // Parse date employed
            String dateEmployedStr = add.getTxtDateEmployed().getText().trim();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dateEmployed = sdf.parse(dateEmployedStr);

            // Validate required fields
            if(lastName.isEmpty() || firstName.isEmpty() || dateEmployedStr.isEmpty()){
                throw new IllegalArgumentException("Last name, first name, and date employed are required.");
            }

            // Save to database
            technicianDAO.addTechnician(lastName, firstName, email, contact, age, sex, dateEmployed);

            JOptionPane.showMessageDialog(null, "Technician saved successfully!");
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

    // Manage Technician Panel
    private void setupManagePanel() {
        var manage = view.getManagePanel();

        // Refresh List
        manage.getBtnRefresh().addActionListener(e -> loadTechnicianList());

        manage.getBtnReturn().addActionListener(e -> view.showPanel("MENU"));

        manage.getBtnSearch().addActionListener(e -> searchTechnicians());
    }

    private void loadTechnicianList() {
        List<Technician> list = technicianDAO.getAllTechnicians();
        view.getManagePanel().displayEntities(list);
    }

    private void searchTechnicians() {
        String query = view.getManagePanel().getTxtSearch().getText().trim();
        if(query.isEmpty()){
            loadTechnicianList();
            return;
        }

        // Search filter
        List<Technician> all = technicianDAO.getAllTechnicians();
        List<Technician> filtered = all.stream()
                .filter(d -> (d.getFirstName() + " " + d.getLastName()).toLowerCase().contains(query.toLowerCase()))
                .toList();

        view.getManagePanel().displayEntities(filtered);
    }

    // Detail Panel
    private void setupDetailPanel(){
        var detail = view.getDetailPanel();
        detail.getBtnReturn().addActionListener(e -> view.showPanel("MANAGE_LIST"));
    }


//    private void refreshTechnicianList() {
//        List<Technician> list = technicianDAO.getAllTechnicians();
//        managePanel.displayEntities(list);
//    }
//
//    private void searchTechnicians(){
//        String query = view.getManagePanel().getTxtSearch().getText().trim();
//        if(query.isEmpty()){
//            loadTechnicianlist();
//            return;
//        }
//
//        List<Technician> all = technicianDAO.getAllTechnicians();
//        List<Technician> filtered = all.stream()
//                .filter(t -> (t.getFirstName() + " " + t.getLastName()).toLowerCase().contains(query.toLowerCase()))
//                .toList();
//
//        view.getManagePanel().displayEntities(filtered);
//    }
//    private void openDetails(int technicianId){
//        Technician tech = technicianDAO.getAllTechnicians().stream()
//                .filter(t -> t.getTechnicianId() == technicianId)
//                .findFirst()
//                .orElse(null);
//
//        if(tech != null){
//            detailPanel.loadEntityData(tech);
//            showPanel("DETAIL_PANEL");
//        }
//    }
//
//    private void deleteTechnician(int technicianId){
//        int confirm = JOptionPane.showConfirmDialog(null,
//                "Delete this technician?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
//
//        if(confirm == JOptionPane.YES_OPTION){
//            technicianDAO.setTechnicianStatus(technicianId, "Deleted");
//            refreshTechnicianList();
//        }
//    }

//    public void actionPerformed(ActionEvent e){
//        String command = e.getActionCommand();
//
//        // --- Handle dynamic row buttons ---
//        if (command.startsWith("TECHNICIAN_SHOW_DETAIL_")) {
//            int id = Integer.parseInt(command.substring("TECHNICIAN_SHOW_DETAIL_".length()));
//            openDetails(id);
//            return;
//        } else if (command.startsWith("TECHNICIAN_DELETE_")) {
//            int id = Integer.parseInt(command.substring("TECHNICIAN_DELETE_".length()));
//            deleteTechnician(id);
//            return;
//        } else if (command.startsWith("TECHNICIAN_EDIT_")) {
//            int id = Integer.parseInt(command.substring("TECHNICIAN_EDIT_".length()));
//            // Optional: add edit logic
//            System.out.println("Edit Technician ID: " + id);
//            return;
//        }
//
//        // Handle static buttons
//        switch(command){
//            case "TECHNICIAN_SHOW_ADD_FORM":
//                addPanel.clearForm();
//                showPanel("ADD_FORM");
//                break;
//
//            case "TECHNICIAN_SHOW_MANAGE_LIST":
//                refreshTechnicianList();
//                showPanel("MANAGE_LIST");
//                break;
//
//            case "TECHNICIAN_RETURN_TO_MAIN":
//                showPanel("MENU");
//                break;
//
//            case "TECHNICIAN_SAVE_NEW":
//                saveTechnician();
//                break;
//
//            case "TECHNICIAN_REFRESH_LIST":
//                refreshTechnicianList();
//                break;
//
//            case "TECHNICIAN_SEARCH":
//                searchTechnicians();
//                break;
//
//            case "TECHNICIAN_RETURN_TO_MENU":
//                showPanel("MENU");
//                break;
//
//            case "TECHNICIAN_DETAIL_RETURN":
//                showPanel("MANAGE_LIST");
//                break;
//
//            default:
//                System.out.println("Unknown command: " + command);
//                break;
//        }
//    }
}
