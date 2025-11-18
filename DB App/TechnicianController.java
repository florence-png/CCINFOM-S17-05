import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class TechnicianController implements ActionListener{

    private JPanel mainCardPanel;
    private TechnicianMenuPanel menuPanel;
    private TechnicianAddPanel addPanel;
    private TechnicianManagePanel managePanel;
    private TechnicianDetailPanel detailPanel;
    private TechnicianDAO technicianDAO;

    public TechnicianController(JPanel mainCardPanel, TechnicianMenuPanel menuPanel, TechnicianAddPanel addPanel, TechnicianManagePanel managePanel, TechnicianDetailPanel detailPanel){
        this.mainCardPanel = mainCardPanel;
        this.menuPanel = menuPanel;
        this.addPanel = addPanel;
        this.managePanel = managePanel;
        this.detailPanel = detailPanel;
        this.technicianDAO = new TechnicianDAO();
        setupListeners();
    }

    private void setupListeners(){
        // Menu Panel
        menuPanel.getBtnAddRecord().addActionListener(this);
        menuPanel.getBtnManageRecord().addActionListener(this);
        menuPanel.getBtnReturn().addActionListener(this);

        // Add Panel
        addPanel.getBtnSave().addActionListener(this);
        addPanel.getBtnReturn().addActionListener(this);

        // Manage Panel
        managePanel.getBtnRefresh().addActionListener(this);
        managePanel.getBtnReturn().addActionListener(this);
        managePanel.getBtnSearch().addActionListener(this);

        // Attach controller for dynamic row buttons
        managePanel.setController(this);

        // Detail Panel
        detailPanel.getBtnReturn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();

        // --- Handle dynamic row buttons ---
        if (command.startsWith("TECHNICIAN_SHOW_DETAIL_")) {
            int id = Integer.parseInt(command.substring("TECHNICIAN_SHOW_DETAIL_".length()));
            openDetails(id);
            return;
        } else if (command.startsWith("TECHNICIAN_DELETE_")) {
            int id = Integer.parseInt(command.substring("TECHNICIAN_DELETE_".length()));
            deleteTechnician(id);
            return;
        } else if (command.startsWith("TECHNICIAN_EDIT_")) {
            int id = Integer.parseInt(command.substring("TECHNICIAN_EDIT_".length()));
            // Optional: add edit logic
            System.out.println("Edit Technician ID: " + id);
            return;
        }

        // Handle static buttons
        switch(command){
            case "TECHNICIAN_SHOW_ADD_FORM":
                addPanel.clearForm();
                showPanel("ADD_FORM");
                break;

            case "TECHNICIAN_SHOW_MANAGE_LIST":
                refreshTechnicianList();
                showPanel("MANAGE_LIST");
                break;

            case "TECHNICIAN_RETURN_TO_MAIN":
                showPanel("MENU");
                break;

            case "TECHNICIAN_SAVE_NEW":
                saveTechnician();
                break;

            case "TECHNICIAN_REFRESH_LIST":
                refreshTechnicianList();
                break;

            case "TECHNICIAN_SEARCH":
                searchTechnicians();
                break;

            case "TECHNICIAN_RETURN_TO_MENU":
                showPanel("MENU");
                break;

            case "TECHNICIAN_DETAIL_RETURN":
                showPanel("MANAGE_LIST");
                break;

            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }

    private void showPanel(String name){
        CardLayout cl = (CardLayout) mainCardPanel.getLayout();
        cl.show(mainCardPanel, name);
    }

    private void saveTechnician(){
        try{
            String lastName = addPanel.getTxtLastName().getText().trim();
            String firstName = addPanel.getTxtFirstName().getText().trim();
            String email = addPanel.getTxtEmail().getText().trim();
            String contact = addPanel.getTxtContactNumber().getText().trim();
            String dateEmployedStr = addPanel.getTxtDateEmployed().getText().trim();

            if(lastName.isEmpty() || firstName.isEmpty() || dateEmployedStr.isEmpty()){
                throw new IllegalArgumentException("Last name, first name, and date employed are required.");
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dateEmployed = sdf.parse(dateEmployedStr);

            technicianDAO.addTechnician(lastName, firstName, email, contact, dateEmployed);
            JOptionPane.showMessageDialog(null, "Technician saved successfully!");
            addPanel.clearForm();
            showPanel("MENU");

        }
        catch(java.text.ParseException e){
            JOptionPane.showMessageDialog(null, "Invalid date format. Use yyyy-MM-dd", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshTechnicianList(){
        List<Technician> list = technicianDAO.getAllTechnicians();
        managePanel.displayEntities(list);
    }

    private void searchTechnicians(){
        String query = managePanel.getTxtSearch().getText().trim();
        if(query.isEmpty()){
            refreshTechnicianList();
            return;
        }

        List<Technician> all = technicianDAO.getAllTechnicians();
        List<Technician> filtered = all.stream()
                .filter(t -> (t.getFirstName() + " " + t.getLastName())
                        .toLowerCase().contains(query.toLowerCase()))
                .toList();

        managePanel.displayEntities(filtered);
    }

    private void openDetails(int technicianId){
        Technician tech = technicianDAO.getAllTechnicians().stream()
                .filter(t -> t.getTechnicianId() == technicianId)
                .findFirst()
                .orElse(null);

        if(tech != null){
            detailPanel.loadEntityData(tech);
            showPanel("DETAIL_PANEL");
        }
    }

    private void deleteTechnician(int technicianId){
        int confirm = JOptionPane.showConfirmDialog(null,
                "Delete this technician?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if(confirm == JOptionPane.YES_OPTION){
            technicianDAO.setTechnicianStatus(technicianId, "Deleted");
            refreshTechnicianList();
        }
    }
}
