import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

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

        // Add Technician -> Add Form
        menu.getBtnAddRecord().addActionListener(e -> {
            view.getAddPanel().clearForm();
            view.showPanel("ADD_FORM");
        });

        // Manage Technicians -> Manage List
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
    private void setupAddPanel(){
        var add = view.getAddPanel();

        // Save button
        add.getBtnSave().addActionListener(e -> saveTechnician());

        // Return to Menu
        add.getBtnReturn().addActionListener(e -> view.showPanel("MENU"));
    }

    private void saveTechnician(){
        var add = view.getAddPanel();

        try {
            String lastName = add.getTxtLastName().getText().trim();
            String firstName = add.getTxtFirstName().getText().trim();
            String email = add.getTxtEmail().getText().trim();
            String contactNumber = add.getTxtContactNumber().getText().trim();
            String dateStr = add.getTxtDateEmployed().getText().trim();

            // VALIDATE INPUT
            if (lastName.isEmpty() || firstName.isEmpty()) {
                throw new IllegalArgumentException("Last name and first name are required.");
            }
            if (dateStr.isEmpty()) {
                throw new IllegalArgumentException("Date employed is required.");
            }

            // PARSE DATE
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dateUtil = sdf.parse(dateStr);
            java.sql.Date dateEmployed = new java.sql.Date(dateUtil.getTime());

            // SAVE TO DB
            technicianDAO.addTechnician(lastName, firstName, email, contactNumber, dateEmployed);

            JOptionPane.showMessageDialog(null, "Technician saved successfully!");
            add.clearForm();

        } catch (java.text.ParseException e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid date format. Use yyyy-MM-dd.",
                    "Date Error", JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error: " + e.getMessage(),
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MANAGE PANEL SETUP
    private void setupManagePanel(){
        var manage = view.getManagePanel();

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

        if (query.isEmpty()) {
            loadTechnicianList();
            return;
        }

        List<Technician> all = technicianDAO.getAllTechnicians();
        List<Technician> filtered = all.stream()
                .filter(t -> (t.getFirstName() + " " + t.getLastName())
                        .toLowerCase()
                        .contains(query.toLowerCase()))
                .toList();

        view.getManagePanel().displayEntities(filtered);
    }

    // DETAIL PANEL SETUP
    private void setupDetailPanel(){
        var detail = view.getDetailPanel();
        detail.getBtnReturn().addActionListener(e -> view.showPanel("MANAGE_LIST"));
    }

    public void openDetails(Technician technician){
        view.getDetailPanel().loadEntityData(technician);
        view.showPanel("DETAIL_PANEL");
    }

    public void deleteTechnician(Technician technician){
        int confirm = JOptionPane.showConfirmDialog(null,
                "Delete technician " + technician.getFirstName() + " " + technician.getLastName() + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if(confirm == JOptionPane.YES_OPTION){
            technicianDAO.deleteTechnician(technician.getTechnicianId());
            loadTechnicianList();
        }
    }
}