import java.awt.*;
import javax.swing.*;

public class Driver {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Blood Bank Management System");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(600, 480);
            mainFrame.setLocationRelativeTo(null); // Center the frame
            mainFrame.setResizable(true);

            JPanel mainCardPanel = new JPanel(new CardLayout());

            MenuGUI menuGUI = new MenuGUI();
            DonorGUI donorGUI = new DonorGUI();
            TechnicianGUI technicianGUI = new TechnicianGUI();
            TransactionsGUI transactionsGUI = new TransactionsGUI();
            AssignTechGUI assignTechGUI = new AssignTechGUI();
            ExtractBloodGUI extractBloodGUI = new ExtractBloodGUI();
            HospitalGUI hospitalGUI = new HospitalGUI();
            SchedAppointGUI schedAppointGUI = new SchedAppointGUI();

            mainCardPanel.add(menuGUI, "MENU");
            mainCardPanel.add(donorGUI, "DONOR_GUI");
            mainCardPanel.add(technicianGUI, "TECHNICIAN_GUI");
            mainCardPanel.add(transactionsGUI, "TRANSACTIONS_GUI");
            mainCardPanel.add(assignTechGUI, "ASSIGN_TECH_GUI");
            mainCardPanel.add(extractBloodGUI, "EXTRACT_BLOOD_GUI");
            mainCardPanel.add(schedAppointGUI, "SCHED_APPT_GUI");
            mainCardPanel.add(hospitalGUI, "HOSPITAL_GUI");

            MenuController menuController = new MenuController(menuGUI, mainCardPanel);
            DonorController donorController = new DonorController(donorGUI, mainCardPanel);
            TechnicianController technicianController = new TechnicianController(technicianGUI, mainCardPanel);
            TransactionsController transactionsController = new TransactionsController(transactionsGUI, mainCardPanel);
            AssignTechController assignTechController = new AssignTechController(assignTechGUI, mainCardPanel);
            ExtractBloodController extractController = new ExtractBloodController(extractBloodGUI, mainCardPanel);
            ProcHospReqGUI procHospReqGUI = new ProcHospReqGUI();
            SchedAppointController schedController = new SchedAppointController(schedAppointGUI, mainCardPanel);
            HospitalController hospitalController = new HospitalController(hospitalGUI, mainCardPanel);
            mainCardPanel.add(procHospReqGUI, "PROCESS_HOSP_GUI");
            ProcHospReqController procController = new ProcHospReqController(procHospReqGUI, mainCardPanel);

            mainFrame.setContentPane(mainCardPanel);
            mainFrame.setVisible(true);
        });
    }
}