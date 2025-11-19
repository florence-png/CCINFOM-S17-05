import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TransactionsController implements ActionListener {
    
    private TransactionsGUI transactionsGUI;
    private JPanel mainCardPanel;
    
    public TransactionsController(TransactionsGUI transactionsGUI, JPanel mainCardPanel) {
        this.transactionsGUI = transactionsGUI;
        this.mainCardPanel = mainCardPanel;
        addListeners();
    }
    
    private void addListeners() {
        transactionsGUI.getReturnButton().addActionListener(this);
        transactionsGUI.getScheduleButton().addActionListener(this);
        transactionsGUI.getAssignButton().addActionListener(this);
        transactionsGUI.getExtractButton().addActionListener(this);
        transactionsGUI.getProcessButton().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch (command) {
            case "BTN_RETURN_TO_MENU":
                // Go back to main menu
                CardLayout cl = (CardLayout) (mainCardPanel.getLayout());
                cl.show(mainCardPanel, "MENU");
                break;
            case "BTN_SCHEDULE_APPT":
                cl = (CardLayout) (mainCardPanel.getLayout());
                cl.show(mainCardPanel, "SCHED_APPT_GUI"); // open scheduling GUI
                break;
            case "BTN_ASSIGN_TECH":
                cl = (CardLayout) (mainCardPanel.getLayout());
                cl.show(mainCardPanel, "ASSIGN_TECH_GUI"); // open assign technician GUI
                break;
            case "BTN_EXTRACT_BLOOD":
                cl = (CardLayout) (mainCardPanel.getLayout());
                cl.show(mainCardPanel, "EXTRACT_BLOOD_GUI"); // open extraction GUI
                break;
            case "BTN_PROCESS_HOSP_REQ":
                cl = (CardLayout) (mainCardPanel.getLayout());
                cl.show(mainCardPanel, "PROCESS_HOSP_GUI"); // open process hospital request (placeholder)
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }
}