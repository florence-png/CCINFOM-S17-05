
public class HospitalMenuPanel extends AbstractMenuPanel {
    public HospitalMenuPanel() {
        super("Hospital");

        btnAddRecord.setActionCommand("HOSPITAL_SHOW_ADD_FORM");
        btnManageRecord.setActionCommand("HOSPITAL_SHOW_MANAGE_LIST");
        btnReturn.setActionCommand("HOSPITAL_RETURN_TO_MAIN");
    }
}
