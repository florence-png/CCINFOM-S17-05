import java.awt.*;
import javax.swing.*;

public class TransactionsGUI extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JPanel mainTransactionPanel;

    private JButton returnBtn;
    private JButton btnSchedule;
    private JButton btnAssign;
    private JButton btnExtract;
    private JButton btnProcess;

    public TransactionsGUI() {
        super(new BorderLayout());
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        mainTransactionPanel = createMainTransactionPanel();
        cardPanel.add(mainTransactionPanel, "MENU");

        add(cardPanel, BorderLayout.CENTER);
        showPanel("MENU");
    }

    private JPanel createMainTransactionPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Transactions");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Use plain JButtons (default L&F) — use HTML for multiline labels
        btnSchedule = new JButton("<html><center>Schedule an<br>Appointment</center></html>");
        btnAssign = new JButton("<html><center>Assign a<br>Technician</center></html>");
        btnExtract = new JButton("<html><center>Extract Blood</center></html>");
        btnProcess = new JButton("<html><center>Process<br>Hospital<br>Request</center></html>");

        btnSchedule.setActionCommand("BTN_SCHEDULE_APPT");
        btnAssign.setActionCommand("BTN_ASSIGN_TECH");
        btnExtract.setActionCommand("BTN_EXTRACT_BLOOD");
        btnProcess.setActionCommand("BTN_PROCESS_HOSP_REQ");

        // Minimal styling to match other menu buttons: font and centered text
        for (JButton b : new JButton[]{btnSchedule, btnAssign, btnExtract, btnProcess}) {
            b.setFocusPainted(false);
            b.setHorizontalAlignment(SwingConstants.CENTER);
        }

        gridPanel.add(btnSchedule);
        gridPanel.add(btnAssign);
        gridPanel.add(btnExtract);
        gridPanel.add(btnProcess);

        mainPanel.add(gridPanel, BorderLayout.CENTER);

        returnBtn = new JButton("Return to Main Menu");
        returnBtn.setActionCommand("BTN_RETURN_TO_MENU");
        returnBtn.setPreferredSize(new Dimension(340, 50));
        returnBtn.setFocusPainted(false);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(returnBtn);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    public void showPanel(String name) {
        cardLayout.show(cardPanel, name);
    }

    // getters for controllers
    public JButton getReturnButton() { return returnBtn; }
    public JButton getScheduleButton() { return btnSchedule; }
    public JButton getAssignButton() { return btnAssign; }
    public JButton getExtractButton() { return btnExtract; }
    public JButton getProcessButton() { return btnProcess; }
}
