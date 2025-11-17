import javax.swing.*;
import java.awt.*;

public class AbstractMenuPanel extends JPanel {
    protected JButton btnAddRecord;
    protected JButton btnManageRecord;
    protected JButton btnReturn;

    public AbstractMenuPanel(String entityName) {
        super(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        add(createHeader(entityName + " Management"), BorderLayout.NORTH);

        // Center buttons
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 40, 40));
        btnAddRecord = new JButton("Add " + entityName + " Record");
        btnManageRecord = new JButton("Manage " + entityName + " Records");
        centerPanel.add(btnAddRecord);
        centerPanel.add(btnManageRecord);
        add(centerPanel, BorderLayout.CENTER);

        // South button
        btnReturn = new JButton("Return to Main Menu");
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.add(btnReturn);
        add(southPanel, BorderLayout.SOUTH);
    }

    // Getters for controller
    public JButton getBtnAddRecord() { return btnAddRecord; }
    public JButton getBtnManageRecord() { return btnManageRecord; }
    public JButton getBtnReturn() { return btnReturn; }

    // Header helper method
    private JLabel createHeader(String title) {
        JLabel lbl = new JLabel(title, SwingConstants.CENTER);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 28));
        return lbl;
    }
}
