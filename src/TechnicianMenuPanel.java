public class TechnicianMenuPanel extends AbstractMenuPanel {
    public TechnicianMenuPanel() {
        super("Technician");

        btnAddRecord.setActionCommand("TECHNICIAN_SHOW_ADD_FORM");
        btnManageRecord.setActionCommand("TECHNICIAN_SHOW_MANAGE_LIST");
        btnReturn.setActionCommand("TECHNICIAN_RETURN_TO_MAIN");
    }
}