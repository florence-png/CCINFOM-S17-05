import javax.swing.*;
import java.awt.*;

public class HospitalGUI extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    // Specific panels for this entity
    private HospitalMenuPanel menuPanel;
    private HospitalAddPanel addPanel;
    private HospitalManagePanel managePanel;
    private HospitalDetailPanel detailPanel;

    public HospitalGUI() {
        super(new BorderLayout());
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create the specific panels
        menuPanel = new HospitalMenuPanel();
        addPanel = new HospitalAddPanel();
        managePanel = new HospitalManagePanel();
        detailPanel = new HospitalDetailPanel();

        // Add them to the CardLayout
        cardPanel.add(menuPanel, "MENU");
        cardPanel.add(addPanel, "ADD_FORM");
        cardPanel.add(managePanel, "MANAGE_LIST");
        cardPanel.add(detailPanel, "DETAIL_PANEL");

        add(cardPanel, BorderLayout.CENTER);
        showPanel("MENU");
    }

    public void showPanel(String name) {
        cardLayout.show(cardPanel, name);
    }

    // Getters for HospitalController
    public HospitalMenuPanel getMenuPanel() { return menuPanel; }
    public HospitalAddPanel getAddPanel() { return addPanel; }
    public HospitalManagePanel getManagePanel() { return managePanel; }
    public HospitalDetailPanel getDetailPanel() { return detailPanel; }
}