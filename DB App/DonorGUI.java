import javax.swing.*;
import java.awt.*;

public class DonorGUI extends JPanel {
        private CardLayout cardLayout;
        private JPanel cardPanel;

        // Specific panels for this entity
        private DonorMenuPanel menuPanel;
        private AddDonorPanel addPanel;
        private ManageDonorPanel managePanel;
        private DetailDonorPanel detailPanel;

        public DonorGUI() {
            super(new BorderLayout());
            cardLayout = new CardLayout();
            cardPanel = new JPanel(cardLayout);

            // Create the specific panels
            menuPanel = new DonorMenuPanel();
            addPanel = new AddDonorPanel();
            managePanel = new ManageDonorPanel();
            detailPanel = new DetailDonorPanel();

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

        // Getters for DonorController
        public DonorMenuPanel getMenuPanel() { return menuPanel; }
        public AddDonorPanel getAddPanel() { return addPanel; }
        public ManageDonorPanel getManagePanel() { return managePanel; }
        public DetailDonorPanel getDetailPanel() { return detailPanel; }
}
