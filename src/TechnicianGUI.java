import javax.swing.*;
import java.awt.*;

public class TechnicianGUI extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    // Specific panels for this entity
    private TechnicianMenuPanel menuPanel;
    private TechnicianAddPanel addPanel;
    private TechnicianManagePanel managePanel;
    private TechnicianDetailPanel detailPanel;

    public TechnicianGUI() {
        super(new BorderLayout());
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create the specific panels
        menuPanel = new TechnicianMenuPanel();
        addPanel = new TechnicianAddPanel();
        managePanel = new TechnicianManagePanel();
        detailPanel = new TechnicianDetailPanel();

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

    // Getters for TechnicianController
    public TechnicianMenuPanel getMenuPanel() { return menuPanel; }
    public TechnicianAddPanel getAddPanel() { return addPanel; }
    public TechnicianManagePanel getManagePanel() { return managePanel; }
    public TechnicianDetailPanel getDetailPanel() { return detailPanel; }
}
