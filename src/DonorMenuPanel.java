import javax.swing.*;

public class DonorMenuPanel extends AbstractMenuPanel {
    public DonorMenuPanel() {
        super("Donor");

        // You can set specific action commands here
        btnAddRecord.setActionCommand("DONOR_SHOW_ADD_FORM");
        btnManageRecord.setActionCommand("DONOR_SHOW_MANAGE_LIST");
        btnReturn.setActionCommand("DONOR_RETURN_TO_MAIN");
    }
}