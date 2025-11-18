import javax.swing.*;
import java.awt.*;

public class Driver {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Blood Bank Management System");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(900, 700); // Increased size for better layout
            mainFrame.setLocationRelativeTo(null); // Center the frame
            mainFrame.setResizable(true);

            JPanel mainCardPanel = new JPanel(new CardLayout());

            MenuGUI menuGUI = new MenuGUI();
            DonorGUI donorGUI = new DonorGUI();
            TechnicianGUI technicianGUI = new TechnicianGUI();

            mainCardPanel.add(menuGUI, "MENU");
            mainCardPanel.add(donorGUI, "DONOR_GUI");
            mainCardPanel.add(technicianGUI, "TECHNICIAN_GUI");

            MenuController menuController = new MenuController(menuGUI, mainCardPanel);
            DonorController donorController = new DonorController(donorGUI, mainCardPanel);
            TechnicianController technicianController = new TechnicianController(technicianGUI, mainCardPanel);

            mainFrame.setContentPane(mainCardPanel);
            mainFrame.setVisible(true);
        });
    }
}