import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class HospitalController {
    private HospitalGUI view;
    private HospitalDAO hospitalDAO;
    private JPanel mainCardPanel;

    public HospitalController(HospitalGUI view, JPanel mainCardPanel) {
        this.view = view;
        this.hospitalDAO = new HospitalDAO();
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
            loadHospitalList();
            view.showPanel("MANAGE_LIST");
        });

        // Return to Main Menu
        menu.getBtnReturn().addActionListener(e -> {
            CardLayout cl = (CardLayout) mainCardPanel.getLayout();
            cl.show(mainCardPanel, "MENU");
        });
    }

    // Add Hospital Panel
    private void setupAddPanel() {
        var add = view.getAddPanel();

        // Save button
        add.getBtnSave().addActionListener(e -> saveHospital());

        // Return to Menu
        add.getBtnReturn().addActionListener(e -> view.showPanel("MENU"));
    }

    private void saveHospital(){
        var add = view.getAddPanel();
        try{
            String hospitalName = add.getTxtHospitalName().getText().trim();
            String street = add.getTxtStreet().getText().trim();
            String city = add.getTxtCity().getText().trim();
            String contact = add.getTxtContactNumber().getText().trim();

            // Save to database
            hospitalDAO.addHospital(hospitalName, street, city, contact);

            JOptionPane.showMessageDialog(null, "Hospital saved successfully!");
            add.clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(),
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Manage Hospital Panel
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
                    deleteHospital(id);
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
        manage.getBtnRefresh().addActionListener(e -> loadHospitalList());
        manage.getBtnSearch().addActionListener(e -> searchHospitals());
    }

    private void openDetails(int hospitalId) {
        Hospital hospital = hospitalDAO.getHospitalById(hospitalId);
        if (hospital != null) {
            view.getDetailPanel().loadEntityData(hospital);
            view.showPanel("DETAIL_PANEL");
        } else {
            JOptionPane.showMessageDialog(null, "Hospital not found.");
        }
    }

    private void deleteHospital(int hospitalId) {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete this hospital?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            hospitalDAO.deleteHospital(hospitalId);
            loadHospitalList(); // Refresh the list
        }
    }

    private void loadHospitalList() {
        List<Hospital> list = hospitalDAO.getAllHospitals();
        view.getManagePanel().displayEntities(list);
    }

    private void searchHospitals() {
        String query = view.getManagePanel().getTxtSearch().getText().trim();
        if(query.isEmpty()){
            loadHospitalList();
            return;
        }

        // Search filter
        List<Hospital> all = hospitalDAO.getAllHospitals();
        List<Hospital> filtered = all.stream()
                .filter(t -> (t.getHospitalName()).toLowerCase().contains(query.toLowerCase()))
                .toList();

        view.getManagePanel().displayEntities(filtered);
    }

    // Detail Panel
    private void setupDetailPanel(){
        var detail = view.getDetailPanel();
        detail.getBtnReturn().addActionListener(e -> view.showPanel("MANAGE_LIST"));
    }
}
