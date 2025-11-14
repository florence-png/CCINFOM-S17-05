import javax.swing.*;
import java.awt.*;

public class Driver {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the main frame
            JFrame mainFrame = new JFrame("Blood Bank Management System");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(500, 500);
            mainFrame.setResizable(true);

            // Create main CardLayout panel
            JPanel mainCardPanel = new JPanel(new CardLayout());

            // Initialize GUIs
            MenuGUI menuGUI = new MenuGUI();
            DonorGUI donorGUI = new DonorGUI();
            TechnicianGUI technicianGUI = new TechnicianGUI();

            // Add GUIs to main CardLayout panel
            mainCardPanel.add(menuGUI, "MENU");
            mainCardPanel.add(donorGUI, "DONOR_GUI");
            mainCardPanel.add(technicianGUI, "TECHNICIAN_GUI");

            // Controllers
            MenuController menuController = new MenuController(menuGUI, mainCardPanel);
            DonorController donorController = new DonorController(donorGUI, mainCardPanel);
            TechnicianController technicianController = new TechnicianController(technicianGUI, mainCardPanel);

            // Show the main frame
            mainFrame.setContentPane(mainCardPanel);
            mainFrame.setVisible(true);
        });
    }
}
