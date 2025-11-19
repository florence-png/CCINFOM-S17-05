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
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }
}